create database traveldiary;

CREATE TABLE `traveldiary`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NULL,
  `emailId` VARCHAR(45) NULL,
  `birthday` VARCHAR(45) NULL,
  `profilePhotoId` VARCHAR(45) NULL,
  `createdTs` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTs` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));


CREATE TABLE `travel_details` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `userId` INT(11) NULL,
  `location` VARCHAR(45) NULL,
  `rating` INT(11) NULL,
  `review` VARCHAR(125) NULL,
  `dateOfTravel` TIMESTAMP NULL,
  `createdts` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTs` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_td_user_id`FOREIGN KEY (`userId`)REFERENCES `traveldiary`.`user` (`id`)ON DELETE NO ACTION ON UPDATE NO ACTION);



CREATE TABLE `traveldiary`.`photos` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `travelId` INT(11) NULL,
  `photoUrl` VARCHAR(145) NULL,
  `type` VARCHAR(45) NULL,
  `comments` VARCHAR(45) NULL,
  `createdTs` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTs` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_photo_travelId_idx` (`travelId` ASC),
  CONSTRAINT `fk_photo_travelId` FOREIGN KEY (`travelId`) REFERENCES `traveldiary`.`travel_details` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION);
