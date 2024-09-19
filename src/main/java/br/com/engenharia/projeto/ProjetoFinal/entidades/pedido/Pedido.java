package br.com.engenharia.projeto.ProjetoFinal.entidades.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.entidades.item.Item;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento.Pagamento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Table(name = "pedidos")
@Entity(name = "Pedido")
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate pedidoRealizado;
    
    @Transient
    private BigDecimal valorTotal;

    private String codigoPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Item> itens;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pagamento pagamento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientes_id", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private StatusEntrega  statusEntrega;

    @Enumerated(EnumType.STRING)
    private DevolucaoFoiPedidaOUNAO trocaDevolucao;
    
    public Pedido(Long id, LocalDate pedidoRealizado, String codigoPedido, Cliente cliente, StatusEntrega statusEntrega,
			DevolucaoFoiPedidaOUNAO trocaDevolucao) {
		
    	this.id = id;
		this.pedidoRealizado = pedidoRealizado;
		this.codigoPedido = codigoPedido;
		this.cliente = cliente;
		this.statusEntrega = statusEntrega;
		this.trocaDevolucao = trocaDevolucao;
	}
    
    public Pedido() {}
         
    public void adicionarItem(Item item) {
        if (this.itens == null) {
            this.itens = new ArrayList<>();
        }
        
        this.itens.add(item);

        item.setPedido(this);
    }
    
    public BigDecimal atualizarValorTotal() {
        if (this.itens != null && !this.itens.isEmpty()) {
            this.valorTotal = this.itens.stream()
                                        .map(Item::getSubtotal)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            this.valorTotal = BigDecimal.ZERO;
        }
		return valorTotal;
    }
    
    public void devolucaoPedida(DevolucaoFoiPedidaOUNAO trocaDevolucao) {
    	this.trocaDevolucao = trocaDevolucao;
    }
    
    public void devolverItem(DevolucaoFoiPedidaOUNAO trocaDevolucao) {
    	this.trocaDevolucao = trocaDevolucao;
    }

	public void setId(Long id) {
		this.id = id;
	}

	public void setPedidoRealizado(LocalDate pedidoRealizado) {
		this.pedidoRealizado = pedidoRealizado;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setStatusEntrega(StatusEntrega statusEntrega) {
		this.statusEntrega = statusEntrega;
	}

	public void setTrocaDevolucao(DevolucaoFoiPedidaOUNAO trocaDevolucao) {
		this.trocaDevolucao = trocaDevolucao;
	}
}