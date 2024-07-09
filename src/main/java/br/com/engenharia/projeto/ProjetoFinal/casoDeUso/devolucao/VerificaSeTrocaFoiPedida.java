
package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.devolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.pedido.PedidoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.DevolucaoFoiPedidaOUNAO;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

@Service
public class VerificaSeTrocaFoiPedida implements IstrategyDevolucao{

	@Autowired
	private PedidoDao pedidoDao;
	
    private static final String MENSAGEM_ERRO = "Devolução não pedida.";
	
	@Override
	public void processar(DadosCadastroDevolucao dados) {
		var pedido = pedidoDao.devolvePedidoPeloCodigo(dados.codigoPedido());
		if(pedido.getTrocaDevolucao() != DevolucaoFoiPedidaOUNAO.DEVOLUCAO_PEDIDO) {
			throw new ValidacaoExcepetion(MENSAGEM_ERRO);
		}
	}
}