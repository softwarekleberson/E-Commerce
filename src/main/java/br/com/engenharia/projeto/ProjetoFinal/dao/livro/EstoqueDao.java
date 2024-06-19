package br.com.engenharia.projeto.ProjetoFinal.dao.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidade.estoque.Estoque;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.EstoqueRepository;

@Service
public class EstoqueDao implements IdaoEstoque{

	@Autowired
	private EstoqueRepository repository;
	
	public EstoqueDao(EstoqueRepository repository) {
		this.repository = repository;
	}

	@Override
	public void salvar(Estoque estoque) {
		repository.save(estoque);
	}

	public int verificaDisponibilidadeLivro(Long idLivro) {
		var quantidadeDisponivel = repository.findTotalQuantidadeByLivroId(idLivro);
		return quantidadeDisponivel;
	}
}