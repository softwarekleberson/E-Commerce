package br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
