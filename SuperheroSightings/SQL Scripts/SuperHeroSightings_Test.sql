DROP DATABASE IF EXISTS Super_Hero_Sightings_Test;
CREATE DATABASE Super_Hero_Sightings_Test;
USE Super_Hero_Sightings_Test;

CREATE TABLE `Character_Types`
(
	`character_type_id` INT NOT NULL AUTO_INCREMENT,
    `character_type_name` VARCHAR(25) NOT NULL,
    PRIMARY KEY (`character_type_id`)
);

CREATE TABLE `Archetypes`
(
	`archetype_id` INT NOT NULL AUTO_INCREMENT,
    `archetype_name` VARCHAR(100) NOT NULL,
    `archetype_description` VARCHAR(250) DEFAULT "unavailable",
    `character_type_id` INT NOT NULL,
    PRIMARY KEY (`archetype_id`)
);

CREATE TABLE `Superpowers` 
(
	`superpower_id` INT NOT NULL AUTO_INCREMENT,
    `superpower_type` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`superpower_id`)
);


CREATE TABLE `Locations`
(
	`location_id` INT NOT NULL AUTO_INCREMENT,
    `location_name` VARCHAR(250) DEFAULT "unknown",
    `location_description` VARCHAR(250) DEFAULT "unavailable",
    `location_street` VARCHAR(50) NOT NULL,
    `location_city` VARCHAR(50) NOT NULL,
    `location_state` VARCHAR(50) NOT NULL,
    `location_zip` VARCHAR(50) NOT NULL,
    `location_country` VARCHAR(50) NOT NULL,
    `location_latitude` DECIMAL(11,8) DEFAULT NULL,
    `location_longitude` DECIMAL(11,8) DEFAULT NULL,
    PRIMARY KEY (`location_id`)
);

CREATE TABLE `Sightings`
(
	`sighting_id` INT NOT NULL AUTO_INCREMENT,
    `sighting_date` DATETIME NOT NULL,
    `location_id` INT NOT NULL,
    PRIMARY KEY (`sighting_id`)
);

CREATE TABLE `Organizations`
(
	`organization_id` INT NOT NULL AUTO_INCREMENT,
    `organization_name` VARCHAR(250) DEFAULT "unknown", 
    `organization_description` VARCHAR(250) DEFAULT "unknown",
    `organization_phone` VARCHAR(25) DEFAULT "unknown",
	`organization_email` VARCHAR(100) DEFAULT "unknown",
    `location_id` INT NOT NULL,
    PRIMARY KEY (`organization_id`)
);

CREATE TABLE `Archetypes_Organizations`
(
	`archetype_organization_id` INT NOT NULL AUTO_INCREMENT,
	`archetype_id` INT NOT NULL,
    `organization_id` INT NOT NULL,
    PRIMARY KEY (`archetype_organization_id`),
    KEY (`archetype_id`),
    KEY (`organization_id`)
);

CREATE TABLE `Archetypes_Sightings`
(
	`archetype_sighting_id` INT NOT NULL AUTO_INCREMENT,
    `archetype_id` INT NOT NULL,
    `sighting_id` INT NOT NULL,
    PRIMARY KEY (`archetype_sighting_id`),
    KEY(`archetype_id`),
    kEY(`sighting_id`)
);

CREATE TABLE `Archetypes_Superpowers`
(
	`archetype_superpower` INT NOT NULL AUTO_INCREMENT,
    `archetype_id` INT NOT NULL,
    `superpower_id` INT NOT NULL,
    PRIMARY KEY (`archetype_superpower`),
    KEY(`archetype_id`),
    KEY(`superpower_id`)
);

ALTER TABLE `Archetypes`
ADD CONSTRAINT `character_type_id_fk` FOREIGN KEY (`character_type_id`) 
REFERENCES `Character_Types` (`character_type_id`);

ALTER TABLE `Sightings`
ADD CONSTRAINT `location_id_fk` FOREIGN KEY (`location_id`) 
REFERENCES `Locations` (`location_id`);

ALTER TABLE `Organizations`
ADD CONSTRAINT `location_id_fk2` FOREIGN KEY (`location_id`) 
REFERENCES `Locations` (`location_id`);

