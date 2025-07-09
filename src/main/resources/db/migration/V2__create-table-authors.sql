-- Criação da tabela authors
-- Creating the authors table
CREATE TABLE authors (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    PRIMARY KEY(id)
);

-- Alteração da tabela topics para adicionar a coluna author_id
-- Change topics table to add author_id column
ALTER TABLE topics ADD author_id BIGINT NOT NULL;

-- Adição da chave estrangeira que referencia a tabela authors
-- Addition of the foreign key that references the authors table
ALTER TABLE topics ADD CONSTRAINT fk_topics_author_id FOREIGN KEY (author_id) REFERENCES authors(id);
