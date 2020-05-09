select * from bank_user;

insert into bank_user values (34, 'ethan12', 'pass', 0);

select * from bank_account;

--anonymous block for creating a new user
declare user_id varchar2(20);
begin 
create_new_user(user_id, 'five nine guy', 'password1', 0);
end;

--creating a new account
insert into bank_account values (1, 2, 'newtest', 200, 5);

declare account_id varchar2(20);
begin
create_new_account(account_id, 10, '5-9test', 1000);
end;