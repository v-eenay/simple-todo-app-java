<%@ page contentType="text/css" %>
<style>
    :root {
        /* Light Theme Colors - Old Money Style */
        --light-bg: #f8f6f1;
        --light-card: #ffffff;
        --light-text: #2c3e50;
        --light-accent: #8b4513;
        --light-secondary: #a0522d;
        --light-success: #2e8b57;
        --light-danger: #8b0000;
        --light-border: #d4af37;
        --light-shadow: rgba(0, 0, 0, 0.1);
        --light-gold: #d4af37;
        --light-gold-light: #ffd700;
        
        /* Dark Theme Colors - Vintage Luxury */
        --dark-bg: #1a1a1a;
        --dark-card: #2d2d2d;
        --dark-text: #e0e0e0;
        --dark-accent: #d4af37;
        --dark-secondary: #b8860b;
        --dark-success: #3cb371;
        --dark-danger: #cd5c5c;
        --dark-border: #8b4513;
        --dark-shadow: rgba(0, 0, 0, 0.4);
        --dark-gold: #ffd700;
        --dark-gold-light: #fff8dc;
    }

    /* Base Styles */
    body {
        font-family: 'Playfair Display', serif;
        font-size: 1.1rem;
        line-height: 1.6;
        transition: all 0.5s ease;
        background-color: var(--light-bg);
        color: var(--light-text);
    }

    /* Light Theme */
    body.light-theme {
        background-color: var(--light-bg);
        color: var(--light-text);
    }

    /* Dark Theme */
    body.dark-theme {
        background-color: var(--dark-bg);
        color: var(--dark-text);
    }

    /* Typography */
    h1, h2, h3, h4, h5, h6 {
        font-family: 'Playfair Display', serif;
        font-weight: 700;
        letter-spacing: -0.02em;
        line-height: 1.2;
        color: var(--light-accent);
    }

    .dark-theme h1, .dark-theme h2, .dark-theme h3, 
    .dark-theme h4, .dark-theme h5, .dark-theme h6 {
        color: var(--dark-accent);
    }

    .display-4 {
        font-size: 3.5rem;
        font-weight: 800;
    }

    .lead {
        font-size: 1.25rem;
        font-weight: 400;
        font-style: italic;
        color: var(--light-text);
    }

    .dark-theme .lead {
        color: var(--dark-text);
    }

    /* Cards */
    .card {
        border: 2px solid var(--light-border);
        border-radius: 0;
        transition: all 0.5s ease;
        background: var(--light-card);
        box-shadow: 0 4px 6px var(--light-shadow);
        position: relative;
        overflow: hidden;
        margin-bottom: 2rem;
    }

    .card::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 4px;
        background: linear-gradient(90deg, var(--light-gold), var(--light-gold-light));
        opacity: 0;
        transition: opacity 0.5s ease;
    }

    .card:hover::before {
        opacity: 1;
    }

    .dark-theme .card {
        border-color: var(--dark-border);
        background: var(--dark-card);
        box-shadow: 0 4px 6px var(--dark-shadow);
    }

    .dark-theme .card::before {
        background: linear-gradient(90deg, var(--dark-gold), var(--dark-gold-light));
    }

    /* Buttons */
    .btn {
        font-family: 'Playfair Display', serif;
        font-weight: 600;
        padding: 0.75rem 1.5rem;
        border-radius: 0;
        transition: all 0.3s ease;
        text-transform: uppercase;
        letter-spacing: 1px;
        font-size: 0.9rem;
        position: relative;
        overflow: hidden;
    }

    .btn::after {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: linear-gradient(45deg, transparent, rgba(255,255,255,0.2), transparent);
        transform: translateX(-100%);
        transition: transform 0.6s ease;
    }

    .btn:hover::after {
        transform: translateX(100%);
    }

    .btn-primary {
        background: var(--light-accent);
        border: 2px solid var(--light-border);
        color: #ffffff;
    }

    .btn-primary:hover {
        background: var(--light-secondary);
        transform: translateY(-2px);
        color: #ffffff;
    }

    .dark-theme .btn-primary {
        background: var(--dark-accent);
        border-color: var(--dark-border);
        color: #ffffff;
    }

    .dark-theme .btn-primary:hover {
        background: var(--dark-secondary);
        color: #ffffff;
    }

    .btn-outline-dark {
        border: 2px solid var(--light-text);
        color: var(--light-text);
    }

    .btn-outline-dark:hover {
        background: var(--light-text);
        color: var(--light-bg);
    }

    .dark-theme .btn-outline-dark {
        border-color: var(--dark-text);
        color: var(--dark-text);
    }

    .dark-theme .btn-outline-dark:hover {
        background: var(--dark-text);
        color: var(--dark-bg);
    }

    /* Form Controls */
    .form-control {
        font-family: 'Playfair Display', serif;
        padding: 0.75rem 1rem;
        border-radius: 0;
        border: 2px solid var(--light-border);
        font-size: 1rem;
        transition: all 0.3s ease;
        background: var(--light-card);
        color: var(--light-text);
    }

    .dark-theme .form-control {
        border-color: var(--dark-border);
        background: var(--dark-card);
        color: var(--dark-text);
    }

    .form-control:focus {
        border-color: var(--light-accent);
        box-shadow: 0 0 0 3px rgba(139, 69, 19, 0.1);
    }

    .dark-theme .form-control:focus {
        border-color: var(--dark-accent);
        box-shadow: 0 0 0 3px rgba(212, 175, 55, 0.1);
    }

    /* Theme Toggle */
    .theme-toggle {
        width: 40px;
        height: 40px;
        border-radius: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
        transition: all 0.5s ease;
        background: transparent;
        border: 2px solid var(--light-border);
        padding: 0;
    }

    .theme-toggle i {
        font-size: 1.5rem;
        transition: all 0.5s ease;
        color: var(--light-accent);
    }

    .dark-theme .theme-toggle i {
        color: var(--dark-accent);
    }

    /* Navigation */
    .navbar {
        padding: 1rem 0;
        border-bottom: 2px solid var(--light-border);
        background: var(--light-card);
        margin-bottom: 2rem;
    }

    .dark-theme .navbar {
        border-bottom-color: var(--dark-border);
        background: var(--dark-card);
    }

    .navbar-brand {
        font-size: 1.5rem;
        font-weight: 800;
        color: var(--light-accent);
    }

    .dark-theme .navbar-brand {
        color: var(--dark-accent);
    }

    /* Alerts */
    .alert {
        border: 2px solid var(--light-border);
        border-radius: 0;
        padding: 1rem;
        font-weight: 500;
        font-family: 'Playfair Display', serif;
        margin-bottom: 2rem;
    }

    .alert-danger {
        background: #fff5f5;
        color: var(--light-danger);
        border-color: var(--light-danger);
    }

    .dark-theme .alert-danger {
        background: rgba(139, 0, 0, 0.1);
        color: var(--dark-danger);
        border-color: var(--dark-danger);
    }

    /* Todo Items */
    .todo-item {
        border-left: 4px solid var(--light-accent);
        padding: 1.5rem;
        margin-bottom: 1rem;
        border-radius: 0;
        background: var(--light-card);
        transition: all 0.3s ease;
        position: relative;
        overflow: hidden;
        box-shadow: 0 2px 4px var(--light-shadow);
    }

    .todo-item::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: linear-gradient(45deg, transparent, rgba(255,255,255,0.1), transparent);
        transform: translateX(-100%);
        transition: transform 0.6s ease;
    }

    .todo-item:hover::before {
        transform: translateX(100%);
    }

    .dark-theme .todo-item {
        border-left-color: var(--dark-accent);
        background: var(--dark-card);
        box-shadow: 0 2px 4px var(--dark-shadow);
    }

    .todo-item.completed {
        border-left-color: var(--light-success);
    }

    .dark-theme .todo-item.completed {
        border-left-color: var(--dark-success);
    }

    .todo-item h5 {
        margin-bottom: 0.5rem;
        color: var(--light-text);
    }

    .dark-theme .todo-item h5 {
        color: var(--dark-text);
    }

    .todo-item p {
        color: var(--light-text);
        opacity: 0.8;
    }

    .dark-theme .todo-item p {
        color: var(--dark-text);
        opacity: 0.8;
    }

    /* Theme Toggle Message */
    .theme-message {
        position: fixed;
        bottom: 20px;
        left: 50%;
        transform: translateX(-50%);
        background: var(--light-card);
        padding: 0.75rem 1.5rem;
        border-radius: 0;
        border: 2px solid var(--light-border);
        font-family: 'Playfair Display', serif;
        opacity: 0;
        transition: opacity 0.3s ease;
        z-index: 1000;
        box-shadow: 0 2px 4px var(--light-shadow);
    }

    .dark-theme .theme-message {
        background: var(--dark-card);
        border-color: var(--dark-border);
        box-shadow: 0 2px 4px var(--dark-shadow);
    }

    .theme-message.show {
        opacity: 1;
    }

    /* Modal */
    .modal-content {
        border-radius: 0;
        border: 2px solid var(--light-border);
    }

    .dark-theme .modal-content {
        border-color: var(--dark-border);
        background: var(--dark-card);
        color: var(--dark-text);
    }

    .modal-header {
        border-bottom: 2px solid var(--light-border);
    }

    .dark-theme .modal-header {
        border-bottom-color: var(--dark-border);
    }

    .modal-footer {
        border-top: 2px solid var(--light-border);
    }

    .dark-theme .modal-footer {
        border-top-color: var(--dark-border);
    }

    /* Animations */
    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(20px); }
        to { opacity: 1; transform: translateY(0); }
    }

    @keyframes slideIn {
        from { transform: translateX(-100%); }
        to { transform: translateX(0); }
    }

    .fade-in {
        animation: fadeIn 0.5s ease forwards;
    }

    .slide-in {
        animation: slideIn 0.5s ease forwards;
    }

    /* Responsive Adjustments */
    @media (max-width: 768px) {
        .display-4 {
            font-size: 2.5rem;
        }
        
        .lead {
            font-size: 1.1rem;
        }
        
        .btn {
            padding: 0.5rem 1rem;
        }

        .todo-item {
            padding: 1rem;
        }
    }
</style> 