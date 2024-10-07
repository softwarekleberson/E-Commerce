document.getElementById('ativarForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    // Coleta os dados do formulário
    const formData = new FormData(this);
    const data = {
        idLivro: parseInt(formData.get('idLivro'), 10),
        categoria: formData.get('categoria'),
        justificativa: formData.get('justificativa')
    };

    // Envia os dados para o backend
    fetch('http://localhost:8080/livro/ativar', {
        method: 'PUT',
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
        })
        .catch(error => {
            console.error('Erro:', error);
            // Você pode adicionar alguma lógica para mostrar uma mensagem de erro ao usuário aqui
        })
        .finally(() => {
            document.getElementById('ativarForm').reset();
        });
});
