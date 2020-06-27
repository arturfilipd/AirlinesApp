--NUKE--
--drop table airports; drop table planes; drop table people; drop table employees; drop table clients; drop table flights; drop table tickets; drop table users; drop table user_roles; drop table roles;

insert into Airports values (0, 'Chopin Airport', 'Warsaw', 'WAW');
insert into Airports values (1, 'Lech Walesa Airport', 'Gdansk', 'GDN');
insert into Airports values (2, 'Katowice International', 'Katowice', 'KTW');
insert into Airports values (3, 'Heringsdorf Airport', 'Heringsdorf', 'HDF');

insert into Planes(id, plane_name, seats_in_economic, seats_in_buisness, apid) values (0, 'Airbus 220', 80, 40, 0);
insert into Planes(id, plane_name, seats_in_economic, seats_in_buisness, apid) values (1, 'Boeing 787', 160, 80, 1);
insert into Planes(id, plane_name, seats_in_economic, seats_in_buisness, apid) values (2, 'Boeing 777', 200, 100, 2);
insert into Planes(id, plane_name, seats_in_economic, seats_in_buisness, apid) values (3, 'Boeing 777', 200, 100, 3);

insert into People values (0, 'Krzysztof', '66032243631', '476348980', 'Bielak');
insert into People values (1, 'Adam', '74011868355', '458741256', 'Kisiel');
insert into People values (2, 'Wojciech', '51100679694', '458741256', 'Kamien');
insert into People values (3, 'Beata', '61091943915', '458965215', 'Niebieska');
insert into People values (4, 'Aleksandra', '56081569853', '789123654', 'Drzewo');

insert into Employees values(0, null, '2020-06-21', 'Manager', 3000, 0);
insert into Employees values(1, null, '2020-06-21', 'Employee', 4000, 1);
insert into Employees values(2, null, '2020-06-21', 'Employee', 5000, 2);

insert into Clients values(0, 3);
insert into Clients values(1, 4);

insert into Flights values(0, '2020-06-23', 400, 200, '2020-06-22', 1, 0, 0);
insert into Flights values(1, '2020-06-23', 400, 200, '2020-06-22', 2, 1, 1);
insert into Flights values(2, '2020-06-23', 400, 200, '2020-06-22', 3, 2, 2);

insert into Tickets(id, class_name, paid, price, seat, clientId, flightId) values(0, 'Economic', false, 200, null, 0, 0);
insert into Tickets(id, class_name, paid, price, seat, clientId, flightId) values(1, 'Business', true, 500, null, 1, 1);

insert into Users(id,email, password, username, personid, provider, image_url, provider_id) values(1,'Krzysztof@example.com', '$2a$10$jR8eB9ffzpjFY9HgVe/SvuaHO11HtR8xZO79c7SNoUxBsTncsABQ.', 'szef', 0,'local', null, null); --password is qwerty
insert into Users(id,email, password, username, personid, provider, image_url, provider_id) values(2,'Adam@example.com', 'qwerty', 'Adam', 1,'local', null, null);
insert into Users(id,email, password, username, personid, provider, image_url, provider_id) values(3,'Wojciech@example.com', 'qwerty', 'Wojciech', 2,'local', null, null);
insert into Users(id,email, password, username, personid, provider, image_url, provider_id) values(4,'Beata@example.com', 'qwerty', 'Beata', 3,'local', null, null);
insert into Users(id,email, password, username, personid, provider, image_url, provider_id) values(5,'Aleksandra@example.com', 'qwerty', 'Aleksandra', 4,'local', null, null);

insert into Roles values(1, 'ROLE_USER');
insert into Roles values(2, 'ROLE_EMPLOYEE');
insert into Roles values(3, 'ROLE_MANAGER');

insert into User_roles values(1, 3);
insert into User_roles values(1, 1);
insert into User_roles values(2, 2);
insert into User_roles values(2, 1);
insert into User_roles values(3, 1);
insert into User_roles values(3, 2);
insert into User_roles values(4, 1);
insert into User_roles values(5, 1);






