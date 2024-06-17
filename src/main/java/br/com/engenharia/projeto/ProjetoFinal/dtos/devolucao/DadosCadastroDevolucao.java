package br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao;

import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.TrocaDevolucao;

public record DadosCadastroDevolucao(
		
		String codigoPedido,
		Long idCliente,
		TrocaDevolucao trocaDevolucao
		
		) {
}