package br.com.engenharia.projeto.ProjetoFinal.entidade.cliente;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosCadastroCartao;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.ValidacaoExcepetion;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "cartoes")
public class Cartao {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private boolean principal;
	
	private String nomeImpresso;
	
	public static final int CODIGO_CARTAO_CREDITO_MINIMO = 3;
	public static final int CODIGO_CARTAO_CREDITO_MAXIMO = 4;
	private String codigo;
	
	public static final int NUMERO_CARTAO_MINIMO = 16;
	public static final int NUMERO_CARTAO_MAXIMO = 19;
	private String numeroCartao;
	
	@Enumerated(EnumType.STRING)
	private Bandeira bandeira;
	
	private LocalDate dataExpiracao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientes_id")
	private Cliente cliente;
	private boolean ativo;
	
	public Cartao(@Valid DadosCadastroCartao dados) {
		setPrincipal(dados.principal());
		setBandeira(dados.bandeira());
		setCodigo(dados.codigo());
		setNomeImpresso(dados.nomeImpresso());
		setNumeroCartao(dados.numeroCartao());
		setDataExpiracao(dados.dataExpiracao());
		setCliente(dados.idCliente());
		setAtivo(true);
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setCliente(Long idClinte) {
		this.cliente = new Cliente();
		cliente.setId(idClinte);
	}
	
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	
	public void setNomeImpresso(String nomeImpresso) {
		if(nomeImpresso == null || nomeImpresso.trim().isEmpty() || nomeImpresso.length() <= 2) {
			throw new ValidacaoExcepetion("Nome deve possuir mais de 2 digitos");
		}
		this.nomeImpresso = nomeImpresso.trim();
	}
	
	public void setCodigo(String codigo) {
	    if(codigo.trim().length() != CODIGO_CARTAO_CREDITO_MINIMO && codigo.trim().length() != CODIGO_CARTAO_CREDITO_MAXIMO) {
	        throw new ValidacaoExcepetion("Codigo do cartão deve ter 3 ou 4 digitos");
	    }
	    this.codigo = codigo.trim();
	}
	
	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}
	
	public void setNumeroCartao(String numeroCartao) {
		if(numeroCartao.trim().length() < NUMERO_CARTAO_MINIMO || numeroCartao.trim().length() > NUMERO_CARTAO_MAXIMO) {
            throw new ValidacaoExcepetion("Codigo do cartão das bandeiras Visa "
            							  + "e Mastercard devem possuir 16 digitos,"
            							  + " bandeira Elo até 19 digitos");
        }
	    this.numeroCartao = numeroCartao.trim();
	}
	
	public void setDataExpiracao(LocalDate dataExpiracao) {
		if(dataExpiracao.isBefore(LocalDate.now())) {
			throw new ValidationException("Data de expiração não deve ser no passado");
		}
		this.dataExpiracao = dataExpiracao;
	}
}