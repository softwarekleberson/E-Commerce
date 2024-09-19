package br.com.engenharia.projeto.ProjetoFinal.dtos.pedido;

import java.math.BigDecimal;

public record DadosPedidoItemProduto(
		
		Long pedidoId,        
	    String codigoPedido,  
	    Long clienteId,       
	    String statusEntrega, 
	    int quantidade,       
	    BigDecimal precoUnitario, 
	    BigDecimal subtotal,      
	    String tituloLivro,   
	    BigDecimal precoLivro     
		
		) {}
