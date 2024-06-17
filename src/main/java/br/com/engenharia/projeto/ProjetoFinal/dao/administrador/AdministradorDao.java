package br.com.engenharia.projeto.ProjetoFinal.dao.administrador;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.administrador.AdministradorRepository;

@Service
public class AdministradorDao implements IdaoAdministrador{

	private AdministradorRepository repository;
	
	public AdministradorDao(AdministradorRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void salvar(Administrador adminstrador) {
		repository.save(adminstrador);
	}
	
	public Administrador pegaAdministradorAleatorio() {
		return repository.findRandomAdministrador();
	}

	@Override
	public void deletar(Long id) {
		Optional<Administrador> existeAdministrador = repository.findById(id);
		if(existeAdministrador.isEmpty()) {
			throw new IllegalArgumentException("Id administrador invalido");
		}
		repository.deleteById(id);
	}
}
