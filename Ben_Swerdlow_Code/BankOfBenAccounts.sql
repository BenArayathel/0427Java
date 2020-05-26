SELECT TRUNC(DBMS_RANDOM.VALUE()*1e10) FROM DUAL;

SET SERVEROUTPUT ON;

DROP TABLE bankofben_accounts;

CREATE TABLE bankofben_accounts(
    "Account Number" NUMBER(10) PRIMARY KEY,
    "Balance" NUMBER(14,2) NOT NULL CHECK("Balance" >= 0),
    "Customer ID" VARCHAR(20) NOT NULL,
    CONSTRAINT "FK Customer ID" FOREIGN KEY("Customer ID") REFERENCES bankofben_customers("Customer ID")
);

ALTER TABLE bankofben_accounts DROP CONSTRAINT "Minimum Balance";
ALTER TABLE bankofben_accounts ADD CONSTRAINT "Non-negative Balance" CHECK("Balance" >= 0);

DECLARE
"Account Number" NUMBER;
"Number of AcctNumbers" NUMBER;
BEGIN
    CREATEACCOUNT("Account Number", 0, 'CUCAAA2000100000', "Number of AcctNumbers");
END;

UPDATE bankofben_customers SET "Application Pending"=0 WHERE "Customer ID"='CUCAAA200010000';

CREATE TABLE bankofben_transactions(
    "Transaction ID" VARCHAR(20) PRIMARY KEY,
    "Timestamp" TIMESTAMP(6) NOT NULL,
    "Account Number" NUMBER(10) NOT NULL,
    "Initial Balance" NUMBER(14,2) NOT NULL,
    "Amount" NUMBER(14,2) NOT NULL,
    "Final Balance" NUMBER(14,2) NOT NULL,
    CONSTRAINT "FK Account Number" FOREIGN KEY("Account Number") REFERENCES bankofben_accounts("Account Number")
)
ALTER TABLE bankofben_transactions ADD CHECK("Final Balance"="Initial Balance"+"Amount");

DECLARE
    TID VARCHAR(20);
    TSTAMP TIMESTAMP(6);
    --IBAL NUMBER(14,2);
BEGIN
    CREATETRANSACTION(TID, TSTAMP, 3969255661, 10000, 10);
END;

SELECT * FROM bankofben_transactions WHERE "Timestamp" < CURRENT_TIMESTAMP;

DECLARE
    "TID" VARCHAR(20);
    "TSTAMP" TIMESTAMP(9);
    "IBAL" NUMBER(14,2);
BEGIN
    CREATETRANSACTION("TID", "TSTAMP", 3969255661, "IBAL", 10);
END;

DELETE FROM bankofben_transactions;
/*
DECLARE
    TEST NUMBER;
BEGIN
    SELECT "Balance" INTO TEST FROM bankofben_accounts WHERE "Account Number"=3969255661;
    INSERT INTO bankofben_transactions("Transaction ID", "Timestamp", "Account Number", "Initial Balance", "Amount", 
        "Final Balance") VALUES('TR'||TRANSACTION_SEQ.nextval, CURRENT_TIMESTAMP, 3969255661, TEST, 100, 
        TEST+100);
END;
*/

SET SERVEROUTPUT ON;

DECLARE
    TEST NUMBER;
BEGIN
    SELECT "Balance" INTO TEST FROM bankofben_accounts WHERE "Account Number"=3969255661;
    DBMS_OUTPUT.PUT_LINE(TEST);
END;

DECLARE
    TEST TIMESTAMP;
BEGIN
    SELECT CURRENT_TIMESTAMP INTO TEST FROM dual;
    DBMS_OUTPUT.PUT_LINE(TEST);
END;


CREATE TABLE bankofben_payments(
    "Payment ID" VARCHAR(20) PRIMARY KEY,
    "Initiator's ID" VARCHAR(20) NOT NULL,
    "Paying Account Number" NUMBER(10) NOT NULL,
    "Receiving Account Number" NUMBER(10) NOT NULL,
    "Amount" NUMBER(14,2) NOT NULL CHECK("Amount" >= 0),
    CONSTRAINT "FK Initiator's ID" FOREIGN KEY("Initiator's ID") REFERENCES bankofben_customers("Customer ID"),
    CONSTRAINT "FK Paying Account Number" FOREIGN KEY("Paying Account Number") REFERENCES bankofben_accounts("Account Number"),
    CONSTRAINT "FK Receiving Account Number" FOREIGN KEY("Receiving Account Number") REFERENCES bankofben_accounts("Account Number")
);
ALTER TABLE bankofben_payments ADD "Pending" NUMBER NOT NULL CHECK("Pending">-1 AND "Pending"<2);
ALTER TABLE bankofben_payments ADD "Transaction ID" VARCHAR(20);
ALTER TABLE bankofben_payments ADD CONSTRAINT "FK Transaction ID" FOREIGN KEY("Transaction ID") REFERENCES bankofben_transactions("Transaction ID");

DECLARE
    "Customer ID" VARCHAR(20);
BEGIN
    CREATECUSTOMER(
        "Customer ID",
        'Earl',
        'Fabian',
        'Gaston',
        'Harrison',
        to_date('10/10/1980', 'dd/mm/yyyy'),
        657483922,
        'eafaga@gmail.com',
        4078793450,
        'eafaga',
        'c0rr3ct!');
END;

DECLARE
"Account Number" NUMBER;
"Number of AcctNumbers" NUMBER;
BEGIN
    CREATEACCOUNT("Account Number", 10000, 'CUGAEA1980100020', "Number of AcctNumbers");
END;

DECLARE
    "Payment ID" VARCHAR2(20);
BEGIN
    CREATEPAYMENT("Payment ID", 'CUGAEA1980100020', 3969255661, 9180651690, 100.77, 1);
END;

CREATE TABLE bankofben_requests(
    "Request ID" VARCHAR(20) PRIMARY KEY,
    "Initiator's ID" VARCHAR(20) NOT NULL,
    "Requestor Account Number" NUMBER(10) NOT NULL,
    "Sought Account Number" NUMBER(10) NOT NULL,
    "Amount" NUMBER(14,2) NOT NULL CHECK("Amount" >= 0),
    "Pending" NUMBER NOT NULL CHECK("Pending">-1 AND "Pending"<2),
    CONSTRAINT "FK Req. Initiator's ID" FOREIGN KEY("Initiator's ID") REFERENCES bankofben_customers("Customer ID"),
    CONSTRAINT "FK Requestor Account Number" FOREIGN KEY("Requestor Account Number") REFERENCES bankofben_accounts("Account Number"),
    CONSTRAINT "FK Sought Account Number" FOREIGN KEY("Sought Account Number") REFERENCES bankofben_accounts("Account Number")
);


DECLARE
    "Request ID" VARCHAR2(20);
BEGIN
    CREATEREQUEST("Request ID", 'CUCAAA2000100000', 9180651690, 3969255661, 200.077, 1);
END;


SELECT last_number FROM SYS.all_sequences WHERE sequence_name='CUSTOMER_SEQ';

SELECT * FROM dba_users;

SELECT * FROM planets;

DELETE FROM bankofben_customers WHERE "Customer ID"='CUTEMC1990100040';

SELECT last_number FROM SYS.all_sequences WHERE sequence_name='CUSTOMER_SEQ'

SELECT CUSTOMER_SEQ.nextval FROM dual;

SELECT * FROM bankofben_employees WHERE "Username"='michaelscarn' AND "Password"='Ih34rtH0lly!';

ALTER TABLE bankofben_accounts ADD "Pending" NUMBER CHECK("Pending">-1 AND "Pending"<2);

UPDATE bankofben_accounts SET "Pending"=0;

ALTER TABLE bankofben_accounts MODIFY "Pending" DEFAULT 1;

ALTER TABLE bankofben_accounts MODIFY "Pending" NOT NULL;

DECLARE
ACTN NUMBER(10);
N_IDS NUMBER;
BEGIN
    CREATEACCOUNT(ACTN, 100, 'CUCAAA2000100000', N_IDS);
END;

DECLARE
    "Customer ID" VARCHAR(20);
BEGIN
    CREATECUSTOMER(
        "Customer ID",
        'Ian',
        'Jacob',
        'Khan',
        'Larson',
        to_date('08/16/1983', 'mm/dd/yyyy'),
        778449013,
        'iajakh@gmail.com',
        4079993450,
        'iajakh',
        '!!H0rs3!!');
END;


DECLARE
ACTN NUMBER(10);
N_IDS NUMBER;
BEGIN
    CREATEACCOUNT(ACTN, 132.18, 'CUCAAA2000100000', N_IDS);
END;

BEGIN
    removecustomerandaccounts('CUTETE1987100268');
END;

BEGIN
    removeaccountnotcustomer(7883516090);
END;

ALTER TABLE bankofben_accounts DROP CONSTRAINT "FK Customer ID";
DELETE FROM bankofben_accounts WHERE "Account Number"=7883516090;
ALTER TABLE bankofben_accounts ADD CONSTRAINT "FK Customer ID" FOREIGN KEY ("Customer ID") 
    REFERENCES bankofben_customers("Customer ID");

ALTER TABLE bankofben_transactions ADD CONSTRAINT "FK Account Number" FOREIGN KEY ("Account Number")
	  REFERENCES "ADMIN"."BANKOFBEN_ACCOUNTS" ("Account Number");
ALTER TABLE bankofben_transactions ADD CONSTRAINT "FK Other Account Number" FOREIGN KEY ("Other Account Number")
	  REFERENCES "ADMIN"."BANKOFBEN_ACCOUNTS" ("Account Number");

--This is to test a psuedo-cascade delete
ALTER TABLE bankofben_accounts DROP CONSTRAINT "FK Customer ID";
DELETE FROM bankofben_customers WHERE "Customer ID"='CUROBE1986100229';
DELETE FROM bankofben_accounts WHERE "Customer ID"='CUROBE1986100229';
ALTER TABLE bankofben_accounts ADD CONSTRAINT "FK Customer ID" FOREIGN KEY ("Customer ID") 
    REFERENCES bankofben_customers("Customer ID");