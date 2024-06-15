CREATE TABLE livros (
    id BIGINT NOT NULL AUTO_INCREMENT,
    ativo TINYINT NOT NULL DEFAULT 1,
    data DATE,
    preco DECIMAL(10,2),
    titulo VARCHAR(100),
    isbn VARCHAR(17),
    paginas INT,
    sinopse TEXT,
    codigo_barra VARCHAR(13),
    altura DOUBLE,
    largura DOUBLE,
    peso DOUBLE,
    profundidade DOUBLE,
    editora VARCHAR(100),
    edicao VARCHAR(20),
    PRIMARY KEY (id)
);
