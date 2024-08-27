function toggleSidebar() {
    var sidebar = document.getElementById('social-share');
    if (sidebar.classList.contains('open')) {
        sidebar.classList.remove('open');
    } else {
        sidebar.classList.add('open');
    }
}
