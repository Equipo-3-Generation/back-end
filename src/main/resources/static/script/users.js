document.addEventListener("DOMContentLoaded", () => {
    const user = JSON.parse(localStorage.getItem("usuario"));

    if (user) {
        document.getElementById("user-name").textContent = user.name || "No especificado";
        document.getElementById("user-email").textContent = user.email || "No especificado";
        document.getElementById("user-phone").textContent = user.telephone || "No especificado";
    } else {
        alert("No hay datos de usuario. Redirigiendo al inicio de sesión.");
        window.location.href = "/CodigoCepa/pages/registrer.html"; // Redirige al registro si no hay usuario
    }

    document.getElementById("logout-btn").addEventListener("click", () => {
        localStorage.removeItem("usuario");
        window.location.href = "/CodigoCepa/pages/registrer.html"; // Regresa al formulario de registro
    });
});