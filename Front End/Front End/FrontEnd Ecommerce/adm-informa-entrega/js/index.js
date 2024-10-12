document.getElementById("entregaForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    // Captura o valor do campo "codigo"
    const codigo = document.getElementById("codigo").value;

    // URL do endpoint para a requisição PUT
    const url = 'http://localhost:8080/administrador/status/entrega/entregue';

    // Cria os dados que serão enviados na requisição
    const data = { 
        codigo: codigo
    };

    // Envia a requisição PUT usando fetch
    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data) // Converte os dados para JSON
    })
    .then(response => {
        // Verifica se há conteúdo na resposta
        if (response.ok) {
            return response.text().then(text => {
                // Se houver texto na resposta, tenta tratá-lo como JSON, caso contrário, retorna o texto vazio
                return text ? JSON.parse(text) : {};
            });
        } else {
            throw new Error('Erro na requisição');
        }
    })
    .then(result => {
        // Lógica a ser aplicada após o sucesso da requisição
        alert('Status de entrega atualizado com sucesso!');
    })
    .catch(error => {
        console.error('Erro ao atualizar o status:', error);
        alert('Erro ao atualizar o status de entrega.');
    });
});
