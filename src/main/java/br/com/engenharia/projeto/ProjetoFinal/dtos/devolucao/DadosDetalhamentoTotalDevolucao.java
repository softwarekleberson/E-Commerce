package br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.dominio.devolucao.AnalisePedidoDevolucaoAceitoOuRecusa;
import br.com.engenharia.projeto.ProjetoFinal.dominio.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.dominio.devolucao.EsperandoDevolucaoOuRecebido;
import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.imagem.Imagens;

public record DadosDetalhamentoTotalDevolucao(
		
		Long idDevolucao,
		String codigoDevolucao,
		String urlProduto,
		String codigoPedido,
		LocalDate dataPedidoTroca,
		LocalDate dataConclusaoTroca,
		String emailAdm,
		EsperandoDevolucaoOuRecebido analisePedidoDevolucao,
		AnalisePedidoDevolucaoAceitoOuRecusa analiseDevolucao
		
		) {
	
	public DadosDetalhamentoTotalDevolucao(Devolucao dados) {
		this(dados.getId(), dados.getCodigoDevolucao(),
				
			 dados.getPedido().getLivro().getImagens()
			 .stream().findFirst().map(Imagens::getUrl).orElse(null),
			 
			 dados.getCodigoPedido(), dados.getDataPedidoTroca(),
			 dados.getDataConclusaoTroca(), dados.getAdministrador().getEmail().getEmail(),
			 dados.getEsperandoDevolucaoOuRecebido(),
			 dados.getAnalisePedido());
	}
}