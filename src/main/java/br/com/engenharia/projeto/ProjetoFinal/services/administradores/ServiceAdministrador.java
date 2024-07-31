package br.com.engenharia.projeto.ProjetoFinal.services.administradores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador.IStrategyAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador.CriptografiaSenhaAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dominio.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.dominio.administrador.RepositorioDeAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dominio.cliente.contato.Email;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosDetalhamentoAdministrador;
import jakarta.validation.Valid;

@Service
public class ServiceAdministrador {

	@Autowired
	private List<IStrategyAdministrador> validadores;
	
	@Autowired
	private CriptografiaSenhaAdministrador criptografia;
	
	@Autowired
	private RepositorioDeAdministrador repositorioDeAdministrador;
	
	public DadosDetalhamentoAdministrador criarAdministrador(@Valid DadosCadastroAdministrador dados) {
		
		Email email = new Email(dados.email());
		boolean emailCadastrado = repositorioDeAdministrador.verificaEmailCadastrado(email);
		if(!emailCadastrado) {
			throw new IllegalArgumentException("Email cadastrado anteriormente");
		}
		
		Administrador administrador = new Administrador(dados);
		validadores.forEach(v -> v.processar(administrador));
		criptografia.processar(administrador);
		
		repositorioDeAdministrador.salvar(administrador);
		return new DadosDetalhamentoAdministrador(administrador);
	}
}