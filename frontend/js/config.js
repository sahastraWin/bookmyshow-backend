// Dynamic API configuration
const isLocalhost = window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1';

export const CONFIG = {
    API_BASE_URL: (() => {
        // Manual override if specified
        const customUrl = localStorage.getItem('bms_api_url');
        if (customUrl) return customUrl;

        // If local dev server (e.g., port 5500)
        if (isLocalhost && window.location.port && window.location.port !== '8080') {
            return 'http://localhost:8080/api';
        }

        // If served by the backend directly (same origin, e.g. on Render or local 8080)
        if (window.location.port === '8080' || window.location.origin.includes('onrender.com')) {
            return `${window.location.origin}/api`;
        }

        // Default fallback when deployed on separate static host
        return 'https://bookmyshow-backend.onrender.com/api';
    })()
};
