
create table person(
id INT AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
is_admin BIT ,
PRIMARY KEY (person_id)
);
--

create table credit_score(
    id INT NOT NULL UNIQUE,
    score INT NOT NULL,
    salary INT NOT NULL,
    net_worth INT NOT NULL,
    total_loans INT NOT NULL,
    repaid_loans INT NOT NULL
    occupation VARCHAR(255),
);

create table account(
id INT NOT NULL AUTO_INCREMENT,
person_id INT NOT NULL,
balance INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (person_id) REFERENCES person(id)
);


create table loan_applications(
id INT NOT NULL auto_increment,
account_id INT NOT NULL,
amount INT NOT NULL,
reason VARCHAR(255) NOT NULL,
status VARCHAR(255) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (account_id) REFERENCES account(id),
);

create table loan(
id INT NOT NULL auto_increment,
application_id INT NOT NULL,
account_id INT NOT NULL
rate_of_interest INT NOT NULL,
principal_amount INT NOT NULL,
remaining_amount INT NOT NULL,
remaining_time INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (application_id) REFERENCES loan_applications(id),
FOREIGN KEY (account_id) REFERENCES account(id),
);

create table transactions(
id INT NOT NULL auto_increment,
sender_id INT NOT NULL,
receiver_id INT NOT NULL,
amount INT NOT NULL,
context VARCHAR(255) NOT NULL,
loan_id INT ,
PRIMARY KEY (id),
FOREIGN KEY (sender_id) REFERENCES account(account_id),
FOREIGN KEY (receiver_id) REFERENCES account(account_id), 
FOREIGN KEY (loan_id) REFERENCES loan(id)
);

create table npa(
loan_id INT NOT NULL,
solution VARCHAR(255),
loss INT NOT NULL
);