CREATE TABLE `item` (
  `item_id` int(12) NOT NULL,
  `template_id` int(10) NOT NULL,
  `character_id` int(12) NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `character_id` (`character_id`)
) ENGINE=MyISAM;