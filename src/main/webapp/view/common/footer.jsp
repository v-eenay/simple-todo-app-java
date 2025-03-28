    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function toggleTheme() {
            const body = document.body;
            const icon = document.querySelector('.theme-toggle i');
            
            if (body.classList.contains('light-theme')) {
                body.classList.remove('light-theme');
                body.classList.add('dark-theme');
                icon.classList.remove('bi-moon-stars');
                icon.classList.add('bi-sun');
            } else {
                body.classList.remove('dark-theme');
                body.classList.add('light-theme');
                icon.classList.remove('bi-sun');
                icon.classList.add('bi-moon-stars');
            }
        }
    </script>
</body>
</html> 