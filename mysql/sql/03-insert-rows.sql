USE `vader`;

INSERT INTO `users` (
    id, display_name, avatar_url, created_at, updated_at
)
VALUES
    ('DUMMY_USER_ID', 'tester', 'https://hogehoge/icon.jpeg', ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000));

INSERT INTO `posts` (
    id, user_id, title, description, text, created_at, updated_at
)
VALUES
    (1, 'DUMMY_USER_ID', 'タイトル', '概要', '内容', ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000));