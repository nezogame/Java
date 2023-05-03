CREATE TABLE IF NOT EXISTS employers (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    surname varchar(30) NOT NULL,
    phone varchar(20) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS vacancies (
    id int NOT NULL AUTO_INCREMENT,
    employer_id int NOT NULL,
    position varchar(50) NOT NULL,
    description varchar(1000) NOT NULL,
    salary int NOT NULL,
    publication_date date NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(employer_id) REFERENCES employers(id) ON DELETE CASCADE ON UPDATE CASCADE
    );