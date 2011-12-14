INSERT INTO `character` (`character_id`, `account_id`, `clan_id`, `name`, `race`, `class`, `sex`, `level`, `experience`, `sp`, `point_x`, `point_y`, `point_z`, `point_angle`, `appearance_hair_style`, `appearance_hair_color`, `apperance_face`) VALUES
(268437456, 'rogiel', NULL, 'Rogiel', 'HUMAN', 'HUMAN_FIGHTER', 'MALE', 1, 0, 0, -71338, 258271, -3104, 0, 'STYLE_B', 'COLOR_B', 'FACE_B'),
(268437457, 'rogiel2', NULL, 'Rogiel2', 'HUMAN', 'HUMAN_FIGHTER', 'MALE', 0, 0, 0, 0, 0, 0, 0, 'STYLE_A', 'COLOR_A', 'FACE_A');


INSERT INTO `item` (`item_id`, `template_id`, `character_id`, `location`, `paperdoll`, `count`, `coord_x`, `coord_y`, `coord_z`) VALUES
(268635457, 57, 268437456, 'INVENTORY', NULL, 200000000, NULL, NULL, NULL),
(268635459, 57, NULL, 'GROUND', NULL, 100, 147459, 24434, -1992),
(268635460, 1, 268437456, 'INVENTORY', NULL, 1, NULL, NULL, NULL);

