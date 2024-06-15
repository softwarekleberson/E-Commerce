package br.com.engenharia.projeto.ProjetoFinal.services.estoque;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.livro.EstoqueDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.estoque.DadosCadastroEstoque;
import br.com.engenharia.projeto.ProjetoFinal.dtos.estoque.DadosDetalhamentoEstoque;
import br.com.engenharia.projeto.ProjetoFinal.entidade.estoque.Estoque;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;
import jakarta.validation.Valid;

@Service
public class ServiceEstoque {

	@Autowired
	private EstoqueDao estoqueDao;
	
	@Autowired
	private LivroRepository repository;
	
	public DadosDetalhamentoEstoque criar(@Valid DadosCadastroEstoque dados) {

		Optional<Livro> existeLivro = repository.findById(dados.idLivro());
		if(existeLivro.isEmpty()) {
			throw new IllegalArgumentException("Id do livro incorreto");
		}
		
	    Estoque estoque = new Estoque(dados);
	    estoqueDao.salvar(estoque);
	    return new DadosDetalhamentoEstoque(estoque);
	}
}
