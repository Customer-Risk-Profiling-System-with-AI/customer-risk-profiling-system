import React from 'react';
import { NavLink } from 'react-router-dom';
import { LayoutDashboard, Users, ShieldAlert, Bell, Settings } from 'lucide-react';
import clsx from 'clsx';

const Sidebar = () => {
    const navItems = [
        { name: 'Dashboard', path: '/', icon: LayoutDashboard },
        { name: 'Customer', path: '/customers', icon: Users },
        { name: 'Risk Analysis', path: '/risk-analysis', icon: ShieldAlert },
        { name: 'Alerts', path: '/alerts', icon: Bell },
        { name: 'Settings', path: '/settings', icon: Settings },
    ];

    return (
        <aside className="w-64 bg-white border-r border-gray-200 flex flex-col h-full">
            <div className="p-6">
                <h2 className="text-2xl font-bold text-blue-900 flex items-center gap-2">
                    FraudGuard
                </h2>
            </div>

            <nav className="flex-1 px-4 space-y-2">
                {navItems.map((item) => (
                    <NavLink
                        key={item.path}
                        to={item.path}
                        className={({ isActive }) =>
                            clsx(
                                'flex items-center gap-3 px-4 py-3 rounded-lg text-sm font-medium transition-colors',
                                isActive
                                    ? 'bg-blue-600 text-white'
                                    : 'text-gray-600 hover:bg-gray-50 hover:text-gray-900'
                            )
                        }
                    >
                        <item.icon className="w-5 h-5" />
                        {item.name}
                    </NavLink>
                ))}
            </nav>

            <div className="p-4 border-t border-gray-200">
                <div className="flex items-center gap-3">
                    <div className="w-8 h-8 rounded-full bg-blue-100 flex items-center justify-center text-blue-600 font-bold text-xs">
                        FA
                    </div>
                    <div>
                        <p className="text-sm font-medium text-gray-900">Fraud Analyst</p>
                        <p className="text-xs text-gray-500">analyst@bank.com</p>
                    </div>
                </div>
            </div>
        </aside>
    );
};

export default Sidebar;
