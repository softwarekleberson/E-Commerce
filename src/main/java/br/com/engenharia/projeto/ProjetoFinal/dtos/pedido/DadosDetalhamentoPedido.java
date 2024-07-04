package br.com.engenharia.projeto.ProjetoFinal.dtos.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Imagens;
import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.StatusEntrega;

public record DadosDetalhamentoPedido(
		
		Long id,
		LocalDate pedidoRealizado,
		int quantidade,
		BigDecimal valorTotal,
		String codigoPedido,
		String descricaoPedido,
		StatusEntrega  statusEntrega,
		String urlLivro,
		String nome,
		Long idCliente
		
		) {
	
	public DadosDetalhamentoPedido(Pedido pedido) {
		this(pedido.getId(),pedido.getPedidoRealizado(),
			pedido.getQuantidade(), pedido.getValorTotal(),
			pedido.getCodigoPedido(), pedido.getLivro().getSinopse(),
			pedido.getStatusEntrega(), pedido.getLivro().getImagens().stream().findFirst().map(Imagens::getUrl).orElse(null),
			pedido.getLivro().getTitulo(), pedido.getCliente().getId());
	}
}
