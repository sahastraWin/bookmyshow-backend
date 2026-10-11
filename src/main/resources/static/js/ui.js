import { AuthService } from './auth.js';

// Theme management (black/dark by default, persisted across sessions)
export function getSavedTheme() {
    return localStorage.getItem('bms_theme') || 'dark';
}

export function applyTheme(theme) {
    document.documentElement.setAttribute('data-theme', theme);
    localStorage.setItem('bms_theme', theme);
    const themeBtn = document.getElementById('theme-toggle');
    if (themeBtn) {
        themeBtn.innerHTML = theme === 'light' ? '🌙 Dark Mode' : '☀️ Light Mode';
        themeBtn.setAttribute('title', `Switch to ${theme === 'light' ? 'Dark' : 'Light'} Mode`);
    }
}

// Initial theme application
applyTheme(getSavedTheme());

// Scroll effects for Navbar
window.addEventListener('scroll', () => {
    const nav = document.querySelector('nav');
    if (nav) {
        if (window.scrollY > 20) nav.classList.add('scrolled');
        else nav.classList.remove('scrolled');
    }
});

export function initNav() {
    const nav = document.querySelector('nav');
    if (!nav) return;

    const user = AuthService.getCurrentUser();
    const currentTheme = getSavedTheme();
    const toggleLabel = currentTheme === 'light' ? '🌙 Dark Mode' : '☀️ Light Mode';
    
    nav.innerHTML = `
        <a href="index.html" class="nav-brand">🎬 Midnight Cinema</a>
        <div class="nav-links">
            <a href="index.html">Home</a>
            <a href="shows.html">Browse Shows</a>
            ${user ? `<a href="bookings.html">My Bookings</a>` : ''}
            <button id="theme-toggle" class="btn-theme-toggle" title="Switch Theme">${toggleLabel}</button>
            ${user ? `
                <div class="user-tag">👤 <strong>${escapeHtml(user.name || user.email.split('@')[0])}</strong></div>
                <button id="nav-logout-btn" class="btn-secondary btn-sm">Sign Out</button>
            ` : `
                <a href="auth.html" class="btn-primary btn-sm">Sign In</a>
            `}
        </div>
    `;

    const themeBtn = document.getElementById('theme-toggle');
    if (themeBtn) {
        themeBtn.addEventListener('click', () => {
            const active = document.documentElement.getAttribute('data-theme') || 'dark';
            const next = active === 'light' ? 'dark' : 'light';
            applyTheme(next);
            showToast(`Switched to ${next === 'light' ? 'Light' : 'Black (Dark)'} Mode`, 'info');
        });
    }

    const logoutBtn = document.getElementById('nav-logout-btn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', () => {
            AuthService.logout();
        });
    }
}

export function showToast(message, type = 'info') {
    let container = document.getElementById('toast-container');
    if (!container) {
        container = document.createElement('div');
        container.id = 'toast-container';
        document.body.appendChild(container);
    }
    const toast = document.createElement('div');
    toast.className = `toast ${type}`;
    const icon = type === 'error' ? '⚠️' : type === 'success' ? '✅' : 'ℹ️';
    toast.innerHTML = `<span>${icon}</span> <span>${escapeHtml(message)}</span>`;
    container.appendChild(toast);
    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transform = 'translateY(10px)';
        toast.style.transition = 'all 0.3s ease';
        setTimeout(() => toast.remove(), 300);
    }, 3500);
}

export function showSuccessPopup(title, message, onClose) {
    const overlay = document.createElement('div');
    overlay.className = 'popup-overlay';
    overlay.innerHTML = `
        <div class="popup-card success">
            <div class="popup-icon-wrap">
                <div class="popup-icon-pulse"></div>
                <div class="popup-icon">✓</div>
            </div>
            <div class="green-badge">★ Reservation Confirmed</div>
            <h3>${escapeHtml(title)}</h3>
            <p>${escapeHtml(message)}</p>
            <button class="btn-success" id="popup-ok" style="width: 100%;">View Booking Pass 🎟️</button>
        </div>`;
    document.body.appendChild(overlay);

    let executed = false;
    const triggerClose = () => {
        if (executed) return;
        executed = true;
        overlay.style.opacity = '0';
        overlay.style.transition = 'opacity 0.25s ease';
        setTimeout(() => {
            overlay.remove();
            if (onClose) onClose();
        }, 250);
    };

    overlay.querySelector('#popup-ok').addEventListener('click', triggerClose);
    
    // Auto-advance after 3.5 seconds if user doesn't click
    const timer = setTimeout(triggerClose, 3500);

    overlay.addEventListener('click', (e) => {
        if (e.target === overlay) {
            clearTimeout(timer);
            triggerClose();
        }
    });
}

function escapeHtml(str) {
    if (!str) return '';
    return String(str)
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;');
}

document.addEventListener('DOMContentLoaded', () => {
    initNav();
});
