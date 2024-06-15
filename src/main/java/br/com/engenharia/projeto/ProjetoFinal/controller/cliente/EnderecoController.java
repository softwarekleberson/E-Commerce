package br.com.engenharia.projeto.ProjetoFinal.controller.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.CobrancaDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.EntregaDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosAtualizacaoCobrancas;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosDetalhamentoCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosAtualizacaoEntregas;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosDetalhamentoEntrega;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.ClienteRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CobrancaRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.EntregaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("endereco")
@CrossOrigin(origins = "*")
public class EnderecoController {

	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private CobrancaRepository cobrancaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping("/entrega")
	public ResponseEntity cadastrarEntrega(@RequestBody @Valid DadosCadastroEntrega dados) {
		var entrega = new Entrega(dados);
		new EntregaDao(entregaRepository, clienteRepository).salvarNovoEntrega(entrega);
		return ResponseEntity.ok(entrega);
	}
	
	@PostMapping("/cobranca")
	public ResponseEntity cadastrarCobranca(@RequestBody @Valid DadosCadastroCobranca dados) {
		var cobranca = new Cobranca(dados);
		new CobrancaDao(cobrancaRepository, clienteRepository).salvarNovaCobranca(cobranca);
		return ResponseEntity.ok(cobranca);
	}
	
	@GetMapping("/entrega/{clienteId}")
	public ResponseEntity<Page<DadosDetalhamentoEntrega>> listarEnderecosEntrega(@PathVariable Long clienteId, Pageable pageable){
		Page<DadosDetalhamentoEntrega> entregas = new EntregaDao(entregaRepository, clienteRepository).listarEntregasDoCliente(clienteId, pageable);
		return ResponseEntity.ok(entregas);
    }
	
	@GetMapping("/cobranca/{clienteId}")
	public ResponseEntity<Page<DadosDetalhamentoCobranca>> listarEnderecosCobranca(@PathVariable Long clienteId, Pageable pageable){
		Page<DadosDetalhamentoCobranca> cobrancas = new CobrancaDao(cobrancaRepository, clienteRepository).listarEnderecosCobrancaDoCliente(clienteId, pageable);
		return ResponseEntity.ok(cobrancas);
    }
	
	@PutMapping("/entrega/{entregaId}")
	public  ResponseEntity atualizarEntrega(@PathVariable Long entregaId, @RequestBody @Valid DadosAtualizacaoEntregas dados) {
		Entrega updateEntrega = new EntregaDao(entregaRepository, clienteRepository).alterar(entregaId, dados);
		return ResponseEntity.ok(updateEntrega);
	}
	
	@PutMapping("/cobranca/{cobrancaId}")
	public  ResponseEntity atualizarCobranca(@PathVariable Long cobrancaId, @RequestBody @Valid DadosAtualizacaoCobrancas dados) {
		Cobranca updateCobranca = new CobrancaDao(cobrancaRepository, clienteRepository).alterar(cobrancaId, dados);
		return ResponseEntity.ok(updateCobranca);
	}
	
	@DeleteMapping("/entrega/{idEntrega}")
	public ResponseEntity<Void> deletarEnderecoEntrega (@PathVariable Long idEntrega) {
		new EntregaDao(entregaRepository, clienteRepository).excluir(idEntrega);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/cobranca/{idCobranca}")
	public ResponseEntity<Void> deletarEnderecoCobranca (@PathVariable Long idCobranca) {
		new CobrancaDao(cobrancaRepository, clienteRepository).excluir(idCobranca);
		return ResponseEntity.noContent().build();
	}	
}