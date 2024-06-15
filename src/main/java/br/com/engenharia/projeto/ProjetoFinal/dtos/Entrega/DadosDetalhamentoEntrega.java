package br.com.engenharia.projeto.ProjetoFinal.dtos.Entrega;

import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Entrega;

public record DadosDetalhamentoEntrega(
		
		Long id, String logradouro, String numero,
		String bairro, String cep, String observacao,
		String fraseEntrega, String tipoLogradouto,
		String tipoResidencia, String cidade,
		String estado, String pais, boolean principal, String ddd, 
		String telefone, String receptor
		
									  				) 
{
	
	public DadosDetalhamentoEntrega(Entrega entrega) {
		this(entrega.getId(),entrega.getLogradouro(), entrega.getNumero(),
				entrega.getBairro(), entrega.getCep(), entrega.getObservacao(),
				entrega.getFraseEntrega(), entrega.getTipoLogradouro().getTipoLogradouro(),
				entrega.getTipoResidencia().getTipoResidencia(), entrega.getCidade().getCidade(),
				entrega.getCidade().getEstado().getEstado(), 
				entrega.getCidade().getEstado().getPais().getPais(), entrega.isPrincipal(),
				entrega.getCliente().getTelefone().getDdd(), entrega.getCliente().getTelefone().getTelefone(),
				entrega.getCliente().getNome());
	}
}