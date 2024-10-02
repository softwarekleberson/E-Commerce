package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento.DadosCadastroPagamento;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento.DadosCadastroPagamentoCartao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento.DadosCadastroPagamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.CartaoNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.Pagamento;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.StatusCompra;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CartaoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CobrancaRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.EntregaRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom.CupomRepositroy;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pagamento.PagamentoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos.PedidoRepository;
import jakarta.validation.Valid;

@Service
public class ServicePagamento {

	 private final EntregaRepository entregaRepository;
	 private final CobrancaRepository cobrancaRepository;
	 private final CartaoRepository cartaoRepository;
	 private final PedidoRepository pedidoRepository;
	 private final CupomRepositroy cupomRepository;
	 private final PagamentoRepository pagamentoRepository;

	 @Autowired
	 public ServicePagamento(
	        EntregaRepository entregaRepository,
	        CobrancaRepository cobrancaRepository,
	        CartaoRepository cartaoRepository,
	        PedidoRepository pedidoRepository,
	        CupomRepositroy cupomRepository,
	        PagamentoRepository pagamentoRepository) {
	        this.entregaRepository = entregaRepository;
	        this.cobrancaRepository = cobrancaRepository;
	        this.cartaoRepository = cartaoRepository;
	        this.pedidoRepository = pedidoRepository;
	        this.cupomRepository = cupomRepository;
	        this.pagamentoRepository = pagamentoRepository;
	 }

	 public void validarDadosDoPagamento(@Valid DadosCadastroPagamento dados, Long clienteId) {
		var entrega = verificarEntrega(dados.idEntrega());
	 	var cobranca = verificarCobranca(dados.idCobranca());
	    List<Cartao> cartoes = verificarCartoes(dados.cartoes());
	    List<Cupom> cupons = verificarCupons(dados.cupons());
	    BigDecimal valorTotalPedido = calcularValorTotalDosPedidos(clienteId);
	    List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);

	    criarPagamento(entrega, cobranca, cartoes, cupons, valorTotalPedido, pedidos);
	    excluirCuponsAposUso(dados.cupons());
	 }

	 private void excluirCuponsAposUso(List<DadosCadastroPagamentoCupom> cupons) {
		
	}

	private void criarPagamento(Entrega entrega, Cobranca cobranca, List<Cartao> cartoes, List<Cupom> cupons,
	                             BigDecimal valorTotalPedido, List<Pedido> pedidos) {
	   		 
		 try {
		   
	        Pagamento pagamento = new Pagamento(
	        UUID.randomUUID().toString(),
	        LocalDateTime.now(),
	        entrega,
	        cobranca,
	        valorTotalPedido,
	        pedidos,
	        cartoes,
	        cupons,
	        StatusCompra.EM_ANALISE
	        
	    );

	      pagamentoRepository.save(pagamento);
	      
	 } catch (Exception e) {
	      throw new ValidacaoExcepetion("Pagamento não pode ser criado");
	      }
	}

	 private List<Cartao> verificarCartoes(List<DadosCadastroPagamentoCartao> dadosCartoes) {
	   return dadosCartoes.stream()
	     .map(dado -> cartaoRepository.findById(dado.idCartao())
	     .orElseThrow(() -> new CartaoNaoEncontradoExcecao("Cartão inválido")))
	     .toList();
	 }

	 private List<Cupom> verificarCupons(List<DadosCadastroPagamentoCupom> dadosCupons) {
	    return dadosCupons.stream()
	       .map(dado -> cupomRepository.buscarCupomPorId(dado.idCupom())
	       .orElseThrow(() -> new ValidacaoExcepetion("Cupom não encontrado para o ID fornecido")))
	       .toList();
	 }

	 private Entrega verificarEntrega(Long id) {
	    return entregaRepository.findById(id)
	    .orElseThrow(() -> new ValidacaoExcepetion("Id da entrega não encontrado"));
	 }

	 private Cobranca verificarCobranca(Long id) {
	    return cobrancaRepository.findById(id)
	     .orElseThrow(() -> new ValidacaoExcepetion("Id da cobrança não existe"));
	 }

	private BigDecimal calcularValorTotalDosPedidos(Long clienteId) {
	    return pedidoRepository.findByClienteId(clienteId).stream()
	    .map(Pedido::atualizarValorTotal)
	    .reduce(BigDecimal.ZERO, BigDecimal::add);
	 }
}
