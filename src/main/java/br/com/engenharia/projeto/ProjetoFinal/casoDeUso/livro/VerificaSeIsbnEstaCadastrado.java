package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.livro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.livro.LivroDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

@Service
public class VerificaSeIsbnEstaCadastrado implements IstrategyLivro{

	@Autowired
	private LivroDao livroDao;
	
    private static final String MENSAGEM_ERRO = "Este livro foi cadastrado anteriormente.";
	
	@Override
	public void validar(DadosCadastroLivro dados) {
		Optional<Livro> livro = livroDao.isbnCadastradoAnteriormente(dados.isbn());
		if(livro.isPresent()) {
			throw new ValidacaoExcepetion(MENSAGEM_ERRO);
		}
	}
}