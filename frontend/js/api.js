import { CONFIG } from './config.js';

export async function fetchApi(endpoint, options = {}) {
    const url = `${CONFIG.API_BASE_URL}${endpoint}`;
    
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers
    };

    // We don't have JWT, but if we did, we'd add it here.
    const user = JSON.parse(localStorage.getItem('bms_user'));
    if (user && user.id) {
        headers['X-User-Id'] = user.id; // Optional side-channel
    }

    try {
        const response = await fetch(url, { ...options, headers });
        if (!response.ok) {
            const err = await response.json().catch(() => ({}));
            throw new Error(err.message || `HTTP ${response.status}`);
        }
        
        // Some endpoints return 204 No Content
        const text = await response.text();
        return text ? JSON.parse(text) : null;
    } catch (error) {
        console.error(`API Error on ${endpoint}:`, error);
        throw error;
    }
}
