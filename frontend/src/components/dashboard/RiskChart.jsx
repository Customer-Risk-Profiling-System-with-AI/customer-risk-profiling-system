import React from 'react';
import { AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

const data = [
    { name: 'Jan', risk: 4000, highRisk: 2400 },
    { name: 'Feb', risk: 3000, highRisk: 1398 },
    { name: 'Mar', risk: 2000, highRisk: 9800 },
    { name: 'Apr', risk: 2780, highRisk: 3908 },
    { name: 'May', risk: 1890, highRisk: 4800 },
    { name: 'Jun', risk: 2390, highRisk: 3800 },
    { name: 'Jul', risk: 3490, highRisk: 4300 },
];

const RiskChart = () => {
    return (
        <div className="bg-white p-6 rounded-xl border border-gray-200 shadow-sm h-[400px]">
            <div className="mb-6">
                <h3 className="text-lg font-bold text-gray-900">Risk Distribution</h3>
                <p className="text-sm text-gray-500">Current customer risk segmentation over time</p>
            </div>
            <div className="h-[300px] w-full">
                <ResponsiveContainer width="100%" height="100%">
                    <AreaChart
                        data={data}
                        margin={{
                            top: 10,
                            right: 30,
                            left: 0,
                            bottom: 0,
                        }}
                    >
                        <CartesianGrid strokeDasharray="3 3" vertical={false} stroke="#E5E7EB" />
                        <XAxis dataKey="name" axisLine={false} tickLine={false} tick={{ fill: '#6B7280', fontSize: 12 }} />
                        <YAxis axisLine={false} tickLine={false} tick={{ fill: '#6B7280', fontSize: 12 }} />
                        <Tooltip
                            contentStyle={{ backgroundColor: '#1F2937', color: '#F9FAFB', border: 'none', borderRadius: '8px' }}
                            itemStyle={{ color: '#F9FAFB' }}
                        />
                        <Area type="monotone" dataKey="risk" stackId="1" stroke="#3B82F6" fill="#3B82F6" fillOpacity={0.2} />
                        <Area type="monotone" dataKey="highRisk" stackId="1" stroke="#EF4444" fill="#EF4444" fillOpacity={0.2} />
                    </AreaChart>
                </ResponsiveContainer>
            </div>
        </div>
    );
};

export default RiskChart;
