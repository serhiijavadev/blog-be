CREATE TABLE IF NOT EXISTS authors
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(255)  NOT NULL,
    about VARCHAR(2048) NOT NULL
);

CREATE TABLE IF NOT EXISTS articles
(
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(1024) NOT NULL,
    content    TEXT          NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    author_id  BIGINT        NOT NULL,
    version    BIGINT        NOT NULL DEFAULT 0,

    CONSTRAINT fk_article_author FOREIGN KEY (author_id)
        REFERENCES authors (id) ON DELETE CASCADE
);