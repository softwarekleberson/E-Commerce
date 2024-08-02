document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('livro');

    form.addEventListener('submit', async (event) => {
        event.preventDefault(); // Evita o envio padrão do formulário

        // Coleta os dados do formulário
        const formData = new FormData(form);
        const data = {
            id: formData.get('id'),
            data: formData.get('data') || null,
            preco: formData.get('preco') ? parseFloat(formData.get('preco')) : null,
            ativo: formData.get('ativo') === 'on' || null, // Assumindo que um checkbox é usado para "ativo"
            titulo: formData.get('titulo') || null,
            isbn: formData.get('isbn') || null,
            paginas: formData.get('paginas') ? parseInt(formData.get('paginas'), 10) : null,
            sinopse: formData.get('sinopse') || null,
            codigoBarra: formData.get('codigoBarra') || null,
            dimensoes: {
                altura: formData.get('altura') ? parseFloat(formData.get('altura')) : null,
                largura: formData.get('largura') ? parseFloat(formData.get('largura')) : null,
                peso: formData.get('peso') ? parseFloat(formData.get('peso')) : null,
                profundidade: formData.get('profundidade') ? parseFloat(formData.get('profundidade')) : null
            },
            editora: formData.get('editora') || null,
            precificacao: formData.get('precificacao') ? parseFloat(formData.get('precificacao')) : null,
            edicao: formData.get('edicao') || null
        };

        try {
            // Envia a requisição PUT
            const response = await fetch('http://localhost:8080/livro', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            // Verifica se a requisição foi bem-sucedida
            if (!response.ok) {
                throw new Error('Erro na requisição');
            }

            const result = await response.json();
            console.log('Resposta do servidor:', result);

            // Adicione aqui o que deseja fazer após a resposta bem-sucedida, como exibir uma mensagem
            alert('Livro atualizado com sucesso!');
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao atualizar o livro.');
        }
    });
});
