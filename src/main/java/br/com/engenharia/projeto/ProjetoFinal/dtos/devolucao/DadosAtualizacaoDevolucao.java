package br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao;

import br.com.engenharia.projeto.ProjetoFinal.dominio.administrador.ProdutoVoltaParaEstoque;
import br.com.engenharia.projeto.ProjetoFinal.dominio.devolucao.EsperandoDevolucaoOuRecebido;
import br.com.engenharia.projeto.ProjetoFinal.dominio.estoque.EstadoDoProdutoAoEntrarNoEstoque;
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
