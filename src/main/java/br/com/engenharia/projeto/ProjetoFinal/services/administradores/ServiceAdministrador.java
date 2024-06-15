package br.com.engenharia.projeto.ProjetoFinal.services.administradores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador.IStrategyAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador.CriptografiaSenhaAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dao.administrador.AdministradorDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.cupom.CupomDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosDetalhamentoAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosCadastroCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosDetalhamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Email;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cupom.Cupom;
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
}