package br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;

public interface CupomRepositroy extends JpaRepository<Cupom, Long>{

	Page<Cupom> findByCliente_Id(Long clienteId, Pageable pageable);

	@Query("SELECT c FROM Cupom c WHERE c.id = :id")
	Optional<Cupom> buscarCupomPorId(@Param("id") String id);
}
