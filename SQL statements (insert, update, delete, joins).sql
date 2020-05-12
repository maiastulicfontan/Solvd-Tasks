-- 20+ statements using insert, update and delete
insert into Business_Entities
values ();

insert into Locations (name, hours_of_operation, additional_information)
values ('New York City Laguardia Airport', 'Mon-Sun Open 24 hours.', 
'All arriving passengers will be directed to Terminal Shuttle buses, which will transport all car rental customers to a new Consolidated Rental 
Car Pick-Up and Drop-Off area where car rental shuttles will be waiting to pick up and drop off customers.');

insert into Departments (name, description)
values ('Sales', 'Involves all operations related to the sale process');

insert into Employee_Positions (name, description, salary, department_id)
values ('Location Manager', 'Open and closes location, supervises and motivates team members' , 46000, 1);

insert into Employee_Positions (name, description, salary, department_id)
values ('Counter Sales Agent', 'Employee who operates the computer agent control system, performs office duties and addresses customer inquiries', 25000, 1);

insert into Car_Brands(name)
values('Ford');

insert into Car_Brands (name)
values('Chevrolet');

insert into Car_Types (name, description)
values('Convertible', 'Smooth drive with style and sophistication. Cruise down the open roads to soak in the sun and fresh air.');
 
insert into Car_Types (name, description)
values('SUV', 'Our most spacious option for passenger vehicles. Additional room for luggage');

insert into Car_Models (name, transmission, number_of_seats, airbag_info, luggage_space, fuel_consumption, brand_id, type_id, cost_per_day)
values ('Mustang Convertible', 'Automatic', 4, 'Dual airbags', '2 Small Suitcases', '11 km/liters', 1, 1, 80);

insert into Car_Models (name, transmission, number_of_seats, airbag_info, luggage_space, fuel_consumption, brand_id, type_id, cost_per_day)
values ('Equinox', 'Automatic', 5, 'Dual airbags', '2 Large Suitcases, 2 Small Suitcases', '9 km/liters', 2, 2, 55);

insert into Cars (license_plate, model_year, color, model_id)
values('ABC1234', 2018, 'Obsydian Black', 1);

insert into Credit_Card_Types (name)
values('Visa');

insert into Reservation_Status (name)
values('Active');

insert into Reservation_Status (name)
values('Cancelled');

insert into Payment_Types (name)
values('Credit Card');

insert into People (business_entity_id, first_name, last_name, birth_date, national_gvt_id)
values (2, 'John', 'Smith', '1975-03-10', 15487654); 

insert into Customers (person_id, email_address)
values (2, 'johnsmith@mymail.com'); 

insert into Phone_Numbers (business_entity_id, phone_number)
values (2, '(212) 861-2456');

insert into Drivers_Licenses (customer_id, number, valid_from, expiration_date) 
values (2, 5487921, '2019-06-13', '2020-06-13');

insert into Credit_Cards (number, valid_from, expiration_date, customer_id, type_id)
values (4587, '02/20', '02/22', 2, 1);

insert into Special_Service_Types (name, cost)
values ('Child Seat', 15);

insert into Special_Services (name, type_id)
values ('Child Seat 3-6M', 1);

insert into Reservations (pickup_date_time, return_date_time, confirmation_number, pickup_location_id, return_location_id, customer_id, status_id, car_id)
values ('2020-04-30 10:30:00', '2020-05-03 14:30:00', 4587964, 1, 1, 2, 1, 1);

insert into People (business_entity_id, first_name, last_name, birth_date, national_gvt_id)
values (3, 'Chris', 'Moore', '1990-07-08', 5487123); 

insert into Employees (person_id, hire_date, position_id, location_id)
values (3, '2019-03-06', 2, 1);

insert into Rentals (reservation_id, employee_id)
values (1,3);

insert into Payments (type_id, rental_id)
values (1, 1);

update Employee_Positions
set salary = 50000
where id = 1;

update Car_Types
set name = 'SUV/Minivan'
where id = 2;

update Reservations
set cost = 400
where id = 1;

delete from Car_Models
where id = 2;

-- using joins to retrieve data from all the tables
select * from Business_Entities be
left join People p on be.id = p.business_entity_id
left join Employees emp on emp.person_id = p.business_entity_id
left join Locations loc on loc.business_entity_id = be.id
left join Addresses addr on loc.business_entity_id = addr.business_entity_id
left join Phone_Numbers pn on be.id = pn.business_entity_id
left join Employee_Positions epos on emp.position_id = epos.id 
left join Departments dep on epos.department_id = dep.id
left join Rentals r on r.employee_id = emp.person_id
left join Payments pay on pay.rental_id = r.id
left join Payment_Types payt on payt.id = pay.type_id
left join Reservations res on res.id = r.reservation_id
left join Reservation_Status resstat on resstat.id = res.status_id
left join Cars c on c.id = res.car_id
left join Car_Models cm on cm.id = c.model_id
left join Car_Brands cb on cb.id = cm.brand_id
left join Car_Types ct on ct.id = cm.type_id
left join Special_Services_Reservations ssr on res.id = ssr.reservation_id
left join Special_Services ss on ssr.special_services_id = ss.id
left join Special_Service_Types sst on sst.id = ss.type_id
left join Customers cust on p.business_entity_id = cust.person_id
left join Drivers_Licenses dl on dl.customer_id = cust.person_id
left join Credit_Cards cc on cc.customer_id = cust.person_id
left join Credit_Card_Types cct on cct.id = cc.type_id;


-- 5 queries using 'having'

-- select customers with average reservation costs above $200 
select * from Customers c
inner join Reservations r
on c.person_id = r.customer_id
group by c.person_id 
having avg(r.cost) > 200;

-- select employees associated with less than 10 rentals
select * from Employees e
left join Rentals r
on r.employee_id = e.person_id
group by e.person_id
having count(r.id) < 10;

-- select cars that have been rented more than 20 times
select * , count(rt.id)
from Cars c
left join Reservations rs
on c.id = rs.car_id
left join Rentals rt
on rs.id = rt.reservation_id
group by c.id
having count(rt.id) > 20;

-- select the most expensive car a customer has rented if the cost per day exceeds $300
select *, max(cm.cost_per_day)
from Customers cust
left join Reservations rs
on cust.person_id = rs.customer_id
left join Rentals rt
on rs.id = rt.reservation_id
left join Cars
on rs.car_id = cars.id
left join Car_Models cm
on cars.model_id = cm.id
group by cust.person_id
having max(cm.cost_per_day) > 300;


-- select pickup locations with active reservations for a total cost amount of over $20000
select *, sum(r.cost)
from Reservations r
left join Reservation_Status rs
on r.status_id = rs.id
left join Locations loc
on r.pickup_location_id = loc.business_entity_id
where rs.name = 'Active'
group by loc.business_entity_id
having sum(r.cost) > 20000;