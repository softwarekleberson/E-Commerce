package br.com.engenharia.projeto.ProjetoFinal.entidade.livro;

public class ImagemNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ImagemNaoEncontradoExcecao(String message) {
		super(message);
	}
}
