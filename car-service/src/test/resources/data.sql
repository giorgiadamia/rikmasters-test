insert into cars (brand, driver, manufacturer, state_number, vin, year_of_issue, id)
values ('Mercedes-Benz', null, 'Germany', 'X228AM197', 'VIN1234', 2009, 99);

insert into details (serial_number, id)
values ('123454', 99);

insert into cars_details (car_id, details_id)
values (99, 99);