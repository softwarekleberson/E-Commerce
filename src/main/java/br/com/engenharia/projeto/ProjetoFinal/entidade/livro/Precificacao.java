package br.com.engenharia.projeto.ProjetoFinal.entidade.livro;

import java.math.BigDecimal;
import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.dtos.estoque.DadosCadastroPrecificacao;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Precificacao")
@Table(name = "precificacoes")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Precificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "precificacao")
	private List<Livro> livros;
	
	private BigDecimal precificacao;
	
	public Precificacao(DadosCadastroPrecificacao dados) {
		setPrecificacao(dados.precificacao());
	}
	
	public Precificacao(Long idPrecificacao) {
		this.id = idPrecificacao;
	}
	
	public void setPrecificacao(BigDecimal precificacao) {
		if(precificacao == null || precificacao.compareTo(BigDecimal.ZERO) <= 0) {
	        throw new ValidacaoExcepetion("A precificação deve ser um valor positivo maior que zero.");
		}
		this.precificacao = precificacao;
	}
}
