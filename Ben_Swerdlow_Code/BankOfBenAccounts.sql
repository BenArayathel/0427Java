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