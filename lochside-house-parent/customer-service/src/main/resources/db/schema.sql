CREATE TABLE CUSTOMER_ADDRESS (
  ID          BIGINT PRIMARY KEY AUTO_INCREMENT,
  HOUSE_NUMBER VARCHAR (30),
  ADDRESS_LINE_1 VARCHAR (60),
  ADDRESS_LINE_2 varchar (60),
  POSTCODE varchar (10)
);

CREATE TABLE CUSTOMERS (
  ID         BIGINT PRIMARY KEY AUTO_INCREMENT,
  FIRST_NAME VARCHAR(30),
  SURNAME VARCHAR (30),
  CUSTOMER_ADDRESS_ID BIGINT,
  REGISTRATION_DATE DATE ,
  EMAIL varchar(60),
  FOREIGN KEY (CUSTOMER_ADDRESS_ID) REFERENCES CUSTOMER_ADDRESS(ID),

);

CREATE TABLE USERS(
  ID BIGINT PRIMARY KEY AUTO_INCREMENT,
  USERNAME VARCHAR (30),
  PASSWORD VARCHAR (30)
);

CREATE TABLE AUTHORITIES(
  ID BIGINT PRIMARY KEY AUTO_INCREMENT,
  USERNAME varchar(30),
  AUTHORITY varchar(30)
);