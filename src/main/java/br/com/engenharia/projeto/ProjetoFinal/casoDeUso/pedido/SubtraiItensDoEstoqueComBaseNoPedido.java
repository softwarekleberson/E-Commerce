package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.estoque.RepositorioDeEstoque;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;

@Service
public class SubtraiItensDoEstoqueComBaseNoPedido implements IStrategyPedido{

	@Autowired
	private RepositorioDeEstoque repositorioDeEstoque;
	
	@Override
	public void processar(DadosCadastroPedido dados) {
		
	}
}