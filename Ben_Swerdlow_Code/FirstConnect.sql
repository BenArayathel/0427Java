declare
i VARCHAR(20);
begin
createtrainee(i, 'Ben', '1234567890', 'ben@gmail.com', to_date('10-05-1992','dd-mm-yyyy'), 'Ann Arbor');
end;

CREATE TABLE bankofben_customers(
    "Customer ID" VARCHAR2(20) PRIMARY KEY,
    "First Name" VARCHAR2(20) NOT NULL,
    "Middle Name" VARCHAR2(20),
    "Last Name" VARCHAR2(30) NOT NULL,
    "Mom's Maiden Name" VARCHAR2(30) NOT NULL,
    "Date of Birth" DATE NOT NULL,
    "Social Security Number" NUMBER NOT NULL UNIQUE,
    "Email" VARCHAR2(30) NOT NULL UNIQUE,
    "Phone Number" NUMBER NOT NULL,
    "Username" VARCHAR2(20) NOT NULL UNIQUE,
    "Password" VARCHAR2(30) NOT NULL,
    "Application Pending" NUMBER CHECK ("Application Pending" > -1 AND "Application Pending" < 2)
);

ALTER TABLE bankofben_customers MODIFY "Phone Number" NUMBER(10) CHECK ("Phone Number" > 999999999 AND "Phone Number" < 10000000000);
ALTER TABLE bankofben_customers MODIFY "Social Security Number" NUMBER(9) CHECK ("Social Security Number" > 99999999 AND "Social Security Number" < 1000000000);

declare
"Customer ID" VARCHAR2(20);
begin
createcustomer("Customer ID", 'Aaron', 'Babbish', 'Calhoun', 'Davidson',
    to_date('01/01/2000', 'dd/mm/yyyy'), 123456789, 'aabaca@gmail.com', 3216540987, 'aabaca', 'P4ssw0rd!');
end;

--CREATING A NEW USER
CREATE USER bankaccess IDENTIFIED BY "bankP4ssw0rd!1005";

--Grant our created user DBA (database access)
GRANT CONNECT, RESOURCE to bankaccess;
GRANT DBA TO bankaccess WITH ADMIN OPTION;

--SEE ALL USERS
SELECT * FROM all_users;

--WHATEVER YOU DO, !!!!!!!!!!!DON'T--------DELETE-----------SYSTEM---------OR----------SYS!!!!!!!!!!!!!

SELECT * FROM DBA_users;

SELECT * FROM bankofben_customers;

SELECT * FROM bankofben_customers WHERE EXISTS(SELECT * FROM bankofben_customers WHERE "Social Security Number"=103456789);
SELECT * FROM bankofben_customers WHERE "Social Security Number"=123456789;

CREATE TABLE bankofben_employees(
    "Employee ID" VARCHAR2(20) PRIMARY KEY,
    "First Name" VARCHAR2(20) NOT NULL,
    "Middle Name" VARCHAR2(20),
    "Last Name" VARCHAR2(30) NOT NULL,
    "Mom's Maiden Name" VARCHAR2(30) NOT NULL,
    "Date of Birth" DATE NOT NULL,
    "Social Security Number" NUMBER(9) NOT NULL UNIQUE CHECK ("Social Security Number" > 99999999 AND "Social Security Number" < 1000000000),
    "Email" VARCHAR2(30) NOT NULL UNIQUE,
    "Phone Number" NUMBER(10) NOT NULL CHECK("Phone Number" > 999999999 AND "Phone Number" < 10000000000),
    "Username" VARCHAR2(20) NOT NULL UNIQUE,
    "Password" VARCHAR2(30) NOT NULL,
    "Designation" VARCHAR2(20)
);

ALTER TABLE bankofben_employees ADD "Supervisor" VARCHAR(20);
ALTER TABLE bankofben_employees RENAME COLUMN "Supervisor" to "Supervisor Employee ID";
ALTER TABLE bankofben_employees MODIFY "Email" VARCHAR2(60);

declare
"Employee ID" VARCHAR2(20);
begin
createemployee("Employee ID", 'Benjamin', 'Eli', 'Swerdlow', 'Tobias',
    to_date('10/05/1992', 'mm/dd/yyyy'), 111111111, 'benswerdlow@gmail.com', 9086572134, 'benswerd', 'P4ssw0rd!', 'Founder', NULL);
end;

--UPDATE bankofben_employees SET "Date of Birth"=to_date('10/05/1992', 'mm/dd/yyyy');

declare
"Employee ID" VARCHAR2(20);
begin
createemployee("Employee ID", 'Michael', 'Gary', 'Scott', 'Kevis',
    to_date('03/15/1964', 'mm/dd/yyyy'), 987654321, 'michael.scott@dundermifflin.com', 9287437243, 
    'michaelscarn', 'Ih34rtH0lly!', 'Branch Manager', 'EMSWBE1992100000');
end;

ALTER TABLE bankofben_employees ADD "Can Hire"  NUMBER CHECK ("Can Hire" > -1 AND "Can Hire" < 2);
UPDATE bankofben_employees SET "Can Hire"=1;


SELECT * FROM bankofben_payments WHERE "Paying Account Number" IN (8636764367,1124348100,2903441653,9180651690,6249235260,
7883516090, 6417611898, 2701610162, 3371920762,2399796675,2172701410,3606912123,8880351552,6840187510,3538978757,3217445300,
7169902380, 8625849980) OR "Receiving Account Number" IN (8636764367,1124348100,2903441653,9180651690,6249235260,
7883516090, 6417611898, 2701610162, 3371920762,2399796675,2172701410,3606912123,8880351552,6840187510,3538978757,3217445300,
7169902380, 8625849980);
