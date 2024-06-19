package br.com.engenharia.projeto.ProjetoFinal.services.carrinho;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.carrinho.CarrinhoDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.ClienteDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.item.ItemDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.livro.EstoqueDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.livro.LivroDao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.carrinho.Carrinho;
import br.com.engenharia.projeto.ProjetoFinal.entidade.carrinho.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;

@Service
public class ServiceCarrinho {

	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private EstoqueDao estoqueDao;
	
	@Autowired
	private CarrinhoDao carrinhoDao;
	
	@Autowired
	private LivroDao livroDao;
	
	@Autowired
	private ItemDao itemDao;

	public void adicionarItemAoCarrinho(Long clienteId, Long livroId, int quantidade) {
		
		Cliente cliente = verificaExistenciaCliente(clienteId);
		
		Carrinho carrinho = cliente.getCarrinho();		
		Livro livro = inicializaLivro(livroId);
		
		if(carrinho == null) {
			carrinho = criaCarrinho(clienteId, cliente);
		}
		
		adicionaItemAoCarrinho(livroId, quantidade, carrinho, livro);
	}

	private Carrinho criaCarrinho(Long clienteId, Cliente cliente) {
		Carrinho carrinho;
		carrinho = new Carrinho();
		carrinho.setCliente(clienteId);
		cliente.setCarrinho(carrinho);
		carrinhoDao.salvar(carrinho);
		return carrinho;
	}

	private void adicionaItemAoCarrinho(Long livroId, int quantidade, Carrinho carrinho, Livro livro) {
	    if (carrinho.getItensCarrinho() == null) {
	        carrinho.setItensCarrinho(new ArrayList<>());
	    }

	    Item itemExistente = carrinho.getItensCarrinho().stream()
	        .filter(item -> item.getLivro().getId().equals(livroId))
	        .findFirst()
	        .orElse(null);

	    if (itemExistente != null) {
	        itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
	    } else {
	        Item item = new Item();
	        item.setCarrinho(carrinho);
	        item.setLivro(livro);
	        item.setQuantidade(quantidade);
	        carrinho.getItensCarrinho().add(item);
	        itemDao.salvar(item);
	    }

	    carrinho.calcularPrecoTotal();
	    carrinhoDao.salvar(carrinho);
	}
	
	private Livro inicializaLivro(Long livroId) {
		Livro livro = livroDao.recuperarLivroPeloId(livroId);
		return livro;
	}

	private Cliente verificaExistenciaCliente(Long clienteId) {
		Cliente cliente = clienteDao.recuperaClientePelo(clienteId);
		return cliente;
	}
}