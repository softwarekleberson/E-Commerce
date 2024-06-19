package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.pedido;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;

public interface IStrategyPedido {

	public void processar(DadosCadastroPedido dados);
}
