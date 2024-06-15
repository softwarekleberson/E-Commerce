package br.com.engenharia.projeto.ProjetoFinal.dtos.cartao;

import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Bandeira;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCartao(
		
		@NotNull
		Long idCliente,
		
		boolean principal,
		
		@NotNull
		String numeroCartao,
		
		@NotNull
		String nomeImpresso,
		
		@NotNull
		String codigo,
		
		@NotNull
		Bandeira bandeira
		
									)

									{
}
