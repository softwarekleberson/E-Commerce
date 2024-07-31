package br.com.engenharia.projeto.ProjetoFinal.persistencia.livro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.categoria.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
