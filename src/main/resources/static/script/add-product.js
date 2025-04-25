document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('productForm');

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const nombre = document.getElementById('nombreProducto').value;
        const descripcion = document.getElementById('descripcionProducto').value;
        const precio = document.getElementById('precioProducto').value;
        const stock = document.getElementById('stockProducto').value;
        const dimensiones = `${document.getElementById('largoProducto').value} X ${document.getElementById('anchoProducto').value} X ${document.getElementById('altoProducto').value}`;

        const producto = {
            name: nombre,
            description: descripcion,
            price: parseFloat(precio),
            stock: parseInt(stock),
            dimensions: dimensiones
        }

        const url = `http://localhost:8080/api/products`;

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(producto)
        })
        .then(response => {
            if (!response.ok) {
                return Promise.reject('Failed to create product');
            }
            return response.json();
        })
        .then(data => {
            console.log('Producto guardado:', data);
            alert('Producto registrado exitosamente.');
            form.reset();
        })
        .catch(error => {
            console.error('Error al registrar el producto:', error);
            alert('Error al registrar el producto. Intente nuevamente.');
        });
    });
});
