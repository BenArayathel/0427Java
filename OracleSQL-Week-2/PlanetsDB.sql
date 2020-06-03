--SQL BASICS

--CREATING TABLE

CREATE TABLE planets(
    planet_id NUMBER(5) PRIMARY KEY, --MAX ID POSSIBLE IS 99999 --PK means NOT NULL and UNIQUE
    planet_name VARCHAR2(50) NOT NULL UNIQUE, --COMMON CONTRAINS: PRIMARY, NOT NULL, UNIQUE
    has_rings NUMBER(5) CHECK (has_rings > -1 AND has_rings < 2), --NO BOOLEAN TYPES IN SQL, USE BIT VALUES
    number_of_moons NUMBER(5) CHECK (number_of_moons > -1),
    slogan VARCHAR2(50)
    );
    
DROP TABLE planets;

--INSERTING VALUE ITO TABLE
INSERT INTO planets VALUES(1, 'Mercury',0,0,'Never too close');
INSERT INTO planets VALUES(2, 'Venus',0,0,'bit warm in here init');
INSERT INTO planets VALUES(3, 'Earth',0,1,'just right!');
INSERT INTO planets VALUES(4, 'Mars',0,2,'Earth 2.0');
INSERT INTO planets VALUES(5, 'Jupiter',1,67,'BIG');
INSERT INTO planets VALUES(6, 'Saturn',1,82,'running out of imagination');
INSERT INTO planets VALUES(7, 'Uranus',1,27,NULL);
INSERT INTO planets VALUES(8, 'Neptune',1,14,'getting nippy');

--INSERT INTO planets VALUES(9, 'Nsdeptune',2,14,'getting nippy');

-- '*' all the columns
SELECT * FROM planets;
--SELECT [col1,col2,col3,etc.] FROM <table name>;
SELECT planet_name, number_of_moons FROM planets;
--ALIEAS, changes the title of the attribute in a result set
SELECT planet_name AS name, number_of_moons AS moons FROM planets;
--Don't need to use 'AS' 
SELECT planet_name wow, number_of_moons wowzer FROM planets;


--WHERE clause, basically a filter
SELECT * FROM planets WHERE planet_name = 'Mercury';
SELECT * FROM planets WHERE planet_name != 'Mercury';
SELECT * FROM planets WHERE planet_name = 'Jupiter' AND number_of_moons = 67;
SELECT * FROM planets WHERE planet_name = 'Mercury' OR number_of_moons = 67;

SELECT * FROM planets WHERE NOT number_of_moons > 0;

--SELECT * FROM planets WHERE slogan = NULL;
--SELECT * FROM planets WHERE slogan = 'null';
SELECT* FROM planets WHERE slogan IS NULL;

--LIKE keyword, use with wildcards, to find patterns

--'%' is a wildcard that is a stand-in for 0 or more characters
SELECT * FROM planets WHERE slogan LIKE 'B%';
SELECT * FROM planets WHERE slogan LIKE '%B%%%';

--'_' is a wildcard that is a stand-in for EXACTLY ONE character
SELECT * FROM planets WHERE slogan LIKE '_IG%%%%%';
SELECT * FROM planets WHERE slogan LIKE 'B__%%%';

--DEscribes the table 
DESC planets;

SELECT * FROM planets ORDER BY planet_name; --Default is ascending order
SELECT*FROM planets ORDER BY planet_name ASC; -- Default
SELECT*FROM planets ORDER BY has_rings DESC; 

--BETWEEN keyword (inclusive range)
SELECT * FROM planets WHERE planet_id BETWEEN 2 AND 4;
SELECT * FROM planets WHERE planet_id BETWEEN 2 AND 4 AND planet_name = 'Mars';

--IN keyword
--TEDIOUS
SELECT * FROM planets WHERE planet_name = 'Jupiter' OR planet_name = 'Earth' OR planet_name = 'Mars';
--EPIC FAST WAY 
SELECT * FROM planets WHERE planet_name IN('Jupiter','Earth','Mars');

--AGGREGATE functions
--a calculation operating on a group of records/entries
--COUNT, MAX, MIN, AVG, SUM, DISTINCT (there are more...)

SELECT * FROM planets;
SELECT COUNT(*) FROM planets;
SELECT COUNT(number_of_moons) FROM planets;
SELECT COUNT(DISTINCT(number_of_moons)) FROM planets;

SELECT MAX(number_of_moons) FROM planets;
SELECT AVG(number_of_moons) FROM planets;
SELECT MIN(number_of_moons) FROM planets;
SELECT SUM(number_of_moons) FROM planets;

--GROUP BY exampple:
--group by allows aggregate functions to work on smaller pockets of data
SELECT COUNT(*) AS number_of_planets FROM planets;
SELECT COUNT(*) AS number_of_planets FROM planets GROUP BY has_rings;

--HAVING example 
--where group by is NOT used, HAVING behaves like WHERE
-- WHERE filters BEFORE aggregate functions, HAVING filters after
SELECT planet_name FROM planets;
SELECT COUNT(*) AS number_of_planets FROM planets GROUP BY has_rings HAVING SUM(number_of_moons)>1000;

--SCALAR functions
/*
splitting Scalar function into (my own terminology)
numeric, character, date, conversion 
*/

--What id dual? dual is a dummy table
--used like a sysout 

SELECT 'hello world' FROM dual;
SELECT 5*7 from dual;
SELECT * FROM dual; -- default value of dual is'X'

--NUMERIC ... round(x,y)
SELECT abs(-99) from dual;
SELECT floor(88.99) from dual;
SELECT ceil(88.01) from dual;
SELECT trunc(8238.89320289340982, 1) from dual;
SELECT trunc(8238.89320289340982, -2) from dual;

--CHARACTTER
--upper(x), lower(x) and length(x)
SELECT upper('HeLlO WoRlD') FROM dual;

--DATE
-- next_day(x, 'week_Day'), last_day(x), sysdate
SELECT sysdate FROM dual;
SELECT last_day(sysdate) FROM dual;
SELECT next_day(sysdate, 'Friday') FROM dual;

--CONVERSION converts data types
--to_char(), to_date(), and more.....

--SUBQUERIES--

SELECT * FROM planets WHERE planet_id IN(
    SELECT planet_id FROM planets P WHERE P.has_rings < 1);


