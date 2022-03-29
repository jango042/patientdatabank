CREATE TABLE patient (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    patient_name VARCHAR(225) NOT NULL,
    age INT,
    last_visit_date DATE NOT NULL,
    PRIMARY KEY (id)
);