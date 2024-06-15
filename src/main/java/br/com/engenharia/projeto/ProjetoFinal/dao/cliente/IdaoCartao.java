package br.com.engenharia.projeto.ProjetoFinal.dao.cliente;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosAtualizacaoCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cartao;

public interface IdaoCartao {

	public String salvar(Cartao entidade);
	Cartao alterar(Long idCartao, DadosAtualizacaoCartao dados);
	public void deletar(Long id);
}
