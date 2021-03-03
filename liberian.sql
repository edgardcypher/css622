--
-- File generated with SQLiteStudio v3.3.0 on Wed Mar 3 13:29:39 2021
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Account
DROP TABLE IF EXISTS Account;

CREATE TABLE Account (
    account_id              TEXT    PRIMARY KEY,
    username                TEXT    NOT NULL,
    password                TEXT    NOT NULL,
    status                  TEXT    NOT NULL,
    type_account            TEXT    NOT NULL,
    date_of_accountCreation TEXT    NOT NULL,
    person_id               INTEGER NOT NULL,
    FOREIGN KEY (
        person_id
    )
    REFERENCES Person (person_id) ON DELETE CASCADE
);

INSERT INTO Account (account_id, username, password, status, type_account, date_of_accountCreation, person_id) VALUES ('memb1', 'jdoe', 'member01', 'Active', 'Member', '2021-03-03 05:55:02', 1);
INSERT INTO Account (account_id, username, password, status, type_account, date_of_accountCreation, person_id) VALUES ('memb2', 'jEdward', 'member02', 'Active', 'Member', '2021-03-03 05:55:02', 2);

-- Table: Address
DROP TABLE IF EXISTS Address;

CREATE TABLE Address (
    address_id    INTEGER PRIMARY KEY,
    city          TEXT    NOT NULL,
    street_name   TEXT    NOT NULL,
    zip_code      TEXT    NOT NULL,
    country       TEXT    NOT NULL,
    street_number INT     NOT NULL,
    state         TEXT    NOT NULL
);

INSERT INTO Address (address_id, city, street_name, zip_code, country, street_number, state) VALUES (1, 'Malden', 'Franklin', '02148', 'USA', 34, 'MA');
INSERT INTO Address (address_id, city, street_name, zip_code, country, street_number, state) VALUES (2, 'Saugus', 'Carlon', '01906', 'USA', 12, 'MA');

-- Table: Book
DROP TABLE IF EXISTS Book;

CREATE TABLE Book (
    book_id          TEXT PRIMARY KEY,
    ISBN             TEXT NOT NULL,
    title            TEXT NOT NULL,
    Author           TEXT NOT NULL,
    subject          TEXT NOT NULL,
    publisher        TEXT NOT NULL,
    language         TEXT NOT NULL,
    publication_date TEXT NOT NULL,
    price            TEXT NOT NULL,
    date_of_purchase TEXT NOT NULL,
    rack_number      INT  NOT NULL,
    rack_location    TEXT NOT NULL,
    book_status      TEXT NOT NULL,
    pages            INT  NOT NULL,
    format           TEXT NOT NULL
);

INSERT INTO Book (book_id, ISBN, title, Author, subject, publisher, language, publication_date, price, date_of_purchase, rack_number, rack_location, book_status, pages, format) VALUES ('book1', '9781419231811', 'Learning Dancing', 'Addy Konan', 'Dancing', 'O''reilly', 'English', '2000-10-22T00:00', '$100', '2002-10-23T00:00', 4, '4CL1', 'available', 100, 'hardcover');
INSERT INTO Book (book_id, ISBN, title, Author, subject, publisher, language, publication_date, price, date_of_purchase, rack_number, rack_location, book_status, pages, format) VALUES ('book2', '9781419231821', 'Learning Cooking', 'Addy Oman', 'Cooking', 'O''reilly', 'English', '1995-11-10T00:00', '$135', '2000-11-21T00:00', 3, 'L2C1', 'available', 123, 'Hardcover');
INSERT INTO Book (book_id, ISBN, title, Author, subject, publisher, language, publication_date, price, date_of_purchase, rack_number, rack_location, book_status, pages, format) VALUES ('book3', '4781419231821', 'Learning Java', 'Herver Oman', 'Programming', 'O''reilly', 'English', '2001-10-20T00:00', '$635', '2007-10-25T00:00', 3, 'L2C1', 'available', 80, 'Hardcover');

-- Table: IssuedBook
DROP TABLE IF EXISTS IssuedBook;

CREATE TABLE IssuedBook (
    id               INTEGER PRIMARY KEY,
    borrowed_date    TEXT,
    return_book_date TEXT,
    account_id       TEXT    NOT NULL,
    book_id          TEXT    NOT NULL,
    FOREIGN KEY (
        book_id
    )
    REFERENCES Book (book_id) ON DELETE CASCADE,
    FOREIGN KEY (
        account_id
    )
    REFERENCES Account (account_id) ON DELETE CASCADE
);

INSERT INTO IssuedBook (id, borrowed_date, return_book_date, account_id, book_id) VALUES (1, '2021-03-03 07:16:51', '2021-03-13 07:16:51', 'memb1', 'book1');
INSERT INTO IssuedBook (id, borrowed_date, return_book_date, account_id, book_id) VALUES (2, '2021-03-01 07:16:51', '2021-03-20 07:16:51', 'memb2', 'book2');

-- Table: Person
DROP TABLE IF EXISTS Person;

CREATE TABLE Person (
    person_id    INTEGER PRIMARY KEY,
    name         TEXT    NOT NULL,
    email        TEXT    NOT NULL,
    phone_number TEXT    NOT NULL,
    address_id   INTEGER NOT NULL,
    FOREIGN KEY (
        address_id
    )
    REFERENCES Address (address_id) ON DELETE CASCADE
);

INSERT INTO Person (person_id, name, email, phone_number, address_id) VALUES (1, 'Joe Doe', 'jdoe@gmail.com', '781-345-2342', 1);
INSERT INTO Person (person_id, name, email, phone_number, address_id) VALUES (2, 'Joe Edward', 'jedgard@gmail.com', '235-233-2345', 2);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
