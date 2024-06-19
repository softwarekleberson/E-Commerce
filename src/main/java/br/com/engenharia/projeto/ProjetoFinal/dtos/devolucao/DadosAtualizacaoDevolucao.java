package br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao;

import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.ProdutoVoltaParaEstoque;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.EsperandoDevolucaoOuRecebido;
import br.com.engenharia.projeto.ProjetoFinal.entidade.estoque.EstadoDoProdutoAoEntrarNoEstoque;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDevolucao(
		
		@NotNull
		String codigoPedido,
		
		@NotNull
		EsperandoDevolucaoOuRecebido esperandoDevolucaoOuRecebido,
		
		@NotNull
		ProdutoVoltaParaEstoque produtoVoltaParaEstoque,
		
		@NotNull
		EstadoDoProdutoAoEntrarNoEstoque estadoProduto
		
		) {
}
