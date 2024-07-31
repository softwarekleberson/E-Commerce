package br.com.engenharia.projeto.ProjetoFinal.dao.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.log.Log;
import br.com.engenharia.projeto.ProjetoFinal.dominio.log.RepositorioDeLog;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.log.LogRepository;

@Service
public class LogDao implements RepositorioDeLog {

	@Autowired
	private LogRepository logRepository;
	
	public LogDao(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@Override
	public String save(Log entidade) {
		this.logRepository.save(entidade);
		return null;
	}
}