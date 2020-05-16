select * from bank_user where username = 'UNITtest';
select * from bank_account where account_name = 'UNITtest';
select * from bank_account where account_id = '572';
select * from bank_transaction;

delete from bank_user where username = 'UNITtest';
delete from bank_account where account_id = '572';



update bank_account set account_balance = 5000 where account_name = 'UNITtest';

insert into bank_account (account_id, user_id, account_name, account_balance) values (572,569,'UNITtest',10);
insert into bank_user (user_id, username, password, approved) values (, 'UNITtest', 'test', 0);


delete from bank_transaction where account_name = 'test' and transaction_id = 173;

select * from bank_user where username = 'ethan1' and password = 'ethan1';
select * from bank_user where username = 'ethan1' and password = 'ethan1';

update bank_user set approved = 0 where user_id = 92;

insert into bank_user values (34, 'ethan12', 'pass', 0);

select * from bank_account where user_id = 92;
delete from bank_account where user_id = ;
update bank_account set account_name = 'test' where account_name = 'evenmoremoemore';
update bank_account set account_balance = (account_balance + 90) where account_name = 'evenmoremoemore' and user_id = 92;

select account_name, account_balance from bank_account inner join bank_user on bank_account.user_id = bank_user.user_id where username = 'ethan1';

--anonymous block for creating a new user
declare user_id varchar2(20);
begin 
create_new_user(user_id, 'UNITtest', 'test', 0);
end;

--creating a new account
insert into bank_account values (1, 2, 'newtest', 200, 5);

select * from bank_account;


--does a comment separate anonymous blocks?
declare account_id varchar2(20);
begin
create_new_account(account_id, 569, 'UNITtest', 10);
end;


declare transaction_id varchar2(20);
begin
create_new_transaction(transaction_id, 20, 'newaccount', 24, 'deposit');
end;