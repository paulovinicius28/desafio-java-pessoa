CREATE TABLE tb_pessoa(
   id_pessoa INT NOT NULL,
   nome VARCHAR(50) NOT NULL,
   idade INT,
   peso DECIMAL(5,2),

   PRIMARY KEY(id_pessoa)
);

INSERT INTO tb_pessoa(id_pessoa, nome, idade, peso) VALUES (1, 'Paulo Vinicius', 29, 86.94);