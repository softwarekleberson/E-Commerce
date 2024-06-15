package br.com.engenharia.projeto.ProjetoFinal.dao.administrador;

import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.Administrador;

public interface IdaoAdministrador {

	public void salvar(Administrador adminstrador);
	public void deletar(Long id);

}
