FROM python:3.11-slim
WORKDIR /app
COPY backend/ml-service/requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY backend/ml-service/app.py .
COPY ml/fraud_detection_model.pkl /ml/fraud_detection_model.pkl
ENV PYTHONUNBUFFERED=1
EXPOSE 8000
CMD ["uvicorn", "app:app", "--host", "0.0.0.0", "--port", "8000"]
