create or replace procedure faxina as
  cid integer;
  n_o varchar2(50);
  n_i varchar2(50);
  cursor c_i is select constraint_name, table_name from user_constraints 
                where instr(constraint_name,'$') = 0 and instr(table_name,'$') = 0 order by r_constraint_name;
  cursor c_v  is select view_name from user_views where instr(view_name,'$') = 0;
  cursor c_t is select table_name from user_tables where instr(table_name,'$') = 0;
  cursor c_s is select sequence_name from user_sequences where instr(sequence_name,'$') = 0;
  cursor c_g is select trigger_name from user_triggers where instr(trigger_name,'$') = 0;
  cursor c_p is select object_name from user_objects where object_name <> 'FAXINA' and object_type = 'PROCEDURE' 
                  and  instr(object_name,'$') = 0;
  cursor c_f is select object_name from user_objects where object_type = 'FUNCTION' and instr(object_name,'$') = 0;
begin
 open c_i;
 loop
  fetch c_i into n_i, n_o;
  exit when c_i%NOTFOUND; 
  cid := DBMS_SQL.OPEN_CURSOR;
  if instr(n_o,' ') <> 0
  then
   if instr(n_i,' ') <> 0
   then
    DBMS_SQL.PARSE(cid, 'ALTER TABLE "' || n_o  ||'" DROP CONSTRAINT "' || n_i || '"', dbms_sql.v7);
   else
    DBMS_SQL.PARSE(cid, 'ALTER TABLE "' || n_o  ||'" DROP CONSTRAINT ' || n_i,dbms_sql.v7);
   end if;
  else
   if instr(n_i,' ') <> 0
   then
    DBMS_SQL.PARSE(cid, 'ALTER TABLE ' || n_o  ||' DROP CONSTRAINT "' || n_i || '"', dbms_sql.v7);
   else
    DBMS_SQL.PARSE(cid, 'ALTER TABLE ' || n_o  ||' DROP CONSTRAINT ' || n_i, dbms_sql.v7);
   end if;
  end if;
  DBMS_SQL.CLOSE_CURSOR(cid);
 end loop;
 close c_i;          
 open c_v;
 loop
  fetch c_v into n_o;
  exit when c_v%NOTFOUND;
  cid := DBMS_SQL.OPEN_CURSOR;
  if instr(n_o,' ') <> 0
  then
   DBMS_SQL.PARSE(cid, 'DROP VIEW "' || n_o || '"', dbms_sql.v7);
  else
   DBMS_SQL.PARSE(cid, 'DROP VIEW ' || n_o || '', dbms_sql.v7);
  end if;
  DBMS_SQL.CLOSE_CURSOR(cid);
 end loop;
 close c_v;
 open c_t;
 loop
  fetch c_t into n_o;
  exit when c_t%NOTFOUND;
  cid := DBMS_SQL.OPEN_CURSOR;
  if instr(n_o,' ') <> 0
  then
   DBMS_SQL.PARSE(cid, 'DROP TABLE "' || n_o || '"', dbms_sql.v7);
  else
   DBMS_SQL.PARSE(cid, 'DROP TABLE ' || n_o || '', dbms_sql.v7);
  end if;
  DBMS_SQL.CLOSE_CURSOR(cid);
 end loop;
 close c_t;
 open c_s;
 loop
  fetch c_s into n_o;
  exit when c_s%NOTFOUND;
  cid := DBMS_SQL.OPEN_CURSOR;
  if instr(n_o,' ') <> 0
  then
   DBMS_SQL.PARSE(cid, 'DROP sequence "' || n_o || '"', dbms_sql.v7);
  else
   DBMS_SQL.PARSE(cid, 'DROP sequence ' || n_o || '', dbms_sql.v7);
  end if;
  DBMS_SQL.CLOSE_CURSOR(cid);
 end loop;
 close c_s;
 open c_g;
 loop
  fetch c_g into n_o;
  exit when c_g%NOTFOUND;
  cid := DBMS_SQL.OPEN_CURSOR;
  if instr(n_o,' ') <> 0
  then
   DBMS_SQL.PARSE(cid, 'DROP trigger "' || n_o || '"', dbms_sql.v7);
  else
   DBMS_SQL.PARSE(cid, 'DROP trigger ' || n_o || '', dbms_sql.v7);
  end if;
  DBMS_SQL.CLOSE_CURSOR(cid);
 end loop;
 close c_g;
 open c_p;
 loop
  fetch c_p into n_o;
  exit when c_p%NOTFOUND;
  cid := DBMS_SQL.OPEN_CURSOR;
  if instr(n_o,' ') <> 0
  then
   DBMS_SQL.PARSE(cid, 'DROP procedure "' || n_o || '"', dbms_sql.v7);
  else
   DBMS_SQL.PARSE(cid, 'DROP procedure ' || n_o || '', dbms_sql.v7);
  end if;
  DBMS_SQL.CLOSE_CURSOR(cid);
 end loop;
 close c_p;
 open c_f;
 loop
  fetch c_f into n_o;
  exit when c_f%NOTFOUND;
  cid := DBMS_SQL.OPEN_CURSOR;
  if instr(n_o,' ') <> 0
  then
   DBMS_SQL.PARSE(cid, 'DROP function "' || n_o || '"', dbms_sql.v7);
  else
   DBMS_SQL.PARSE(cid, 'DROP function ' || n_o || '', dbms_sql.v7);
  end if;
  DBMS_SQL.CLOSE_CURSOR(cid);
 end loop;
 close c_f;
end;
/


rem execute esta procedure digitando:
rem exec faxina