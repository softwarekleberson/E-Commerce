package br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroPagamentoCartao(
		
		@NotNull
		Long idCartao,
		
		@NotNull
		Long parcelas
		
		) {
}
