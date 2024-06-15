package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.NovaSenha;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cliente.DadosAtualizacaoSenha;

@Service
public class ValidaSenhaAtualizadaCliente implements IStrategySenhaAtualizadaCliente {

	@Override
    public void processar(DadosAtualizacaoSenha dados) {
		String senha = dados.senha();
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