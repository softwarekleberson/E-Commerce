package br.com.engenharia.projeto.ProjetoFinal.services.livro;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.livro.IstrategyLivro;
import br.com.engenharia.projeto.ProjetoFinal.dao.livro.LivroDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.autor.Autor;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.categoria.Categoria;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.imagem.Imagens;
import jakarta.validation.Valid;

@Service
public class ServiceInsertLivro {
	
	@Autowired
    private LivroDao livroDao;
	
	@Autowired
	private List<IstrategyLivro> validacoes;
	
    public DadosDetalhamentoLivro criar(@Valid DadosCadastroLivro dados) {

    	validacoes.forEach(v -> v.validar(dados));
        Livro livro = new Livro(dados);
        livroDao.salvar(livro);

        List<Categoria> categorias = criarCategoria(dados);
        List<Autor> autores = criarAutor(dados);
        List<Imagens> imagens = criarImagem(dados, livro);
        
        return new DadosDetalhamentoLivro(livro);
    }
    
    private List<Imagens> criarImagem(@Valid DadosCadastroLivro dados, Livro livro) {
        return dados.imagem().stream()
                .map(dadosImagem -> {
                    Imagens imagem = new Imagens(dadosImagem);
                    imagem.setLivro(livro); 
                    return imagem;
                })
                .collect(Collectors.toList());
    }

    private List<Categoria> criarCategoria(@Valid DadosCadastroLivro dados) {
        return dados.categoria().stream()
                .map(Categoria::new)
                .collect(Collectors.toList());
    }
    
    private List<Autor> criarAutor(@Valid DadosCadastroLivro dados) {
        return dados.autor().stream()
                .map(Autor::new)
                .collect(Collectors.toList());
    }
}