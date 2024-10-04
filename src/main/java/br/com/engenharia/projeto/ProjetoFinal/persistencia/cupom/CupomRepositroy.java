package br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;
import jakarta.transaction.Transactional;

public interface CupomRepositroy extends JpaRepository<Cupom, Long>{

	Page<Cupom> findByCliente_Id(Long clienteId, Pageable pageable);

	@Query("SELECT c FROM Cupom c WHERE c.id = :id AND c.status = TRUE")
	Optional<Cupom> buscarCupomPorId(@Param("id") String id);

	@Modifying
	@Transactional
	@Query("DELETE FROM Cupom c WHERE c.id IN :ids")
	void deleteCupomPosUso(@Param("ids") List<String> ids);
}
