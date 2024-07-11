package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarCategoria;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.categoria.Categoria;

public interface IdaoCategoria {

	void salvar(Categoria categoria);
	public void alterar(List<DadosAtualizarCategoria> dados);
	void excluir(Long livroId, Long idCategoria);
}
