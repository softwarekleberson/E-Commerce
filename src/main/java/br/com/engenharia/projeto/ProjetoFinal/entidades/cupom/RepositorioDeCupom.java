package br.com.engenharia.projeto.ProjetoFinal.entidades.cupom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepositorioDeCupom {

	public void salvar(Cupom cupom);
	public Page listarCuponsDosClientes(Long clienteId, Pageable pageable);

}
