package br.com.engenharia.projeto.ProjetoFinal.dominio.log;

public class LogNaoEncontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public LogNaoEncontradoExcecao(String message) {
		super(message);
	}
}
