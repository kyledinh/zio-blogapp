-- Add person fixtures
INSERT INTO person (first_name, last_name, address, phone, email)
VALUES ('Marcus', 'Aurelius', '1 Birdwell Island, New York, NY', '212-215-1928', 'marcus@rome.com'),
       ('Sherlock', 'Holmes', '221B Baker St, London, England, UK', '+44-20-7224-3688', 'sherlock@sherlockholmes.com');

-- Add scrawl fixtures
INSERT INTO scrawl (title, body, create_date, status, person_id)
VALUES ('Meditations', 'These are the writings', NOW(), 'Active', (SELECT id FROM person WHERE email = 'marcus@rome.com')),
       ('House of Baskerville', 'Elementary as I would say.', NOW(), 'Active', (SELECT id FROM person WHERE email = 'sherlock@sherlockholmes.com'));
