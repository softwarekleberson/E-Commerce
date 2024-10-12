// Funções para abrir e fechar os modais

function openModal(modalId) {
    document.getElementById(modalId).style.display = "block";
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = "none";
}

// Abertura dos modais
document.getElementById("editEntrega").onclick = function () {
    openModal("modalEntrega");
}

document.getElementById("editCobranca").onclick = function () {
    openModal("modalCobranca");
}

document.getElementById("editCartao").onclick = function () {
    openModal("modalCartao");
}

// Fechando os modais
document.getElementById("closeEntrega").onclick = function () {
    closeModal("modalEntrega");
}

document.getElementById("closeCobranca").onclick = function () {
    closeModal("modalCobranca");
}

document.getElementById("closeCartao").onclick = function () {
    closeModal("modalCartao");
}

// Fechando modais ao clicar fora deles
window.onclick = function (event) {
    if (event.target.className === "modal") {
        closeModal("modalEntrega");
        closeModal("modalCobranca");
        closeModal("modalCartao");
    }
}

// Função para aplicar cupons
document.getElementById("applyCoupons").onclick = function () {
    var coupon1 = document.getElementById("coupon1").value;
    var coupon2 = document.getElementById("coupon2").value;

    // Lógica para aplicar cupons (apenas exemplo, ajuste conforme necessário)
    if (coupon1) {
        alert("Cupom 1 aplicado: " + coupon1);
    }
    if (coupon2) {
        alert("Cupom 2 aplicado: " + coupon2);
    }
}
