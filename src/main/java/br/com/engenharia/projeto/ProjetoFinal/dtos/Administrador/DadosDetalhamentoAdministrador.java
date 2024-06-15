package br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador;

import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Email;

public record DadosDetalhamentoAdministrador(
		
		Long id,
		String nome,
		Email email
		
		) {

	public DadosDetalhamentoAdministrador(Administrador adminstrador) {
		this(adminstrador.getId(), adminstrador.getNome(), adminstrador.getEmail());
	}
}
