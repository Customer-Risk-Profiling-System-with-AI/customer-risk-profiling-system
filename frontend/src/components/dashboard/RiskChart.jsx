import React from 'react';
import { AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';
import { Activity } from 'lucide-react';

const RiskChart = ({ riskDistribution = [], timelineData = [] }) => {
    const hasData = timelineData && timelineData.length > 0;

    return (
        <div className="bg-white p-6 rounded-xl border border-gray-200 shadow-xs flex flex-col justify-between">
            <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-2 mb-6">
                <div>
                    <h3 className="text-base font-bold text-gray-900">Dynamic Risk Segmentation & Activity</h3>
                    <p className="text-xs text-gray-500">Live risk trajectory and transactions processed by AI Engine</p>
                </div>
                {/* Real Distribution Badges */}
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
                {!hasData ? (
                    <div className="h-full flex flex-col items-center justify-center text-gray-400 text-sm">
                        <Activity className="w-8 h-8 mb-2 text-gray-300 animate-pulse" />
                        <p>No timeline transactions recorded yet.</p>
                        <p className="text-xs text-gray-400 mt-1">Submit new transactions to plot real-time curves.</p>
                    </div>
                ) : (
                    <ResponsiveContainer width="100%" height="100%">
                        <AreaChart
                            data={timelineData}
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
                )}
            </div>
        </div>
    );
};

export default RiskChart;
