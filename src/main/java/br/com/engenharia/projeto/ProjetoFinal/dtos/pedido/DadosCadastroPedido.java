package br.com.engenharia.projeto.ProjetoFinal.dtos.pedido;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPedido(
		
		@NonNull
		Long idCarrinho,
		
		@NotNull
		Long idLivro,
		
		@NotNull
		int quantidade		
		) {
}
