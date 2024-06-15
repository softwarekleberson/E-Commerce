document.getElementById('myForm').addEventListener('submit', function(event) {
    var isChecked = document.getElementById('principal').checked;
    document.getElementById('principal').value = isChecked ? 'true' : 'false';
});

document.getElementById("myForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const jsonData = {};

    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    sendDataToBackend(jsonData);
    this.reset();
    window.location.href = "cartoes.html"
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

function preencherCamposDoCartao(dadosDoCartao) {
    var tituloCartao = document.querySelector('.card h2');
    var descricaoCartao = document.querySelector('.card p');

    tituloCartao.textContent = dadosDoCartao.titulo; 
    descricaoCartao.textContent = "Terminado em " + dadosDoCartao.numero.slice(-4); 
}

function obterEExibirInformacoesDoCartao() {
    var url = '';

    fetch(url)
    .then(response => response.json())
    .then(data => {
        preencherCamposDoCartao(data);
    })
    .catch(error => {
        console.error('Erro ao obter informações do cartão:', error);
    });
}

obterEExibirInformacoesDoCartao();
