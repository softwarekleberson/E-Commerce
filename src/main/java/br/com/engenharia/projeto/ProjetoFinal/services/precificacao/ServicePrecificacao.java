package br.com.engenharia.projeto.ProjetoFinal.services.precificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.precificacao.Precificacao;
import br.com.engenharia.projeto.ProjetoFinal.dominio.livro.precificacao.RepositorioDePrecificacao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Precificacao.DadosDetalhamentoPrecificacao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.estoque.DadosCadastroPrecificacao;
import jakarta.validation.Valid;

@Service
public class ServicePrecificacao {

	@Autowired
	private RepositorioDePrecificacao repositorioDePrecificacao;
	
	public DadosDetalhamentoPrecificacao criar(@Valid DadosCadastroPrecificacao dados) {

	    Precificacao precificacao = new Precificacao(dados);
	    repositorioDePrecificacao.salvar(precificacao);
	    return new DadosDetalhamentoPrecificacao(precificacao);
	}
}