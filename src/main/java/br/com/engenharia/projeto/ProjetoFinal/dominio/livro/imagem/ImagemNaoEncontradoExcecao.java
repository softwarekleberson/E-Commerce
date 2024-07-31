package br.com.engenharia.projeto.ProjetoFinal.dominio.livro.imagem;

public class ImagemNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ImagemNaoEncontradoExcecao(String message) {
		super(message);
	}
}
