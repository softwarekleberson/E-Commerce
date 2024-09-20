package br.com.engenharia.projeto.ProjetoFinal.persistencia.pedidos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	Pedido findByCodigoPedido(String codigoPedido);
	Page<Pedido> findByCliente_Id(Long clienteId, Pageable pageable);
	List<Pedido> findByPagoFalseAndPedidoRealizadoBefore(LocalDate dataLimite);
	
}
