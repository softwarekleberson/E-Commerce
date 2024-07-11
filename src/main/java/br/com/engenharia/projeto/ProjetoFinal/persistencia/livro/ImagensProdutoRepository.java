package br.com.engenharia.projeto.ProjetoFinal.persistencia.livro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engenharia.projeto.ProjetoFinal.entidade.livro.imagem.Imagens;

public interface ImagensProdutoRepository extends JpaRepository<Imagens, Long>{

}
