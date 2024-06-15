package br.com.engenharia.projeto.ProjetoFinal.controller.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dao.cupom.CupomDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosCadastroCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosDetalhamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.administrador.AdministradorRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom.CupomRepositroy;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ServiceAdministrador;
import jakarta.validation.Valid;

@RestController
@RequestMapping("administrador")
@CrossOrigin(origins = "*")
public class AdministradorController {

	@Autowired
	private ServiceAdministrador service;
	
	@Autowired
	private AdministradorRepository repository;
	
	@Autowired
	private CupomRepositroy cupomRepositroy;
	
	@PostMapping
	public ResponseEntity cadastrarAdministrador(@RequestBody @Valid DadosCadastroAdministrador dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criarAdministrador(dados);
	    var uri = uriBuilder.path("/administradores/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PostMapping("/cupons")
	public ResponseEntity cadastrarCupom(@RequestBody @Valid DadosCadastroCupom dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criarCupom(dados);
	    var uri = uriBuilder.path("/cupom/{id}").buildAndExpand(dto.idCliente()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping("/cupons/{clienteId}")
	public ResponseEntity<Page<DadosDetalhamentoCupom>> listarCupomPorCliente(@PathVariable Long clienteId, Pageable pageable){
		Page<DadosDetalhamentoCupom> cupons = new CupomDao(cupomRepositroy).listarCuponsDosClientes(clienteId, pageable);
		return ResponseEntity.ok(cupons);
    }
}
