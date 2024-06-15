package br.com.engenharia.projeto.ProjetoFinal.entidade.cliente;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Cidade {

	private String cidade;
	
	@Embedded
	private Estado estado;
	
	public Cidade(DadosCadastroCobranca dados) {
		setCidade(dados.cidadeCobranca());
		setEstadoCobranca(dados);
	}
	
	public Cidade(DadosCadastroEntrega dados) {
		setCidade(dados.cidadeEntrega());
		setEstadoEntrega(dados);
	}
	
	public void setCidade(String cidade) {
		if (cidade == null || cidade.trim().isEmpty()) {
			throw new IllegalArgumentException("Cidade não deve ser nulo ou vazio");
		 }
		
		this.cidade = cidade.trim();
	}
	
	public void setEstadoCobranca(DadosCadastroCobranca dados) {
		this.estado = new Estado(dados);
	}
	
	public void setEstadoEntrega(DadosCadastroEntrega dados) {
		this.estado = new Estado(dados);
	}
}