select * from bank_user;

select * from bank_user where username = 'ethan1' and password = 'ethan1';
select * from bank_user where username = 'ethan1' and password = 'ethan1';

insert into bank_user values (34, 'ethan12', 'pass', 0);

select * from bank_account;
select account_name from bank_account where user_id = (select user_id from bank_user where username = username);

--anonymous block for creating a new user
declare user_id varchar2(20);
begin 
create_new_user(user_id, 'five nine guy', 'password1', 0);
end;

--creating a new account
insert into bank_account values (1, 2, 'newtest', 200, 5);

declare account_id varchar2(20);
begin
create_new_account(account_id, 92, 'ethan1sstuff', 1000);
end;