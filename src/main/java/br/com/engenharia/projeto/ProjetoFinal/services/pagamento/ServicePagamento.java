package br.com.engenharia.projeto.ProjetoFinal.services.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pagamento.DadosCadastroPagamento;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.Pagamento;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.StatusCompra;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.StatusPedido;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CartaoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CobrancaRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.EntregaRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom.CupomRepositroy;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pagamento.PagamentoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos.PedidoRepository;
import jakarta.transaction.Transactional;
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

	@Transactional
	public void validarDadosDoPagamento(@Valid DadosCadastroPagamento dados, Long clienteId) {
		var entrega = verificarExistenciaEntrega(dados.idEntrega());
		var cobranca = verificarExistenciaCobranca(dados.idCobranca());
		BigDecimal valorTotalPedido = calcularValorTotalDosPedidos(clienteId);
		List<Pedido> pedidos = listaPedidos(clienteId);
		List<Cartao> cartoes = verificaInformacoesSobreCartao(dados.idCartao1(), dados.idCartao2());
		List<Cupom> cupons = verificaInformacoesSobreCupom(dados.cupom1(), dados.cupom2());

		Pagamento pagamento = new Pagamento(LocalDateTime.now(),
											entrega,
											cobranca,
											valorTotalPedido,
											pedidos,
											cartoes,
											cupons,
											StatusCompra.APROVADO);
		
		pagamentoRepository.save(pagamento);
		pagamentoRepository.flush();
		
		associarPagamentoAPedidos(pagamento, pedidos);
	}

	private void associarPagamentoAPedidos(Pagamento pagamento, List<Pedido> pedidos) {
		for (Pedido pedido : pedidos) {
			pedido.setPagamento(pagamento);
			pedido.setPago(true);
			pedido.setStatusEntrega(StatusPedido.PAGO);
			pedidoRepository.save(pedido);
		}
	    pedidoRepository.flush();
	}

	private List<Cupom> verificaInformacoesSobreCupom(String idCupom1, String idCupom2) {

		List<Cupom> cupons = new ArrayList<>();

		if (idCupom1 != null) {
			var cupom1 = cupomRepository.buscarCupomPorId(idCupom1);
			if (cupom1.isPresent()) {
				cupons.add(cupom1.get());
			} else {
				throw new ValidacaoExcepetion("Cupom 1 não encontrado.");
			}
		}

		if (idCupom2 != null) {
			var cupom2 = cupomRepository.buscarCupomPorId(idCupom2);
			if (cupom2.isPresent()) {
				cupons.add(cupom2.get());
			} else {
				throw new ValidacaoExcepetion("Cupom 2 não encontrado.");
			}
		}
		return cupons;
	}

	private List<Cartao> verificaInformacoesSobreCartao(Long idCartao1, Long idCartao2) {

		List<Cartao> cartoes = new ArrayList<>();

		if (idCartao1 != null) {
			var cartao1 = cartaoRepository.findById(idCartao1);
			if (cartao1.isPresent()) {
				cartoes.add(cartao1.get());
			} else {
				throw new ValidacaoExcepetion("Cartão 1 não encontrado.");
			}
		}

		if (idCartao2 != null) {
			var cartao2 = cartaoRepository.findById(idCartao2);
			if (cartao2.isPresent()) {
				cartoes.add(cartao2.get());
			} else {
				throw new ValidacaoExcepetion("Cartão 2 não encontrado.");
			}
		}
		return cartoes;
	}

	private List<Pedido> listaPedidos(Long clienteId) {
		List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);

		if (pedidos.isEmpty()) {
			throw new ValidacaoExcepetion("Não a pedidos para este cliente");
		}
		return pedidos;
	}

	private Entrega verificarExistenciaEntrega(Long id) {
		return entregaRepository.findById(id)
				.orElseThrow(() -> new ValidacaoExcepetion("Id da entrega não encontrado"));
	}

	private Cobranca verificarExistenciaCobranca(Long id) {
		return cobrancaRepository.findById(id)
				.orElseThrow(() -> new ValidacaoExcepetion("Id da cobrança não existe"));
	}

	private BigDecimal calcularValorTotalDosPedidos(Long clienteId) {
		return pedidoRepository.findByClienteId(clienteId).stream()
				.map(Pedido::atualizarValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}