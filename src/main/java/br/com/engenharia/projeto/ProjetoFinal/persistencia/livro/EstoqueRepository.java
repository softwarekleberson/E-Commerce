package br.com.engenharia.projeto.ProjetoFinal.persistencia.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.engenharia.projeto.ProjetoFinal.entidades.estoque.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
	
	@Query("SELECT SUM(e.quantidade) FROM Estoque e WHERE e.livro.id = :livroId")
    Integer findTotalQuantidadeByLivroId(@Param("livroId") Long livroId);
}
