package br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.endereco;

import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.ValidacaoExcepetion;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class Cep {

	private String cep;
	
	public Cep(String cep) {
		setCep(cep);
	}
	
	public void setCep(String cep) {
		String regexCEP = "^\\d{5}-\\d{3}$";
		if(!cep.matches(regexCEP)) {
			throw new ValidacaoExcepetion("Cep no formato irregular");
		}
		this.cep = cep.trim().toLowerCase();
	}
}