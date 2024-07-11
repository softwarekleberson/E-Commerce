package br.com.engenharia.projeto.ProjetoFinal.services.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.livro.StatusLivroDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.livro.LivroDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroStatusLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.statusLivro.StatusLivro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;

@Service
public class ServiceAtivarInativarLivro {

	@Autowired
	private LivroDao livroDao;
	
	@Autowired
	private StatusLivroDao statusLivroDao;
	
	@Autowired
	private LivroRepository livroRepository;
	
	public void delecaoLogica(DadosCadastroStatusLivro dados) {
		Livro livro = livroRepository.getReferenceById(dados.idLivro());
		livro.setAtivo(false);
		
		StatusLivro inativacao = new StatusLivro(null,
											   dados.justificativa(),
											   dados.categoria(),
											   livro);
				
		livroDao.salvar(livro);
		statusLivroDao.salvar(inativacao);
	}
	
	public void ativarLogica(DadosCadastroStatusLivro dados) {
		Livro livro = livroRepository.getReferenceById(dados.idLivro());
		livro.setAtivo(true);
		
		StatusLivro ativacao = new StatusLivro(null,
				   								 dados.justificativa(),
				                                 dados.categoria(),
				                                 livro);
		livroDao.salvar(livro);	
		statusLivroDao.salvar(ativacao);

	}
}