CREATE SCHEMA `GuideHelperDB` ;

USE `GuideHelperDB`;

CREATE TABLE `GuideHelperDB`.`Users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `is_guide` TINYINT(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0,
  `first_name` TINYTEXT NULL,
  `last_name` TINYTEXT NULL,
  `mail` VARCHAR(320) NULL,
  `phone_number` VARCHAR(30) NULL,
  `city` TINYTEXT NULL,
  `description` TEXT NULL,
  `tags` JSON NULL,
  PRIMARY KEY (`user_id`));

CREATE TABLE `GuideHelperDB`.`Excursions` (
  `excursion_id` INT NOT NULL AUTO_INCREMENT,
  `title` TINYTEXT NOT NULL,
  `city` TINYTEXT NOT NULL,
  `description` TEXT NULL,
  `guide_id` INT NOT NULL,
  `cost` INT UNSIGNED ZEROFILL NULL,
  `excursion_image` MEDIUMBLOB NULL,
  PRIMARY KEY (`excursion_id`));
  
CREATE TABLE `GuideHelperDB`.`Orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `excursion_id` INT NOT NULL,
  `date_time` DATETIME NULL,
  PRIMARY KEY (`order_id`));
  
CREATE TABLE `GuideHelperDB`.`Subscriptions` (
  `subscription_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `guide_id` INT NOT NULL,
  PRIMARY KEY (`subscription_id`));
 
  
INSERT INTO `GuideHelperDB`.`Users`
 (`user_id`, `login`, `is_guide`, `first_name`, `last_name`, `mail`, `phone_number`, `city`, `description`, `tags`)
 VALUES ('0', 'Test', '0', 'Tester', 'Testov', '890vovamen@gmail.com', '+7-977-777-77-77', 'Spb', 'Test Query', 'null');





  

