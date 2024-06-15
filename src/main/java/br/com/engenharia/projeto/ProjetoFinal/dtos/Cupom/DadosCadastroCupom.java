package br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom;

import java.math.BigDecimal;

import br.com.engenharia.projeto.ProjetoFinal.entidade.cupom.TipoCupom;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCupom(
		
		@NotNull
		Long idCliente,
		
		@NotNull
		TipoCupom tipoCupom,
		
		@NotNull
		BigDecimal valor
		
						) {
}
