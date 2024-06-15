package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoSenha;

@Service
public class ValidarConfirmacaoSenhaUpdateCliente implements IStrategySenhaAtualizadaCliente{

	@Override
	public void processar(DadosAtualizacaoSenha dados) {
		
		if(!dados.senha().matches(dados.confirmarSenha())) {
			throw new IllegalArgumentException("primeira senha não corresponde a confirmar senha");
		}		
	}
}