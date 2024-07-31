document.addEventListener('DOMContentLoaded', function () {
    // 1. Obter o ID do cliente da URL
    const urlParams = new URLSearchParams(window.location.search);
    const clienteId = urlParams.get('clienteId');

    // 2. Construir a URL do endpoint
    const endpoint = `http://localhost:8080/administrador/cupons/${clienteId}`;

    // 3. Fazer uma solicitação GET para o endpoint usando Fetch
    fetch(endpoint)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao carregar os dados do cupom.');
            }
            return response.json();
        })
        .then(data => {
            // 4. Verificar se há conteúdo e manipular adequadamente
            if (data.content && Array.isArray(data.content)) {
                // Se houver conteúdo e for um array, iterar sobre eles
                const cupomContainer = document.querySelector('.cupom-container');
                data.content.forEach(cupom => {
                    const cupomElement = document.createElement('div');
                    cupomElement.classList.add('cupom');
                    const status = cupom.status ? 'Válido' : 'Inválido';
                    cupomElement.innerHTML = `
                        <h3>${cupom.tipoCupom}</h3>
                        <p>Valor R$ ${cupom.valor}</p>
                        <p>Código: ${cupom.idCupom}</p>
                        <p>Status: ${status}</p>
                    `;
                    cupomContainer.appendChild(cupomElement);
                });
            } else {
                // Se não houver conteúdo ou não for um array, faça algo diferente
                console.error('Os dados retornados não são válidos.');
                // Você pode adicionar lógica aqui para lidar com outros casos
            }
        })
        .catch(error => {
            console.error('Erro:', error);
        });
});
