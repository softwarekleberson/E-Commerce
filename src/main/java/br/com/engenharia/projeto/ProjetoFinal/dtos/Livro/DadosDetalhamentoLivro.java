package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import java.math.BigDecimal;

import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.autor.Autor;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.categoria.Categoria;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.imagem.Imagens;

public record DadosDetalhamentoLivro(
    
	Long id,
    String titulo,
    String primeiraImagem,
    String autor,
    String categoria,
    BigDecimal preco
    
) {
    public DadosDetalhamentoLivro(Livro livro) {
        this(livro.getId(),
             livro.getTitulo(),
             livro.getImagens().stream().findFirst().map(Imagens::getUrl).orElse(null),
             livro.getAutores().stream().findFirst().map(Autor::getAutor).orElse(null),
             livro.getCategorias().stream().findFirst().map(Categoria::getCategoria).orElse(null),
             livro.getPreco());
    }
}
