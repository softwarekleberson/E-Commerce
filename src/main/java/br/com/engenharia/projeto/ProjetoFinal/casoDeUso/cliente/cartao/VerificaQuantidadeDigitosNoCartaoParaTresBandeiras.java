package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.cartao;

import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosCadastroCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.cartao.Bandeira;
import jakarta.validation.ValidationException;

@Service
public class VerificaQuantidadeDigitosNoCartaoParaTresBandeiras implements IstrategyValidaCartao{

	private final static int VISA_MASTERCARD_LENGHT = 16;
	private final static int ELO_MAXIMO_LENGHT = 19;
	private final static int ELO_MINIMO_LENGHT = 16;

	@Override
	public void processar(DadosCadastroCartao dados) {
	    Bandeira bandeira = dados.bandeira();
	    String numeroCartao = dados.numeroCartao();
	    
	    if (bandeira == Bandeira.MASTERCARD || bandeira == Bandeira.VISA) {
	        if (numeroCartao.length() != VISA_MASTERCARD_LENGHT) {
	            throw new ValidationException("Mastercard ou Visa precisam conter 16 digitos");
	        }
	    } else if (bandeira == Bandeira.ELO) {
	        if (!(numeroCartao.length() >= ELO_MINIMO_LENGHT && numeroCartao.length() <= ELO_MAXIMO_LENGHT)) {
	            throw new ValidationException("Elo precisa conter entre 16 ou 19 digitos");
	        }
	    }
	}
}