# Deployment

## Recommended architecture

- **Frontend:** deploy the Vite app as a static site on Vercel.
- **Backend:** deploy the API gateway, transaction service, risk-score service,
  and ML service as separate Docker web services on Render.
- **Database:** use an external PostgreSQL provider and supply the connection
  settings to the transaction and risk-score services.

The gateway is the only backend URL exposed to the frontend. Backend services
use Render private service hostnames.

## Render deployment

1. Push this repository to GitHub.
2. In Render, choose **New > Blueprint** and select the repository.
3. Render detects [`render.yaml`](./render.yaml) and creates four Docker
   services.
4. Set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` for both database-backed
   services. `DB_URL` must include the database name, for example:
   `jdbc:postgresql://host:5432/customer_risk`.
5. Verify:
   - `https://customer-risk-gateway.onrender.com/actuator/health`
   - `https://customer-risk-ml.onrender.com/health`

Free Render services sleep when idle, so the first request may be slow.

## Frontend deployment on Vercel

Create a Vercel project from this repository with:

- **Root Directory:** `frontend`
- **Build Command:** `npm run build`
- **Output Directory:** `dist`
- **Environment variable:** `VITE_API_BASE=https://customer-risk-gateway.onrender.com/api`

Do not include a trailing slash in `VITE_API_BASE`.

## Local Docker build

Run from the repository root:

```bat
docker build -f backend\api-gateway\Dockerfile -t customer-risk-gateway .
docker build -f backend\transaction-service\Dockerfile -t customer-risk-transaction .
docker build -f backend\risk-score-service\Dockerfile -t customer-risk-score .
docker build -f backend\ml-service\Dockerfile -t customer-risk-ml .
```
