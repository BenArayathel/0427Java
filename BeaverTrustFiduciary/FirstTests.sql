--comment
--not case sensitive, but remember to use single quotes

create table myStuff (
    stuff_id number(5) primary key,
    stuff_name VARCHAR2(50) not null unique, --can be the only thing with that name, has to be filled in
    stuff_isCool number(5) check (stuff_isCool > -1 and stuff_isCool < 2), --booleans are weird in sql, check makes sure it is 0 or 1
    stuff_amount number(5)
    );
    
insert into myStuff values (1, 'desk', 0, 1);
insert into myStuff values (2, 'dvd', 1, 100);
insert into myStuff values (3, 'books', 1, 200);
insert into myStuff values(4, 'paintings', 0, 4);
    
select * from myStuff ORDER BY stuff_id;

select stuff_name from myStuff where stuff_isCool = 0;

