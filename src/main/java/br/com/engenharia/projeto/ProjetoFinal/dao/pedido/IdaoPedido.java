package br.com.engenharia.projeto.ProjetoFinal.dao.pedido;

import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.Pedido;

public interface IdaoPedido {

	public void salvar(Pedido pedido);
	public boolean verificaCodigoPedido(String codigoPedido);
}
