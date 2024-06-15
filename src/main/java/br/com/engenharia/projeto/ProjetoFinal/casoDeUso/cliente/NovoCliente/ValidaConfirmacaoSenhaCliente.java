package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovoCliente;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;

@Service
public class ValidaConfirmacaoSenhaCliente implements IStrategyCliente{

	@Override
	public void processar(Cliente dominio) {
		
		if(!dominio.getSenha().matches(dominio.getConfirmar_Senha())) {
			throw new IllegalArgumentException("primeira senha não corresponde a confirmar senha");
		}		
	}
}