// Função para capturar os parâmetros da URL
function getParamsFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    const livroId = urlParams.get('id');
    const clienteId = urlParams.get('clienteId');

    return { livroId, clienteId };
}

// Função para capturar a quantidade do span
function getQuantidade() {
    const quantidadeElemento = document.getElementById('quantidade');
    return parseInt(quantidadeElemento.textContent, 10);
}

// Adicionando o listener para o botão de "Adicionar ao carrinho"
document.getElementById('adicionar-carrinho').addEventListener('click', () => {
    const { livroId, clienteId } = getParamsFromURL(); // Captura id do livro e clienteId
    const quantidade = getQuantidade(); // Captura a quantidade do span

    // Dados para enviar na requisição
    const data = {
        quantidade,
    };

    console.log('Dados para adicionar ao carrinho:', data);

    console.log(clienteId, livroId, data.quantidade)
    // Exemplo de requisição POST
    fetch(`http://localhost:8080/pedidos/${livroId}/${clienteId}`, { // Corrigindo a URL
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => response.json())
        .then(result => {
            console.log('Sucesso:', result);
            // Aqui você pode adicionar alguma lógica após o sucesso da requisição
        })
        .catch(error => {
            console.error('Erro:', error);
        });
});

// Carregar os detalhes do livro ao carregar a página
document.addEventListener('DOMContentLoaded', () => {
    const { livroId } = getParamsFromURL();
    carregarDetalhesLivro(livroId);

    // Controle de quantidade
    const quantidadeElemento = document.getElementById('quantidade');
    let quantidade = parseInt(quantidadeElemento.textContent, 10);

    document.getElementById('incrementar').addEventListener('click', () => {
        quantidade += 1;
        quantidadeElemento.textContent = quantidade;
    });

    document.getElementById('decrementar').addEventListener('click', () => {
        if (quantidade > 0) {
            quantidade -= 1;
            quantidadeElemento.textContent = quantidade;
        }
    });
});