document.addEventListener("DOMContentLoaded", () => {
    // Captura o ID do cliente da URL
    const urlParams = new URLSearchParams(window.location.search);
    const clienteId = urlParams.get('clienteId');

    if (clienteId) {
        // Faz a requisição à API para obter os dados do pedido
        fetch(`http://localhost:8080/pedidos/${clienteId}`)
            .then(response => response.json())
            .then(data => renderCarrinho(data))
            .catch(error => console.error('Erro ao buscar dados:', error));
    } else {
        console.error('ID do cliente não encontrado na URL.');
    }
});

function renderCarrinho(data) {
    const tabelaCarrinho = document.querySelector('table tbody');

    // Limpa a tabela antes de adicionar novos itens
    tabelaCarrinho.innerHTML = '';

    // Itera sobre os itens do carrinho e os preenche dinamicamente
    data.content.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td><a href="#"><img src="${item.primeiraImagem}" alt="${item.nome}"></a></td>
            <td class="descricao">
                <h4>${item.nome}</h4>
                <p>${item.dataPedido}</p>
            </td>
            <td>
                <select class="cor-seletor" name="quantidade" id="quantidade-${item.id}">
                    ${generateQuantityOptions(item.quantidade)}
                </select>
            </td>
            <td class="valor-produto">
                <p>R$ ${item.precoUnitario.toFixed(2)}</p>
            </td>
            <td>
                <p>R$ ${item.subtotal.toFixed(2)}</p>
            </td>
            <td>
                <i class="fa-solid fa-square-xmark remove-item" data-id="${item.id}" style="cursor: pointer;"></i>
            </td>
        `;
        tabelaCarrinho.appendChild(row);
    });

    // Atualiza subtotal e total
    const subtotal = data.content.reduce((acc, item) => acc + item.subtotal, 0);
    document.querySelector('.valor-subtotal').textContent = `R$ ${subtotal.toFixed(2)}`;
    document.querySelector('.valor-total').textContent = `R$ ${subtotal.toFixed(2)}`;

    // Função para lidar com a remoção de itens
    document.querySelectorAll('.remove-item').forEach(button => {
        button.addEventListener('click', (event) => {
            const itemId = event.target.getAttribute('data-id');
            removerItem(itemId);
        });
    });
}

// Função para remover o item do carrinho com requisição DELETE
function removerItem(itemId) {
    fetch(`http://localhost:8080/pedidos/itens/produto/${itemId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            console.log(`Item ${itemId} removido com sucesso.`);
            // Atualiza o carrinho após a remoção
            location.reload();  // Recarrega a página para atualizar os itens do carrinho
        } else {
            console.error(`Erro ao remover o item ${itemId}.`);
        }
    })
    .catch(error => console.error('Erro ao tentar remover o item:', error));
}

// Função auxiliar para gerar as opções do select com base na quantidade do item
function generateQuantityOptions(quantidadeSelecionada) {
    let options = '';
    for (let i = 1; i <= 10; i++) {
        options += `<option value="${i}" ${i === quantidadeSelecionada ? 'selected' : ''}>${i}</option>`;
    }
    return options;
}
