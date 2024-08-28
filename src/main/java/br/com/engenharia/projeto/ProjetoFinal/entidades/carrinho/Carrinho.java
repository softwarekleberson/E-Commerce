package br.com.engenharia.projeto.ProjetoFinal.entidades.carrinho;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Carrinho")
@Table(name = "carrinhos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Item> itensCarrinho = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;
	
	private BigDecimal precoTotal;
	
	private LocalDate dataCriacao = LocalDate.now();
	
	public void calcularPrecoTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (Item item : itensCarrinho) {
			BigDecimal itemTotal = item.getLivro().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
			total = total.add(itemTotal);
		}
		this.precoTotal = total;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setCliente(Long idCliente) {
		this.cliente = new Cliente();
		cliente.setId(idCliente);
	}
	
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setItensCarrinho(List<Item> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}
	
	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}
}