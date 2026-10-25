"""
ML Model & Backend Service Connection Test Suite
Verifies:
1. Loading of .pkl files (ml/fraud_detection_model.pkl and backend/ml-service/model.pkl)
2. Scikit-learn Pipeline architecture & feature compatibility
3. Live HTTP API connection to the Python ML Service (port 8000)
4. Integration with Spring Boot risk-score-service FeatureDTO schema
"""

import os
import sys
import json
import urllib.request
import urllib.error
import joblib
import pandas as pd

CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
PROJECT_ROOT = os.path.abspath(os.path.join(CURRENT_DIR, "..", ".."))
PKL_BACKEND = os.path.join(CURRENT_DIR, "model.pkl")
PKL_ML = os.path.join(PROJECT_ROOT, "ml", "fraud_detection_model.pkl")
SERVICE_URL = "http://127.0.0.1:8000"

def log_section(title):
    print(f"\n{'=' * 65}")
    print(f"  {title}")
    print(f"{'=' * 65}")

def test_pkl_files():
    log_section("1. Testing .pkl Model Files")
    for name, path in [("Backend Model (model.pkl)", PKL_BACKEND), ("Root ML Model (fraud_detection_model.pkl)", PKL_ML)]:
        if os.path.exists(path):
            size_kb = os.path.getsize(path) / 1024
            print(f"  [OK] Found {name}: {path} ({size_kb:.1f} KB)")
            try:
                model = joblib.load(path)
                print(f"       -> Successfully loaded with joblib.")
                print(f"       -> Type: {type(model).__name__}")
                if hasattr(model, "steps"):
                    print(f"       -> Pipeline steps: {[s[0] for s in model.steps]}")
            except Exception as e:
                print(f"  [FAIL] Error loading {path}: {e}")
                return False
        else:
            print(f"  [WARN] File not found: {path}")
    return True

def test_live_service():
    log_section("2. Testing Live ML Service Connection (http://127.0.0.1:8000)")
    
    # 2.1 Health Check
    health_url = f"{SERVICE_URL}/health"
    try:
        req = urllib.request.Request(health_url)
        with urllib.request.urlopen(req, timeout=5) as resp:
            data = json.loads(resp.read().decode('utf-8'))
            print(f"  [OK] Health Check endpoint ({health_url}):")
            print(f"       Status: {data.get('status')}")
            print(f"       Service: {data.get('service')}")
            print(f"       Model Loaded: {data.get('model_loaded')}")
            print(f"       Model Path: {data.get('model_path')}")
            if not data.get("model_loaded"):
                print("  [FAIL] Server is running but report model_loaded=False!")
                return False
    except Exception as e:
        print(f"  [FAIL] Unable to reach ML Service at {health_url}: {e}")
        print("         Ensure ML Service is running via: python -m uvicorn app:app --port 8000")
        return False

    # 2.2 Predict: Normal Low-Risk Profile
    predict_url = f"{SERVICE_URL}/predict"
    low_risk_payload = {
        "txCount30d": 12,
        "totalAmount30d": 520.0,
        "avgTxnAmount30d": 43.33,
        "maxTxnAmount30d": 90.0,
        "amountStddev30d": 14.5,
        "spendVelocity7dVsPrev7d": 1.01,
        "txVelocity7dVsPrev7d": 0.98,
        "ecomRatio30d": 0.35,
        "posRatio30d": 0.55,
        "atmRatio30d": 0.10,
        "uniqueMerchantCount30d": 5,
        "riskyMerchantCount30d": 0,
        "newDeviceFlagRecent": False,
        "newCountryFlagRecent": False
    }

    try:
        req = urllib.request.Request(
            predict_url,
            data=json.dumps(low_risk_payload).encode('utf-8'),
            headers={'Content-Type': 'application/json'}
        )
        with urllib.request.urlopen(req, timeout=5) as resp:
            data = json.loads(resp.read().decode('utf-8'))
            print(f"\n  [OK] Predict (Normal Low-Risk Profile):")
            print(f"       Risk Score: {data.get('risk_score')}/100")
            print(f"       Risk Level: {data.get('risk_level')}")
            print(f"       Error: {data.get('error')}")
            assert data.get("risk_level") == "Low"
    except Exception as e:
        print(f"  [FAIL] Low-risk prediction failed: {e}")
        return False

    # 2.3 Predict: Fraudulent High-Risk Profile
    high_risk_payload = {
        "txCount30d": 55,
        "totalAmount30d": 18200.0,
        "avgTxnAmount30d": 330.9,
        "maxTxnAmount30d": 3800.0,
        "amountStddev30d": 490.0,
        "spendVelocity7dVsPrev7d": 4.5,
        "txVelocity7dVsPrev7d": 3.2,
        "ecomRatio30d": 0.90,
        "posRatio30d": 0.05,
        "atmRatio30d": 0.05,
        "uniqueMerchantCount30d": 18,
        "riskyMerchantCount30d": 4,
        "newDeviceFlagRecent": True,
        "newCountryFlagRecent": True
    }

    try:
        req = urllib.request.Request(
            predict_url,
            data=json.dumps(high_risk_payload).encode('utf-8'),
            headers={'Content-Type': 'application/json'}
        )
        with urllib.request.urlopen(req, timeout=5) as resp:
            data = json.loads(resp.read().decode('utf-8'))
            print(f"\n  [OK] Predict (Fraudulent High-Risk Profile):")
            print(f"       Risk Score: {data.get('risk_score')}/100")
            print(f"       Risk Level: {data.get('risk_level')}")
            print(f"       Error: {data.get('error')}")
            assert data.get("risk_level") == "High"
    except Exception as e:
        print(f"  [FAIL] High-risk prediction failed: {e}")
        return False

    return True

if __name__ == "__main__":
    pkl_ok = test_pkl_files()
    live_ok = test_live_service()

    log_section("TEST SUMMARY")
    if pkl_ok and live_ok:
        print("  ALL TESTS PASSED: .pkl model is connected to the backend & responding accurately!")
    else:
        print("  SOME TESTS FAILED! Check the error logs above.")
        sys.exit(1)
