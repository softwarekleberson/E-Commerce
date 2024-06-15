package br.com.engenharia.projeto.ProjetoFinal.dao.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.Pedido;

public interface IdaoPedido {

	public void salvar(Pedido pedido);
	Page pegaTodosClientes(Pageable page);
}
