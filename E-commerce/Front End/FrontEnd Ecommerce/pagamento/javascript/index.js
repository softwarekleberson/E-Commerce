function adicionarCartao(){
    document.getElementById("popup-adicionar-cartao").style.display = "flex";
}

function adicionarEndereco() {
    document.getElementById("popup-adicionar-endereco").style.display = "flex";
}

function fecharFormulario(idPopup) {
    var popup = document.getElementById(idPopup);
    popup.style.display = "none";
}

document.getElementById('myFormEndereco').addEventListener('submit', function(event) {
    var isChecked = document.getElementById('principal').checked;
    document.getElementById('principal').value = isChecked ? 'true' : 'false';
});

document.getElementById("myFormCartao").addEventListener("submit", function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const jsonData = {};

    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    sendDataToBackendCartao(jsonData);
    this.reset();
});

function sendDataToBackendCartao(data) {
    data.principal = data.principal ? 'true' : 'false';
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
        window.alert("Novo cartão adicionado");
        window.location = 'pagamento.html'
    })
    .catch(error => {
        console.error('Erro:', error);
        window.alert("Erro ao salvar cartão")
    });
}


/***
 * Insert Entrega requisição Post
 */

document.getElementById('myFormEndereco').addEventListener('submit', function(event) {
    event.preventDefault();

    var isChecked = document.getElementById('principal').checked;
    var principalValue = isChecked ? true : false;
    document.getElementById('principal').value = principalValue;

    const formData = new FormData(this);
    const jsonData = {};

    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    sendDataToBackend(jsonData);
    this.reset();
});

function sendDataToBackend(data) {
    data.principal = data.principal ? 'true' : 'false';

    fetch('http://localhost:8080/endereco/entrega', {
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


/**Endereço Entrega principal */
document.addEventListener('DOMContentLoaded', function () {
    const userList = document.getElementById('entrega');

    function getClienteIdFromURL() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('idCliente');
    }

    function carregarUsuarios(idCliente) {
        fetch(`http://localhost:8080/endereco/entrega/${idCliente}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao carregar usuários: ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                if (data.hasOwnProperty('content') && Array.isArray(data.content)) {
                    userList.innerHTML = '';

                    const enderecoPrincipal = data.content.find(endereco => endereco.principal);

                    if (enderecoPrincipal) {
                        const div = document.createElement('section');
                        div.classList.add('card');
                        div.innerHTML = `
                            <h4>1 Endereço principal</h4>
                            <p id="logradouro"> ${enderecoPrincipal.logradouro}</p>
                            <p id="tipoResidencia"> ${enderecoPrincipal.tipoResidencia} - ${enderecoPrincipal.numero} ${enderecoPrincipal.observacao}</p>
                            <p id="estado"> ${enderecoPrincipal.cidade}, ${enderecoPrincipal.estado} ${enderecoPrincipal.cep}
                            <p id="pais"> ${enderecoPrincipal.pais}</p>
                            <a class="link-entrega adicionar endereco" href="#" onclick="adicionarEndereco()">Adicionar</a> 
                            
                        `;
                        userList.appendChild(div);
                    } else {
                        console.error('Endereço principal não encontrado.');
                    }

                } else {
                    console.error('Os dados retornados não estão no formato esperado.');
                }
            })
            .catch(error => {
                console.error(error);
            });
    }

    const idCliente = getClienteIdFromURL();
    if (idCliente) {
        carregarUsuarios(idCliente);
    } else {
        console.error('ID do cliente não encontrado na URL.');
    }
});


/**
 * Cartão principal
 */

document.addEventListener('DOMContentLoaded', function () {
    const userList = document.getElementById('cartao');

    function getClienteIdFromURL() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('idCliente');
    }

    function carregarUsuarios(idCliente) {
        fetch(`http://localhost:8080/cartoes/${idCliente}`)
            .then(response => response.json())
            .then(data => {
                
                if (data.hasOwnProperty('content') && Array.isArray(data.content)) {
                 
                    userList.innerHTML = '';

                    const cartaoPrincipal = data.content.find(cartao => cartao.principal);

                    if (cartaoPrincipal) {
                        const div = document.createElement('section');
                        div.classList.add('card');
                        div.innerHTML = `
                            <h4>2 Cartão principal</h4>
                            <p id="nomeImpresso">${cartaoPrincipal.nomeImpresso}</hp>
                            <p id="codigo ">Codigo : ${cartaoPrincipal.codigo}</p>
                            <a class="link-cartao adicionar pagamento" href="#" onclick="adicionarCartao()">Adicionar</a>
                        `;
                        userList.appendChild(div);
                    } else {
                        console.error('Cartão principal não encontrado.');
                    }
                } else {
                    console.error('Os dados retornados não estão no formato esperado.');
                }
            })
            .catch(error => {
                console.error('Erro ao carregar usuários:', error);
            });
    }

    const idCliente = getClienteIdFromURL();
    if (idCliente) {
        carregarUsuarios(idCliente);
    } else {
        console.error('ID do cliente não encontrado na URL.');
    }
});