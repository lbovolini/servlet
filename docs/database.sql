CREATE TABLE IF NOT EXISTS `jdbc`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `birthday` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`email`))

