package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.livro.EstoqueDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;
import jakarta.validation.ValidationException;

@Service
public class VerificaQuantidadeDisponivelEmEstoque implements IStrategyPedido{

	@Autowired
	private EstoqueDao estoqueDao;
	
	@Override
	public void processar(DadosCadastroPedido dados) {
		var estoqueItem = estoqueDao.verificaDisponibilidadeLivro(dados.idLivro());
		if(dados.quantidade() > estoqueItem) {
			throw new ValidationException("Estoque não comporta seu pedido");
		}
	}
}