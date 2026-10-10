// Scroll effects for Navbar
window.addEventListener('scroll', () => {
    const nav = document.querySelector('nav');
    if(nav) {
        if(window.scrollY > 20) nav.classList.add('scrolled');
        else nav.classList.remove('scrolled');
    }
});
