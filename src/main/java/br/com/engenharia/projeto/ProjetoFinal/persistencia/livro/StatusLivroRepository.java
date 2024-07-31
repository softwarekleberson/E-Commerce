package br.com.engenharia.projeto.ProjetoFinal.persistencia.livro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.statusLivro.StatusLivro;

public interface StatusLivroRepository extends JpaRepository<StatusLivro, Long>{

}
