use fraudanalytics;
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10000,'Charles Zaiontz','1972-01-31','cust1@gmail.com',9820123276,650000,'M','503, H.A tower, Piplene Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10001,'Amol  Bhosale','1987-01-27','cust2@gmail.com',9820123276,800000,'M','305, I.A tower, L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10002,'Sumit Desai','1992-01-26','cust3@gmail.com',9820123276,500000,'M','101, A.I Building, P.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10003,'Rachana Naik','1962-02-02','cust4@gmail.com',9820123276,500000,'F','111, B.I Apartment, L.T Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10004,'Thenappan Palaniappan','1957-02-03','cust5@gmail.com',9820123276,500000,'F','201, C.I tower C Wing, L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10005,'Ashish  Sharma','1995-01-25','cust6@gmail.com',9820123276,500000,'M','303, D.I Apartment, L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10006,'Ashay Shingornikar','1985-01-27','cust7@gmail.com',9820123276,500000,'M','408, E.I Apartment, L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10007,'Swapnil More','1975-01-30','cust8@gmail.com',9820123276,500000,'M','901, F.A.I tower, L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10008,'Harshal Tahasildar','1965-02-01','cust9@gmail.com',9820123276,500000,'M','101, M.K tower, L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10009,'Girijesh Kumar','1955-02-04','cust10@gmail.com',9820123276,500000,'M','101, N.K.J tower, L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10010,'Prashant  Sharma','1994-01-25','cust11@gmail.com',9820123276,500000,'M','202, A.P Building, L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10011,'Ankush Srivastava','1984-01-28','cust12@gmail.com',9820123276,500000,'M','311, P.A tower, L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10012,'Vaibhav  Vartak','1974-01-30','cust13@gmail.com',9820123276,500000,'M','312, N.G Building,L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10013,'Monika Patil','1964-02-02','cust14@gmail.com',9820123276,500000,'F','311, K.D.M.C Apartment, L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10014,'Sudhakar Nandigam','1954-02-04','cust15@gmail.com',9820123276,500000,'M','411, M.C.G.M tower, L.A Road');
INSERT INTO customer (customerid,name, dob, emailid,phonenumber,annualincome,gender, address) VALUES (10015,'Aparna  Naik','1993-01-25','cust16@gmail.com',9820123276,500000,'F','901, K.S Apartment, L.A Road');


update customer set emailid='ankesh.somani@mastek.com' where customerid=10000;
update customer set emailid='abhishek.murkute@mastek.com' where customerid=10001;
update customer set emailid='sourabh.jain@mastek.com' where customerid=10002;





INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (100,10000,'VISA',4567);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (101,10001,'VISA',2351);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (102,10002,'VISA',9854);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (103,10003,'VISA',9698);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (104,10004,'VISA',9769);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (105,10005,'MASTERCARD',9820);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (106,10006,'MASTERCARD',8721);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (107,10007,'MASTERCARD',8520);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (108,10008,'MASTERCARD',9631);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (109,10009,'MASTERCARD',7410);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (110,10010,'VISA',2469);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (111,10011,'VISA',2475);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (112,10012,'MASTERCARD',9536);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (113,10013,'VISA',8574);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (114,10014,'MASTERCARD',5910);
INSERT INTO account(accountid,customerid,cardtype,cardnumber) VALUES (115,10015,'MASTERCARD',5132);


INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1234,0,'SAI COFEE HOUSE',0);
INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1235,0,'SAI COFEE HOUSE',0);
INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1236,0,'SAI COFEE HOUSE',0);
INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1237,0,'SAI COFEE HOUSE',0);
INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1238,0,'SAI COFEE HOUSE',0);
INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1239,0,'SAI COFEE HOUSE',0);
INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1240,1,'SAI COFEE HOUSE',0);
INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1241,1,'SAI COFEE HOUSE',0);
INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1242,1,'SAI COFEE HOUSE',0);
INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1243,1,'SAI COFEE HOUSE',0);
INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1244,1,'SAI COFEE HOUSE',0);
INSERT INTO pointofsales(posid,categoryid,description,isonline) VALUES (1245,1,'SAI COFEE HOUSE',0);



select  trans2.id,count(trans1.transactionid) as cnt,
((sum(trans1.amount)+trans2.amount)/(count(trans1.transactionid)+1)) as avg
from transactions trans1,transactions trans2
where UNIX_TIMESTAMP(trans1.transtime)<=
(select UNIX_TIMESTAMP(transtime) from transactions where id=trans2.id) 
and UNIX_TIMESTAMP(trans1.transtime)>
(select UNIX_TIMESTAMP(transtime)-(60*60*24*30) from transactions where id=trans2.id)
and trans1.transactionscol is null
and trans2.id >=32
group by trans2.id
order by trans2.id asc;