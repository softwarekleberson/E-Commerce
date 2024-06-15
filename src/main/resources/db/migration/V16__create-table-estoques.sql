CREATE TABLE estoques (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    livro_id BIGINT,
    quantidade INT NOT NULL,
    valor_Custo DECIMAL(10, 2) NOT NULL,
    data_Entrada DATE NOT NULL,
    fornecedor VARCHAR(255) NOT NULL,
    FOREIGN KEY (livro_id) REFERENCES livros(id)
);
