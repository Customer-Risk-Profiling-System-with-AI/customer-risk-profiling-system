import React, { useState } from 'react';
import { AlertTriangle, ChevronRight, Filter, Bell, CheckCircle } from 'lucide-react';
import clsx from 'clsx';

const Alerts = () => {
    const [activeTab, setActiveTab] = useState('New');

    const alerts = [
        {
            id: 1,
            customer: 'Emily Davis',
            customerId: 'CUST-11203',
            riskScore: 85,
            title: 'Surge in eCommerce activity',
            desc: 'Customer showed 350% increase in online transactions within 24 hours.',
            tags: ['High Velocity', 'New Merchant', 'Large Amount'],
            priority: 'Critical',
            time: 'Oct 25, 2025, 11:13 AM',
            status: 'New',
        },
        {
            id: 2,
            customer: 'Sarah Johnson',
            customerId: 'CUST-10567',
            riskScore: 82,
            title: 'New device with high-velocity spending',
            desc: 'Multiple transactions from a new device in a short period.',
            tags: ['New Device', 'High Velocity', 'Unusual Location'],
            priority: 'Critical',
            time: 'Oct 25, 2025, 10:45 AM',
            status: 'New',
        },
        {
            id: 3,
            customer: 'Michael Chen',
            customerId: 'CUST-10892',
            riskScore: 71,
            title: 'Geographic anomaly detected',
            desc: 'Transaction location inconsistent with previous history.',
            tags: ['Geo Mismatch'],
            priority: 'High',
            time: 'Oct 25, 2025, 09:12 AM',
            status: 'Investigating',
        },
    ];

    const getPriorityColor = (priority) => {
        switch (priority) {
            case 'Critical': return 'bg-red-600 text-white';
            case 'High': return 'bg-orange-500 text-white';
            default: return 'bg-blue-500 text-white';
        }
    };

    return (
        <div className="space-y-6">
            {/* Header */}
            <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
                <div>
                    <h1 className="text-2xl font-bold text-gray-900">Risk Alerts</h1>
                    <p className="text-gray-500">Real-time alerts for high-risk customer activities</p>
                </div>
                <div className="flex items-center gap-2">
                    <span className="flex items-center gap-2 text-sm text-green-600 font-medium bg-green-50 px-3 py-1.5 rounded-full">
                        <span className="w-2 h-2 rounded-full bg-green-500"></span>
                        System Online
                    </span>
                </div>
            </div>

            {/* Stats Cards */}
            <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
                {[
                    { label: 'Critical Alerts', value: '2', color: 'red' },
                    { label: 'High Priority', value: '2', color: 'orange' },
                    { label: 'Under Review', value: '2', color: 'blue' },
                    { label: 'Resolved Today', value: '1', color: 'green' },
                ].map((stat, idx) => (
                    <div key={idx} className="bg-white p-6 rounded-xl border border-gray-200 shadow-sm">
                        <p className="text-sm font-medium text-gray-500">{stat.label}</p>
                        <h3 className={`text-3xl font-bold text-${stat.color}-600 mt-2`}>{stat.value}</h3>
                    </div>
                ))}
            </div>

            {/* Alert Management Section */}
            <div className="bg-white rounded-xl border border-gray-200 shadow-sm">
                <div className="p-6 border-b border-gray-200 flex flex-col sm:flex-row sm:items-center justify-between gap-4">
                    <h3 className="text-lg font-bold text-gray-900">Alert Management</h3>
                    <div className="flex bg-gray-100 p-1 rounded-lg">
                        {['All Alerts', 'New', 'Investigating', 'Resolved'].map((tab) => (
                            <button
                                key={tab}
                                onClick={() => setActiveTab(tab)}
                                className={clsx(
                                    'px-4 py-2 text-sm font-medium rounded-md transition-all',
                                    activeTab === tab
                                        ? 'bg-white text-gray-900 shadow-sm'
                                        : 'text-gray-500 hover:text-gray-900'
                                )}
                            >
                                {tab}
                            </button>
                        ))}
                    </div>
                </div>

                <div className="divide-y divide-gray-100">
                    {alerts.map((alert) => (
                        <div key={alert.id} className="p-6 hover:bg-gray-50 transition-colors">
                            <div className="flex flex-col md:flex-row md:items-start justify-between gap-6">
                                <div className="flex items-start gap-4">
                                    <div className="mt-1">
                                        <AlertTriangle className="w-6 h-6 text-red-500" />
                                    </div>
                                    <div>
                                        <h4 className="text-lg font-bold text-gray-900 flex items-center gap-2">
                                            {alert.title}
                                            <span className="bg-red-600 text-white text-xs font-bold px-2 py-0.5 rounded-full uppercase">
                                                {alert.priority}
                                            </span>
                                        </h4>

                                        <div className="mt-2 flex flex-wrap items-center gap-x-4 gap-y-2 text-sm">
                                            <span className="font-semibold text-gray-900">{alert.customer}</span>
                                            <span className="text-gray-500">{alert.customerId}</span>
                                            <span className="px-2 py-0.5 rounded text-xs font-bold bg-white border border-red-200 text-red-700">
                                                Risk: {alert.riskScore}
                                            </span>
                                        </div>

                                        <p className="text-gray-600 mt-2 text-sm">{alert.desc}</p>

                                        <div className="flex flex-wrap gap-2 mt-3">
                                            {alert.tags.map((tag, idx) => (
                                                <span key={idx} className="px-2 py-1 bg-gray-100 text-gray-600 text-xs font-medium rounded">
                                                    {tag}
                                                </span>
                                            ))}
                                        </div>

                                        <div className="mt-4 flex items-center gap-6">
                                            <button className="text-sm font-medium text-gray-500 hover:text-gray-900">Dismiss</button>
                                        </div>
                                    </div>
                                </div>

                                <div className="flex flex-col items-end gap-4">
                                    <div className="flex items-center gap-2">
                                        <span className="bg-blue-100 text-blue-700 px-3 py-1 rounded-full text-xs font-bold uppercase transition-colors hover:bg-blue-200 cursor-pointer">
                                            {alert.status}
                                        </span>
                                    </div>
                                    <button className="px-4 py-2 bg-blue-900 text-white text-sm font-medium rounded-lg hover:bg-blue-800 transition-colors shadow-sm">
                                        Investigate
                                    </button>
                                    <span className="text-xs text-gray-400 flex items-center gap-1">
                                        <Bell className="w-3 h-3" />
                                        {alert.time}
                                    </span>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Alerts;
