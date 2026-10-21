import os
import joblib
import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import StandardScaler
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import classification_report, roc_auc_score

FEATURE_NAMES = [
    "txCount30d",
    "totalAmount30d",
    "avgTxnAmount30d",
    "maxTxnAmount30d",
    "amountStddev30d",
    "spendVelocity7dVsPrev7d",
    "txVelocity7dVsPrev7d",
    "ecomRatio30d",
    "posRatio30d",
    "atmRatio30d",
    "uniqueMerchantCount30d",
    "riskyMerchantCount30d",
    "newDeviceFlagRecent",
    "newCountryFlagRecent"
]

def generate_synthetic_behavior_data(n_samples=6000, random_state=42):
    """
    Generates realistic customer financial transaction features matching FeatureDTO.
    """
    np.random.seed(random_state)
    
    # 82% normal customers, 18% high-risk / fraudulent profiles
    n_fraud = int(n_samples * 0.18)
    n_normal = n_samples - n_fraud
    
    # --- Normal Customers ---
    norm_tx_count = np.random.poisson(lam=18, size=n_normal) + 1
    norm_avg_txn = np.random.gamma(shape=3.0, scale=35.0, size=n_normal) + 10.0
    norm_total_amount = norm_tx_count * norm_avg_txn * np.random.uniform(0.9, 1.1, size=n_normal)
    norm_max_txn = norm_avg_txn * np.random.uniform(1.2, 2.5, size=n_normal)
    norm_stddev = norm_avg_txn * np.random.uniform(0.15, 0.45, size=n_normal)
    norm_spend_velocity = np.random.normal(loc=1.0, scale=0.25, size=n_normal).clip(0.1, 2.0)
    norm_tx_velocity = np.random.normal(loc=1.0, scale=0.20, size=n_normal).clip(0.1, 1.8)
    
    # Channel distribution
    norm_ecom = np.random.beta(a=2, b=3, size=n_normal)
    norm_pos = np.random.beta(a=3, b=2, size=n_normal) * (1 - norm_ecom)
    norm_atm = 1.0 - (norm_ecom + norm_pos)
    
    norm_unique_merch = np.clip(np.random.poisson(lam=6, size=n_normal), 1, norm_tx_count)
    norm_risky_merch = np.random.choice([0, 1], size=n_normal, p=[0.96, 0.04])
    norm_new_device = np.random.choice([0, 1], size=n_normal, p=[0.92, 0.08])
    norm_new_country = np.random.choice([0, 1], size=n_normal, p=[0.97, 0.03])
    norm_labels = np.zeros(n_normal, dtype=int)
    
    # --- High-Risk / Fraudulent Profiles ---
    fraud_tx_count = np.random.poisson(lam=32, size=n_fraud) + 5
    fraud_avg_txn = np.random.gamma(shape=5.0, scale=95.0, size=n_fraud) + 50.0
    fraud_total_amount = fraud_tx_count * fraud_avg_txn * np.random.uniform(1.2, 2.0, size=n_fraud)
    fraud_max_txn = fraud_avg_txn * np.random.uniform(3.0, 7.5, size=n_fraud)  # Big spikes
    fraud_stddev = fraud_avg_txn * np.random.uniform(0.6, 1.8, size=n_fraud)
    fraud_spend_velocity = np.random.normal(loc=3.2, scale=1.1, size=n_fraud).clip(1.8, 8.0) # High velocity
    fraud_tx_velocity = np.random.normal(loc=2.5, scale=0.8, size=n_fraud).clip(1.5, 6.0)
    
    fraud_ecom = np.random.beta(a=4, b=1, size=n_fraud)  # mostly ecom
    fraud_pos = np.random.beta(a=1, b=3, size=n_fraud) * (1 - fraud_ecom)
    fraud_atm = 1.0 - (fraud_ecom + fraud_pos)
    
    fraud_unique_merch = np.clip(np.random.poisson(lam=12, size=n_fraud), 2, fraud_tx_count)
    fraud_risky_merch = np.random.choice([1, 2, 3, 4], size=n_fraud, p=[0.45, 0.35, 0.15, 0.05])
    fraud_new_device = np.random.choice([0, 1], size=n_fraud, p=[0.25, 0.75])
    fraud_new_country = np.random.choice([0, 1], size=n_fraud, p=[0.40, 0.60])
    fraud_labels = np.ones(n_fraud, dtype=int)
    
    # Combine
    X_dict = {
        "txCount30d": np.concatenate([norm_tx_count, fraud_tx_count]),
        "totalAmount30d": np.concatenate([norm_total_amount, fraud_total_amount]),
        "avgTxnAmount30d": np.concatenate([norm_avg_txn, fraud_avg_txn]),
        "maxTxnAmount30d": np.concatenate([norm_max_txn, fraud_max_txn]),
        "amountStddev30d": np.concatenate([norm_stddev, fraud_stddev]),
        "spendVelocity7dVsPrev7d": np.concatenate([norm_spend_velocity, fraud_spend_velocity]),
        "txVelocity7dVsPrev7d": np.concatenate([norm_tx_velocity, fraud_tx_velocity]),
        "ecomRatio30d": np.concatenate([norm_ecom, fraud_ecom]),
        "posRatio30d": np.concatenate([norm_pos, fraud_pos]),
        "atmRatio30d": np.concatenate([norm_atm, fraud_atm]),
        "uniqueMerchantCount30d": np.concatenate([norm_unique_merch, fraud_unique_merch]),
        "riskyMerchantCount30d": np.concatenate([norm_risky_merch, fraud_risky_merch]),
        "newDeviceFlagRecent": np.concatenate([norm_new_device, fraud_new_device]),
        "newCountryFlagRecent": np.concatenate([norm_new_country, fraud_new_country]),
    }
    
    df = pd.DataFrame(X_dict)
    y = np.concatenate([norm_labels, fraud_labels])
    return df, y

def train_and_export():
    print("=== Training Customer Risk Profiling & Fraud Detection Model ===")
    df, y = generate_synthetic_behavior_data(n_samples=8000, random_state=42)
    
    X_train, X_test, y_train, y_test = train_test_split(df, y, test_size=0.2, random_state=42, stratify=y)
    
    print(f"Dataset shape: {df.shape}, Train samples: {len(X_train)}, Test samples: {len(X_test)}")
    print(f"Features: {FEATURE_NAMES}")
    
    # Build ML pipeline
    pipeline = Pipeline(steps=[
        ("scaler", StandardScaler()),
        ("classifier", RandomForestClassifier(
            n_estimators=150,
            max_depth=12,
            min_samples_split=4,
            class_weight="balanced",
            random_state=42,
            n_jobs=-1
        ))
    ])
    
    print("Fitting model...")
    pipeline.fit(X_train, y_train)
    
    # Evaluation
    test_probs = pipeline.predict_proba(X_test)[:, 1]
    test_preds = (test_probs >= 0.5).astype(int)
    
    auc = roc_auc_score(y_test, test_probs)
    print(f"\nModel ROC-AUC Score: {auc:.4f}")
    print("\nClassification Report:")
    print(classification_report(y_test, test_preds, target_names=["Normal", "Fraud/High-Risk"]))
    
    # Feature importances
    rf = pipeline.named_steps["classifier"]
    importances = sorted(zip(FEATURE_NAMES, rf.feature_importances_), key=lambda x: x[1], reverse=True)
    print("\nFeature Importances:")
    for feat, imp in importances:
        print(f"  - {feat:25s}: {imp:.4f}")
    
    # Export paths
    script_dir = os.path.dirname(os.path.abspath(__file__))
    ml_pkl_path = os.path.join(script_dir, "fraud_detection_model.pkl")
    backend_ml_dir = os.path.abspath(os.path.join(script_dir, "..", "backend", "ml-service"))
    backend_pkl_path = os.path.join(backend_ml_dir, "model.pkl")
    
    print(f"\nSaving model to {ml_pkl_path}...")
    joblib.dump(pipeline, ml_pkl_path)
    
    if os.path.exists(backend_ml_dir):
        print(f"Saving copy to {backend_pkl_path}...")
        joblib.dump(pipeline, backend_pkl_path)
    
    print("\nModel training and .pkl serialization complete!")

if __name__ == "__main__":
    train_and_export()
