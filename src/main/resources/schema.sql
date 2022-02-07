CREATE TABLE KWStatesPrices
(
  state VARCHAR(255) PRIMARY KEY,
  price DOUBLE(255)
)
AS
SELECT *
FROM CSVREAD('/home/abdullah/Desktop/KWStatesPrices.csv');