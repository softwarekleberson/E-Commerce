CREATE TABLE pagamentos (
    id VARCHAR(36) PRIMARY KEY,          -- UUID como String
	data_pagamento TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    entregas_id BIGINT,
    cobrancas_id BIGINT,
    valor_total DECIMAL(10, 2) NOT NULL,
    pedidos_id BIGINT,
    metodo_pagamento VARCHAR(50) NOT NULL,
    status_compra VARCHAR(50) NOT NULL,
    FOREIGN KEY (entregas_id) REFERENCES Entregas(id),
    FOREIGN KEY (cobrancas_id) REFERENCES Cobrancas(id),
    FOREIGN KEY (pedidos_id) REFERENCES Pedidos(id)
);
