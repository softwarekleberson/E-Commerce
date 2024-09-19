package br.com.engenharia.projeto.ProjetoFinal.dao.item;

import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosAtualizacaoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;

public interface RepositorioDeItem {

	public void salvar(Item item);
	public DadosDetalhamentoItem upadate(Long id, DadosAtualizacaoItem dados);
	public void deletar(Long id);
}
