CREATE TABLE `character_shortcut` (
  `shortcut_id` int(10) NOT NULL AUTO_INCREMENT,
  `character_id` int(10) NOT NULL,
  `slot` int(2) NOT NULL,
  `page` int(1) NOT NULL,
  `type` enum('ITEM','SKILL','ACTION','MACRO','RECIPE','TPBOOKMARK') NOT NULL,
  `object_id` int(10) NOT NULL,
  `level` int(2) DEFAULT NULL,
  `character_type` int(10) DEFAULT NULL,
  PRIMARY KEY (`shortcut_id`),
  UNIQUE KEY `character_id-slot-page` (`character_id`,`slot`,`page`),
  KEY `character_id` (`character_id`),
  KEY `character_id-page` (`character_id`,`page`),
  KEY `character_id-type` (`character_id`,`type`)
);