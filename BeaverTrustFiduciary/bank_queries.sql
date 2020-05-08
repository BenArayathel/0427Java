select * from bank_user;

select * from bank_account;

--anonymous block for creating a new user
declare user_id varchar2(20);
begin 
create_new_user(user_id, 'first dude', 'password1');
end;

--creating a new account
insert into bank_account values (1, 2, 'newtest', 200, 5);

declare account_id varchar2(20);
begin
create_new_account(account_id, 4, 'newtestaccount', 2300, 1);
end;