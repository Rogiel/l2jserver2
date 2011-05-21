CREATE TABLE IF NOT EXISTS `character_shortcut` (
  `character_id` int(10) NOT NULL,
  `shortcut_id` int(10) DEFAULT NULL,
  `slot` int(2) NOT NULL,
  `page` int(1) NOT NULL,
  `type` enum('ITEM','SKILL','ACTION','MACRO','RECIPE','TPBOOKMARK') NOT NULL,
  `level` int(2) DEFAULT NULL,
  `character_type` int(10) NOT NULL,
  PRIMARY KEY (`character_id`,`slot`,`page`),
  KEY `character_id` (`character_id`),
  KEY `shortcut_id` (`shortcut_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;