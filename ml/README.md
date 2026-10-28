# ML

Machine learning models and data processing for the Customer Risk Profiling System.

## Overview

This folder contains ML models, training scripts, and data pipelines.

- `train_model.py` trains the Random Forest model and exports it as a `.pkl` file
- `fraud_detection_model.pkl` is the trained model used by the ML service
- `fraudDetection.ipynb` is a Colab notebook that compares Logistic Regression, Random Forest, XGBoost and LightGBM
- `Objectives.md`, `ML_Techniqes.md` and `DataSet Analysis.md` document the fraud objectives, the modelling strategy and the dataset

The trained model is served by the Python FastAPI service in `backend/ml-service`, which the `risk-score-service` calls to get a risk score (0-100) and a risk level (Low / Medium / High).

## Dataset Overview

- **Dataset Name:** Nigerian Financial Transactions and Fraud Detection Dataset
- **Source:** [Hugging Face - Electric Sheep Africa](https://huggingface.co/datasets/electricsheepafrica/Nigerian-Financial-Transactions-and-Fraud-Detection-Dataset)
- **Domain:** Financial Services - Fraud Detection
- **Region:** Nigeria (NGN currency, local payment methods)

## Getting Started

### Install dependencies

```bash
pip install numpy pandas scikit-learn joblib
```

Use the same scikit-learn version for training and serving, because `.pkl` files depend on it.

### Train the model

From the `ml/` folder:

```bash
python train_model.py
```

This saves the model to `ml/fraud_detection_model.pkl` and a copy to `backend/ml-service/model.pkl`.

### Run the ML service

From `backend/ml-service/`:

```bash
python -m pip install -r requirements.txt
python -m uvicorn app:app --host 127.0.0.1 --port 8000
```

The service exposes `GET /health` and `POST /predict`. To check the connection, run `python test_ml_connection.py` from the same folder.

### Model input features

The model takes 14 customer-level features calculated over 30 days:

`txCount30d`, `totalAmount30d`, `avgTxnAmount30d`, `maxTxnAmount30d`, `amountStddev30d`, `spendVelocity7dVsPrev7d`, `txVelocity7dVsPrev7d`, `ecomRatio30d`, `posRatio30d`, `atmRatio30d`, `uniqueMerchantCount30d`, `riskyMerchantCount30d`, `newDeviceFlagRecent`, `newCountryFlagRecent`

### Run the notebook (optional)

`fraudDetection.ipynb` is written for Google Colab and needs a Hugging Face token saved as a Colab secret named `HF_TOKEN`. It also needs `datasets`, `huggingface_hub`, `xgboost` and `lightgbm`.
