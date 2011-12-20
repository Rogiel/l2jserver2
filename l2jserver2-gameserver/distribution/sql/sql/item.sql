CREATE TABLE `item` (
  `item_id` int(12) NOT NULL,
  `template_id` int(10) NOT NULL,
  `character_id` int(12) DEFAULT NULL,
  `location` enum('GROUND','PAPERDOLL','INVENTORY','WAREHOUSE') DEFAULT NULL,
  `paperdoll` enum('UNDERWEAR','HEAD','HAIR1','HAIR2','NECK','RIGHT_HAND','CHEST','LEFT_HAND','RIGHT_EAR','LEFT_EAR','GLOVES','LEGS','FEET','RIGHT_FINGER','LEFT_FINGER','LEFT_BRACELET','RIGHT_BRACELET','DECORATION_1','DECORATION_2','DECORATION_3','DECORATION_4','DECORATION_5','DECORATION_6','CLOAK,BELT') DEFAULT NULL,
  `count` int(10) NOT NULL,
  `coord_x` int(10) DEFAULT NULL,
  `coord_y` int(10) DEFAULT NULL,
  `coord_z` int(10) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `character_id` (`character_id`),
  KEY `template_id` (`template_id`)
);