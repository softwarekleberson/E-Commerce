package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovoCliente;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

@Service
public class ValidaFormatoSenhaCliente implements IStrategyCliente {

    private static final String MENSAGEM_ERRO_QUANTIDADE_MINIMA = "Senha deve ter no mínimo 8 caracteres";
    private static final String MENSAGEM_ERRO_FORMATO_INCORRETO = "Senha deve conter pelo menos uma letra maiúscula,\"\n"
    		+ "            		+ \"					 uma letra minúscula, um número e um caractere especial";
    
    public void processar(Cliente dominio) {
    	String senha = dominio.getSenha();
        if (senha.length() < 8) {
            throw new ValidacaoExcepetion(MENSAGEM_ERRO_QUANTIDADE_MINIMA);
        }

        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?]).*$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(senha);

        if (!matcher.matches()) {
            throw new ValidacaoExcepetion(MENSAGEM_ERRO_FORMATO_INCORRETO);
        }
    }
}