package br.com.engenharia.projeto.ProjetoFinal.services.livro;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.livro.LivroDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivro;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Autor;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Categoria;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Imagens;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.AutorRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.CategoriaRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.ImagensProdutoRepository;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;
import jakarta.validation.Valid;

@Service
public class ServiceLivro {
	
	@Autowired
    private LivroDao livroDao;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private ImagensProdutoRepository produtoRepository;
	
    public DadosDetalhamentoLivro criar(@Valid DadosCadastroLivro dados) {

        Livro livro = new Livro(dados);
        livroDao.salvar(livro);

        List<Categoria> categorias = criarCategoria(dados);
        List<Autor> autores = criarAutor(dados);
        List<Imagens> imagens = criarImagem(dados, livro);

        categorias.forEach(categoria -> categoria.addLivro(livro));
        autores.forEach(autor -> autor.addLivro(livro));
        
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

	public DadosDetalhamentoLivro atualizarLivro(@Valid DadosAtualizarLivro dados) {
		Optional<Livro> livroExiste = livroRepository.findById(dados.id());
		if(livroExiste.isEmpty()) {
			throw new IllegalArgumentException("Id do livro não existe");
		}
		
		DadosDetalhamentoLivro livro = new LivroDao(livroRepository, categoriaRepository, autorRepository, produtoRepository).alterar(dados);
		return livro;
	}   
}
