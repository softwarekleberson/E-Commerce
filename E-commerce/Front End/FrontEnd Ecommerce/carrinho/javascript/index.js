document.addEventListener("DOMContentLoaded", function() {
    // Adiciona evento de clique ao ícone "x" em cada linha da tabela
    var removeButtons = document.querySelectorAll(".fa-square-xmark");
    removeButtons.forEach(function(button) {
        button.addEventListener("click", function() {
            var row = this.closest("tr"); // Encontra a linha pai do ícone clicado
            row.remove(); // Remove a linha
            updateCartTotal(); // Atualiza o total do carrinho
        });
    });

    // Função para atualizar o total do carrinho
    function updateCartTotal() {
        var total = 0;
        var rows = document.querySelectorAll("#cart-table tr");
        rows.forEach(function(row) {
            var price = row.querySelector(".valor-produto").textContent.replace("R$", "").trim();
            total += parseFloat(price);
        });
        document.querySelector(".valor-total").textContent = "R$ " + total.toFixed(2);
    }
});
