package br.com.engenharia.projeto.ProjetoFinal.entidade.livro;

public class LivroNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public LivroNaoEncontradoExcecao(String message) {
		super(message);
	}
}
