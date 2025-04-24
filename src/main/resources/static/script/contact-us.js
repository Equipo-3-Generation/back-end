document.addEventListener("DOMContentLoaded", function () {
    const formulario = document.getElementById("formulario-contacto");

    formulario.addEventListener("submit", function (event) {
        event.preventDefault(); // Evita el envío automático

        let nombre = document.getElementById("name").value.trim();
        let email = document.getElementById("email").value.trim();
        let telefono = document.getElementById("phone").value.trim();
        let mensaje = document.getElementById("message").value.trim();

        // Validar que los campos no estén vacíos
        if (nombre === "" || email === "" || telefono === "" || mensaje === "") {
            alert("Por favor, completa todos los campos.");
            return;
        }

        // Validar formato de email con expresión regular
        let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert("Por favor, introduce un correo válido.");
            return;
        }

        // Validar teléfono (solo números y longitud de 10 dígitos)
        let phoneRegex = /^[0-9]{10}$/;
        if (!phoneRegex.test(telefono)) {
            alert("Por favor, introduce un número de teléfono válido de 10 dígitos.");
            return;
        }

        // Si pasa todas las validaciones, se puede enviar el formulario
        alert("Formulario enviado con éxito");
        formulario.submit(); // Ahora sí envía el formulario
    });
});
