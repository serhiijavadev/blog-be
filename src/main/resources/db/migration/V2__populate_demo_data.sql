INSERT INTO authors (id, name, about)
VALUES (42, 'John Doe', 'I am a software engineer.');

INSERT INTO articles (title, content, created_at, updated_at, author_id)
VALUES ('First Article', 'This is the first article.', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 42),
       ('Second Article', 'This is the second article.', '2022-01-02 00:00:00', '2022-01-02 00:00:00', 42),
       ('Third Article', 'This is the third article.', '2023-01-03 00:00:00', '2023-01-03 00:00:00', 42);