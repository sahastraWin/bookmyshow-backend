import { fetchApi } from './api.js';

export const AuthService = {
    async login(email, password) {
        const user = await fetchApi('/users/login', {
            method: 'POST',
            body: JSON.stringify({ email, password })
        });
        localStorage.setItem('bms_user', JSON.stringify(user));
        return user;
    },
    async register(name, email, password, phone) {
        const user = await fetchApi('/users/register', {
            method: 'POST',
            body: JSON.stringify({ name, email, password, phoneNumber: phone })
        });
        localStorage.setItem('bms_user', JSON.stringify(user));
        return user;
    },
    logout() {
        localStorage.removeItem('bms_user');
        window.location.reload();
    },
    getCurrentUser() {
        return JSON.parse(localStorage.getItem('bms_user'));
    }
};

window.AuthService = AuthService;
