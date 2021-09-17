CREATE TABLE currency
(
    id   int identity primary key,
    name varchar,
    buy  float,
    sell float,
    date varchar,
    bank varchar
);