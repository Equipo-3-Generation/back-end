// Navbar
fetch("../general/navbar/navbar.html")
    .then(response => response.text())
    .then(data => {
    document.getElementById("navbar-placeholder").innerHTML = data;
    actualizarContadorCarrito();
});


function actualizarContadorCarrito() {
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    const carritoContador = document.getElementById('carritoContador');
    if (!carritoContador) return;

    let totalCantidad = 0;

    carrito.forEach(producto => {
        totalCantidad += producto.cantidad || 1;
    });

    carritoContador.textContent = totalCantidad;
}