package br.com.engenharia.projeto.ProjetoFinal.entidade.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.engenharia.projeto.ProjetoFinal.dtos.pedido.DadosCadastroPedido;
import br.com.engenharia.projeto.ProjetoFinal.entidade.carrinho.Carrinho;
import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.Livro;
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

@AllArgsConstructor
@Getter
@Table(name = "pedidos")
@Entity(name = "Pedido")
@EqualsAndHashCode(of = "codigoPedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate pedidoRealizado;

    public static final int QUANTIDADE_MINIMA = 0;
    private int quantidade;

    private BigDecimal valorTotal;

    private String codigoPedido;

    @ManyToOne
    @JoinColumn(name = "carrinho_id", nullable = false)
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Enumerated(EnumType.STRING)
    private StatusEntrega  statusEntrega;

    @Enumerated(EnumType.STRING)
    private TrocaDevolucao trocaDevolucao;
    
    public Pedido () {
    	
    }
    
    public Pedido(DadosCadastroPedido dados) {
		setPedidoRealizado(LocalDate.now());
		setQuantidade(dados.quantidade());
		setStatusEntrega(StatusEntrega.EM_SEPARACAO);
		setTrocaDevolucao(TrocaDevolucao.DEVOLUCAO_NAO_PEDIDA);
	}
    
    public void devolverItem(TrocaDevolucao trocaDevolucao) {
    	this.trocaDevolucao = trocaDevolucao;
    }

	public void setId(Long id) {
		this.id = id;
	}

	public void setPedidoRealizado(LocalDate pedidoRealizado) {
		this.pedidoRealizado = pedidoRealizado;
	}

	public void setQuantidade(int quantidade) {
		if(quantidade <= QUANTIDADE_MINIMA) {
			throw new IllegalArgumentException("Qauntidade não deve ser menor ou igual a 0");
		}
		this.quantidade = quantidade;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setCodigoPedido(String codigoPedido) {
        if (codigoPedido == null) {
            throw new IllegalArgumentException("codigoPedido não pode ser null");
        }
        
        this.codigoPedido = codigoPedido;
    }

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void setStatusEntrega(StatusEntrega statusEntrega) {
		this.statusEntrega = statusEntrega;
	}

	public void setTrocaDevolucao(TrocaDevolucao trocaDevolucao) {
		this.trocaDevolucao = trocaDevolucao;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", pedidoRealizado=" + pedidoRealizado + ", quantidade=" + quantidade
				+ ", valorTotal=" + valorTotal + ", codigoPedido=" + codigoPedido + ", carrinho=" + carrinho
				+ ", livro=" + livro + ", statusEntrega=" + statusEntrega + ", trocaDevolucao=" + trocaDevolucao
				+ "]";
	}
}