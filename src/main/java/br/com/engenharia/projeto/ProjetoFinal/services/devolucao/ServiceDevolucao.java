package br.com.engenharia.projeto.ProjetoFinal.services.devolucao;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.administrador.AdministradorDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.ClienteDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.devolucao.DevolucaoDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.pedido.PedidoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.AnalisePedidoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.StatusEntrega;
import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.TrocaDevolucao;
import jakarta.validation.Valid;

@Service
public class ServiceDevolucao {

	@Autowired
	private AdministradorDao administradorDao;
	
	@Autowired
	private PedidoDao pedidoDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private DevolucaoDao devolucaoDao;
		
	public DadosDetalhamentoDevolucao pedidoDevolucao(@Valid DadosCadastroDevolucao dados) {
		
		//Refatorar e colocar as regras de negocio aqui
		var cliente = verificaStatusAtivoCliente(dados);
		verificaCodigoPedido(dados);
		var pedido = carregaPedidoPeloCodigoPedido(dados);
		verificaSeTrocaFoiPedida(pedido);
		verificaStatusEntrega(pedido);
		verificaSePedidoFoiPedidoDevolucaoAnterior(pedido);

		var adm = escolheAdmAleatoriamente();
		var analisePedidoDevolucao = analisaPedidoDevolucao();
		String codigoDevolucao = UUID.randomUUID().toString();
		
		
		var devolucao = new Devolucao(null, codigoDevolucao,LocalDate.now(),
									  null, cliente, pedido,
									  adm, analisePedidoDevolucao);
		
		devolucaoDao.salvar(devolucao);
		return new DadosDetalhamentoDevolucao(devolucao);
	}

	private void verificaSePedidoFoiPedidoDevolucaoAnterior(Pedido pedido) {
		boolean trocaPedida = pedidoDao.verificaSePedidoEstaEmEstadoDeDevolucao(pedido.getId());
		if(trocaPedida != true) {
			throw new IllegalArgumentException("Pedido consta em estado de devolução");
		}
	}

	private void verificaSeTrocaFoiPedida(Pedido pedido) {
		if(pedido.getTrocaDevolucao() != TrocaDevolucao.DEVOLUCAO_PEDIDO) {
			throw new IllegalArgumentException("Devolução não pedida");
		}
	}

	private AnalisePedidoDevolucao analisaPedidoDevolucao() {
		var analisePedidoDevolucao = AnalisePedidoDevolucao.ESPERANDO_DEVOLUCAO;
		return analisePedidoDevolucao;
	}

	private void verificaStatusEntrega(Pedido pedido) {
		if(pedido.getStatusEntrega() != StatusEntrega.RECEBIDO) {
			throw new IllegalArgumentException("Não se pode devolver produto não entregue");
		}
	}

	private Administrador escolheAdmAleatoriamente() {
		var adm = administradorDao.pegaAdministradorAleatorio();
		return adm;
	}

	private Pedido carregaPedidoPeloCodigoPedido(DadosCadastroDevolucao dados) {
		var pedido = pedidoDao.devolvePedidoPeloCodigo(dados.codigoPedido());
		return pedido;
	}

	private void verificaCodigoPedido(DadosCadastroDevolucao dados) {
		if(!pedidoDao.verificaCodigoPedido(dados.codigoPedido())) {
			throw new IllegalArgumentException("Codigo produto incorreto");
		}
	}

	private Cliente verificaStatusAtivoCliente(DadosCadastroDevolucao dados) {
		var cliente = clienteDao.pegaClienteAtivo(dados.idCliente());
		if(cliente.getAtivo() != true) {
			throw new IllegalArgumentException("Cliente inativo ou inativo");
		}
		return cliente;
	}
}