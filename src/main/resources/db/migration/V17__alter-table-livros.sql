ALTER TABLE livros
ADD COLUMN precificacao_id INT,
ADD FOREIGN KEY (precificacao_id) REFERENCES precificacoes(id);
