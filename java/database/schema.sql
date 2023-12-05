BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS session_tasks;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS task_tags;

CREATE TABLE status (
   status_id smallint NOT NULL,
   status_name varchar(20)
 );

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE tasks (
  task_id SERIAL,
  task_description varchar(120) NOT NULL,
  task_owner int NOT NULL REFERENCES users (user_id),
  task_estimated_duration bigint DEFAULT 0,
  task_actual_duration bigint NOT NULL DEFAULT 0,
  task_status_id int NOT NULL DEFAULT 0,
  CONSTRAINT PK_task PRIMARY KEY (task_id)
);


CREATE TABLE sessions (
  session_id SERIAL,
  user_id int NOT NULL REFERENCES users(user_id),
  session_notes varchar(2000),
  session_started timestamp NOT NULL,
  session_ended timestamp NOT NULL,
  CONSTRAINT PK_session PRIMARY KEY (session_id)
);

CREATE TABLE tags (
  tag_id SERIAL,
  user_id int NOT NULL REFERENCES users(user_id),
  tag_name varchar(50),
  CONSTRAINT PK_tag PRIMARY KEY (tag_id)
);

CREATE TABLE user_tasks (
    user_id int NOT NULL REFERENCES users(user_id),
    task_id int NOT NULL REFERENCES tasks(task_id)
);

CREATE TABLE user_tags (
    user_id int NOT NULL REFERENCES users(user_id),
    tag_id int NOT NULL REFERENCES tags(tag_id)
);

CREATE TABLE session_tasks (
  session_id int NOT NULL REFERENCES sessions(session_id),
  task_id int NOT NULL REFERENCES tasks(task_id),
  task_start timestamp,
  task_stop timestamp,
  status_id int REFERENCES status(status_id)
);


CREATE TABLE task_tags (
  task_id int NOT NULL REFERENCES tasks(task_id),
  tag_id int NOT NULL
);

CREATE TABLE notes (
    note_id SERIAL,
    task_id int NOT NULL REFERENCES tasks(task_id),
    CONSTRAINT PK_notes PRIMARY KEY (note_id)
);

COMMIT TRANSACTION;
