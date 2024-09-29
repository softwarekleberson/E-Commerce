package br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento;

import java.util.List;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPagamento(
		
		@NotNull
		Long idEntrega,
		
		@NotNull
		Long idCobranca,
		
		@NotNull
		String codigoPedido,
		
		List<DadosCadastroPagamentoCartao> cartoes,
		List<DadosCadastroPagamentoCupom> cupons
		
		) {
}