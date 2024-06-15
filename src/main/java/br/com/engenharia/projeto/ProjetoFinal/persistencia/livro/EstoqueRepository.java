package br.com.engenharia.projeto.ProjetoFinal.persistencia.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.engenharia.projeto.ProjetoFinal.entidade.estoque.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

}
