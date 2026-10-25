# Python ML Service

This directory contains the only runtime ML service for the system. It is a
FastAPI application that loads the serialized scikit-learn pipeline at startup.
There is no Spring Boot application in this service.

## Start locally

From this directory, install the Python dependencies and start the service:

```bat
python -m pip install -r requirements.txt
python -m uvicorn app:app --host 127.0.0.1 --port 8000
```

On Windows, `start_ml_service.bat` runs the second command.

The service exposes:

- `GET /health` - reports whether the `.pkl` model was loaded
- `POST /predict` - accepts the risk features and returns `risk_score`,
  `risk_level`, and `error`

The `risk-score-service` calls this service directly at
`http://127.0.0.1:8000`, configured in its `application.properties`.

## Verify the service

With the service running, execute:

```bat
python test_ml_connection.py
```

The model is loaded automatically when `app.py` starts. Do not run the `.pkl`
file directly.
