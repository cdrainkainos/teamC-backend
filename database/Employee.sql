SHOW DATABASES;

USE AgileSprints_SamB;
SHOW TABLES;

-- CREATE
CREATE TABLE Employees(
EmployeeID smallint PRIMARY KEY AUTO_INCREMENT,
FirstName varchar(50),
LastName varchar(50)
);



INSERT INTO Employees(
	FirstName,
    LastName
    )
VALUES
("Marta", "Zielonka"),
("Blazej", "Rajkowski"),
("Sam", "Bannister");
