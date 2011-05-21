CREATE TABLE IF NOT EXISTS `npc_spawn` (
  `npc_id` int(10) NOT NULL,
  `point_x` int(10) NOT NULL,
  `point_y` int(10) NOT NULL,
  `point_z` int(10) NOT NULL,
  `point_angle` double NOT NULL,
  KEY `npc_id` (`npc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;