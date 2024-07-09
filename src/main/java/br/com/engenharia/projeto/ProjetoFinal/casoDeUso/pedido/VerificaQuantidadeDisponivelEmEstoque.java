package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.livro.EstoqueDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

@Service
public class VerificaQuantidadeDisponivelEmEstoque implements IStrategyPedido{

	@Autowired
	private EstoqueDao estoqueDao;
	
    private static final String MENSAGEM_ERRO = "Estoque não comporta seu pedido.";
	
	@Override
	public void processar(DadosCadastroPedido dados) {
		var estoqueItem = estoqueDao.verificaDisponibilidadeLivro(dados.idLivro());
		if(dados.quantidade() > estoqueItem) {
			throw new ValidacaoExcepetion(MENSAGEM_ERRO);
		}
	}
}