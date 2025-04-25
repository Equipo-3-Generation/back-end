document.getElementById("edit-email").addEventListener("click", function() {
    let emailInput = document.getElementById("email-deshabilitado");
    emailInput.disabled = !emailInput.disabled; // Alternar entre habilitado/deshabilitado

    // Cambiar contenido del modal
    document.getElementById("alertModalLabel").innerText = "Cambiar tu correo electrónico";
    document.getElementById("modal-body-text").innerText = "Confirma que deseas cambiar tu correo electrónico haciendo clic en el enlace del correo electrónico que te enviamos.";

    // Mostrar el modal
    let modal = new bootstrap.Modal(document.getElementById("alertModal"));
    modal.show();

    // Acción del botón de confirmación
    document.getElementById("confirm-btn").addEventListener("click", function() {
        modal.hide();  // Cierra el modal al confirmar
    }, { once: true }); // `{ once: true }` evita que se agreguen múltiples eventos al hacer clic varias veces
});