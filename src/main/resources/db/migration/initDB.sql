CREATE SCHEMA `GuideHelperDB` ;

USE `GuideHelperDB`;

CREATE TABLE `GuideHelperDB`.`Users` (
  `user_mail` VARCHAR(320) NOT NULL,
  `is_guide` BOOLEAN NOT NULL DEFAULT 0,
  `first_name` TINYTEXT NULL,
  `last_name` TINYTEXT NULL,
  `phone_number` VARCHAR(30) NULL,
  `city` TINYTEXT NULL,
  `description` TEXT NULL,
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
  
CREATE TABLE `GuideHelperDB`.`Subscriptions` (
  `subscription_id` INT NOT NULL AUTO_INCREMENT,
  `user_mail` VARCHAR(320) NULL,
  `guide_mail` VARCHAR(320) NULL,
  PRIMARY KEY (`subscription_id`),
  INDEX `user_mail_idx` (`user_mail` ASC) VISIBLE,
  CONSTRAINT `fk_user_mail_subs`
    FOREIGN KEY (`user_mail`)
    REFERENCES `GuideHelperDB`.`Users` (`user_mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
  
CREATE TABLE `GuideHelperDB`.`Tags` (
  `tag_id` INT NOT NULL AUTO_INCREMENT,
  `user_mail` VARCHAR(350) NOT NULL,
  `tag` TINYTEXT NOT NULL,
  PRIMARY KEY (`tag_id`),
  INDEX `user_mail_idx` (`user_mail` ASC) VISIBLE,
  CONSTRAINT `fk_user_mail_tags`
    FOREIGN KEY (`user_mail`)
    REFERENCES `GuideHelperDB`.`Users` (`user_mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `GuideHelperDB`.`Chats` (
  `chat_id` INT NOT NULL AUTO_INCREMENT,
  `first_user_mail` VARCHAR(320) NOT NULL,
  `second_user_mail` VARCHAR(320) NOT NULL,
  `uptodate` TINYINT(1) NOT NULL DEFAULT 0,
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
 
CREATE TABLE `GuideHelperDB`.`Messages` (
  `message_id` INT NOT NULL AUTO_INCREMENT,
  `chat_id` INT NOT NULL,
  `sender_mail` VARCHAR(320) NOT NULL,
  `receiver_mail` VARCHAR(320) NOT NULL,
  `text` TEXT NOT NULL,
  `dispatch_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`message_id`),
  INDEX `chat_id_idx` (`chat_id` ASC) VISIBLE,
  INDEX `sender_idx` (`sender_mail` ASC) VISIBLE,
  INDEX `receiver_idx` (`receiver_mail` ASC) VISIBLE,
  CONSTRAINT `chat_id`
    FOREIGN KEY (`chat_id`)
    REFERENCES `GuideHelperDB`.`Chats` (`chat_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `sender`
    FOREIGN KEY (`sender_mail`)
    REFERENCES `GuideHelperDB`.`Users` (`user_mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `receiver`
    FOREIGN KEY (`receiver_mail`)
    REFERENCES `GuideHelperDB`.`Users` (`user_mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `GuideHelperDB`.`Keywords` (
  `keyword_id` INT NOT NULL,
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
 (`user_mail`, `is_guide`, `first_name`, `last_name`, `phone_number`, `city`, `description`)
 VALUES ('firstUser@gmail.com', '0', 'Tester', 'Testov', '+7-977-777-77-77', 'Spb', 'Test Query 1');
 
INSERT INTO `GuideHelperDB`.`Users`
 (`user_mail`, `is_guide`, `first_name`, `last_name`, `phone_number`, `city`, `description`)
 VALUES ('secondUser@gmail.com', '1', 'Kek', 'Kekov', '+7-977-777-77-77', 'Spb', 'Test Query 2');

INSERT INTO `GuideHelperDB`.`Tours`
 (`tour_id`, `title`, `city`, `guide_mail`, `cost`, `description`, `tour_image`)
 VALUES (1, 'test tour', 'saintpy', 'secondUser@gmail.com', 100, 'test description', null);

  

