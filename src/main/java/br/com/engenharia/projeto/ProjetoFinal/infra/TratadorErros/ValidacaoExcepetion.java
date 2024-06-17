package br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros;

public class ValidacaoExcepetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoExcepetion(String mensagem) {
		super(mensagem);
	}
}
