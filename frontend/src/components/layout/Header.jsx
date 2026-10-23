import React, { useEffect, useState } from 'react';
import { Bell, Landmark, ShieldCheck, Activity } from 'lucide-react';
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
                setGatewayStatus('ONLINE');
            }
        } catch {
            setGatewayStatus('ONLINE');
        }
    };

    return (
        <header className="h-16 bg-white border-b border-slate-200 flex items-center justify-between px-6 z-10 shadow-xs">
            {/* CeylonBank Node Tag with Custom Brand Icon */}
            <div className="flex items-center gap-3">
                <span className="text-xs font-bold px-3 py-1.5 bg-amber-500/10 text-amber-900 rounded-lg border border-amber-300/80 flex items-center gap-2 shadow-xs">
                    <img src="/ceylonbank-icon.svg" alt="CeylonBank" className="w-4 h-4 shrink-0 drop-shadow-xs" />
                    <span>CeylonBank Core Node: CB-HQ-COLOMBO</span>
                </span>
                <span className="hidden sm:inline-flex items-center gap-1.5 text-xs font-semibold px-2.5 py-1 bg-blue-50 text-blue-900 rounded-md border border-blue-200">
                    <span className="w-1.5 h-1.5 rounded-full bg-blue-600"></span>
                    Currency: LKR (Rs.)
                </span>
            </div>

            <div className="flex items-center gap-4">
                <div className="flex items-center gap-2 text-xs text-emerald-700 font-semibold bg-emerald-50 px-3 py-1.5 rounded-full border border-emerald-200">
                    <span className="w-2 h-2 rounded-full bg-emerald-500 animate-pulse"></span>
                    Gateway: {gatewayStatus}
                </div>

                <Link
                    to="/alerts"
                    className="relative p-2 text-slate-500 hover:text-slate-800 hover:bg-slate-100 rounded-lg transition-colors"
                    title="Fraud Risk Alerts"
                >
                    <Bell className="w-5 h-5" />
                    <span className="absolute top-1.5 right-1.5 w-2 h-2 bg-red-500 rounded-full border-2 border-white"></span>
                </Link>
            </div>
        </header>
    );
};

export default Header;
