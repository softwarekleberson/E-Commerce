package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.devolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.pedido.RepositorioDePedido;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosCadastroDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

@Service
public class VerificaCodigoPedido implements IstrategyDevolucao{

	@Autowired
	private RepositorioDePedido repositorioDePedido;
	
    private static final String MENSAGEM_ERRO = "Codigo produto incorreto.";
	
	@Override
	public void processar(DadosCadastroDevolucao dados) {
		if(!repositorioDePedido.verificaCodigoPedido(dados.codigoPedido())) {
			throw new ValidacaoExcepetion(MENSAGEM_ERRO);
		}
	}
}