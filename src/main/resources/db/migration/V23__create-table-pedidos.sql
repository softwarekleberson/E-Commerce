CREATE TABLE pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_realizado DATE NOT NULL,
    pago BOOLEAN NOT NULL,
    valor_total DECIMAL(10, 2),  -- Adicione NOT NULL se for obrigatório
    codigo_pedido VARCHAR(255) NOT NULL,
    pagamento_id VARCHAR(36),              -- Alterado para VARCHAR(36)
    clientes_id BIGINT NOT NULL,            -- Alterado para cliente_id
    status_entrega VARCHAR(255),
    troca_devolucao VARCHAR(255),
    FOREIGN KEY (pagamento_id) REFERENCES pagamentos(id), 
    FOREIGN KEY (clientes_id) REFERENCES clientes(id) 
);