-- room
INSERT INTO room(title)
VALUES ('foo');
INSERT INTO room(title)
VALUES ('bar');

-- message
INSERT INTO message(contents, message_type, created_at, room_id)
VALUES ('foo', 'TEXT', '2020-08-01 00:00:00', 1);
INSERT INTO message(contents, message_type, created_at, room_id)
VALUES ('bar', 'TEXT', '2020-08-01 00:00:00', 2);
