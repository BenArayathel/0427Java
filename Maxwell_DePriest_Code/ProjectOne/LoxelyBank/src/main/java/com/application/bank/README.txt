JRE System Library - JavaSE-1.8

Dependencies used: 

  <dependencies>
  		<!-- https://mvnrepository.com/artifact/junit/junit -->
		<dependency>
    		<groupId>junit</groupId>
    		<artifactId>junit</artifactId>
    		<version>4.12</version>
   		 <scope>test</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/log4j/log4j -->
		<dependency>
    		<groupId>log4j</groupId>
    		<artifactId>log4j</artifactId>
    		<version>1.2.17</version>
		</dependency>
		
		<dependency>
   			<groupId>com.oracle.jdbc</groupId>
    		<artifactId>ojdbc8</artifactId>
    		<version>12.2.0.1</version>
		</dependency>
		
		<dependency>
    		<groupId>junit</groupId>
    		<artifactId>junit</artifactId>
    		<version>4.12</version>
   		 	<scope>test</scope>
		</dependency>

 	 </dependencies>


Setting up the tables:

CREATE TABLE bankuser(id string not null, name string, email string, phone string, password string, status string, CONSTRAINT bankuser_pk PRIMARY KEY (email));
CREATE TABLE bankaccount(id string not null, savingsnumber string not null, checking number string not null, checkingamount string not null, savingsamount string not null, active string string not null, email string not null, CONSTRAINT fk_email FOREIGN KEY (email) REFERENCES bankuser(email) ON DELETE CASCADE); 

Sample SQL Queries:

UPDATE bankuser SET password = 'shikehi' WHERE email = 'ben@email.com';
UPDATE bankaccount SET savingsnumber = '1748573' WHERE email = 'ben@email';
UPDATE bankuser SET name - 'Leia Organa-Solo' WHERE email = 'leia@email.com';

SELECT * FROM bankuser;
SELECT * FROM bankaccount;

SELECT * FROM bankuser WHERE email = 'han@email.com';
SELECT * FROM bankuser WHERE name = 'han solo';
SELECT * FROM bankaccount WHERE email = 'han@email.com';

DELETE FROM bankuser WHERE email = 'ben@email.com';


