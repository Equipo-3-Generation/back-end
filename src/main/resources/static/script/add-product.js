// --> Función para agregar un nuevo producto al localStorage y a la tabla de productos
document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('productForm');

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const producto = {
            nombre: document.getElementById('nombreProducto').value,
            descripcion: document.getElementById('descripcionProducto').value,
            categoria: document.getElementById('categoriaProducto').value,
            stock: document.getElementById('stockProducto').value,
            precio: document.getElementById('precioProducto').value,
            peso: document.getElementById('pesoProducto').value,
            dimensiones: {
                largo: document.getElementById('largoProducto').value,
                ancho: document.getElementById('anchoProducto').value,
                alto: document.getElementById('altoProducto').value
            },
            materiales: {
                PLA: document.getElementById('materialPLA').checked,
                ABS: document.getElementById('materialABS').checked,
                PETG: document.getElementById('materialPETG').checked
            },
            personalizacion: document.getElementById('personalizacion').checked,
            imagen: document.getElementById('formFile').files[0]?.name || ''
        };

        // Obtener los productos anteriores y agregar el nuevo
        let productos = JSON.parse(localStorage.getItem('productos')) || [];
        productos.push(producto);
        localStorage.setItem('productos', JSON.stringify(productos));

        alert('Producto registrado exitosamente.');
        form.reset();
    });
});

// --> Función para vista previa de imágenes del formulario de creación de objetos

document.addEventListener('DOMContentLoaded', function () {
    const inputFile = document.getElementById('formFile');
    const imagePreview = document.getElementById('imagePreview');
    
    inputFile.addEventListener('change', function(event) {
        const file = event.target.files[0];
        const reader = new FileReader();

        reader.onload = function(e) {
            imagePreview.style.display = 'block'; // Muestra la imagen
            imagePreview.src = e.target.result; // Asigna la URL generada al src de la imagen
        };

        if (file) {
            reader.readAsDataURL(file); // Convierte el archivo a una URL y la asigna a la imagen
        }
    });
});