package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.devolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.pedido.PedidoDao;
import br.com.engenharia.projeto.ProjetoFinal.dominio.pedido.Pedido;
import br.com.engenharia.projeto.ProjetoFinal.dominio.pedido.RepositorioDePedido;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;
import jakarta.validation.ValidationException;

@Service
public class VerificaStatusAtivoCliente implements IstrategyDevolucao{

	@Autowired
	private RepositorioDePedido repositorioDePedido;
	
    private static final String MENSAGEM_ERRO = "Cliente não está ativo.";
	
	@Override
	public void processar(DadosCadastroDevolucao dados) {
		Pedido pedido = repositorioDePedido.devolvePedidoPeloCodigo(dados.codigoPedido());
		if(pedido.getCarrinho().getCliente().getAtivo() != true) {
			throw new ValidacaoExcepetion(MENSAGEM_ERRO);
		}
	}
}