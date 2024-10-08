document.addEventListener('DOMContentLoaded', function () {
    // Passo 1: Capturar o ID do pedido da URL
    const urlParams = new URLSearchParams(window.location.search);
    const pedidoId = urlParams.get('idCliente'); // Supomos que o ID é passado como parâmetro na URL (ex: ?id=1)

    // Passo 2: Realizar o GET dinâmico
    fetch(`http://localhost:8080/pedidos/${pedidoId}`)
        .then(response => response.json())
        .then(data => {
            const pedidosSection = document.getElementById('pedidos-pagar');
            pedidosSection.innerHTML = ''; // Limpa a seção antes de exibir os dados
            
            let totalGeral = 0; // Variável para acumular os subtotais

            // Passo 3: Iterar sobre o array "content" e renderizar os pedidos
            data.content.forEach(pedido => {
                const pedidoElement = document.createElement('div');
                pedidoElement.classList.add('pedido-item');

                // Montar o HTML para cada pedido
                pedidoElement.innerHTML = `
                    <h3>${pedido.nome}</h3>
                    <img src="${pedido.primeiraImagem}" alt="${pedido.nome}" width="100" />
                    <p>Quantidade: ${pedido.quantidade}</p>
                    <p>Preço Unitário: R$${pedido.precoUnitario.toFixed(2)}</p>
                    <p>Subtotal: R$${pedido.subtotal.toFixed(2)}</p>
                    <hr>
                `;
                
                pedidosSection.appendChild(pedidoElement);

                // Somar o subtotal ao total geral
                totalGeral += pedido.subtotal;
            });

            // Passo 4: Exibir o total geral
            const totalElement = document.createElement('div');
            totalElement.classList.add('total-geral');
            totalElement.innerHTML = `
                <h3 class="preco-final">Total Geral: R$${totalGeral.toFixed(2)}</h3>
            `;
            
            pedidosSection.appendChild(totalElement); // Adicionar o total geral no final
        })
        .catch(error => {
            console.error('Erro ao buscar os pedidos:', error);
        });
});
