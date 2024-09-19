package br.com.engenharia.projeto.ProjetoFinal.entidades.estoque;

public interface RepositorioDeEstoque {

	void salvar(Estoque estoque);
	Estoque verificaDisponibilidadeLivro(Long idLivro);
}
