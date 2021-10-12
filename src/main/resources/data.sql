insert into prime_user (username, password, first_name, last_name, email, date_of_birth, role) 
values('admin', '$2a$10$VlRLLgZVPaxRMYC7cWSw6uii2hyQDT5o8fR887Ii97EAxfQE/w85W', 'ashish', 'kumar',  'a.kumar@primestarter.de', PARSEDATETIME('23-MAR-1987 11.55.36.558000000 PM','dd-MMM-yyyy hh.mm.ss.SSS a','en'), 'ADMIN');
-- password=pw

-- BRAND

INSERT INTO brand(id,brand_name) VALUES(1,'Electra');
INSERT INTO brand(id,brand_name) VALUES(2,'Haro');
INSERT INTO brand(id,brand_name) VALUES(3,'Heller');
INSERT INTO brand(id,brand_name) VALUES(4,'Pure Cycles');
INSERT INTO brand(id,brand_name) VALUES(5,'Ritchey');
INSERT INTO brand(id,brand_name) VALUES(6,'Strider');
INSERT INTO brand(id,brand_name) VALUES(7,'Sun Bicycles');
INSERT INTO brand(id,brand_name) VALUES(8,'Surly');
INSERT INTO brand(id,brand_name) VALUES(9,'Trek');

INSERT INTO category(id,category_name) VALUES(1,'Children Bicycles');
INSERT INTO category(id,category_name) VALUES(2,'Comfort Bicycles');
INSERT INTO category(id,category_name) VALUES(3,'Cruisers Bicycles');
INSERT INTO category(id,category_name) VALUES(4,'Cyclocross Bicycles');
INSERT INTO category(id,category_name) VALUES(5,'Electric Bikes');
INSERT INTO category(id,category_name) VALUES(6,'Mountain Bikes');
INSERT INTO category(id,category_name) VALUES(7,'Road Bikes');

INSERT INTO product(id, product_name, brand_id, category_id, model_year, list_price) VALUES(1,'Trek 820 - 2016',9,6,2016,379.99)