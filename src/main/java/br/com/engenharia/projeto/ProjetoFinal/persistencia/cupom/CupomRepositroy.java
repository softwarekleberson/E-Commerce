package br.com.engenharia.projeto.ProjetoFinal.persistencia.cupom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cupom.Cupom;

public interface CupomRepositroy extends JpaRepository<Cupom, Long>{

	Page<Cupom> findByCliente_Id(Long clienteId, Pageable pageable);

}
