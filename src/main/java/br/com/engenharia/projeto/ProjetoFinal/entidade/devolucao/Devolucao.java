package br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao;

import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cupom.Cupom;
import br.com.engenharia.projeto.ProjetoFinal.entidade.pedido.Pedido;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "devolucoes")
@Entity(name = "Devolucao")
@EqualsAndHashCode(of = "id")
public class Devolucao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "cupom_id")
	private Cupom cupom;
	
	@Enumerated(EnumType.STRING)
	private PedidoDevolucao pedidoDevolucao;
	
	public void aceitaOuNaoTroca(PedidoDevolucao devolucao) {
		this.pedidoDevolucao = devolucao;
	}
}