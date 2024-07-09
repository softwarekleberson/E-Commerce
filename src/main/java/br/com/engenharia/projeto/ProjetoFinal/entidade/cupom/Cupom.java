package br.com.engenharia.projeto.ProjetoFinal.entidade.cupom;

import java.math.BigDecimal;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cupom.DadosCadastroCupom;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;
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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Cupom")
@Table(name = "cupons")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cupom {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
    @Enumerated(EnumType.STRING)
	private TipoCupom tipoCupom;
	private BigDecimal valor;
	private boolean status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientes_id") 
	private Cliente cliente;
	
	public Cupom(@Valid DadosCadastroCupom dados) {
		setValor(dados.valor());
		setTipoCupom(dados.tipoCupom());
		setCliente(dados.idCliente());
		setStatus(true);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setValor(BigDecimal valor) {
		if(valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValidacaoExcepetion("Valor não deve ser menor ou igual a zero");
		}
		this.valor = valor;
	}
	
	public void setTipoCupom(TipoCupom tipoCupom) {
		this.tipoCupom = tipoCupom;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setCliente(Long idCliente) {
		this.cliente = new Cliente();
		cliente.setId(idCliente);
	}
}
