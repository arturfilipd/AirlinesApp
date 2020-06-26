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


--FlightController Tests
insert into Flights values(100, '2020-06-23', 400, 200, '2020-06-22', 101, 102, 100);
insert into Flights values(111, '2020-01-21', 400, 200, '2020-01-21', 101, 102, 100);
insert into Flights values(222, '2020-01-21', 400, 200, '2020-01-21', 101, 102, 100);

--TicketController Tests
insert into Clients values(500, 1);
insert into Tickets(id, class_name, paid, price, seat, clientId, flightId) values(100, 'Business', false, 500, null, 500, 100);
insert into Tickets(id, class_name, paid, price, seat, clientId, flightId) values(101, 'Business', false, 500, null, 500, 100);

--EmployeeController Tests
insert into People values (1, 'Tester', '12121212', '12413212', 'Pracowniczy');
insert into Users values (2, 'employee@oftheyear.com', '$2a$10$jR8eB9ffzpjFY9HgVe/SvuaHO11HtR8xZO79c7SNoUxBsTncsABQ.', 'emploji', '1');
insert into Employees values(1, null, '2020-06-21', 'Employee', 4000, 1);
insert into People values (2, 'Tester2', '66352131', '476348980', 'Pracowniczy');
insert into Users values (3, 'employee@oftheyear.com2', '$2a$10$jR8eB9ffzpjFY9HgVe/SvuaHO11HtR8xZO79c7SNoUxBsTncsABQ.', 'emploji2', '2');
insert into Employees values(2, null, '2020-06-21', 'Employee', 4000, 2);

--Auth Password Change Test
insert into People values (3, 'Tester22', '66663431', '47005980', 'Pracowniczy');
insert into Users values (4, 'tester@typ.com', '$2a$10$4Ng90KA8NqeAL/5iiJaVW.cwcV/P50BlTMy7TkfvQqCIUD7L/qXjO', 'passchanger', '3');
insert into People values (4, 'Tester222', '0000543431', '4767575280', 'Pracowniczy');
insert into Users values (5, 'em462e@oftheyear.com2', '$2a$10$3sJgjkLapSGX9sJ7TYKcSuwmEyummpCzU5IuOvzTagnXQ5nInHqI.', 'testuser2', '4');
insert into User_roles values(4, 1);