DROP TABLE IF EXISTS animal;
DROP TABLE IF EXISTS staff;

CREATE TABLE animal (
id INT NOT NULL AUTO_INCREMENT, 
name VARCHAR(255) NOT NULL, 
staff_id INT, 
species VARCHAR(255) NOT NULL, 
gender VARCHAR(255) NOT NULL, 
PRIMARY KEY (id));

CREATE TABLE staff (
id INT NOT NULL AUTO_INCREMENT, 
firstName VARCHAR(255) NOT NULL, 
secondName VARCHAR(255) NOT NULL, 
PRIMARY KEY (id));

ALTER TABLE animal ADD CONSTRAINT FK72mt33dhhs48hf9gcqrq4fxte FOREIGN KEY (staff_id) REFERENCES staff (id);
