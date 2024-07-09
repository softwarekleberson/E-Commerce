package br.com.engenharia.projeto.ProjetoFinal.dao.cartao;

import java.util.Optional;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosAtualizacaoCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.cartao.Cartao;

public interface IdaoCartao {

	public Cartao salvar(Cartao entidade);
	Cartao alterar(Long idCartao, DadosAtualizacaoCartao dados);
	public void deletar(Long id);
	public Optional<Cartao> cartaoCadastradoAnteriormente(String numeroCartao);
}
