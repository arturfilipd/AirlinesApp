--drop table airports; drop table planes; drop table people; drop table employees; drop table clients; drop table flights; drop table tickets; drop table users; drop table user_roles; drop table roles;

insert into People values (90, 'Krzysztof', '66032243631', '476348980', 'Bielak');
insert into People values (91, 'Tester', '12121212', '12413212', 'Pracowniczy');
insert into People values (92, 'Tester2', '66352131', '476348980', 'Pracowniczy');
insert into People values (93, 'Tester22', '66663431', '47005980', 'Pracowniczy');
insert into People values (94, 'Tester222', '0000543431', '4767575280', 'Pracowniczy');

insert into Users (id,email, password, username, personid, provider, image_url, provider_id) values(91,'Krzysztof@example.com', '$2a$10$jR8eB9ffzpjFY9HgVe/SvuaHO11HtR8xZO79c7SNoUxBsTncsABQ.', 'szef', 90, 'local', null, null); --password is qwertyinsert into Roles values(1, 'ROLE_USER');
insert into Users (id,email, password, username, personid, provider, image_url, provider_id) values (92, 'employee@oftheyear.com', '$2a$10$jR8eB9ffzpjFY9HgVe/SvuaHO11HtR8xZO79c7SNoUxBsTncsABQ.', 'emploji', 91, 'local', null, null);
insert into Users (id,email, password, username, personid, provider, image_url, provider_id)  values (93, 'employee@oftheyear.com2', '$2a$10$jR8eB9ffzpjFY9HgVe/SvuaHO11HtR8xZO79c7SNoUxBsTncsABQ.', 'emploji2', 92, 'local', null, null);
insert into Users (id,email, password, username, personid, provider, image_url, provider_id)  values (94, 'tester@typ.com', '$2a$10$nqdPADSoemJ6zGil.rqTr.koPe/jz1ALTqT/adyaEbkSsQv8SAgKm', 'passchanger', 93, 'local', null, null);
insert into Users (id,email, password, username, personid, provider, image_url, provider_id)  values (95, 'em462e@oftheyear.com2', '$2a$10$3sJgjkLapSGX9sJ7TYKcSuwmEyummpCzU5IuOvzTagnXQ5nInHqI.', 'testuser2', 94, 'local', null, null);

insert into Roles values(1, 'ROLE_USER');
insert into Roles values(2, 'ROLE_EMPLOYEE');
insert into Roles values(3, 'ROLE_MANAGER');

insert into User_roles values(91, 1);
insert into User_roles values(91, 2);
insert into User_roles values(91, 3);
insert into User_roles values(94, 1);

insert into Employees values(91, null, '2020-06-21', 'Employee', 4000, 91);
insert into Employees values(92, null, '2020-06-21', 'Employee', 4000, 92);

--PlaneController Tests
insert into Airports values(100, 'AIRPORT A', 'ARPA', 'CITY A');
insert into Airports values(101, 'AIRPORT A', 'ARPB', 'CITY B');

insert into Planes values(102, 'PlanesListTest', 100, 20, 100);

insert into Flights values(100, '2020-06-23', 400, 200, '2020-06-22', 101, 102, 100);
insert into Flights values(111, '2020-01-21', 400, 200, '2020-01-21', 101, 102, 100);
insert into Flights values(222, '2020-01-21', 400, 200, '2020-01-21', 101, 102, 100);

insert into Clients values(500, 91);

insert into Tickets(id, class_name, paid, price, seat, clientId, flightId) values(100, 'Business', false, 500, null, 500, 100);
insert into Tickets(id, class_name, paid, price, seat, clientId, flightId) values(101, 'Business', false, 500, null, 500, 100);
