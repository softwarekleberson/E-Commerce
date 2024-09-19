package br.com.engenharia.projeto.ProjetoFinal.persistencia.itemPedido;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;

public interface ItemPedidoRepository extends JpaRepository<Item, Long>{

}
