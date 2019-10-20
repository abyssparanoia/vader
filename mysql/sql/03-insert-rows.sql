USE `vader`;

INSERT INTO `posts` (
    id, title, description, text, created_at, updated_at
)
VALUES
    (1, 'タイトル', '概要', '内容', ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000), ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000))