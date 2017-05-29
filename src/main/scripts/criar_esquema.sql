-- RELACIONAL

CREATE TABLE tipos_telefones (
	cod_tipo_telefone NUMBER ( 2 ) NOT NULL,
	descricao VARCHAR2 ( 20 ) NOT NULL,
	CONSTRAINT PK_TIPOS_TELEFONES PRIMARY KEY (cod_tipo_telefone)
	)
/
CREATE TABLE administradores (
	cod_administrador NUMBER ( 6 ) NOT NULL,
	nivel_privilegio NUMBER ( 1 ) NOT NULL,
	CONSTRAINT PK_ADMINISTRADORES PRIMARY KEY (cod_administrador)
	)
/
CREATE TABLE clientes_enderecos (
	cod_cliente NUMBER ( 6 ) NOT NULL,
	cod_endereco NUMBER ( 3 ) NOT NULL,
	data_cadastro DATE NOT NULL,
	CONSTRAINT PK_CLIENTES_ENDERECOS PRIMARY KEY (cod_cliente, cod_endereco)
	)
/
CREATE TABLE produtos (
	cod_produto NUMBER ( 5 ) NOT NULL,
	titulo VARCHAR2 ( 200 ) NOT NULL,
	ano_lancamento DATE NOT NULL,
	importado CHAR ( 1 ) DEFAULT 'N' NOT NULL,
	preco NUMBER ( 10, 2 ) NOT NULL,
	prazo_entrega NUMBER ( 3 ) NOT NULL,
	CONSTRAINT PK_PRODUTOS PRIMARY KEY (cod_produto),
	CONSTRAINT CHK_PROD_IMPORTADO CHECK (importado in ('S','N'))
	)
/
CREATE TABLE enderecos (
	cod_endereco NUMBER ( 3 ) NOT NULL,
	rua VARCHAR2 ( 60 ) NOT NULL,
	numero NUMBER ( 5 ) NOT NULL,
	complemento VARCHAR2 ( 20 ),
	cod_cidade NUMBER ( 4 ) NOT NULL,
	cep CHAR ( 8 ) NOT NULL,
	CONSTRAINT PK_ENDERECOS PRIMARY KEY (cod_endereco)
	)
/
CREATE TABLE telefones (
	cod_cliente NUMBER ( 6 ) NOT NULL,
	cod_telefone NUMBER ( 2 ) NOT NULL,
	cod_tipo_telefone NUMBER ( 2 ) NOT NULL,
	ddd NUMBER ( 3 ),
	numero VARCHAR2 ( 10 ) NOT NULL,
	CONSTRAINT PK_TELEFONES PRIMARY KEY (cod_cliente, cod_telefone)
	)
/
CREATE TABLE clientes (
	cod_cliente NUMBER ( 6 ) NOT NULL,
	data_nascimento DATE,
	data_cadastro DATE NOT NULL,
	CONSTRAINT PK_CLIENTES PRIMARY KEY (cod_cliente)
	)
/
CREATE TABLE estados (
	uf CHAR ( 2 ) NOT NULL,
	nome VARCHAR2 ( 40 ) NOT NULL,
	regiao CHAR ( 2 ) NOT NULL,
	CONSTRAINT PK_ESTADOS PRIMARY KEY (uf)
	)
/
CREATE TABLE usuarios (
	cod_usuario NUMBER ( 6 ) NOT NULL,
	nome VARCHAR2 ( 100 ) NOT NULL,
	cpf CHAR ( 11 ) NOT NULL,
	email VARCHAR2 ( 40 ) NOT NULL,
	username VARCHAR2 ( 20 ) NOT NULL,
	password VARCHAR2 ( 20 ) NOT NULL,
	CONSTRAINT PK_USUARIOS PRIMARY KEY (cod_usuario),
	CONSTRAINT AK_USU_CPF UNIQUE (cpf),
	CONSTRAINT AK_USU_USERNAME UNIQUE (username)
	)
/
CREATE TABLE cidades (
	cod_cidade NUMBER ( 4 ) NOT NULL,
	nome VARCHAR2 ( 60 ) NOT NULL,
	uf CHAR ( 2 ) NOT NULL,
	CONSTRAINT PK_CIDADES PRIMARY KEY (cod_cidade)
	)
/
CREATE TABLE pedidos_produtos (
	num_pedido NUMBER ( 7 ) NOT NULL,
	cod_produto NUMBER ( 5 ) NOT NULL,
	quantidade NUMBER ( 3 ) NOT NULL,
	valor_unitario NUMBER ( 10, 2 ) NOT NULL,
	CONSTRAINT PK_PEDIDOS_PRODUTOS PRIMARY KEY (num_pedido, cod_produto)
	)
/
CREATE TABLE autores (
	cod_autor NUMBER ( 4 ) NOT NULL,
	nome VARCHAR2 ( 100 ) NOT NULL,
	descricao VARCHAR2 ( 1024 ),
	CONSTRAINT PK_AUTORES PRIMARY KEY (cod_autor)
	)
/
CREATE TABLE pedidos (
	num_pedido NUMBER ( 7 ) NOT NULL,
	cod_cliente NUMBER ( 6 ) NOT NULL,
	cod_endereco NUMBER ( 3 ) NOT NULL,
	data_emissao DATE NOT NULL,
	CONSTRAINT PK_PEDIDOS PRIMARY KEY (num_pedido)
	)
/
CREATE TABLE autores_produtos (
	cod_autor NUMBER ( 4 ) NOT NULL,
	cod_produto NUMBER ( 5 ) NOT NULL,
	CONSTRAINT PK_AUTORES_PRODUTOS PRIMARY KEY (cod_autor, cod_produto)
	)
/
ALTER TABLE administradores ADD ( CONSTRAINT FK_USU_ADM FOREIGN KEY (cod_administrador) REFERENCES usuarios (cod_usuario))
/
ALTER TABLE clientes_enderecos ADD ( CONSTRAINT FK_CLI_CLIEND FOREIGN KEY (cod_cliente) REFERENCES clientes (cod_cliente))
/
ALTER TABLE clientes_enderecos ADD ( CONSTRAINT FK_END_CLIEND FOREIGN KEY (cod_endereco) REFERENCES enderecos (cod_endereco))
/
ALTER TABLE enderecos ADD ( CONSTRAINT FK_CID_END FOREIGN KEY (cod_cidade) REFERENCES cidades (cod_cidade))
/
ALTER TABLE telefones ADD ( CONSTRAINT FK_CLI_TEL FOREIGN KEY (cod_cliente) REFERENCES clientes (cod_cliente))
/
ALTER TABLE telefones ADD ( CONSTRAINT FK_TIPTEL_TEL FOREIGN KEY (cod_tipo_telefone) REFERENCES tipos_telefones (cod_tipo_telefone))
/
ALTER TABLE clientes ADD ( CONSTRAINT FK_USU_CLI FOREIGN KEY (cod_cliente) REFERENCES usuarios (cod_usuario))
/
ALTER TABLE cidades ADD ( CONSTRAINT FK_EST_CID FOREIGN KEY (uf) REFERENCES estados (uf))
/
ALTER TABLE pedidos_produtos ADD ( CONSTRAINT FK_PED_PEDPROD FOREIGN KEY (num_pedido) REFERENCES pedidos (num_pedido))
/
ALTER TABLE pedidos_produtos ADD ( CONSTRAINT FK_PROD_PEDPROD FOREIGN KEY (cod_produto) REFERENCES produtos (cod_produto))
/
ALTER TABLE pedidos ADD ( CONSTRAINT FK_CLIEND_PED FOREIGN KEY (cod_cliente, cod_endereco) REFERENCES clientes_enderecos (cod_cliente, cod_endereco))
/
ALTER TABLE autores_produtos ADD ( CONSTRAINT FK_AUT_AUTPROD FOREIGN KEY (cod_autor) REFERENCES autores (cod_autor))
/
ALTER TABLE autores_produtos ADD ( CONSTRAINT FK_PRD_AUTPROD FOREIGN KEY (cod_produto) REFERENCES produtos (cod_produto))
/
