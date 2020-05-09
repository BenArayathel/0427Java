select * from employee;

select * from employee where lastname = 'King';

select * from employee where firstname = 'Andrew' and reportsto is null;

select * from album ORDER BY title desc;

select firstname, city from customer order by city asc;

select * from genre;

insert into genre (genreid, name) values (26, 'bigfeelz');

select * from invoice;

--syntax is...
insert into employee (...stuff...) values (...stuff...), (...more stuff...);

update where firstname = 'Aaron' and lastname = 'Mitchell from customer


--skipping ahead to joins

select customer.lastname, invoice.invoiceid from customer inner join customer on customer.id = invoice.customerid;
