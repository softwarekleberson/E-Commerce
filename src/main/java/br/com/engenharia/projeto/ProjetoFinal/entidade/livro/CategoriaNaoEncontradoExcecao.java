package br.com.engenharia.projeto.ProjetoFinal.entidade.livro;

public class CategoriaNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public CategoriaNaoEncontradoExcecao(String message) {
		super(message);
	}
}
