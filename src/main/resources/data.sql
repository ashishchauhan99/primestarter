insert into prime_user (username, password, first_name, last_name, date_of_birth, role) 
values('admin', '$2a$10$VlRLLgZVPaxRMYC7cWSw6uii2hyQDT5o8fR887Ii97EAxfQE/w85W', 'ashish', 'kumar',  PARSEDATETIME('23-MAR-1987 11.55.36.558000000 PM','dd-MMM-yyyy hh.mm.ss.SSS a','en'), 'ADMIN');
-- password=pw