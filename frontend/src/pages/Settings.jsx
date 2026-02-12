import React, { useState } from 'react';
import { Database, Smartphone, Shield, Repeat, Plus, Settings as SettingsIcon, Trash2 } from 'lucide-react';
import clsx from 'clsx';

const Settings = () => {
    const [activeTab, setActiveTab] = useState('Data Sources');

    const dataSources = [
        {
            id: 1,
            name: 'Transaction Database',
            records: '2.3M records',
            status: 'Connected',
            icon: Database,
        },
        {
            id: 2,
            name: 'Device Fingerprinting',
            records: '450K records',
            status: 'Connected',
            icon: Smartphone,
        },
        {
            id: 3,
            name: 'Authentication Logs',
            records: '3.1M records',
            status: 'Connected',
            icon: Shield,
        },
        {
            id: 4,
            name: 'Chargeback Records',
            records: '12K records',
            status: 'Connection Issue',
            icon: Repeat,
        },
    ];

    const getStatusColor = (status) => {
        return status === 'Connected' ? 'bg-green-100 text-green-700' : 'bg-yellow-100 text-yellow-700';
    };

    return (
        <div className="space-y-6">
            <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
                <div>
                    <h1 className="text-2xl font-bold text-gray-900">Settings</h1>
                    <p className="text-gray-500">Configure system parameters and preferences</p>
                </div>
            </div>

            <div className="bg-white rounded-xl border border-gray-200 shadow-sm overflow-hidden">
                {/* Tabs */}
                <div className="border-b border-gray-200 bg-gray-50 px-6 pt-4">
                    <div className="flex gap-6 overflow-x-auto">
                        {['Model Configuration', 'Alert Settings', 'Data Sources', 'Security'].map((tab) => (
                            <button
                                key={tab}
                                onClick={() => setActiveTab(tab)}
                                className={clsx(
                                    'pb-4 text-sm font-medium border-b-2 transition-colors whitespace-nowrap',
                                    activeTab === tab
                                        ? 'border-blue-600 text-blue-600'
                                        : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                                )}
                            >
                                {tab}
                            </button>
                        ))}
                    </div>
                </div>

                {/* Content */}
                <div className="p-6">
                    {activeTab === 'Data Sources' && (
                        <div className="space-y-6">
                            <div className="flex justify-between items-center">
                                <div>
                                    <h3 className="text-lg font-bold text-gray-900">Connected Data Sources</h3>
                                    <p className="text-sm text-gray-500">Manage integrations and data pipelines</p>
                                </div>
                                <button className="flex items-center gap-2 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors shadow-sm">
                                    <Plus className="w-4 h-4" />
                                    Add Source
                                </button>
                            </div>

                            <div className="space-y-4">
                                {dataSources.map((source) => (
                                    <div key={source.id} className="flex flex-col sm:flex-row sm:items-center justify-between p-4 border border-gray-200 rounded-xl hover:border-blue-300 transition-colors bg-gray-50/50">
                                        <div className="flex items-center gap-4 mb-4 sm:mb-0">
                                            <div className="w-12 h-12 bg-white rounded-lg border border-gray-200 flex items-center justify-center shadow-sm">
                                                <source.icon className="w-6 h-6 text-blue-600" />
                                            </div>
                                            <div>
                                                <h4 className="text-base font-semibold text-gray-900">{source.name}</h4>
                                                <p className="text-xs text-gray-500">{source.records}</p>
                                            </div>
                                        </div>

                                        <div className="flex items-center gap-4">
                                            <span className={clsx('px-3 py-1 rounded-full text-xs font-medium', getStatusColor(source.status))}>
                                                {source.status}
                                            </span>
                                            <button className="p-2 text-gray-400 hover:text-blue-600 transition-colors">
                                                <SettingsIcon className="w-5 h-5" />
                                            </button>
                                            <button className="p-2 text-gray-400 hover:text-red-600 transition-colors">
                                                <Trash2 className="w-5 h-5" />
                                            </button>
                                        </div>
                                    </div>
                                ))}
                            </div>

                            {/* Visualization of Data Flow */}
                            <div className="border-t border-gray-100 pt-8 mt-8">
                                <h4 className="text-sm font-bold text-gray-900 mb-6 uppercase tracking-wider">Connection Status</h4>
                                <div className="flex flex-wrap items-center justify-around gap-4 relative">
                                    {/* Visual Connector Line (Fake) */}
                                    <div className="absolute top-1/2 left-10 right-10 h-0.5 bg-gray-200 -z-10 hidden md:block"></div>

                                    {[
                                        { label: 'Transaction DB', icon: Database },
                                        { label: 'AI Engine', icon: Smartphone }, // Using generic icon
                                        { label: 'Dashboard', icon: SettingsIcon },
                                    ].map((step, idx) => (
                                        <div key={idx} className="bg-white px-6 py-4 rounded-xl border border-blue-100 shadow-sm flex flex-col items-center gap-2 z-10 w-40 text-center">
                                            <step.icon className="w-6 h-6 text-green-500" />
                                            <span className="text-xs font-medium text-gray-600">{step.label}</span>
                                        </div>
                                    ))}
                                </div>
                            </div>
                        </div>
                    )}

                    {activeTab !== 'Data Sources' && (
                        <div className="text-center py-12">
                            <div className="w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-4">
                                <SettingsIcon className="w-8 h-8 text-gray-400" />
                            </div>
                            <h3 className="text-lg font-medium text-gray-900">Configuration</h3>
                            <p className="text-gray-500 mt-2">Settings for {activeTab} are currently being developed.</p>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default Settings;
