package br.com.engenharia.projeto.ProjetoFinal.entidades.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosDetalhamentoPedido;

public interface RepositorioDePedido {

	public void salvar(Pedido pedido);
	public boolean verificaCodigoPedido(String codigoPedido);
	Page<DadosDetalhamentoPedido> listarPedidosCliente(Long clienteId, Pageable pageable);
	public Pedido devolvePedidoPeloCodigo(String codigoPedido);
}
