package br.com.engenharia.projeto.ProjetoFinal.persistencia.livro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.autor.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

}
