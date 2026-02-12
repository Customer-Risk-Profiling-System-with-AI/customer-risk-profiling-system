import React from 'react';
import { Users, ShieldCheck, Activity, AlertOctagon } from 'lucide-react';
import StatsCard from '../components/dashboard/StatsCard';
import RiskChart from '../components/dashboard/RiskChart';
import RecentAlerts from '../components/dashboard/RecentAlerts';
import QuickActions from '../components/dashboard/QuickActions';

const Dashboard = () => {
    const stats = [
        { title: 'Total Customers', value: '9,280', change: '+12.5%', changeType: 'increase', icon: Users },
        { title: 'Active Alerts', value: '45', change: '+5.2%', changeType: 'increase', icon: AlertOctagon },
        { title: 'High Risk Profiles', value: '128', change: '-2.4%', changeType: 'decrease', icon: ShieldCheck },
        { title: 'Fraud Attempts', value: '12', change: '-15.3%', changeType: 'decrease', icon: Activity },
    ];

    return (
        <div className="space-y-6">
            <div className="mb-6">
                <h1 className="text-2xl font-bold text-gray-900">Risk Monitoring Dashboard</h1>
                <p className="text-gray-500">Real-time customer risk profiling and fraud detection analytics</p>
            </div>

            {/* Stats Grid */}
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                {stats.map((stat, index) => (
                    <StatsCard key={index} {...stat} />
                ))}
            </div>

            <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
                {/* Main Chart Area */}
                <div className="lg:col-span-2 space-y-6">
                    <RiskChart />
                    <RecentAlerts />
                </div>

                {/* Side Panel */}
                <div className="space-y-6">
                    <QuickActions />
                    {/* Placeholder for another side widget if needed */}
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
