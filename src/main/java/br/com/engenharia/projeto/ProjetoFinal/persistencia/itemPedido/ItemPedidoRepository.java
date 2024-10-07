package br.com.engenharia.projeto.ProjetoFinal.persistencia.itemPedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;

public interface ItemPedidoRepository extends JpaRepository<Item, Long> {

	@Query("SELECT new br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem( " +
		       "i.id, l.id, p.id, i.quantidade, p.pedidoRealizado, l.titulo, " +
		       "img.url, " + 
		       "i.precoUnitario, i.subtotal, p.codigoPedido) " +
		       "FROM Item i " +
		       "JOIN i.livro l " +
		       "JOIN l.imagens img " + 
		       "JOIN i.pedido p " +
		       "JOIN p.cliente c " +
		       "WHERE c.id = :clienteId " +
		       "AND img.id = (SELECT MIN(i2.id) FROM Imagens i2 WHERE i2.livro.id = l.id)") 
	List<DadosDetalhamentoItem> buscarItensDetalhadosPorClienteId(@Param("clienteId") Long clienteId);
}
