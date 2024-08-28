package br.com.engenharia.projeto.ProjetoFinal.entidades.administrador;

import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

public class AlgoritmoVerificaSenha {

private static final String SENHA_NAO_CONFERE = "A senha não corresponde à confirmação da senha.";
	
	public static void algoritmoVerificaSenha(String senha, String confirmarSenha) {
		if(!senha.equals(confirmarSenha)) {
			throw new ValidacaoExcepetion(SENHA_NAO_CONFERE);
		}
	}
}