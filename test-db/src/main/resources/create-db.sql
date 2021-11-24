DROP TABLE IF EXISTS patient;

DROP TABLE IF EXISTS nurse;

CREATE TABLE nurse(
    id int NOT NULL auto_increment,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    CONSTRAINT nurse_pk PRIMARY KEY (id)
);

CREATE TABLE patient(
    id int NOT NULL auto_increment,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    diagnosis varchar(100),
    illness_date date NOT NULL,
    nurse_id int,
    CONSTRAINT patient_pk PRIMARY KEY (id),
    CONSTRAINT patient_nurse_fk FOREIGN KEY (nurse_id) REFERENCES nurse(id)
);