CREATE TABLE pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_realizado DATE NOT NULL,
    codigo_pedido VARCHAR(255) NOT NULL,
    clientes_id BIGINT NOT NULL,
    status_entrega VARCHAR(50) NOT NULL,
    troca_devolucao VARCHAR(50) NOT NULL,
    FOREIGN KEY (clientes_id) REFERENCES Clientes(id)
);
