CREATE TABLE tb_usuario (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   nome VARCHAR(200) NOT NULL,
   email VARCHAR(200) NOT NULL,
   senha VARCHAR(100) NOT NULL
);