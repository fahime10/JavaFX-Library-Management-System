DROP SCHEMA `library_db`;

CREATE SCHEMA `library_db`;

CREATE TABLE `library_db`.`user` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_first_name` VARCHAR(20) NOT NULL,
  `user_last_name` VARCHAR(20) NOT NULL,
  `user_username` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_username_UNIQUE` (`user_username` ASC) VISIBLE);

CREATE TABLE `library_db`.`book` (
  `book_id` INT NOT NULL AUTO_INCREMENT,
  `book_title` VARCHAR(100) NOT NULL,
  `book_author` VARCHAR(40) NOT NULL,
  `book_desc` VARCHAR(100) NOT NULL,
  `book_pages` INT NOT NULL,
  PRIMARY KEY (`book_id`));

