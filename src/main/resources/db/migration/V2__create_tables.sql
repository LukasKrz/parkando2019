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

CREATE TABLE MIEJSCEDODATKOWETYDZIEN (
  id INT AUTO_INCREMENT,
  parkId VARCHAR(5),
  parkPlaceId INT,
  studentCardIdPoniedzialek INT,
  reservationDatePoniedzialek DATE,
  studentCardIdWtorek INT,
  reservationDateWtorek DATE,
  studentCardIdSroda INT,
  reservationDateSroda DATE,
  studentCardIdCzwartek INT,
  reservationDateCzwartek DATE,
  studentCardIdPiatek INT,
  reservationDatePiatek DATE
);

CREATE TABLE MIEJSCEDODATKOWEWEEKEND (
  id INT AUTO_INCREMENT,
  parkId VARCHAR(5),
  parkPlaceId INT,
  studentCardIdSobota INT,
  reservationDateSobota DATE,
  studentCardIdNiedziela INT,
  reservationDateNiedziela DATE
);