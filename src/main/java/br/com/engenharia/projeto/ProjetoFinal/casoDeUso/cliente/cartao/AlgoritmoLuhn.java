package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.cartao;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosCadastroCartao;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.erros.ValidacaoExcepetion;
import jakarta.validation.ValidationException;

@Service
public class AlgoritmoLuhn implements IstrategyValidaCartao{

    private static final String MENSAGEM_ERRO = "O número %s é inválido.";
	
	@Override
	public void processar(DadosCadastroCartao dados) {
		
		 int sum = 0;
		 String numero = dados.numeroCartao();
	     boolean alternar = false;

	        for (int i = numero.length() - 1; i >= 0; i--) {
	            int n = Integer.parseInt(numero.substring(i, i + 1));
	            if (alternar) {
	                n *= 2;
	                if (n > 9) {
	                    n -= 9;
	                }
	            }
	            sum += n;
	            alternar = !alternar;
	        }

	        if (sum % 10 != 0) {
	            throw new ValidacaoExcepetion(String.format(MENSAGEM_ERRO, numero));
	        }
	}
}