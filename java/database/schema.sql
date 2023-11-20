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
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE tasks (
  task_id SERIAL,
  task_description varchar(120) NOT NULL,
  task_estimated_duration bigint,
  task_actual_duration bigint,
  task_status_id int NOT NULL DEFAULT 0,
  CONSTRAINT PK_task PRIMARY KEY (task_id)
);


CREATE TABLE sessions (
  session_id serial,
  user_id int NOT NULL,
  session_notes varchar(2000),
  session_started bigint NOT NULL,
  session_ended bigint,
  session_duration bigint,
  CONSTRAINT PK_session PRIMARY KEY (session_id)
);

CREATE TABLE tags (
  tag_id serial,
  user_id int NOT NULL,
  tag_name varchar(50),
  CONSTRAINT PK_tag PRIMARY KEY (tag_id)
);

CREATE TABLE user_tasks (
    user_id int NOT NULL,
    task_id int NOT NULL
);

CREATE TABLE user_tags (
    user_id int NOT NULL,
    tag_id int NOT NULL
);

CREATE TABLE session_tasks (
  session_id int NOT NULL,
  task_id int NOT NULL,
  task_start bigint,
  task_stop bigint,
  task_duration bigint
);


CREATE TABLE task_tags (
  task_id int NOT NULL,
  tag_id int NOT NULL
);

COMMIT TRANSACTION;
