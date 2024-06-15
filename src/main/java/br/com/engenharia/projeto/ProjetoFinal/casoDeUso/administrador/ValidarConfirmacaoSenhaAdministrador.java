package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.Administrador;

@Service
public class ValidarConfirmacaoSenhaAdministrador implements IStrategyAdministrador{

	@Override
	public void processar(Administrador dominio) {
		if(!dominio.getSenha().matches(dominio.getConfirmar_Senha())) {
			throw new IllegalArgumentException("primeira senha não corresponde a confirmar senha");
		}
	}
}
