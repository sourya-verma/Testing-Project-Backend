# --- !Ups

CREATE TABLE "student"("id" int PRIMARY KEY AUTO_INCREMENT,"name" varchar(200) , "email" varchar(200) unique ,"university_name" varchar(200));
insert into "student" values(1,'Aditya','aditya@gmail.com','hcu');
insert into "student" values(2,'Aman','aman@gmail.com','du');


# --- !Downs
DROP TABLE "student";


