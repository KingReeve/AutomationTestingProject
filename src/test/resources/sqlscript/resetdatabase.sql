-- needed for referential integrity enforcement
--PRAGMA foreign_keys = 1;
--
--create table users(
--	id integer primary key,
--	username text unique,
--	password text
--);
--
--create table planets(
--	id integer primary key,
--	name text,
--	ownerId integer references users(id)
--);
--
--create table moons(
--	id integer primary key,
--	name text,
--	myPlanetId integer references planets(id)
--);

DELETE FROM users;
DELETE FROM planets;
DELETE FROM moons;
INSERT INTO users (id, username, password) VALUES ('1', 'test1', 'test1');
INSERT INTO users (id, username, password) VALUES ('2', 'test', 'test');
INSERT INTO moons (id, name, myPlanetId) VALUES ('5', 'testDeletePositive', '1');
INSERT INTO moons (id, name, myPlanetId) VALUES ('4', 'testDeleteNegative', '2');
INSERT INTO planets (id, name, ownerId) VALUES ('1', 'testPlanetForMoons', '2');
INSERT INTO planets (id, name, ownerId) VALUES ('9', 'testPlanetDeleteIDPositive', '1');
INSERT INTO planets (id, name, ownerId) VALUES ('99', 'testPlanetDeleteNamePositive', '1');
INSERT INTO planets (id, name, ownerId) VALUES ('7', 'testPlanetDeleteIDNegative', '2');
--DELETE FROM moons WHERE name = 'test';
--DELETE FROM moons WHERE name = '';
--DELETE FROM moons WHERE name = 'testMoonCreationMoon';
--DELETE FROM moons WHERE name = 'RANDYRANDYRANDYRANDYRANDYRANDY';
--DELETE FROM moons WHERE name = 'STANSTANSTANSTANSTANSTANSTANST';--
INSERT INTO planets (id, name, ownerId) VALUES ('50', 'testMoonCreationPlanet', '2');
--DELETE FROM moons WHERE name = 'testMoonCreationMoon';
INSERT INTO moons (id, name, myPlanetId) VALUES ('30', 'testUniqueMoonNegative', '1');
--DELETE FROM moons WHERE name = 'testNoPlanetMoonNegative';
INSERT INTO planets (id, name, ownerId) VALUES ('51', 'testMoonSearchPlanetPositive', '1');
INSERT INTO planets (id, name, ownerId) VALUES ('52', 'testMoonSearchPlanetNegative', '2');
INSERT INTO moons (id, name, myPlanetId) VALUES ('60', 'MoonSearchPositive', '51');
INSERT INTO moons (id, name, myPlanetId) VALUES ('61', 'MoonSearchNegative', '52');
--DELETE FROM planets WHERE name = 'testPlanetCreationPositive';
--DELETE FROM planets WHERE name = '123456789012345678901234567890';
INSERT INTO planets(id, name, ownerId) VALUES ('70', 'testPCreateUniqueNegative', '1');
INSERT INTO planets (id, name, ownerId) VALUES ('71', 'testPlanetSearchPositive', '1');
INSERT INTO planets (id, name, ownerId) VALUES ('72', 'testPlanetSearchNegative', '2');