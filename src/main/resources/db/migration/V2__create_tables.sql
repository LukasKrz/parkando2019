CREATE TABLE STUDENT (
  id INT AUTO_INCREMENT,
  cardId INT,
  name VARCHAR(255),
  surname VARCHAR(255),
  userType VARCHAR(255),
  eMail VARCHAR(255)
);

CREATE TABLE MIEJSCEPODSTAWOWE (
  id INT AUTO_INCREMENT,
  parkId VARCHAR(5),
  parkPlaceId INT,
  idStudentDzienny INT,
  dataRezerwacjiDzienny DATE,
  idStudentZaoczny INT,
  dataRezerwacjiZaoczny DATE
);

CREATE TABLE MIEJSCEDODATKOWE (
  id INT AUTO_INCREMENT,
  parkId VARCHAR(5),
  parkPlaceId INT,
  idStudentDzienny INT,
  dataRezerwacjiDzienny DATE,
  idStudentZaoczny INT,
  dataRezerwacjiZaoczny DATE
);