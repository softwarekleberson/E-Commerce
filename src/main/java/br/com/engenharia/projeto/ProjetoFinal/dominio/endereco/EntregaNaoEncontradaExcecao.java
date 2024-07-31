package br.com.engenharia.projeto.ProjetoFinal.dominio.endereco;

public class EntregaNaoEncontradaExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public EntregaNaoEncontradaExcecao(String message) {
		super(message);
	}
}
