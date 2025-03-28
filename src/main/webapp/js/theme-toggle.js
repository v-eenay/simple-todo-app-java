// Get the saved theme or default to light
let currentTheme = localStorage.getItem('theme') || 'light';

function setTheme(theme) {
    const themeCSS = document.getElementById('theme-css');
    const toggleIcon = document.querySelector('.theme-toggle-icon');
    
    if (theme === 'dark') {
        themeCSS.innerHTML = `<%@ include file="/assets/css/retro-dark.css" %>`;
        if (toggleIcon.textContent === '🌙') {
            toggleIcon.textContent = '☀️';
        } else if (toggleIcon.classList.contains('bi')) {
            toggleIcon.classList.remove('bi-moon-stars');
            toggleIcon.classList.add('bi-sun');
        }
        document.documentElement.setAttribute('data-bs-theme', 'dark');
    } else {
        themeCSS.innerHTML = `<%@ include file="/assets/css/retro-light.css" %>`;
        if (toggleIcon.textContent === '☀️') {
            toggleIcon.textContent = '🌙';
        } else if (toggleIcon.classList.contains('bi')) {
            toggleIcon.classList.remove('bi-sun');
            toggleIcon.classList.add('bi-moon-stars');
        }
        document.documentElement.setAttribute('data-bs-theme', 'light');
    }
    
    localStorage.setItem('theme', theme);
    currentTheme = theme;

    // Show a fun message when theme changes
    const themeMessage = theme === 'dark' ? 
        'DARK MODE ACTIVATED' :
        'LIGHT MODE ACTIVATED';
    
    showThemeMessage(themeMessage);
}

function showThemeMessage(message) {
    const messageDiv = document.createElement('div');
    messageDiv.style.position = 'fixed';
    messageDiv.style.bottom = '20px';
    messageDiv.style.right = '20px';
    messageDiv.style.padding = '10px 20px';
    messageDiv.style.backgroundColor = 'var(--bg-color)';
    messageDiv.style.color = 'var(--text-color)';
    messageDiv.style.border = '1px solid var(--border-color)';
    messageDiv.style.borderRadius = '0';
    messageDiv.style.zIndex = '1000';
    messageDiv.style.transition = 'all 0.3s ease';
    messageDiv.style.fontFamily = 'var(--font-family)';
    messageDiv.style.fontWeight = 'bold';
    messageDiv.textContent = message;
    
    document.body.appendChild(messageDiv);
    
    setTimeout(() => {
        messageDiv.style.opacity = '0';
        setTimeout(() => {
            document.body.removeChild(messageDiv);
        }, 300);
    }, 2000);
}

function toggleTheme() {
    const newTheme = currentTheme === 'light' ? 'dark' : 'light';
    setTheme(newTheme);
}

// Set initial theme
setTheme(currentTheme);