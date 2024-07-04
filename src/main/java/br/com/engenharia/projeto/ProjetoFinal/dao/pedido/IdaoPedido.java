package br.com.engenharia.projeto.ProjetoFinal.dao.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosDetalhamentoPedido;
import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.Pedido;

public interface IdaoPedido {

	public void salvar(Pedido pedido);
	public boolean verificaCodigoPedido(String codigoPedido);
	Page<DadosDetalhamentoPedido> listarPedidosCliente(Long clienteId, Pageable pageable);
}
