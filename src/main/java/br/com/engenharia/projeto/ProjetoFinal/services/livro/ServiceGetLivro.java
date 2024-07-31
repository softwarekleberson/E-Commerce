package br.com.engenharia.projeto.ProjetoFinal.services.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.Livro;
import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.RepositorioDeLivro;
import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.statusLivro.Categoria;
import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.statusLivro.RepositorioDeInativacao;
import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.statusLivro.StatusLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivro;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

@Service
public class ServiceGetLivro {

	@Autowired
	private RepositorioDeLivro repositorioDeLivro;
	
	@Autowired
	private RepositorioDeInativacao repositorioDeInativacao;
	
	public static final int QUANTIDADE_MINIMA_LIVRO = 0;
	
	public Page<DadosDetalhamentoLivro> listarLivros(Pageable pageable) {
        Page<DadosDetalhamentoLivro> livros = repositorioDeLivro.listarLivros(pageable);
                
        if (livros.isEmpty()) {
            throw new ValidacaoExcepetion("Não há livros disponíveis");
        }
        
        return livros;
    }

	public Livro listarLivroExpecifico(Long id) {
		var livro = repositorioDeLivro.recuperarLivroPeloId(id);
		if(livro.getEstoque().getQuantidade() <= QUANTIDADE_MINIMA_LIVRO) {
			StatusLivro statusLivro = new StatusLivro();
			statusLivro.setId(null);
			statusLivro.setCategoria(Categoria.FORA_MERCADO);
			statusLivro.setJustificativa("Sem estoque");
			statusLivro.setLivro(livro);
			repositorioDeInativacao.salvar(statusLivro);
		}
		return livro;
	}
}