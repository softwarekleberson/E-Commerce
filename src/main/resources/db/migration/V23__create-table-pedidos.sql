CREATE TABLE pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_realizado DATE NOT NULL,
    quantidade INT NOT NULL,
    valor_total DECIMAL(19, 2) NOT NULL,
    codigo_pedido VARCHAR(50) NOT NULL,
    carrinho_id BIGINT NOT NULL,
    livro_id BIGINT NOT NULL,
    status_entrega VARCHAR(50) NOT NULL,
    status_devolucao VARCHAR(50),
    FOREIGN KEY (carrinho_id) REFERENCES carrinhos(id),
    FOREIGN KEY (livro_id) REFERENCES livros(id)
);
