package br.com.engenharia.projeto.ProjetoFinal.entidade.pagamento;

import java.util.HashSet;
import java.util.Set;

import br.com.engenharia.projeto.ProjetoFinal.entidade.carrinho.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cupom.Cupom;

public class Pagamento {

	private Set<Item> itens = new HashSet<>();
	private Entrega entrega;
	private Set<Cartao> cartao = new HashSet<>();
	private Set<Cupom> cupuns = new HashSet<>();
	private StatusCompra status;
	
}
