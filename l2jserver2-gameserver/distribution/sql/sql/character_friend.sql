CREATE TABLE `character_friend` (
  `character_id` int(10) NOT NULL,
  `character_id_friend` int(10) NOT NULL,
  PRIMARY KEY (`character_id`,`character_id_friend`)
);