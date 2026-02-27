import React, { useState } from 'react';
import { Search, Filter, Eye, MoreHorizontal, Download } from 'lucide-react';
import clsx from 'clsx';

const Customers = () => {
    const [searchTerm, setSearchTerm] = useState('');
    const [filter, setFilter] = useState('All');

    const customers = [
        { id: 'CUST001', name: 'John Anderson', score: 85, level: 'HIGH', status: 'Review', email: 'john.a@example.com' },
        { id: 'CUST002', name: 'Sarah Mitchell', score: 42, level: 'MEDIUM', status: 'Active', email: 'sarah.m@example.com' },
        { id: 'CUST003', name: 'Michael Chen', score: 15, level: 'LOW', status: 'Active', email: 'm.chen@example.com' },
        { id: 'CUST004', name: 'Emma Rodriguez', score: 92, level: 'HIGH', status: 'Blocked', email: 'emma.r@example.com' },
        { id: 'CUST005', name: 'David Thompson', score: 28, level: 'LOW', status: 'Active', email: 'd.thompson@example.com' },
        { id: 'CUST006', name: 'Lisa Williams', score: 67, level: 'MEDIUM', status: 'Review', email: 'lisa.w@example.com' },
        { id: 'CUST007', name: 'James Wilson', score: 88, level: 'HIGH', status: 'Review', email: 'j.wilson@example.com' },
    ];

    const getRiskColor = (level) => {
        switch (level) {
            case 'HIGH': return 'bg-red-100 text-red-700 border-red-200';
            case 'MEDIUM': return 'bg-yellow-100 text-yellow-700 border-yellow-200';
            case 'LOW': return 'bg-green-100 text-green-700 border-green-200';
            default: return 'bg-gray-100 text-gray-700';
        }
    };

    const getStatusColor = (status) => {
        switch (status) {
            case 'Active': return 'bg-green-50 text-green-700';
            case 'Review': return 'bg-yellow-50 text-yellow-700';
            case 'Blocked': return 'bg-red-50 text-red-700';
            default: return 'bg-gray-50 text-gray-700';
        }
    };

    return (
        <div className="space-y-6">
            <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
                <div>
                    <h1 className="text-2xl font-bold text-gray-900">Customer Management</h1>
                    <p className="text-gray-500">Monitor and manage customer risk profiles</p>
                </div>
                <div className="flex gap-2">
                    <button className="flex items-center gap-2 px-4 py-2 bg-white border border-gray-300 rounded-lg text-gray-700 hover:bg-gray-50 transition-colors">
                        <Download className="w-4 h-4" />
                        Export
                    </button>
                </div>
            </div>

            <div className="bg-white rounded-xl border border-gray-200 shadow-sm overflow-hidden">
                {/* Toolbar */}
                <div className="p-4 border-b border-gray-200 flex flex-col sm:flex-row sm:items-center justify-between gap-4">
                    <div className="relative flex-1 max-w-md">
                        <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
                        <input
                            type="text"
                            placeholder="Search customers..."
                            className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                        />
                    </div>
                    <div className="flex gap-2">
                        {['All', 'High Risk', 'Medium Risk', 'Low Risk'].map((f) => (
                            <button
                                key={f}
                                onClick={() => setFilter(f)}
                                className={clsx(
                                    'px-3 py-1.5 text-sm font-medium rounded-lg transition-colors',
                                    filter === f
                                        ? 'bg-blue-600 text-white'
                                        : 'bg-gray-100 text-gray-600 hover:bg-gray-200'
                                )}
                            >
                                {f}
                            </button>
                        ))}
                    </div>
                </div>

                {/* Table */}
                <div className="overflow-x-auto">
                    <table className="w-full">
                        <thead className="bg-gray-50">
                            <tr>
                                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-500 uppercase tracking-wider">Customer ID</th>
                                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-500 uppercase tracking-wider">Name</th>
                                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-500 uppercase tracking-wider">Risk Score</th>
                                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-500 uppercase tracking-wider">Risk Level</th>
                                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-500 uppercase tracking-wider">Status</th>
                                <th className="px-6 py-3 text-right text-xs font-semibold text-gray-500 uppercase tracking-wider">Actions</th>
                            </tr>
                        </thead>
                        <tbody className="divide-y divide-gray-200">
                            {customers.map((customer) => (
                                <tr key={customer.id} className="hover:bg-gray-50 transition-colors">
                                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{customer.id}</td>
                                    <td className="px-6 py-4 whitespace-nowrap">
                                        <div className="flex items-center">
                                            <div className="w-8 h-8 rounded-full bg-blue-100 text-blue-600 flex items-center justify-center font-bold text-xs mr-3">
                                                {customer.name.charAt(0)}
                                            </div>
                                            <div>
                                                <div className="text-sm font-medium text-gray-900">{customer.name}</div>
                                                <div className="text-xs text-gray-500">{customer.email}</div>
                                            </div>
                                        </div>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap">
                                        <div className="flex items-center gap-2">
                                            <div className="text-sm font-bold text-gray-900 w-6">{customer.score}</div>
                                            <div className="w-24 h-2 bg-gray-200 rounded-full overflow-hidden">
                                                <div
                                                    className={clsx(
                                                        "h-full rounded-full",
                                                        customer.score >= 70 ? "bg-red-500" : customer.score >= 40 ? "bg-yellow-500" : "bg-green-500"
                                                    )}
                                                    style={{ width: `${customer.score}%` }}
                                                ></div>
                                            </div>
                                        </div>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap">
                                        <span className={clsx("px-2 py-1 text-xs font-bold rounded-md border", getRiskColor(customer.level))}>
                                            {customer.level}
                                        </span>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap">
                                        <span className={clsx("px-2 py-1 text-xs font-medium rounded-full", getStatusColor(customer.status))}>
                                            {customer.status}
                                        </span>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                        <button className="text-blue-600 hover:text-blue-900 bg-blue-50 px-3 py-1.5 rounded-lg text-xs font-semibold transition-colors">
                                            View Details
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>

                {/* Pagination */}
                <div className="px-6 py-4 border-t border-gray-200 flex items-center justify-between">
                    <span className="text-sm text-gray-500">Showing 1 to 7 of 9,280 entries</span>
                    <div className="flex gap-2">
                        <button className="px-3 py-1 text-sm border border-gray-300 rounded-md disabled:opacity-50">Previous</button>
                        <button className="px-3 py-1 text-sm bg-blue-600 text-white rounded-md">1</button>
                        <button className="px-3 py-1 text-sm border border-gray-300 rounded-md">2</button>
                        <button className="px-3 py-1 text-sm border border-gray-300 rounded-md">3</button>
                        <button className="px-3 py-1 text-sm border border-gray-300 rounded-md">Next</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Customers;
