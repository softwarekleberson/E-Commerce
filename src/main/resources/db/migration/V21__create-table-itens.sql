CREATE TABLE itens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    carrinho_id BIGINT NOT NULL,
    livro_id BIGINT NOT NULL,
    FOREIGN KEY (carrinho_id) REFERENCES carrinhos(id),
    FOREIGN KEY (livro_id) REFERENCES livros(id)
);