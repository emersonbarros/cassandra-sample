CREATE  KEYSPACE IF NOT EXISTS sample WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
CREATE TABLE sample.user (user_id uuid PRIMARY KEY, name text);
CREATE TYPE sample.address (street text, number int);