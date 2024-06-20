package br.com.engenharia.projeto.ProjetoFinal.casoDeUso.cliente.cartao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.dao.cliente.CartaoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.cartao.DadosCadastroCartao;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Cartao;
import jakarta.validation.ValidationException;

@Service
public class VerificaSeCartaoFoiCadastradoAnteriormente implements IstrategyValidaCartao{

	@Autowired
	private CartaoDao cartaoDao;
	
	@Override
	public void processar(DadosCadastroCartao dados) {
		Optional<Cartao> cartao = cartaoDao.cartaoCadastradoAnteriormente(dados.numeroCartao());
		if(cartao.isPresent()) {
			throw new ValidationException("Cartão cadastrado anteriormente");
		}
	}
}