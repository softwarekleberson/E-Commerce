package br.com.engenharia.projeto.ProjetoFinal.dominio.administrador;

import br.com.engenharia.projeto.ProjetoFinal.dominio.cliente.contato.Email;

public interface RepositorioDeAdministrador {
	
	public void salvar(Administrador adminstrador);
	public void deletar(Long id);
	boolean verificaEmailCadastrado(Email email);
	Administrador pegaAdministradorAleatorio();
}
