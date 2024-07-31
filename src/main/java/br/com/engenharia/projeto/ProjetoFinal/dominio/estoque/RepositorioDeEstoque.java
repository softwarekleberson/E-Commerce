package br.com.engenharia.projeto.ProjetoFinal.dominio.estoque;

public interface RepositorioDeEstoque {

	void salvar(Estoque estoque);
	public int verificaDisponibilidadeLivro(Long idLivro);
}
