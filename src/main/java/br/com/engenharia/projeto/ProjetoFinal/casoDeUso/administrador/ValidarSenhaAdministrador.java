package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.administrador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dominio.administrador.Administrador;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;
import jakarta.validation.ValidationException;

@Service
public class ValidarSenhaAdministrador implements IStrategyAdministrador {

    private static final String SENHA_CURTA = "Senha deve ter no mínimo 8 caracteres";
    private static final String SENHA_INVALIDA = "Senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial";
    private static final String REGEX_SENHA = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?]).*$";

    @Override
    public void processar(Administrador dominio) {
        String senha = dominio.getSenha();
        
        if (senha.length() < 8) {
            throw new ValidacaoExcepetion(SENHA_CURTA);
        }

        Pattern regex = Pattern.compile(REGEX_SENHA);
        Matcher matcher = regex.matcher(senha);

        if (!matcher.matches()) {
            throw new ValidationException(SENHA_INVALIDA);
        }
    }
}