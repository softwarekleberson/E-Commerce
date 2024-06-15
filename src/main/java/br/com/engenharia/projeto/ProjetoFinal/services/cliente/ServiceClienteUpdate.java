package br.com.engenharia.projeto.ProjetoFinal.services.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha.CriptografaSenhaCliente;
import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha.IStrategySenhaAtualizadaCliente;
import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.ClienteDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.CobrancaDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.EntregaDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.log.LogDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoCliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoSenha;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosDetalhamentoCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidade.log.Log;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.cliente.ClienteRepository;
import jakarta.validation.Valid;

@Service
public class ServiceClienteUpdate {
	
	@Autowired
	private ClienteRepository clienteRepositoy;
	
    @Autowired
    private ClienteDao daoCliente;
    
    @Autowired
    private CobrancaDao cobrancaDao;
    
    @Autowired
    private EntregaDao entregaDao;

    @Autowired
    private LogDao daoLog;
    
    @Autowired
    private List<IStrategySenhaAtualizadaCliente> validadores;
    
    @Autowired
    private List<CriptografaSenhaCliente> validaoresCriptografiaSenha;

	public DadosDetalhamentoCliente atualizarCliente(@Valid DadosAtualizacaoCliente dados) {
		Optional<Cliente> cliente = clienteRepositoy.findById(dados.idCliente());
		if(cliente.isEmpty()) {
			throw new IllegalArgumentException("Id incorreto");
		}
		
		daoCliente.alterarCliente(cliente.get().getId(), dados); 
		Log log = new Log(dados.idCliente());
		daoLog.save(log);
		
		return null;
	}
	
	public DadosDetalhamentoCliente atualizarSenha(@Valid DadosAtualizacaoSenha dados) {
		Optional<Cliente> cliente = clienteRepositoy.findById(dados.idCliente());
		if(cliente.isEmpty()) {
			throw new IllegalArgumentException("Id incorreto");
		}
		
		validadores.forEach(v-> v.processar(dados));
		validaoresCriptografiaSenha.forEach(v-> v.processar(cliente.get()));
		daoCliente.alterarSenha(dados.idCliente(), cliente.get().devolveSenhaCriptografada());
		
		Log log = new Log(dados.idCliente());
		daoLog.save(log);
		
		return null;
	}
}