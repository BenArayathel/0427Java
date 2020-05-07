--basic plsql 'anonymous block'

-- set the console output on
set SERVEROUTPUT ON;
declare
name varchar2(20):='mynamegoeshere';
begin
DBMS_OUTPUT.PUT_line('hello ' || name);
end;

--notes:
--order makes a big difference, two different green arrow buttons make a difference. you have to highlight
--the entire block above and then run it, or click the arrow with the paragraph to run all of it at once

------------------------------------------------

