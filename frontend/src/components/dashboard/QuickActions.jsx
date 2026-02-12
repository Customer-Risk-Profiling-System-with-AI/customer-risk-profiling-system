import React from 'react';
import { ArrowRight } from 'lucide-react';

const QuickActions = () => {
    return (
        <div className="bg-white rounded-xl border border-gray-200 shadow-sm p-6">
            <h3 className="text-lg font-bold text-gray-900 mb-6">Quick Actions</h3>

            <div className="space-y-6">
                <div className="flex items-center gap-4">
                    <div className="w-12 h-12 rounded-lg bg-orange-100 flex items-center justify-center">
                        <span className="text-orange-600 font-bold text-xl">23</span>
                    </div>
                    <div>
                        <p className="text-sm font-medium text-gray-900">Pending Reviews</p>
                        <p className="text-xs text-gray-500">Requires attention</p>
                    </div>
                </div>

                <div className="flex items-center gap-4">
                    <div className="w-12 h-12 rounded-lg bg-green-100 flex items-center justify-center">
                        <span className="text-green-600 font-bold text-sm">96.8%</span>
                    </div>
                    <div>
                        <p className="text-sm font-medium text-gray-900">Model Accuracy</p>
                        <p className="text-xs text-gray-500">Last 30 days</p>
                    </div>
                </div>

                <button className="w-full bg-blue-600 hover:bg-blue-700 text-white py-3 rounded-lg font-medium flex items-center justify-center gap-2 transition-colors shadow-lg shadow-blue-200">
                    Run Risk Analysis
                    <ArrowRight className="w-4 h-4" />
                </button>
            </div>
        </div>
    );
};

export default QuickActions;
