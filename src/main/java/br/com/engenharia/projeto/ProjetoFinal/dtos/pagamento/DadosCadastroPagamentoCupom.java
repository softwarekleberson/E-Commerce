package br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroPagamentoCupom(
		
		@NotNull
		String idCupom
		
		) {
}
