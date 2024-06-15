package br.com.engenharia.projeto.ProjetoFinal.persistencia.livro;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	Page<Livro> findAllByAtivoTrue(Pageable paginacao);

	BigDecimal findPrecoById(Long idLivro);

}
