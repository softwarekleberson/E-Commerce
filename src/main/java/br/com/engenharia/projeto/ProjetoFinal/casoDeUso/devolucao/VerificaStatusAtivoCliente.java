package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.devolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.ClienteDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import jakarta.validation.ValidationException;

@Service
public class VerificaStatusAtivoCliente implements IstrategyDevolucao{

	@Autowired
	private ClienteDao clienteDao;
	
	@Override
	public void processar(DadosCadastroDevolucao dados) {
		var clienteAtivo = clienteDao.pegaClienteAtivo(dados.idCliente());
		if(clienteAtivo.getAtivo() != true) {
			throw new ValidationException("Cliente não está ativo");
		}
	}
}