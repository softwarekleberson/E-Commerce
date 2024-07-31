package br.com.engenharia.projeto.ProjetoFinal.persistencia.carrinho;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.dominio.carrinho.Carrinho;
import br.com.engenharia.projeto.ProjetoFinal.dtos.carrinho.DetalhamentoCarrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{

	Page<DetalhamentoCarrinho> findDetalhesCarrinhoByClienteId(Long clienteId,
	Pageable page);
}
