// link productos https://www.xataka.com/basics/61-proyectos-impresion-3d-utiles-que-te-puedes-imprimir

document.addEventListener('DOMContentLoaded', function () {
    const contenedor = document.getElementById('productList');
    //const productos = JSON.parse(localStorage.getItem('productos')) || [];

        const url = `http://3.84.190.109/api/products`;

        fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                return response.json();
            })
            .then(productos => {
                productos.forEach((producto, index) => {
                    const card = document.createElement('div');
                    card.className = 'col';
                    card.innerHTML = `
                        <div class="card h-100">
                            <img src="${producto.imagen ? producto.imagen : 'https://via.placeholder.com/150'}" class="card-img-top" alt="${producto.nombre}">
                            <div class="card-body">
                                <h5 class="card-title">${producto.nombre}</h5>
                                <p class="card-text">${producto.descripcion}</p>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">Precio: ${producto.precio} MXN</li>
                                    <li class="list-group-item">Stock: ${producto.stock} piezas</li>
                                    <li class="list-group-item">Peso: ${producto.peso} g</li>
                                </ul>
                                <div class="mt-3 justify-content-between">
                                    <button class="btn btn-success btn-sm" onclick="enviarProducto(${index}, 'inicio')">Enviar a inicio</button>
                                    <button class="btn btn-primary btn-sm" onclick="enviarProducto(${index}, 'catalogo')">Enviar a productos</button>
                                </div>
                            </div>
                        </div>
                    `;
                    contenedor.appendChild(card);
                });
            })
            .catch(error => {
                console.error(error);
            })
});

function enviarProducto(index, destino) {
    const productos = JSON.parse(localStorage.getItem('productos')) || [];
    const producto = productos[index];
    
    let clave;
    if (destino === 'inicio') {
        clave = 'productosEnInicio';
    } else if (destino === 'catalogo') {
        clave = 'productosEnCatalogo';
    }

    const lista = JSON.parse(localStorage.getItem(clave)) || [];
    lista.push(producto);
    localStorage.setItem(clave, JSON.stringify(lista));
    alert(`Producto enviado a ${destino === 'inicio' ? 'inicio' : 'catálogo'} correctamente.`);
}