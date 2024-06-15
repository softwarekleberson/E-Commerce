package br.com.engenharia.projeto.ProjetoFinal.persistencia.carrinho;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidade.carrinho.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
