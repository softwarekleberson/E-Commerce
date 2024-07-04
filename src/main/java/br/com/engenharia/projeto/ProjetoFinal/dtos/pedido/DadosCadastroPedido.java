package br.com.engenharia.projeto.ProjetoFinal.dtos.pedido;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPedido(
		
		@NotNull
		Long idLivro,
		
		@NotNull
		int quantidade		
		) {
}
