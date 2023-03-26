-- Add person fixtures
INSERT INTO person (first_name, last_name, address, phone, email)
VALUES ('Bob', 'Mack', '23 Somewhere, New York, NY', '212-215-1928', 'bob@mack.com'),
       ('Steve', 'Stevens', '13 Nadir St, London, England, UK', '+44-20-7224-3688', 'steve@stevens.com');

-- Add scrawl fixtures
INSERT INTO scrawl (title, body, create_date, status, person_id)
VALUES ('Pale Fire', 'Only when making water. Then as now I walked at my ouwn risk: whipped by the bough', NOW(), 'Active', (SELECT id FROM person WHERE email = 'bob@mack.com')),
       ('Tao Te Ching', 'One who excels as a warior does not appear formidable; One who excels in fighting is never roused in anger;', NOW(), 'Active', (SELECT id FROM person WHERE email = 'steve@stevens.com'));
