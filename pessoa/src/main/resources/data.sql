CREATE TABLE tb_pessoa(
   id_pessoa LONG not null,
   nome varchar(50) not null,
   idade Int,
   peso Double(3,2),

   PRIMARY KEY(id_pessoa)
);

insert into tb_pessoa(id_pessoa, nome, idade, peso) VALUES (1, 'Paulo Vinicius', 29, 86.94);