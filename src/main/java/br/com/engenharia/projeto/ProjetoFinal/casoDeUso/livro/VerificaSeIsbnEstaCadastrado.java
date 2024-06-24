package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.livro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.livro.LivroDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;
import jakarta.validation.ValidationException;

@Service
public class VerificaSeIsbnEstaCadastrado implements IstrategyLivro{

	@Autowired
	private LivroDao livroDao;
	
	@Override
	public void validar(DadosCadastroLivro dados) {
		Optional<Livro> livro = livroDao.isbnCadastradoAnteriormente(dados.isbn());
		if(livro.isPresent()) {
			throw new ValidationException("Este livro foi cadastrado anteriormente");
		}
	}
}