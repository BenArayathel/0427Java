--Only highlighted lines will run
--Best practice to highlight line by line and run each line
--Can run line-by-line by putting blinking cursor on line and press ctrl-enter

--CREATING A NEW USER
CREATE USER puser IDENTIFIED BY p4ssw0rd;

--Grant our created user DBA (database access)
GRANT CONNECT, RESOURCE to puser;
GRANT DBA TO puser WITH ADMIN OPTION;

--SEE ALL USERS
SELECT * FROM all_users;

--WHATEVER YOU DO, !!!!!!!!!!!DON'T--------DELETE-----------SYSTEM---------OR----------SYS!!!!!!!!!!!!!

SELECT * FROM DBA_users;

--Don't run the following commands
--make panetsDB and run this
--SQL BASICS

CREATE TABLE planets(
    planet_id NUMBER(5) PRIMARY KEY, --MAX ID POSSIBLE IS 99999 --PK means NOT NULL and UNIQUE
    planet_name VARCHAR2(50) NOT NULL UNIQUE, --COMMON CONSTRAINS: PIMARY, NOT NULL, UNIQUE
    --SQL doesn't really support booleans, instead use bit values 0 and 1
    has_rings NUMBER(5) CHECK (has_rings > -1 AND has_rings < 2), 
    number_of_moons NUMBER(5) check (number_of_moons >= 0),
    slogan VARCHAR2(50)--There are no constraints to slogan, so it can accept any value (including null)
);

--removes the values and all the table structures
---like throwing away a glass of water instead of emptying the glass and keeping the glass
DROP TABLE planets;

--INSERTING VALUE INTO TABLE
INSERT INTO planets VALUES(1, 'Mercury',0,0,'Never too close');
INSERT INTO planets VALUES(2, 'Venus',0,0,'bit warm in here init');
INSERT INTO planets VALUES(3, 'Earth',0,1,'just right!');
INSERT INTO planets VALUES(4, 'Mars',0,2,'Earth 2.0');
INSERT INTO planets VALUES(5, 'Jupiter',1,67,'BIG');
INSERT INTO planets VALUES(6, 'Saturn',1,82,'running out of imagination');
INSERT INTO planets VALUES(7, 'Uranus',1,27,NULL);
INSERT INTO planets VALUES(8, 'Neptune',1,14,'getting nippy');

-- '*' == all the columns of a table
SELECT * FROM planets;
--SELECT [col1, col2, col3, etc.] FROM <table_name>
SELECT planet_name, number_of_moons FROM planets;
--ALIAS lets you re-title attributes in the result set (don't have to be related to the original column name)
SELECT planet_name AS name, number_of_moons AS moons FROM planets;

--WHERE clause: basically a filter
SELECT * FROM planets WHERE planet_name = "Mercury";
SELECT * FROM planets WHERE planet_name != "Mercury";
SELECT * FROM planets WHERE planet_name = "Jupiter" AND number_of_moons > 0;
SELECT * FROM planets WHERE NOT number_of_moons > 0;
SELECT * FROM planets WHERE slogan IS NULL;

-- '%' keyword, use with wildcards, to find patterns, called LIKE
SELECT * FROM planets WHERE slogan LIKE 'B%';--0 or more B's at the start the string

-- '_' is a stand-in for EXACTLY ONE character
SELECT * FROM planets WHERE slogan LIKE '_IG%%%%';

-- DESC is a description of the table
DESC planets;
-- gives attribute names, if they cannot be null, and data type

--
SELECT * FROM planets ORDER BY planet_name; -- default ordering is ascending order
SELECT * FROM planets ORDER BY planet_name ASC; -- same as default
SELECT * FROM planets ORDER BY planet_name DESC;
-- orders descending (same as DESC for description, have to look into context to distinguish)

--BETWEEN keyword (inclusive range)
SELECT * FROM planets WHERE planet_id BETWEEN 2 AND 4;
SELECT * FROM planets WHERE planet_id BETWEEN 2 AND 4 AND planet_name = 'Mars';

--IN keyword
--TEDIOUS!!!!!
SELECT * FROM planets WHERE planet_name = 'Jupiter' OR planet_name = 'Earth' OR planet_name = 'Mars';
-- MUCH BETTER!!!!!
SELECT * FROM planets WHERE planet_name IN('Jupiter', 'Earth', 'Mars');

-- AGGREGATE FUNCTIONS
-- a calculation operating on a group of records and entries
-- common agg functions
----COUNT, MAX, MIN, AVERAGE, SUM, DISTINCT

SELECT * FROM planets;
SELECT COUNT(*) FROM planets;
SELECT COUNT(number_of_moons) FROM planets;
-- count the number of distinct entries for number_of_moons
SELECT COUNT(DISTINCT(number_of_moons)) FROM planets;
SELECT MAX(number_of_moons) FROM planets;
SELECT AVG(number_of_moons) FROM planets;
SELECT SUM(number_of_moons) FROM planets;
SELECT MIN(number_of_moons) FROM planets;

-- GROUP BY allows aggregate functions to work on smaller pockets of data
-- how many planets have rings or do not have rings?
SELECT COUNT(*) AS number_of_planets FROM planets GROUP BY has_rings;
--?????SELECT COUNT(*) AS number_of_planets FROM planets GROUP BY has_rings GROUP BY number_of_moons;?????

-- HAVING example
-- where group by is NOT used, HAVING behaves like WHERE
-- WHERE filters BEFORE aggregate functions, HAVING filters after agg functions
SELECT COUNT(*) FROM planets GROUP BY has_rings HAVING SUM(number_of_moons)>2;

/*
SCALAR functions
Ben A.'s categories:
numeric, character, date, and conversion
*/

-- What is dual? dual is a dummy table
-- use like sysout

SELECT 'hello world' FROM dual;
SELECT 5*7 FROM dual;
SELECT * FROM dual;--default value of dual is x

--numeric
SELECT abs(-99) FROM dual;
SELECT floor(99.9) FROM dual;--99
SELECT ceiling(99.9) FROM dual;--100
SELECT trunc(8238.1234908, 4) FROM dual; -- truncates input to 4 decimal points
SELECT trunc(8238.1234908, 2) FROM dual; -- truncates input to 2 decimal points
SELECT trunc(8238.1234908, 1) FROM dual; -- truncates input to 1 decimal points
SELECT trunc(8238.1234908, 0) FROM dual; -- truncates input to 0 decimal points
SELECT trunc(8238.1234908, -1) FROM dual; -- truncates input to -1 decimal points
SELECT round(8238.1234908, -1) FROM dual; -- rounds input to -1 decimal points
SELECT abs(-99) FROM dual;
SELECT abs(-99) FROM dual;
SELECT abs(-99) FROM dual;

--character
--upper(x), lower(x), length(x)
-- same dual syntax

--DATE
-- next_day(x, 'week_Day'), last_day(x), sysdate
SELECT sysdate FROM dual;-- today's date
SELECT last_day(sysdate) from dual;--the last day in the current month
SELECT next_day(sysdate, 'Friday') FROM dual;--the next day that is a Friday 

--CONVERSION converts data types
--to_char(), to_date(), and more.....



-- SUBQUERIES --
SELECT * FROM planets WHERE planets_id IN(
    SELECT planet_id FROM planets P WHERE P.has_rings < 1);