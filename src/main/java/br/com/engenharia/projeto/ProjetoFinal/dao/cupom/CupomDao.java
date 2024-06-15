package br.com.engenharia.projeto.ProjetoFinal.dao.cupom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosDetalhamentoCupom;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosDetalhamentoCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cartao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom.CupomRepositroy;

@Service
public class CupomDao implements IdaoCupom{

	@Autowired
	private CupomRepositroy repository;
	
	public CupomDao(CupomRepositroy repository) {
		this.repository = repository;
	}
	
	@Override
	public void salvar(Cupom cupom) {
		this.repository.save(cupom);
	}
	
	public Page listarCuponsDosClientes(Long clienteId, Pageable pageable) {
		 Page<Cupom> cuponsPage = repository.findByCliente_Id(clienteId, pageable);	        
	     if(cuponsPage.isEmpty()) {
	    	 throw new IllegalArgumentException("Id incorreto");
	     }
		 return cuponsPage.map(DadosDetalhamentoCupom::new);
	}
}
