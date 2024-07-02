insert into user_details(id,birth_date,name)
values(10001, current_date(),'Ranga');

insert into user_details(id,birth_date,name)
values(10002, current_date(),'James');

insert into user_details(id,birth_date,name)
values(10003, current_date(),'Maria');

insert into post(id,description,user_id)
values(20001,'Learn JPA',10001);

insert into post(id,description,user_id)
values(20002,'Learn Microservices',10001);

insert into post(id,description,user_id)
values(20003,'Learn Docker',10002);

insert into post(id,description,user_id)
values(20004,'Learn DevOps',10002);
