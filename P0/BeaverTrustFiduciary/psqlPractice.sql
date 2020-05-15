-- using anonymous functions to call procedures that i wrote in other 'files' that are stored in the db

select * from mystuff;

set SERVEROUTPUT ON;

declare 
result number;
begin
add2Numbers(10,22,result);
dbms_output.put_line('Result is '||result);
end;


  DECLARE 
   a number; 
   b number; 
   c number;
   begin
   addme2(4,153,c);
   DBMS_OUTPUT.PUT_LINE(c);
   end;
   
   --here i use the stored procedure and sequence to insert a new item with incremented id
declare
stuff_id varchar2(20);
begin
create_new_stuff(stuff_id, 'bigoldsandwhichmadeofcheese', 0, 5);
dbms_output.put_line('stuff added to db');
end;

