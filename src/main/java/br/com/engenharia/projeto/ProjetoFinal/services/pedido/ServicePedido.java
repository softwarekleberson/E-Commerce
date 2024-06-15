package br.com.engenharia.projeto.ProjetoFinal.services.pedido;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.pedido.PedidoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosDetalhamentoPedido;
import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.carrinho.CarrinhoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;
import jakarta.validation.Valid;

@Service
public class ServicePedido {

	@Autowired
	private PedidoDao pedidoDao;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	public DadosDetalhamentoPedido criar(@Valid DadosCadastroPedido dados) {
		
		try {
			var livro = livroRepository.getReferenceById(dados.idLivro());
			var carrinho = carrinhoRepository.getReferenceById(dados.idCarrinho());
			
			BigDecimal valorTotal = livro.getPreco().multiply(new BigDecimal(dados.quantidade()));
			UUID geradorUnico = UUID.randomUUID();
			String codigoPedido = geradorUnico.toString();
			
			Pedido pedido = new Pedido(dados);
			pedido.setValorTotal(valorTotal);
			pedido.setLivro(livro);
			pedido.setCarrinho(carrinho);
			pedido.setCodigoPedido(codigoPedido);
			
			pedidoDao.salvar(pedido);
			return new DadosDetalhamentoPedido(pedido);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}