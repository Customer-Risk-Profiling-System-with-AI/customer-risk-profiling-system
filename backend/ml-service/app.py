import pickle
import pandas as pd
from fastapi import FastAPI

app = FastAPI()

with open("model.pkl", "rb") as f:
    model = pickle.load(f)

@app.post("/predict")
def predict(data: dict):
    df = pd.DataFrame([data])
    result = model.predict(df)[0]

    return {
        "risk_score": float(result)
    }