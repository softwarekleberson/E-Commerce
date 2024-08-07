package br.com.engenharia.projeto.ProjetoFinal.dominio.estoque;

import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Fornecedor {

	private String fornecedor;
	
	public Fornecedor(String fornecedor) {
		setFornecedor(fornecedor);
	}
	
	public void setFornecedor(String fornecedor) {
		if(fornecedor.trim() == null) {
			throw new ValidacaoExcepetion("Fornecedor não deve ser nulo");
		}
		this.fornecedor = fornecedor;
	}
}