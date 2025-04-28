document.addEventListener('DOMContentLoaded', function () {
    
    const contenedor = document.getElementById('productosContainer');
    const productos = JSON.parse(localStorage.getItem('paginaProductos')) || [];

    productos.forEach((producto, index) => {
        const card = document.createElement('div');
        card.className = 'col';
        card.innerHTML = `
            <div class="card rounded-0 h-100 text-center">
                <div class="imagen-box">
                    <img src="${producto.imagen}" class="card-img-top imagen" alt="${producto.nombre}">
                </div>
                <div class="card-body d-flex flex-column justify-content-between">
                    <h5 class="card-title text-primary-emphasis fw-bold">${producto.nombre.toUpperCase()}</h5>
                    <p class="card-text text-danger fw-bold">$${producto.precio}</p>
                    <p class="text-muted small">Categoría: ${producto.categoria}</p>
                    <button class="btn btn-dark rounded-0 mt-auto" onclick="agregarAlCarrito(${index})">Añadir al carrito</button>
                </div>
            </div>
        `;
        contenedor.appendChild(card);
    });
    actualizarContadorCarrito();
});

// Función para agregar al carrito
function agregarAlCarrito(index) {
    const productos = JSON.parse(localStorage.getItem('paginaProductos')) || [];
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];

    carrito.push(productos[index]);
    localStorage.setItem('carrito', JSON.stringify(carrito));
    actualizarContadorCarrito();

    // Mostrar mensaje
    alert('Producto añadido al carrito.');
}

function agregarProductoManual(nombre, precio, imagen) {
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    carrito.push({
        nombre: nombre,
        precio: precio,
        imagen: imagen,
        cantidad: 1
    });
    localStorage.setItem('carrito', JSON.stringify(carrito));
    actualizarContadorCarrito();
    alert('Producto añadido al carrito.');
}