package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivroCompleto;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;

public interface IdaoLivro {

	void salvar(Livro livro);
	Page<DadosDetalhamentoLivro> listarLivros(Pageable pageable);
	public DadosDetalhamentoLivroCompleto listarLivroExpecifico(Long id);
	void excluir(Long livroId);
	void exclusao(Livro exclusaoLogica);
	DadosDetalhamentoLivro alterar(DadosAtualizarLivro dados);
}
