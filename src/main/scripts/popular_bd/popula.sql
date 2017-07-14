@./tipos_telefones.sql
commit;
@./estados.sql
commit;
@./cidades.sql
commit;
@./usuarios.sql
commit;
@./administradores.sql
commit;
@./clientes.sql
commit;
@./enderecos.sql
commit;
@./clientes_enderecos.sql
commit;
@./telefones.sql
commit;
@./pedidos.sql
commit;
@./autores.sql
commit;
@./produtos.sql
commit;
@./autores_produtos.sql
commit;
@./pedidos_produtos.sql
commit;

SELECT * FROM RESERVAS;

SELECT RESERVAS.IDENTIFICADOR , Count(*) ,
SUM(( RESERVAS.DATA_TERMINO - RESERVAS.DATA_INICIO)) TTT, 
SUM(EQUIPAMENTO.CUSTO_DIARIA *( RESERVAS.DATA_TERMINO - RESERVAS.DATA_INICIO)) TT 
FROM RESERVAS 
INNER JOIN EQUIPAMENTO ON RESERVAS.IDENTIFICADOR = EQUIPAMENTO.IDENTIFICADOR
group by RESERVAS.IDENTIFICADOR, EQUIPAMENTO.CUSTO_DIARIA ;




