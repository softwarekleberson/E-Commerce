package br.com.engenharia.projeto.ProjetoFinal.persistencia.itemPedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;

public interface ItemPedidoRepository extends JpaRepository<Item, Long>{

	@Query("SELECT new br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem(i.id, l.id, p.id, i.quantidade, p.pedidoRealizado, l.titulo, " +
		       "(SELECT img.url FROM Imagens img WHERE img.livro.id = l.id ORDER BY img.id ASC LIMIT 1), " +
		       "i.precoUnitario, i.subtotal) " +
		       "FROM Item i " +
		       "JOIN i.livro l " +
		       "JOIN i.pedido p " +
		       "JOIN p.cliente c " +
		       "WHERE c.id = :clienteId")
	List<DadosDetalhamentoItem> buscarItensDetalhadosPorClienteId(@Param("clienteId") Long clienteId);

}
