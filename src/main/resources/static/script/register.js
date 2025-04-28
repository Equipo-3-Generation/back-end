document.addEventListener('DOMContentLoaded', function () {
    const registerForm = document.getElementById('registerForm');
    const togglePassword = document.getElementById('togglePassword');
    const passwordInput = document.getElementById('password');
    const toggleConfirmPassword = document.getElementById('toggleConfirmPassword');
    const confirmPasswordInput = document.getElementById('confirm-password');

    registerForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const name = document.getElementById('name').value.trim();
        const tel = document.getElementById('tel').value.trim();
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;
        const terminosAceptados = document.getElementById('terminos').checked;

        const errores = [];
        const usuarios = JSON.parse(localStorage.getItem('usuarios')) || [];

        // Validaciones
        if (!name) errores.push('El nombre es obligatorio.');
        if (!tel.match(/^\d{10}$/)) errores.push('El número de teléfono debe tener 10 dígitos numéricos.');
        if (!email.includes('@') || !email.includes('.')) errores.push('Correo electrónico no válido.');
        if (password.length < 8) errores.push('La contraseña debe tener mínimo 8 caracteres.');
        if (password !== confirmPassword) errores.push('Las contraseñas no coinciden.');
        if (usuarios.find(user => user.email === email)) errores.push('Este correo ya está registrado.');
        if (!terminosAceptados) errores.push('Debes aceptar los Términos y Condiciones.');

        const errorDiv = document.getElementById('errores');
        errorDiv.innerHTML = '';

        if (errores.length > 0) {
            errores.forEach(err => {
                const alerta = document.createElement('div');
                alerta.className = 'alert alert-danger';
                alerta.textContent = err;
                errorDiv.appendChild(alerta);
            });
            return;
        }

        // Crear objeto
        const nuevoUsuario = { 
            name, 
            telephoneNumber: tel, 
            email, 
            password 
        };

        fetch(`http://localhost:8080/api/v2/users`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoUsuario)
        })
        .then(response => {
            if(!response.ok) {
                throw new Error('No se pudo registrar el usuario.');
            }
            return response.json();
        })
        .then(data => {
            mostrarModal('Registro exitoso 🎉');
            registerForm.reset();
            setTimeout(() => {
                window.location.href = '/pages/login.html';
            }, 2000);
        })
        .catch(error => {
            console.error('Error', error);
            mostrarModal('Error al registrar. Intenta de nuevo.')
        });
    });

    // Ver contraseña
    togglePassword.addEventListener('click', function () {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);
        this.innerHTML = type === 'password' ? '<i class="bi bi-eye-fill"></i>' : '<i class="bi bi-eye-slash-fill"></i>';
    });

    // Ver contraseña confirmacion
    toggleConfirmPassword.addEventListener('click', function () {
        const type = confirmPasswordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        confirmPasswordInput.setAttribute('type', type);
        this.innerHTML = type === 'password' ? '<i class="bi bi-eye-fill"></i>' : '<i class="bi bi-eye-slash-fill"></i>';
    });
});

function mostrarModal(mensaje) {
    const modalBody = document.getElementById('infoModalBody');
    modalBody.textContent = mensaje;

    const modal = new bootstrap.Modal(document.getElementById('infoModal'));
    modal.show();
}
