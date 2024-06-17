package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.devolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.pedido.PedidoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.ValidacaoExcepetion;

@Service
public class VerificaCodigoPedido implements IstrategyDevolucao{

	@Autowired
	private PedidoDao pedidoDao;
	
	@Override
	public void processar(DadosCadastroDevolucao dados) {
		if(!pedidoDao.verificaCodigoPedido(dados.codigoPedido())) {
			throw new ValidacaoExcepetion("Codigo produto incorreto");
		}
	}
}