--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS sannicollas;

--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE sannicollas.tb_aluno
(
    id uuid not null,
    nome varchar(100),
    data_de_nascimento timestamp,
    turma_id int8 not null,
    cidade varchar(100),
    estado varchar(100),
    nome_pai varchar(100),
    nome_mae varchar(100),
    situacao_id int8 not null,
    documentacao_id int8 not null,
    primary key (id)
);

--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE sannicollas.tb_turma
(
    id int8 not null,
    descricao varchar(100) not null ,
    turno_id int8 not null,
    nivel_id int8 not null ,
    primary key (id)
);

--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE sannicollas.tb_turno
(
    id int8 not null,
    descricao varchar(100),
    primary key (id)
);

INSERT INTO sannicollas.tb_turno
VALUES
    (1, 'Matutino'),
    (2, 'Vespertino');

--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE sannicollas.tb_nivel
(
    id int8 not null,
    descricao varchar(100),
    primary key (id)
);

INSERT INTO sannicollas.tb_nivel
VALUES
    (1, 'Educação Infantil'),
    (2, 'Fundamental');

--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE sannicollas.tb_situacao
(
    id int8 not null,
    descricao varchar(100),
    primary key (id)
);

INSERT INTO sannicollas.tb_situacao
VALUES
    (1, 'Cursando'),
    (2, 'Aprovado'),
    (3, 'Reprovado');

--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE sannicollas.tb_documentacao
(
    id int8 not null,
    descricao varchar(100),
    primary key (id)
);

INSERT INTO sannicollas.tb_documentacao
VALUES
    (1, 'Regular'),
    (2, 'Irregular');

--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE sannicollas.tb_turma ADD constraint tb_turma_turno_id_fk foreign key (turno_id) references sannicollas.tb_turno(id);

ALTER TABLE sannicollas.tb_aluno ADD constraint tb_aluno_turma_id_fk foreign key (turma_id) references sannicollas.tb_turma(id);

ALTER TABLE sannicollas.tb_turma ADD constraint tb_turma_nivel_id_fk foreign key (nivel_id) references sannicollas.tb_nivel(id);

ALTER TABLE sannicollas.tb_aluno ADD constraint tb_turma_situacao_id_fk foreign key (situacao_id) references sannicollas.tb_situacao(id);

ALTER TABLE sannicollas.tb_aluno ADD constraint tb_turma_documentacao_id_fk foreign key (documentacao_id) references sannicollas.tb_documentacao(id);

--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO sannicollas.tb_turma
VALUES
    (1, 'Socialização', 1, 1),
    (2, 'Socialização', 2, 1),
    (3, 'Maternal', 1, 1),
    (4, 'Maternal', 2, 1),
    (5, 'Jardim I', 1, 1),
    (6, 'Jardim I', 2, 1),
    (7, 'Jardim II', 1, 1),
    (8, 'Jardim II', 2, 1),
    (9, '1° Ano', 1, 2),
    (10, '1° Ano', 2, 2),
    (11, '2° Ano', 1, 2),
    (12, '2° Ano', 2, 2),
    (13, '3° Ano', 1, 2),
    (14, '3° Ano', 2, 2),
    (15, '4° Ano', 1, 2),
    (16, '4° Ano', 2, 2),
    (17, '5° Ano', 1, 2),
    (18, '5° Ano', 2, 2);

--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------

