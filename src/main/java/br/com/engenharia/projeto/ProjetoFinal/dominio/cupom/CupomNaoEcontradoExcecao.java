package br.com.engenharia.projeto.ProjetoFinal.dominio.cupom;

public class CupomNaoEcontradoExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public CupomNaoEcontradoExcecao(String message) {
		super(message);
	}
}
