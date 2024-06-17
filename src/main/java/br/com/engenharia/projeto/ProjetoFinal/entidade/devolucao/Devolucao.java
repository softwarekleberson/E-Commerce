package br.com.engenharia.projeto.ProjetoFinal.entidade.devolucao;

import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.entidade.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;
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
	
	private String codigoDevolucao;
	
	private LocalDate dataPedidoTroca;
	
	private LocalDate dataConclusaoTroca;
	
	@ManyToOne
	@JoinColumn(name = "clientes_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "pedidos_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "administradores_id")
	private Administrador administrador;
	
	@Enumerated(EnumType.STRING)
	private AnalisePedidoDevolucao AnalisePedidoDevolucao;
		
	public void aceitaOuNaoTroca(AnalisePedidoDevolucao AnalisePedidoDevolucao) {
		this.AnalisePedidoDevolucao = AnalisePedidoDevolucao;
	}
}