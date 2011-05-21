CREATE TABLE IF NOT EXISTS `clan` (
  `clan_id` int(10) NOT NULL,
  `character_id_leader` int(10) NOT NULL,
  PRIMARY KEY (`clan_id`),
  KEY `character_id_leader` (`character_id_leader`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;