package br.com.engenharia.projeto.ProjetoFinal.services.administradores;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.cupom.CupomDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.devolucao.DevolucaoDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.livro.EstoqueDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosDetalhamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosAtualizacaoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoTotalDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.ProdutoVoltaParaEstoque;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cupom.TipoCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.AnalisePedidoDevolucaoAceitoOuRecusa;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.estoque.Estoque;
import jakarta.validation.Valid;

@Service
public class ServiceAceitarDevolucao {
	
	@Autowired 
	private DevolucaoDao devolucaoDao;
	
	@Autowired
	private CupomDao cupomDao;
	
	@Autowired
	private EstoqueDao estoqueDao;
	
	public DadosDetalhamentoTotalDevolucao devolucaoAceita (@Valid DadosAtualizacaoDevolucao dados, String codigoDevolucao) {
		var aceitaDevolucao = devolucaoDao.carregarDevolucao(codigoDevolucao);
		
		aceitaDevolucao.setDataConclusaoTroca(LocalDate.now());
		aceitaDevolucao.devoluvaoChegou(dados.esperandoDevolucaoOuRecebido());
		aceitaDevolucao.analisePedidoDevolucao(AnalisePedidoDevolucaoAceitoOuRecusa.TROCA_ACEITA);
		
		geraCupomAposAprovarDevolucao(aceitaDevolucao);
		devolucaoVoltaParaEstoque(dados, aceitaDevolucao);
		devolucaoDao.salvar(aceitaDevolucao);
		
		return new DadosDetalhamentoTotalDevolucao(aceitaDevolucao);
	}

	private void devolucaoVoltaParaEstoque(DadosAtualizacaoDevolucao dados, Devolucao devolucao) {
		if(dados.produtoVoltaParaEstoque() == ProdutoVoltaParaEstoque.SIM) {
			var estoque = new Estoque();
			
			estoque.setId(null);
			estoque.setLivro(devolucao.getPedido().getLivro().getId());
			estoque.setQuantidade(devolucao.getPedido().getQuantidade());
			estoque.setValorCusto(BigDecimal.ZERO);
			estoque.setDataEntrada(LocalDate.now());
			estoque.setFornecedor("Devolução feita pelo cliente");
			estoque.setEstadoDoProduto(dados.estadoProduto());
			
			estoqueDao.salvar(estoque);
		}
	}

	private DadosDetalhamentoCupom geraCupomAposAprovarDevolucao(Devolucao aceitaDevolucao) {
		Long clienteId = aceitaDevolucao.getPedido().getCarrinho().getCliente().getId();
		Cupom gerarCupom = new Cupom();
		
		gerarCupom.setId(null);
		gerarCupom.setTipoCupom(TipoCupom.TROCA);
		gerarCupom.setValor(aceitaDevolucao.getPedido().getValorTotal());
		gerarCupom.setStatus(true);
		gerarCupom.setCliente(clienteId);
		
		cupomDao.salvar(gerarCupom);
		return new DadosDetalhamentoCupom(gerarCupom);
	}
}