package br.com.engenharia.projeto.ProjetoFinal.dtos.cliente;

import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;

public record DadosDetalhamentoSenha(
		
	Long idCliente, String senha
		
	) {

	public DadosDetalhamentoSenha(Cliente cliente) {
		this(cliente.getId() ,cliente.getSenha());
	}
}
