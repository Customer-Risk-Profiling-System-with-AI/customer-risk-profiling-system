import React from 'react';
import { Bell } from 'lucide-react';

const Header = () => {
    return (
        <header className="h-16 bg-white border-b border-gray-200 flex items-center justify-between px-6 z-10">
            <div className="flex items-center gap-2">
                {/* Breadcrumbs or Page Title could go here */}
            </div>

            <div className="flex items-center gap-4">
                <div className="flex items-center gap-2 text-sm text-green-600 font-medium bg-green-50 px-3 py-1 rounded-full">
                    <span className="w-2 h-2 rounded-full bg-green-600 animate-pulse"></span>
                    System Online
                </div>

                <button className="relative p-2 text-gray-400 hover:text-gray-600 transition-colors">
                    <Bell className="w-5 h-5" />
                    <span className="absolute top-1.5 right-1.5 w-2 h-2 bg-red-500 rounded-full border border-white"></span>
                </button>
            </div>
        </header>
    );
};

export default Header;
