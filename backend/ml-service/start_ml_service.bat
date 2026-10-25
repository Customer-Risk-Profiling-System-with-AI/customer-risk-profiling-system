@echo off
echo ========================================================
echo Starting Python Customer Risk Profiling ML Service
echo ========================================================
cd /d "%~dp0"
python -m uvicorn app:app --host 127.0.0.1 --port 8000
pause
