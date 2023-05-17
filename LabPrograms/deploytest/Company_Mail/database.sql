create database companymail;

create table company_mailer_user(
	ID bigint, NAME varchar(4000), EMAIL varchar(4000),
	PASSWORD varchar(4000), GENDER varchar(4000), DOB date,
        ADDRESSLINE varchar(4000), CITY varchar(4000), STATE varchar(4000),
        COUNTRY varchar(4000), CONTACT varchar(4000), REGISTEREDDATE date, AUTHORIZED varchar(4000),
        primary key(ID)
);


create table company_mailer_message(
	ID bigint, SENDER varchar(4000), RECEIVER varchar(4000), SUBJECT varchar(4000),
        MESSAGE varchar(4000), TRASH varchar(4000), MESSAGEDATE date,
        primary key(ID)
);