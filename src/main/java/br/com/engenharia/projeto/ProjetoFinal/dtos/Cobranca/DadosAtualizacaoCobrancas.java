package br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCobrancas (
				
		boolean principal,
		
		String tipoResidenciaCobranca,
		
		String tipoLogradouroCobranca,
		
		String logradouroCobranca,
		
		String numeroCobranca,
		
		String bairroCobranca,
		
		String cepCobranca,
		
		String observacaoCobranca,

		String cidadeCobranca,
		
		String estadoCobranca,
		
		String paisCobranca
		
									  ){
}