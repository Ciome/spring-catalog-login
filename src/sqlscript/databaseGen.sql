DROP DATABASE  IF EXISTS `spring_catalog_webapp`;

CREATE DATABASE  IF NOT EXISTS `spring_catalog_webapp`;
USE `spring_catalog_webapp`;

DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(128) DEFAULT NULL,
	`description` varchar(128) DEFAULT NULL,
	`img` varchar(128) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user_data`;

CREATE TABLE `user_data` (
	`id` int(11) NOT NULL auto_increment,
    `first_name` varchar(50) DEFAULT NULL,
	`last_name` varchar(50) DEFAULT NULL,
    `email` varchar(50) DEFAULT NULL,
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(50) NOT NULL,
    `password` char(68) NOT NULL,
    `is_enabled` boolean default true,
    `user_data_id` int(11) UNIQUE,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`user_data_id`) REFERENCES `user_data`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `user` (username, password)
VALUES 
('john','$2a$10$hKVc.cCDWla/tQI4qA1a8.BPz6TJxRHTZIVVnmmnBTTSNLOvM3zLu'),
('mary','$2a$10$hKVc.cCDWla/tQI4qA1a8.BPz6TJxRHTZIVVnmmnBTTSNLOvM3zLu'),
('susan','$2a$10$hKVc.cCDWla/tQI4qA1a8.BPz6TJxRHTZIVVnmmnBTTSNLOvM3zLu');

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
	PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `role` (name)
VALUES
('ROLE_USER'),
('ROLE_ADMIN');

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1,1),
(2,1),
(3,1),
(2,2);


