document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('loginForm');
    const togglePassword = document.getElementById('togglePassword');
    const passwordInput = document.getElementById('password');

    loginForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value.trim();

        if(!email || !password){
            mostrarModal('Por favor completa todos los campos')
        }

        fetch(`http://localhost:8080/api/v2/users/email/${email}`)
            .then(response => {
                if(!response.ok){
                    throw new Error('Correo no encontrado');
                }
                return response.json();
            })
            .then(user => {
                if(user.password === password) {
                    localStorage.setItem('usuarioActual', JSON.stringify(user));
                    mostrarModal('Inicio de sesión exitoso 🎉');
                    setTimeout(() => {
                        window.location.href = '/index.html';
                    }, 2000);
                } else {
                    mostrarModal('Constraseña incorrecta.');
                }
            })
            .catch(error => {
                console.error(error);
                mostrarModal('Usuario no encontrado o error en la conexión.')
            });
    });

    // Ver contraseña
    togglePassword.addEventListener('click', function () {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);
        this.innerHTML = type === 'password' ? '<i class="bi bi-eye-fill"></i>' : '<i class="bi bi-eye-slash-fill"></i>';
    });
});

function mostrarModal(mensaje) {
    const modalBody = document.getElementById('infoModalBody');
    modalBody.textContent = mensaje;

    const modal = new bootstrap.Modal(document.getElementById('infoModal'));
    modal.show();
}
