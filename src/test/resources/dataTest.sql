--drop table airports; drop table planes; drop table people; drop table employees; drop table clients; drop table flights; drop table tickets; drop table users; drop table user_roles; drop table roles;

insert into People values (0, 'Krzysztof', '66032243631', '476348980', 'Bielak');

insert into Users values(1,'Krzysztof@example.com', '$2a$10$jR8eB9ffzpjFY9HgVe/SvuaHO11HtR8xZO79c7SNoUxBsTncsABQ.', 'szef', 0); --password is qwerty

insert into Roles values(1, 'ROLE_USER');
insert into Roles values(2, 'ROLE_EMPLOYEE');
insert into Roles values(3, 'ROLE_MANAGER');

insert into User_roles values(1, 1);
insert into User_roles values(1, 2);
insert into User_roles values(1, 3);