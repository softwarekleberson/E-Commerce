package br.com.engenharia.projeto.ProjetoFinal.dao.endereco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosAtualizacaoEntregas;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosDetalhamentoEntrega;
import br.com.engenharia.projeto.ProjetoFinal.entidade.endereco.Entrega;

public interface IdaoEntrega {

	DadosDetalhamentoEntrega salvarNovoEntrega(Entrega entrega);
	void salvar(Entrega entrega);
	Page<DadosDetalhamentoEntrega> listarEntregasDoCliente(Long clienteId, Pageable pageable);
	void excluir(Long idEntrega);
	public Entrega alterar(Long entregaId, DadosAtualizacaoEntregas dados);
}
