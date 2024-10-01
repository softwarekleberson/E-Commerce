CREATE TABLE pagamentos (
    id VARCHAR(36) PRIMARY KEY,          -- UUID como String
    data_pagamento TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    entregas_id BIGINT,                  -- Certifique-se de que este é o tipo correto
    cobrancas_id BIGINT,                  -- Certifique-se de que este é o tipo correto
    valor_total DECIMAL(10, 2) NOT NULL,
    metodo_pagamento VARCHAR(50) NOT NULL,
    status_compra VARCHAR(50) NOT NULL,
    FOREIGN KEY (entregas_id) REFERENCES entregas(id),
    FOREIGN KEY (cobrancas_id) REFERENCES cobrancas(id)
);