package br.com.engenharia.projeto.ProjetoFinal.entidades.cliente.cliente;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;

public class AlgoritmoVerificaFormatoSenha {

	private static final String MENSAGEM_ERRO_QUANTIDADE_MINIMA = "Senha deve ter no mínimo 8 caracteres";
    private static final String MENSAGEM_ERRO_FORMATO_INCORRETO = "Senha deve conter pelo menos uma letra maiúscula,\"\n"
    		+ "            		+ \"					 uma letra minúscula, um número e um caractere especial";
    
    public static void algoritmoVerificaFormatoSenha(String senha) {
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
