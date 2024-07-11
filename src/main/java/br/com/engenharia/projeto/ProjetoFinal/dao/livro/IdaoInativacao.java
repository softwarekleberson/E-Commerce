package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.statusLivro.StatusLivro;

public interface IdaoInativacao {

	void salvar(StatusLivro inativacao);
}
