package br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao;

public class DevolucaoNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public DevolucaoNaoEncontradoExcecao(String message) {
		super(message);
	}
}
