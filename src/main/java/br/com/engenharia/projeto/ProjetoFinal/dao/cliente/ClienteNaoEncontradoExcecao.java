package br.com.engenharia.projeto.ProjetoFinal.dao.cliente;

public class ClienteNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ClienteNaoEncontradoExcecao(String message) {
		super(message);
	}
}
