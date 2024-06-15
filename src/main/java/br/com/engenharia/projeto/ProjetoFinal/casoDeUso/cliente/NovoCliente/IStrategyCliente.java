package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovoCliente;

import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;

public interface IStrategyCliente {

	public void processar(Cliente dominio);
}
