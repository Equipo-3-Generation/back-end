document.addEventListener("DOMContentLoaded", () => {
    const cartItems = JSON.parse(localStorage.getItem("cart")) || [];
    const cartContainer = document.getElementById("cart-items");
    const totalSpan = document.getElementById("total");
  
    let total = 0;
    cartItems.forEach(item => {
      const itemDiv = document.createElement("div");
      itemDiv.className = "mb-2";
      itemDiv.innerHTML = `
        <strong>${item.nombre}</strong> - $${item.precio}
      `;
      cartContainer.appendChild(itemDiv);
      total += item.precio;
    });
  
    totalSpan.textContent = total;
  });
  [{ nombre: "Producto 1", precio: 120 }, { nombre: "Producto 2", precio: 80 }]
