package br.com.engenharia.projeto.ProjetoFinal.dao.cliente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosAtualizacaoCartao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosDetalhamentoCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.CartaoRepository;

@Service
public class CartaoDao implements IdaoCartao{

	@Autowired
	private CartaoRepository repository;
	
	public CartaoDao(CartaoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public String salvar(Cartao entidade) {
		verificaCartaoPrincipalCliente(entidade);
		repository.save(entidade);
		return null;
	}
	
	public Cartao alterar(Long idCartao, DadosAtualizacaoCartao dados) {
		Optional<Cartao> opDataBaseCartao = repository.findById(idCartao);
		
		if(opDataBaseCartao.isPresent()) {
			Cartao cartao = opDataBaseCartao.get();
			
			if(dados.nomeImpresso() != null) {
				cartao.setNomeImpresso(dados.nomeImpresso());
			}
			
			if(dados.codigo() != null) {
				cartao.setCodigo(dados.codigo());
			}
			
			if(dados.principal() == true || dados.principal() == false) {
				cartao.setPrincipal(dados.principal());
				verificaCartaoPrincipalClienteUpdate(cartao);
			}
			
			repository.save(cartao);
			return cartao;
		} 
	
		return null;
	}

	@Override
	public void deletar(Long id) {
		Optional<Cartao> cartao = repository.findById(id);
		if(cartao.isEmpty()) {
			throw new IllegalArgumentException("Id cartão incorreto");
		}
		repository.deleteById(id);
	}

	public Page listarCartaosDoCliente(Long clienteId, Pageable pageable) {
		 Page<Cartao> cartoesPage = repository.findByCliente_Id(clienteId, pageable);	        
	     if(cartoesPage.isEmpty()) {
	    	 throw new IllegalArgumentException("Id incorreto");
	     }
		 return cartoesPage.map(DadosDetalhamentoCartao::new);
	}
	
	private void verificaCartaoPrincipalClienteUpdate(Cartao entidade) {
	    if (entidade.isPrincipal()) {
	        repository.atualizarCartoesNaoPrincipalClienteExceptIdAndPrincipal(entidade.getCliente().getId(), entidade.getId());
	    }
	}
	
	private void verificaCartaoPrincipalCliente(Cartao entidade) {
	    if (entidade.isPrincipal()) {
	        repository.atualizarCartoesNaoPrincipalCliente(entidade.getCliente().getId());
	    }
	}
}