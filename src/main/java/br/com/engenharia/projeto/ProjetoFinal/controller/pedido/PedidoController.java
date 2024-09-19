package br.com.engenharia.projeto.ProjetoFinal.controller.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dao.item.RepositorioDeItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosAtualizacaoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;
import br.com.engenharia.projeto.ProjetoFinal.services.pedido.ServicePedido;
import br.com.engenharia.projeto.ProjetoFinal.services.pedido.ServicePedidoUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

	@Autowired
	private ServicePedido insert;
	
	@Autowired
	private ServicePedidoUpdate update;
	
	@Autowired
	private RepositorioDeItem repositorioDeItem;
	
	@PostMapping("{clienteId}/{livroId}")
	public ResponseEntity cadastrar(@PathVariable Long clienteId, @PathVariable Long livroId, @RequestBody @Valid DadosCadastroPedido dados, UriComponentsBuilder uriBuilder) {
		var dto = insert.criar(dados, clienteId, livroId);
	    var uri = uriBuilder.path("/pedido/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping("itens/produto/{id}")
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoItem dados) {
		DadosDetalhamentoItem detalhamentoItem = update.atualizar(dados, id);
        return ResponseEntity.ok(detalhamentoItem);
	}
	
	@DeleteMapping("itens/produto/{id}")
	public ResponseEntity<Void> deletar (@PathVariable Long id){
		repositorioDeItem.deletar(id);
		return ResponseEntity.noContent().build();
	}
}