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
                    <p>${order.dataPedido}</p>
                    <p>R$ ${order.subtotal}</p>
                    <p>Pedido ID: ${order.idPedido}</p>
                    <p class="codigo-pedido">${order.codigoPedido}</p>
                </div>
            </div>
            <div>
                <p class="data-entrega">Status: ${order.status}</p>
                <div class="imagem-e-descricao">
                    <img src="${order.primeiraImagem}" alt="">
                    <p class="data-entrega">Entregue ${order.entregue}</p>
                    <p class="nome-produto">${order.nome}</p>
                    <p class="quantidade-produto">${order.quantidade}</p>
                    <button class="botao-devolucao">Devolução</button>
                </div>
            </div>
        </div>
        `;
    };

    const fetchOrders = async (orderId) => {
        try {
            const response = await fetch(`http://localhost:8080/pedidos/pagos/${orderId}`);
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

            // Adicionando listeners para o botão de devolução
            document.querySelectorAll('.botao-devolucao').forEach((button, index) => {
                button.addEventListener('click', async () => {
                    const orderElement = button.closest('.caixa-principal');
                    const codigoPedido = orderElement.querySelector('.codigo-pedido').textContent;

                    // Captura o clienteId da URL
                    const urlParams = new URLSearchParams(window.location.search);
                    const clienteId = urlParams.get('clienteId');

                    // Construir a URL com clienteId dinâmico
                    const url = `http://localhost:8080/devolucoes/${clienteId}`;

                    // Montar o corpo da requisição
                    const requestBody = {
                        codigoPedido: codigoPedido
                    };

                    try {
                        // Fazer a requisição POST
                        const postResponse = await fetch(url, {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(requestBody) // Enviar o código do pedido no corpo da requisição
                        });

                        if (postResponse.ok) {
                            console.log('Devolução solicitada com sucesso.');
                        } else {
                            console.error('Erro ao solicitar devolução:', postResponse.statusText);
                        }
                    } catch (error) {
                        console.error('Erro ao enviar a requisição de devolução:', error);
                    }
                });
            });
        } catch (error) {
            console.error('Erro ao buscar o pedido:', error);
        }
    };

    const urlParams = new URLSearchParams(window.location.search);
    const orderId = urlParams.get('clienteId');

    if (orderId) {
        fetchOrders(orderId);
    } else {
        console.error('ID do pedido não encontrado na URL');
    }
});
