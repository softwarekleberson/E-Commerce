package br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento;

import java.util.HashSet;
import java.util.Set;

import br.com.engenharia.projeto.ProjetoFinal.entidades.carrinho.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cartao.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;

public class Pagamento {

	private Set<Item> itens = new HashSet<>();
	private Entrega entrega;
	private Set<Cartao> cartao = new HashSet<>();
	private Set<Cupom> cupuns = new HashSet<>();
	private StatusCompra status;
	
}
