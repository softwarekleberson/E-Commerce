package br.com.engenharia.projeto.ProjetoFinal.dominio.livro.categoria;

public class CategoriaNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public CategoriaNaoEncontradoExcecao(String message) {
		super(message);
	}
}
