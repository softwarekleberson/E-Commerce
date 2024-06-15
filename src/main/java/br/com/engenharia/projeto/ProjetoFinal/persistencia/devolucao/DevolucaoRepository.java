package br.com.engenharia.projeto.ProjetoFinal.persistencia.devolucao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao.Devolucao;

public interface DevolucaoRepository extends JpaRepository<Devolucao, Long>{

}
