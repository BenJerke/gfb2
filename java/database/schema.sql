BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS session_tasks;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS task_tags;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE tasks (
  task_id SERIAL,
  task_description varchar(120) NOT NULL,
  task_created TIMESTAMP NOT NULL,
  task_initiated TIMESTAMP,
  task_terminated TIMESTAMP,
  task_estimated_duration INTERVAL,
  task_actual_duration INTERVAL,
  task_status_id int NOT NULL DEFAULT 0,
  CONSTRAINT PK_task PRIMARY KEY (task_id)
);

CREATE TABLE status (
  status_id int NOT NULL,
  status_name varchar(20)
);

CREATE TABLE sessions (
  session_id serial,
  user_id int NOT NULL,
  session_notes varchar(2000),
  session_started TIMESTAMP NOT NULL,
  session_ended TIMESTAMP,
  session_duration INTERVAL,
  CONSTRAINT PK_session PRIMARY KEY (session_id)
);


CREATE TABLE session_tasks (
  session_id int NOT NULL,
  task_id int NOT NULL,
  task_start TIMESTAMP,
  task_stop TIMESTAMP,
  task_duration INTERVAL
);

CREATE TABLE tags (
  tag_id serial,
  user_id int NOT NULL,
  tag_name varchar(50),
  CONSTRAINT PK_tag PRIMARY KEY (tag_id)
);

CREATE TABLE task_tags (
  task_id int NOT NULL,
  tag_id int NOT NULL
);

COMMIT TRANSACTION;
