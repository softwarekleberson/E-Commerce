package br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao;

import java.math.BigDecimal;

import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.AnalisePedidoDevolucaoAceitoOuRecusa;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.devolucao.EsperandoDevolucaoOuRecebido;

public record DadosDetalhamentoDevolucao(
		
		Long id,
		String codigoDevolucao,
		String codigoPedido,
		int quantidade,
		BigDecimal precoTotal,
		EsperandoDevolucaoOuRecebido devolucao,
		AnalisePedidoDevolucaoAceitoOuRecusa analiseDevolucao
		
		) {

	public DadosDetalhamentoDevolucao(Devolucao devolucao) {
		this(devolucao.getId(),devolucao.getCodigoDevolucao(),
			 devolucao.getPedido().getCodigoPedido(),
			 devolucao.getPedido().getQuantidade(),
			 devolucao.getPedido().getValorTotal(),
			 devolucao.getEsperandoDevolucaoOuRecebido(),
			 devolucao.getAnalisePedido());
	}
}
