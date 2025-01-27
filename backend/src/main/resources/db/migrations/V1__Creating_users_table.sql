CREATE TABLE IF NOT EXISTS users(
	id VARCHAR PRIMARY KEY,
	name VARCHAR NOT NULL,
	email VARCHAR NOT NULL UNIQUE,
	car VARCHAR,
	course VARCHAR NOT NULL,
	enrollment_number VARCHAR NOT NULL,
	image_url VARCHAR,
	password_hash VARCHAR NOT NULL,
	rating DECIMAL,
	rides_provided BIGINT,
	role VARCHAR NOT NULL
);

INSERT INTO users (id, name, email, course, enrollment_number, image_url, password_hash,role)
VALUES ('2dd7fd6e-28e7-44c9-93a0-c97172987f9c', 'admin', 'admin@umbrella.com', 'UFRN', '01', 'https://frightened-purple-gopher.myfilebase.com/ipfs/QmQm1neVUDC2ojuijt6vTmnsyQrJF17famR176fSi67Jph','$2y$10$lWhUB4so2PZciqcwyFCLzu/2eV89zX7ci.Hw0A9wKIRjsB9ryrlWe', 'ADMIN');