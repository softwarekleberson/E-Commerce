package br.com.engenharia.projeto.ProjetoFinal.persistencia.administrador;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Email;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

	Optional<Administrador> findByEmail(Email email);

}
