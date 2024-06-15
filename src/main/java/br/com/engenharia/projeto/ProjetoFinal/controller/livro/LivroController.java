package br.com.engenharia.projeto.ProjetoFinal.controller.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.engenharia.projeto.ProjetoFinal.dao.livro.LivroDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosAtualizarLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosCadastroLivro;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Livro.DadosDetalhamentoLivro;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.livro.LivroRepository;
import br.com.engenharia.projeto.ProjetoFinal.services.livro.ServiceLivro;
import jakarta.validation.Valid;

@RestController
@RequestMapping("livro")
@CrossOrigin(origins = "*")
public class LivroController {

	@Autowired
	private ServiceLivro service;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLivro dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criar(dados);
		var uri = uriBuilder.path("/livro/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosDetalhamentoLivro>> listarLivrosAtivos(@PageableDefault(size = 15) Pageable paginacao){
		var page = new LivroDao(livroRepository).listarLivros(paginacao);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalharLivro(@PathVariable Long id) {
		var livro = new LivroDao(livroRepository).listarLivroExpecifico(id);
		return ResponseEntity.ok(livro);
	}
	
	@PutMapping
	public ResponseEntity atualizarLivro(@RequestBody @Valid DadosAtualizarLivro dados) {
		DadosDetalhamentoLivro livro = service.atualizarLivro(dados);
		return ResponseEntity.ok(livro);
	}
}
