document.getElementById('myForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Previne o envio do formulário

    // Obtém o checkbox
    var isChecked = document.getElementById('principal').checked;

    // Cria um objeto FormData
    const formData = new FormData(this);
    const jsonData = {};

    // Preenche o jsonData com os valores do FormData
    formData.forEach((value, key) => {
        // Se o checkbox estiver marcado, define o valor como true, senão, false
        if (key === 'principal') {
            jsonData[key] = isChecked ? true : false; // Usar booleano verdadeiro/falso
        } else {
            jsonData[key] = value;
        }
    });

    sendDataToBackend(jsonData);
    this.reset(); // Reseta o formulário
});

function sendDataToBackend(data) {
    fetch('http://localhost:8080/cartoes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao enviar dados para o backend');
        }
        return response.json();
    })
    .then(data => {
        console.log('Dados enviados com sucesso:', data);
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}
