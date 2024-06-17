package br.com.engenharia.projeto.ProjetoFinal.entidade.pedido;

public class PedidoNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public PedidoNaoEncontradoExcecao(String message) {
		super(message);
	}
}
