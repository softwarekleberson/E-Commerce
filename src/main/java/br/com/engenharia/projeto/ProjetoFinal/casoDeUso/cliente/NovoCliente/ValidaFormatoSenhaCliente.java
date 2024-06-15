package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovoCliente;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cliente;

@Service
public class ValidaFormatoSenhaCliente implements IStrategyCliente {

    public void processar(Cliente dominio) {
    	String senha = dominio.getSenha();
        if (senha.length() < 8) {
            throw new IllegalArgumentException("Senha deve ter no mínimo 8 caracteres");
        }

        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?]).*$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(senha);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial");
        }
    }
}