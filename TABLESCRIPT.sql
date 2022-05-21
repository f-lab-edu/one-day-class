-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ONEDAYCLASS
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ONEDAYCLASS
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ONEDAYCLASS` DEFAULT CHARACTER SET utf8 ;
USE `ONEDAYCLASS` ;

-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`user` (
  `id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `user_type` VARCHAR(1) NOT NULL,
  `user_status` VARCHAR(1) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`image_file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`image_file` (
  `id` INT NOT NULL,
  `path` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `content_type` VARCHAR(1) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`big_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`big_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `detail` VARCHAR(45) NOT NULL,
  `file_id` INT NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_big_category_file1_idx` (`file_id` ASC) VISIBLE,
  CONSTRAINT `fk_big_category_file1`
    FOREIGN KEY (`file_id`)
    REFERENCES `ONEDAYCLASS`.`image_file` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`user_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`user_category` (
  `id` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `big_category_id` INT NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `big_category_id`),
  INDEX `fk_user_category_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_category_big_category1_idx` (`big_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_category_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ONEDAYCLASS`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_category_big_category1`
    FOREIGN KEY (`big_category_id`)
    REFERENCES `ONEDAYCLASS`.`big_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`small_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`small_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `big_category_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `big_category_id`),
  INDEX `fk_small_category_big_category1_idx` (`big_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_small_category_big_category1`
    FOREIGN KEY (`big_category_id`)
    REFERENCES `ONEDAYCLASS`.`big_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`message` (
  `id` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_message_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ONEDAYCLASS`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`address` (
  `id` INT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `town` VARCHAR(45) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`one_day_class`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`one_day_class` (
  `id` INT NOT NULL,
  `host_user_id` VARCHAR(45) NOT NULL,
  `small_category_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `image_file_id` INT NOT NULL,
  `material` VARCHAR(45) NOT NULL,
  `detail` VARCHAR(45) NOT NULL,
  `rating` DOUBLE NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `host_user_id`, `small_category_id`, `address_id`),
  INDEX `fk_one_day_class_user1_idx` (`host_user_id` ASC) VISIBLE,
  INDEX `fk_one_day_class_small_category1_idx` (`small_category_id` ASC) VISIBLE,
  INDEX `fk_one_day_class_file1_idx` (`image_file_id` ASC) VISIBLE,
  INDEX `fk_one_day_class_address1_idx` (`address_id` ASC) VISIBLE,
  CONSTRAINT `fk_one_day_class_user1`
    FOREIGN KEY (`host_user_id`)
    REFERENCES `ONEDAYCLASS`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_one_day_class_small_category1`
    FOREIGN KEY (`small_category_id`)
    REFERENCES `ONEDAYCLASS`.`small_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_one_day_class_file1`
    FOREIGN KEY (`image_file_id`)
    REFERENCES `ONEDAYCLASS`.`image_file` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_one_day_class_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `ONEDAYCLASS`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`course` (
  `id` INT NOT NULL,
  `one_day_class_id` INT NOT NULL,
  `capacity` INT NOT NULL,
  `date` VARCHAR(45) NOT NULL,
  `time` VARCHAR(45) NOT NULL,
  `course_status` VARCHAR(1) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `one_day_class_id`),
  INDEX `fk_course_one_day_class1_idx` (`one_day_class_id` ASC) VISIBLE,
  CONSTRAINT `fk_course_one_day_class1`
    FOREIGN KEY (`one_day_class_id`)
    REFERENCES `ONEDAYCLASS`.`one_day_class` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`course_application_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`course_application_history` (
  `id` INT NOT NULL,
  `guest_user_id` VARCHAR(45) NOT NULL,
  `course_id` INT NOT NULL,
  `application_status` VARCHAR(1) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `guest_user_id`, `course_id`),
  INDEX `fk_one_day_class_application_details_user1_idx` (`guest_user_id` ASC) VISIBLE,
  INDEX `fk_one_day_class_application_detail_course1_idx` (`course_id` ASC) VISIBLE,
  CONSTRAINT `fk_one_day_class_application_details_user1`
    FOREIGN KEY (`guest_user_id`)
    REFERENCES `ONEDAYCLASS`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_one_day_class_application_detail_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `ONEDAYCLASS`.`course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`review` (
  `id` INT NOT NULL,
  `one_day_class_id` INT NOT NULL,
  `guest_user_id` VARCHAR(45) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `review_type` VARCHAR(1) NOT NULL,
  `image_file_id` INT NOT NULL,
  `rating` DOUBLE NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `one_day_class_id`, `guest_user_id`),
  INDEX `fk_review_one_day_class1_idx` (`one_day_class_id` ASC) VISIBLE,
  INDEX `fk_review_user1_idx` (`guest_user_id` ASC) VISIBLE,
  INDEX `fk_review_file1_idx` (`image_file_id` ASC) VISIBLE,
  CONSTRAINT `fk_review_one_day_class1`
    FOREIGN KEY (`one_day_class_id`)
    REFERENCES `ONEDAYCLASS`.`one_day_class` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`guest_user_id`)
    REFERENCES `ONEDAYCLASS`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_file1`
    FOREIGN KEY (`image_file_id`)
    REFERENCES `ONEDAYCLASS`.`image_file` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`comment` (
  `id` INT NOT NULL,
  `review_id` INT NOT NULL,
  `review_one_day_class_id` INT NOT NULL,
  `user_id` VARCHAR(45) NULL,
  `content` VARCHAR(45) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `review_id`, `review_one_day_class_id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_comment_review1_idx` (`review_id` ASC, `review_one_day_class_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ONEDAYCLASS`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_review1`
    FOREIGN KEY (`review_id` , `review_one_day_class_id`)
    REFERENCES `ONEDAYCLASS`.`review` (`id` , `one_day_class_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`small_category_application_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`small_category_application_history` (
  `id` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `big_category_id` INT NOT NULL,
  `small_category_name` VARCHAR(45) NOT NULL,
  `status` VARCHAR(1) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `big_category_id`),
  INDEX `fk_small_category_application_history_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_small_category_application_history_image_big_category1_idx` (`big_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_small_category_application_history_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ONEDAYCLASS`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_small_category_application_history_image_big_category1`
    FOREIGN KEY (`big_category_id`)
    REFERENCES `ONEDAYCLASS`.`big_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`big_category_application_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`big_category_application_history` (
  `id` INT NOT NULL,
  `host_user_id` VARCHAR(45) NOT NULL,
  `big_category_name` VARCHAR(45) NOT NULL,
  `big_category_detail` VARCHAR(45) NOT NULL,
  `image_file_id` INT NOT NULL,
  `status` VARCHAR(1) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `host_user_id`),
  INDEX `fk_big_category_application_history_user1_idx` (`host_user_id` ASC) VISIBLE,
  INDEX `fk_big_category_application_history_image_file1_idx` (`image_file_id` ASC) VISIBLE,
  CONSTRAINT `fk_big_category_application_history_user1`
    FOREIGN KEY (`host_user_id`)
    REFERENCES `ONEDAYCLASS`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_big_category_application_history_image_file1`
    FOREIGN KEY (`image_file_id`)
    REFERENCES `ONEDAYCLASS`.`image_file` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`nested_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`nested_comment` (
  `id` INT NOT NULL,
  `comment_id` INT NOT NULL,
  `user_id` VARCHAR(45) NULL,
  `content` VARCHAR(45) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `comment_id`),
  INDEX `fk_nested_comment_comment1_idx` (`comment_id` ASC) VISIBLE,
  INDEX `fk_nested_comment_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_nested_comment_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `ONEDAYCLASS`.`comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nested_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ONEDAYCLASS`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ONEDAYCLASS`.`like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ONEDAYCLASS`.`like` (
  `id` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `review_id` INT NOT NULL,
  `like_status` VARCHAR(1) NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `review_id`),
  INDEX `fk_like_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_like_review1_idx` (`review_id` ASC) VISIBLE,
  CONSTRAINT `fk_like_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ONEDAYCLASS`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_like_review1`
    FOREIGN KEY (`review_id`)
    REFERENCES `ONEDAYCLASS`.`review` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
