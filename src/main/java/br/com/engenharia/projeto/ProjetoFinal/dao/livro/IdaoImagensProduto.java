package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarImagem;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Imagens;

public interface IdaoImagensProduto {

	void salvar(Imagens imagens);
	public void alterar(List<DadosAtualizarImagem> dados);
	void excluir(Long livroId, Long idImagem);

}
