package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovoCliente;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

@Service
public class ValidaConfirmacaoSenhaCliente implements IStrategyCliente{

    private static final String MENSAGEM_ERRO = "primeira senha não corresponde a confirmar senha";
	
	@Override
	public void processar(Cliente dominio) {
		
		if(!dominio.getSenha().matches(dominio.getConfirmar_Senha())) {
			throw new ValidacaoExcepetion(MENSAGEM_ERRO);
		}		
	}
}