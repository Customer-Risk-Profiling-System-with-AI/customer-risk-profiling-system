import React from 'react';
import { AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, BarChart, Bar, Legend } from 'recharts';

const RiskChart = ({ riskDistribution = [], timelineData = [] }) => {
    // Default fallback timeline data if fewer than 2 days in database
    const displayData = timelineData.length > 0 ? timelineData : [
        { name: 'Mon', low: 12, medium: 5, high: 2 },
        { name: 'Tue', low: 18, medium: 8, high: 3 },
        { name: 'Wed', low: 15, medium: 6, high: 1 },
        { name: 'Thu', low: 22, medium: 9, high: 4 },
        { name: 'Fri', low: 25, medium: 12, high: 5 },
        { name: 'Sat', low: 30, medium: 15, high: 6 },
        { name: 'Sun', low: 28, medium: 11, high: 4 },
    ];

    return (
        <div className="bg-white p-6 rounded-xl border border-gray-200 shadow-sm flex flex-col justify-between">
            <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-2 mb-6">
                <div>
                    <h3 className="text-base font-bold text-gray-900">Dynamic Risk Segmentation & Activity</h3>
                    <p className="text-xs text-gray-500">Real-time risk scoring across customer portfolio</p>
                </div>
                {/* Distribution Badges */}
                <div className="flex items-center gap-3 text-xs">
                    <span className="flex items-center gap-1.5 font-medium text-emerald-700 bg-emerald-50 px-2.5 py-1 rounded-lg border border-emerald-200">
                        <span className="w-2 h-2 rounded-full bg-emerald-500" />
                        Low: {riskDistribution.find(d => d.name === 'Low')?.count || 0}
                    </span>
                    <span className="flex items-center gap-1.5 font-medium text-amber-700 bg-amber-50 px-2.5 py-1 rounded-lg border border-amber-200">
                        <span className="w-2 h-2 rounded-full bg-amber-500" />
                        Medium: {riskDistribution.find(d => d.name === 'Medium')?.count || 0}
                    </span>
                    <span className="flex items-center gap-1.5 font-medium text-red-700 bg-red-50 px-2.5 py-1 rounded-lg border border-red-200">
                        <span className="w-2 h-2 rounded-full bg-red-500" />
                        High: {riskDistribution.find(d => d.name === 'High')?.count || 0}
                    </span>
                </div>
            </div>

            <div className="h-[280px] w-full">
                <ResponsiveContainer width="100%" height="100%">
                    <AreaChart
                        data={displayData}
                        margin={{
                            top: 10,
                            right: 20,
                            left: -10,
                            bottom: 0,
                        }}
                    >
                        <defs>
                            <linearGradient id="colorLow" x1="0" y1="0" x2="0" y2="1">
                                <stop offset="5%" stopColor="#10B981" stopOpacity={0.4}/>
                                <stop offset="95%" stopColor="#10B981" stopOpacity={0.0}/>
                            </linearGradient>
                            <linearGradient id="colorMedium" x1="0" y1="0" x2="0" y2="1">
                                <stop offset="5%" stopColor="#F59E0B" stopOpacity={0.4}/>
                                <stop offset="95%" stopColor="#F59E0B" stopOpacity={0.0}/>
                            </linearGradient>
                            <linearGradient id="colorHigh" x1="0" y1="0" x2="0" y2="1">
                                <stop offset="5%" stopColor="#EF4444" stopOpacity={0.5}/>
                                <stop offset="95%" stopColor="#EF4444" stopOpacity={0.0}/>
                            </linearGradient>
                        </defs>
                        <CartesianGrid strokeDasharray="3 3" vertical={false} stroke="#F3F4F6" />
                        <XAxis dataKey="name" axisLine={false} tickLine={false} tick={{ fill: '#6B7280', fontSize: 11 }} />
                        <YAxis axisLine={false} tickLine={false} tick={{ fill: '#6B7280', fontSize: 11 }} />
                        <Tooltip
                            contentStyle={{ backgroundColor: '#0F172A', color: '#F8FAFC', border: 'none', borderRadius: '10px', fontSize: '12px' }}
                            itemStyle={{ color: '#F8FAFC' }}
                        />
                        <Area type="monotone" dataKey="low" name="Low Risk" stroke="#10B981" strokeWidth={2} fillOpacity={1} fill="url(#colorLow)" />
                        <Area type="monotone" dataKey="medium" name="Medium Risk" stroke="#F59E0B" strokeWidth={2} fillOpacity={1} fill="url(#colorMedium)" />
                        <Area type="monotone" dataKey="high" name="High Risk" stroke="#EF4444" strokeWidth={2} fillOpacity={1} fill="url(#colorHigh)" />
                    </AreaChart>
                </ResponsiveContainer>
            </div>
        </div>
    );
};

export default RiskChart;
