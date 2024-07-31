package br.com.engenharia.projeto.ProjetoFinal.dao.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.carrinho.Item;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.carrinho.ItemRepository;

@Service
public class ItemDao implements RepositorioDeItem{

	@Autowired
	private ItemRepository repository;
	
	public ItemDao(ItemRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void salvar(Item itens) {
		this.repository.save(itens);
	}
}
