package br.com.engenharia.projeto.ProjetoFinal.dominio.cliente;

public class ClienteNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ClienteNaoEncontradoExcecao(String message) {
		super(message);
	}
}
