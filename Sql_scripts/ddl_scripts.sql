CREATE DATABASE fraudanalytics;
CREATE TABLE fraudanalytics.customer (
  customerid INT NOT NULL,
  name VARCHAR(45) NULL,
  dob DATE NULL,
  emailid VARCHAR(150) NULL,
  phonenumber VARCHAR(15) NULL,
  annualincome DOUBLE NULL,
  gender VARCHAR(10) NULL,
  address VARCHAR(200) NULL,
  PRIMARY KEY (customerid))
COMMENT = 'customer profile master table';
CREATE TABLE fraudanalytics.account (
  accountid INT NOT NULL,
  customerid INT NULL,
  cardtype VARCHAR(45) NULL,
  cardnumber INT NULL,
  PRIMARY KEY (accountid),
  UNIQUE INDEX accountid_UNIQUE (accountid ASC),
  INDEX customerid_idx (customerid ASC),
  CONSTRAINT customerid
    FOREIGN KEY (customerid)
    REFERENCES fraudanalytics.customer (customerid)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'account profile';
CREATE TABLE fraudanalytics.pointofsales (
  posid INT NOT NULL,
  description VARCHAR(45) NULL,
  categoryid INT NULL,
  isonline TINYINT(1) NULL,
  PRIMARY KEY (posid))
COMMENT = 'This table consists of all the point of sale locations.';
CREATE TABLE fraudanalytics.suspected_transactions (
  accountid INT NOT NULL,
  transactionid VARCHAR(45) NOT NULL,
  cardnumber INT NULL,
  amount DOUBLE NULL,
  created_on DATETIME NULL,
  modified_on DATETIME NULL,
  fraud_status VARCHAR(45) NULL,
  transactiondate VARCHAR(20) NULL,
  PRIMARY KEY (accountid, transactionid));
