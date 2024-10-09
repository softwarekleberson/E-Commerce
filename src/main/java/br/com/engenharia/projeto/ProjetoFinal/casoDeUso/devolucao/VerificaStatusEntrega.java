package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.devolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.pedido.PedidoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.RepositorioDePedido;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.StatusPedido;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoException;

@Service
public class VerificaStatusEntrega implements IstrategyDevolucao{

	@Autowired
	private RepositorioDePedido repositorioDePedido;
	
    private static final String MENSAGEM_ERRO = "Não se pode devolver produto não entregue.";
	
	@Override
	public void processar(DadosCadastroDevolucao dados) {
		var pedido = repositorioDePedido.devolvePedidoPeloCodigo(dados.codigoPedido());
		if(pedido.getStatusEntrega() != StatusPedido.RECEBIDO) {
			throw new ValidacaoException(MENSAGEM_ERRO);
		}
	}
}