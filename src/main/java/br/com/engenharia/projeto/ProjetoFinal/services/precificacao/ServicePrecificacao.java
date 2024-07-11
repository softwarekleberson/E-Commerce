package br.com.engenharia.projeto.ProjetoFinal.services.precificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.livro.PrecificacaoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Precificacao.DadosDetalhamentoPrecificacao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.estoque.DadosCadastroPrecificacao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.precificacao.Precificacao;
import jakarta.validation.Valid;

@Service
public class ServicePrecificacao {

	@Autowired
	private PrecificacaoDao precificacaoDao;
	
	public DadosDetalhamentoPrecificacao criar(@Valid DadosCadastroPrecificacao dados) {

	    Precificacao precificacao = new Precificacao(dados);
	    precificacaoDao.salvar(precificacao);
	    return new DadosDetalhamentoPrecificacao(precificacao);
	}
}
