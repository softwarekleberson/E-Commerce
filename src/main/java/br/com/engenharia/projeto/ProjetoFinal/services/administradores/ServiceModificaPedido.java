package br.com.engenharia.projeto.ProjetoFinal.services.administradores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.PedidoNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.RepositorioDePedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.StatusPedido;

@Service
public class ServiceModificaPedido {

	@Autowired
	private RepositorioDePedido repository;
		
	public void modificarStatusEntregaPedido(String codigoPedido) {
		var pedido = repository.devolvePedidoPeloCodigo(codigoPedido);
		
		if(pedido != null && pedido.isPago()) {
			pedido.modificarStatusEntrega(StatusPedido.RECEBIDO);
			repository.salvar(pedido);
		}
		else {
			throw new PedidoNaoEncontradoExcecao("Codigo de pedido não encontrado");
		}
	}

	@Scheduled(cron = "0 0 0 * * *", zone = "America/Sao_Paulo")
	public void excluirPedidosNaoFinalizados(int dias) {
		
		LocalDate dataLimite = LocalDate.now().minusDays(1);
		List<Pedido> pedidosParaExcluir = repository.encontrarPedidosNaoPagosAposDataLimite(dataLimite);
    	
		for(Pedido pedido : pedidosParaExcluir) {
			repository.excluir(pedido);
		}
	}
}