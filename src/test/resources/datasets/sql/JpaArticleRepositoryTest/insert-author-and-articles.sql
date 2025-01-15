INSERT INTO authors (id, name, about)
VALUES (142, 'John Doe', 'I am a software engineer.');

INSERT INTO articles (id, title, content, created_at, updated_at, author_id)
VALUES (1001, 'First Article', 'This is the first article.', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 142),
       (1002, 'Second Article', 'This is the second article.', '2022-01-02 00:00:00', '2022-01-02 00:00:00', 142),
       (1003, 'Third Article', 'This is the third article.', '2023-01-03 00:00:00', '2023-01-03 00:00:00', 142),
       (1004, 'Fourth Article', 'This is the fourth article.', '2024-01-01 00:00:00', '2024-01-01 00:00:00', 142),
       (1005, 'Fifth Article', 'This is the fifth article.', '2025-01-01 00:00:00', '2025-01-01 00:00:00', 142);
