// link productos https://www.xataka.com/basics/61-proyectos-impresion-3d-utiles-que-te-puedes-imprimir

document.addEventListener('DOMContentLoaded', function () {
    const contenedor = document.getElementById('productList');

    fetch(`http://3.84.190.109/api/v2/products`)
        .then(response => response.json())
        .then(productos => {
            localStorage.setItem('productos', JSON.stringify(productos));

            productos.forEach((producto, index) => {
                const card = document.createElement('div');
                card.className = 'col';

                card.innerHTML = `
                    <div class="card h-100">
                        <img src="${producto.imageUrl}" class="card-img-top" alt="${producto.name}">
                        <div class="card-body">
                            <h5 class="card-title">${producto.name}</h5>
                            <p class="card-text">${producto.description}</p>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item"><strong>Precio:</strong> $${producto.price}</li>
                                <li class="list-group-item"><strong>Stock:</strong> ${producto.stock} piezas</li>
                                <li class="list-group-item"><strong>Peso:</strong> ${producto.weight} g</li>
                                <li class="list-group-item"><strong>Dimensiones:</strong> ${producto.dimensions}</li>
                                <li class="list-group-item"><strong>Material:</strong> ${producto.materials}</li>
                                <li class="list-group-item"><strong>Personalización:</strong> ${producto.customizable ? "Sí" : "No"}</li>
                            </ul>
                        </div>
                        <div class="card-footer text-center">
                            <button class="btn btn-success m-1" onclick="enviarAInicio(${index})">Enviar a Inicio</button>
                            <button class="btn btn-primary m-1" onclick="enviarAProductos(${index})">Enviar a Productos</button>
                        </div>
                    </div>
                `;
                contenedor.appendChild(card);
            });
        })
        .catch(error => {
            console.error('Error al cargar productos:', error);
        });
});

// Funciones para enviar
function enviarAInicio(index) {
    const productos = JSON.parse(localStorage.getItem('productos')) || [];
    const inicioProductos = JSON.parse(localStorage.getItem('inicioProductos')) || [];

    if (inicioProductos.length >= 6) {
        alert('El límite de 6 productos destacados en la portada se ha alcanzado.');
        return;
    }

    inicioProductos.push(productos[index]);
    localStorage.setItem('inicioProductos', JSON.stringify(inicioProductos));
    alert('Producto enviado a Inicio.');
}

function enviarAProductos(index) {
    const productos = JSON.parse(localStorage.getItem('productos')) || [];
    const paginaProductos = JSON.parse(localStorage.getItem('paginaProductos')) || [];
    paginaProductos.push(productos[index]);
    localStorage.setItem('paginaProductos', JSON.stringify(paginaProductos));
    alert('Producto enviado a Página de Productos.');
}