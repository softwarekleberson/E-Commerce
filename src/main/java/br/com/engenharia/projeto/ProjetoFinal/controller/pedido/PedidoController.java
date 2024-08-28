package br.com.engenharia.projeto.ProjetoFinal.controller.pedido;

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

import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosDetalhamentoPedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.RepositorioDePedido;
import br.com.engenharia.projeto.ProjetoFinal.services.pedido.ServicePedido;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

	@Autowired
	private ServicePedido service;
	
	@Autowired
	private RepositorioDePedido repositorioDePedido;
	
	@PostMapping("{clienteId}/{carrinhoId}")
	public ResponseEntity cadastrar(@PathVariable Long clienteId, @PathVariable Long carrinhoId, @RequestBody @Valid DadosCadastroPedido dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criar(dados, clienteId, carrinhoId);
	    var uri = uriBuilder.path("/pedido/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping("{clienteId}")
	public ResponseEntity<Page<DadosDetalhamentoPedido>> listarPorCliente(@PathVariable Long clienteId, Pageable pageable){
		Page<DadosDetalhamentoPedido> pedidos = repositorioDePedido.listarPedidosCliente(clienteId, pageable);
		return ResponseEntity.ok(pedidos);
    }
}