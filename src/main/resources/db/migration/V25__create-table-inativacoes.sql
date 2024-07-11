CREATE TABLE statuslivro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    justificativa VARCHAR(255),
    categoria VARCHAR(255),
    livro_id BIGINT,
    FOREIGN KEY (livro_id) REFERENCES livros(id)
);
