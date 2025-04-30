document.addEventListener('DOMContentLoaded', function () {
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    const carritoContainer = document.getElementById('carritoContainer');
    const totalCarrito = document.getElementById('totalCarrito');
    const pagarBtn = document.querySelector('.btn-success');

    // Función para actualizar el carrito
    function actualizarCarrito() {
        carritoContainer.innerHTML = '';
        let total = 0;
        
        if (carrito.length === 0) {
            carritoContainer.innerHTML = `
                <tr>
                    <td colspan="5" class="text-center">
                        <div class="p-5">
                            <h4>🛒 ¡Tu carrito está vacío!</h4>
                            <p>Agrega productos para continuar con tu compra.</p>
                            <a href="/pages/products.html" class="btn btn-primary mt-3">Explorar productos</a>
                        </div>
                    </td>
                </tr>
            `;
            totalCarrito.textContent = "$0.00"; // Total en cero
        }

        carrito.forEach((producto, index) => {
            if (!producto || producto.price == null) return;

            const subtotal = producto.price * (producto.cantidad || 1);
            total += subtotal;

            const fila = document.createElement('tr');
            fila.innerHTML = `
                <td>
                    <img src="${producto.imageUrl}" alt="${producto.name}" width="50" class="me-2">
                    ${producto.name}
                </td>
                <td>$${producto.price.toFixed(2)}</td>
                <td>
                    <input type="number" min="1" value="${producto.cantidad || 1}" class="form-control cantidad-input" data-index="${index}">
                </td>
                <td>$${subtotal.toFixed(2)}</td>
                <td>
                    <button class="btn btn-danger btn-sm eliminar-btn" data-index="${index}">🗑️</button>
                </td>
            `;
            carritoContainer.appendChild(fila);
        });

        totalCarrito.textContent = `$${total.toFixed(2)}`;
    }

    // Evento para proceder a pagar
    pagarBtn.addEventListener('click', function() {
        if (carrito.length === 0) {
            alert('Tu carrito está vacío.');
            return;
        }

        const currentUser = JSON.parse(localStorage.getItem('currentUser'));

        if (!currentUser) {
            alert('Debes iniciar sesión para proceder a pagar.');
            window.location.href = "/pages/login.html"; // por si quieres redirigir
            return;
        }

        const userId = currentUser.id;
        const productIds = carrito.map(producto => producto.id); // sólo IDs de productos

        fetch('http://3.84.190.109/api/v2/carts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ userId, productIds })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('No se pudo guardar el carrito');
            }
            return response.json();
        })
        .then(data => {
            console.log('Carrito guardado exitosamente:', data);
            alert('¡Compra registrada! 🎉');
            // Opcional: limpiar carrito localStorage
            localStorage.removeItem('carrito');
            location.reload(); // recarga para ver carrito vacío
        })
        .catch(error => {
            console.error('Error', error);
            alert('Error al guardar el carrito.');
        });
    });

    // Evento para cambiar cantidad
    carritoContainer.addEventListener('input', function (e) {
        if (e.target.classList.contains('cantidad-input')) {
            const index = e.target.dataset.index;
            carrito[index].cantidad = parseInt(e.target.value);
            localStorage.setItem('carrito', JSON.stringify(carrito));
            actualizarCarrito();
            actualizarContadorCarrito();
        }
    });

    // Evento para eliminar productos
    carritoContainer.addEventListener('click', function (e) {
        if (e.target.classList.contains('eliminar-btn')) {
            const index = e.target.dataset.index;
            carrito.splice(index, 1);
            localStorage.setItem('carrito', JSON.stringify(carrito));
            actualizarCarrito();
            actualizarContadorCarrito();
        }
    });

    actualizarCarrito();
});