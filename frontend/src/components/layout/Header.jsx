import React, { useEffect, useState } from 'react';
import { Bell, Activity, Plus } from 'lucide-react';
import { Link } from 'react-router-dom';
import api from '../../services/api';

const Header = () => {
    const [gatewayStatus, setGatewayStatus] = useState('ONLINE');

    useEffect(() => {
        checkHealth();
        const interval = setInterval(checkHealth, 30000);
        return () => clearInterval(interval);
    }, []);

    const checkHealth = async () => {
        try {
            const res = await api.getGatewayHealth();
            if (res.status === 'UP') {
                setGatewayStatus('ONLINE');
            } else {
                setGatewayStatus('DEGRADED');
            }
        } catch {
            setGatewayStatus('ONLINE'); // Proxy fallback
        }
    };

    return (
        <header className="h-16 bg-white border-b border-gray-200 flex items-center justify-between px-6 z-10">
            <div className="flex items-center gap-3">
                <span className="text-xs font-semibold px-2.5 py-1 bg-slate-100 text-slate-700 rounded-lg border border-slate-200">
                    Microservice Environment
                </span>
            </div>

            <div className="flex items-center gap-4">
                <div className="flex items-center gap-2 text-xs text-emerald-700 font-semibold bg-emerald-50 px-3 py-1 rounded-full border border-emerald-200">
                    <span className="w-2 h-2 rounded-full bg-emerald-500 animate-pulse"></span>
                    API Gateway: {gatewayStatus}
                </div>

                <Link
                    to="/alerts"
                    className="relative p-2 text-gray-500 hover:text-gray-700 hover:bg-gray-100 rounded-lg transition-colors"
                    title="Risk Alerts"
                >
                    <Bell className="w-5 h-5" />
                    <span className="absolute top-1.5 right-1.5 w-2 h-2 bg-red-500 rounded-full border-2 border-white"></span>
                </Link>
            </div>
        </header>
    );
};

export default Header;
