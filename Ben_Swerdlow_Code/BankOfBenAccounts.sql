SELECT TRUNC(DBMS_RANDOM.VALUE()*1e10) FROM DUAL;

SET SERVEROUTPUT ON;

DROP TABLE bankofben_accounts;

CREATE TABLE bankofben_accounts(
    "Account Number" NUMBER(10) PRIMARY KEY,
    "Balance" NUMBER(14,2) NOT NULL CHECK("Balance" >= 0),
    "Customer ID" VARCHAR(20) NOT NULL,
    CONSTRAINT "FK Customer ID" FOREIGN KEY("Customer ID") REFERENCES bankofben_customers("Customer ID")
);

DECLARE
"Account Number" NUMBER;
"Number of AcctNumbers" NUMBER;
BEGIN
    CREATEACCOUNT("Account Number", 0, 'CUCAAA2000100000', "Number of AcctNumbers");
END;

UPDATE bankofben_customers SET "Application Pending"=0 WHERE "Customer ID"='CUCAAA200010000';

CREATE bankofben_transactions(
    "TIME" TIMESTAMP_VALUE PRIMARY KEY
    
)