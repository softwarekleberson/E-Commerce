package br.com.engenharia.projeto.ProjetoFinal.dao.cartao;

public class CartaoNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public CartaoNaoEncontradoExcecao(String message) {
		super(message);
	}
}
