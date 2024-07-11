package br.com.engenharia.projeto.ProjetoFinal.dtos.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.autor.Autor;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.categoria.Categoria;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.imagem.Imagens;

public record DadosDetalhamentoLivroCompleto(
		
		Long id,
	    LocalDate ano,
	    String titulo,
	    BigDecimal preco,
	    String isbn,
	    int paginas,
	    String sinopse,
	    String codigoBarra,
	    String editora,
	    String edicao,
	    BigDecimal precificacao,
	    double largura,
	    double altura,
	    double profundidade,
	    double peso,
	    String imagemPrincipal,
	    String imagemSecundaria,
	    String imagemTerceara,
	    String categorias,
	    String autores
		
		) {

	public DadosDetalhamentoLivroCompleto(Livro livro) {
	    this(livro.getId(), livro.getData(), livro.getTitulo(), livro.getPreco(),
	    		livro.getIsbn(),
                livro.getPaginas(), livro.getSinopse(), livro.getCodigoBarra(),
                livro.getEditora().getEditora(), livro.getEdicao().getEdicao(),
                livro.getPrecificacao().getPrecificacao(), livro.getDimensoes().getLargura(),
                livro.getDimensoes().getAltura(), livro.getDimensoes().getProfundidade(),
                livro.getDimensoes().getPeso(),
                livro.getImagens().stream().findFirst().map(Imagens::getUrl).orElse(null),
                livro.getImagens().stream().skip(1).findFirst().map(Imagens::getUrl).orElse(null),
                livro.getImagens().stream().skip(2).findFirst().map(Imagens::getUrl).orElse(null),
                livro.getCategorias().stream().findFirst().map(Categoria::getCategoria).orElse(null),
                livro.getAutores().stream().findFirst().map(Autor::getAutor).orElse(null));
	}
}