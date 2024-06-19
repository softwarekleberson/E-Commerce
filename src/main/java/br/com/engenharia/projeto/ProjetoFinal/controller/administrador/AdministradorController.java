package br.com.engenharia.projeto.ProjetoFinal.controller.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dao.cupom.CupomDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.devolucao.DevolucaoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosCadastroCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosDetalhamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosAtualizacaoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoTotalDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ServiceAdministrador;
import jakarta.validation.Valid;

@RestController
@RequestMapping("administrador")
@CrossOrigin(origins = "*")
public class AdministradorController {

	@Autowired
	private ServiceAdministrador service;
			
	@Autowired
	private CupomDao cupomDao;
	
	@Autowired
	private DevolucaoDao devolucaoDao;
	
	@PostMapping
	public ResponseEntity cadastrarAdministrador(@RequestBody @Valid DadosCadastroAdministrador dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criarAdministrador(dados);
	    var uri = uriBuilder.path("/administradores/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PostMapping("/cupons")
	public ResponseEntity cadastrarCupomPromocional(@RequestBody @Valid DadosCadastroCupom dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criarCupom(dados);
	    var uri = uriBuilder.path("/cupom/{id}").buildAndExpand(dto.idCliente()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping("devolucoes/recusar/{codigoDevolucao}")
	public ResponseEntity recusarDevolucoes(@PathVariable String codigoDevolucao, @RequestBody @Valid DadosAtualizacaoDevolucao dados) {
		DadosDetalhamentoTotalDevolucao detalhamentoDevolucao = service.devolucaoRecusada(dados, codigoDevolucao);
        return ResponseEntity.ok(detalhamentoDevolucao);
	}
	
	@PutMapping("devolucoes/aceitar/{codigoDevolucao}")
	public ResponseEntity aceitarDevolucoes(@PathVariable String codigoDevolucao, @RequestBody @Valid DadosAtualizacaoDevolucao dados) {
		DadosDetalhamentoTotalDevolucao detalhamentoDevolucao = service.devolucaoAceita(dados, codigoDevolucao);
        return ResponseEntity.ok(detalhamentoDevolucao);
	}
	
	@GetMapping("devolucoes/{admId}")
	public ResponseEntity<Page<DadosDetalhamentoTotalDevolucao>> listarTodasAsDevolucoes(@PathVariable Long admId, Pageable pageable){
		Page<DadosDetalhamentoTotalDevolucao> devolucoes = devolucaoDao.listarTodasAsDevolucoes(pageable, admId);
		return ResponseEntity.ok(devolucoes);
    }
	
	@GetMapping("/cupons/{clienteId}")
	public ResponseEntity<Page<DadosDetalhamentoCupom>> listarCupomPorCliente(@PathVariable Long clienteId, Pageable pageable){
		Page<DadosDetalhamentoCupom> cupons = cupomDao.listarCuponsDosClientes(clienteId, pageable);
		return ResponseEntity.ok(cupons);
    }
}