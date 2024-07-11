package br.com.engenharia.projeto.ProjetoFinal.persistencia.livro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.categoria.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
