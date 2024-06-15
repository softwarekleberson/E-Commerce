package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtulizacaoAutor;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Autor;

public interface IdaoAutor {

	void salvar(Autor autor);
	public void alterar(List<DadosAtulizacaoAutor> dados);
	void excluir(Long livroId, Long idAutor);
}
