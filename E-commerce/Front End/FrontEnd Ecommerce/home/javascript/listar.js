// Fazendo a requisição GET
fetch('http://localhost:8080/livro')
.then(response => response.json())
.then(data => {
    // Iterando sobre os dados recebidos e preenchendo os elementos HTML
    const produtosDiv = document.getElementById('produtos');
    data.content.forEach(livro => {
        const div = document.createElement('div');
        div.innerHTML = `
            <a href="detalhe-produto.html?id=${livro.id}">
                <img src="${livro.primeiraImagem}" alt="${livro.titulo}">
            </a>
            <h3>${livro.titulo}</h3>
            <p>${livro.autor}</p>
            <p>R$ ${livro.preco.toFixed(2)}</p>
        `;
        produtosDiv.appendChild(div);
    });
})
.catch(error => console.error('Ocorreu um erro:', error));