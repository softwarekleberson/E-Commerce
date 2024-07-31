package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.estoque.RepositorioDeEstoque;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

@Service
public class VerificaQuantidadeDisponivelEmEstoque implements IStrategyPedido{

	@Autowired
	private RepositorioDeEstoque repositorioDeEstoque;
	
    private static final String MENSAGEM_ERRO = "Estoque não comporta seu pedido.";
	
	@Override
	public void processar(DadosCadastroPedido dados) {
		var estoqueItem = repositorioDeEstoque.verificaDisponibilidadeLivro(dados.idLivro());
		if(dados.quantidade() > estoqueItem) {
			throw new ValidacaoExcepetion(MENSAGEM_ERRO);
		}
	}
}