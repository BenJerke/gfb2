BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO status(status_id, status_name)
VALUES (0, 'New'), (1, 'In Progress'), (2, 'Paused'), (3, 'Complete'), (4, 'Cancelled'), (5, 'Blocked');


-- tags
INSERT INTO tags (user_id, tag_name) VALUES (1, 'User 1 - Administration');
INSERT INTO tags (user_id, tag_name) VALUES (1, 'User 1- My Cool Project');
INSERT INTO tags (user_id, tag_name) VALUES (2, 'User 2 - Chores');

-- tasks
INSERT INTO tasks (task_description, task_owner, task_estimated_duration) VALUES ('User1 Admin Task', 1, 3600);
INSERT INTO tasks (task_description, task_owner, task_estimated_duration) VALUES ('User 1s Project Task', 1, 3600);
INSERT INTO tasks (task_description, task_owner, task_estimated_duration) VALUES ('User 1s Administrative Project Task', 1, 3600);
INSERT INTO tasks (task_description, task_owner, task_estimated_duration) VALUES ('User 2s Chore', 2, 1400);
INSERT INTO tasks (task_description, task_owner, task_estimated_duration, task_actual_duration, task_status_id) VALUES ('User 1s Completed Task', 2, 3600, 3600, 3);

-- task_tags
INSERT INTO task_tags (task_id, tag_id) VALUES (1, 1);
INSERT INTO task_tags (task_id, tag_id) VALUES (2, 2);
INSERT INTO task_tags (task_id, tag_id) VALUES (3, 1);
INSERT INTO task_tags (task_id, tag_id) VALUES (3, 2);


COMMIT TRANSACTION;