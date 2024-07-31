
package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.devolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.pedido.PedidoDao;
import br.com.engenharia.projeto.ProjetoFinal.dominio.pedido.DevolucaoFoiPedidaOUNAO;
import br.com.engenharia.projeto.ProjetoFinal.dominio.pedido.RepositorioDePedido;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

@Service
public class VerificaSeTrocaFoiPedida implements IstrategyDevolucao{

	@Autowired
	private RepositorioDePedido repositorioDePedido;
	
    private static final String MENSAGEM_ERRO = "Devolução não pedida.";
	
	@Override
	public void processar(DadosCadastroDevolucao dados) {
		var pedido = repositorioDePedido.devolvePedidoPeloCodigo(dados.codigoPedido());
		if(pedido.getTrocaDevolucao() != DevolucaoFoiPedidaOUNAO.DEVOLUCAO_PEDIDO) {
			throw new ValidacaoExcepetion(MENSAGEM_ERRO);
		}
	}
}