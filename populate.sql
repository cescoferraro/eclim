insert into FUNCIONARIO (MATRICULA,SENHA,NASCIMENTO,DATA_ADMISSAO,SEXO,NOME,ENDERECO,SALARIO)
select 123456389, '8734hrr', TO_DATE('07-JAN-56'), TO_DATE('07-JAN-99'),'M','ANTONIO', 'Rua Paulo', 10000 from dual
union all select 123456789, 'hf27834', TO_DATE('07-JAN-66'), TO_DATE('07-JAN-03'),'M','JOSE', 'Rua Julio', 5000 from dual;

insert into EQUIPAMENTO (IDENTIFICADOR,DATA_AQUISICAO,DESCRICAO,CUSTO_DIARIA) 
select 333, TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 'T', 23444 from dual
union all select 444, TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 'T', 23444 from dual;

insert into RESERVAS (PROTOCOLO,MATRICULA,IDENTIFICADOR,DATA_INICIO,DATA_TERMINO) 
select 885533, 123456389, 333 , TO_DATE('2019/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2019/05/05 21:02:44', 'yyyy/mm/dd hh:mi:ss') from dual
union all select 9933333, 123456389, 444 , TO_DATE('2019/05/03 21:02:44', 'yyyy/mm/dd hh:mi:ss'), TO_DATE('2019/05/05 15:02:44', 'yyyy/mm/dd hh:mi:ss') from dual
union all select 663333, 123456389, 333 , TO_DATE('2019/05/03 11:42:44', 'yyyy/mm/dd hh:mi:ss'), TO_DATE('2019/05/04 19:52:04', 'yyyy/mm/dd hh:mi:ss') from dual;
