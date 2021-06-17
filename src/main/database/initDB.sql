DROP DATABASE IF EXISTS GuideHelperDB;

CREATE SCHEMA `GuideHelperDB` ;

USE `GuideHelperDB`;

CREATE TABLE `GuideHelperDB`.`Users` (
  `user_mail` VARCHAR(320) NOT NULL,
  `is_guide` BOOLEAN NOT NULL DEFAULT 0,
  `name` TINYTEXT NULL,
  `phone_number` VARCHAR(30) NULL,
  `city` TINYTEXT NULL,
  `description` TEXT NULL,
  `avatar_url` VARCHAR(1024) NULL,
  `token` VARCHAR(170) NULL,
  PRIMARY KEY (`user_mail`));

CREATE TABLE `GuideHelperDB`.`Tours` (
  `tour_id` INT NOT NULL AUTO_INCREMENT,
  `title` TINYTEXT NOT NULL,
  `city` TINYTEXT NOT NULL,
  `guide_mail` VARCHAR(320) NOT NULL,
  `cost` INT UNSIGNED NULL DEFAULT 0,
  `description` TEXT NULL,
  `tour_image` VARCHAR(320) NULL,
  `capacity` INT,
  `duration` VARCHAR(15),
  PRIMARY KEY (`tour_id`),
  INDEX `guide_mail_idx` (`guide_mail` ASC) VISIBLE,
  CONSTRAINT `guide_mail`
    FOREIGN KEY (`guide_mail`)
    REFERENCES `GuideHelperDB`.`Users` (`user_mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `GuideHelperDB`.`Orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_mail` VARCHAR(320) NOT NULL,
  `tour_id` INT NOT NULL,
  `date_time` DATETIME NULL,
  PRIMARY KEY (`order_id`),
  INDEX `tour_id_idx` (`tour_id` ASC) VISIBLE,
  INDEX `user_mail_idx` (`user_mail` ASC) VISIBLE,
  CONSTRAINT `customer_mail`
    FOREIGN KEY (`user_mail`)
    REFERENCES `GuideHelperDB`.`Users` (`user_mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `tour_id`
    FOREIGN KEY (`tour_id`)
    REFERENCES `GuideHelperDB`.`Tours` (`tour_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `GuideHelperDB`.`Favorites` (
  `favorite_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(320) NOT NULL,
  `tour_id` INT NOT NULL,
  PRIMARY KEY (`favorite_id`),
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_tour_id_idx` (`tour_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `GuideHelperDB`.`Users` (`user_mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tour_id`
    FOREIGN KEY (`tour_id`)
    REFERENCES `GuideHelperDB`.`Tours` (`tour_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `GuideHelperDB`.`Chats` (
  `chat_id` INT NOT NULL AUTO_INCREMENT,
  `first_user_mail` VARCHAR(320) NOT NULL,
  `second_user_mail` VARCHAR(320) NOT NULL,
  `number_of_messages` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`chat_id`),
  INDEX `first_user_idx` (`first_user_mail` ASC) VISIBLE,
  INDEX `second_user_idx` (`second_user_mail` ASC) VISIBLE,
  CONSTRAINT `first_user`
    FOREIGN KEY (`first_user_mail`)
    REFERENCES `GuideHelperDB`.`Users` (`user_mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `second_user`
    FOREIGN KEY (`second_user_mail`)
    REFERENCES `GuideHelperDB`.`Users` (`user_mail`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `GuideHelperDB`.`Keywords` (
  `keyword_id` INT NOT NULL AUTO_INCREMENT,
  `chat_id` INT NOT NULL,
  `word` TINYTEXT NOT NULL,
  PRIMARY KEY (`keyword_id`),
  INDEX `fk_chat_id_idx` (`chat_id` ASC) VISIBLE,
  CONSTRAINT `fk_chat_id`
    FOREIGN KEY (`chat_id`)
    REFERENCES `GuideHelperDB`.`Chats` (`chat_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


