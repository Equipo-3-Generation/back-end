function mostrarModal(mensaje) {
    const modalBody = document.getElementById("infoModalBody");
    modalBody.textContent = mensaje;

    const modal = new bootstrap.Modal(document.getElementById("infoModal"));
    modal.show();
}

// Capturar el formulario
document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Evita que el formulario recargue la página

    // Obtener valores del formulario
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    const users = JSON.parse(localStorage.getItem("users")) || [];
    const foundUser = users.find(user => user.email === email && user.password === password);

    if (foundUser) {
        mostrarModal("Inicio de sesión exitoso"); // Mostrar mensaje solo si es correcto

        localStorage.setItem("currentUser", JSON.stringify(foundUser));

        // Redirigir a inicio después de 2 segundos
        setTimeout(() => {
            window.location.href = "/index.html";
        }, 2000);
    } else {
        mostrarModal("Correo o contraseña incorrectos");
    }

    // Limpiar formulario después de enviar
    document.getElementById("loginForm").reset();
});



