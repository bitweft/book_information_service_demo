DROP TABLE IF EXISTS book_information;
Create table book_information(
    book_id VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    image_url VARCHAR(1000) NOT NULL,
    publish_date BIGINT NOT NULL,
    PRIMARY KEY(book_id)
);
