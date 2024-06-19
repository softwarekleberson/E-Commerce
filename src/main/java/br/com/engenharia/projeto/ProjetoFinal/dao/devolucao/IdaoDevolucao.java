package br.com.engenharia.projeto.ProjetoFinal.dao.devolucao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoTotalDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.Devolucao;

public interface IdaoDevolucao {

	public void salvar(Devolucao devolucao);
	public Devolucao carregarDevolucao(String codigoDevolucao);
	public Page<DadosDetalhamentoTotalDevolucao> listarTodasAsDevolucoes(Pageable pageable, Long admId);

}
