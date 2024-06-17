package br.com.engenharia.projeto.ProjetoFinal.entidade.carrinho;

public class CarrinhoNaoEncontradoExcecao extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	public CarrinhoNaoEncontradoExcecao(String message) {
		super(message);
	}
}
