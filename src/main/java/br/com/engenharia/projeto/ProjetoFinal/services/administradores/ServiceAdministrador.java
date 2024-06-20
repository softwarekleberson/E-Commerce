package br.com.engenharia.projeto.ProjetoFinal.services.administradores;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador.IStrategyAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador.CriptografiaSenhaAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dao.administrador.AdministradorDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.cupom.CupomDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.devolucao.DevolucaoDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.livro.EstoqueDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosDetalhamentoAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosCadastroCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosDetalhamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosAtualizacaoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoTotalDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.ProdutoVoltaParaEstoque;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Email;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cupom.TipoCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.AnalisePedidoDevolucaoAceitoOuRecusa;
import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.estoque.Estoque;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.administrador.AdministradorRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.ClienteRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom.CupomRepositroy;
import jakarta.validation.Valid;

@Service
public class ServiceAdministrador {

	@Autowired
	private List<IStrategyAdministrador> validadores;
	
	@Autowired
	private CriptografiaSenhaAdministrador criptografia;
	
	@Autowired
	private AdministradorRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CupomRepositroy cupomRepositroy;
	
	@Autowired 
	private DevolucaoDao devolucaoDao;
	
	@Autowired
	private CupomDao cupomDao;
	
	@Autowired
	private EstoqueDao estoqueDao;
	
	public DadosDetalhamentoAdministrador criarAdministrador(@Valid DadosCadastroAdministrador dados) {
		
		Email email = new Email(dados.email());
		Optional<Administrador> admExiste = repository.findByEmail(email);
		if(!admExiste.isEmpty()) {
			throw new IllegalArgumentException("Email cadastrado anteriormente");
		}
		
		Administrador administrador = new Administrador(dados);
		validadores.forEach(v -> v.processar(administrador));
		criptografia.processar(administrador);
		
		new AdministradorDao(repository).salvar(administrador);
		return new DadosDetalhamentoAdministrador(administrador);
	}

	public DadosDetalhamentoCupom criarCupom(@Valid DadosCadastroCupom dados) {
		Optional<Cliente> clienteExiste = clienteRepository.findById(dados.idCliente());
		if(clienteExiste.isEmpty()) {
			throw new IllegalArgumentException("Id do cliente não existe");
		}
		
		Cupom cupom = new Cupom(dados);
		
		new CupomDao(cupomRepositroy).salvar(cupom);
		return new DadosDetalhamentoCupom(cupom);
	}
	
	public DadosDetalhamentoTotalDevolucao devolucaoRecusada(@Valid DadosAtualizacaoDevolucao dados, String codigoDevolucao) {
		var recusaDevolucao = devolucaoDao.carregarDevolucao(codigoDevolucao);
		
		recusaDevolucao.setDataConclusaoTroca(LocalDate.now());
		recusaDevolucao.devoluvaoChegou(dados.esperandoDevolucaoOuRecebido());
		recusaDevolucao.analisePedidoDevolucao(AnalisePedidoDevolucaoAceitoOuRecusa.TROCA_RECUSADA);
		devolucaoDao.salvar(recusaDevolucao);
		
		return new DadosDetalhamentoTotalDevolucao(recusaDevolucao);
	}
	
	public DadosDetalhamentoTotalDevolucao devolucaoAceita (@Valid DadosAtualizacaoDevolucao dados, String codigoDevolucao) {
		var aceitaDevolucao = devolucaoDao.carregarDevolucao(codigoDevolucao);
		
		aceitaDevolucao.setDataConclusaoTroca(LocalDate.now());
		aceitaDevolucao.devoluvaoChegou(dados.esperandoDevolucaoOuRecebido());
		aceitaDevolucao.analisePedidoDevolucao(AnalisePedidoDevolucaoAceitoOuRecusa.TROCA_ACEITA);
		
		geraCupomAposAprovarDevolucao(aceitaDevolucao);
		devolucaoVoltaParaEstoque(dados, aceitaDevolucao);
		devolucaoDao.salvar(aceitaDevolucao);
		
		return new DadosDetalhamentoTotalDevolucao(aceitaDevolucao);
	}

	private void devolucaoVoltaParaEstoque(DadosAtualizacaoDevolucao dados, Devolucao devolucao) {
		if(dados.produtoVoltaParaEstoque() == ProdutoVoltaParaEstoque.SIM) {
			var estoque = new Estoque();
			
			estoque.setId(null);
			estoque.setLivro(devolucao.getPedido().getLivro().getId());
			estoque.setQuantidade(devolucao.getPedido().getQuantidade());
			estoque.setValorCusto(BigDecimal.ZERO);
			estoque.setDataEntrada(LocalDate.now());
			estoque.setFornecedor("Devolução feita pelo cliente");
			estoque.setEstadoDoProduto(dados.estadoProduto());
			
			estoqueDao.salvar(estoque);
		}
	}

	private DadosDetalhamentoCupom geraCupomAposAprovarDevolucao(Devolucao aceitaDevolucao) {
		Long clienteId = aceitaDevolucao.getPedido().getCarrinho().getCliente().getId();
		Cupom gerarCupom = new Cupom();
		
		gerarCupom.setId(null);
		gerarCupom.setTipoCupom(TipoCupom.TROCA);
		gerarCupom.setValor(aceitaDevolucao.getPedido().getValorTotal());
		gerarCupom.setStatus(true);
		gerarCupom.setCliente(clienteId);
		
		cupomDao.salvar(gerarCupom);
		return new DadosDetalhamentoCupom(gerarCupom);
	}
}