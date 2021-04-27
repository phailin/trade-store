DROP TABLE IF EXISTS trades;

CREATE TABLE trades (
  ID BIGINT AUTO_INCREMENT  PRIMARY KEY,
  TRADE_ID VARCHAR(100) NOT NULL,
  VERSION INT NOT NULL,
  COUNTER_PARTY_ID VARCHAR(100) NOT NULL,
  BOOK_ID VARCHAR(100) NOT NULL,
  MATURITY_DATE DATE NOT NULL,
  CREATED_DATE DATE NOT NULL,
  EXPIRED BOOLEAN NOT NULL
);

--INSERT INTO trades (tradeId, version, counterPartyId, bookId, maturityDate, createdDate, expired) VALUES
--('T1', 1, 'CP-1', 'B1', parsedatetime('17/04/2021', 'dd/MM/yyyy'), CURRENT_DATE, false );