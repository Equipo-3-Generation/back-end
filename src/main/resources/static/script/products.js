document.addEventListener('DOMContentLoaded', () => {
    const contenedor = document.getElementById('productosContainer');
    const productos = JSON.parse(localStorage.getItem('productosEnCatalogo')) || [];

    productos.forEach(producto => {
        const div = document.createElement('div');
        div.innerHTML = `
            <div class="card mb-3">
                <img src="${producto.imagen ? producto.imagen : 'https://via.placeholder.com/150'}" class="card-img-top" alt="${producto.nombre}">
                <div class="card-body">
                    <h5 class="card-title">${producto.nombre}</h5>
                    <p class="card-text">${producto.descripcion}</p>
                    <p class="card-text"><strong>Precio:</strong> ${producto.precio} MXN</p>
                </div>
            </div>
        `;
        contenedor.appendChild(div);
    });
});