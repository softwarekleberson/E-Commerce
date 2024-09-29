package br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente;

import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

public class AlgoritmoVerificaSenhaCliente {

	 private static final String MENSAGEM_ERRO = "primeira senha não corresponde a confirmar senha";
		
	 public static void algoritmoVerificaSenhaCliente(String senha, String confirmarSenha) {		
		if(!senha.matches(confirmarSenha)) {
			throw new ValidacaoExcepetion(MENSAGEM_ERRO);
		}		
	}
}