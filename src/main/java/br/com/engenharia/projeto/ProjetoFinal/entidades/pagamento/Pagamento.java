package br.com.engenharia.projeto.ProjetoFinal.entidades.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Cobranca;
import br.com.engenharia.projeto.ProjetoFinal.entidades.endereco.Entrega;
import br.com.engenharia.projeto.ProjetoFinal.entidades.pedido.Pedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Entity(name = "Pagamento")
@Table(name = "pagamentos")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime dataPagamento = LocalDateTime.now();
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Entrega entrega;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cobranca cobranca;
      
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    
    @OneToOne
    @JoinColumn(name = "pedido_id") 
    private Pedido pedido;
    
    @Column(name = "metodo_pagamento")
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    
    @Column(name = "status_compra")
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;
    
    public void mudarStatusPagamento(StatusCompra statusCompra) {
    	this.statusCompra = statusCompra;
    }
}
