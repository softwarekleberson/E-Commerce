package br.com.engenharia.projeto.ProjetoFinal.entidade.livro;

import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.ValidacaoExcepetion;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class Edicao {

	private String edicao;
	
	public Edicao(String edicao) {
		setEdicao(edicao);
	}
	
	public void setEdicao(String edicao) {
		if(edicao.trim() == null) {
			throw new ValidacaoExcepetion("Edição não deve ser nula");
		}
		this.edicao = edicao.trim().toLowerCase();
	}
}
