CREATE TABLE book (
	id BIGINT NOT NULL AUTO_INCREMENT,
	genre VARCHAR(255),
	isbn VARCHAR(255),
	title VARCHAR(255),
	PRIMARY KEY (id)
);

CREATE TABLE author_reward (
	author_id BIGINT NOT NULL,
	title VARCHAR(255) NOT NULL,
	year INT NOT NULL
);

CREATE TABLE author (
	id BIGINT NOT NULL AUTO_INCREMENT,
	birth_date DATE NOT NULL,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	sex VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE author_book (
	author_id BIGINT NOT NULL,
	book_id BIGINT NOT NULL
);

CREATE INDEX FK_author_book_book_id ON author_book (book_id ASC);

CREATE INDEX FK_author_book_author_id ON author_book (author_id ASC);

CREATE UNIQUE INDEX UK_book_isbn ON book (isbn ASC);

CREATE INDEX FK_author_reward_author_id ON author_reward (author_id ASC);