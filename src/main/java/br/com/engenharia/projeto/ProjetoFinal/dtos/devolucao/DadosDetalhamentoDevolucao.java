package br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao;

import java.math.BigDecimal;

import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.AnalisePedidoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.Devolucao;

public record DadosDetalhamentoDevolucao(
		
		Long id,
		String codigoDevolucao,
		int quantidade,
		BigDecimal precoTotal,
		AnalisePedidoDevolucao devolucao
		
		) {

	public DadosDetalhamentoDevolucao(Devolucao devolucao) {
		this(devolucao.getId(),devolucao.getCodigoDevolucao(),
			 devolucao.getPedido().getQuantidade(),
			 devolucao.getPedido().getValorTotal(),
			 devolucao.getAnalisePedidoDevolucao());
	}
}
