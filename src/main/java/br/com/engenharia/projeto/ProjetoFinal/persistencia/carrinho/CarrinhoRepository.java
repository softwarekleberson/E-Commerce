package br.com.engenharia.projeto.ProjetoFinal.persistencia.carrinho;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.dtos.carrinho.DetalhamentoCarrinho;
import br.com.engenharia.projeto.ProjetoFinal.entidades.carrinho.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{

	Page<DetalhamentoCarrinho> findDetalhesCarrinhoByClienteId(Long clienteId,
	Pageable page);
}
