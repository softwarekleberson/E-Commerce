document.addEventListener('DOMContentLoaded', () => {
    const ordersContainer = document.getElementById('orders-container');

    const createOrderHTML = (order) => {
        return `
        <div class="caixa-principal">
            <div class="cabecalho">
                <div class="informacoes-pedito">
                    <p>Pedido realizado</p>
                    <p>Total</p>
                    <p>Entregue</p>
                    <p>Nº pedido</p>
                </div>
                <div class="informacoes-pedito-valores">
                    <p>${order.pedidoRealizado}</p>
                    <p>R$ ${order.valorTotal}</p>
                    <p>Cliente ID: ${order.idCliente}</p>
                    <p>${order.codigoPedido}</p>
                </div>
            </div>
            <div>
                <p class="data-entrega">Status: ${order.statusEntrega}</p>
                <div class="imagem-e-descricao">
                <img src="${order.urlLivro}" alt="">
                <p class="nome-produto">${order.nome}</p>
                <p class="descricao-produto">${order.descricaoPedido}</p>
                <p class="quantidade-produto">${order.quantidade}</p>
                <button class="botao-devolucao">Devolução</button>
                <button class="botao-trocar">Trocar</button>
            </div>
        </div>
        `;
    };

    const fetchOrders = async (orderId) => {
        try {
            const response = await fetch(`http://localhost:8080/pedidos/${orderId}`);
            if (!response.ok) {
                throw new Error('Erro ao buscar o pedido');
            }
            const data = await response.json();
            const orders = data.content;

            ordersContainer.innerHTML = ''; 
            orders.forEach(order => {
                const orderHTML = createOrderHTML(order);
                ordersContainer.innerHTML += orderHTML;
            });
        } catch (error) {
            console.error('Erro ao buscar o pedido:', error);
        }
    };

    const urlParams = new URLSearchParams(window.location.search);
    const orderId = urlParams.get('clienteId');

    if (orderId) {
        console.log('Order ID:', orderId); 
        fetchOrders(orderId);
    } else {
        console.error('ID do pedido não encontrado na URL');
    }
});
