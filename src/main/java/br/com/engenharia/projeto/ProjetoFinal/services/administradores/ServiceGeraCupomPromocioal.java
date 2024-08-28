package br.com.engenharia.projeto.ProjetoFinal.services.administradores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosCadastroCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosDetalhamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.RepositorioDeCupom;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.ClienteRepository;
import jakarta.validation.Valid;

@Service
public class ServiceGeraCupomPromocioal {

	@Autowired
	private RepositorioDeCupom repositorioDeCupom;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public DadosDetalhamentoCupom criarCupom(@Valid DadosCadastroCupom dados) {
		Optional<Cliente> clienteExiste = clienteRepository.findById(dados.idCliente());
		if(clienteExiste.isEmpty()) {
			throw new IllegalArgumentException("Id do cliente não existe");
		}
		
		Cupom cupom = new Cupom(dados);
		
		repositorioDeCupom.salvar(cupom);
		return new DadosDetalhamentoCupom(cupom);
	}
}