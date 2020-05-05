INSERT INTO planets VALUES(1, 'Mercury',0,0,'Never too close');
INSERT INTO planets VALUES(2, 'Venus',0,0,'bit warm in here init');
INSERT INTO planets VALUES(3, 'Earth',0,1,'just right!');
INSERT INTO planets VALUES(4, 'Mars',0,2,'Earth 2.0');
INSERT INTO planets VALUES(5, 'Jupiter',1,67,'BIG');
INSERT INTO planets VALUES(6, 'Saturn',1,82,'running out of imagination');
INSERT INTO planets VALUES(7, 'Uranus',1,27,NULL);
INSERT INTO planets VALUES(8, 'Neptune',1,14,'getting nippy');

SELECT planet_name FROM planets;

DESC planets;

SELECT * FROM planets ORDER BY planet_name;
SELECT*FROM planets ORDER BY has_rings DESC;

SELECT * FROM planets WHERE planet_id BETWEEN 2 AND 4;