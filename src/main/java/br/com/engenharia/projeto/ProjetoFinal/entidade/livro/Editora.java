package br.com.engenharia.projeto.ProjetoFinal.entidade.livro;

import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.ValidacaoExcepetion;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Editora {

	private String editora;
	
	public Editora(String editora) {
		setEditora(editora);
	}
	
	public void setEditora(String editora) {
		if(editora.trim() == null) {
			throw new ValidacaoExcepetion("Editora não deve ser nula");
		}
		this.editora = editora.trim().toLowerCase();
	}
}
