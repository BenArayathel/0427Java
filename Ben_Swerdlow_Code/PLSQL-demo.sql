-- You need to highlight the block you want to execute, then execute it. :(
-- If you want the whole thing to work at once, you need to order it as
-- set SERVEROUTPUT ON
-- write out all functions
-- write out all anonymous blocks to complete functions

set SERVEROUTPUT ON;

begin
dbms_output.put_line('Hello PLSQL');
end;

declare 
name varchar2(10):='Raj';
begin
DBMS_OUTPUT.PUT_LINE('Hello'||name);
END;


create or replace procedure helloProc AS
--after AS, it is declaration block, DO NOT use the declare keyword after as
begin
DBMS_OUTPUT.PUT_LINE('Hello Procedure!');
end helloProc;
-- only the compilation is done, we need to run it in an anonymous block it

BEGIN
helloProc();
END;

create or replace function helloFunc return varchar2 AS
begin
return 'Hello function!';
end helloFunc;

begin
DBMS_OUTPUT.PUT_LINE(helloFunc());
end;


/*
Types of parameters
1) IN - Input (default) cannot change in function
----can put IN to specify, but don't have to
2) OUT - Output
----have to put OUT to specify
3) INOUT - acts as both IN and OUT, can assign default values to it and is modifiable
----rarely used
*/

-- a is declared explicitly as IN, b is implicitly IN, res has to be declared as OUT
create or replace procedure add2Numbers(a IN number, b number, res OUT number) AS
begin
res:=a+b;
end add2Numbers;

declare
result number;
begin
add2Numbers(10,22,result);
DBMS_OUTPUT.PUT_LINE('Result is '||result);
end;

-- HOMEWORK - Look up ways to do
-- if conditions, loops, etc.
-- loop == normal loop, while loop, for loop
-- switch == case

declare
id varchar2(20);
begin
createtrainee(id, 'Danesh', 0987654321, 'Danesh1@gmail.com', to_date('10-10-1990','dd-mm-yyyy'), 'Bangalore');
DBMS_OUTPUT.PUT_LINE('Trainee created with id '||id);
end;


SELECT 'TR'||upper(substr('Danesh',0,2))||substr(1234567890,0,3)||
  extract(year from SYSDATE)||trainee_seq.nextval from dual;
  
SELECT SYSDATE from dual;

declare
--c number;
-- c is a number and it's just a counter
-- it doesn't necessarily need to be initialized, apparently
begin
for c in (SELECT id, name, contact, email, dob, city from trainee) loop
dbms_output.put_line('id = '||c.id||' name = '||c.name);
-- do some sort of calculation or operation besides just 
end loop;
end;

begin
for m in (SELECT * from trainee) loop
dbms_output.put_line('id = '||m.id||' name = '||m.name);
end loop;
end;

/*HOMEWORK
read about the following
    cursors
    triggers
    collection
        varrays
        record
        index by table
*/


