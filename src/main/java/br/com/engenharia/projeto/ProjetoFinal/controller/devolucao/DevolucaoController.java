package br.com.engenharia.projeto.ProjetoFinal.controller.devolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.services.devolucao.ServiceDevolucao;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/devolucoes")
@CrossOrigin(origins = "*")
public class DevolucaoController {

	@Autowired
	private ServiceDevolucao service;
	
	@PostMapping
	public ResponseEntity cadastrarPedidoDevolucao(@RequestBody @Valid DadosCadastroAdministrador dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criar(dados);
	    var uri = uriBuilder.path("/devolucoes/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
