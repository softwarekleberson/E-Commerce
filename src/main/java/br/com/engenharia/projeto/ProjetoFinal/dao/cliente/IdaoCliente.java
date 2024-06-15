package br.com.engenharia.projeto.ProjetoFinal.dao.cliente;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;

public interface IdaoCliente {

	public void salvar(Cliente entidade);
	public void alterarCliente(Long id, DadosAtualizacaoCliente dados);
	void alterarSenha(Long id, String senhaCriptografada);
	public Optional<Cliente> consultar(Cliente entidade);
	public void deletar(Long id);
	Page pegaTodosClientes(Pageable page);
	public Optional<Cliente> verificaExistenciaClienteId(Long id);
	public Cliente recuperaClientePelo(Long id);
}
