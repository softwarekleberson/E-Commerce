package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

@Service
public class ValidarConfirmacaoSenhaAdministrador implements IStrategyAdministrador{

    private static final String SENHA_NAO_CONFERE = "A senha não corresponde à confirmação da senha.";
	
	@Override
	public void processar(Administrador dominio) {
		if(!dominio.getSenha().equals(dominio.getConfirmarSenha())) {
			throw new ValidacaoExcepetion(SENHA_NAO_CONFERE);
		}
	}
}