document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('productForm');

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        // Capturar datos del formulario
        const name = document.getElementById('nombreProducto').value.trim();
        const description = document.getElementById('descripcionProducto').value.trim();
        const categorySelect = document.getElementById('categoriaProducto');
        const category = categorySelect.options[categorySelect.selectedIndex].text;
        const price = parseFloat(document.getElementById('precioProducto').value);
        const stock = parseInt(document.getElementById('stockProducto').value);
        const dimensions = `${document.getElementById('largoProducto').value}x${document.getElementById('anchoProducto').value}x${document.getElementById('altoProducto').value} cm`;
        const weight = parseFloat(document.getElementById('pesoProducto').value);
        const imageUrl = document.getElementById('formFile').value; 
        const customizable = document.getElementById('personalizacion').checked;

        // Material
        const materials = [];
        if (document.getElementById('materialPLA').checked) materials.push('PLA');
        if (document.getElementById('materialABS').checked) materials.push('ABS');
        if (document.getElementById('materialPETG').checked) materials.push('PETG');

        // Validación de entradas
        if (!name || !description || isNaN(price) || isNaN(stock) || isNaN(weight) || !imageUrl) {
            alert('Por favor, completa todos los campos.');
            return;
        }

        // Crear objeto producto
        const nuevoProducto = {
            name,
            description,
            category,
            price,
            stock,
            dimensions,
            weight,
            imageUrl,
            materials: materials.join(', '),
            customizable
        };

        // Guardar objetos
        fetch(`http://localhost:8080/api/v2/products`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoProducto)
        })
        .then(response => {
            if(!response.ok) {
                throw new Error('No se pudo agregar el producto');
            }
            return response.json();
        })
        .then(data => {
            mostrarToast('Producto agregado exitosamente.');
            productForm.reset();
            document.getElementById('imagePreview').style.display = 'none';
        })
        .catch(error => {
            console.error('Error', error);
            if (error.message.includes('409') || error.message.includes('duplicado')) {
                mostrarToast('Ya existe un producto con ese nombre.');
            } else {
                mostrarToast('Error al agregar producto.');
            }
        });
    });

    // Mostrar vista previa de imagen al escribir URL
    const inputImagen = document.getElementById('formFile');
    const imagePreview = document.getElementById('imagePreview');

    inputImagen.addEventListener('input', function() {
        const url = inputImagen.value.trim();
        if (url) {
            imagePreview.src = url;
            imagePreview.style.display = 'block';
        } else {
            imagePreview.style.display = 'none';
        }
    });
});

function mostrarToast(mensaje) {
    const toast = document.getElementById('toast');
    const toastBody = toast.querySelector('.toast-body');
    toastBody.textContent = mensaje;
    const bsToast = new bootstrap.Toast(toast);
    bsToast.show();
}