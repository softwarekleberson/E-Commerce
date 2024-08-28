package br.com.engenharia.projeto.ProjetoFinal.entidades.carrinho;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.carrinho.DetalhamentoCarrinho;

public interface RepositorioDeCarrinho {

	public void salvar(Carrinho carrinho);
	public Page<DetalhamentoCarrinho> listar(Long clienteId, Pageable page);
}
