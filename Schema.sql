CREATE DATABASE social_Platform;
USE social_platform;

CREATE TABLE users
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(80),
	email VARCHAR(100),
	password VARCHAR(255),
	date_created DATETIME,
	date_modified DATETIME
);

CREATE TABLE posts
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(60),
    body VARCHAR(255),
    user_id INT,
    date_created DATETIME,
    date_modified DATETIME,
    
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE categories
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60),
    date_created DATETIME,
    date_modified DATETIME
);

CREATE TABLE post_categories
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    category_id INT,
    date_created DATETIME,
    date_modified DATETIME,
    
    FOREIGN KEY (post_id) REFERENCES posts (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);
