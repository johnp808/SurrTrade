-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema surrtrade
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `surrtrade` ;

-- -----------------------------------------------------
-- Schema surrtrade
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `surrtrade` DEFAULT CHARACTER SET utf8 ;
USE `surrtrade` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `primary_bike` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `role` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_login` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `bike_picture` VARCHAR(2083) NULL DEFAULT NULL,
  `user_picture` VARCHAR(2083) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `feed_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `feed_post` ;

CREATE TABLE IF NOT EXISTS `feed_post` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NULL DEFAULT NULL,
  `feed_content` TEXT NOT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC),
  INDEX `idx_feed_post_created_at` (`created_at` ASC),
  CONSTRAINT `feed_post_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NULL DEFAULT NULL,
  `feed_post_id` INT(11) NULL DEFAULT NULL,
  `comment_content` TEXT NOT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC),
  INDEX `feed_post_id` (`feed_post_id` ASC),
  CONSTRAINT `comment_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`),
  CONSTRAINT `comment_ibfk_2`
    FOREIGN KEY (`feed_post_id`)
    REFERENCES `feed_post` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `conversation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conversation` ;

CREATE TABLE IF NOT EXISTS `conversation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user1_id` INT(11) NULL DEFAULT NULL,
  `user2_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user1_id` (`user1_id` ASC),
  INDEX `user2_id` (`user2_id` ASC),
  CONSTRAINT `conversation_ibfk_1`
    FOREIGN KEY (`user1_id`)
    REFERENCES `user` (`id`),
  CONSTRAINT `conversation_ibfk_2`
    FOREIGN KEY (`user2_id`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `feed_post_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `feed_post_like` ;

CREATE TABLE IF NOT EXISTS `feed_post_like` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NULL DEFAULT NULL,
  `feed_post_id` INT(11) NULL DEFAULT NULL,
  `liked_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC),
  INDEX `feed_post_id` (`feed_post_id` ASC),
  INDEX `idx_feed_post_like_liked_at` (`liked_at` ASC),
  CONSTRAINT `feed_post_like_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`),
  CONSTRAINT `feed_post_like_ibfk_2`
    FOREIGN KEY (`feed_post_id`)
    REFERENCES `feed_post` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `feed_post_picture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `feed_post_picture` ;

CREATE TABLE IF NOT EXISTS `feed_post_picture` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `feed_post_id` INT(11) NULL DEFAULT NULL,
  `picture_url` VARCHAR(2083) NOT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `feed_post_id` (`feed_post_id` ASC),
  CONSTRAINT `feed_post_picture_ibfk_1`
    FOREIGN KEY (`feed_post_id`)
    REFERENCES `feed_post` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `market_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `market_item` ;

CREATE TABLE IF NOT EXISTS `market_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NULL DEFAULT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `category` VARCHAR(255) NULL DEFAULT NULL,
  `image_url` VARCHAR(2083) NULL DEFAULT NULL,
  `view_count` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC),
  INDEX `idx_market_item_created_at` (`created_at` ASC),
  INDEX `idx_market_item_view_count` (`view_count` ASC),
  INDEX `idx_market_item_title` (`title` ASC),
  CONSTRAINT `market_item_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

CREATE TABLE IF NOT EXISTS `message` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `conversation_id` INT(11) NULL DEFAULT NULL,
  `sender_id` INT(11) NULL DEFAULT NULL,
  `message_content` TEXT NOT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `seen` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `conversation_id` (`conversation_id` ASC),
  INDEX `sender_id` (`sender_id` ASC),
  CONSTRAINT `message_ibfk_1`
    FOREIGN KEY (`conversation_id`)
    REFERENCES `conversation` (`id`),
  CONSTRAINT `message_ibfk_2`
    FOREIGN KEY (`sender_id`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user_favorite_market_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_favorite_market_item` ;

CREATE TABLE IF NOT EXISTS `user_favorite_market_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `market_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_user_favorite_market_item_user_id` (`user_id` ASC),
  INDEX `idx_user_favorite_market_item_market_item_id` (`market_item_id` ASC),
  CONSTRAINT `user_favorite_market_item_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`),
  CONSTRAINT `user_favorite_market_item_ibfk_2`
    FOREIGN KEY (`market_item_id`)
    REFERENCES `market_item` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SET SQL_MODE = '';
DROP USER IF EXISTS admin@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'password';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'admin'@'localhost';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'admin'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `surrtrade`;
INSERT INTO `user` (`id`, `username`, `email`, `password`, `primary_bike`, `status`, `role`, `created_at`, `updated_at`, `last_login`, `bike_picture`, `user_picture`) VALUES (1, 'John', 'john@john.com', 'password', 'Jetson Bolt Pro', 'online', 'admin', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `primary_bike`, `status`, `role`, `created_at`, `updated_at`, `last_login`, `bike_picture`, `user_picture`) VALUES (2, 'Mac', 'mac@mac.com', 'password', 'Surron Bee', 'offline', 'registered', NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `feed_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `surrtrade`;
INSERT INTO `feed_post` (`id`, `user_id`, `feed_content`, `created_at`, `updated_at`) VALUES (1, 1, 'Ride Around Magic Island Ala Moana', '2024-07-15 14:45:30', '2024-07-15 15:45:30');

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `surrtrade`;
INSERT INTO `comment` (`id`, `user_id`, `feed_post_id`, `comment_content`, `created_at`) VALUES (1, 2, 1, 'Hey I can Join!', '2024-07-15 16:45:30');
INSERT INTO `comment` (`id`, `user_id`, `feed_post_id`, `comment_content`, `created_at`) VALUES (2, 1, 1, 'Awesome let\'s go Friday around 5PM.', '2024-07-15 18:30:30');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conversation`
-- -----------------------------------------------------
START TRANSACTION;
USE `surrtrade`;
INSERT INTO `conversation` (`id`, `user1_id`, `user2_id`) VALUES (1, 1, 2);
INSERT INTO `conversation` (`id`, `user1_id`, `user2_id`) VALUES (2, 2, 1);
INSERT INTO `conversation` (`id`, `user1_id`, `user2_id`) VALUES (3, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `feed_post_like`
-- -----------------------------------------------------
START TRANSACTION;
USE `surrtrade`;
INSERT INTO `feed_post_like` (`id`, `user_id`, `feed_post_id`, `liked_at`) VALUES (1, 2, 1, '2024-07-15 14:45:30');

COMMIT;


-- -----------------------------------------------------
-- Data for table `feed_post_picture`
-- -----------------------------------------------------
START TRANSACTION;
USE `surrtrade`;
INSERT INTO `feed_post_picture` (`id`, `feed_post_id`, `picture_url`, `created_at`) VALUES (1, 1, 'www.examplepic.com', '2024-07-15 14:45:30');

COMMIT;


-- -----------------------------------------------------
-- Data for table `market_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `surrtrade`;
INSERT INTO `market_item` (`id`, `user_id`, `title`, `description`, `price`, `created_at`, `updated_at`, `category`, `image_url`, `view_count`) VALUES (1, 1, 'Jetson Bolt Pro OEM Seat and Post', 'Selling a OEM seat and post for the Jetson Bolt Pro. Used Twice.', 30.00, '2024-07-15 14:45:30', NULL, 'Accessories', NULL, 2);
INSERT INTO `market_item` (`id`, `user_id`, `title`, `description`, `price`, `created_at`, `updated_at`, `category`, `image_url`, `view_count`) VALUES (2, 2, 'Surron Front Shocks', 'Selling OEM Front Shocks For Surron', 45.00, '2024-07-16 10:10:10', NULL, 'Accessories', NULL, 35);

COMMIT;


-- -----------------------------------------------------
-- Data for table `message`
-- -----------------------------------------------------
START TRANSACTION;
USE `surrtrade`;
INSERT INTO `message` (`id`, `conversation_id`, `sender_id`, `message_content`, `created_at`, `seen`) VALUES (1, 1, 1, 'How\'s it going?', '2024-07-15 14:45:30', 1);
INSERT INTO `message` (`id`, `conversation_id`, `sender_id`, `message_content`, `created_at`, `seen`) VALUES (2, 1, 2, 'Good, do you still have that 48v battery for sale?', '2024-07-15 18:45:30', 0);
INSERT INTO `message` (`id`, `conversation_id`, `sender_id`, `message_content`, `created_at`, `seen`) VALUES (3, 2, 1, 'Hi do you still have the helmet for sale?', '2024-07-16 11:45:30', 0);
INSERT INTO `message` (`id`, `conversation_id`, `sender_id`, `message_content`, `created_at`, `seen`) VALUES (4, 3, 2, 'Did you want to buy my clutch?', '2024-07-15 10:45:30', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_favorite_market_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `surrtrade`;
INSERT INTO `user_favorite_market_item` (`id`, `user_id`, `market_item_id`) VALUES (1, 2, 1);
INSERT INTO `user_favorite_market_item` (`id`, `user_id`, `market_item_id`) VALUES (2, 1, 2);

COMMIT;

