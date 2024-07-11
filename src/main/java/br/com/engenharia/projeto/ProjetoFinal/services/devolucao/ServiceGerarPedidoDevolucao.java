package br.com.engenharia.projeto.ProjetoFinal.services.devolucao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.devolucao.IstrategyDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dao.administrador.AdministradorDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.ClienteDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.devolucao.DevolucaoDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.pedido.PedidoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.EsperandoDevolucaoOuRecebido;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.AnalisePedidoDevolucaoAceitoOuRecusa;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.DevolucaoFoiPedidaOUNAO;
import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.Pedido;
import jakarta.validation.Valid;

@Service
public class ServiceGerarPedidoDevolucao {

	@Autowired
	private AdministradorDao administradorDao;
	
	@Autowired
	private PedidoDao pedidoDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private List<IstrategyDevolucao> validacoes;
	
	@Autowired
	private DevolucaoDao devolucaoDao;
		
	public DadosDetalhamentoDevolucao pedidoDevolucao(@Valid DadosCadastroDevolucao dados, Long idCliente) {
		
		var pedido = carregaPedidoPeloCodigoPedido(dados);
		pedido.devolucaoPedida(DevolucaoFoiPedidaOUNAO.DEVOLUCAO_PEDIDO);
		pedidoDao.salvar(pedido);

		validacoes.forEach(v ->v.processar(dados));
		
		var cliente = carregaClientePeloId(idCliente);
		var admAleatorio = escolheAdmAleatoriamente();
		String codigoPedido = pedido.getCodigoPedido();
		String criaCodigoDevolucao = UUID.randomUUID().toString();
				
		var devolucao = new Devolucao(null, criaCodigoDevolucao,LocalDate.now(),
									  null, cliente, pedido, codigoPedido,
									  admAleatorio, EsperandoDevolucaoOuRecebido.ESPERANDO_DEVOLUCAO,
									  AnalisePedidoDevolucaoAceitoOuRecusa.EM_ANALISE
									  );
		
		devolucaoDao.salvar(devolucao);
		return new DadosDetalhamentoDevolucao(devolucao);
	}

	private Cliente carregaClientePeloId(Long id) {
		var cliente = clienteDao.recuperaClientePelo(id);
		return cliente;
	}

	private Administrador escolheAdmAleatoriamente() {
		var adm = administradorDao.pegaAdministradorAleatorio();
		return adm;
	}

	private Pedido carregaPedidoPeloCodigoPedido(DadosCadastroDevolucao dados) {
		var pedido = pedidoDao.devolvePedidoPeloCodigo(dados.codigoPedido());
		return pedido;
	}
}