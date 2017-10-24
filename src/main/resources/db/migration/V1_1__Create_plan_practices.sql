CREATE TABLE plan_practices
(
  id       INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  length   INT,
  multiple INT,
  exercise VARCHAR(45),
  split    TIME
);