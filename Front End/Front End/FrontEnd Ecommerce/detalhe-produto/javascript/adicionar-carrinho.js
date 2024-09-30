document.addEventListener('DOMContentLoaded', () => {
    // Obtendo os parâmetros da URL
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    const clienteId = urlParams.get('clienteId');

    // Verificar se id e clienteId estão definidos
    if (!id || !clienteId) {
        console.error('ID ou clienteId não estão definidos na URL.');
        alert('Erro: ID ou clienteId não estão presentes na URL.');
        return;
    }

    // Adicionar evento de clique ao botão "Adicionar ao Carrinho"
    document.getElementById('adicionar-carrinho').addEventListener('click', () => {
        const quantidade = document.getElementById('quantidade').innerText; // Captura a quantidade

        // Montando a URL de destino
        const url = `http://localhost:8080/pedidos/${id}/${clienteId}`;

        // Dados a serem enviados no POST
        const data = {
            quantidade: quantidade
        };

        // Fazendo a requisição POST
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na requisição: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                console.log('Sucesso:', data);
            })
            .catch((error) => {
                console.error('Erro:', error);
                alert('Erro ao adicionar o livro ao carrinho. Tente novamente.');
            });
    });
});
