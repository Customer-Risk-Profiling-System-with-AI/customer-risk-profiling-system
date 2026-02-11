✅ What This Dataset Includes

Size & Scope





~5,000,000 transaction records# Dataset Analysis: Nigerian Financial Transactions and Fraud Detection

## 📋 Dataset Overview

**Dataset Name:** Nigerian Financial Transactions and Fraud Detection Dataset  
**Source:** [Hugging Face - Electric Sheep Africa](https://huggingface.co/datasets/electricsheepafrica/Nigerian-Financial-Transactions-and-Fraud-Detection-Dataset)  
**Domain:** Financial Services - Fraud Detection  
**Region:** Nigeria (NGN currency, local payment methods)

---

## 📊 Dataset Specifications

### Size & Scope

| Metric | Value |
|--------|-------|
| **Total Records** | ~5,000,000 transactions |
| **Feature Columns** | ~45 distinct features |
| **Time Period** | 12-month simulated period |
| **Fraud Ratio** | ~15% (realistic imbalance) |
| **Currency** | Nigerian Naira (NGN) |
| **Data Type** | Synthetic financial transactions |

### Key Characteristics

- ✅ Large-scale dataset suitable for robust ML model training
- ✅ Region-specific (Nigerian financial context)
- ✅ Realistic fraud distribution for production scenarios
- ✅ Rich feature engineering with behavioral and risk indicators
- ✅ Multiple fraud types for granular analysis

---

## 🎯 Target Variables

### Fraud Labels

| Feature | Type | Description |
|---------|------|-------------|
| `is_fraud` | Binary | Primary target variable (0 = legitimate, 1 = fraudulent) |
| `fraud_type` | Categorical | Specific fraud category (account takeover, identity fraud, etc.) |

**Use Cases:**
- Binary classification models
- Multi-class fraud detection
- Cost-sensitive learning approaches
- Model evaluation metrics (ROC-AUC, Precision-Recall)

---

## 🔍 Feature Categories

### 1️⃣ Core Transaction Fields

**Purpose:** Basic transaction identifiers and metadata

| Feature | Description | ML Relevance |
|---------|-------------|--------------|
| `transaction_id` | Unique transaction identifier | Tracking & debugging |
| `timestamp` | Transaction date/time | Temporal pattern analysis |
| `amount_ngn` | Transaction amount in NGN | Primary fraud signal |
| `transaction_channel` | Payment method (USSD, mobile app, card) | Channel-based risk profiling |
| `sender_account_id` | Sender account identifier | User behavior tracking |
| `receiver_account_id` | Receiver account identifier | Network analysis |
| `merchant_category` | Type of merchant | Category-based risk scoring |

**Status:** ✅ **Fully Available**

---

### 2️⃣ User Behavior Features

**Purpose:** Historical account activity and behavioral patterns

| Feature | Description | Fraud Detection Value |
|---------|-------------|----------------------|
| `avg_transaction_amount` | Average historical transaction size | Deviation detection |
| `account_age_days` | Days since account creation | New account fraud risk |
| `transaction_velocity` | Rate of recent transactions | Velocity-based anomalies |
| `transactions_last_1h` | Transaction count in last hour | Burst activity detection |
| `transactions_last_24h` | Transaction count in last 24 hours | Daily activity monitoring |
| `behavioral_risk_score` | Aggregated behavioral risk metric | Pre-computed risk signal |

**Status:** ✅ **Fully Available**

**Key Strength:** Enables detection of sudden behavioral deviations from normal user patterns.

---

### 3️⃣ Temporal & Velocity Features

**Purpose:** Time-based patterns and rapid transaction sequences

| Feature | Description | Detection Use Case |
|---------|-------------|-------------------|
| `time_since_last_transaction` | Seconds since previous transaction | Rapid-fire fraud detection |
| `transaction_hour` | Hour of day (0-23) | Off-hours activity patterns |
| `is_night_transaction` | Boolean flag for late-night transactions | Unusual timing detection |
| Rolling window counters | 1h/24h transaction aggregates | Velocity abuse monitoring |

**Status:** ✅ **Fully Available**

**Application:** Critical for real-time fraud detection systems and rule-based filters.

---

### 4️⃣ Device & Network Features

**Purpose:** Device fingerprinting and account takeover detection

| Feature | Description | Security Application |
|---------|-------------|---------------------|
| `device_id` | Unique device identifier | Device tracking |
| `ip_address` | Transaction IP address | Geolocation & VPN detection |
| `is_new_device` | First-time device flag | New device risk assessment |
| `shared_device_count` | Number of accounts using device | Device sharing anomalies |
| `shared_ip_count` | Number of accounts from IP | IP-based fraud rings |
| `device_risk_score` | Aggregated device risk metric | Device reputation scoring |

**Status:** ✅ **Fully Available**

**Critical For:** Account takeover (ATO) detection, credential stuffing prevention, multi-accounting fraud.

---

### 5️⃣ Geographical & Location Features

**Purpose:** Location-based anomaly detection and impossible travel patterns

| Feature | Description | Fraud Signal |
|---------|-------------|--------------|
| `city` | Transaction city | Location consistency |
| `state` | Transaction state/region | Regional fraud patterns |
| `location_risk_score` | Geographic risk metric | High-risk location flagging |
| `geo_anomaly_flag` | Location inconsistency indicator | Impossible travel detection |

**Status:** ✅ **Available with Engineered Signals**

**Use Case:** Detecting transactions from unusual locations or physically impossible location changes.

---

### 6️⃣ Merchant & Channel Risk Features

**Purpose:** Risk profiling based on merchant and payment channel

| Feature | Description | Risk Assessment |
|---------|-------------|-----------------|
| `merchant_category` | Type of business (retail, online, etc.) | Category-based fraud rates |
| `merchant_risk_score` | Merchant reputation metric | High-risk merchant flagging |
| `channel_risk_score` | Payment channel risk level | Channel-specific fraud patterns |

**Status:** ✅ **Fully Available**

**Application:** Risk-weighted scoring models, channel-specific fraud rules.

---

### 7️⃣ Risk Scoring & Composite Features

**Purpose:** Pre-computed risk indicators for explainable AI

| Feature | Description | Model Integration |
|---------|-------------|-------------------|
| `overall_risk_score` | Composite risk metric | Primary risk indicator |
| `behavioral_risk_score` | User behavior risk | Behavioral component |
| `device_risk_score` | Device-based risk | Device component |
| `location_risk_score` | Geographic risk | Location component |
| `persona_risk_score` | User profile risk | User segment risk |

**Status:** ✅ **Fully Available**

**Advantage:** Supports hybrid rule-ML systems and provides explainability for model decisions.

---

## ✅ Feature Alignment with Project Requirements

### Comparison Against Project Needs

| Required Feature Category | Dataset Coverage | Status |
|---------------------------|------------------|--------|
| Transaction-level features | Complete (ID, timestamp, amount, channel, accounts) | ✅ **100%** |
| User behavior features | Complete (avg amount, frequency, velocity, patterns) | ✅ **100%** |
| Temporal & velocity features | Complete (time gaps, rolling windows, off-hours flags) | ✅ **100%** |
| Device & network features | Complete (device ID, IP, new device, sharing metrics) | ✅ **100%** |
| Geographical features | Complete (city, state, risk scores, anomaly flags) | ✅ **100%** |
| Merchant & channel risk | Complete (category, risk scores) | ✅ **100%** |
| Fraud labels | Complete (binary + multi-class) | ✅ **100%** |
| Risk scoring features | Complete (pre-computed risk indicators) | ✅ **100%** |

### Overall Assessment

**✅ This dataset contains ALL essential features required for the Transaction Risk Profiling / Fraud Detection project.**

---

## 💪 Dataset Strengths

### For Machine Learning Projects

1. **Large Scale**
   - 5M records provide sufficient data for training deep learning models
   - Supports proper train/validation/test splits without data leakage

2. **Rich Feature Engineering**
   - Behavioral analytics (velocity, frequency, patterns)
   - Temporal dynamics (rolling windows, time gaps)
   - Risk scores (pre-computed composite metrics)

3. **Realistic Fraud Distribution**
   - ~15% fraud ratio mimics real-world imbalance
   - Not too extreme for minority class learning
   - Suitable for evaluation metrics like Precision-Recall AUC

4. **Multi-dimensional Fraud Signals**
   - Device-based (account takeover detection)
   - Location-based (impossible travel)
   - Behavioral (velocity anomalies)
   - Temporal (off-hours transactions)

5. **Supervised Learning Ready**
   - Clear binary labels for classification
   - Fraud types for multi-class analysis
   - Enables model performance benchmarking

6. **Nigerian Financial Context**
   - Local payment channels (USSD, mobile money)
   - Regional cities and IP ranges
   - NGN currency transactions

7. **Explainability Support**
   - Pre-computed risk scores enable transparent decision-making
   - Feature importance analysis possible
   - Rule-based system integration feasible

---

## ⚠️ Dataset Limitations

### Known Constraints

| Limitation | Impact | Mitigation Strategy |
|------------|--------|---------------------|
| **Synthetic Data** | Patterns are realistic but not real production data | Validate models on real data when deploying; use as benchmark |
| **Regional Specificity** | Designed for Nigeria; may not generalize globally | Fine-tune for other regions; consider transfer learning |
| **12-Month Period** | Limited temporal coverage | Acceptable for most ML projects; note in documentation |
| **Missing KYC Details** | No real biometric/ID verification data | Expected in public datasets; not critical for pattern-based ML |
| **No Financial Context** | Account balances not included | Focus on behavioral/transactional patterns instead |
| **No Demographics** | Age, gender not available | Use behavioral proxies; personas available |

### What This Dataset Does NOT Include

❌ Real KYC data (National ID, passport, biometrics)  
❌ Account balance before/after transaction  
❌ True customer demographics (age, gender, income)  
❌ Cross-bank or cross-platform transaction linkage  
❌ Real merchant business registration details  

**Note:** These gaps are expected and acceptable for public academic/research datasets.

---

## 🎯 Recommended Use Cases

### 1. Supervised Classification
- Binary fraud detection (is_fraud)
- Multi-class fraud type prediction (fraud_type)
- Probability scoring for risk ranking

### 2. Anomaly Detection
- Unsupervised outlier detection
- Autoencoders for behavioral anomalies
- Isolation forests for transaction outliers

### 3. Feature Engineering
- Temporal aggregation techniques
- Interaction feature creation
- Dimensionality reduction (PCA, feature selection)

### 4. Imbalanced Learning
- SMOTE and over/under-sampling
- Cost-sensitive learning
- Ensemble methods (balanced random forests)

### 5. Explainable AI
- Feature importance analysis (SHAP, LIME)
- Rule extraction from models
- Risk score interpretation

### 6. Real-Time Systems
- Streaming feature computation
- Low-latency scoring pipelines
- Rule-based pre-filtering

---

## 🧪 Suggested ML Approaches

### Classical Models
- Logistic Regression (baseline)
- Random Forest (ensemble)
- XGBoost / LightGBM (gradient boosting)
- Support Vector Machines (SVM)

### Deep Learning
- Feedforward Neural Networks
- Autoencoders (anomaly detection)
- LSTM (temporal sequence modeling)
- Transformer-based models

### Hybrid Approaches
- Rule-based pre-filtering + ML scoring
- Risk score thresholds + model predictions
- Ensemble of multiple model types

### Recommended Pipeline
```
1. Exploratory Data Analysis (EDA)
2. Feature engineering & selection
3. Handling class imbalance (SMOTE/undersampling)
4. Model training (multiple algorithms)
5. Hyperparameter tuning (grid/random search)
6. Evaluation (ROC-AUC, PR-AUC, F1, cost metrics)
7. Model interpretation (SHAP values)
8. Production deployment considerations
```

---

## 📈 Evaluation Metrics

### Recommended Metrics for Fraud Detection

| Metric | Why Important |
|--------|---------------|
| **Precision-Recall AUC** | Better than ROC-AUC for imbalanced datasets |
| **F1-Score** | Balances precision and recall |
| **Recall @ k** | Ensures catching most fraud cases |
| **Precision @ k** | Minimizes false positives for investigations |
| **Cost-based metrics** | Account for financial impact of false negatives vs. false positives |
| **Confusion Matrix** | Understand FP/FN trade-offs |

### Class Imbalance Handling

Given ~15% fraud ratio:
- ✅ Use stratified sampling for train/test splits
- ✅ Consider SMOTE or ADASYN for minority class
- ✅ Apply class weights in model training
- ✅ Evaluate using Precision-Recall curves, not just accuracy

---

## 💡 Project Recommendations

### For Your Fraud Detection Project

**✅ Use this dataset for:**
- Model training and testing
- Feature engineering experimentation
- Algorithm benchmarking
- Proof-of-concept development

**✅ Combine with:**
- Stratified sampling techniques
- Cost-sensitive learning
- Ensemble methods
- Explainability frameworks (SHAP, LIME)

**✅ Validate with:**
- Cross-validation (stratified k-fold)
- Temporal validation (time-based splits)
- Real transaction logs (if available in production)

**✅ Document:**
- Synthetic nature of data in limitations
- Regional specificity (Nigeria)
- Model performance on this benchmark

---

## 🔗 Dataset Access

**Primary Source:**  
[https://huggingface.co/datasets/electricsheepafrica/Nigerian-Financial-Transactions-and-Fraud-Detection-Dataset](https://huggingface.co/datasets/electricsheepafrica/Nigerian-Financial-Transactions-and-Fraud-Detection-Dataset)

**Loading Example:**
```python
from datasets import load_dataset

# Load the dataset
dataset = load_dataset("electricsheepafrica/Nigerian-Financial-Transactions-and-Fraud-Detection-Dataset")

# Access as pandas DataFrame
import pandas as pd
df = dataset['train'].to_pandas()
```

---

## 📝 Conclusion

### Final Assessment

**✅ This dataset is HIGHLY SUITABLE for the Transaction Risk Profiling / Fraud Detection project.**

**Justification:**
- Contains all required feature categories (100% coverage)
- Provides 5M records with realistic fraud distribution
- Includes pre-engineered features for behavioral, temporal, device, and location analysis
- Supports both supervised classification and anomaly detection approaches
- Enables explainable AI through risk scoring features
- Sufficient scale for robust model development

**Confidence Level:** **HIGH** ⭐⭐⭐⭐⭐

This dataset will serve as an excellent foundation for building, training, and benchmarking fraud detection models. The synthetic nature is clearly documented and acceptable for academic/research projects.

---
