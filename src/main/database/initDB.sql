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
  PRIMARY KEY (`user_mail`));

CREATE TABLE `GuideHelperDB`.`Tours` (
  `tour_id` INT NOT NULL AUTO_INCREMENT,
  `title` TINYTEXT NOT NULL,
  `city` TINYTEXT NOT NULL,
  `guide_mail` VARCHAR(320) NOT NULL,
  `cost` INT UNSIGNED NULL DEFAULT 0,
  `description` TEXT NULL,
  `tour_image` MEDIUMBLOB NULL,
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
  `uptodate` TINYINT(1) NOT NULL DEFAULT 0,
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

INSERT INTO `GuideHelperDB`.`Users`
 (`user_mail`, `is_guide`, `name`, `phone_number`, `city`, `description`, `avatar_url`)
 VALUES ('firstUser@gmail.com', '0', 'Tester', '+7-977-777-77-77', 'Spb', 'Test Query 1', null);

INSERT INTO `GuideHelperDB`.`Users`
(`user_mail`, `is_guide`, `name`, `phone_number`, `city`, `description`, `avatar_url`)
VALUES ('thdUser@gmail.com', '2', 'Petrov', '+7-977-777-77-77', 'Spb', 'Test Query 3', null);

INSERT INTO `GuideHelperDB`.`Users`
 (`user_mail`, `is_guide`, `name`, `phone_number`, `city`, `description`, `avatar_url`)
 VALUES ('secondUser@gmail.com', '1', 'Kek', '+7-977-777-77-77', 'Spb', 'Test Query 2', null);

INSERT INTO `GuideHelperDB`.`Tours`
 (`tour_id`, `title`, `city`, `guide_mail`, `cost`, `description`, `tour_image`)
 VALUES (1, 'test tour', 'saintpy', 'secondUser@gmail.com', 100, 'test description', null);

INSERT INTO `GuideHelperDB`.`Tours`
 (`tour_id`, `title`, `city`, `guide_mail`, `cost`, `description`, `tour_image`)
 VALUES (2, 'msk tour', 'msk', 'firstUser@gmail.com', 300, 'The extensive sightseeing tour of Moscow is the main tourist route not only for the guests of the city, but also for the residents of Moscow. A tour of Moscow - it can be called a perennial classics, because it allows to get acquainted with legendary sights and to create a holistic perception of the majestic capital. ', null);

INSERT INTO `GuideHelperDB`.`Tours`
(`tour_id`, `title`, `city`, guide_mail, cost, description, `tour_image`)
VALUES (3, 'yar tour', 'sainyartpy', 'thdUser@gmail.com', 500, 'The ceremonial halls of the Winter Palace, the exhibits of the Old Hermitage and the New Hermitage - on excursion you will discover the treasury of one of the main museums of the world. You will find the collections of ancient and Egyptian halls, as well as immersion in the culture of Western Europe - we will break down the plot of the paintings, penetrate the mysteries of painting and talk about the creative path of artists', null);

INSERT INTO GuideHelperDB.Orders (order_id, user_mail, tour_id, date_time) VALUES (1, 'firstUser@gmail.com', 1, '2021-05-01 15:00:00.0');

INSERT INTO GuideHelperDB.Orders (order_id, user_mail, tour_id, date_time) VALUES (2, 'firstUser@gmail.com', 1, '2015-05-01 15:00:00.0');


