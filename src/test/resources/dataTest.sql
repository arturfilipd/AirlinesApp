--drop table airports; drop table planes; drop table people; drop table employees; drop table clients; drop table flights; drop table tickets; drop table users; drop table user_roles; drop table roles;

insert into People values (0, 'Krzysztof', '66032243631', '476348980', 'Bielak');

insert into Users values(1,'Krzysztof@example.com', '$2a$10$jR8eB9ffzpjFY9HgVe/SvuaHO11HtR8xZO79c7SNoUxBsTncsABQ.', 'szef', 0); --password is qwerty

insert into Roles values(1, 'ROLE_USER');
insert into Roles values(2, 'ROLE_EMPLOYEE');
insert into Roles values(3, 'ROLE_MANAGER');

insert into User_roles values(1, 1);
insert into User_roles values(1, 2);
insert into User_roles values(1, 3);

--PlaneController Tests
insert into Airports values(100, 'AIRPORT A', 'ARPA', 'CITY A');
insert into Airports values(101, 'AIRPORT A', 'ARPB', 'CITY B');
insert into Planes values(102, 'PlanesListTest', 100, 20, 100);
insert into Clients values(500, 1);
insert into Flights values(100, '2020-06-23', 400, 200, '2020-06-22', 101, 102, 100);
insert into Flights values(111, '2020-01-21', 400, 200, '2020-01-21', 101, 102, 100);
