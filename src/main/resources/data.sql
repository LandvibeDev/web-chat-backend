-- room
INSERT INTO room(title) VALUES ('study spring');
INSERT INTO room(title) VALUES ('study react');
INSERT INTO room(title) VALUES ('landvibeDev');

-- message
INSERT INTO message(contents, message_type, created_at, room_id) VALUES ('hi', 'TEXT', '2020-08-01 00:00:00', 1);
INSERT INTO message(contents, message_type, created_at, room_id) VALUES ('hello', 'TEXT', '2020-08-01 00:00:00', 1);
INSERT INTO message(contents, message_type, created_at, room_id) VALUES ('good', 'TEXT', '2020-08-01 00:00:00', 2);
