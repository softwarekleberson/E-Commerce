package br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	Optional<Pedido> findByCodigoPedido(String codigoPedido);

}
