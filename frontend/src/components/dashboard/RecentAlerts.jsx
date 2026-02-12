import React from 'react';
import { AlertTriangle, ChevronRight } from 'lucide-react';

const RecentAlerts = () => {
    const alerts = [
        {
            id: 1,
            customer: 'John Smith',
            customerId: 'CUST-10234',
            riskScore: 78,
            message: 'Unusual high-value international transaction',
            time: 'Oct 25, 2025, 04:07 AM',
            status: 'New',
        },
        {
            id: 2,
            customer: 'Sarah Johnson',
            customerId: 'CUST-10567',
            riskScore: 82,
            message: 'New device used with high-velocity spending',
            time: 'Oct 25, 2025, 03:37 AM',
            status: 'Investigating',
        },
        {
            id: 3,
            customer: 'Michael Chen',
            customerId: 'CUST-10892',
            riskScore: 71,
            message: 'Geographic anomaly detected',
            time: 'Oct 25, 2025, 02:22 AM',
            status: 'New',
        },
        {
            id: 4,
            customer: 'Emily Davis',
            customerId: 'CUST-11203',
            riskScore: 85,
            message: 'Surge in eCommerce activity',
            time: 'Oct 25, 2025, 01:22 AM',
            status: 'New',
        },
    ];

    return (
        <div className="bg-white rounded-xl border border-gray-200 shadow-sm">
            <div className="p-6 border-b border-gray-200 flex justify-between items-center">
                <h3 className="text-lg font-bold text-gray-900">Recent High-Risk Alerts</h3>
                <button className="text-sm text-blue-600 hover:text-blue-700 font-medium">View All</button>
            </div>
            <div className="divide-y divide-gray-100">
                {alerts.map((alert) => (
                    <div key={alert.id} className="p-4 hover:bg-gray-50 transition-colors flex items-center justify-between">
                        <div className="flex items-start gap-4">
                            <div className="mt-1">
                                <AlertTriangle className="w-5 h-5 text-red-500" />
                            </div>
                            <div>
                                <div className="flex items-center gap-2">
                                    <span className="font-semibold text-gray-900">{alert.customer}</span>
                                    <span className="text-xs text-gray-500">{alert.customerId}</span>
                                    <span className="px-2 py-0.5 rounded text-xs font-bold bg-orange-100 text-orange-700">
                                        Risk: {alert.riskScore}
                                    </span>
                                </div>
                                <p className="text-sm text-gray-600 mt-1">{alert.message}</p>
                                <p className="text-xs text-gray-400 mt-1">{alert.time}</p>
                            </div>
                        </div>
                        <div className="flex items-center gap-4">
                            <span
                                className={`px-3 py-1 rounded-full text-xs font-medium ${alert.status === 'New'
                                        ? 'bg-blue-100 text-blue-700'
                                        : 'bg-yellow-100 text-yellow-700'
                                    }`}
                            >
                                {alert.status}
                            </span>
                            <button className="text-gray-400 hover:text-gray-600">
                                <ChevronRight className="w-5 h-5" />
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default RecentAlerts;
