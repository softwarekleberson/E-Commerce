package br.com.engenharia.projeto.ProjetoFinal.dtos.cliente;

import java.time.LocalDate;
import java.util.List;

import br.com.engenharia.projeto.ProjetoFinal.dominio.cliente.Genero;
import br.com.engenharia.projeto.ProjetoFinal.dominio.cliente.contato.TipoTelefone;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCliente(
        
		@NotNull
        Genero genero,
        
        @NotNull
        String nome,
        
        @NotNull LocalDate nascimento,
        
        @NotNull
        String cpf,
        
        @NotNull
		String senha,
		
        @NotNull
		String confirmarSenha,
		
        @NotNull
		String email,
		
        @NotNull
		String ddd,
		
        @NotNull
		String telefone,
		
        @NotNull
		TipoTelefone tipo,
                
        @Valid @NotNull List<DadosCadastroEntrega> entrega,
                
        @Valid @NotNull List<DadosCadastroCobranca> cobranca
                                                                   )                                                       {
}