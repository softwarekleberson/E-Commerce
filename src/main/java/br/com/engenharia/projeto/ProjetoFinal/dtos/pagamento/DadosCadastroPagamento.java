package br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPagamento(
		
		@NotNull
		Long idCliente,
		
		@NotNull
		Long idEntrega,
		
		@NotNull
		Long idCobranca,
		
		List<DadosCadastroPagamentoCartao> cartoes,
		DadosCadastroPagamentoCupom cupom,
		@Valid @NotNull List<DadosCadastroPagamentoProduto> produtos
		
		) {
}
