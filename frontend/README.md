# Frontend

React + Vite UI for the Customer Risk Profiling System. This app provides the analyst dashboard, risk views, and management screens using reusable layout and dashboard components.

## Tech Stack

- React 19
- React Router
- Tailwind CSS (via PostCSS)
- Recharts for charts
- Lucide icons
- Vite + ESLint

## Getting Started

```bash
npm install
npm run dev
```

Other scripts:

```bash
npm run build
npm run preview
npm run lint
```

## Routes

- / (Dashboard)
- /customers
- /risk-analysis
- /alerts
- /settings

All routes are rendered inside the shared layout (sidebar + header).

## Key Screens

- Dashboard: KPI cards, risk chart, recent alerts, and quick actions.
- Customers: search, filters, and a detailed risk table.
- Risk Analysis: system overview, features, risk distribution, and benefits.
- Alerts: alert queue with status tabs and investigation actions.
- Settings: data-source connections and placeholder configuration tabs.

## Project Structure

```
src/
	components/
		dashboard/        # KPI cards, charts, and alert widgets
		layout/           # layout, header, sidebar
	pages/              # route screens
	App.jsx             # routing
	main.jsx            # app bootstrap
	index.css           # global styles
```
