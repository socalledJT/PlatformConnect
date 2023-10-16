CREATE DATABASE social_Platform;
USE social_platform;

CREATE TABLE users
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(80),
	email VARCHAR(100),
	password VARCHAR(255),
	date_created datetime,
	date_modified datetime
);

create table posts
(
	id int auto_increment primary key,
    title varchar(60),
    body varchar(255),
    user_id int,
    date_created datetime,
    date_modified datetime,
    
    foreign key (user_id) references users (id)
);

create table categories
(
	id int auto_increment primary key,
    name varchar(60),
    date_created datetime,
    date_modified datetime
);

create table post_categories
(
	id int auto_increment primary key,
    post_id int,
    category_id int,
    date_created datetime,
    date_modified datetime,
    
    foreign key (post_id) references posts (id),
    foreign key (category_id) references categories (id)
);
