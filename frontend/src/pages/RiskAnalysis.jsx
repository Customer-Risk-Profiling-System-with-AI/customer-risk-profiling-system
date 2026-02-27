import React from 'react';
import { ShieldCheck, Brain, Database, Activity, Lock, TrendingUp } from 'lucide-react';

const RiskAnalysis = () => {
    return (
        <div className="space-y-8">
            <div className="text-center space-y-2">
                <h1 className="text-3xl font-bold text-blue-900">Risk Analysis</h1>
                <p className="text-gray-500 max-w-2xl mx-auto">
                    AI-powered customer risk profiling and fraud prevention system
                </p>
            </div>

            {/* System Overview Stats */}
            <section>
                <div className="text-center mb-6">
                    <h2 className="text-xl font-bold text-gray-800">System Overview</h2>
                    <p className="text-sm text-gray-500">
                        Our AI-driven risk profiling system shifts from transaction-level reaction to customer-level prevention
                    </p>
                </div>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                    {[
                        { label: 'Prevention Rate', value: '94.2%', sub: 'vs last month' },
                        { label: 'Model Accuracy', value: '96.8%', sub: 'on validation set' },
                        { label: 'Monitored Customers', value: '9,280', sub: 'active profiles' },
                    ].map((stat, idx) => (
                        <div key={idx} className="bg-white p-6 rounded-xl border border-gray-200 shadow-sm text-center">
                            <h3 className="text-4xl font-bold text-blue-600 mb-2">{stat.value}</h3>
                            <p className="text-sm font-medium text-gray-600">{stat.label}</p>
                            <p className="text-xs text-gray-400 mt-1">{stat.sub}</p>
                        </div>
                    ))}
                </div>
            </section>

            {/* Core Features */}
            <section>
                <h2 className="text-xl font-bold text-gray-800 mb-6 text-center">Core Features</h2>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                    {[
                        {
                            title: 'Machine Learning Models',
                            desc: 'Advanced AI algorithms analyze behavioral patterns and detect anomalies.',
                            points: ['96.8% Accuracy', 'Real-time Processing', 'Continuous Learning'],
                            icon: Brain,
                        },
                        {
                            title: 'Data Aggregation',
                            desc: 'Collect and process customer transaction histories, device fingerprints, and more.',
                            points: ['Multi-source Integration', 'Privacy Compliant', 'Scalable Processing'],
                            icon: Database,
                        },
                        {
                            title: 'Risk Scoring Engine',
                            desc: 'Generate dynamic risk scores (0-100) for every customer in real-time.',
                            points: ['Continuous Updates', 'Reason Codes', 'Configurable Thresholds'],
                            icon: Activity,
                        },
                    ].map((feature, idx) => (
                        <div key={idx} className="bg-white p-6 rounded-xl border border-gray-200 shadow-sm hover:shadow-md transition-shadow">
                            <h3 className="text-lg font-bold text-blue-900 mb-3 flex items-center gap-2">
                                <feature.icon className="w-5 h-5" />
                                {feature.title}
                            </h3>
                            <p className="text-sm text-gray-600 mb-4">{feature.desc}</p>
                            <ul className="space-y-2">
                                {feature.points.map((point, pIdx) => (
                                    <li key={pIdx} className="text-xs font-medium text-green-700 flex items-center gap-2">
                                        <span className="w-1.5 h-1.5 bg-green-500 rounded-full"></span>
                                        {point}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    ))}
                </div>
            </section>

            {/* Risk Distribution */}
            <section>
                <h2 className="text-xl font-bold text-gray-800 mb-6 text-center">Risk Distribution</h2>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                    {[
                        { title: 'Low Risk', score: '0-39', count: '7,300', color: 'green', pct: '20%' }, // pct is simplified
                        { title: 'Medium Risk', score: '40-69', count: '1,500', color: 'yellow', pct: '8.17%' },
                        { title: 'High Risk', score: '70-100', count: '280', color: 'red', pct: '3%' },
                    ].map((risk, idx) => (
                        <div key={idx} className="bg-white p-6 rounded-xl border border-gray-200 shadow-sm">
                            <div className="flex justify-between items-start mb-4">
                                <h3 className="text-lg font-bold text-gray-900">{risk.title}</h3>
                                <span className="text-xs text-gray-400">Score: {risk.score}</span>
                            </div>
                            <h4 className={`text-3xl font-bold text-${risk.color}-600 mb-1`}>{risk.count}</h4>
                            <p className="text-xs text-gray-500">Customers of Total</p>
                            <p className={`text-xs font-bold text-${risk.color}-600 mt-2`}>{risk.pct}</p>
                        </div>
                    ))}
                </div>
            </section>

            {/* Key Benefits */}
            <section>
                <h2 className="text-xl font-bold text-gray-800 mb-6 text-center">Key Benefits</h2>
                <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
                    {[
                        { title: 'Identify High-Risk Customers', desc: 'Identify high-risk customers before fraud occurs.' },
                        { title: 'Data-Driven Decisions', desc: 'Focus investigative efforts on the most promising leads.' },
                        { title: 'Enhanced Customer Experience', desc: 'Reduce friction for low-risk users while applying controls where needed.' },
                        { title: 'Continuous Learning', desc: 'ML models continuously adapt to evolving fraud strategies.' },
                    ].map((benefit, idx) => (
                        <div key={idx} className="bg-white p-6 rounded-xl border border-gray-200 shadow-sm border-t-4 border-t-blue-500">
                            <h3 className="text-md font-bold text-gray-900 mb-2">{benefit.title}</h3>
                            <p className="text-xs text-gray-600 leading-relaxed">{benefit.desc}</p>
                        </div>
                    ))}
                </div>
            </section>
        </div>
    );
};

export default RiskAnalysis;
