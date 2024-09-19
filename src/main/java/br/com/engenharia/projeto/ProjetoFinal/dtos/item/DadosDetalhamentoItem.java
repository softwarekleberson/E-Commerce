package br.com.engenharia.projeto.ProjetoFinal.dtos.item;

import java.math.BigDecimal;

import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;

public record DadosDetalhamentoItem(
		
		Long id,
		Long idLivro,
		Long idPedido,
		int quantidade,
		BigDecimal precoUnitario,
		BigDecimal subtotal
		
		) {

	public DadosDetalhamentoItem(Item item) {
		this(item.getId(), item.getLivro().getId(),
			 item.getPedido().getId(),
		     item.getQuantidade(),
			 item.getPrecoUnitario(),
			 item.getSubtotal());
	}
}