// Navbar
fetch("../../../src/main/resources/static/general/navbar/navbar.html")
    .then(response => response.text())
    .then(data => {
    document.getElementById("navbar-placeholder").innerHTML = data;
});

