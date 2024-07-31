package br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom;

import java.math.BigDecimal;

import br.com.engenharia.projeto.ProjetoFinal.dominio.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.dominio.cupom.TipoCupom;

public record DadosDetalhamentoCupom(
		
		Long idCupom,
		Long idCliente,
		BigDecimal valor,
		TipoCupom tipoCupom,
		boolean status
		
		) {
	
	public DadosDetalhamentoCupom(Cupom cupom) {
		this(cupom.getId(),cupom.getCliente().getId(),
			 cupom.getValor(),cupom.getTipoCupom(), cupom.isStatus());
	}
}