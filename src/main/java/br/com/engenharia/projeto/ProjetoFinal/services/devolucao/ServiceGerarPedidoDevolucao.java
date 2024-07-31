package br.com.engenharia.projeto.ProjetoFinal.services.devolucao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.devolucao.IstrategyDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dao.devolucao.DevolucaoDao;
import br.com.engenharia.projeto.ProjetoFinal.dominio.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.dominio.administrador.RepositorioDeAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dominio.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.dominio.cliente.RepositorioDeCliente;
import br.com.engenharia.projeto.ProjetoFinal.dominio.devolucao.AnalisePedidoDevolucaoAceitoOuRecusa;
import br.com.engenharia.projeto.ProjetoFinal.dominio.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.dominio.devolucao.EsperandoDevolucaoOuRecebido;
import br.com.engenharia.projeto.ProjetoFinal.dominio.pedido.DevolucaoFoiPedidaOUNAO;
import br.com.engenharia.projeto.ProjetoFinal.dominio.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.dominio.pedido.RepositorioDePedido;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoDevolucao;
import jakarta.validation.Valid;

@Service
public class ServiceGerarPedidoDevolucao {

	@Autowired
	private RepositorioDeAdministrador repositorioDeAdministrador;
	
	@Autowired
	private RepositorioDePedido repositorioDePedido;
	
	@Autowired
	private RepositorioDeCliente repositorioDeCliente;
	
	@Autowired
	private List<IstrategyDevolucao> validacoes;
	
	@Autowired
	private DevolucaoDao devolucaoDao;
		
	public DadosDetalhamentoDevolucao pedidoDevolucao(@Valid DadosCadastroDevolucao dados, Long idCliente) {
		
		var pedido = carregaPedidoPeloCodigoPedido(dados);
		pedido.devolucaoPedida(DevolucaoFoiPedidaOUNAO.DEVOLUCAO_PEDIDO);
		repositorioDePedido.salvar(pedido);

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
		var cliente = repositorioDeCliente.recuperaClientePelo(id);
		return cliente;
	}

	private Administrador escolheAdmAleatoriamente() {
		var adm = repositorioDeAdministrador.pegaAdministradorAleatorio();
		return adm;
	}

	private Pedido carregaPedidoPeloCodigoPedido(DadosCadastroDevolucao dados) {
		var pedido = repositorioDePedido.devolvePedidoPeloCodigo(dados.codigoPedido());
		return pedido;
	}
}