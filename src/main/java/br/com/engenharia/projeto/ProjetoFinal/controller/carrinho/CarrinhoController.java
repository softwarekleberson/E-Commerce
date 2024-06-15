package br.com.engenharia.projeto.ProjetoFinal.controller.carrinho;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.engenharia.projeto.ProjetoFinal.dao.carrinho.CarrinhoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.carrinho.DetalhamentoCarrinho;
import br.com.engenharia.projeto.ProjetoFinal.services.carrinho.ServiceCarrinho;

@RestController
@RequestMapping("carrinho")
@CrossOrigin(origins = "*")
public class CarrinhoController {

	@Autowired
	private ServiceCarrinho service;
		
	@Autowired
	private CarrinhoDao carrinhoDao;
	
    @PostMapping("/cliente/{clienteId}/livro/{livroId}/quantidade/{quantidade}/adicionar")
	public void adicionarItem(@PathVariable Long clienteId, @PathVariable Long livroId, @PathVariable int quantidade) {
		service.adicionarItemAoCarrinho(clienteId, livroId, quantidade);
	}
    
    public ResponseEntity<Page<DetalhamentoCarrinho>> listar
    (@PathVariable Long clienteId, Pageable pageable){
		
    	Page<DetalhamentoCarrinho> carrinho = carrinhoDao.listar(clienteId, pageable);
    	return null;
    }
}