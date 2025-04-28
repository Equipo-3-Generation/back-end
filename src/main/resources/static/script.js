//Carrusel
document.addEventListener("DOMContentLoaded", function() {

    const usuarioActual = JSON.parse(localStorage.getItem('usuarioActual'));
    if (usuarioActual) {
        console.log('Bienvenida, ' + usuarioActual.name);
    }

    // Cards
    const ratings = document.querySelectorAll(".rating");

    ratings.forEach(rating => {
        let stars = rating.querySelectorAll("i");
        let selectedRating = 0;

        stars.forEach(star => {
            star.addEventListener("mouseover", function() {
                highlightStars(stars, this.dataset.value);
            });

            star.addEventListener("click", function() {
                selectedRating = this.dataset.value;
                highlightStars(stars, selectedRating, true);
            });
        });

        rating.addEventListener("mouseleave", function() {
            highlightStars(stars, selectedRating);
        });
    });

    function highlightStars(stars, value, fixed = false) {
        stars.forEach(star => {
            let starValue = parseInt(star.dataset.value);
            let selectedValue = parseInt(value);

            if (starValue <= selectedValue) {
                star.classList.add("bi-star-fill");
                star.classList.remove("bi-star");
            } else {
                star.classList.add("bi-star");
                star.classList.remove("bi-star-fill");
            }
        });
    }

});

document.addEventListener('DOMContentLoaded', function () {
    const contenedor = document.getElementById('inicioProductosContainer');
    const titulo = document.getElementById('productosRecientesTitulo');
    const productos = JSON.parse(localStorage.getItem('inicioProductos')) || [];

    if (productos.length > 0) {
        titulo.style.display = 'block'; // Mostrar el título si hay productos
    }

    productos.slice(0, 6).forEach((producto, index) => {
        const card = document.createElement('div');
        card.className = 'col-md-4';
        card.innerHTML = `
            <div class="card text-center">
                <div class="imagen-box">
                    <img src="${producto.imageUrl}" class="card-img-top imagen" alt="${producto.name}">
                </div>
                <div class="card-body">
                    <h5 class="card-title fw-bold text-danger">${producto.name}</h5>
                    <p class="card-text fw-bold">$${producto.price}</p>
                    <a href="/pages/products.html" class="btn btn-dark rounded-2">Ver más productos</a>
                </div>
            </div>
        `;
        contenedor.appendChild(card);
    });
});
