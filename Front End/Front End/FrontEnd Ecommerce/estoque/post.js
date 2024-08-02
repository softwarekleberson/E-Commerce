document.getElementById('estoqueForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    // Coleta os dados do formulário
    const formData = new FormData(this);
    const data = {
        idLivro: parseInt(formData.get('idLivro'), 10),
        quantidade: parseInt(formData.get('quantidade'), 10),
        valorCusto: formData.get('valorCusto'),
        dataEntrada: formData.get('dataEntrada'),
        fornecedor: formData.get('fornecedor'),
        estadoProduto: formData.get('estadoProduto')
    };

    // Envia os dados para o backend
    fetch('http://localhost:8080/estoque', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Assume que a resposta é JSON
        })
        .then(result => {
            console.log('Sucesso:', result);
            // Resetar o formulário após o envio bem-sucedido
            document.getElementById('estoqueForm').reset();
        })
        .catch(error => {
            console.error('Erro:', error);
            // Você pode adicionar alguma lógica para mostrar uma mensagem de erro ao usuário aqui
        });
});
