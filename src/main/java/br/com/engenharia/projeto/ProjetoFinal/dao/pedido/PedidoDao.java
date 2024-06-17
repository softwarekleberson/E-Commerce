package br.com.engenharia.projeto.ProjetoFinal.dao.pedido;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos.PedidoRepository;

@Service
public class PedidoDao implements IdaoPedido{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public PedidoDao(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
	@Override
	public void salvar(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	public boolean verificaSePedidoEstaEmEstadoDeDevolucao(Long idPedido) {
		var pedido = pedidoRepository.getReferenceById(idPedido);
		if(pedido != null) {
			throw new IllegalArgumentException("Este pedido consta em pedido de troca ");
		}
		return false;
	}

	public boolean verificaCodigoPedido(String codigoPedido) {
		Optional<Pedido> pedido = pedidoRepository.findByCodigoPedido(codigoPedido);
		if(pedido.isEmpty()) {
			throw new IllegalArgumentException("Codigo do produto não encontrado");
		}
		return true;
	}

	public Pedido devolvePedidoPeloCodigo(String codigoPedido) {
		var pedido = pedidoRepository.findByCodigoPedido(codigoPedido);
		if(pedido.isEmpty()) {
			throw new IllegalArgumentException("Codigo pedido incorreto");
		}
		return pedido.get();
	}
}