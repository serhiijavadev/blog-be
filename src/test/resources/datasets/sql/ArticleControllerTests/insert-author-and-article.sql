INSERT INTO authors (id, name, about)
VALUES (42, 'John Doe', 'I am a software engineer.');

INSERT INTO articles (id, title, content, created_at, updated_at, author_id)
VALUES (101, 'First Article', 'This is the first article.', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 42);