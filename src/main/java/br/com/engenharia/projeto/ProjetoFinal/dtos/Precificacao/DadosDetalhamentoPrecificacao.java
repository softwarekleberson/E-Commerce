package br.com.engenharia.projeto.ProjetoFinal.dtos.Precificacao;

import java.math.BigDecimal;

import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.precificacao.Precificacao;

public record DadosDetalhamentoPrecificacao(
		
		Long id,
		BigDecimal precificacao
		
										){
	
	public DadosDetalhamentoPrecificacao(Precificacao precificacao) {
		this(precificacao.getId(), precificacao.getPrecificacao());
	}
}
