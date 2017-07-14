CREATE TABLE FUNCIONARIO
(
    MATRICULA NUMBER(9),
    SENHA VARCHAR(20),
    NASCIMENTO DATE,
    DATA_ADMISSAO DATE,
    SEXO VARCHAR(1),
    NOME VARCHAR(35),
    ENDERECO VARCHAR(35),
    SALARIO NUMBER(5),
    PRIMARY KEY (MATRICULA)
);


CREATE TABLE EQUIPAMENTO
(
    IDENTIFICADOR NUMBER(9),
    DATA_AQUISICAO DATE,
    DESCRICAO VARCHAR(100),
    CUSTO_DIARIA NUMBER(10),
    PRIMARY KEY (IDENTIFICADOR)
);


CREATE TABLE RESERVAS(
    MATRICULA NUMBER(9),
    IDENTIFICADOR NUMBER(9),
    PROTOCOLO INT,
    DATA_INICIO DATE,
    DATA_TERMINO DATE,
    PRIMARY KEY (PROTOCOLO),
    CONSTRAINT FK_MATRICULA FOREIGN KEY (MATRICULA) REFERENCES FUNCIONARIO (MATRICULA),
    CONSTRAINT FK_IDENTIFICADOR FOREIGN KEY (IDENTIFICADOR) REFERENCES EQUIPAMENTO (IDENTIFICADOR)
);








