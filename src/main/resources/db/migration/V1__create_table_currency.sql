CREATE TABLE currency (
    id INT AUTO_INCREMENT PRIMARY KEY,          -- Primary key column
    currency VARCHAR(3),       -- Currency as a string
    buy FLOAT,                   -- Buy rate as a float
    sell FLOAT,                  -- Sell rate as a float
    date VARCHAR(50),           -- Date as a string
    bank VARCHAR(50)            -- Bank as a string
);