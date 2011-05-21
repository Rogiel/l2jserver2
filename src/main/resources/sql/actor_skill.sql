CREATE TABLE IF NOT EXISTS `actor_skill` (
  `actor_id` int(10) NOT NULL,
  `skill_id` int(10) NOT NULL,
  `level` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`actor_id`,`skill_id`),
  KEY `actor_id` (`actor_id`),
  KEY `skill_id` (`skill_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;