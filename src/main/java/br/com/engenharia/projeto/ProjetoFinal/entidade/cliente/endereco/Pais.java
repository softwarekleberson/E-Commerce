package br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.endereco;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.ValidacaoExcepetion;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Pais {

	private String pais;

	public Pais(DadosCadastroEntrega dados) {
		setPais(dados.paisEntrega());
	}
	
	public Pais(DadosCadastroCobranca dados) {
		setPais(dados.paisCobranca());
	}
	
	public void setPais(String pais) {
		if(pais == null || pais.trim().length() == 0) {
			throw new ValidacaoExcepetion("Pais não deve ser nulo");
		}
		this.pais = pais.trim().toLowerCase();
	}
}