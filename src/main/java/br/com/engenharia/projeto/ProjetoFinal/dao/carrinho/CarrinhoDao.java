package br.com.engenharia.projeto.ProjetoFinal.dao.carrinho;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.carrinho.Carrinho;
import br.com.engenharia.projeto.ProjetoFinal.dominio.carrinho.CarrinhoNaoEncontradoExcecao;
import br.com.engenharia.projeto.ProjetoFinal.dominio.carrinho.RepositorioDeCarrinho;
import br.com.engenharia.projeto.ProjetoFinal.dominio.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.carrinho.DetalhamentoCarrinho;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.carrinho.CarrinhoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.ClienteRepository;

@Service
public class CarrinhoDao implements RepositorioDeCarrinho{

	private CarrinhoRepository carrinhoRepository;
	
	private ClienteRepository clienteRepository;
	
	public CarrinhoDao(CarrinhoRepository carrinhoRepository) {
		this.carrinhoRepository = carrinhoRepository;
	}

	@Override
	public void salvar(Carrinho carrinho) {
		carrinhoRepository.save(carrinho);
	}

	 public Page<DetalhamentoCarrinho> listar(Long clienteId, Pageable page) {
	        Optional<Cliente> cliOpt = clienteRepository.findById(clienteId);
	        if (cliOpt.isEmpty()) {
	            throw new CarrinhoNaoEncontradoExcecao("Id do cliente não existe");
	        }
	        return carrinhoRepository.findDetalhesCarrinhoByClienteId(clienteId, page);
	 }
}