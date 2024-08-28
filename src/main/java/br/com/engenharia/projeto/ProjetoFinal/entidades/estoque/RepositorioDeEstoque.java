package br.com.engenharia.projeto.ProjetoFinal.entidades.estoque;

public interface RepositorioDeEstoque {

	void salvar(Estoque estoque);
	public int verificaDisponibilidadeLivro(Long idLivro);
}
