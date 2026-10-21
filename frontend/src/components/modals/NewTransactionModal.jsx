import React, { useState } from 'react';
import { X, Send, AlertTriangle, CheckCircle2, ShieldAlert } from 'lucide-react';
import api from '../../services/api';

const NewTransactionModal = ({ isOpen, onClose, onTransactionCreated }) => {
    const [formData, setFormData] = useState({
        customerId: '101',
        amount: '1250.00',
        currency: 'USD',
        channel: 'ECOM',
        merchantCode: 'AMZ-GLOBAL-99',
        deviceCode: 'DEV-IOS-2026',
        location: 'US-NY-NEWYORK',
    });
    const [loading, setLoading] = useState(false);
    const [result, setResult] = useState(null);
    const [error, setError] = useState(null);

    if (!isOpen) return null;

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError(null);
        setResult(null);

        try {
            const payload = {
                customerId: Number(formData.customerId),
                amount: parseFloat(formData.amount),
                currency: formData.currency,
                channel: formData.channel,
                merchantCode: formData.merchantCode,
                deviceCode: formData.deviceCode,
                location: formData.location,
                transactionDatetime: new Date().toISOString(),
            };

            const response = await api.createTransaction(payload);
            setResult(response);
            if (onTransactionCreated) {
                onTransactionCreated(response);
            }
        } catch (err) {
            setError(err.message || 'Failed to submit transaction');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 p-4 backdrop-blur-xs animate-in fade-in duration-200">
            <div className="relative w-full max-w-lg bg-white rounded-2xl shadow-2xl border border-gray-100 overflow-hidden">
                {/* Header */}
                <div className="flex items-center justify-between px-6 py-4 bg-linear-to-r from-blue-900 to-indigo-900 text-white">
                    <div className="flex items-center gap-2">
                        <ShieldAlert className="w-5 h-5 text-blue-300" />
                        <h2 className="font-semibold text-lg">Simulate Transaction Ingestion</h2>
                    </div>
                    <button
                        onClick={onClose}
                        className="text-white/70 hover:text-white p-1 rounded-lg hover:bg-white/10 transition-colors"
                    >
                        <X className="w-5 h-5" />
                    </button>
                </div>

                {/* Form or Result */}
                <div className="p-6">
                    {result ? (
                        <div className="space-y-4">
                            <div className={`p-4 rounded-xl border flex items-start gap-3 ${
                                result.transactionStatus === 'APPROVED'
                                    ? 'bg-emerald-50 border-emerald-200 text-emerald-900'
                                    : 'bg-red-50 border-red-200 text-red-900'
                            }`}>
                                {result.transactionStatus === 'APPROVED' ? (
                                    <CheckCircle2 className="w-6 h-6 text-emerald-600 shrink-0 mt-0.5" />
                                ) : (
                                    <AlertTriangle className="w-6 h-6 text-red-600 shrink-0 mt-0.5" />
                                )}
                                <div>
                                    <h4 className="font-bold text-base">
                                        Transaction {result.transactionStatus}: #{result.transactionId}
                                    </h4>
                                    <p className="text-sm mt-1">
                                        {result.declineReason || 'Transaction approved after behavioral risk evaluation.'}
                                    </p>
                                    <div className="mt-3 grid grid-cols-2 gap-2 text-xs text-gray-600 bg-white/70 p-2.5 rounded-lg border">
                                        <div><strong>Customer ID:</strong> {result.customerId}</div>
                                        <div><strong>Amount:</strong> ${Number(result.amount).toFixed(2)} {result.currency}</div>
                                        <div><strong>Channel:</strong> {result.channel}</div>
                                        <div><strong>Status:</strong> <span className={`font-bold ${result.transactionStatus === 'APPROVED' ? 'text-emerald-600' : 'text-red-600'}`}>{result.transactionStatus}</span></div>
                                    </div>
                                </div>
                            </div>

                            <div className="flex justify-end gap-3 pt-2">
                                <button
                                    type="button"
                                    onClick={() => setResult(null)}
                                    className="px-4 py-2 border border-gray-300 rounded-lg text-sm font-medium hover:bg-gray-50 transition-colors"
                                >
                                    Submit Another
                                </button>
                                <button
                                    type="button"
                                    onClick={onClose}
                                    className="px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded-lg text-sm font-medium transition-colors"
                                >
                                    Done
                                </button>
                            </div>
                        </div>
                    ) : (
                        <form onSubmit={handleSubmit} className="space-y-4">
                            {error && (
                                <div className="p-3 bg-red-50 border border-red-200 text-red-700 text-xs rounded-lg flex items-center gap-2">
                                    <AlertTriangle className="w-4 h-4 shrink-0" />
                                    <span>{error}</span>
                                </div>
                            )}

                            <div className="grid grid-cols-2 gap-3">
                                <div>
                                    <label className="block text-xs font-semibold text-gray-700 mb-1">Customer ID *</label>
                                    <input
                                        type="number"
                                        required
                                        value={formData.customerId}
                                        onChange={(e) => setFormData({ ...formData, customerId: e.target.value })}
                                        className="w-full px-3 py-2 text-sm border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                                    />
                                </div>
                                <div>
                                    <label className="block text-xs font-semibold text-gray-700 mb-1">Amount *</label>
                                    <input
                                        type="number"
                                        step="0.01"
                                        required
                                        value={formData.amount}
                                        onChange={(e) => setFormData({ ...formData, amount: e.target.value })}
                                        className="w-full px-3 py-2 text-sm border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                                    />
                                </div>
                            </div>

                            <div className="grid grid-cols-2 gap-3">
                                <div>
                                    <label className="block text-xs font-semibold text-gray-700 mb-1">Channel *</label>
                                    <select
                                        value={formData.channel}
                                        onChange={(e) => setFormData({ ...formData, channel: e.target.value })}
                                        className="w-full px-3 py-2 text-sm border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                                    >
                                        <option value="ECOM">ECOM (Online)</option>
                                        <option value="POS">POS (In-store)</option>
                                        <option value="ATM">ATM</option>
                                        <option value="MOBILE">MOBILE App</option>
                                        <option value="TRANSFER">WIRE TRANSFER</option>
                                        <option value="QR">QR Payment</option>
                                    </select>
                                </div>
                                <div>
                                    <label className="block text-xs font-semibold text-gray-700 mb-1">Currency *</label>
                                    <input
                                        type="text"
                                        required
                                        value={formData.currency}
                                        onChange={(e) => setFormData({ ...formData, currency: e.target.value })}
                                        className="w-full px-3 py-2 text-sm border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                                    />
                                </div>
                            </div>

                            <div className="grid grid-cols-2 gap-3">
                                <div>
                                    <label className="block text-xs font-semibold text-gray-700 mb-1">Merchant Code *</label>
                                    <input
                                        type="text"
                                        required
                                        value={formData.merchantCode}
                                        onChange={(e) => setFormData({ ...formData, merchantCode: e.target.value })}
                                        className="w-full px-3 py-2 text-sm border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                                    />
                                </div>
                                <div>
                                    <label className="block text-xs font-semibold text-gray-700 mb-1">Device Code *</label>
                                    <input
                                        type="text"
                                        required
                                        value={formData.deviceCode}
                                        onChange={(e) => setFormData({ ...formData, deviceCode: e.target.value })}
                                        className="w-full px-3 py-2 text-sm border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                                    />
                                </div>
                            </div>

                            <div>
                                <label className="block text-xs font-semibold text-gray-700 mb-1">Location / Country *</label>
                                <input
                                    type="text"
                                    required
                                    value={formData.location}
                                    onChange={(e) => setFormData({ ...formData, location: e.target.value })}
                                    className="w-full px-3 py-2 text-sm border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                                />
                            </div>

                            <div className="flex justify-end gap-3 pt-3 border-t">
                                <button
                                    type="button"
                                    onClick={onClose}
                                    className="px-4 py-2 border border-gray-300 rounded-lg text-sm font-medium hover:bg-gray-50 transition-colors"
                                >
                                    Cancel
                                </button>
                                <button
                                    type="submit"
                                    disabled={loading}
                                    className="flex items-center gap-2 px-5 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded-lg text-sm font-medium transition-colors shadow-sm disabled:opacity-50"
                                >
                                    {loading ? (
                                        <div className="w-4 h-4 border-2 border-white border-t-transparent rounded-full animate-spin" />
                                    ) : (
                                        <Send className="w-4 h-4" />
                                    )}
                                    Submit & Profile Risk
                                </button>
                            </div>
                        </form>
                    )}
                </div>
            </div>
        </div>
    );
};

export default NewTransactionModal;
