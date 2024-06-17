package br.com.engenharia.projeto.ProjetoFinal.dao.devolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.Devolucao;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.devolucao.DevolucaoRepository;

@Service
public class DevolucaoDao implements IdaoDevolucao{

	@Autowired
	private DevolucaoRepository devolucao;
	
	@Override
	public void salvar(Devolucao devolucao) {
		this.devolucao.save(devolucao);
	}
}