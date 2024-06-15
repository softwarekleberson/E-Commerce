package br.com.engenharia.projeto.ProjetoFinal.services.cliente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha.CriptografaSenhaCliente;
import br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovoCliente.IStrategyCliente;
import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.ClienteDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.CobrancaDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.EntregaDao;
import br.com.engenharia.projeto.ProjetoFinal.dao.log.LogDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosCadastroCliente;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DetalharCliente;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.entidade.log.Log;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ServiceCliente {
	
    @Autowired
    private ClienteDao daoCliente;

    @Autowired
    private EntregaDao daoEntrega;

    @Autowired
    private CobrancaDao daoCobranca;
    
    @Autowired
    private LogDao daoLog;

    @Autowired
    private List<IStrategyCliente> validadores;

    @Autowired
    private CriptografaSenhaCliente criptografiaSenha;

    @Transactional
    public DetalharCliente criar(@Valid DadosCadastroCliente dados) {
        
    	try {
    		
			Cliente cliente = new Cliente(dados);
			validadores.forEach(v -> v.processar(cliente));
			criptografiaSenha.processar(cliente);
			daoCliente.salvar(cliente);

			List<Cobranca> cobrancas = criarCobrancas(dados, cliente);
			List<Entrega> entregas = criarEntregas(dados, cliente);
			atribuirIdCliente(cobrancas, entregas, cliente.getId());

			salvarCobrancas(cobrancas);
			salvarEntregas(entregas);
			
			Log log = new Log(cliente.getId());
			daoLog.save(log);
			
			return new DetalharCliente(cliente);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		return null;
    }

    private List<Cobranca> criarCobrancas(DadosCadastroCliente dados, Cliente cliente) {        
    	return dados.cobranca().stream()
                .map(Cobranca::new)
                .collect(Collectors.toList());        
    }

    private List<Entrega> criarEntregas(DadosCadastroCliente dados, Cliente cliente) {
        return dados.entrega().stream()
                .map(Entrega::new)
                .collect(Collectors.toList());
    }

    private void atribuirIdCliente(List<Cobranca> cobrancas, List<Entrega> entregas, Long clienteId) {
    	cobrancas.forEach(c -> c.setCliente(clienteId));
        entregas.forEach(e -> e.setCliente(clienteId));
    }

    private void salvarCobrancas(List<Cobranca> cobrancas) {
    	cobrancas.forEach(daoCobranca::salvar);
    }

    private void salvarEntregas(List<Entrega> entregas) {
    	entregas.forEach(daoEntrega::salvar);
    }
}