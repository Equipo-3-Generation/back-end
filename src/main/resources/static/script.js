//Carrusel
document.addEventListener("DOMContentLoaded", function() {
    console.log("Página cargada correctamente.");

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
// carrusuel
document.addEventListener("DOMContentLoaded", function() {
    console.log("Página cargada correctamente.");
});


///
document.addEventListener('DOMContentLoaded', () => {
    const contenedor = document.getElementById('productosContainer');
    const productos = JSON.parse(localStorage.getItem('productosEnInicio')) || []; 

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