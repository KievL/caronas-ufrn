CREATE TABLE IF NOT EXISTS rides (
  id VARCHAR PRIMARY KEY,
  driver_id VARCHAR NOT NULL,
  max_passengers_quantity INTEGER NOT NULL,
  to_ufrn BOOLEAN NOT NULL,
  from_location VARCHAR NOT NULL,
  to_location VARCHAR NOT NULL,
  price FLOAT NOT NULL,
  departure_time TIMESTAMP NOT NULL,
  arrival_time TIMESTAMP NOT NULL,
  date DATE NOT NULL,
  status VARCHAR NOT NULL,
  FOREIGN KEY (driver_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS ride_passengers (
  ride_id VARCHAR NOT NULL,
  user_id VARCHAR NOT NULL,
  PRIMARY KEY (ride_id, user_id),
  FOREIGN KEY (ride_id) REFERENCES rides(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS ride_requesters (
  ride_id VARCHAR NOT NULL,
  user_id VARCHAR NOT NULL,
  PRIMARY KEY (ride_id, user_id),
  FOREIGN KEY (ride_id) REFERENCES rides(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);
