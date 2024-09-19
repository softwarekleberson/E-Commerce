package br.com.engenharia.projeto.ProjetoFinal.dao.item;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosAtualizacaoItem;
import br.com.engenharia.projeto.ProjetoFinal.dtos.item.DadosDetalhamentoItem;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.PedidoNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.itemPedido.ItemPedidoRepository;

@Service
public class ItemDao implements RepositorioDeItem{

	@Autowired
	private ItemPedidoRepository repository;
	
	public ItemDao(ItemPedidoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void salvar(Item item) {
		repository.save(item);
	}

	@Override
	public DadosDetalhamentoItem upadate(Long id, DadosAtualizacaoItem dados) {
		Optional<Item> opDataItem = repository.findById(id);
		int quantidade = dados.quantidade();
		if(opDataItem.isPresent()) {
			Item item = opDataItem.get();
			
			if(quantidade > 0) {
				item.setQuantidade(dados.quantidade());
				BigDecimal upateSubtotal = item.getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade));
				item.setSubtotal(upateSubtotal);
			
			}else {
				throw new PedidoNaoEncontradoExcecao("Quantidade inferior ao permitido");
			}
			
			repository.save(item);
			return new DadosDetalhamentoItem(item);
		}
		return null;
	}

	@Override
	public void deletar(Long id) {
		Optional<Item> item = repository.findById(id);
		if(item.isEmpty()) {
			throw new PedidoNaoEncontradoExcecao("Item não encontrado");
		}
		repository.deleteById(id);
	}
}