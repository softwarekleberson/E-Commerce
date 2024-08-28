package br.com.engenharia.projeto.ProjetoFinal.entidades.carrinho;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.engenharia.projeto.ProjetoFinal.entidades.livro.livro.Livro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "ItensCarrinho")
@Table(name = "itens")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private int quantidade;
	 
	 @Column(name = "preco_unitario")
	 private BigDecimal precoUnitario;
	 
	 private BigDecimal subtotal;

	 @ManyToOne
	 @JoinColumn(name = "carrinho_id")
	 private Carrinho carrinho;

	 @ManyToOne
	 @JoinColumn(name = "livro_id")
	 private Livro livro;
	 
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
}
