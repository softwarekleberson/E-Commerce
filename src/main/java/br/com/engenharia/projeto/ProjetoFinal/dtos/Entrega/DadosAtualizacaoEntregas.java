package br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoEntregas (
		
		boolean principal,
		
		String tipoResidenciaEntrega,
		
		String tipoLogradouroEntrega,
		
		String logradouroEntrega,
		
		String numeroEntrega,
		
		String bairroEntrega,
				
		String cepEntrega,
		
		String observacaoEntrega,

		String cidadeEntrega,
		
		String estadoEntrega,
		
		String paisEntrega,
		
		String fraseEntregaEntrega
									  ){
}
