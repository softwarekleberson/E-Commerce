package br.com.engenharia.projeto.ProjetoFinal.dtos.carrinho;
import java.math.BigDecimal;

public record DadosDetalhamentoItensCarrinho(
		
		Long idLivro,
		Long quantidade,
		BigDecimal valorSubtotal
		
		) {
	
	 public DadosDetalhamentoItensCarrinho(Long idLivro, Long quantidade, BigDecimal valorSubtotal) {
	        
	        this.idLivro = idLivro;
	        this.quantidade = quantidade;
	        this.valorSubtotal = valorSubtotal;
	    }	
}