package br.com.engenharia.projeto.ProjetoFinal.entidade.cliente;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Cobranca.DadosCadastroCobranca;
import br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega.DadosCadastroEntrega;
import br.com.engenharia.projeto.ProjetoFinal.infra.TratadorErros.ValidacaoExcepetion;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@MappedSuperclass
@EqualsAndHashCode(of = "id")
public abstract class Endereco {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	protected String logradouro;
	protected String numero;
	protected String bairro;
	protected String cep;
	protected String observacao;
		
	@Embedded
	protected TipoLogradouro tipoLogradouro;
	
	@Embedded
	protected TipoResidencia tipoResidencia;
	
	@Embedded
	protected Cidade cidade;
		
	public Endereco(@Valid DadosCadastroEntrega dados) {
		setBairro(dados.bairroEntrega());
		setCep(dados.cepEntrega());
		setCidadeEntrega(dados);
		setLogradouro(dados.logradouroEntrega());
		setNumero(dados.numeroEntrega());
		setObservacao(dados.observacaoEntrega());
		setTipoLogradouro(dados.tipoLogradouroEntrega());
		setTipoResidencia(dados.tipoResidenciaEntrega());		
	}
	
	public Endereco(@Valid DadosCadastroCobranca dados) {
		setBairro(dados.bairroCobranca());
		setCep(dados.cepCobranca());
		setCidadeCobranca(dados);
		setLogradouro(dados.logradouroCobranca());
		setNumero(dados.numeroCobranca());
		setObservacao(dados.observacaoCobranca());
		setTipoLogradouro(dados.tipoLogradouroCobranca());
		setTipoResidencia(dados.tipoResidenciaCobranca());
		
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogradouro(String logradouro) {
		if(logradouro == null || logradouro.trim().length() == 0) {
			throw new ValidacaoExcepetion("Logradouro não deve ser nulo");
		}
		this.logradouro = logradouro.trim().toLowerCase();
	}

	public void setNumero(String numero) {
		if(numero == null || numero.trim().length() == 0) {
			throw new ValidacaoExcepetion("Numero não deve ser nulo");
		}
		this.numero = numero.trim().toLowerCase();
	}

	public void setBairro(String bairro) {
		if(bairro == null || bairro.trim().length() == 0) {
			throw new ValidacaoExcepetion("Bairro não deve ser nulo");
		}
		this.bairro = bairro.trim().toLowerCase();
	}

	public void setCep(String cep) {
		String regexCEP = "\\d{8}";
		if(!cep.matches(regexCEP)) {
			throw new ValidacaoExcepetion("Cep no formato irregular");
		}
		this.cep = cep.trim().toLowerCase();
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao.trim().toLowerCase();
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		if(tipoLogradouro == null || tipoLogradouro.trim().length() == 0) {
			throw new ValidacaoExcepetion("Tipo de logradouro não deve ser nulo");
		}
		this.tipoLogradouro = new TipoLogradouro(tipoLogradouro);
	}

	public void setTipoResidencia(String tipoResidencia) {
		if(tipoResidencia == null || tipoResidencia.trim().length() == 0) {
			throw new ValidacaoExcepetion("Tipo de residencia não deve ser nulo");

		}
		this.tipoResidencia = new TipoResidencia(tipoResidencia);
	}

	public void setCidadeEntrega(DadosCadastroEntrega dados) {
		this.cidade = new Cidade(dados);
	}
	
	public void setCidadeCobranca(DadosCadastroCobranca dados) {
		this.cidade = new Cidade(dados);
	}
}