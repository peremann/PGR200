CREATE DATABASE IF NOT EXISTS `things` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `things`;
CREATE TABLE `stuff` (
  `pk` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `things`.`stuff`
(`pk`,
`name`,
`value`)
VALUES
(1,
'Min flotte verdi!',
5);