import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { 
    ShieldCheck, 
    Lock, 
    Mail, 
    ArrowRight, 
    KeyRound, 
    AlertCircle, 
    CheckCircle2, 
    BadgeCheck, 
    Fingerprint,
    Building2
} from 'lucide-react';

const Login = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('fraud.analyst@ceylonbank.lk');
    const [password, setPassword] = useState('CeylonBank2026!');
    const [rememberMe, setRememberMe] = useState(true);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState('');

    const handleLogin = (e) => {
        e.preventDefault();
        setError('');

        if (!email.trim() || !password.trim()) {
            setError('Please enter both your CeylonBank employee email and security clearance key.');
            return;
        }

        setIsLoading(true);

        setTimeout(() => {
            const userSession = {
                name: 'Sunil Jayawardena',
                role: 'Senior Fraud Risk Analyst',
                email: email.trim(),
                bankCode: 'CEYLONBANK',
                bankName: 'CeylonBank PLC',
                department: 'Fraud Intelligence & Risk Prevention Division',
                terminalId: 'CB-HQ-COLOMBO-T01',
                clearanceLevel: 'Level 3 - Fraud Operations',
                loginTime: new Date().toISOString(),
                token: 'cb-jwt-' + Math.random().toString(36).substring(2),
            };

            localStorage.setItem('lankaguard_user', JSON.stringify(userSession));
            localStorage.setItem('fraudguard_user', JSON.stringify(userSession)); // Backward compatibility

            setIsLoading(false);
            navigate('/');
        }, 500);
    };

    const handleQuickFill = (roleName, roleEmail) => {
        setEmail(roleEmail);
        setPassword('CeylonBank2026!');
        setError('');
    };

    return (
        <div className="min-h-screen bg-slate-950 flex flex-col justify-between relative overflow-hidden font-sans">
            {/* Ambient Background Accents */}
            <div className="absolute top-[-10%] left-[-10%] w-[500px] h-[500px] bg-amber-600/10 rounded-full blur-[140px] pointer-events-none"></div>
            <div className="absolute bottom-[-10%] right-[-10%] w-[550px] h-[550px] bg-blue-600/15 rounded-full blur-[140px] pointer-events-none"></div>
            <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[700px] h-[700px] bg-indigo-900/10 rounded-full blur-[160px] pointer-events-none"></div>

            {/* Top Navigation Bar */}
            <header className="relative z-10 border-b border-slate-800/80 bg-slate-900/60 backdrop-blur-md px-6 py-3.5 flex items-center justify-between">
                <div className="flex items-center gap-3">
                    <div className="w-10 h-10 rounded-xl bg-gradient-to-tr from-amber-500 via-blue-600 to-indigo-700 flex items-center justify-center shadow-lg shadow-amber-500/10 p-1.5 border border-amber-400/30">
                        <img src="/ceylonbank-icon.svg" alt="CeylonBank" className="w-7 h-7" />
                    </div>
                    <div>
                        <div className="flex items-center gap-2">
                            <span className="text-base font-black text-white tracking-tight">
                                Ceylon<span className="text-amber-400">Bank</span>
                            </span>
                            <span className="text-[10px] font-bold px-2 py-0.5 rounded-full bg-amber-500/15 text-amber-300 border border-amber-500/30 uppercase tracking-wider">
                                RiskGuard AI
                            </span>
                        </div>
                        <p className="text-[11px] text-slate-400">
                            Customer Fraud Risk Profiling & Behavioral Intelligence Engine
                        </p>
                    </div>
                </div>

                <div className="hidden md:flex items-center gap-4 text-xs">
                    <span className="flex items-center gap-1.5 text-slate-300 bg-slate-800/60 px-3 py-1.5 rounded-lg border border-slate-700/60">
                        <BadgeCheck className="w-3.5 h-3.5 text-amber-400" />
                        CeylonBank Security Core
                    </span>
                    <span className="flex items-center gap-1.5 text-slate-400">
                        <span className="w-2 h-2 rounded-full bg-emerald-500 animate-pulse"></span>
                        CB-HQ Head Office Terminal
                    </span>
                </div>
            </header>

            {/* Main Login Card Section */}
            <main className="relative z-10 flex-1 flex items-center justify-center p-4 sm:p-6 my-4">
                <div className="w-full max-w-lg bg-slate-900/90 backdrop-blur-xl border border-slate-800 rounded-2xl shadow-2xl shadow-black/70 overflow-hidden">
                    {/* Security Banner Header */}
                    <div className="bg-gradient-to-r from-amber-950/40 via-blue-950/30 to-slate-900 p-6 border-b border-slate-800">
                        <div className="flex items-start justify-between">
                            <div>
                                <span className="inline-flex items-center gap-1.5 text-[11px] font-semibold text-amber-300 uppercase tracking-widest bg-amber-500/10 px-2.5 py-0.5 rounded-full border border-amber-500/20 mb-2">
                                    <Fingerprint className="w-3.5 h-3.5 text-amber-400" />
                                    CeylonBank Internal Clearance
                                </span>
                                <h1 className="text-2xl font-bold text-white tracking-tight">
                                    Bank Officer Sign In
                                </h1>
                                <p className="text-sm text-slate-400 mt-1">
                                    Authenticate into CeylonBank Fraud Operations & Customer Profiling.
                                </p>
                            </div>
                            <div className="hidden sm:flex flex-col items-end">
                                <span className="text-[10px] text-slate-400 uppercase font-mono">Terminal ID</span>
                                <span className="text-xs font-mono font-semibold text-amber-400">CB-HQ-COLOMBO</span>
                            </div>
                        </div>
                    </div>

                    <div className="p-6 sm:p-8 space-y-6">
                        {error && (
                            <div className="flex items-center gap-2 p-3 bg-red-950/50 border border-red-800/80 rounded-xl text-red-200 text-sm">
                                <AlertCircle className="w-4 h-4 text-red-400 shrink-0" />
                                <span>{error}</span>
                            </div>
                        )}

                        <form onSubmit={handleLogin} className="space-y-5">
                            {/* CeylonBank Employee Email */}
                            <div>
                                <label className="block text-xs font-semibold text-slate-300 uppercase tracking-wider mb-2">
                                    CeylonBank Employee Email / Officer ID
                                </label>
                                <div className="relative">
                                    <div className="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none text-slate-400">
                                        <Mail className="w-4 h-4" />
                                    </div>
                                    <input
                                        type="email"
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                        placeholder="officer@ceylonbank.lk"
                                        required
                                        className="w-full bg-slate-950/80 border border-slate-700 rounded-xl pl-10 pr-4 py-2.5 text-sm text-slate-100 placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-amber-500 focus:border-transparent transition"
                                    />
                                </div>
                            </div>

                            {/* Security PIN / Clearance Key */}
                            <div>
                                <div className="flex items-center justify-between mb-2">
                                    <label className="block text-xs font-semibold text-slate-300 uppercase tracking-wider">
                                        Security Clearance Password
                                    </label>
                                    <span className="text-xs text-amber-400 hover:text-amber-300 cursor-pointer">
                                        Reset PIN?
                                    </span>
                                </div>
                                <div className="relative">
                                    <div className="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none text-slate-400">
                                        <KeyRound className="w-4 h-4" />
                                    </div>
                                    <input
                                        type="password"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        placeholder="••••••••••••"
                                        required
                                        className="w-full bg-slate-950/80 border border-slate-700 rounded-xl pl-10 pr-4 py-2.5 text-sm text-slate-100 placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-amber-500 focus:border-transparent transition"
                                    />
                                </div>
                            </div>

                            {/* Remember Checkbox */}
                            <div className="flex items-center justify-between pt-1">
                                <label className="flex items-center gap-2 cursor-pointer text-xs text-slate-400 select-none">
                                    <input
                                        type="checkbox"
                                        checked={rememberMe}
                                        onChange={(e) => setRememberMe(e.target.checked)}
                                        className="w-4 h-4 rounded border-slate-700 bg-slate-950 text-amber-500 focus:ring-amber-500"
                                    />
                                    <span>Keep session active on this bank workstation</span>
                                </label>
                                <span className="text-[11px] text-slate-500 font-mono">AES-256 GCM</span>
                            </div>

                            {/* Submit Button */}
                            <button
                                type="submit"
                                disabled={isLoading}
                                className="w-full mt-2 flex items-center justify-center gap-2 bg-gradient-to-r from-amber-600 via-amber-500 to-yellow-600 hover:from-amber-500 hover:to-yellow-500 text-slate-950 font-bold py-3 px-4 rounded-xl shadow-lg shadow-amber-600/20 transition-all duration-200 transform active:scale-[0.99] disabled:opacity-50"
                            >
                                {isLoading ? (
                                    <>
                                        <div className="w-4 h-4 border-2 border-slate-950/30 border-t-slate-950 rounded-full animate-spin"></div>
                                        <span>Verifying Credentials...</span>
                                    </>
                                ) : (
                                    <>
                                        <span>Sign In to CeylonBank Operations</span>
                                        <ArrowRight className="w-4 h-4" />
                                    </>
                                )}
                            </button>
                        </form>

                        {/* Quick-Fill Demonstration Profiles */}
                        <div className="pt-4 border-t border-slate-800">
                            <p className="text-[11px] font-semibold text-slate-400 uppercase tracking-wider mb-2.5">
                                CeylonBank Demonstration Officers:
                            </p>
                            <div className="grid grid-cols-2 gap-2">
                                <button
                                    type="button"
                                    onClick={() => handleQuickFill('Lead Fraud Investigator', 'fraud.analyst@ceylonbank.lk')}
                                    className="text-left p-2.5 bg-slate-800/60 hover:bg-slate-800 border border-slate-700/60 rounded-xl transition text-xs group"
                                >
                                    <div className="font-semibold text-amber-300 group-hover:text-amber-200">
                                        Fraud Risk Lead
                                    </div>
                                    <div className="text-[10px] text-slate-400 truncate">
                                        fraud.analyst@ceylonbank.lk
                                    </div>
                                </button>
                                <button
                                    type="button"
                                    onClick={() => handleQuickFill('Chief Risk Officer', 'risk.manager@ceylonbank.lk')}
                                    className="text-left p-2.5 bg-slate-800/60 hover:bg-slate-800 border border-slate-700/60 rounded-xl transition text-xs group"
                                >
                                    <div className="font-semibold text-blue-300 group-hover:text-blue-200">
                                        Chief Risk Officer
                                    </div>
                                    <div className="text-[10px] text-slate-400 truncate">
                                        risk.manager@ceylonbank.lk
                                    </div>
                                </button>
                            </div>
                        </div>
                    </div>

                    {/* Security Footer Notice */}
                    <div className="bg-slate-950/70 px-6 py-3 border-t border-slate-800 flex items-center justify-between text-[11px] text-slate-400">
                        <span className="flex items-center gap-1.5">
                            <Lock className="w-3.5 h-3.5 text-amber-500" />
                            Internal Proprietary System — CeylonBank PLC
                        </span>
                        <span>v1.0 (Live AI)</span>
                    </div>
                </div>
            </main>

            {/* Bottom Footer */}
            <footer className="relative z-10 text-center py-4 text-xs text-slate-400 border-t border-slate-900 bg-slate-950">
                <p>
                    © 2026 CeylonBank PLC — Customer Risk Profiling & Behavioral Fraud Prediction Platform. Confidential & Proprietary.
                </p>
            </footer>
        </div>
    );
};

export default Login;
