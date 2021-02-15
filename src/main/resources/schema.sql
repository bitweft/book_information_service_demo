DROP TABLE IF EXISTS book_information;
Create table book_information(
    book_id VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publish_date BIGINT,
    PRIMARY KEY(book_id)
);
