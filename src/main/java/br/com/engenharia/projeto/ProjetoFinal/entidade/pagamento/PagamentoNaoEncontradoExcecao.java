package br.com.engenharia.projeto.ProjetoFinal.entidade.pagamento;

public class PagamentoNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public PagamentoNaoEncontradoExcecao(String message) {
		super(message);
	}
}
