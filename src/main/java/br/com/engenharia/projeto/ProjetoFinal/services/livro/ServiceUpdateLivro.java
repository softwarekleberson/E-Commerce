package br.com.engenharia.projeto.ProjetoFinal.services.livro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.RepositorioDeLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivro;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;
import jakarta.validation.Valid;

@Service
public class ServiceUpdateLivro {

	@Autowired
    private RepositorioDeLivro repositorioDeLivro;
	
	@Autowired
	private LivroRepository livroRepository;
		
	public DadosDetalhamentoLivro atualizarLivro(@Valid DadosAtualizarLivro dados) {		
		
		Optional<Livro> infoLivro = livroRepository.findById(dados.id());
		if(infoLivro.isEmpty()) {
			throw new ValidacaoExcepetion("Id do livro não existe");
		}
		
		if(infoLivro.get().getIsbn().equals(dados.isbn())) {
			throw new ValidacaoExcepetion("Isbn registrado anteriormente");
		}
		
		if(infoLivro.get().getCodigoBarra().equals(dados.isbn())) {
			throw new ValidacaoExcepetion("Codigo de barra registrado anteriormente");
		}
		
		DadosDetalhamentoLivro livro = repositorioDeLivro.alterar(dados);
		return livro;
	}  
}