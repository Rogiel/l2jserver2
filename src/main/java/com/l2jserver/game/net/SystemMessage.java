/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY, without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.game.net;

import com.l2jserver.game.net.packet.server.SM_SYSTEM_MESSAGE;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public enum SystemMessage {
	/**
	 * ID: 0<br>
	 * Message: You have been disconnected from the server.
	 */
	YOU_HAVE_BEEN_DISCONNECTED(0),

	/**
	 * ID: 1<br>
	 * Message: The server will be coming down in $1 seconds. Please find a safe
	 * place to log out.
	 */
	THE_SERVER_WILL_BE_COMING_DOWN_IN_S1_SECONDS(1),

	/**
	 * ID: 2<br>
	 * Message: $s1 does not exist.
	 */
	S1_DOES_NOT_EXIST(2),

	/**
	 * ID: 3<br>
	 * Message: $s1 is not currently logged in.
	 */
	S1_IS_NOT_ONLINE(3),

	/**
	 * ID: 4<br>
	 * Message: You cannot ask yourself to apply to a clan.
	 */
	CANNOT_INVITE_YOURSELF(4),

	/**
	 * ID: 5<br>
	 * Message: $s1 already exists.
	 */
	S1_ALREADY_EXISTS(5),

	/**
	 * ID: 6<br>
	 * Message: $s1 does not exist
	 */
	S1_DOES_NOT_EXIST2(6),

	/**
	 * ID: 7<br>
	 * Message: You are already a member of $s1.
	 */
	ALREADY_MEMBER_OF_S1(7),

	/**
	 * ID: 8<br>
	 * Message: You are working with another clan.
	 */
	YOU_ARE_WORKING_WITH_ANOTHER_CLAN(8),

	/**
	 * ID: 9<br>
	 * Message: $s1 is not a clan leader.
	 */
	S1_IS_NOT_A_CLAN_LEADER(9),

	/**
	 * ID: 10<br>
	 * Message: $s1 is working with another clan.
	 */
	S1_WORKING_WITH_ANOTHER_CLAN(10),

	/**
	 * ID: 11<br>
	 * Message: There are no applicants for this clan.
	 */
	NO_APPLICANTS_FOR_THIS_CLAN(11),

	/**
	 * ID: 12<br>
	 * Message: The applicant information is incorrect.
	 */
	APPLICANT_INFORMATION_INCORRECT(12),

	/**
	 * ID: 13<br>
	 * Message: Unable to disperse: your clan has requested to participate in a
	 * castle siege.
	 */
	CANNOT_DISSOLVE_CAUSE_CLAN_WILL_PARTICIPATE_IN_CASTLE_SIEGE(13),

	/**
	 * ID: 14<br>
	 * Message: Unable to disperse: your clan owns one or more castles or
	 * hideouts.
	 */
	CANNOT_DISSOLVE_CAUSE_CLAN_OWNS_CASTLES_HIDEOUTS(14),

	/**
	 * ID: 15<br>
	 * Message: You are in siege.
	 */
	YOU_ARE_IN_SIEGE(15),

	/**
	 * ID: 16<br>
	 * Message: You are not in siege.
	 */
	YOU_ARE_NOT_IN_SIEGE(16),

	/**
	 * ID: 17<br>
	 * Message: The castle siege has begun.
	 */
	CASTLE_SIEGE_HAS_BEGUN(17),

	/**
	 * ID: 18<br>
	 * Message: The castle siege has ended.
	 */
	CASTLE_SIEGE_HAS_ENDED(18),

	/**
	 * ID: 19<br>
	 * Message: There is a new Lord of the castle!
	 */
	NEW_CASTLE_LORD(19),

	/**
	 * ID: 20<br>
	 * Message: The gate is being opened.
	 */
	GATE_IS_OPENING(20),

	/**
	 * ID: 21<br>
	 * Message: The gate is being destroyed.
	 */
	GATE_IS_DESTROYED(21),

	/**
	 * ID: 22<br>
	 * Message: Your target is out of range.
	 */
	TARGET_TOO_FAR(22),

	/**
	 * ID: 23<br>
	 * Message: Not enough HP.
	 */
	NOT_ENOUGH_HP(23),

	/**
	 * ID: 24<br>
	 * Message: Not enough MP.
	 */
	NOT_ENOUGH_MP(24),

	/**
	 * ID: 25<br>
	 * Message: Rejuvenating HP.
	 */
	REJUVENATING_HP(25),

	/**
	 * ID: 26<br>
	 * Message: Rejuvenating MP.
	 */
	REJUVENATING_MP(26),

	/**
	 * ID: 27<br>
	 * Message: Your casting has been interrupted.
	 */
	CASTING_INTERRUPTED(27),

	/**
	 * ID: 28<br>
	 * Message: You have obtained $s1 adena.
	 */
	YOU_PICKED_UP_S1_ADENA(28),

	/**
	 * ID: 29<br>
	 * Message: You have obtained $s2 $s1.
	 */
	YOU_PICKED_UP_S1_S2(29),

	/**
	 * ID: 30<br>
	 * Message: You have obtained $s1.
	 */
	YOU_PICKED_UP_S1(30),

	/**
	 * ID: 31<br>
	 * Message: You cannot move while sitting.
	 */
	CANT_MOVE_SITTING(31),

	/**
	 * ID: 32<br>
	 * Message: You are unable to engage in combat. Please go to the nearest
	 * restart point.
	 */
	UNABLE_COMBAT_PLEASE_GO_RESTART(32),

	/**
	 * ID: 32<br>
	 * Message: You cannot move while casting.
	 */
	CANT_MOVE_CASTING(33),

	/**
	 * ID: 34<br>
	 * Message: Welcome to the World of Lineage II.
	 */
	WELCOME_TO_LINEAGE(34),

	/**
	 * ID: 35<br>
	 * Message: You hit for $s1 damage
	 */
	YOU_DID_S1_DMG(35),

	/**
	 * ID: 36<br>
	 * Message: $c1 hit you for $s2 damage.
	 */
	C1_GAVE_YOU_S2_DMG(36),

	/**
	 * ID: 37<br>
	 * Message: $c1 hit you for $s2 damage.
	 */
	C1_GAVE_YOU_S2_DMG2(37),

	/**
	 * ID: 41<br>
	 * Message: You carefully nock an arrow.
	 */
	GETTING_READY_TO_SHOOT_AN_ARROW(41),

	/**
	 * ID: 42<br>
	 * Message: You have avoided $c1's attack.
	 */
	AVOIDED_C1_ATTACK(42),

	/**
	 * ID: 43<br>
	 * Message: You have missed.
	 */
	MISSED_TARGET(43),

	/**
	 * ID: 44<br>
	 * Message: Critical hit!
	 */
	CRITICAL_HIT(44),

	/**
	 * ID: 45<br>
	 * Message: You have earned $s1 experience.
	 */
	EARNED_S1_EXPERIENCE(45),

	/**
	 * ID: 46<br>
	 * Message: You use $s1.
	 */
	USE_S1(46),

	/**
	 * ID: 47<br>
	 * Message: You begin to use a(n) $s1.
	 */
	BEGIN_TO_USE_S1(46),

	/**
	 * ID: 48<br>
	 * Message: $s1 is not available at this time: being prepared for reuse.
	 */
	S1_PREPARED_FOR_REUSE(48),

	/**
	 * ID: 49<br>
	 * Message: You have equipped your $s1.
	 */
	S1_EQUIPPED(49),

	/**
	 * ID: 50<br>
	 * Message: Your target cannot be found.
	 */
	TARGET_CANT_FOUND(50),

	/**
	 * ID: 51<br>
	 * Message: You cannot use this on yourself.
	 */
	CANNOT_USE_ON_YOURSELF(51),

	/**
	 * ID: 52<br>
	 * Message: You have earned $s1 adena.
	 */
	EARNED_S1_ADENA(52),

	/**
	 * ID: 53<br>
	 * Message: You have earned $s2 $s1(s).
	 */
	EARNED_S2_S1_S(53),

	/**
	 * ID: 54<br>
	 * Message: You have earned $s1.
	 */
	EARNED_ITEM_S1(54),

	/**
	 * ID: 55<br>
	 * Message: You have failed to pick up $s1 adena.
	 */
	FAILED_TO_PICKUP_S1_ADENA(55),

	/**
	 * ID: 56<br>
	 * Message: You have failed to pick up $s1.
	 */
	FAILED_TO_PICKUP_S1(56),

	/**
	 * ID: 57<br>
	 * Message: You have failed to pick up $s2 $s1(s).
	 */
	FAILED_TO_PICKUP_S2_S1_S(57),

	/**
	 * ID: 58<br>
	 * Message: You have failed to earn $s1 adena.
	 */
	FAILED_TO_EARN_S1_ADENA(58),

	/**
	 * ID: 59<br>
	 * Message: You have failed to earn $s1.
	 */
	FAILED_TO_EARN_S1(59),

	/**
	 * ID: 60<br>
	 * Message: You have failed to earn $s2 $s1(s).
	 */
	FAILED_TO_EARN_S2_S1_S(60),

	/**
	 * ID: 61<br>
	 * Message: Nothing happened.
	 */
	NOTHING_HAPPENED(61),

	/**
	 * ID: 62<br>
	 * Message: Your $s1 has been successfully enchanted.
	 */
	S1_SUCCESSFULLY_ENCHANTED(62),

	/**
	 * ID: 63<br>
	 * Message: Your +$S1 $S2 has been successfully enchanted.
	 */
	S1_S2_SUCCESSFULLY_ENCHANTED(63),

	/**
	 * ID: 64<br>
	 * Message: The enchantment has failed! Your $s1 has been crystallized.
	 */
	ENCHANTMENT_FAILED_S1_EVAPORATED(64),

	/**
	 * ID: 65<br>
	 * Message: The enchantment has failed! Your +$s1 $s2 has been crystallized.
	 */
	ENCHANTMENT_FAILED_S1_S2_EVAPORATED(65),

	/**
	 * ID: 66<br>
	 * Message: $c1 is inviting you to join a party. Do you accept?
	 */
	C1_INVITED_YOU_TO_PARTY(66),

	/**
	 * ID: 67<br>
	 * Message: $s1 has invited you to the join the clan, $s2. Do you wish to
	 * join?
	 */
	S1_HAS_INVITED_YOU_TO_JOIN_THE_CLAN_S2(67),

	/**
	 * ID: 68<br>
	 * Message: Would you like to withdraw from the $s1 clan? If you leave, you
	 * will have to wait at least a day before joining another clan.
	 */
	WOULD_YOU_LIKE_TO_WITHDRAW_FROM_THE_S1_CLAN(68),

	/**
	 * ID: 69<br>
	 * Message: Would you like to dismiss $s1 from the clan? If you do so, you
	 * will have to wait at least a day before accepting a new member.
	 */
	WOULD_YOU_LIKE_TO_DISMISS_S1_FROM_THE_CLAN(69),

	/**
	 * ID: 70<br>
	 * Message: Do you wish to disperse the clan, $s1?
	 */
	DO_YOU_WISH_TO_DISPERSE_THE_CLAN_S1(70),

	/**
	 * ID: 71<br>
	 * Message: How many of your $s1(s) do you wish to discard?
	 */
	HOW_MANY_S1_DISCARD(71),

	/**
	 * ID: 72<br>
	 * Message: How many of your $s1(s) do you wish to move?
	 */
	HOW_MANY_S1_MOVE(72),

	/**
	 * ID: 73<br>
	 * Message: How many of your $s1(s) do you wish to destroy?
	 */
	HOW_MANY_S1_DESTROY(73),

	/**
	 * ID: 74<br>
	 * Message: Do you wish to destroy your $s1?
	 */
	WISH_DESTROY_S1(74),

	/**
	 * ID: 75<br>
	 * Message: ID does not exist.
	 */
	ID_NOT_EXIST(75),

	/**
	 * ID: 76<br>
	 * Message: Incorrect password.
	 */
	INCORRECT_PASSWORD(76),

	/**
	 * ID: 77<br>
	 * Message: You cannot create another character. Please delete the existing
	 * character and try again.
	 */
	CANNOT_CREATE_CHARACTER(77),

	/**
	 * ID: 78<br>
	 * Message: When you delete a character, any items in his/her possession
	 * will also be deleted. Do you really wish to delete $s1%?
	 */
	WISH_DELETE_S1(78),

	/**
	 * ID: 79<br>
	 * Message: This name already exists.
	 */
	NAMING_NAME_ALREADY_EXISTS(79),

	/**
	 * ID: 80<br>
	 * Message: Names must be between 1-16 characters, excluding spaces or
	 * special characters.
	 */
	NAMING_CHARNAME_UP_TO_16CHARS(80),

	/**
	 * ID: 81<br>
	 * Message: Please select your race.
	 */
	PLEASE_SELECT_RACE(81),

	/**
	 * ID: 82<br>
	 * Message: Please select your occupation.
	 */
	PLEASE_SELECT_OCCUPATION(82),

	/**
	 * ID: 83<br>
	 * Message: Please select your gender.
	 */
	PLEASE_SELECT_GENDER(83),

	/**
	 * ID: 84<br>
	 * Message: You may not attack in a peaceful zone.
	 */
	CANT_ATK_PEACEZONE(84),

	/**
	 * ID: 85<br>
	 * Message: You may not attack this target in a peaceful zone.
	 */
	TARGET_IN_PEACEZONE(85),

	/**
	 * ID: 86<br>
	 * Message: Please enter your ID.
	 */
	PLEASE_ENTER_ID(86),

	/**
	 * ID: 87<br>
	 * Message: Please enter your password.
	 */
	PLEASE_ENTER_PASSWORD(87),

	/**
	 * ID: 88<br>
	 * Message: Your protocol version is different, please restart your client
	 * and run a full check.
	 */
	WRONG_PROTOCOL_CHECK(88),

	/**
	 * ID: 89<br>
	 * Message: Your protocol version is different, please continue.
	 */
	WRONG_PROTOCOL_CONTINUE(89),

	/**
	 * ID: 90<br>
	 * Message: You are unable to connect to the server.
	 */
	UNABLE_TO_CONNECT(90),

	/**
	 * ID: 91<br>
	 * Message: Please select your hairstyle.
	 */
	PLEASE_SELECT_HAIRSTYLE(91),

	/**
	 * ID: 92<br>
	 * Message: $s1 has worn off.
	 */
	S1_HAS_WORN_OFF(92),

	/**
	 * ID: 93<br>
	 * Message: You do not have enough SP for this.
	 */
	NOT_ENOUGH_SP(93),

	/**
	 * ID: 94<br>
	 * Message: 2004-2009 (c) Copyright NCsoft Corporation. All Rights Reserved.
	 */
	COPYRIGHT(94),

	/**
	 * ID: 95<br>
	 * Message: You have earned $s1 experience and $s2 SP.
	 */
	YOU_EARNED_S1_EXP_AND_S2_SP(95),

	/**
	 * ID: 96<br>
	 * Message: Your level has increased!
	 */
	YOU_INCREASED_YOUR_LEVEL(96),

	/**
	 * ID: 97<br>
	 * Message: This item cannot be moved.
	 */
	CANNOT_MOVE_THIS_ITEM(97),

	/**
	 * ID: 98<br>
	 * Message: This item cannot be discarded.
	 */
	CANNOT_DISCARD_THIS_ITEM(98),

	/**
	 * ID: 99<br>
	 * Message: This item cannot be traded or sold.
	 */
	CANNOT_TRADE_THIS_ITEM(99),

	/**
	 * ID: 100<br>
	 * Message: $c1 is requesting to trade. Do you wish to continue?
	 */
	C1_REQUESTS_TRADE(100),

	/**
	 * ID: 101<br>
	 * Message: You cannot exit while in combat.
	 */
	CANT_LOGOUT_WHILE_FIGHTING(101),

	/**
	 * ID: 102<br>
	 * Message: You cannot restart while in combat.
	 */
	CANT_RESTART_WHILE_FIGHTING(102),

	/**
	 * ID: 103<br>
	 * Message: This ID is currently logged in.
	 */
	ID_LOGGED_IN(103),

	/**
	 * ID: 104<br>
	 * Message: You may not equip items while casting or performing a skill.
	 */
	CANNOT_USE_ITEM_WHILE_USING_MAGIC(104),

	/**
	 * ID: 105<br>
	 * Message: $c1 has been invited to the party.
	 */
	C1_INVITED_TO_PARTY(105),

	/**
	 * ID: 106<br>
	 * Message: You have joined $s1's party.
	 */
	YOU_JOINED_S1_PARTY(106),

	/**
	 * ID: 107<br>
	 * Message: $c1 has joined the party.
	 */
	C1_JOINED_PARTY(107),

	/**
	 * ID: 108<br>
	 * Message: $c1 has left the party.
	 */
	C1_LEFT_PARTY(108),

	/**
	 * ID: 109<br>
	 * Message: Invalid target.
	 */
	INCORRECT_TARGET(109),

	/**
	 * ID: 110<br>
	 * Message: $s1 $s2's effect can be felt.
	 */
	YOU_FEEL_S1_EFFECT(110),

	/**
	 * ID: 111<br>
	 * Message: Your shield defense has succeeded.
	 */
	SHIELD_DEFENCE_SUCCESSFULL(111),

	/**
	 * ID: 112<br>
	 * Message: You may no longer adjust items in the trade because the trade
	 * has been confirmed.
	 */
	NOT_ENOUGH_ARROWS(112),

	/**
	 * ID: 113<br>
	 * Message: $s1 cannot be used due to unsuitable terms.
	 */
	S1_CANNOT_BE_USED(113),

	/**
	 * ID: 114<br>
	 * Message: You have entered the shadow of the Mother Tree.
	 */
	ENTER_SHADOW_MOTHER_TREE(114),

	/**
	 * ID: 115<br>
	 * Message: You have left the shadow of the Mother Tree.
	 */
	EXIT_SHADOW_MOTHER_TREE(115),

	/**
	 * ID: 116<br>
	 * Message: You have entered a peaceful zone.
	 */
	ENTER_PEACEFUL_ZONE(116),

	/**
	 * ID: 117<br>
	 * Message: You have left the peaceful zone.
	 */
	EXIT_PEACEFUL_ZONE(117),

	/**
	 * ID: 118<br>
	 * Message: You have requested a trade with $c1
	 */
	REQUEST_C1_FOR_TRADE(118),

	/**
	 * ID: 119<br>
	 * Message: $c1 has denied your request to trade.
	 */
	C1_DENIED_TRADE_REQUEST(119),

	/**
	 * ID: 120<br>
	 * Message: You begin trading with $c1.
	 */
	BEGIN_TRADE_WITH_C1(120),

	/**
	 * ID: 121<br>
	 * Message: $c1 has confirmed the trade.
	 */
	C1_CONFIRMED_TRADE(121),

	/**
	 * ID: 122<br>
	 * Message: You may no longer adjust items in the trade because the trade
	 * has been confirmed.
	 */
	CANNOT_ADJUST_ITEMS_AFTER_TRADE_CONFIRMED(122),

	/**
	 * ID: 123<br>
	 * Message: Your trade is successful.
	 */
	TRADE_SUCCESSFUL(123),

	/**
	 * ID: 124<br>
	 * Message: $c1 has cancelled the trade.
	 */
	C1_CANCELED_TRADE(124),

	/**
	 * ID: 125<br>
	 * Message: Do you wish to exit the game?
	 */
	WISH_EXIT_GAME(125),

	/**
	 * ID: 126<br>
	 * Message: Do you wish to return to the character select screen?
	 */
	WISH_RESTART_GAME(126),

	/**
	 * ID: 127<br>
	 * Message: You have been disconnected from the server. Please login again.
	 */
	DISCONNECTED_FROM_SERVER(127),

	/**
	 * ID: 128<br>
	 * Message: Your character creation has failed.
	 */
	CHARACTER_CREATION_FAILED(128),

	/**
	 * ID: 129<br>
	 * Message: Your inventory is full.
	 */
	SLOTS_FULL(129),

	/**
	 * ID: 130<br>
	 * Message: Your warehouse is full.
	 */
	WAREHOUSE_FULL(130),

	/**
	 * ID: 131<br>
	 * Message: $s1 has logged in.
	 */
	S1_LOGGED_IN(131),

	/**
	 * ID: 132<br>
	 * Message: $s1 has been added to your friends list.
	 */
	S1_ADDED_TO_FRIENDS(132),

	/**
	 * ID: 133<br>
	 * Message: $s1 has been removed from your friends list.
	 */
	S1_REMOVED_FROM_YOUR_FRIENDS_LIST(133),

	/**
	 * ID: 134<br>
	 * Message: Please check your friends list again.
	 */
	PLEACE_CHECK_YOUR_FRIEND_LIST_AGAIN(134),

	/**
	 * ID: 135<br>
	 * Message: $c1 did not reply to your invitation. Your invitation has been
	 * cancelled.
	 */
	C1_DID_NOT_REPLY_TO_YOUR_INVITE(135),

	/**
	 * ID: 136<br>
	 * Message: You have not replied to $c1's invitation. The offer has been
	 * cancelled.
	 */
	YOU_DID_NOT_REPLY_TO_C1_INVITE(136),

	/**
	 * ID: 137<br>
	 * Message: There are no more items in the shortcut.
	 */
	NO_MORE_ITEMS_SHORTCUT(137),

	/**
	 * ID: 138<br>
	 * Message: Designate shortcut.
	 */
	DESIGNATE_SHORTCUT(138),

	/**
	 * ID: 139<br>
	 * Message: $c1 has resisted your $s2.
	 */
	C1_RESISTED_YOUR_S2(139),

	/**
	 * ID: 140<br>
	 * Message: Your skill was removed due to a lack of MP.
	 */
	SKILL_REMOVED_DUE_LACK_MP(140),

	/**
	 * ID: 141<br>
	 * Message: Once the trade is confirmed, the item cannot be moved again.
	 */
	ONCE_THE_TRADE_IS_CONFIRMED_THE_ITEM_CANNOT_BE_MOVED_AGAIN(141),

	/**
	 * ID: 142<br>
	 * Message: You are already trading with someone.
	 */
	ALREADY_TRADING(142),

	/**
	 * ID: 143<br>
	 * Message: $c1 is already trading with another person. Please try again
	 * later.
	 */
	C1_ALREADY_TRADING(142),

	/**
	 * ID: 144<br>
	 * Message: That is the incorrect target.
	 */
	TARGET_IS_INCORRECT(144),

	/**
	 * ID: 145<br>
	 * Message: That player is not online.
	 */
	TARGET_IS_NOT_FOUND_IN_THE_GAME(145),

	/**
	 * ID: 146<br>
	 * Message: Chatting is now permitted.
	 */
	CHATTING_PERMITTED(146),

	/**
	 * ID: 147<br>
	 * Message: Chatting is currently prohibited.
	 */
	CHATTING_PROHIBITED(147),

	/**
	 * ID: 148<br>
	 * Message: You cannot use quest items.
	 */
	CANNOT_USE_QUEST_ITEMS(148),

	/**
	 * ID: 149<br>
	 * Message: You cannot pick up or use items while trading.
	 */
	CANNOT_USE_ITEM_WHILE_TRADING(149),

	/**
	 * ID: 150<br>
	 * Message: You cannot discard or destroy an item while trading at a private
	 * store.
	 */
	CANNOT_DISCARD_OR_DESTROY_ITEM_WHILE_TRADING(150),

	/**
	 * ID: 151<br>
	 * Message: That is too far from you to discard.
	 */
	CANNOT_DISCARD_DISTANCE_TOO_FAR(151),

	/**
	 * ID: 152<br>
	 * Message: You have invited the wrong target.
	 */
	YOU_HAVE_INVITED_THE_WRONG_TARGET(152),

	/**
	 * ID: 153<br>
	 * Message: $c1 is on another task. Please try again later.
	 */
	C1_IS_BUSY_TRY_LATER(153),

	/**
	 * ID: 154<br>
	 * Message: Only the leader can give out invitations.
	 */
	ONLY_LEADER_CAN_INVITE(154),

	/**
	 * ID: 155<br>
	 * Message: The party is full.
	 */
	PARTY_FULL(155),

	/**
	 * ID: 156<br>
	 * Message: Drain was only 50 percent successful.
	 */
	DRAIN_HALF_SUCCESFUL(156),

	/**
	 * ID: 157<br>
	 * Message: You resisted $c1's drain.
	 */
	RESISTED_C1_DRAIN(157),

	/**
	 * ID: 158<br>
	 * Message: Your attack has failed.
	 */
	ATTACK_FAILED(158),

	/**
	 * ID: 159<br>
	 * Message: You resisted $c1's magic.
	 */
	RESISTED_C1_MAGIC(159),

	/**
	 * ID: 160<br>
	 * Message: $c1 is a member of another party and cannot be invited.
	 */
	C1_IS_ALREADY_IN_PARTY(160),

	/**
	 * ID: 161<br>
	 * Message: That player is not currently online.
	 */
	INVITED_USER_NOT_ONLINE(161),

	/**
	 * ID: 162<br>
	 * Message: Warehouse is too far.
	 */
	WAREHOUSE_TOO_FAR(162),

	/**
	 * ID: 163<br>
	 * Message: You cannot destroy it because the number is incorrect.
	 */
	CANNOT_DESTROY_NUMBER_INCORRECT(163),

	/**
	 * ID: 164<br>
	 * Message: Waiting for another reply.
	 */
	WAITING_FOR_ANOTHER_REPLY(164),

	/**
	 * ID: 165<br>
	 * Message: You cannot add yourself to your own friend list.
	 */
	YOU_CANNOT_ADD_YOURSELF_TO_OWN_FRIEND_LIST(165),

	/**
	 * ID: 166<br>
	 * Message: Friend list is not ready yet. Please register again later.
	 */
	FRIEND_LIST_NOT_READY_YET_REGISTER_LATER(166),

	/**
	 * ID: 167<br>
	 * Message: $c1 is already on your friend list.
	 */
	C1_ALREADY_ON_FRIEND_LIST(167),

	/**
	 * ID: 168<br>
	 * Message: $c1 has sent a friend request.
	 */
	C1_REQUESTED_TO_BECOME_FRIENDS(168),

	/**
	 * ID: 169<br>
	 * Message: Accept friendship 0/1 (1 to accept, 0 to deny)
	 */
	ACCEPT_THE_FRIENDSHIP(169),

	/**
	 * ID: 170<br>
	 * Message: The user who requested to become friends is not found in the
	 * game.
	 */
	THE_USER_YOU_REQUESTED_IS_NOT_IN_GAME(170),

	/**
	 * ID: 171<br>
	 * Message: $c1 is not on your friend list.
	 */
	C1_NOT_ON_YOUR_FRIENDS_LIST(171),

	/**
	 * ID: 172<br>
	 * Message: You lack the funds needed to pay for this transaction.
	 */
	LACK_FUNDS_FOR_TRANSACTION1(172),

	/**
	 * ID: 173<br>
	 * Message: You lack the funds needed to pay for this transaction.
	 */
	LACK_FUNDS_FOR_TRANSACTION2(173),

	/**
	 * ID: 174<br>
	 * Message: That person's inventory is full.
	 */
	OTHER_INVENTORY_FULL(174),

	/**
	 * ID: 175<br>
	 * Message: That skill has been de-activated as HP was fully recovered.
	 */
	SKILL_DEACTIVATED_HP_FULL(175),

	/**
	 * ID: 176<br>
	 * Message: That person is in message refusal mode.
	 */
	THE_PERSON_IS_IN_MESSAGE_REFUSAL_MODE(176),

	/**
	 * ID: 177<br>
	 * Message: Message refusal mode.
	 */
	MESSAGE_REFUSAL_MODE(177),

	/**
	 * ID: 178<br>
	 * Message: Message acceptance mode.
	 */
	MESSAGE_ACCEPTANCE_MODE(178),

	/**
	 * ID: 179<br>
	 * Message: You cannot discard those items here.
	 */
	CANT_DISCARD_HERE(179),

	/**
	 * ID: 180<br>
	 * Message: You have $s1 day(s) left until deletion. Do you wish to cancel
	 * this action?
	 */
	S1_DAYS_LEFT_CANCEL_ACTION(180),

	/**
	 * ID: 181<br>
	 * Message: Cannot see target.
	 */
	CANT_SEE_TARGET(181),

	/**
	 * ID: 182<br>
	 * Message: Do you want to quit the current quest?
	 */
	WANT_QUIT_CURRENT_QUEST(182),

	/**
	 * ID: 183<br>
	 * Message: There are too many users on the server. Please try again later
	 */
	TOO_MANY_USERS(183),

	/**
	 * ID: 184<br>
	 * Message: Please try again later.
	 */
	TRY_AGAIN_LATER(184),

	/**
	 * ID: 185<br>
	 * Message: You must first select a user to invite to your party.
	 */
	FIRST_SELECT_USER_TO_INVITE_TO_PARTY(185),

	/**
	 * ID: 186<br>
	 * Message: You must first select a user to invite to your clan.
	 */
	FIRST_SELECT_USER_TO_INVITE_TO_CLAN(186),

	/**
	 * ID: 187<br>
	 * Message: Select user to expel.
	 */
	SELECT_USER_TO_EXPEL(187),

	/**
	 * ID: 188<br>
	 * Message: Please create your clan name.
	 */
	PLEASE_CREATE_CLAN_NAME(188),

	/**
	 * ID: 189<br>
	 * Message: Your clan has been created.
	 */
	CLAN_CREATED(189),

	/**
	 * ID: 190<br>
	 * Message: You have failed to create a clan.
	 */
	FAILED_TO_CREATE_CLAN(190),

	/**
	 * ID: 191<br>
	 * Message: Clan member $s1 has been expelled.
	 */
	CLAN_MEMBER_S1_EXPELLED(191),

	/**
	 * ID: 192<br>
	 * Message: You have failed to expel $s1 from the clan.
	 */
	FAILED_EXPEL_S1(192),

	/**
	 * ID: 193<br>
	 * Message: Clan has dispersed.
	 */
	CLAN_HAS_DISPERSED(193),

	/**
	 * ID: 194<br>
	 * Message: You have failed to disperse the clan.
	 */
	FAILED_TO_DISPERSE_CLAN(194),

	/**
	 * ID: 195<br>
	 * Message: Entered the clan.
	 */
	ENTERED_THE_CLAN(195),

	/**
	 * ID: 196<br>
	 * Message: $s1 declined your clan invitation.
	 */
	S1_REFUSED_TO_JOIN_CLAN(196),

	/**
	 * ID: 197<br>
	 * Message: You have withdrawn from the clan.
	 */
	YOU_HAVE_WITHDRAWN_FROM_CLAN(197),

	/**
	 * ID: 198<br>
	 * Message: You have failed to withdraw from the $s1 clan.
	 */
	FAILED_TO_WITHDRAW_FROM_S1_CLAN(198),

	/**
	 * ID: 199<br>
	 * Message: You have recently been dismissed from a clan. You are not
	 * allowed to join another clan for 24-hours.
	 */
	CLAN_MEMBERSHIP_TERMINATED(199),

	/**
	 * ID: 200<br>
	 * Message: You have withdrawn from the party.
	 */
	YOU_LEFT_PARTY(200),

	/**
	 * ID: 201<br>
	 * Message: $c1 was expelled from the party.
	 */
	C1_WAS_EXPELLED_FROM_PARTY(201),

	/**
	 * ID: 202<br>
	 * Message: You have been expelled from the party.
	 */
	HAVE_BEEN_EXPELLED_FROM_PARTY(202),

	/**
	 * ID: 203<br>
	 * Message: The party has dispersed.
	 */
	PARTY_DISPERSED(203),

	/**
	 * ID: 204<br>
	 * Message: Incorrect name. Please try again.
	 */
	INCORRECT_NAME_TRY_AGAIN(204),

	/**
	 * ID: 205<br>
	 * Message: Incorrect character name. Please try again.
	 */
	INCORRECT_CHARACTER_NAME_TRY_AGAIN(205),

	/**
	 * ID: 206<br>
	 * Message: Please enter the name of the clan you wish to declare war on.
	 */
	ENTER_CLAN_NAME_TO_DECLARE_WAR(206),

	/**
	 * ID: 207<br>
	 * Message: $s2 of the clan $s1 requests declaration of war. Do you accept?
	 */
	S2_OF_THE_CLAN_S1_REQUESTS_WAR(207),

	/**
	 * ID: 212<br>
	 * Message: You are not a clan member and cannot perform this action.
	 */
	YOU_ARE_NOT_A_CLAN_MEMBER(212),

	/**
	 * ID: 213<br>
	 * Message: Not working. Please try again later.
	 */
	NOT_WORKING_PLEASE_TRY_AGAIN_LATER(184),

	/**
	 * ID: 214<br>
	 * Message: Your title has been changed.
	 */
	TITLE_CHANGED(214),

	/**
	 * ID: 215<br>
	 * Message: War with the $s1 clan has begun.
	 */
	WAR_WITH_THE_S1_CLAN_HAS_BEGUN(215),

	/**
	 * ID: 216<br>
	 * Message: War with the $s1 clan has ended.
	 */
	WAR_WITH_THE_S1_CLAN_HAS_ENDED(216),

	/**
	 * ID: 217<br>
	 * Message: You have won the war over the $s1 clan!
	 */
	YOU_HAVE_WON_THE_WAR_OVER_THE_S1_CLAN(217),

	/**
	 * ID: 218<br>
	 * Message: You have surrendered to the $s1 clan.
	 */
	YOU_HAVE_SURRENDERED_TO_THE_S1_CLAN(218),

	/**
	 * ID: 219<br>
	 * Message: Your clan leader has died. You have been defeated by the $s1
	 * clan.
	 */
	YOU_WERE_DEFEATED_BY_S1_CLAN(219),

	/**
	 * ID: 220<br>
	 * Message: You have $s1 minutes left until the clan war ends.
	 */
	S1_MINUTES_LEFT_UNTIL_CLAN_WAR_ENDS(220),

	/**
	 * ID: 221<br>
	 * Message: The time limit for the clan war is up. War with the $s1 clan is
	 * over.
	 */
	CLAN_WAR_WITH_S1_CLAN_HAS_ENDED(221),

	/**
	 * ID: 222<br>
	 * Message: $s1 has joined the clan.
	 */
	S1_HAS_JOINED_CLAN(222),

	/**
	 * ID: 223<br>
	 * Message: $s1 has withdrawn from the clan.
	 */
	S1_HAS_WITHDRAWN_FROM_THE_CLAN(223),

	/**
	 * ID: 224<br>
	 * Message: $s1 did not respond: Invitation to the clan has been cancelled.
	 */
	S1_DID_NOT_RESPOND_TO_CLAN_INVITATION(224),

	/**
	 * ID: 225<br>
	 * Message: You didn't respond to $s1's invitation: joining has been
	 * cancelled.
	 */
	YOU_DID_NOT_RESPOND_TO_S1_CLAN_INVITATION(225),

	/**
	 * ID: 226<br>
	 * Message: The $s1 clan did not respond: war proclamation has been refused.
	 */
	S1_CLAN_DID_NOT_RESPOND(226),

	/**
	 * ID: 227<br>
	 * Message: Clan war has been refused because you did not respond to $s1
	 * clan's war proclamation.
	 */
	CLAN_WAR_REFUSED_YOU_DID_NOT_RESPOND_TO_S1(227),

	/**
	 * ID: 228<br>
	 * Message: Request to end war has been denied.
	 */
	REQUEST_TO_END_WAR_HAS_BEEN_DENIED(228),

	/**
	 * ID: 229<br>
	 * Message: You do not meet the criteria in order to create a clan.
	 */
	YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN(229),

	/**
	 * ID: 230<br>
	 * Message: You must wait 10 days before creating a new clan.
	 */
	YOU_MUST_WAIT_XX_DAYS_BEFORE_CREATING_A_NEW_CLAN(230),

	/**
	 * ID: 231<br>
	 * Message: After a clan member is dismissed from a clan, the clan must wait
	 * at least a day before accepting a new member.
	 */
	YOU_MUST_WAIT_BEFORE_ACCEPTING_A_NEW_MEMBER(231),

	/**
	 * ID: 232<br>
	 * Message: After leaving or having been dismissed from a clan, you must
	 * wait at least a day before joining another clan.
	 */
	YOU_MUST_WAIT_BEFORE_JOINING_ANOTHER_CLAN(232),

	/**
	 * ID: 233<br>
	 * Message:
	 */
	SUBCLAN_IS_FULL(233),

	/**
	 * ID: 234<br>
	 * Message: The target must be a clan member.
	 */
	TARGET_MUST_BE_IN_CLAN(234),

	/**
	 * ID: 235<br>
	 * Message: You are not authorized to bestow these rights.
	 */
	NOT_AUTHORIZED_TO_BESTOW_RIGHTS(235),

	/**
	 * ID: 236<br>
	 * Message: Only the clan leader is enabled.
	 */
	ONLY_THE_CLAN_LEADER_IS_ENABLED(236),

	/**
	 * ID: 237<br>
	 * Message: The clan leader could not be found.
	 */
	CLAN_LEADER_NOT_FOUND(237),

	/**
	 * ID: 238<br>
	 * Message: Not joined in any clan.
	 */
	NOT_JOINED_IN_ANY_CLAN(238),

	/**
	 * ID: 239<br>
	 * Message: The clan leader cannot withdraw.
	 */
	CLAN_LEADER_CANNOT_WITHDRAW(239),

	/**
	 * ID: 240<br>
	 * Message: Currently involved in clan war.
	 */
	CURRENTLY_INVOLVED_IN_CLAN_WAR(240),

	/**
	 * ID: 241<br>
	 * Message: Leader of the $s1 Clan is not logged in.
	 */
	LEADER_OF_S1_CLAN_NOT_FOUND(241),

	/**
	 * ID: 242<br>
	 * Message: Select target.
	 */
	SELECT_TARGET(242),

	/**
	 * ID: 243<br>
	 * Message: You cannot declare war on an allied clan.
	 */
	CANNOT_DECLARE_WAR_ON_ALLIED_CLAN(243),

	/**
	 * ID: 244<br>
	 * Message: You are not allowed to issue this challenge.
	 */
	NOT_ALLOWED_TO_CHALLENGE(244),

	/**
	 * ID: 245<br>
	 * Message: 5 days has not passed since you were refused war. Do you wish to
	 * continue?
	 */
	FIVE_DAYS_NOT_PASSED_SINCE_REFUSED_WAR(245),

	/**
	 * ID: 246<br>
	 * Message: That clan is currently at war.
	 */
	CLAN_CURRENTLY_AT_WAR(246),

	/**
	 * ID: 247<br>
	 * Message: You have already been at war with the $s1 clan: 5 days must pass
	 * before you can challenge this clan again
	 */
	FIVE_DAYS_MUST_PASS_BEFORE_CHALLENGE_AGAIN(247),

	/**
	 * ID: 248<br>
	 * Message: You cannot proclaim war: the $s1 clan does not have enough
	 * members.
	 */
	S1_CLAN_NOT_ENOUGH_MEMBERS_FOR_WAR(248),

	/**
	 * ID: 249<br>
	 * Message: Do you wish to surrender to the $s1 clan?
	 */
	WISH_SURRENDER_TO_S1_CLAN(249),

	/**
	 * ID: 250<br>
	 * Message: You have personally surrendered to the $s1 clan. You are no
	 * longer participating in this clan war.
	 */
	YOU_HAVE_PERSONALLY_SURRENDERED_TO_THE_S1_CLAN(250),

	/**
	 * ID: 251<br>
	 * Message: You cannot proclaim war: you are at war with another clan.
	 */
	ALREADY_AT_WAR_WITH_ANOTHER_CLAN(251),

	/**
	 * ID: 252<br>
	 * Message: Enter the clan name to surrender to.
	 */
	ENTER_CLAN_NAME_TO_SURRENDER_TO(252),

	/**
	 * ID: 253<br>
	 * Message: Enter the name of the clan you wish to end the war with.
	 */
	ENTER_CLAN_NAME_TO_END_WAR(253),

	/**
	 * ID: 254<br>
	 * Message: A clan leader cannot personally surrender.
	 */
	LEADER_CANT_PERSONALLY_SURRENDER(254),

	/**
	 * ID: 255<br>
	 * Message: The $s1 clan has requested to end war. Do you agree?
	 */
	S1_CLAN_REQUESTED_END_WAR(255),

	/**
	 * ID: 256<br>
	 * Message: Enter title
	 */
	ENTER_TITLE(256),

	/**
	 * ID: 257<br>
	 * Message: Do you offer the $s1 clan a proposal to end the war?
	 */
	DO_YOU_OFFER_S1_CLAN_END_WAR(257),

	/**
	 * ID: 258<br>
	 * Message: You are not involved in a clan war.
	 */
	NOT_INVOLVED_CLAN_WAR(258),

	/**
	 * ID: 259<br>
	 * Message: Select clan members from list.
	 */
	SELECT_MEMBERS_FROM_LIST(259),

	/**
	 * ID: 260<br>
	 * Message: Fame level has decreased: 5 days have not passed since you were
	 * refused war
	 */
	FIVE_DAYS_NOT_PASSED_SINCE_YOU_WERE_REFUSED_WAR(260),

	/**
	 * ID: 261<br>
	 * Message: Clan name is invalid.
	 */
	CLAN_NAME_INCORRECT(261),

	/**
	 * ID: 262<br>
	 * Message: Clan name's length is incorrect.
	 */
	CLAN_NAME_TOO_LONG(262),

	/**
	 * ID: 263<br>
	 * Message: You have already requested the dissolution of your clan.
	 */
	DISSOLUTION_IN_PROGRESS(263),

	/**
	 * ID: 264<br>
	 * Message: You cannot dissolve a clan while engaged in a war.
	 */
	CANNOT_DISSOLVE_WHILE_IN_WAR(264),

	/**
	 * ID: 265<br>
	 * Message: You cannot dissolve a clan during a siege or while protecting a
	 * castle.
	 */
	CANNOT_DISSOLVE_WHILE_IN_SIEGE(265),

	/**
	 * ID: 266<br>
	 * Message: You cannot dissolve a clan while owning a clan hall or castle.
	 */
	CANNOT_DISSOLVE_WHILE_OWNING_CLAN_HALL_OR_CASTLE(266),

	/**
	 * ID: 267<br>
	 * Message: There are no requests to disperse.
	 */
	NO_REQUESTS_TO_DISPERSE(267),

	/**
	 * ID: 268<br>
	 * Message: That player already belongs to another clan.
	 */
	PLAYER_ALREADY_ANOTHER_CLAN(268),

	/**
	 * ID: 269<br>
	 * Message: You cannot dismiss yourself.
	 */
	YOU_CANNOT_DISMISS_YOURSELF(269),

	/**
	 * ID: 270<br>
	 * Message: You have already surrendered.
	 */
	YOU_HAVE_ALREADY_SURRENDERED(270),

	/**
	 * ID: 271<br>
	 * Message: A player can only be granted a title if the clan is level 3 or
	 * above
	 */
	CLAN_LVL_3_NEEDED_TO_ENDOWE_TITLE(271),

	/**
	 * ID: 272<br>
	 * Message: A clan crest can only be registered when the clan's skill level
	 * is 3 or above.
	 */
	CLAN_LVL_3_NEEDED_TO_SET_CREST(272),

	/**
	 * ID: 273<br>
	 * Message: A clan war can only be declared when a clan's skill level is 3
	 * or above.
	 */
	CLAN_LVL_3_NEEDED_TO_DECLARE_WAR(273),

	/**
	 * ID: 274<br>
	 * Message: Your clan's skill level has increased.
	 */
	CLAN_LEVEL_INCREASED(274),

	/**
	 * ID: 275<br>
	 * Message: Clan has failed to increase skill level.
	 */
	CLAN_LEVEL_INCREASE_FAILED(275),

	/**
	 * ID: 276<br>
	 * Message: You do not have the necessary materials or prerequisites to
	 * learn this skill.
	 */
	ITEM_MISSING_TO_LEARN_SKILL(276),

	/**
	 * ID: 277<br>
	 * Message: You have earned $s1.
	 */
	LEARNED_SKILL_S1(277),

	/**
	 * ID: 278<br>
	 * Message: You do not have enough SP to learn this skill.
	 */
	NOT_ENOUGH_SP_TO_LEARN_SKILL(278),

	/**
	 * ID: 279<br>
	 * Message: You do not have enough adena.
	 */
	YOU_NOT_ENOUGH_ADENA(279),

	/**
	 * ID: 280<br>
	 * Message: You do not have any items to sell.
	 */
	NO_ITEMS_TO_SELL(280),

	/**
	 * ID: 281<br>
	 * Message: You do not have enough adena to pay the fee.
	 */
	YOU_NOT_ENOUGH_ADENA_PAY_FEE(281),

	/**
	 * ID: 282<br>
	 * Message: You have not deposited any items in your warehouse.
	 */
	NO_ITEM_DEPOSITED_IN_WH(282),

	/**
	 * ID: 283<br>
	 * Message: You have entered a combat zone.
	 */
	ENTERED_COMBAT_ZONE(283),

	/**
	 * ID: 284<br>
	 * Message: You have left a combat zone.
	 */
	LEFT_COMBAT_ZONE(284),

	/**
	 * ID: 285<br>
	 * Message: Clan $s1 has succeeded in engraving the ruler!
	 */
	CLAN_S1_ENGRAVED_RULER(285),

	/**
	 * ID: 286<br>
	 * Message: Your base is being attacked.
	 */
	BASE_UNDER_ATTACK(286),

	/**
	 * ID: 287<br>
	 * Message: The opposing clan has stared to engrave to monument!
	 */
	OPPONENT_STARTED_ENGRAVING(287),

	/**
	 * ID: 288<br>
	 * Message: The castle gate has been broken down.
	 */
	CASTLE_GATE_BROKEN_DOWN(288),

	/**
	 * ID: 289<br>
	 * Message: An outpost or headquarters cannot be built because at least one
	 * already exists.
	 */
	NOT_ANOTHER_HEADQUARTERS(289),

	/**
	 * ID: 290<br>
	 * Message: You cannot set up a base here.
	 */
	NOT_SET_UP_BASE_HERE(290),

	/**
	 * ID: 291<br>
	 * Message: Clan $s1 is victorious over $s2's castle siege!
	 */
	CLAN_S1_VICTORIOUS_OVER_S2_S_SIEGE(291),

	/**
	 * ID: 292<br>
	 * Message: $s1 has announced the castle siege time.
	 */
	S1_ANNOUNCED_SIEGE_TIME(292),

	/**
	 * ID: 293<br>
	 * Message: The registration term for $s1 has ended.
	 */
	REGISTRATION_TERM_FOR_S1_ENDED(293),

	/**
	 * ID: 294<br>
	 * Message: Because your clan is not currently on the offensive in a Clan
	 * Hall siege war, it cannot summon its base camp.
	 */
	BECAUSE_YOUR_CLAN_IS_NOT_CURRENTLY_ON_THE_OFFENSIVE_IN_A_CLAN_HALL_SIEGE_WAR_IT_CANNOT_SUMMON_ITS_BASE_CAMP(
			294),

	/**
	 * ID: 295<br>
	 * Message: $s1's siege was canceled because there were no clans that
	 * participated.
	 */
	S1_SIEGE_WAS_CANCELED_BECAUSE_NO_CLANS_PARTICIPATED(295),

	/**
	 * ID: 296<br>
	 * Message: You received $s1 damage from taking a high fall.
	 */
	FALL_DAMAGE_S1(296),

	/**
	 * ID: 297<br>
	 * Message: You have taken $s1 damage because you were unable to breathe.
	 */
	DROWN_DAMAGE_S1(297),

	/**
	 * ID: 298<br>
	 * Message: You have dropped $s1.
	 */
	YOU_DROPPED_S1(298),

	/**
	 * ID: 299<br>
	 * Message: $c1 has obtained $s3 $s2.
	 */
	C1_OBTAINED_S3_S2(299),

	/**
	 * ID: 300<br>
	 * Message: $c1 has obtained $s2.
	 */
	C1_OBTAINED_S2(300),

	/**
	 * ID: 301<br>
	 * Message: $s2 $s1 has disappeared.
	 */
	S2_S1_DISAPPEARED(301),

	/**
	 * ID: 302<br>
	 * Message: $s1 has disappeared.
	 */
	S1_DISAPPEARED(302),

	/**
	 * ID: 303<br>
	 * Message: Select item to enchant.
	 */
	SELECT_ITEM_TO_ENCHANT(303),

	/**
	 * ID: 304<br>
	 * Message: Clan member $s1 has logged into game.
	 */
	CLAN_MEMBER_S1_LOGGED_IN(131),

	/**
	 * ID: 305<br>
	 * Message: The player declined to join your party.
	 */
	PLAYER_DECLINED(305),

	// 306 - 308 empty

	/**
	 * ID: 309<br>
	 * Message: You have succeeded in expelling the clan member.
	 */
	YOU_HAVE_SUCCEEDED_IN_EXPELLING_CLAN_MEMBER(309),

	// 310 empty

	/**
	 * ID: 311<br>
	 * Message: The clan war declaration has been accepted.
	 */
	CLAN_WAR_DECLARATION_ACCEPTED(311),

	/**
	 * ID: 312<br>
	 * Message: The clan war declaration has been refused.
	 */
	CLAN_WAR_DECLARATION_REFUSED(312),

	/**
	 * ID: 313<br>
	 * Message: The cease war request has been accepted.
	 */
	CEASE_WAR_REQUEST_ACCEPTED(313),

	// 314 - 318 empty

	/**
	 * ID: 319<br>
	 * Message: This door cannot be unlocked.
	 */
	UNABLE_TO_UNLOCK_DOOR(319),

	/**
	 * ID: 320<br>
	 * Message: You have failed to unlock the door.
	 */
	FAILED_TO_UNLOCK_DOOR(320),

	/**
	 * ID: 321<br>
	 * Message: It is not locked.
	 */
	ITS_NOT_LOCKED(321),

	/**
	 * ID: 322<br>
	 * Message: Please decide on the sales price.
	 */
	DECIDE_SALES_PRICE(322),

	/**
	 * ID: 323<br>
	 * Message: Your force has increased to $s1 level.
	 */
	FORCE_INCREASED_TO_S1(323),

	/**
	 * ID: 324<br>
	 * Message: Your force has reached maximum capacity.
	 */
	FORCE_MAXLEVEL_REACHED(324),

	/**
	 * ID: 325<br>
	 * Message: The corpse has already disappeared.
	 */
	CORPSE_ALREADY_DISAPPEARED(325),

	/**
	 * ID: 326<br>
	 * Message: Select target from list.
	 */
	SELECT_TARGET_FROM_LIST(326),

	/**
	 * ID: 327<br>
	 * Message: You cannot exceed 80 characters.
	 */
	CANNOT_EXCEED_80_CHARACTERS(327),

	/**
	 * ID: 328<br>
	 * Message: Please input title using less than 128 characters.
	 */
	PLEASE_INPUT_TITLE_LESS_128_CHARACTERS(328),

	/**
	 * ID: 329<br>
	 * Message: Please input content using less than 3000 characters.
	 */
	PLEASE_INPUT_CONTENT_LESS_3000_CHARACTERS(329),

	/**
	 * ID: 330<br>
	 * Message: A one-line response may not exceed 128 characters.
	 */
	ONE_LINE_RESPONSE_NOT_EXCEED_128_CHARACTERS(330),

	/**
	 * ID: 331<br>
	 * Message: You have acquired $s1 SP.
	 */
	ACQUIRED_S1_SP(331),

	/**
	 * ID: 332<br>
	 * Message: Do you want to be restored?
	 */
	DO_YOU_WANT_TO_BE_RESTORED(332),

	/**
	 * ID: 333<br>
	 * Message: You have received $s1 damage by Core's barrier.
	 */
	S1_DAMAGE_BY_CORE_BARRIER(333),

	/**
	 * ID: 334<br>
	 * Message: Please enter your private store display message.
	 */
	ENTER_PRIVATE_STORE_MESSAGE(334),

	/**
	 * ID: 335<br>
	 * Message: $s1 has been aborted.
	 */
	S1_HAS_BEEN_ABORTED(335),

	/**
	 * ID: 336<br>
	 * Message: You are attempting to crystallize $s1. Do you wish to continue?
	 */
	WISH_TO_CRYSTALLIZE_S1(336),

	/**
	 * ID: 337<br>
	 * Message: The soulshot you are attempting to use does not match the grade
	 * of your equipped weapon.
	 */
	SOULSHOTS_GRADE_MISMATCH(337),

	/**
	 * ID: 338<br>
	 * Message: You do not have enough soulshots for that.
	 */
	NOT_ENOUGH_SOULSHOTS(338),

	/**
	 * ID: 339<br>
	 * Message: Cannot use soulshots.
	 */
	CANNOT_USE_SOULSHOTS(339),

	/**
	 * ID: 340<br>
	 * Message: Your private store is now open for business.
	 */
	PRIVATE_STORE_UNDER_WAY(340),

	/**
	 * ID: 341<br>
	 * Message: You do not have enough materials to perform that action.
	 */
	NOT_ENOUGH_MATERIALS(341),

	/**
	 * ID: 342<br>
	 * Message: Power of the spirits enabled.
	 */
	ENABLED_SOULSHOT(342),

	/**
	 * ID: 343<br>
	 * Message: Sweeper failed, target not spoiled.
	 */
	SWEEPER_FAILED_TARGET_NOT_SPOILED(343),

	/**
	 * ID: 344<br>
	 * Message: Power of the spirits disabled.
	 */
	SOULSHOTS_DISABLED(344),

	/**
	 * ID: 345<br>
	 * Message: Chat enabled.
	 */
	CHAT_ENABLED(345),

	/**
	 * ID: 346<br>
	 * Message: Chat disabled.
	 */
	CHAT_DISABLED(346),

	/**
	 * ID: 347<br>
	 * Message: Incorrect item count.
	 */
	INCORRECT_ITEM_COUNT(347),

	/**
	 * ID: 348<br>
	 * Message: Incorrect item price.
	 */
	INCORRECT_ITEM_PRICE(348),

	/**
	 * ID: 349<br>
	 * Message: Private store already closed.
	 */
	PRIVATE_STORE_ALREADY_CLOSED(349),

	/**
	 * ID: 350<br>
	 * Message: Item out of stock.
	 */
	ITEM_OUT_OF_STOCK(350),

	/**
	 * ID: 351<br>
	 * Message: Incorrect item count.
	 */
	NOT_ENOUGH_ITEMS(351),

	// 352 - 353: empty

	/**
	 * ID: 354<br>
	 * Message: Cancel enchant.
	 */
	CANCEL_ENCHANT(354),

	/**
	 * ID: 355<br>
	 * Message: Inappropriate enchant conditions.
	 */
	INAPPROPRIATE_ENCHANT_CONDITION(355),

	/**
	 * ID: 356<br>
	 * Message: Reject resurrection.
	 */
	REJECT_RESURRECTION(356),

	/**
	 * ID: 357<br>
	 * Message: It has already been spoiled.
	 */
	ALREADY_SPOILED(357),

	/**
	 * ID: 358<br>
	 * Message: $s1 hour(s) until catle siege conclusion.
	 */
	S1_HOURS_UNTIL_SIEGE_CONCLUSION(358),

	/**
	 * ID: 359<br>
	 * Message: $s1 minute(s) until catle siege conclusion.
	 */
	S1_MINUTES_UNTIL_SIEGE_CONCLUSION(359),

	/**
	 * ID: 360<br>
	 * Message: Castle siege $s1 second(s) left!
	 */
	CASTLE_SIEGE_S1_SECONDS_LEFT(360),

	/**
	 * ID: 361<br>
	 * Message: Over-hit!
	 */
	OVER_HIT(361),

	/**
	 * ID: 362<br>
	 * Message: You have acquired $s1 bonus experience from a successful
	 * over-hit.
	 */
	ACQUIRED_BONUS_EXPERIENCE_THROUGH_OVER_HIT(361),

	/**
	 * ID: 363<br>
	 * Message: Chat available time: $s1 minute.
	 */
	CHAT_AVAILABLE_S1_MINUTE(363),

	/**
	 * ID: 364<br>
	 * Message: Enter user's name to search
	 */
	ENTER_USER_NAME_TO_SEARCH(364),

	/**
	 * ID: 365<br>
	 * Message: Are you sure?
	 */
	ARE_YOU_SURE(365),

	/**
	 * ID: 366<br>
	 * Message: Please select your hair color.
	 */
	PLEASE_SELECT_HAIR_COLOR(366),

	/**
	 * ID: 367<br>
	 * Message: You cannot remove that clan character at this time.
	 */
	CANNOT_REMOVE_CLAN_CHARACTER(367),

	/**
	 * ID: 368<br>
	 * Message: Equipped +$s1 $s2.
	 */
	S1_S2_EQUIPPED(368),

	/**
	 * ID: 369<br>
	 * Message: You have obtained a +$s1 $s2.
	 */
	YOU_PICKED_UP_A_S1_S2(369),

	/**
	 * ID: 370<br>
	 * Message: Failed to pickup $s1.
	 */
	FAILED_PICKUP_S1(370),

	/**
	 * ID: 371<br>
	 * Message: Acquired +$s1 $s2.
	 */
	ACQUIRED_S1_S2(371),

	/**
	 * ID: 372<br>
	 * Message: Failed to earn $s1.
	 */
	FAILED_EARN_S1(372),

	/**
	 * ID: 373<br>
	 * Message: You are trying to destroy +$s1 $s2. Do you wish to continue?
	 */
	WISH_DESTROY_S1_S2(373),

	/**
	 * ID: 374<br>
	 * Message: You are attempting to crystallize +$s1 $s2. Do you wish to
	 * continue?
	 */
	WISH_CRYSTALLIZE_S1_S2(374),

	/**
	 * ID: 375<br>
	 * Message: You have dropped +$s1 $s2 .
	 */
	DROPPED_S1_S2(375),

	/**
	 * ID: 376<br>
	 * Message: $c1 has obtained +$s2$s3.
	 */
	C1_OBTAINED_S2_S3(376),

	/**
	 * ID: 377<br>
	 * Message: $S1 $S2 disappeared.
	 */
	S1_S2_DISAPPEARED(377),

	/**
	 * ID: 378<br>
	 * Message: $c1 purchased $s2.
	 */
	C1_PURCHASED_S2(378),

	/**
	 * ID: 379<br>
	 * Message: $c1 purchased +$s2$s3.
	 */
	C1_PURCHASED_S2_S3(379),

	/**
	 * ID: 380<br>
	 * Message: $c1 purchased $s3 $s2(s).
	 */
	C1_PURCHASED_S3_S2_S(380),

	/**
	 * ID: 381<br>
	 * Message: The game client encountered an error and was unable to connect
	 * to the petition server.
	 */
	GAME_CLIENT_UNABLE_TO_CONNECT_TO_PETITION_SERVER(381),

	/**
	 * ID: 382<br>
	 * Message: Currently there are no users that have checked out a GM ID.
	 */
	NO_USERS_CHECKED_OUT_GM_ID(382),

	/**
	 * ID: 383<br>
	 * Message: Request confirmed to end consultation at petition server.
	 */
	REQUEST_CONFIRMED_TO_END_CONSULTATION(383),

	/**
	 * ID: 384<br>
	 * Message: The client is not logged onto the game server.
	 */
	CLIENT_NOT_LOGGED_ONTO_GAME_SERVER(384),

	/**
	 * ID: 385<br>
	 * Message: Request confirmed to begin consultation at petition server.
	 */
	REQUEST_CONFIRMED_TO_BEGIN_CONSULTATION(385),

	/**
	 * ID: 386<br>
	 * Message: The body of your petition must be more than five characters in
	 * length.
	 */
	PETITION_MORE_THAN_FIVE_CHARACTERS(386),

	/**
	 * ID: 387<br>
	 * Message: This ends the GM petition consultation. Please take a moment to
	 * provide feedback about this service.
	 */
	THIS_END_THE_PETITION_PLEASE_PROVIDE_FEEDBACK(387),

	/**
	 * ID: 388<br>
	 * Message: Not under petition consultation.
	 */
	NOT_UNDER_PETITION_CONSULTATION(388),

	/**
	 * ID: 389<br>
	 * Message: our petition application has been accepted. - Receipt No. is
	 * $s1.
	 */
	PETITION_ACCEPTED_RECENT_NO_S1(389),

	/**
	 * ID: 390<br>
	 * Message: You may only submit one petition (active) at a time.
	 */
	ONLY_ONE_ACTIVE_PETITION_AT_TIME(390),

	/**
	 * ID: 391<br>
	 * Message: Receipt No. $s1, petition cancelled.
	 */
	RECENT_NO_S1_CANCELED(391),

	/**
	 * ID: 392<br>
	 * Message: Under petition advice.
	 */
	UNDER_PETITION_ADVICE(392),

	/**
	 * ID: 393<br>
	 * Message: Failed to cancel petition. Please try again later.
	 */
	FAILED_CANCEL_PETITION_TRY_LATER(393),

	/**
	 * ID: 394<br>
	 * Message: Starting petition consultation with $c1.
	 */
	STARTING_PETITION_WITH_C1(394),

	/**
	 * ID: 395<br>
	 * Message: Ending petition consultation with $c1.
	 */
	PETITION_ENDED_WITH_C1(395),

	/**
	 * ID: 396<br>
	 * Message: Please login after changing your temporary password.
	 */
	TRY_AGAIN_AFTER_CHANGING_PASSWORD(396),

	/**
	 * ID: 397<br>
	 * Message: Not a paid account.
	 */
	NO_PAID_ACCOUNT(397),

	/**
	 * ID: 398<br>
	 * Message: There is no time left on this account.
	 */
	NO_TIME_LEFT_ON_ACCOUNT(398),

	// 399: empty

	/**
	 * ID: 400<br>
	 * Message: You are attempting to drop $s1. Dou you wish to continue?
	 */
	WISH_TO_DROP_S1(400),

	/**
	 * ID: 401<br>
	 * Message: You have to many ongoing quests.
	 */
	TOO_MANY_QUESTS(401),

	/**
	 * ID: 402<br>
	 * Message: You do not possess the correct ticket to board the boat.
	 */
	NOT_CORRECT_BOAT_TICKET(402),

	/**
	 * ID: 403<br>
	 * Message: You have exceeded your out-of-pocket adena limit.
	 */
	EXCEECED_POCKET_ADENA_LIMIT(403),

	/**
	 * ID: 404<br>
	 * Message: Your Create Item level is too low to register this recipe.
	 */
	CREATE_LVL_TOO_LOW_TO_REGISTER(404),

	/**
	 * ID: 405<br>
	 * Message: The total price of the product is too high.
	 */
	TOTAL_PRICE_TOO_HIGH(405),

	/**
	 * ID: 406<br>
	 * Message: Petition application accepted.
	 */
	PETITION_APP_ACCEPTED(406),

	/**
	 * ID: 407<br>
	 * Message: Petition under process.
	 */
	PETITION_UNDER_PROCESS(407),

	/**
	 * ID: 408<br>
	 * Message: Set Period
	 */
	SET_PERIOD(408),

	/**
	 * ID: 409<br>
	 * Message: Set Time-$s1:$s2:$s3
	 */
	SET_TIME_S1_S2_S3(409),

	/**
	 * ID: 410<br>
	 * Message: Registration Period
	 */
	REGISTRATION_PERIOD(410),

	/**
	 * ID: 411<br>
	 * Message: Registration Time-$s1:$s2:$s3
	 */
	REGISTRATION_TIME_S1_S2_S3(411),

	/**
	 * ID: 412<br>
	 * Message: Battle begins in $s1:$s2:$s3
	 */
	BATTLE_BEGINS_S1_S2_S3(412),

	/**
	 * ID: 413<br>
	 * Message: Battle ends in $s1:$s2:$s3
	 */
	BATTLE_ENDS_S1_S2_S3(413),

	/**
	 * ID: 414<br>
	 * Message: Standby
	 */
	STANDBY(414),

	/**
	 * ID: 415<br>
	 * Message: Under Siege
	 */
	UNDER_SIEGE(415),

	/**
	 * ID: 416<br>
	 * Message: This item cannot be exchanged.
	 */
	ITEM_CANNOT_EXCHANGE(416),

	/**
	 * ID: 417<br>
	 * Message: $s1 has been disarmed.
	 */
	S1_DISARMED(417),

	/**
	 * ID: 419<br>
	 * Message: $s1 minute(s) of usage time left.
	 */
	S1_MINUTES_USAGE_LEFT(419),

	/**
	 * ID: 420<br>
	 * Message: Time expired.
	 */
	TIME_EXPIRED(420),

	/**
	 * ID: 421<br>
	 * Message: Another person has logged in with the same account.
	 */
	ANOTHER_LOGIN_WITH_ACCOUNT(421),

	/**
	 * ID: 422<br>
	 * Message: You have exceeded the weight limit.
	 */
	WEIGHT_LIMIT_EXCEEDED(422),

	/**
	 * ID: 423<br>
	 * Message: You have cancelled the enchanting process.
	 */
	ENCHANT_SCROLL_CANCELLED(423),

	/**
	 * ID: 424<br>
	 * Message: Does not fit strengthening conditions of the scroll.
	 */
	DOES_NOT_FIT_SCROLL_CONDITIONS(424),

	/**
	 * ID: 425<br>
	 * Message: Your Create Item level is too low to register this recipe.
	 */
	CREATE_LVL_TOO_LOW_TO_REGISTER2(425),

	/**
	 * ID: 445<br>
	 * Message: (Reference Number Regarding Membership Withdrawal Request: $s1)
	 */
	REFERENCE_MEMBERSHIP_WITHDRAWAL_S1(445),

	/**
	 * ID: 447<br>
	 * Message: .
	 */
	DOT(447),

	/**
	 * ID: 448<br>
	 * Message: There is a system error. Please log in again later.
	 */
	SYSTEM_ERROR_LOGIN_LATER(448),

	/**
	 * ID: 449<br>
	 * Message: The password you have entered is incorrect.
	 */
	PASSWORD_ENTERED_INCORRECT1(449),

	/**
	 * ID: 450<br>
	 * Message: Confirm your account information and log in later.
	 */
	CONFIRM_ACCOUNT_LOGIN_LATER(450),

	/**
	 * ID: 451<br>
	 * Message: The password you have entered is incorrect.
	 */
	PASSWORD_ENTERED_INCORRECT2(451),

	/**
	 * ID: 452<br>
	 * Message: Please confirm your account information and try logging in
	 * later.
	 */
	PLEASE_CONFIRM_ACCOUNT_LOGIN_LATER(450),

	/**
	 * ID: 453<br>
	 * Message: Your account information is incorrect.
	 */
	ACCOUNT_INFORMATION_INCORRECT(453),

	/**
	 * ID: 455<br>
	 * Message: Account is already in use. Unable to log in.
	 */
	ACCOUNT_IN_USE(455),

	/**
	 * ID: 456<br>
	 * Message: Lineage II game services may be used by individuals 15 years of
	 * age or older except for PvP servers,which may only be used by adults 18
	 * years of age and older (Korea Only)
	 */
	LINAGE_MINIMUM_AGE(456),

	/**
	 * ID: 457<br>
	 * Message: Currently undergoing game server maintenance. Please log in
	 * again later.
	 */
	SERVER_MAINTENANCE(457),

	/**
	 * ID: 458<br>
	 * Message: Your usage term has expired.
	 */
	USAGE_TERM_EXPIRED(458),

	/**
	 * ID: 460<br>
	 * Message: to reactivate your account.
	 */
	TO_REACTIVATE_YOUR_ACCOUNT(460),

	/**
	 * ID: 461<br>
	 * Message: Access failed.
	 */
	ACCESS_FAILED(461),

	/**
	 * ID: 461<br>
	 * Message: Please try again later.
	 */
	PLEASE_TRY_AGAIN_LATER(184),

	/**
	 * ID: 464<br>
	 * Message: This feature is only available alliance leaders.
	 */
	FEATURE_ONLY_FOR_ALLIANCE_LEADER(464),

	/**
	 * ID: 465<br>
	 * Message: You are not currently allied with any clans.
	 */
	NO_CURRENT_ALLIANCES(465),

	/**
	 * ID: 466<br>
	 * Message: You have exceeded the limit.
	 */
	YOU_HAVE_EXCEEDED_THE_LIMIT(466),

	/**
	 * ID: 467<br>
	 * Message: You may not accept any clan within a day after expelling another
	 * clan.
	 */
	CANT_INVITE_CLAN_WITHIN_1_DAY(467),

	/**
	 * ID: 468<br>
	 * Message: A clan that has withdrawn or been expelled cannot enter into an
	 * alliance within one day of withdrawal or expulsion.
	 */
	CANT_ENTER_ALLIANCE_WITHIN_1_DAY(468),

	/**
	 * ID: 469<br>
	 * Message: You may not ally with a clan you are currently at war with. That
	 * would be diabolical and treacherous.
	 */
	MAY_NOT_ALLY_CLAN_BATTLE(469),

	/**
	 * ID: 470<br>
	 * Message: Only the clan leader may apply for withdrawal from the alliance.
	 */
	ONLY_CLAN_LEADER_WITHDRAW_ALLY(470),

	/**
	 * ID: 471<br>
	 * Message: Alliance leaders cannot withdraw.
	 */
	ALLIANCE_LEADER_CANT_WITHDRAW(471),

	/**
	 * ID: 472<br>
	 * Message: You cannot expel yourself from the clan.
	 */
	CANNOT_EXPEL_YOURSELF(472),

	/**
	 * ID: 473<br>
	 * Message: Different alliance.
	 */
	DIFFERENT_ALLIANCE(473),

	/**
	 * ID: 474<br>
	 * Message: That clan does not exist.
	 */
	CLAN_DOESNT_EXISTS(474),

	/**
	 * ID: 475<br>
	 * Message: Different alliance.
	 */
	DIFFERENT_ALLIANCE2(475),

	/**
	 * ID: 476<br>
	 * Message: Please adjust the image size to 8x12.
	 */
	ADJUST_IMAGE_8_12(476),

	/**
	 * ID: 477<br>
	 * Message: No response. Invitation to join an alliance has been cancelled.
	 */
	NO_RESPONSE_TO_ALLY_INVITATION(477),

	/**
	 * ID: 478<br>
	 * Message: No response. Your entrance to the alliance has been cancelled.
	 */
	YOU_DID_NOT_RESPOND_TO_ALLY_INVITATION(478),

	/**
	 * ID: 479<br>
	 * Message: $s1 has joined as a friend.
	 */
	S1_JOINED_AS_FRIEND(479),

	/**
	 * ID: 480<br>
	 * Message: Please check your friend list.
	 */
	PLEASE_CHECK_YOUR_FRIENDS_LIST(480),

	/**
	 * ID: 481<br>
	 * Message: $s1 has been deleted from your friends list.
	 */
	S1_HAS_BEEN_DELETED_FROM_YOUR_FRIENDS_LIST(481),

	/**
	 * ID: 482<br>
	 * Message: You cannot add yourself to your own friend list.
	 */
	YOU_CANNOT_ADD_YOURSELF_TO_YOUR_OWN_FRIENDS_LIST(482),

	/**
	 * ID: 483<br>
	 * Message: This function is inaccessible right now. Please try again later.
	 */
	FUNCTION_INACCESSIBLE_NOW(483),

	/**
	 * ID: 484<br>
	 * Message: This player is already registered in your friends list.
	 */
	S1_ALREADY_IN_FRIENDS_LIST(484),

	/**
	 * ID: 485<br>
	 * Message: No new friend invitations may be accepted.
	 */
	NO_NEW_INVITATIONS_ACCEPTED(485),

	/**
	 * ID: 486<br>
	 * Message: The following user is not in your friends list.
	 */
	THE_USER_NOT_IN_FRIENDS_LIST(486),

	/**
	 * ID: 487<br>
	 * Message: ======<Friends List>======
	 */
	FRIEND_LIST_HEADER(487),

	/**
	 * ID: 488<br>
	 * Message: $s1 (Currently: Online)
	 */
	S1_ONLINE(488),

	/**
	 * ID: 489<br>
	 * Message: $s1 (Currently: Offline)
	 */
	S1_OFFLINE(489),

	/**
	 * ID: 490<br>
	 * Message: ========================
	 */
	FRIEND_LIST_FOOTER(490),

	/**
	 * ID: 491<br>
	 * Message: =======<Alliance Information>=======
	 */
	ALLIANCE_INFO_HEAD(491),

	/**
	 * ID: 492<br>
	 * Message: Alliance Name: $s1
	 */
	ALLIANCE_NAME_S1(492),

	/**
	 * ID: 493<br>
	 * Message: Connection: $s1 / Total $s2
	 */
	CONNECTION_S1_TOTAL_S2(493),

	/**
	 * ID: 494<br>
	 * Message: Alliance Leader: $s2 of $s1
	 */
	ALLIANCE_LEADER_S2_OF_S1(494),

	/**
	 * ID: 495<br>
	 * Message: Affiliated clans: Total $s1 clan(s)
	 */
	ALLIANCE_CLAN_TOTAL_S1(495),

	/**
	 * ID: 496<br>
	 * Message: =====<Clan Information>=====
	 */
	CLAN_INFO_HEAD(496),

	/**
	 * ID: 497<br>
	 * Message: Clan Name: $s1
	 */
	CLAN_INFO_NAME_S1(497),

	/**
	 * ID: 498<br>
	 * Message: Clan Leader: $s1
	 */
	CLAN_INFO_LEADER_S1(498),

	/**
	 * ID: 499<br>
	 * Message: Clan Level: $s1
	 */
	CLAN_INFO_LEVEL_S1(499),

	/**
	 * ID: 500<br>
	 * Message: ------------------------
	 */
	CLAN_INFO_SEPARATOR(500),

	/**
	 * ID: 501<br>
	 * Message: ========================
	 */
	CLAN_INFO_FOOT(501),

	/**
	 * ID: 502<br>
	 * Message: You already belong to another alliance.
	 */
	ALREADY_JOINED_ALLIANCE(502),

	/**
	 * ID: 503<br>
	 * Message: $s1 (Friend) has logged in.
	 */
	FRIEND_S1_HAS_LOGGED_IN(503),

	/**
	 * ID: 504<br>
	 * Message: Only clan leaders may create alliances.
	 */
	ONLY_CLAN_LEADER_CREATE_ALLIANCE(504),

	/**
	 * ID: 505<br>
	 * Message: You cannot create a new alliance within 10 days after
	 * dissolution.
	 */
	CANT_CREATE_ALLIANCE_10_DAYS_DISOLUTION(505),

	/**
	 * ID: 506<br>
	 * Message: Incorrect alliance name. Please try again.
	 */
	INCORRECT_ALLIANCE_NAME(506),

	/**
	 * ID: 507<br>
	 * Message: Incorrect length for an alliance name.
	 */
	INCORRECT_ALLIANCE_NAME_LENGTH(507),

	/**
	 * ID: 508<br>
	 * Message: This alliance name already exists.
	 */
	ALLIANCE_ALREADY_EXISTS(508),

	/**
	 * ID: 509<br>
	 * Message: Cannot accept. clan ally is registered as an enemy during siege
	 * battle.
	 */
	CANT_ACCEPT_ALLY_ENEMY_FOR_SIEGE(509),

	/**
	 * ID: 510<br>
	 * Message: You have invited someone to your alliance.
	 */
	YOU_INVITED_FOR_ALLIANCE(510),

	/**
	 * ID: 511<br>
	 * Message: You must first select a user to invite.
	 */
	SELECT_USER_TO_INVITE(511),

	/**
	 * ID: 512<br>
	 * Message: Do you really wish to withdraw from the alliance?
	 */
	DO_YOU_WISH_TO_WITHDRW(512),

	/**
	 * ID: 513<br>
	 * Message: Enter the name of the clan you wish to expel.
	 */
	ENTER_NAME_CLAN_TO_EXPEL(513),

	/**
	 * ID: 514<br>
	 * Message: Do you really wish to dissolve the alliance?
	 */
	DO_YOU_WISH_TO_DISOLVE(514),

	/**
	 * ID: 516<br>
	 * Message: $s1 has invited you to be their friend.
	 */
	SI_INVITED_YOU_AS_FRIEND(516),

	/**
	 * ID: 517<br>
	 * Message: You have accepted the alliance.
	 */
	YOU_ACCEPTED_ALLIANCE(517),

	/**
	 * ID: 518<br>
	 * Message: You have failed to invite a clan into the alliance.
	 */
	FAILED_TO_INVITE_CLAN_IN_ALLIANCE(518),

	/**
	 * ID: 519<br>
	 * Message: You have withdrawn from the alliance.
	 */
	YOU_HAVE_WITHDRAWN_FROM_ALLIANCE(519),

	/**
	 * ID: 520<br>
	 * Message: You have failed to withdraw from the alliance.
	 */
	YOU_HAVE_FAILED_TO_WITHDRAWN_FROM_ALLIANCE(520),

	/**
	 * ID: 521<br>
	 * Message: You have succeeded in expelling a clan.
	 */
	YOU_HAVE_EXPELED_A_CLAN(521),

	/**
	 * ID: 522<br>
	 * Message: You have failed to expel a clan.
	 */
	FAILED_TO_EXPELED_A_CLAN(522),

	/**
	 * ID: 523<br>
	 * Message: The alliance has been dissolved.
	 */
	ALLIANCE_DISOLVED(523),

	/**
	 * ID: 524<br>
	 * Message: You have failed to dissolve the alliance.
	 */
	FAILED_TO_DISOLVE_ALLIANCE(524),

	/**
	 * ID: 525<br>
	 * Message: You have succeeded in inviting a friend to your friends list.
	 */
	YOU_HAVE_SUCCEEDED_INVITING_FRIEND(525),

	/**
	 * ID: 526<br>
	 * Message: You have failed to add a friend to your friends list.
	 */
	FAILED_TO_INVITE_A_FRIEND(526),

	/**
	 * ID: 527<br>
	 * Message: $s1 leader, $s2, has requested an alliance.
	 */
	S2_ALLIANCE_LEADER_OF_S1_REQUESTED_ALLIANCE(527),

	/**
	 * ID: 530<br>
	 * Message: The Spiritshot does not match the weapon's grade.
	 */
	SPIRITSHOTS_GRADE_MISMATCH(530),

	/**
	 * ID: 531<br>
	 * Message: You do not have enough Spiritshots for that.
	 */
	NOT_ENOUGH_SPIRITSHOTS(531),

	/**
	 * ID: 532<br>
	 * Message: You may not use Spiritshots.
	 */
	CANNOT_USE_SPIRITSHOTS(532),

	/**
	 * ID: 533<br>
	 * Message: Power of Mana enabled.
	 */
	ENABLED_SPIRITSHOT(533),

	/**
	 * ID: 534<br>
	 * Message: Power of Mana disabled.
	 */
	DISABLED_SPIRITSHOT(534),

	/**
	 * ID: 536<br>
	 * Message: How much adena do you wish to transfer to your Inventory?
	 */
	HOW_MUCH_ADENA_TRANSFER(536),

	/**
	 * ID: 537<br>
	 * Message: How much will you transfer?
	 */
	HOW_MUCH_TRANSFER(537),

	/**
	 * ID: 538<br>
	 * Message: Your SP has decreased by $s1.
	 */
	SP_DECREASED_S1(538),

	/**
	 * ID: 539<br>
	 * Message: Your Experience has decreased by $s1.
	 */
	EXP_DECREASED_BY_S1(539),

	/**
	 * ID: 540<br>
	 * Message: Clan leaders may not be deleted. Dissolve the clan first and try
	 * again.
	 */
	CLAN_LEADERS_MAY_NOT_BE_DELETED(540),

	/**
	 * ID: 541<br>
	 * Message: You may not delete a clan member. Withdraw from the clan first
	 * and try again.
	 */
	CLAN_MEMBER_MAY_NOT_BE_DELETED(541),

	/**
	 * ID: 542<br>
	 * Message: The NPC server is currently down. Pets and servitors cannot be
	 * summoned at this time.
	 */
	THE_NPC_SERVER_IS_CURRENTLY_DOWN(542),

	/**
	 * ID: 543<br>
	 * Message: You already have a pet.
	 */
	YOU_ALREADY_HAVE_A_PET(543),

	/**
	 * ID: 544<br>
	 * Message: Your pet cannot carry this item.
	 */
	ITEM_NOT_FOR_PETS(544),

	/**
	 * ID: 545<br>
	 * Message: Your pet cannot carry any more items. Remove some, then try
	 * again.
	 */
	YOUR_PET_CANNOT_CARRY_ANY_MORE_ITEMS(545),

	/**
	 * ID: 546<br>
	 * Message: Unable to place item, your pet is too encumbered.
	 */
	UNABLE_TO_PLACE_ITEM_YOUR_PET_IS_TOO_ENCUMBERED(546),

	/**
	 * ID: 547<br>
	 * Message: Summoning your pet.
	 */
	SUMMON_A_PET(547),

	/**
	 * ID: 548<br>
	 * Message: Your pet's name can be up to 8 characters in length.
	 */
	NAMING_PETNAME_UP_TO_8CHARS(548),

	/**
	 * ID: 549<br>
	 * Message: To create an alliance, your clan must be Level 5 or higher.
	 */
	TO_CREATE_AN_ALLY_YOU_CLAN_MUST_BE_LEVEL_5_OR_HIGHER(549),

	/**
	 * ID: 550<br>
	 * Message: You may not create an alliance during the term of dissolution
	 * postponement.
	 */
	YOU_MAY_NOT_CREATE_ALLY_WHILE_DISSOLVING(550),

	/**
	 * ID: 551<br>
	 * Message: You cannot raise your clan level during the term of dispersion
	 * postponement.
	 */
	CANNOT_RISE_LEVEL_WHILE_DISSOLUTION_IN_PROGRESS(263),

	/**
	 * ID: 552<br>
	 * Message: During the grace period for dissolving a clan, the registration
	 * or deletion of a clan's crest is not allowed.
	 */
	CANNOT_SET_CREST_WHILE_DISSOLUTION_IN_PROGRESS(263),

	/**
	 * ID: 553<br>
	 * Message: The opposing clan has applied for dispersion.
	 */
	OPPOSING_CLAN_APPLIED_DISPERSION(553),

	/**
	 * ID: 554<br>
	 * Message: You cannot disperse the clans in your alliance.
	 */
	CANNOT_DISPERSE_THE_CLANS_IN_ALLY(554),

	/**
	 * ID: 555<br>
	 * Message: You cannot move - you are too encumbered
	 */
	CANT_MOVE_TOO_ENCUMBERED(555),

	/**
	 * ID: 556<br>
	 * Message: You cannot move in this state
	 */
	CANT_MOVE_IN_THIS_STATE(556),

	/**
	 * ID: 557<br>
	 * Message: Your pet has been summoned and may not be destroyed
	 */
	PET_SUMMONED_MAY_NOT_DESTROYED(557),

	/**
	 * ID: 558<br>
	 * Message: Your pet has been summoned and may not be let go.
	 */
	PET_SUMMONED_MAY_NOT_LET_GO(558),

	/**
	 * ID: 559<br>
	 * Message: You have purchased $s2 from $c1.
	 */
	PURCHASED_S2_FROM_C1(559),

	/**
	 * ID: 560<br>
	 * Message: You have purchased +$s2 $s3 from $c1.
	 */
	PURCHASED_S2_S3_FROM_C1(560),

	/**
	 * ID: 561<br>
	 * Message: You have purchased $s3 $s2(s) from $c1.
	 */
	PURCHASED_S3_S2_S_FROM_C1(561),

	/**
	 * ID: 562<br>
	 * Message: You may not crystallize this item. Your crystallization skill
	 * level is too low.
	 */
	CRYSTALLIZE_LEVEL_TOO_LOW(562),

	/**
	 * ID: 563<br>
	 * Message: Failed to disable attack target.
	 */
	FAILED_DISABLE_TARGET(563),

	/**
	 * ID: 564<br>
	 * Message: Failed to change attack target.
	 */
	FAILED_CHANGE_TARGET(564),

	/**
	 * ID: 565<br>
	 * Message: Not enough luck.
	 */
	NOT_ENOUGH_LUCK(565),

	/**
	 * ID: 566<br>
	 * Message: Your confusion spell failed.
	 */
	CONFUSION_FAILED(566),

	/**
	 * ID: 567<br>
	 * Message: Your fear spell failed.
	 */
	FEAR_FAILED(567),

	/**
	 * ID: 568<br>
	 * Message: Cubic Summoning failed.
	 */
	CUBIC_SUMMONING_FAILED(568),

	/**
	 * ID: 572<br>
	 * Message: Do you accept $c1's party invitation? (Item Distribution:
	 * Finders Keepers.)
	 */
	C1_INVITED_YOU_TO_PARTY_FINDERS_KEEPERS(572),

	/**
	 * ID: 573<br>
	 * Message: Do you accept $c1's party invitation? (Item Distribution:
	 * Random.)
	 */
	C1_INVITED_YOU_TO_PARTY_RANDOM(573),

	/**
	 * ID: 574<br>
	 * Message: Pets and Servitors are not available at this time.
	 */
	PETS_ARE_NOT_AVAILABLE_AT_THIS_TIME(574),

	/**
	 * ID: 575<br>
	 * Message: How much adena do you wish to transfer to your pet?
	 */
	HOW_MUCH_ADENA_TRANSFER_TO_PET(575),

	/**
	 * ID: 576<br>
	 * Message: How much do you wish to transfer?
	 */
	HOW_MUCH_TRANSFER2(576),

	/**
	 * ID: 577<br>
	 * Message: You cannot summon during a trade or while using the private
	 * shops.
	 */
	CANNOT_SUMMON_DURING_TRADE_SHOP(577),

	/**
	 * ID: 578<br>
	 * Message: You cannot summon during combat.
	 */
	YOU_CANNOT_SUMMON_IN_COMBAT(578),

	/**
	 * ID: 579<br>
	 * Message: A pet cannot be sent back during battle.
	 */
	PET_CANNOT_SENT_BACK_DURING_BATTLE(579),

	/**
	 * ID: 580<br>
	 * Message: You may not use multiple pets or servitors at the same time.
	 */
	SUMMON_ONLY_ONE(580),

	/**
	 * ID: 581<br>
	 * Message: There is a space in the name.
	 */
	NAMING_THERE_IS_A_SPACE(581),

	/**
	 * ID: 582<br>
	 * Message: Inappropriate character name.
	 */
	NAMING_INAPPROPRIATE_CHARACTER_NAME(582),

	/**
	 * ID: 583<br>
	 * Message: Name includes forbidden words.
	 */
	NAMING_INCLUDES_FORBIDDEN_WORDS(583),

	/**
	 * ID: 584<br>
	 * Message: This is already in use by another pet.
	 */
	NAMING_ALREADY_IN_USE_BY_ANOTHER_PET(584),

	/**
	 * ID: 585<br>
	 * Message: Please decide on the price.
	 */
	DECIDE_ON_PRICE(585),

	/**
	 * ID: 586<br>
	 * Message: Pet items cannot be registered as shortcuts.
	 */
	PET_NO_SHORTCUT(586),

	/**
	 * ID: 588<br>
	 * Message: Your pet's inventory is full.
	 */
	PET_INVENTORY_FULL(588),

	/**
	 * ID: 589<br>
	 * Message: A dead pet cannot be sent back.
	 */
	DEAD_PET_CANNOT_BE_RETURNED(589),

	/**
	 * ID: 590<br>
	 * Message: Your pet is motionless and any attempt you make to give it
	 * something goes unrecognized.
	 */
	CANNOT_GIVE_ITEMS_TO_DEAD_PET(590),

	/**
	 * ID: 591<br>
	 * Message: An invalid character is included in the pet's name.
	 */
	NAMING_PETNAME_CONTAINS_INVALID_CHARS(591),

	/**
	 * ID: 592<br>
	 * Message: Do you wish to dismiss your pet? Dismissing your pet will cause
	 * the pet necklace to disappear
	 */
	WISH_TO_DISMISS_PET(592),

	/**
	 * ID: 593<br>
	 * Message: Starving, grumpy and fed up, your pet has left.
	 */
	STARVING_GRUMPY_AND_FED_UP_YOUR_PET_HAS_LEFT(593),

	/**
	 * ID: 594<br>
	 * Message: You may not restore a hungry pet.
	 */
	YOU_CANNOT_RESTORE_HUNGRY_PETS(594),

	/**
	 * ID: 595<br>
	 * Message: Your pet is very hungry.
	 */
	YOUR_PET_IS_VERY_HUNGRY(595),

	/**
	 * ID: 596<br>
	 * Message: Your pet ate a little, but is still hungry.
	 */
	YOUR_PET_ATE_A_LITTLE_BUT_IS_STILL_HUNGRY(596),

	/**
	 * ID: 597<br>
	 * Message: Your pet is very hungry. Please be careful.
	 */
	YOUR_PET_IS_VERY_HUNGRY_PLEASE_BE_CAREFULL(597),

	/**
	 * ID: 598<br>
	 * Message: You may not chat while you are invisible.
	 */
	NOT_CHAT_WHILE_INVISIBLE(598),

	/**
	 * ID: 599<br>
	 * Message: The GM has an important notice. Chat has been temporarily
	 * disabled.
	 */
	GM_NOTICE_CHAT_DISABLED(346),

	/**
	 * ID: 600<br>
	 * Message: You may not equip a pet item.
	 */
	CANNOT_EQUIP_PET_ITEM(600),

	/**
	 * ID: 601<br>
	 * Message: There are $S1 petitions currently on the waiting list.
	 */
	S1_PETITION_ON_WAITING_LIST(601),

	/**
	 * ID: 602<br>
	 * Message: The petition system is currently unavailable. Please try again
	 * later.
	 */
	PETITION_SYSTEM_CURRENT_UNAVAILABLE(602),

	/**
	 * ID: 603<br>
	 * Message: That item cannot be discarded or exchanged.
	 */
	CANNOT_DISCARD_EXCHANGE_ITEM(603),

	/**
	 * ID: 604<br>
	 * Message: You may not call forth a pet or summoned creature from this
	 * location
	 */
	NOT_CALL_PET_FROM_THIS_LOCATION(604),

	/**
	 * ID: 605<br>
	 * Message: You may register up to 64 people on your list.
	 */
	MAY_REGISTER_UP_TO_64_PEOPLE(605),

	/**
	 * ID: 606<br>
	 * Message: You cannot be registered because the other person has already
	 * registered 64 people on his/her list.
	 */
	OTHER_PERSON_ALREADY_64_PEOPLE(606),

	/**
	 * ID: 607<br>
	 * Message: You do not have any further skills to learn. Come back when you
	 * have reached Level $s1.
	 */
	DO_NOT_HAVE_FURTHER_SKILLS_TO_LEARN_S1(607),

	/**
	 * ID: 608<br>
	 * Message: $c1 has obtained $s3 $s2 by using Sweeper.
	 */
	C1_SWEEPED_UP_S3_S2(608),

	/**
	 * ID: 609<br>
	 * Message: $c1 has obtained $s2 by using Sweeper.
	 */
	C1_SWEEPED_UP_S2(609),

	/**
	 * ID: 610<br>
	 * Message: Your skill has been canceled due to lack of HP.
	 */
	SKILL_REMOVED_DUE_LACK_HP(610),

	/**
	 * ID: 611<br>
	 * Message: You have succeeded in Confusing the enemy.
	 */
	CONFUSING_SUCCEEDED(611),

	/**
	 * ID: 612<br>
	 * Message: The Spoil condition has been activated.
	 */
	SPOIL_SUCCESS(612),

	/**
	 * ID: 613<br>
	 * Message: ======<Ignore List>======
	 */
	BLOCK_LIST_HEADER(613),

	/**
	 * ID: 614<br>
	 * Message: $c1 : $c2
	 */
	C1_D_C2(614),

	/**
	 * ID: 615<br>
	 * Message: You have failed to register the user to your Ignore List.
	 */
	FAILED_TO_REGISTER_TO_IGNORE_LIST(615),

	/**
	 * ID: 616<br>
	 * Message: You have failed to delete the character.
	 */
	FAILED_TO_DELETE_CHARACTER(616),

	/**
	 * ID: 617<br>
	 * Message: $s1 has been added to your Ignore List.
	 */
	S1_WAS_ADDED_TO_YOUR_IGNORE_LIST(617),

	/**
	 * ID: 618<br>
	 * Message: $s1 has been removed from your Ignore List.
	 */
	S1_WAS_REMOVED_FROM_YOUR_IGNORE_LIST(618),

	/**
	 * ID: 619<br>
	 * Message: $s1 has placed you on his/her Ignore List.
	 */
	S1_HAS_ADDED_YOU_TO_IGNORE_LIST(619),

	/**
	 * ID: 620<br>
	 * Message: $s1 has placed you on his/her Ignore List.
	 */
	S1_HAS_ADDED_YOU_TO_IGNORE_LIST2(620),

	/**
	 * ID: 621<br>
	 * Message: Game connection attempted through a restricted IP.
	 */
	CONNECTION_RESTRICTED_IP(621),

	/**
	 * ID: 622<br>
	 * Message: You may not make a declaration of war during an alliance battle.
	 */
	NO_WAR_DURING_ALLY_BATTLE(622),

	/**
	 * ID: 623<br>
	 * Message: Your opponent has exceeded the number of simultaneous alliance
	 * battles alllowed.
	 */
	OPPONENT_TOO_MUCH_ALLY_BATTLES1(623),

	/**
	 * ID: 624<br>
	 * Message: $s1 Clan leader is not currently connected to the game server.
	 */
	S1_LEADER_NOT_CONNECTED(624),

	/**
	 * ID: 625<br>
	 * Message: Your request for Alliance Battle truce has been denied.
	 */
	ALLY_BATTLE_TRUCE_DENIED(625),

	/**
	 * ID: 626<br>
	 * Message: The $s1 clan did not respond: war proclamation has been refused.
	 */
	WAR_PROCLAMATION_HAS_BEEN_REFUSED(626),

	/**
	 * ID: 627<br>
	 * Message: Clan battle has been refused because you did not respond to $s1
	 * clan's war proclamation.
	 */
	YOU_REFUSED_CLAN_WAR_PROCLAMATION(627),

	/**
	 * ID: 628<br>
	 * Message: You have already been at war with the $s1 clan: 5 days must pass
	 * before you can declare war again.
	 */
	ALREADY_AT_WAR_WITH_S1_WAIT_5_DAYS(628),

	/**
	 * ID: 629<br>
	 * Message: Your opponent has exceeded the number of simultaneous alliance
	 * battles alllowed.
	 */
	OPPONENT_TOO_MUCH_ALLY_BATTLES2(629),

	/**
	 * ID: 630<br>
	 * Message: War with the clan has begun.
	 */
	WAR_WITH_CLAN_BEGUN(630),

	/**
	 * ID: 631<br>
	 * Message: War with the clan is over.
	 */
	WAR_WITH_CLAN_ENDED(631),

	/**
	 * ID: 632<br>
	 * Message: You have won the war over the clan!
	 */
	WON_WAR_OVER_CLAN(632),

	/**
	 * ID: 633<br>
	 * Message: You have surrendered to the clan.
	 */
	SURRENDERED_TO_CLAN(633),

	/**
	 * ID: 634<br>
	 * Message: Your alliance leader has been slain. You have been defeated by
	 * the clan.
	 */
	DEFEATED_BY_CLAN(634),

	/**
	 * ID: 635<br>
	 * Message: The time limit for the clan war has been exceeded. War with the
	 * clan is over.
	 */
	TIME_UP_WAR_OVER(635),

	/**
	 * ID: 636<br>
	 * Message: You are not involved in a clan war.
	 */
	NOT_INVOLVED_IN_WAR(636),

	/**
	 * ID: 637<br>
	 * Message: A clan ally has registered itself to the opponent.
	 */
	ALLY_REGISTERED_SELF_TO_OPPONENT(637),

	/**
	 * ID: 638<br>
	 * Message: You have already requested a Siege Battle.
	 */
	ALREADY_REQUESTED_SIEGE_BATTLE(638),

	/**
	 * ID: 639<br>
	 * Message: Your application has been denied because you have already
	 * submitted a request for another Siege Battle.
	 */
	APPLICATION_DENIED_BECAUSE_ALREADY_SUBMITTED_A_REQUEST_FOR_ANOTHER_SIEGE_BATTLE(
			639),

	// 640 - 641: empty

	/**
	 * ID: 642<br>
	 * Message: You are already registered to the attacker side and must not
	 * cancel your registration before submitting your request
	 */
	ALREADY_ATTACKER_NOT_CANCEL(642),

	/**
	 * ID: 643<br>
	 * Message: You are already registered to the defender side and must not
	 * cancel your registration before submitting your request
	 */
	ALREADY_DEFENDER_NOT_CANCEL(643),

	/**
	 * ID: 644<br>
	 * Message: You are not yet registered for the castle siege.
	 */
	NOT_REGISTERED_FOR_SIEGE(644),

	/**
	 * ID: 645<br>
	 * Message: Only clans of level 5 or higher may register for a castle siege.
	 */
	ONLY_CLAN_LEVEL_5_ABOVE_MAY_SIEGE(645),

	// 646 - 647: empty

	/**
	 * ID: 648<br>
	 * Message: No more registrations may be accepted for the attacker side.
	 */
	ATTACKER_SIDE_FULL(648),

	/**
	 * ID: 649<br>
	 * Message: No more registrations may be accepted for the defender side.
	 */
	DEFENDER_SIDE_FULL(649),

	/**
	 * ID: 650<br>
	 * Message: You may not summon from your current location.
	 */
	YOU_MAY_NOT_SUMMON_FROM_YOUR_CURRENT_LOCATION(650),

	/**
	 * ID: 651<br>
	 * Message: Place in the current location and direction. Do you wish to
	 * continue?
	 */
	PLACE_CURRENT_LOCATION_DIRECTION(651),

	/**
	 * ID: 652<br>
	 * Message: The target of the summoned monster is wrong.
	 */
	TARGET_OF_SUMMON_WRONG(652),

	/**
	 * ID: 653<br>
	 * Message: You do not have the authority to position mercenaries.
	 */
	YOU_DO_NOT_HAVE_AUTHORITY_TO_POSITION_MERCENARIES(653),

	/**
	 * ID: 654<br>
	 * Message: You do not have the authority to cancel mercenary positioning.
	 */
	YOU_DO_NOT_HAVE_AUTHORITY_TO_CANCEL_MERCENARY_POSITIONING(654),

	/**
	 * ID: 655<br>
	 * Message: Mercenaries cannot be positioned here.
	 */
	MERCENARIES_CANNOT_BE_POSITIONED_HERE(655),

	/**
	 * ID: 656<br>
	 * Message: This mercenary cannot be positioned anymore.
	 */
	THIS_MERCENARY_CANNOT_BE_POSITIONED_ANYMORE(656),

	/**
	 * ID: 657<br>
	 * Message: Positioning cannot be done here because the distance between
	 * mercenaries is too short.
	 */
	POSITIONING_CANNOT_BE_DONE_BECAUSE_DISTANCE_BETWEEN_MERCENARIES_TOO_SHORT(
			657),

	/**
	 * ID: 658<br>
	 * Message: This is not a mercenary of a castle that you own and so you
	 * cannot cancel its positioning.
	 */
	THIS_IS_NOT_A_MERCENARY_OF_A_CASTLE_THAT_YOU_OWN_AND_SO_CANNOT_CANCEL_POSITIONING(
			658),

	/**
	 * ID: 659<br>
	 * Message: This is not the time for siege registration and so registrations
	 * cannot be accepted or rejected.
	 */
	NOT_SIEGE_REGISTRATION_TIME1(659),

	/**
	 * ID: 659<br>
	 * Message: This is not the time for siege registration and so registration
	 * and cancellation cannot be done.
	 */
	NOT_SIEGE_REGISTRATION_TIME2(660),

	/**
	 * ID: 661<br>
	 * Message: This character cannot be spoiled.
	 */
	SPOIL_CANNOT_USE(661),

	/**
	 * ID: 662<br>
	 * Message: The other player is rejecting friend invitations.
	 */
	THE_PLAYER_IS_REJECTING_FRIEND_INVITATIONS(662),

	// 663 will crash client

	/**
	 * ID: 664<br>
	 * Message: Please choose a person to receive.
	 */
	CHOOSE_PERSON_TO_RECEIVE(664),

	/**
	 * ID: 665<br>
	 * Message: of alliance is applying for alliance war. Do you want to accept
	 * the challenge?
	 */
	APPLYING_ALLIANCE_WAR(665),

	/**
	 * ID: 666<br>
	 * Message: A request for ceasefire has been received from alliance. Do you
	 * agree?
	 */
	REQUEST_FOR_CEASEFIRE(666),

	/**
	 * ID: 667<br>
	 * Message: You are registering on the attacking side of the siege. Do you
	 * want to continue?
	 */
	REGISTERING_ON_ATTACKING_SIDE(667),

	/**
	 * ID: 668<br>
	 * Message: You are registering on the defending side of the siege. Do you
	 * want to continue?
	 */
	REGISTERING_ON_DEFENDING_SIDE(668),

	/**
	 * ID: 669<br>
	 * Message: You are canceling your application to participate in the siege
	 * battle. Do you want to continue?
	 */
	CANCELING_REGISTRATION(669),

	/**
	 * ID: 670<br>
	 * Message: You are refusing the registration of clan on the defending side.
	 * Do you want to continue?
	 */
	REFUSING_REGISTRATION(670),

	/**
	 * ID: 671<br>
	 * Message: You are agreeing to the registration of clan on the defending
	 * side. Do you want to continue?
	 */
	AGREEING_REGISTRATION(671),

	/**
	 * ID: 672<br>
	 * Message: $s1 adena disappeared.
	 */
	S1_DISAPPEARED_ADENA(672),

	/**
	 * ID: 673<br>
	 * Message: Only a clan leader whose clan is of level 2 or higher is allowed
	 * to participate in a clan hall auction.
	 */
	AUCTION_ONLY_CLAN_LEVEL_2_HIGHER(673),

	/**
	 * ID: 674<br>
	 * Message: I has not yet been seven days since canceling an auction.
	 */
	NOT_SEVEN_DAYS_SINCE_CANCELING_AUCTION(674),

	/**
	 * ID: 675<br>
	 * Message: There are no clan halls up for auction.
	 */
	NO_CLAN_HALLS_UP_FOR_AUCTION(675),

	/**
	 * ID: 676<br>
	 * Message: Since you have already submitted a bid, you are not allowed to
	 * participate in another auction at this time.
	 */
	ALREADY_SUBMITTED_BID(676),

	/**
	 * ID: 677<br>
	 * Message: Your bid price must be higher than the minimum price that can be
	 * bid.
	 */
	BID_PRICE_MUST_BE_HIGHER(677),

	/**
	 * ID: 678<br>
	 * Message: You have submitted a bid in the auction of .
	 */
	SUBMITTED_A_BID(678),

	/**
	 * ID: 679<br>
	 * Message: You have canceled your bid.
	 */
	CANCELED_BID(679),

	/**
	 * ID: 680<br>
	 * You cannot participate in an auction.
	 */
	CANNOT_PARTICIPATE_IN_AN_AUCTION(680),

	/**
	 * ID: 681<br>
	 * Message: The clan does not own a clan hall.
	 */
	// CLAN_HAS_NO_CLAN_HALL(681) // Doesn't exist in Hellbound anymore

	/**
	 * ID: 683<br>
	 * Message: There are no priority rights on a sweeper.
	 */
	SWEEP_NOT_ALLOWED(683),

	/**
	 * ID: 684<br>
	 * Message: You cannot position mercenaries during a siege.
	 */
	CANNOT_POSITION_MERCS_DURING_SIEGE(684),

	/**
	 * ID: 685<br>
	 * Message: You cannot apply for clan war with a clan that belongs to the
	 * same alliance
	 */
	CANNOT_DECLARE_WAR_ON_ALLY(685),

	/**
	 * ID: 686<br>
	 * Message: You have received $s1 damage from the fire of magic.
	 */
	S1_DAMAGE_FROM_FIRE_MAGIC(686),

	/**
	 * ID: 687<br>
	 * Message: You cannot move while frozen. Please wait.
	 */
	CANNOT_MOVE_FROZEN(687),

	/**
	 * ID: 688<br>
	 * Message: The clan that owns the castle is automatically registered on the
	 * defending side.
	 */
	CLAN_THAT_OWNS_CASTLE_IS_AUTOMATICALLY_REGISTERED_DEFENDING(688),

	/**
	 * ID: 689<br>
	 * Message: A clan that owns a castle cannot participate in another siege.
	 */
	CLAN_THAT_OWNS_CASTLE_CANNOT_PARTICIPATE_OTHER_SIEGE(689),

	/**
	 * ID: 690<br>
	 * Message: You cannot register on the attacking side because you are part
	 * of an alliance with the clan that owns the castle.
	 */
	CANNOT_ATTACK_ALLIANCE_CASTLE(690),

	/**
	 * ID: 691<br>
	 * Message: $s1 clan is already a member of $s2 alliance.
	 */
	S1_CLAN_ALREADY_MEMBER_OF_S2_ALLIANCE(691),

	/**
	 * ID: 692<br>
	 * Message: The other party is frozen. Please wait a moment.
	 */
	OTHER_PARTY_IS_FROZEN(692),

	/**
	 * ID: 693<br>
	 * Message: The package that arrived is in another warehouse.
	 */
	PACKAGE_IN_ANOTHER_WAREHOUSE(693),

	/**
	 * ID: 694<br>
	 * Message: No packages have arrived.
	 */
	NO_PACKAGES_ARRIVED(694),

	/**
	 * ID: 695<br>
	 * Message: You cannot set the name of the pet.
	 */
	NAMING_YOU_CANNOT_SET_NAME_OF_THE_PET(695),

	/**
	 * ID: 697<br>
	 * Message: The item enchant value is strange
	 */
	ITEM_ENCHANT_VALUE_STRANGE(697),

	/**
	 * ID: 698<br>
	 * Message: The price is different than the same item on the sales list.
	 */
	PRICE_DIFFERENT_FROM_SALES_LIST(698),

	/**
	 * ID: 699<br>
	 * Message: Currently not purchasing.
	 */
	CURRENTLY_NOT_PURCHASING(699),

	/**
	 * ID: 700<br>
	 * Message: The purchase is complete.
	 */
	THE_PURCHASE_IS_COMPLETE(700),

	/**
	 * ID: 701<br>
	 * Message: You do not have enough required items.
	 */
	NOT_ENOUGH_REQUIRED_ITEMS(701),

	/**
	 * ID: 702 <br>
	 * Message: There are no GMs currently visible in the public list as they
	 * may be performing other functions at the moment.
	 */
	NO_GM_PROVIDING_SERVICE_NOW(702),

	/**
	 * ID: 703<br>
	 * Message: ======<GM List>======
	 */
	GM_LIST(703),

	/**
	 * ID: 704<br>
	 * Message: GM : $c1
	 */
	GM_C1(704),

	/**
	 * ID: 705<br>
	 * Message: You cannot exclude yourself.
	 */
	CANNOT_EXCLUDE_SELF(705),

	/**
	 * ID: 706<br>
	 * Message: You can only register up to 64 names on your exclude list.
	 */
	ONLY_64_NAMES_ON_EXCLUDE_LIST(706),

	/**
	 * ID: 707<br>
	 * Message: You cannot teleport to a village that is in a siege.
	 */
	NO_PORT_THAT_IS_IN_SIGE(707),

	/**
	 * ID: 708<br>
	 * Message: You do not have the right to use the castle warehouse.
	 */
	YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_CASTLE_WAREHOUSE(708),

	/**
	 * ID: 709<br>
	 * Message: You do not have the right to use the clan warehouse.
	 */
	YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_CLAN_WAREHOUSE(709),

	/**
	 * ID: 710<br>
	 * Message: Only clans of clan level 1 or higher can use a clan warehouse.
	 */
	ONLY_LEVEL_1_CLAN_OR_HIGHER_CAN_USE_WAREHOUSE(710),

	/**
	 * ID: 711<br>
	 * Message: The siege of $s1 has started.
	 */
	SIEGE_OF_S1_HAS_STARTED(711),

	/**
	 * ID: 712<br>
	 * Message: The siege of $s1 has finished.
	 */
	SIEGE_OF_S1_HAS_ENDED(712),

	/**
	 * ID: 713<br>
	 * Message: $s1/$s2/$s3 :
	 */
	S1_S2_S3_D(713),

	/**
	 * ID: 714<br>
	 * Message: A trap device has been tripped.
	 */
	A_TRAP_DEVICE_HAS_BEEN_TRIPPED(714),

	/**
	 * ID: 715<br>
	 * Message: A trap device has been stopped.
	 */
	A_TRAP_DEVICE_HAS_BEEN_STOPPED(715),

	/**
	 * ID: 716<br>
	 * Message: If a base camp does not exist, resurrection is not possible.
	 */
	NO_RESURRECTION_WITHOUT_BASE_CAMP(716),

	/**
	 * ID: 717<br>
	 * Message: The guardian tower has been destroyed and resurrection is not
	 * possible
	 */
	TOWER_DESTROYED_NO_RESURRECTION(717),

	/**
	 * ID: 718<br>
	 * Message: The castle gates cannot be opened and closed during a siege.
	 */
	GATES_NOT_OPENED_CLOSED_DURING_SIEGE(718),

	/**
	 * ID: 719<br>
	 * Message: You failed at mixing the item.
	 */
	ITEM_MIXING_FAILED(719),

	/**
	 * ID: 720<br>
	 * Message: The purchase price is higher than the amount of money that you
	 * have and so you cannot open a personal store.
	 */
	THE_PURCHASE_PRICE_IS_HIGHER_THAN_MONEY(720),

	/**
	 * ID: 721<br>
	 * Message: You cannot create an alliance while participating in a siege.
	 */
	NO_ALLY_CREATION_WHILE_SIEGE(721),

	/**
	 * ID: 722<br>
	 * Message: You cannot dissolve an alliance while an affiliated clan is
	 * participating in a siege battle.
	 */
	CANNOT_DISSOLVE_ALLY_WHILE_IN_SIEGE(722),

	/**
	 * ID: 723<br>
	 * Message: The opposing clan is participating in a siege battle.
	 */
	OPPOSING_CLAN_IS_PARTICIPATING_IN_SIEGE(723),

	/**
	 * ID: 724<br>
	 * Message: You cannot leave while participating in a siege battle.
	 */
	CANNOT_LEAVE_WHILE_SIEGE(724),

	/**
	 * ID: 725<br>
	 * Message: You cannot banish a clan from an alliance while the clan is
	 * participating in a siege
	 */
	CANNOT_DISMISS_WHILE_SIEGE(725),

	/**
	 * ID: 726<br>
	 * Message: Frozen condition has started. Please wait a moment.
	 */
	FROZEN_CONDITION_STARTED(726),

	/**
	 * ID: 727<br>
	 * Message: The frozen condition was removed.
	 */
	FROZEN_CONDITION_REMOVED(727),

	/**
	 * ID: 728<br>
	 * Message: You cannot apply for dissolution again within seven days after a
	 * previous application for dissolution.
	 */
	CANNOT_APPLY_DISSOLUTION_AGAIN(728),

	/**
	 * ID: 729<br>
	 * Message: That item cannot be discarded.
	 */
	ITEM_NOT_DISCARDED(729),

	/**
	 * ID: 730<br>
	 * Message: - You have submitted your $s1th petition. - You may submit $s2
	 * more petition(s) today.
	 */
	SUBMITTED_YOU_S1_TH_PETITION_S2_LEFT(730),

	/**
	 * ID: 731<br>
	 * Message: A petition has been received by the GM on behalf of $s1. The
	 * petition code is $s2.
	 */
	PETITION_S1_RECEIVED_CODE_IS_S2(731),

	/**
	 * ID: 732<br>
	 * Message: $c1 has received a request for a consultation with the GM.
	 */
	C1_RECEIVED_CONSULTATION_REQUEST(732),

	/**
	 * ID: 733<br>
	 * Message: We have received $s1 petitions from you today and that is the
	 * maximum that you can submit in one day. You cannot submit any more
	 * petitions.
	 */
	WE_HAVE_RECEIVED_S1_PETITIONS_TODAY(733),

	/**
	 * ID: 734<br>
	 * Message: You have failed at submitting a petition on behalf of someone
	 * else. $c1 already submitted a petition.
	 */
	PETITION_FAILED_C1_ALREADY_SUBMITTED(734),

	/**
	 * ID: 735<br>
	 * Message: You have failed at submitting a petition on behalf of $c1. The
	 * error number is $s2.
	 */
	PETITION_FAILED_FOR_C1_ERROR_NUMBER_S2(735),

	/**
	 * ID: 736<br>
	 * Message: The petition was canceled. You may submit $s1 more petition(s)
	 * today.
	 */
	PETITION_CANCELED_SUBMIT_S1_MORE_TODAY(736),

	/**
	 * ID: 737<br>
	 * Message: You have cancelled submitting a petition on behalf of $s1.
	 */
	CANCELED_PETITION_ON_S1(737),

	/**
	 * ID: 738<br>
	 * Message: You have not submitted a petition.
	 */
	PETITION_NOT_SUBMITTED(738),

	/**
	 * ID: 739<br>
	 * Message: You have failed at cancelling a petition on behalf of $c1. The
	 * error number is $s2.
	 */
	PETITION_CANCEL_FAILED_FOR_C1_ERROR_NUMBER_S2(739),

	/**
	 * ID: 740<br>
	 * Message: $c1 participated in a petition chat at the request of the GM.
	 */
	C1_PARTICIPATE_PETITION(740),

	/**
	 * ID: 741<br>
	 * Message: You have failed at adding $c1 to the petition chat. Petition has
	 * already been submitted.
	 */
	FAILED_ADDING_C1_TO_PETITION(741),

	/**
	 * ID: 742<br>
	 * Message: You have failed at adding $c1 to the petition chat. The error
	 * code is $s2.
	 */
	PETITION_ADDING_C1_FAILED_ERROR_NUMBER_S2(742),

	/**
	 * ID: 743<br>
	 * Message: $c1 left the petition chat.
	 */
	C1_LEFT_PETITION_CHAT(743),

	/**
	 * ID: 744<br>
	 * Message: You have failed at removing $s1 from the petition chat. The
	 * error code is $s2.
	 */
	PETITION_REMOVING_S1_FAILED_ERROR_NUMBER_S2(744),

	/**
	 * ID: 745<br>
	 * Message: You are currently not in a petition chat.
	 */
	YOU_ARE_NOT_IN_PETITION_CHAT(745),

	/**
	 * ID: 746<br>
	 * Message: It is not currently a petition.
	 */
	CURRENTLY_NO_PETITION(746),

	/**
	 * ID: 748<br>
	 * Message: The distance is too far and so the casting has been stopped.
	 */
	DIST_TOO_FAR_CASTING_STOPPED(748),

	/**
	 * ID: 749<br>
	 * Message: The effect of $s1 has been removed.
	 */
	EFFECT_S1_DISAPPEARED(302),

	/**
	 * ID: 750<br>
	 * Message: There are no other skills to learn.
	 */
	NO_MORE_SKILLS_TO_LEARN(750),

	/**
	 * ID: 751<br>
	 * Message: As there is a conflict in the siege relationship with a clan in
	 * the alliance, you cannot invite that clan to the alliance.
	 */
	CANNOT_INVITE_CONFLICT_CLAN(751),

	/**
	 * ID: 752<br>
	 * Message: That name cannot be used.
	 */
	CANNOT_USE_NAME(752),

	/**
	 * ID: 753<br>
	 * Message: You cannot position mercenaries here.
	 */
	NO_MERCS_HERE(753),

	/**
	 * ID: 754<br>
	 * Message: There are $s1 hours and $s2 minutes left in this week's usage
	 * time.
	 */
	S1_HOURS_S2_MINUTES_LEFT_THIS_WEEK(754),

	/**
	 * ID: 755<br>
	 * Message: There are $s1 minutes left in this week's usage time.
	 */
	S1_MINUTES_LEFT_THIS_WEEK(755),

	/**
	 * ID: 756<br>
	 * Message: This week's usage time has finished.
	 */
	WEEKS_USAGE_TIME_FINISHED(756),

	/**
	 * ID: 757<br>
	 * Message: There are $s1 hours and $s2 minutes left in the fixed use time.
	 */
	S1_HOURS_S2_MINUTES_LEFT_IN_TIME(757),

	/**
	 * ID: 758<br>
	 * Message: There are $s1 hours and $s2 minutes left in this week's play
	 * time.
	 */
	S1_HOURS_S2_MINUTES_LEFT_THIS_WEEKS_PLAY_TIME(758),

	/**
	 * ID: 759<br>
	 * Message: There are $s1 minutes left in this week's play time.
	 */
	S1_MINUTES_LEFT_THIS_WEEKS_PLAY_TIME(759),

	/**
	 * ID: 760<br>
	 * Message: $c1 cannot join the clan because one day has not yet passed
	 * since he/she left another clan.
	 */
	C1_MUST_WAIT_BEFORE_JOINING_ANOTHER_CLAN(760),

	/**
	 * ID: 761<br>
	 * Message: $s1 clan cannot join the alliance because one day has not yet
	 * passed since it left another alliance.
	 */
	S1_CANT_ENTER_ALLIANCE_WITHIN_1_DAY(468),

	/**
	 * ID: 762<br>
	 * Message: $c1 rolled $s2 and $s3's eye came out.
	 */
	C1_ROLLED_S2_S3_EYE_CAME_OUT(762),

	/**
	 * ID: 763<br>
	 * Message: You failed at sending the package because you are too far from
	 * the warehouse.
	 */
	FAILED_SENDING_PACKAGE_TOO_FAR(763),

	/**
	 * ID: 764<br>
	 * Message: You have been playing for an extended period of time. Please
	 * consider taking a break.
	 */
	PLAYING_FOR_LONG_TIME(764),

	/**
	 * ID: 769<br>
	 * Message: A hacking tool has been discovered. Please try again after
	 * closing unnecessary programs.
	 */
	HACKING_TOOL(769),

	/**
	 * ID: 774<br>
	 * Message: Play time is no longer accumulating.
	 */
	PLAY_TIME_NO_LONGER_ACCUMULATING(774),

	/**
	 * ID: 775<br>
	 * Message: From here on, play time will be expended.
	 */
	PLAY_TIME_EXPENDED(775),

	/**
	 * ID: 776<br>
	 * Message: The clan hall which was put up for auction has been awarded to
	 * clan.
	 */
	CLANHALL_AWARDED_TO_CLAN(776),

	/**
	 * ID: 777<br>
	 * Message: The clan hall which was put up for auction was not sold and
	 * therefore has been re-listed.
	 */
	CLANHALL_NOT_SOLD(777),

	/**
	 * ID: 778<br>
	 * Message: You may not log out from this location.
	 */
	NO_LOGOUT_HERE(778),

	/**
	 * ID: 779<br>
	 * Message: You may not restart in this location.
	 */
	NO_RESTART_HERE(779),

	/**
	 * ID: 780<br>
	 * Message: Observation is only possible during a siege.
	 */
	ONLY_VIEW_SIEGE(780),

	/**
	 * ID: 781<br>
	 * Message: Observers cannot participate.
	 */
	OBSERVERS_CANNOT_PARTICIPATE(781),

	/**
	 * ID: 782<br>
	 * Message: You may not observe a siege with a pet or servitor summoned.
	 */
	NO_OBSERVE_WITH_PET(782),

	/**
	 * ID: 783<br>
	 * Message: Lottery ticket sales have been temporarily suspended.
	 */
	LOTTERY_TICKET_SALES_TEMP_SUSPENDED(783),

	/**
	 * ID: 784<br>
	 * Message: Tickets for the current lottery are no longer available.
	 */
	NO_LOTTERY_TICKETS_AVAILABLE(784),

	/**
	 * ID: 785<br>
	 * Message: The results of lottery number $s1 have not yet been published.
	 */
	LOTTERY_S1_RESULT_NOT_PUBLISHED(785),

	/**
	 * ID: 786<br>
	 * Message: Incorrect syntax.
	 */
	INCORRECT_SYNTAX(786),

	/**
	 * ID: 787<br>
	 * Message: The tryouts are finished.
	 */
	CLANHALL_SIEGE_TRYOUTS_FINISHED(787),

	/**
	 * ID: 788<br>
	 * Message: The finals are finished.
	 */
	CLANHALL_SIEGE_FINALS_FINISHED(788),

	/**
	 * ID: 789<br>
	 * Message: The tryouts have begun.
	 */
	CLANHALL_SIEGE_TRYOUTS_BEGUN(789),

	/**
	 * ID: 790<br>
	 * Message: The finals are finished.
	 */
	CLANHALL_SIEGE_FINALS_BEGUN(790),

	/**
	 * ID: 791<br>
	 * Message: The final match is about to begin. Line up!
	 */
	FINAL_MATCH_BEGIN(791),

	/**
	 * ID: 792<br>
	 * Message: The siege of the clan hall is finished.
	 */
	CLANHALL_SIEGE_ENDED(792),

	/**
	 * ID: 793<br>
	 * Message: The siege of the clan hall has begun.
	 */
	CLANHALL_SIEGE_BEGUN(793),

	/**
	 * ID: 794<br>
	 * Message: You are not authorized to do that.
	 */
	YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT(794),

	/**
	 * ID: 795<br>
	 * Message: Only clan leaders are authorized to set rights.
	 */
	ONLY_LEADERS_CAN_SET_RIGHTS(795),

	/**
	 * ID: 796<br>
	 * Message: Your remaining observation time is minutes.
	 */
	REMAINING_OBSERVATION_TIME(796),

	/**
	 * ID: 797<br>
	 * Message: You may create up to 48 macros.
	 */
	YOU_MAY_CREATE_UP_TO_48_MACROS(797),

	/**
	 * ID: 798<br>
	 * Message: Item registration is irreversible. Do you wish to continue?
	 */
	ITEM_REGISTRATION_IRREVERSIBLE(798),

	/**
	 * ID: 799<br>
	 * Message: The observation time has expired.
	 */
	OBSERVATION_TIME_EXPIRED(420),

	/**
	 * ID: 800<br>
	 * Message: You are too late. The registration period is over.
	 */
	REGISTRATION_PERIOD_OVER(800),

	/**
	 * ID: 801<br>
	 * Message: Registration for the clan hall siege is closed.
	 */
	REGISTRATION_CLOSED(801),

	/**
	 * ID: 802<br>
	 * Message: Petitions are not being accepted at this time. You may submit
	 * your petition after a.m./p.m.
	 */
	PETITION_NOT_ACCEPTED_NOW(802),

	/**
	 * ID: 803<br>
	 * Message: Enter the specifics of your petition.
	 */
	PETITION_NOT_SPECIFIED(803),

	/**
	 * ID: 804<br>
	 * Message: Select a type.
	 */
	SELECT_TYPE(804),

	/**
	 * ID: 805<br>
	 * Message: Petitions are not being accepted at this time. You may submit
	 * your petition after $s1 a.m./p.m.
	 */
	PETITION_NOT_ACCEPTED_SUBMIT_AT_S1(805),

	/**
	 * ID: 806<br>
	 * Message: If you are trapped, try typing "/unstuck".
	 */
	TRY_UNSTUCK_WHEN_TRAPPED(806),

	/**
	 * ID: 807<br>
	 * Message: This terrain is navigable. Prepare for transport to the nearest
	 * village.
	 */
	STUCK_PREPARE_FOR_TRANSPORT(807),

	/**
	 * ID: 808<br>
	 * Message: You are stuck. You may submit a petition by typing "/gm".
	 */
	STUCK_SUBMIT_PETITION(808),

	/**
	 * ID: 809<br>
	 * Message: You are stuck. You will be transported to the nearest village in
	 * five minutes.
	 */
	STUCK_TRANSPORT_IN_FIVE_MINUTES(809),

	/**
	 * ID: 810<br>
	 * Message: Invalid macro. Refer to the Help file for instructions.
	 */
	INVALID_MACRO(810),

	/**
	 * ID: 811<br>
	 * Message: You will be moved to (). Do you wish to continue?
	 */
	WILL_BE_MOVED(811),

	/**
	 * ID: 812<br>
	 * Message: The secret trap has inflicted $s1 damage on you.
	 */
	TRAP_DID_S1_DAMAGE(812),

	/**
	 * ID: 813<br>
	 * Message: You have been poisoned by a Secret Trap.
	 */
	POISONED_BY_TRAP(813),

	/**
	 * ID: 814<br>
	 * Message: Your speed has been decreased by a Secret Trap.
	 */
	SLOWED_BY_TRAP(814),

	/**
	 * ID: 815<br>
	 * Message: The tryouts are about to begin. Line up!
	 */
	TRYOUTS_ABOUT_TO_BEGIN(815),

	/**
	 * ID: 816<br>
	 * Message: Tickets are now available for Monster Race $s1!
	 */
	MONSRACE_TICKETS_AVAILABLE_FOR_S1_RACE(816),

	/**
	 * ID: 817<br>
	 * Message: Now selling tickets for Monster Race $s1!
	 */
	MONSRACE_TICKETS_NOW_AVAILABLE_FOR_S1_RACE(817),

	/**
	 * ID: 818<br>
	 * Message: Ticket sales for the Monster Race will end in $s1 minute(s).
	 */
	MONSRACE_TICKETS_STOP_IN_S1_MINUTES(818),

	/**
	 * ID: 819<br>
	 * Message: Tickets sales are closed for Monster Race $s1. Odds are posted.
	 */
	MONSRACE_S1_TICKET_SALES_CLOSED(819),

	/**
	 * ID: 820<br>
	 * Message: Monster Race $s2 will begin in $s1 minute(s)!
	 */
	MONSRACE_S2_BEGINS_IN_S1_MINUTES(820),

	/**
	 * ID: 821<br>
	 * Message: Monster Race $s1 will begin in 30 seconds!
	 */
	MONSRACE_S1_BEGINS_IN_30_SECONDS(821),

	/**
	 * ID: 822<br>
	 * Message: Monster Race $s1 is about to begin! Countdown in five seconds!
	 */
	MONSRACE_S1_COUNTDOWN_IN_FIVE_SECONDS(822),

	/**
	 * ID: 823<br>
	 * Message: The race will begin in $s1 second(s)!
	 */
	MONSRACE_BEGINS_IN_S1_SECONDS(823),

	/**
	 * ID: 824<br>
	 * Message: They're off!
	 */
	MONSRACE_RACE_START(824),

	/**
	 * ID: 825<br>
	 * Message: Monster Race $s1 is finished!
	 */
	MONSRACE_S1_RACE_END(825),

	/**
	 * ID: 826<br>
	 * Message: First prize goes to the player in lane $s1. Second prize goes to
	 * the player in lane $s2.
	 */
	MONSRACE_FIRST_PLACE_S1_SECOND_S2(826),

	/**
	 * ID: 827<br>
	 * Message: You may not impose a block on a GM.
	 */
	YOU_MAY_NOT_IMPOSE_A_BLOCK_ON_GM(827),

	/**
	 * ID: 828<br>
	 * Message: Are you sure you wish to delete the $s1 macro?
	 */
	WISH_TO_DELETE_S1_MACRO(828),

	/**
	 * ID: 829<br>
	 * Message: You cannot recommend yourself.
	 */
	YOU_CANNOT_RECOMMEND_YOURSELF(829),

	/**
	 * ID: 830<br>
	 * Message: You have recommended $c1. You have $s2 recommendations left.
	 */
	YOU_HAVE_RECOMMENDED_C1_YOU_HAVE_S2_RECOMMENDATIONS_LEFT(830),

	/**
	 * ID: 831<br>
	 * Message: You have been recommended by $c1.
	 */
	YOU_HAVE_BEEN_RECOMMENDED_BY_C1(831),

	/**
	 * ID: 832<br>
	 * Message: That character has already been recommended.
	 */
	THAT_CHARACTER_IS_RECOMMENDED(832),

	/**
	 * ID: 833<br>
	 * Message: You are not authorized to make further recommendations at this
	 * time. You will receive more recommendation credits each day at 1 p.m.
	 */
	NO_MORE_RECOMMENDATIONS_TO_HAVE(833),

	/**
	 * ID: 834<br>
	 * Message: $c1 has rolled $s2.
	 */
	C1_ROLLED_S2(834),

	/**
	 * ID: 835<br>
	 * Message: You may not throw the dice at this time. Try again later.
	 */
	YOU_MAY_NOT_THROW_THE_DICE_AT_THIS_TIME_TRY_AGAIN_LATER(184),

	/**
	 * ID: 836<br>
	 * Message: You have exceeded your inventory volume limit and cannot take
	 * this item.
	 */
	YOU_HAVE_EXCEEDED_YOUR_INVENTORY_VOLUME_LIMIT_AND_CANNOT_TAKE_THIS_ITEM(836),

	/**
	 * ID: 837<br>
	 * Message: Macro descriptions may contain up to 32 characters.
	 */
	MACRO_DESCRIPTION_MAX_32_CHARS(837),

	/**
	 * ID: 838<br>
	 * Message: Enter the name of the macro.
	 */
	ENTER_THE_MACRO_NAME(838),

	/**
	 * ID: 839<br>
	 * Message: That name is already assigned to another macro.
	 */
	MACRO_NAME_ALREADY_USED(839),

	/**
	 * ID: 840<br>
	 * Message: That recipe is already registered.
	 */
	RECIPE_ALREADY_REGISTERED(840),

	/**
	 * ID: 841<br>
	 * Message: No further recipes may be registered.
	 */
	NO_FUTHER_RECIPES_CAN_BE_ADDED(841),

	/**
	 * ID: 842<br>
	 * Message: You are not authorized to register a recipe.
	 */
	NOT_AUTHORIZED_REGISTER_RECIPE(842),

	/**
	 * ID: 843<br>
	 * Message: The siege of $s1 is finished.
	 */
	SIEGE_OF_S1_FINISHED(843),

	/**
	 * ID: 844<br>
	 * Message: The siege to conquer $s1 has begun.
	 */
	SIEGE_OF_S1_BEGUN(844),

	/**
	 * ID: 845<br>
	 * Message: The deadlineto register for the siege of $s1 has passed.
	 */
	DEADLINE_FOR_SIEGE_S1_PASSED(845),

	/**
	 * ID: 846<br>
	 * Message: The siege of $s1 has been canceled due to lack of interest.
	 */
	SIEGE_OF_S1_HAS_BEEN_CANCELED_DUE_TO_LACK_OF_INTEREST(846),

	/**
	 * ID: 847<br>
	 * Message: A clan that owns a clan hall may not participate in a clan hall
	 * siege.
	 */
	CLAN_OWNING_CLANHALL_MAY_NOT_SIEGE_CLANHALL(847),

	/**
	 * ID: 848<br>
	 * Message: $s1 has been deleted.
	 */
	S1_HAS_BEEN_DELETED(848),

	/**
	 * ID: 849<br>
	 * Message: $s1 cannot be found.
	 */
	S1_NOT_FOUND(849),

	/**
	 * ID: 850<br>
	 * Message: $s1 already exists.
	 */
	S1_ALREADY_EXISTS2(850),

	/**
	 * ID: 851<br>
	 * Message: $s1 has been added.
	 */
	S1_ADDED(851),

	/**
	 * ID: 852<br>
	 * Message: The recipe is incorrect.
	 */
	RECIPE_INCORRECT(852),

	/**
	 * ID: 853<br>
	 * Message: You may not alter your recipe book while engaged in
	 * manufacturing.
	 */
	CANT_ALTER_RECIPEBOOK_WHILE_CRAFTING(853),

	/**
	 * ID: 854<br>
	 * Message: You are missing $s2 $s1 required to create that.
	 */
	MISSING_S2_S1_TO_CREATE(854),

	/**
	 * ID: 855<br>
	 * Message: $s1 clan has defeated $s2.
	 */
	S1_CLAN_DEFEATED_S2(855),

	/**
	 * ID: 856<br>
	 * Message: The siege of $s1 has ended in a draw.
	 */
	SIEGE_S1_DRAW(856),

	/**
	 * ID: 857<br>
	 * Message: $s1 clan has won in the preliminary match of $s2.
	 */
	S1_CLAN_WON_MATCH_S2(857),

	/**
	 * ID: 858<br>
	 * Message: The preliminary match of $s1 has ended in a draw.
	 */
	MATCH_OF_S1_DRAW(858),

	/**
	 * ID: 859<br>
	 * Message: Please register a recipe.
	 */
	PLEASE_REGISTER_RECIPE(859),

	/**
	 * ID: 860<br>
	 * Message: You may not buld your headquarters in close proximity to another
	 * headquarters.
	 */
	HEADQUARTERS_TOO_CLOSE(860),

	/**
	 * ID: 861<br>
	 * Message: You have exceeded the maximum number of memos.
	 */
	TOO_MANY_MEMOS(861),

	/**
	 * ID: 862<br>
	 * Message: Odds are not posted until ticket sales have closed.
	 */
	ODDS_NOT_POSTED(862),

	/**
	 * ID: 863<br>
	 * Message: You feel the energy of fire.
	 */
	FEEL_ENERGY_FIRE(863),

	/**
	 * ID: 864<br>
	 * Message: You feel the energy of water.
	 */
	FEEL_ENERGY_WATER(864),

	/**
	 * ID: 865<br>
	 * Message: You feel the energy of wind.
	 */
	FEEL_ENERGY_WIND(865),

	/**
	 * ID: 866<br>
	 * Message: You may no longer gather energy.
	 */
	NO_LONGER_ENERGY(866),

	/**
	 * ID: 867<br>
	 * Message: The energy is depleted.
	 */
	ENERGY_DEPLETED(867),

	/**
	 * ID: 868<br>
	 * Message: The energy of fire has been delivered.
	 */
	ENERGY_FIRE_DELIVERED(868),

	/**
	 * ID: 869<br>
	 * Message: The energy of water has been delivered.
	 */
	ENERGY_WATER_DELIVERED(869),

	/**
	 * ID: 870<br>
	 * Message: The energy of wind has been delivered.
	 */
	ENERGY_WIND_DELIVERED(870),

	/**
	 * ID: 871<br>
	 * Message: The seed has been sown.
	 */
	THE_SEED_HAS_BEEN_SOWN(871),

	/**
	 * ID: 872<br>
	 * Message: This seed may not be sown here.
	 */
	THIS_SEED_MAY_NOT_BE_SOWN_HERE(872),

	/**
	 * ID: 873<br>
	 * Message: That character does not exist.
	 */
	CHARACTER_DOES_NOT_EXIST(873),

	/**
	 * ID: 874<br>
	 * Message: The capacity of the warehouse has been exceeded.
	 */
	WAREHOUSE_CAPACITY_EXCEEDED(874),

	/**
	 * ID: 875<br>
	 * Message: The transport of the cargo has been canceled.
	 */
	CARGO_CANCELED(875),

	/**
	 * ID: 876<br>
	 * Message: The cargo was not delivered.
	 */
	CARGO_NOT_DELIVERED(876),

	/**
	 * ID: 877<br>
	 * Message: The symbol has been added.
	 */
	SYMBOL_ADDED(877),

	/**
	 * ID: 878<br>
	 * Message: The symbol has been deleted.
	 */
	SYMBOL_DELETED(878),

	/**
	 * ID: 879<br>
	 * Message: The manor system is currently under maintenance.
	 */
	THE_MANOR_SYSTEM_IS_CURRENTLY_UNDER_MAINTENANCE(879),

	/**
	 * ID: 880<br>
	 * Message: The transaction is complete.
	 */
	THE_TRANSACTION_IS_COMPLETE(880),

	/**
	 * ID: 881<br>
	 * Message: There is a discrepancy on the invoice.
	 */
	THERE_IS_A_DISCREPANCY_ON_THE_INVOICE(881),

	/**
	 * ID: 882<br>
	 * Message: The seed quantity is incorrect.
	 */
	THE_SEED_QUANTITY_IS_INCORRECT(882),

	/**
	 * ID: 883<br>
	 * Message: The seed information is incorrect.
	 */
	THE_SEED_INFORMATION_IS_INCORRECT(883),

	/**
	 * ID: 884<br>
	 * Message: The manor information has been updated.
	 */
	THE_MANOR_INFORMATION_HAS_BEEN_UPDATED(884),

	/**
	 * ID: 885<br>
	 * Message: The number of crops is incorrect.
	 */
	THE_NUMBER_OF_CROPS_IS_INCORRECT(885),

	/**
	 * ID: 886<br>
	 * Message: The crops are priced incorrectly.
	 */
	THE_CROPS_ARE_PRICED_INCORRECTLY(886),

	/**
	 * ID: 887<br>
	 * Message: The type is incorrect.
	 */
	THE_TYPE_IS_INCORRECT(887),

	/**
	 * ID: 888<br>
	 * Message: No crops can be purchased at this time.
	 */
	NO_CROPS_CAN_BE_PURCHASED_AT_THIS_TIME(888),

	/**
	 * ID: 889<br>
	 * Message: The seed was successfully sown.
	 */
	THE_SEED_WAS_SUCCESSFULLY_SOWN(889),

	/**
	 * ID: 890<br>
	 * Message: The seed was not sown.
	 */
	THE_SEED_WAS_NOT_SOWN(890),

	/**
	 * ID: 891<br>
	 * Message: You are not authorized to harvest.
	 */
	YOU_ARE_NOT_AUTHORIZED_TO_HARVEST(891),

	/**
	 * ID: 892<br>
	 * Message: The harvest has failed.
	 */
	THE_HARVEST_HAS_FAILED(892),

	/**
	 * ID: 893<br>
	 * Message: The harvest failed because the seed was not sown.
	 */
	THE_HARVEST_FAILED_BECAUSE_THE_SEED_WAS_NOT_SOWN(890),

	/**
	 * ID: 894<br>
	 * Message: Up to $s1 recipes can be registered.
	 */
	UP_TO_S1_RECIPES_CAN_REGISTER(894),

	/**
	 * ID: 895<br>
	 * Message: No recipes have been registered.
	 */
	NO_RECIPES_REGISTERED(895),

	/**
	 * ID: 896<br>
	 * Message:The ferry has arrived at Gludin Harbor.
	 */
	FERRY_AT_GLUDIN(896),

	/**
	 * ID: 897<br>
	 * Message:The ferry will leave for Talking Island Harbor after anchoring
	 * for ten minutes.
	 */
	FERRY_LEAVE_TALKING(897),

	/**
	 * ID: 898<br>
	 * Message: Only characters of level 10 or above are authorized to make
	 * recommendations.
	 */
	ONLY_LEVEL_SUP_10_CAN_RECOMMEND(898),

	/**
	 * ID: 899<br>
	 * Message: The symbol cannot be drawn.
	 */
	CANT_DRAW_SYMBOL(899),

	/**
	 * ID: 900<br>
	 * Message: No slot exists to draw the symbol
	 */
	SYMBOLS_FULL(900),

	/**
	 * ID: 901<br>
	 * Message: The symbol information cannot be found.
	 */
	SYMBOL_NOT_FOUND(901),

	/**
	 * ID: 902<br>
	 * Message: The number of items is incorrect.
	 */
	NUMBER_INCORRECT(902),

	/**
	 * ID: 903<br>
	 * Message: You may not submit a petition while frozen. Be patient.
	 */
	NO_PETITION_WHILE_FROZEN(903),

	/**
	 * ID: 904<br>
	 * Message: Items cannot be discarded while in private store status.
	 */
	NO_DISCARD_WHILE_PRIVATE_STORE(904),

	/**
	 * ID: 905<br>
	 * Message: The current score for the Humans is $s1.
	 */
	HUMAN_SCORE_S1(905),

	/**
	 * ID: 906<br>
	 * Message: The current score for the Elves is $s1.
	 */
	ELVES_SCORE_S1(906),

	/**
	 * ID: 907<br>
	 * Message: The current score for the Dark Elves is $s1.
	 */
	DARK_ELVES_SCORE_S1(906),

	/**
	 * ID: 908<br>
	 * Message: The current score for the Orcs is $s1.
	 */
	ORCS_SCORE_S1(908),

	/**
	 * ID: 909<br>
	 * Message: The current score for the Dwarves is $s1.
	 */
	DWARVEN_SCORE_S1(909),

	/**
	 * ID: 910<br>
	 * Message: Current location : $s1, $s2, $s3 (Near Talking Island Village)
	 */
	LOC_TI_S1_S2_S3(910),

	/**
	 * ID: 911<br>
	 * Message: Current location : $s1, $s2, $s3 (Near Gludin Village)
	 */
	LOC_GLUDIN_S1_S2_S3(911),

	/**
	 * ID: 912<br>
	 * Message: Current location : $s1, $s2, $s3 (Near the Town of Gludio)
	 */
	LOC_GLUDIO_S1_S2_S3(912),

	/**
	 * ID: 913<br>
	 * Message: Current location : $s1, $s2, $s3 (Near the Neutral Zone)
	 */
	LOC_NETRAL_ZONE_S1_S2_S3(913),

	/**
	 * ID: 914<br>
	 * Message: Current location : $s1, $s2, $s3 (Near the Elven Village)
	 */
	LOC_ELVEN_S1_S2_S3(914),

	/**
	 * ID: 915<br>
	 * Message: Current location : $s1, $s2, $s3 (Near the Dark Elf Village)
	 */
	LOC_DARK_ELVEN_S1_S2_S3(915),

	/**
	 * ID: 916<br>
	 * Message: Current location : $s1, $s2, $s3 (Near the Town of Dion)
	 */
	LOC_DION_S1_S2_S3(916),

	/**
	 * ID: 917<br>
	 * Message: Current location : $s1, $s2, $s3 (Near the Floran Village)
	 */
	LOC_FLORAN_S1_S2_S3(917),

	/**
	 * ID: 918<br>
	 * Message: Current location : $s1, $s2, $s3 (Near the Town of Giran)
	 */
	LOC_GIRAN_S1_S2_S3(918),

	/**
	 * ID: 919<br>
	 * Message: Current location : $s1, $s2, $s3 (Near Giran Harbor)
	 */
	LOC_GIRAN_HARBOR_S1_S2_S3(919),

	/**
	 * ID: 920<br>
	 * Message: Current location : $s1, $s2, $s3 (Near the Orc Village)
	 */
	LOC_ORC_S1_S2_S3(920),

	/**
	 * ID: 921<br>
	 * Message: Current location : $s1, $s2, $s3 (Near the Dwarven Village)
	 */
	LOC_DWARVEN_S1_S2_S3(921),

	/**
	 * ID: 922<br>
	 * Message: Current location : $s1, $s2, $s3 (Near the Town of Oren)
	 */
	LOC_OREN_S1_S2_S3(922),

	/**
	 * ID: 923<br>
	 * Message: Current location : $s1, $s2, $s3 (Near Hunters Village)
	 */
	LOC_HUNTER_S1_S2_S3(923),

	/**
	 * ID: 924<br>
	 * Message: Current location : $s1, $s2, $s3 (Near Aden Castle Town)
	 */
	LOC_ADEN_S1_S2_S3(924),

	/**
	 * ID: 925<br>
	 * Message: Current location : $s1, $s2, $s3 (Near the Coliseum)
	 */
	LOC_COLISEUM_S1_S2_S3(925),

	/**
	 * ID: 926<br>
	 * Message: Current location : $s1, $s2, $s3 (Near Heine)
	 */
	LOC_HEINE_S1_S2_S3(926),

	/**
	 * ID: 927<br>
	 * Message: The current time is $s1:$s2.
	 */
	TIME_S1_S2_IN_THE_DAY(927),

	/**
	 * ID: 928<br>
	 * Message: The current time is $s1:$s2.
	 */
	TIME_S1_S2_IN_THE_NIGHT(928),

	/**
	 * ID: 929<br>
	 * Message: No compensation was given for the farm products.
	 */
	NO_COMPENSATION_FOR_FARM_PRODUCTS(929),

	/**
	 * ID: 930<br>
	 * Message: Lottery tickets are not currently being sold.
	 */
	NO_LOTTERY_TICKETS_CURRENT_SOLD(930),

	/**
	 * ID: 931<br>
	 * Message: The winning lottery ticket numbers has not yet been anonunced.
	 */
	LOTTERY_WINNERS_NOT_ANNOUNCED_YET(931),

	/**
	 * ID: 932<br>
	 * Message: You cannot chat locally while observing.
	 */
	NO_ALLCHAT_WHILE_OBSERVING(932),

	/**
	 * ID: 933<br>
	 * Message: The seed pricing greatly differs from standard seed prices.
	 */
	THE_SEED_PRICING_GREATLY_DIFFERS_FROM_STANDARD_SEED_PRICES(933),

	/**
	 * ID: 934<br>
	 * Message: It is a deleted recipe.
	 */
	A_DELETED_RECIPE(934),

	/**
	 * ID: 935<br>
	 * Message: The amount is not sufficient and so the manor is not in
	 * operation.
	 */
	THE_AMOUNT_IS_NOT_SUFFICIENT_AND_SO_THE_MANOR_IS_NOT_IN_OPERATION(935),

	/**
	 * ID: 936<br>
	 * Message: Use $s1.
	 */
	USE_S1_(936),

	/**
	 * ID: 937<br>
	 * Message: Currently preparing for private workshop.
	 */
	PREPARING_PRIVATE_WORKSHOP(937),

	/**
	 * ID: 938<br>
	 * Message: The community server is currently offline.
	 */
	CB_OFFLINE(938),

	/**
	 * ID: 939<br>
	 * Message: You cannot exchange while blocking everything.
	 */
	NO_EXCHANGE_WHILE_BLOCKING(939),

	/**
	 * ID: 940<br>
	 * Message: $s1 is blocked everything.
	 */
	S1_BLOCKED_EVERYTHING(940),

	/**
	 * ID: 941<br>
	 * Message: Restart at Talking Island Village.
	 */
	RESTART_AT_TI(941),

	/**
	 * ID: 942<br>
	 * Message: Restart at Gludin Village.
	 */
	RESTART_AT_GLUDIN(942),

	/**
	 * ID: 943<br>
	 * Message: Restart at the Town of Gludin. || guess should be Gludio ,)
	 */
	RESTART_AT_GLUDIO(943),

	/**
	 * ID: 944<br>
	 * Message: Restart at the Neutral Zone.
	 */
	RESTART_AT_NEUTRAL_ZONE(944),

	/**
	 * ID: 945<br>
	 * Message: Restart at the Elven Village.
	 */
	RESTART_AT_ELFEN_VILLAGE(945),

	/**
	 * ID: 946<br>
	 * Message: Restart at the Dark Elf Village.
	 */
	RESTART_AT_DARKELF_VILLAGE(946),

	/**
	 * ID: 947<br>
	 * Message: Restart at the Town of Dion.
	 */
	RESTART_AT_DION(947),

	/**
	 * ID: 948<br>
	 * Message: Restart at Floran Village.
	 */
	RESTART_AT_FLORAN(948),

	/**
	 * ID: 949<br>
	 * Message: Restart at the Town of Giran.
	 */
	RESTART_AT_GIRAN(949),

	/**
	 * ID: 950<br>
	 * Message: Restart at Giran Harbor.
	 */
	RESTART_AT_GIRAN_HARBOR(950),

	/**
	 * ID: 951<br>
	 * Message: Restart at the Orc Village.
	 */
	RESTART_AT_ORC_VILLAGE(951),

	/**
	 * ID: 952<br>
	 * Message: Restart at the Dwarven Village.
	 */
	RESTART_AT_DWARFEN_VILLAGE(952),

	/**
	 * ID: 953<br>
	 * Message: Restart at the Town of Oren.
	 */
	RESTART_AT_OREN(953),

	/**
	 * ID: 954<br>
	 * Message: Restart at Hunters Village.
	 */
	RESTART_AT_HUNTERS_VILLAGE(954),

	/**
	 * ID: 955<br>
	 * Message: Restart at the Town of Aden.
	 */
	RESTART_AT_ADEN(955),

	/**
	 * ID: 956<br>
	 * Message: Restart at the Coliseum.
	 */
	RESTART_AT_COLISEUM(956),

	/**
	 * ID: 957<br>
	 * Message: Restart at Heine.
	 */
	RESTART_AT_HEINE(957),

	/**
	 * ID: 958<br>
	 * Message: Items cannot be discarded or destroyed while operating a private
	 * store or workshop.
	 */
	ITEMS_CANNOT_BE_DISCARDED_OR_DESTROYED_WHILE_OPERATING_PRIVATE_STORE_OR_WORKSHOP(
			958),

	/**
	 * ID: 959<br>
	 * Message: $s1 (*$s2) manufactured successfully.
	 */
	S1_S2_MANUFACTURED_SUCCESSFULLY(959),

	/**
	 * ID: 960<br>
	 * Message: $s1 manufacturing failure.
	 */
	S1_MANUFACTURE_FAILURE(960),

	/**
	 * ID: 961<br>
	 * Message: You are now blocking everything.
	 */
	BLOCKING_ALL(961),

	/**
	 * ID: 962<br>
	 * Message: You are no longer blocking everything.
	 */
	NOT_BLOCKING_ALL(961),

	/**
	 * ID: 963<br>
	 * Message: Please determine the manufacturing price.
	 */
	DETERMINE_MANUFACTURE_PRICE(963),

	/**
	 * ID: 964<br>
	 * Message: Chatting is prohibited for one minute.
	 */
	CHATBAN_FOR_1_MINUTE(964),

	/**
	 * ID: 965<br>
	 * Message: The chatting prohibition has been removed.
	 */
	CHATBAN_REMOVED(965),

	/**
	 * ID: 966<br>
	 * Message: Chatting is currently prohibited. If you try to chat before the
	 * prohibition is removed, the prohibition time will become even longer.
	 */
	CHATTING_IS_CURRENTLY_PROHIBITED(966),

	/**
	 * ID: 967<br>
	 * Message: Do you accept $c1's party invitation? (Item Distribution: Random
	 * including spoil.)
	 */
	C1_PARTY_INVITE_RANDOM_INCLUDING_SPOIL(967),

	/**
	 * ID: 968<br>
	 * Message: Do you accept $c1's party invitation? (Item Distribution: By
	 * Turn.)
	 */
	C1_PARTY_INVITE_BY_TURN(968),

	/**
	 * ID: 969<br>
	 * Message: Do you accept $c1's party invitation? (Item Distribution: By
	 * Turn including spoil.)
	 */
	C1_PARTY_INVITE_BY_TURN_INCLUDING_SPOIL(969),

	/**
	 * ID: 970<br>
	 * Message: $s2's MP has been drained by $c1.
	 */
	S2_MP_HAS_BEEN_DRAINED_BY_C1(970),

	/**
	 * ID: 971<br>
	 * Message: Petitions cannot exceed 255 characters.
	 */
	PETITION_MAX_CHARS_255(971),

	/**
	 * ID: 972<br>
	 * Message: This pet cannot use this item.
	 */
	PET_CANNOT_USE_ITEM(972),

	/**
	 * ID: 973<br>
	 * Message: Please input no more than the number you have.
	 */
	INPUT_NO_MORE_YOU_HAVE(973),

	/**
	 * ID: 974<br>
	 * Message: The soul crystal succeeded in absorbing a soul.
	 */
	SOUL_CRYSTAL_ABSORBING_SUCCEEDED(974),

	/**
	 * ID: 975<br>
	 * Message: The soul crystal was not able to absorb a soul.
	 */
	SOUL_CRYSTAL_ABSORBING_FAILED(975),

	/**
	 * ID: 976<br>
	 * Message: The soul crystal broke because it was not able to endure the
	 * soul energy.
	 */
	SOUL_CRYSTAL_BROKE(976),

	/**
	 * ID: 977<br>
	 * Message: The soul crystals caused resonation and failed at absorbing a
	 * soul.
	 */
	SOUL_CRYSTAL_ABSORBING_FAILED_RESONATION(977),

	/**
	 * ID: 978<br>
	 * Message: The soul crystal is refusing to absorb a soul.
	 */
	SOUL_CRYSTAL_ABSORBING_REFUSED(978),

	/**
	 * ID: 979<br>
	 * Message: The ferry arrived at Talking Island Harbor.
	 */
	FERRY_ARRIVED_AT_TALKING(979),

	/**
	 * ID: 980<br>
	 * Message: The ferry will leave for Gludin Harbor after anchoring for ten
	 * minutes.
	 */
	FERRY_LEAVE_FOR_GLUDIN_AFTER_10_MINUTES(980),

	/**
	 * ID: 981<br>
	 * Message: The ferry will leave for Gludin Harbor in five minutes.
	 */
	FERRY_LEAVE_FOR_GLUDIN_IN_5_MINUTES(981),

	/**
	 * ID: 982<br>
	 * Message: The ferry will leave for Gludin Harbor in one minute.
	 */
	FERRY_LEAVE_FOR_GLUDIN_IN_1_MINUTE(982),

	/**
	 * ID: 983<br>
	 * Message: Those wishing to ride should make haste to get on.
	 */
	MAKE_HASTE_GET_ON_BOAT(983),

	/**
	 * ID: 984<br>
	 * Message: The ferry will be leaving soon for Gludin Harbor.
	 */
	FERRY_LEAVE_SOON_FOR_GLUDIN(984),

	/**
	 * ID: 985<br>
	 * Message: The ferry is leaving for Gludin Harbor.
	 */
	FERRY_LEAVING_FOR_GLUDIN(985),

	/**
	 * ID: 986<br>
	 * Message: The ferry has arrived at Gludin Harbor.
	 */
	FERRY_ARRIVED_AT_GLUDIN(986),

	/**
	 * ID: 987<br>
	 * Message: The ferry will leave for Talking Island Harbor after anchoring
	 * for ten minutes.
	 */
	FERRY_LEAVE_FOR_TALKING_AFTER_10_MINUTES(987),

	/**
	 * ID: 988<br>
	 * Message: The ferry will leave for Talking Island Harbor in five minutes.
	 */
	FERRY_LEAVE_FOR_TALKING_IN_5_MINUTES(988),

	/**
	 * ID: 989<br>
	 * Message: The ferry will leave for Talking Island Harbor in one minute.
	 */
	FERRY_LEAVE_FOR_TALKING_IN_1_MINUTE(989),

	/**
	 * ID: 990<br>
	 * Message: The ferry will be leaving soon for Talking Island Harbor.
	 */
	FERRY_LEAVE_SOON_FOR_TALKING(990),

	/**
	 * ID: 991<br>
	 * Message: The ferry is leaving for Talking Island Harbor.
	 */
	FERRY_LEAVING_FOR_TALKING(991),

	/**
	 * ID: 992<br>
	 * Message: The ferry has arrived at Giran Harbor.
	 */
	FERRY_ARRIVED_AT_GIRAN(992),

	/**
	 * ID: 993<br>
	 * Message: The ferry will leave for Giran Harbor after anchoring for ten
	 * minutes.
	 */
	FERRY_LEAVE_FOR_GIRAN_AFTER_10_MINUTES(993),

	/**
	 * ID: 994<br>
	 * Message: The ferry will leave for Giran Harbor in five minutes.
	 */
	FERRY_LEAVE_FOR_GIRAN_IN_5_MINUTES(994),

	/**
	 * ID: 995<br>
	 * Message: The ferry will leave for Giran Harbor in one minute.
	 */
	FERRY_LEAVE_FOR_GIRAN_IN_1_MINUTE(995),

	/**
	 * ID: 996<br>
	 * Message: The ferry will be leaving soon for Giran Harbor.
	 */
	FERRY_LEAVE_SOON_FOR_GIRAN(996),

	/**
	 * ID: 997<br>
	 * Message: The ferry is leaving for Giran Harbor.
	 */
	FERRY_LEAVING_FOR_GIRAN(997),

	/**
	 * ID: 998<br>
	 * Message: The Innadril pleasure boat has arrived. It will anchor for ten
	 * minutes.
	 */
	INNADRIL_BOAT_ANCHOR_10_MINUTES(998),

	/**
	 * ID: 999<br>
	 * Message: The Innadril pleasure boat will leave in five minutes.
	 */
	INNADRIL_BOAT_LEAVE_IN_5_MINUTES(999),

	/**
	 * ID: 1000<br>
	 * Message: The Innadril pleasure boat will leave in one minute.
	 */
	INNADRIL_BOAT_LEAVE_IN_1_MINUTE(1000),

	/**
	 * ID: 1001<br>
	 * Message: The Innadril pleasure boat will be leaving soon.
	 */
	INNADRIL_BOAT_LEAVE_SOON(1001),

	/**
	 * ID: 1002<br>
	 * Message: The Innadril pleasure boat is leaving.
	 */
	INNADRIL_BOAT_LEAVING(1002),

	/**
	 * ID: 1003<br>
	 * Message: Cannot possess a monster race ticket.
	 */
	CANNOT_POSSES_MONS_TICKET(1003),

	/**
	 * ID: 1004<br>
	 * Message: You have registered for a clan hall auction.
	 */
	REGISTERED_FOR_CLANHALL(1004),

	/**
	 * ID: 1005<br>
	 * Message: There is not enough adena in the clan hall warehouse.
	 */
	NOT_ENOUGH_ADENA_IN_CWH(1005),

	/**
	 * ID: 1006<br>
	 * Message: You have bid in a clan hall auction.
	 */
	BID_IN_CLANHALL_AUCTION(1006),

	/**
	 * ID: 1007<br>
	 * Message: The preliminary match registration of $s1 has finished.
	 */
	PRELIMINARY_REGISTRATION_OF_S1_FINISHED(1007),

	/**
	 * ID: 1008<br>
	 * Message: A hungry strider cannot be mounted or dismounted.
	 */
	HUNGRY_STRIDER_NOT_MOUNT(1008),

	/**
	 * ID: 1009<br>
	 * Message: A strider cannot be ridden when dead.
	 */
	STRIDER_CANT_BE_RIDDEN_WHILE_DEAD(1009),

	/**
	 * ID: 1010<br>
	 * Message: A dead strider cannot be ridden.
	 */
	DEAD_STRIDER_CANT_BE_RIDDEN(1010),

	/**
	 * ID: 1011<br>
	 * Message: A strider in battle cannot be ridden.
	 */
	STRIDER_IN_BATLLE_CANT_BE_RIDDEN(1011),

	/**
	 * ID: 1012<br>
	 * Message: A strider cannot be ridden while in battle.
	 */
	STRIDER_CANT_BE_RIDDEN_WHILE_IN_BATTLE(1012),

	/**
	 * ID: 1013<br>
	 * Message: A strider can be ridden only when standing.
	 */
	STRIDER_CAN_BE_RIDDEN_ONLY_WHILE_STANDING(1013),

	/**
	 * ID: 1014<br>
	 * Message: Your pet gained $s1 experience points.
	 */
	PET_EARNED_S1_EXP(1014),

	/**
	 * ID: 1015<br>
	 * Message: Your pet hit for $s1 damage.
	 */
	PET_HIT_FOR_S1_DAMAGE(1015),

	/**
	 * ID: 1016<br>
	 * Message: Pet received $s2 damage by $c1.
	 */
	PET_RECEIVED_S2_DAMAGE_BY_C1(1016),

	/**
	 * ID: 1017<br>
	 * Message: Pet's critical hit!
	 */
	CRITICAL_HIT_BY_PET(1017),

	/**
	 * ID: 1018<br>
	 * Message: Your pet uses $s1.
	 */
	PET_USES_S1(1018),

	/**
	 * ID: 1019<br>
	 * Message: Your pet uses $s1.
	 */
	PET_USES_S1_(1019),

	/**
	 * ID: 1020<br>
	 * Message: Your pet picked up $s1.
	 */
	PET_PICKED_S1(1020),

	/**
	 * ID: 1021<br>
	 * Message: Your pet picked up $s2 $s1(s).
	 */
	PET_PICKED_S2_S1_S(1021),

	/**
	 * ID: 1022<br>
	 * Message: Your pet picked up +$s1 $s2.
	 */
	PET_PICKED_S1_S2(1022),

	/**
	 * ID: 1023<br>
	 * Message: Your pet picked up $s1 adena.
	 */
	PET_PICKED_S1_ADENA(1023),

	/**
	 * ID: 1024<br>
	 * Message: Your pet put on $s1.
	 */
	PET_PUT_ON_S1(1024),

	/**
	 * ID: 1025<br>
	 * Message: Your pet took off $s1.
	 */
	PET_TOOK_OFF_S1(1025),

	/**
	 * ID: 1026<br>
	 * Message: The summoned monster gave damage of $s1
	 */
	SUMMON_GAVE_DAMAGE_S1(1026),

	/**
	 * ID: 1027<br>
	 * Message: Servitor received $s2 damage caused by $s1.
	 */
	SUMMON_RECEIVED_DAMAGE_S2_BY_S1(1027),

	/**
	 * ID: 1028<br>
	 * Message: Summoned monster's critical hit!
	 */
	CRITICAL_HIT_BY_SUMMONED_MOB(1028),

	/**
	 * ID: 1029<br>
	 * Message: Summoned monster uses $s1.
	 */
	SUMMONED_MOB_USES_S1(1029),

	/**
	 * ID: 1030<br>
	 * Message: <Party Information>
	 */
	PARTY_INFORMATION(1030),

	/**
	 * ID: 1031<br>
	 * Message: Looting method: Finders keepers
	 */
	LOOTING_FINDERS_KEEPERS(1031),

	/**
	 * ID: 1032<br>
	 * Message: Looting method: Random
	 */
	LOOTING_RANDOM(1032),

	/**
	 * ID: 1033<br>
	 * Message: Looting method: Random including spoil
	 */
	LOOTING_RANDOM_INCLUDE_SPOIL(1033),

	/**
	 * ID: 1034<br>
	 * Message: Looting method: By turn
	 */
	LOOTING_BY_TURN(1034),

	/**
	 * ID: 1035<br>
	 * Message: Looting method: By turn including spoil
	 */
	LOOTING_BY_TURN_INCLUDE_SPOIL(1035),

	/**
	 * ID: 1036<br>
	 * Message: You have exceeded the quantity that can be inputted.
	 */
	YOU_HAVE_EXCEEDED_QUANTITY_THAT_CAN_BE_INPUTTED(1036),

	/**
	 * ID: 1037<br>
	 * Message: $c1 manufactured $s2.
	 */
	C1_MANUFACTURED_S2(1037),

	/**
	 * ID: 1038<br>
	 * Message: $c1 manufactured $s3 $s2(s).
	 */
	C1_MANUFACTURED_S3_S2_S(1038),

	/**
	 * ID: 1039<br>
	 * Message: Items left at the clan hall warehouse can only be retrieved by
	 * the clan leader. Do you want to continue?
	 */
	ONLY_CLAN_LEADER_CAN_RETRIEVE_ITEMS_FROM_CLAN_WAREHOUSE(1039),

	/**
	 * ID: 1040<br>
	 * Message: Items sent by freight can be picked up from any Warehouse
	 * location. Do you want to continue?
	 */
	ITEMS_SENT_BY_FREIGHT_PICKED_UP_FROM_ANYWHERE(1040),

	/**
	 * ID: 1041<br>
	 * Message: The next seed purchase price is $s1 adena.
	 */
	THE_NEXT_SEED_PURCHASE_PRICE_IS_S1_ADENA(1041),

	/**
	 * ID: 1042<br>
	 * Message: The next farm goods purchase price is $s1 adena.
	 */
	THE_NEXT_FARM_GOODS_PURCHASE_PRICE_IS_S1_ADENA(1042),

	/**
	 * ID: 1043<br>
	 * Message: At the current time, the "/unstuck" command cannot be used.
	 * Please send in a petition.
	 */
	NO_UNSTUCK_PLEASE_SEND_PETITION(1043),

	/**
	 * ID: 1044<br>
	 * Message: Monster race payout information is not available while tickets
	 * are being sold.
	 */
	MONSRACE_NO_PAYOUT_INFO(1044),

	/**
	 * ID: 1046<br>
	 * Message: Monster race tickets are no longer available.
	 */
	MONSRACE_TICKETS_NOT_AVAILABLE(1046),

	/**
	 * ID: 1047<br>
	 * Message: We did not succeed in producing $s1 item.
	 */
	NOT_SUCCEED_PRODUCING_S1(1047),

	/**
	 * ID: 1048<br>
	 * Message: When "blocking" everything, whispering is not possible.
	 */
	NO_WHISPER_WHEN_BLOCKING(1048),

	/**
	 * ID: 1049<br>
	 * Message: When "blocking" everything, it is not possible to send
	 * invitations for organizing parties.
	 */
	NO_PARTY_WHEN_BLOCKING(1049),

	/**
	 * ID: 1050<br>
	 * Message: There are no communities in my clan. Clan communities are
	 * allowed for clans with skill levels of 2 and higher.
	 */
	NO_CB_IN_MY_CLAN(1050),

	/**
	 * ID: 1051 <br>
	 * Message: Payment for your clan hall has not been made please make payment
	 * tomorrow.
	 */
	PAYMENT_FOR_YOUR_CLAN_HALL_HAS_NOT_BEEN_MADE_PLEASE_MAKE_PAYMENT_TO_YOUR_CLAN_WAREHOUSE_BY_S1_TOMORROW(
			1051),

	/**
	 * ID: 1052 <br>
	 * Message: Payment of Clan Hall is overdue the owner loose Clan Hall.
	 */
	THE_CLAN_HALL_FEE_IS_ONE_WEEK_OVERDUE_THEREFORE_THE_CLAN_HALL_OWNERSHIP_HAS_BEEN_REVOKED(
			1052),

	/**
	 * ID: 1053<br>
	 * Message: It is not possible to resurrect in battlefields where a siege
	 * war is taking place.
	 */
	CANNOT_BE_RESURRECTED_DURING_SIEGE(1053),

	/**
	 * ID: 1054<br>
	 * Message: You have entered a mystical land.
	 */
	ENTERED_MYSTICAL_LAND(1054),

	/**
	 * ID: 1055<br>
	 * Message: You have left a mystical land.
	 */
	EXITED_MYSTICAL_LAND(1055),

	/**
	 * ID: 1056<br>
	 * Message: You have exceeded the storage capacity of the castle's vault.
	 */
	VAULT_CAPACITY_EXCEEDED(1056),

	/**
	 * ID: 1057<br>
	 * Message: This command can only be used in the relax server.
	 */
	RELAX_SERVER_ONLY(1057),

	/**
	 * ID: 1058<br>
	 * Message: The sales price for seeds is $s1 adena.
	 */
	THE_SALES_PRICE_FOR_SEEDS_IS_S1_ADENA(1058),

	/**
	 * ID: 1059<br>
	 * Message: The remaining purchasing amount is $s1 adena.
	 */
	THE_REMAINING_PURCHASING_IS_S1_ADENA(1059),

	/**
	 * ID: 1060<br>
	 * Message: The remainder after selling the seeds is $s1.
	 */
	THE_REMAINDER_AFTER_SELLING_THE_SEEDS_IS_S1(1060),

	/**
	 * ID: 1061<br>
	 * Message: The recipe cannot be registered. You do not have the ability to
	 * create items.
	 */
	CANT_REGISTER_NO_ABILITY_TO_CRAFT(1061),

	/**
	 * ID: 1062<br>
	 * Message: Writing something new is possible after level 10.
	 */
	WRITING_SOMETHING_NEW_POSSIBLE_AFTER_LEVEL_10(1062),

	/**
	 * ID: 1063<br>
	 * if you become trapped or unable to move, please use the '/unstuck'
	 * command.
	 */
	PETITION_UNAVAILABLE(1063),

	/**
	 * ID: 1064<br>
	 * Message: The equipment, +$s1 $s2, has been removed.
	 */
	EQUIPMENT_S1_S2_REMOVED(1064),

	/**
	 * ID: 1065<br>
	 * Message: While operating a private store or workshop, you cannot discard,
	 * destroy, or trade an item.
	 */
	CANNOT_TRADE_DISCARD_DROP_ITEM_WHILE_IN_SHOPMODE(1065),

	/**
	 * ID: 1066<br>
	 * Message: $s1 HP has been restored.
	 */
	S1_HP_RESTORED(1066),

	/**
	 * ID: 1067<br>
	 * Message: $s2 HP has been restored by $c1
	 */
	S2_HP_RESTORED_BY_C1(1067),

	/**
	 * ID: 1068<br>
	 * Message: $s1 MP has been restored.
	 */
	S1_MP_RESTORED(1068),

	/**
	 * ID: 1069<br>
	 * Message: $s2 MP has been restored by $c1.
	 */
	S2_MP_RESTORED_BY_C1(1069),

	/**
	 * ID: 1070<br>
	 * Message: You do not have 'read' permission.
	 */
	NO_READ_PERMISSION(1070),

	/**
	 * ID: 1071<br>
	 * Message: You do not have 'write' permission.
	 */
	NO_WRITE_PERMISSION(1071),

	/**
	 * ID: 1072<br>
	 * Message: You have obtained a ticket for the Monster Race #$s1 - Single
	 */
	OBTAINED_TICKET_FOR_MONS_RACE_S1_SINGLE(1072),

	/**
	 * ID: 1073<br>
	 * Message: You have obtained a ticket for the Monster Race #$s1 - Single
	 */
	OBTAINED_TICKET_FOR_MONS_RACE_S1_SINGLE_(1073),

	/**
	 * ID: 1074<br>
	 * Message: You do not meet the age requirement to purchase a Monster Race
	 * Ticket.
	 */
	NOT_MEET_AGE_REQUIREMENT_FOR_MONS_RACE(1074),

	/**
	 * ID: 1075<br>
	 * Message: The bid amount must be higher than the previous bid.
	 */
	BID_AMOUNT_HIGHER_THAN_PREVIOUS_BID(1075),

	/**
	 * ID: 1076<br>
	 * Message: The game cannot be terminated at this time.
	 */
	GAME_CANNOT_TERMINATE_NOW(1076),

	/**
	 * ID: 1077<br>
	 * Message: A GameGuard Execution error has occurred. Please send the *.erl
	 * file(s) located in the GameGuard folder to game@inca.co.kr
	 */
	GG_EXECUTION_ERROR(1077),

	/**
	 * ID: 1078<br>
	 * Message: When a user's keyboard input exceeds a certain cumulative score
	 * a chat ban will be applied. This is done to discourage spamming. Please
	 * avoid posting the same message multiple times during a short period.
	 */
	DONT_SPAM(1078),

	/**
	 * ID: 1079<br>
	 * Message: The target is currently banend from chatting.
	 */
	TARGET_IS_CHAT_BANNED(1079),

	/**
	 * ID: 1080<br>
	 * Message: Being permanent, are you sure you wish to use the facelift
	 * potion - Type A?
	 */
	FACELIFT_POTION_TYPE_A(1080),

	/**
	 * ID: 1081<br>
	 * Message: Being permanent, are you sure you wish to use the hair dye
	 * potion - Type A?
	 */
	HAIRDYE_POTION_TYPE_A(1081),

	/**
	 * ID: 1082<br>
	 * Message: Do you wish to use the hair style change potion - Type A? It is
	 * permanent.
	 */
	HAIRSTYLE_POTION_TYPE_A(1082),

	/**
	 * ID: 1083<br>
	 * Message: Facelift potion - Type A is being applied.
	 */
	FACELIFT_POTION_TYPE_A_APPLIED(1083),

	/**
	 * ID: 1084<br>
	 * Message: Hair dye potion - Type A is being applied.
	 */
	HAIRDYE_POTION_TYPE_A_APPLIED(1084),

	/**
	 * ID: 1085<br>
	 * Message: The hair style chance potion - Type A is being used.
	 */
	HAIRSTYLE_POTION_TYPE_A_USED(1085),

	/**
	 * ID: 1086<br>
	 * Message: Your facial appearance has been changed.
	 */
	FACE_APPEARANCE_CHANGED(1086),

	/**
	 * ID: 1087<br>
	 * Message: Your hair color has changed.
	 */
	HAIR_COLOR_CHANGED(1087),

	/**
	 * ID: 1088<br>
	 * Message: Your hair style has been changed.
	 */
	HAIR_STYLE_CHANGED(1088),

	/**
	 * ID: 1089<br>
	 * Message: $c1 has obtained a first anniversary commemorative item.
	 */
	C1_OBTAINED_ANNIVERSARY_ITEM(1089),

	/**
	 * ID: 1090<br>
	 * Message: Being permanent, are you sure you wish to use the facelift
	 * potion - Type B?
	 */
	FACELIFT_POTION_TYPE_B(1090),

	/**
	 * ID: 1091<br>
	 * Message: Being permanent, are you sure you wish to use the facelift
	 * potion - Type C?
	 */
	FACELIFT_POTION_TYPE_C(1091),

	/**
	 * ID: 1092<br>
	 * Message: Being permanent, are you sure you wish to use the hair dye
	 * potion - Type B?
	 */
	HAIRDYE_POTION_TYPE_B(1092),

	/**
	 * ID: 1093<br>
	 * Message: Being permanent, are you sure you wish to use the hair dye
	 * potion - Type C?
	 */
	HAIRDYE_POTION_TYPE_C(1093),

	/**
	 * ID: 1094<br>
	 * Message: Being permanent, are you sure you wish to use the hair dye
	 * potion - Type D?
	 */
	HAIRDYE_POTION_TYPE_D(1094),

	/**
	 * ID: 1095<br>
	 * Message: Do you wish to use the hair style change potion - Type B? It is
	 * permanent.
	 */
	HAIRSTYLE_POTION_TYPE_B(1095),

	/**
	 * ID: 1096<br>
	 * Message: Do you wish to use the hair style change potion - Type C? It is
	 * permanent.
	 */
	HAIRSTYLE_POTION_TYPE_C(1096),

	/**
	 * ID: 1097<br>
	 * Message: Do you wish to use the hair style change potion - Type D? It is
	 * permanent.
	 */
	HAIRSTYLE_POTION_TYPE_D(1097),

	/**
	 * ID: 1098<br>
	 * Message: Do you wish to use the hair style change potion - Type E? It is
	 * permanent.
	 */
	HAIRSTYLE_POTION_TYPE_E(1098),

	/**
	 * ID: 1099<br>
	 * Message: Do you wish to use the hair style change potion - Type F? It is
	 * permanent.
	 */
	HAIRSTYLE_POTION_TYPE_F(1099),

	/**
	 * ID: 1100<br>
	 * Message: Do you wish to use the hair style change potion - Type G? It is
	 * permanent.
	 */
	HAIRSTYLE_POTION_TYPE_G(1100),

	/**
	 * ID: 1101<br>
	 * Message: Facelift potion - Type B is being applied.
	 */
	FACELIFT_POTION_TYPE_B_APPLIED(1101),

	/**
	 * ID: 1102<br>
	 * Message: Facelift potion - Type C is being applied.
	 */
	FACELIFT_POTION_TYPE_C_APPLIED(1102),

	/**
	 * ID: 1103<br>
	 * Message: Hair dye potion - Type B is being applied.
	 */
	HAIRDYE_POTION_TYPE_B_APPLIED(1103),

	/**
	 * ID: 1104<br>
	 * Message: Hair dye potion - Type C is being applied.
	 */
	HAIRDYE_POTION_TYPE_C_APPLIED(1104),

	/**
	 * ID: 1105<br>
	 * Message: Hair dye potion - Type D is being applied.
	 */
	HAIRDYE_POTION_TYPE_D_APPLIED(1105),

	/**
	 * ID: 1106<br>
	 * Message: The hair style chance potion - Type B is being used.
	 */
	HAIRSTYLE_POTION_TYPE_B_USED(1106),

	/**
	 * ID: 1107<br>
	 * Message: The hair style chance potion - Type C is being used.
	 */
	HAIRSTYLE_POTION_TYPE_C_USED(1107),

	/**
	 * ID: 1108<br>
	 * Message: The hair style chance potion - Type D is being used.
	 */
	HAIRSTYLE_POTION_TYPE_D_USED(1108),

	/**
	 * ID: 1109<br>
	 * Message: The hair style chance potion - Type E is being used.
	 */
	HAIRSTYLE_POTION_TYPE_E_USED(1109),

	/**
	 * ID: 1110<br>
	 * Message: The hair style chance potion - Type F is being used.
	 */
	HAIRSTYLE_POTION_TYPE_F_USED(1110),

	/**
	 * ID: 1111<br>
	 * Message: The hair style chance potion - Type G is being used.
	 */
	HAIRSTYLE_POTION_TYPE_G_USED(1111),

	/**
	 * ID: 1112<br>
	 * Message: The prize amount for the winner of Lottery #$s1 is $s2 adena. We
	 * have $s3 first prize winners.
	 */
	AMOUNT_FOR_WINNER_S1_IS_S2_ADENA_WE_HAVE_S3_PRIZE_WINNER(1112),

	/**
	 * ID: 1113<br>
	 * Message: The prize amount for Lucky Lottery #$s1 is $s2 adena. There was
	 * no first prize winner in this drawing, therefore the jackpot will be
	 * added to the next drawing.
	 */
	AMOUNT_FOR_LOTTERY_S1_IS_S2_ADENA_NO_WINNER(1113),

	/**
	 * ID: 1114<br>
	 * Message: Your clan may not register to participate in a siege while under
	 * a grace period of the clan's dissolution.
	 */
	CANT_PARTICIPATE_IN_SIEGE_WHILE_DISSOLUTION_IN_PROGRESS(263),

	/**
	 * ID: 1115<br>
	 * Message: Individuals may not surrender during combat.
	 */
	INDIVIDUALS_NOT_SURRENDER_DURING_COMBAT(1115),

	/**
	 * ID: 1116<br>
	 * Message: One cannot leave one's clan during combat.
	 */
	YOU_CANNOT_LEAVE_DURING_COMBAT(1116),

	/**
	 * ID: 1117<br>
	 * Message: A clan member may not be dismissed during combat.
	 */
	CLAN_MEMBER_CANNOT_BE_DISMISSED_DURING_COMBAT(1117),

	/**
	 * ID: 1118<br>
	 * Message: Progress in a quest is possible only when your inventory's
	 * weight and volume are less than 80 percent of capacity.
	 */
	INVENTORY_LESS_THAN_80_PERCENT(1118),

	/**
	 * ID: 1119<br>
	 * Message: Quest was automatically canceled when you attempted to settle
	 * the accounts of your quest while your inventory exceeded 80 percent of
	 * capacity.
	 */
	QUEST_CANCELED_INVENTORY_EXCEEDS_80_PERCENT(1119),

	/**
	 * ID: 1120<br>
	 * Message: You are still a member of the clan.
	 */
	STILL_CLAN_MEMBER(1120),

	/**
	 * ID: 1121<br>
	 * Message: You do not have the right to vote.
	 */
	NO_RIGHT_TO_VOTE(1121),

	/**
	 * ID: 1122<br>
	 * Message: There is no candidate.
	 */
	NO_CANDIDATE(1122),

	/**
	 * ID: 1123<br>
	 * Message: Weight and volume limit has been exceeded. That skill is
	 * currently unavailable.
	 */
	WEIGHT_EXCEEDED_SKILL_UNAVAILABLE(1123),

	/**
	 * ID: 1124<br>
	 * Message: A recipe book may not be used while using a skill.
	 */
	NO_RECIPE_BOOK_WHILE_CASTING(1124),

	/**
	 * ID: 1125<br>
	 * Message: An item may not be created while engaged in trading.
	 */
	CANNOT_CREATED_WHILE_ENGAGED_IN_TRADING(1125),

	/**
	 * ID: 1126<br>
	 * Message: You cannot enter a negative number.
	 */
	NO_NEGATIVE_NUMBER(1126),

	/**
	 * ID: 1127<br>
	 * Message: The reward must be less than 10 times the standard price.
	 */
	REWARD_LESS_THAN_10_TIMES_STANDARD_PRICE(1127),

	/**
	 * ID: 1128<br>
	 * Message: A private store may not be opened while using a skill.
	 */
	PRIVATE_STORE_NOT_WHILE_CASTING(1128),

	/**
	 * ID: 1129<br>
	 * Message: This is not allowed while riding a ferry or boat.
	 */
	NOT_ALLOWED_ON_BOAT(1129),

	/**
	 * ID: 1130<br>
	 * Message: You have given $s1 damage to your target and $s2 damage to the
	 * servitor.
	 */
	GIVEN_S1_DAMAGE_TO_YOUR_TARGET_AND_S2_DAMAGE_TO_SERVITOR(1130),

	/**
	 * ID: 1131<br>
	 * Message: It is now midnight and the effect of $s1 can be felt.
	 */
	NIGHT_EFFECT_APPLIES(1131),

	/**
	 * ID: 1132<br>
	 * Message: It is now dawn and the effect of $s1 will now disappear.
	 */
	DAY_EFFECT_DISAPPEARS(1132),

	/**
	 * ID: 1133<br>
	 * Message: Since HP has decreased, the effect of $s1 can be felt.
	 */
	HP_DECREASED_EFFECT_APPLIES(1133),

	/**
	 * ID: 1134<br>
	 * Message: Since HP has increased, the effect of $s1 will disappear.
	 */
	HP_INCREASED_EFFECT_DISAPPEARS(1134),

	/**
	 * ID: 1135<br>
	 * Message: While you are engaged in combat, you cannot operate a private
	 * store or private workshop.
	 */
	CANT_OPERATE_PRIVATE_STORE_DURING_COMBAT(1135),

	/**
	 * ID: 1136<br>
	 * Message: Since there was an account that used this IP and attempted to
	 * log in illegally, this account is not allowed to connect to the game
	 * server for $s1 minutes. Please use another game server.
	 */
	ACCOUNT_NOT_ALLOWED_TO_CONNECT(1136),

	/**
	 * ID: 1137<br>
	 * Message: $c1 harvested $s3 $s2(s).
	 */
	C1_HARVESTED_S3_S2S(1137),

	/**
	 * ID: 1138<br>
	 * Message: $c1 harvested $s2(s).
	 */
	C1_HARVESTED_S2S(1138),

	/**
	 * ID: 1139<br>
	 * Message: The weight and volume limit of your inventory must not be
	 * exceeded.
	 */
	INVENTORY_LIMIT_MUST_NOT_BE_EXCEEDED(1139),

	/**
	 * ID: 1140<br>
	 * Message: Would you like to open the gate?
	 */
	WOULD_YOU_LIKE_TO_OPEN_THE_GATE(1140),

	/**
	 * ID: 1141<br>
	 * Message: Would you like to close the gate?
	 */
	WOULD_YOU_LIKE_TO_CLOSE_THE_GATE(1141),

	/**
	 * ID: 1142<br>
	 * Message: Since $s1 already exists nearby, you cannot summon it again.
	 */
	CANNOT_SUMMON_S1_AGAIN(1142),

	/**
	 * ID: 1143<br>
	 * Message: Since you do not have enough items to maintain the servitor's
	 * stay, the servitor will disappear.
	 */
	SERVITOR_DISAPPEARED_NOT_ENOUGH_ITEMS(351),

	/**
	 * ID: 1144<br>
	 * Message: Currently, you don't have anybody to chat with in the game.
	 */
	NOBODY_IN_GAME_TO_CHAT(1144),

	/**
	 * ID: 1145<br>
	 * Message: $s2 has been created for $c1 after the payment of $s3 adena is
	 * received.
	 */
	S2_CREATED_FOR_C1_FOR_S3_ADENA(1145),

	/**
	 * ID: 1146<br>
	 * Message: $c1 created $s2 after receiving $s3 adena.
	 */
	C1_CREATED_S2_FOR_S3_ADENA(1146),

	/**
	 * ID: 1147<br>
	 * Message: $s2 $s3 have been created for $c1 at the price of $s4 adena.
	 */
	S2_S3_S_CREATED_FOR_C1_FOR_S4_ADENA(1147),

	/**
	 * ID: 1148<br>
	 * Message: $c1 created $s2 $s3 at the price of $s4 adena.
	 */
	C1_CREATED_S2_S3_S_FOR_S4_ADENA(1148),

	/**
	 * ID: 1149<br>
	 * Message: Your attempt to create $s2 for $c1 at the price of $s3 adena has
	 * failed.
	 */
	CREATION_OF_S2_FOR_C1_AT_S3_ADENA_FAILED(1149),

	/**
	 * ID: 1150<br>
	 * Message: $c1 has failed to create $s2 at the price of $s3 adena.
	 */
	C1_FAILED_TO_CREATE_S2_FOR_S3_ADENA(1150),

	/**
	 * ID: 1151<br>
	 * Message: $s2 is sold to $c1 at the price of $s3 adena.
	 */
	S2_SOLD_TO_C1_FOR_S3_ADENA(1151),

	/**
	 * ID: 1152<br>
	 * Message: $s2 $s3 have been sold to $c1 for $s4 adena.
	 */
	S3_S2_S_SOLD_TO_C1_FOR_S4_ADENA(1152),

	/**
	 * ID: 1153<br>
	 * Message: $s2 has been purchased from $c1 at the price of $s3 adena.
	 */
	S2_PURCHASED_FROM_C1_FOR_S3_ADENA(1153),

	/**
	 * ID: 1154<br>
	 * Message: $s3 $s2 has been purchased from $c1 for $s4 adena.
	 */
	S3_S2_S_PURCHASED_FROM_C1_FOR_S4_ADENA(1154),

	/**
	 * ID: 1155<br>
	 * Message: +$s2 $s3 have been sold to $c1 for $s4 adena.
	 */
	S3_S2_SOLD_TO_C1_FOR_S4_ADENA(1155),

	/**
	 * ID: 1156<br>
	 * Message: +$s2 $s3 has been purchased from $c1 for $s4 adena.
	 */
	S2_S3_PURCHASED_FROM_C1_FOR_S4_ADENA(1156),

	/**
	 * ID: 1157<br>
	 * Message: Trying on state lasts for only 5 seconds. When a character's
	 * state changes, it can be cancelled.
	 */
	TRYING_ON_STATE(1157),

	/**
	 * ID: 1158<br>
	 * Message: You cannot dismount from this elevation.
	 */
	CANNOT_DISMOUNT_FROM_ELEVATION(1158),

	/**
	 * ID: 1159<br>
	 * Message: The ferry from Talking Island will arrive at Gludin Harbor in
	 * approximately 10 minutes.
	 */
	FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_10_MINUTES(1159),

	/**
	 * ID: 1160<br>
	 * Message: The ferry from Talking Island will be arriving at Gludin Harbor
	 * in approximately 5 minutes.
	 */
	FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_5_MINUTES(1160),

	/**
	 * ID: 1161<br>
	 * Message: The ferry from Talking Island will be arriving at Gludin Harbor
	 * in approximately 1 minute.
	 */
	FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_1_MINUTE(1161),

	/**
	 * ID: 1162<br>
	 * Message: The ferry from Giran Harbor will be arriving at Talking Island
	 * in approximately 15 minutes.
	 */
	FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_15_MINUTES(1162),

	/**
	 * ID: 1163<br>
	 * Message: The ferry from Giran Harbor will be arriving at Talking Island
	 * in approximately 10 minutes.
	 */
	FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_10_MINUTES(1163),

	/**
	 * ID: 1164<br>
	 * Message: The ferry from Giran Harbor will be arriving at Talking Island
	 * in approximately 5 minutes.
	 */
	FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_5_MINUTES(1164),

	/**
	 * ID: 1165<br>
	 * Message: The ferry from Giran Harbor will be arriving at Talking Island
	 * in approximately 1 minute.
	 */
	FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_1_MINUTE(1165),

	/**
	 * ID: 1166<br>
	 * Message: The ferry from Talking Island will be arriving at Giran Harbor
	 * in approximately 20 minutes.
	 */
	FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_20_MINUTES(1166),

	/**
	 * ID: 1167<br>
	 * Message: The ferry from Talking Island will be arriving at Giran Harbor
	 * in approximately 20 minutes.
	 */
	FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_15_MINUTES(1167),

	/**
	 * ID: 1168<br>
	 * Message: The ferry from Talking Island will be arriving at Giran Harbor
	 * in approximately 20 minutes.
	 */
	FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_10_MINUTES(1168),

	/**
	 * ID: 1169<br>
	 * Message: The ferry from Talking Island will be arriving at Giran Harbor
	 * in approximately 20 minutes.
	 */
	FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_5_MINUTES(1169),

	/**
	 * ID: 1170<br>
	 * Message: The ferry from Talking Island will be arriving at Giran Harbor
	 * in approximately 1 minute.
	 */
	FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_1_MINUTE(1170),

	/**
	 * ID: 1171<br>
	 * Message: The Innadril pleasure boat will arrive in approximately 20
	 * minutes.
	 */
	INNADRIL_BOAT_ARRIVE_20_MINUTES(1171),

	/**
	 * ID: 1172<br>
	 * Message: The Innadril pleasure boat will arrive in approximately 15
	 * minutes.
	 */
	INNADRIL_BOAT_ARRIVE_15_MINUTES(1172),

	/**
	 * ID: 1173<br>
	 * Message: The Innadril pleasure boat will arrive in approximately 10
	 * minutes.
	 */
	INNADRIL_BOAT_ARRIVE_10_MINUTES(1173),

	/**
	 * ID: 1174<br>
	 * Message: The Innadril pleasure boat will arrive in approximately 5
	 * minutes.
	 */
	INNADRIL_BOAT_ARRIVE_5_MINUTES(1174),

	/**
	 * ID: 1175<br>
	 * Message: The Innadril pleasure boat will arrive in approximately 1
	 * minute.
	 */
	INNADRIL_BOAT_ARRIVE_1_MINUTE(1175),

	/**
	 * ID: 1176<br>
	 * Message: This is a quest event period.
	 */
	QUEST_EVENT_PERIOD(1176),

	/**
	 * ID: 1177<br>
	 * Message: This is the seal validation period.
	 */
	VALIDATION_PERIOD(1177),

	/**
	 * ID: 1178<br>
	 * <Seal of Avarice description>
	 */
	AVARICE_DESCRIPTION(1178),

	/**
	 * ID: 1179<br>
	 * <Seal of Gnosis description>
	 */
	GNOSIS_DESCRIPTION(1179),

	/**
	 * ID: 1180<br>
	 * <Seal of Strife description>
	 */
	STRIFE_DESCRIPTION(1180),

	/**
	 * ID: 1181<br>
	 * Message: Do you really wish to change the title?
	 */
	CHANGE_TITLE_CONFIRM(1181),

	/**
	 * ID: 1182<br>
	 * Message: Are you sure you wish to delete the clan crest?
	 */
	CREST_DELETE_CONFIRM(1182),

	/**
	 * ID: 1183<br>
	 * Message: This is the initial period.
	 */
	INITIAL_PERIOD(1183),

	/**
	 * ID: 1184<br>
	 * Message: This is a period of calculating statistics in the server.
	 */
	RESULTS_PERIOD(1184),

	/**
	 * ID: 1185<br>
	 * Message: days left until deletion.
	 */
	DAYS_LEFT_UNTIL_DELETION(1185),

	/**
	 * ID: 1186<br>
	 * Message: To create a new account, please visit the PlayNC website
	 * (http://www.plaync.com/us/support/)
	 */
	TO_CREATE_ACCOUNT_VISIT_WEBSITE(1186),

	/**
	 * ID: 1187<br>
	 * Message: If you forgotten your account information or password, please
	 * visit the Support Center on the PlayNC
	 * website(http://www.plaync.com/us/support/)
	 */
	ACCOUNT_INFORMATION_FORGOTTON_VISIT_WEBSITE(1187),

	/**
	 * ID: 1188<br>
	 * Message: Your selected target can no longer receive a recommendation.
	 */
	YOUR_TARGET_NO_LONGER_RECEIVE_A_RECOMMENDATION(1188),

	/**
	 * ID: 1189<br>
	 * Message: This temporary alliance of the Castle Attacker team is in
	 * effect. It will be dissolved when the Castle Lord is replaced.
	 */
	TEMPORARY_ALLIANCE(1189),

	/**
	 * ID: 1189<br>
	 * Message: This temporary alliance of the Castle Attacker team has been
	 * dissolved.
	 */
	TEMPORARY_ALLIANCE_DISSOLVED(1190),

	/**
	 * ID: 1191<br>
	 * Message: The ferry from Gludin Harbor will be arriving at Talking Island
	 * in approximately 10 minutes.
	 */
	FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_10_MINUTES(1191),

	/**
	 * ID: 1192<br>
	 * Message: The ferry from Gludin Harbor will be arriving at Talking Island
	 * in approximately 5 minutes.
	 */
	FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_5_MINUTES(1192),

	/**
	 * ID: 1193<br>
	 * Message: The ferry from Gludin Harbor will be arriving at Talking Island
	 * in approximately 1 minute.
	 */
	FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_1_MINUTE(1193),

	/**
	 * ID: 1194<br>
	 * Message: A mercenary can be assigned to a position from the beginning of
	 * the Seal Validatio period until the time when a siege starts.
	 */
	MERC_CAN_BE_ASSIGNED(1194),

	/**
	 * ID: 1195<br>
	 * Message: This mercenary cannot be assigned to a position by using the
	 * Seal of Strife.
	 */
	MERC_CANT_BE_ASSIGNED_USING_STRIFE(1195),

	/**
	 * ID: 1196<br>
	 * Message: Your force has reached maximum capacity.
	 */
	FORCE_MAXIMUM(1196),

	/**
	 * ID: 1197<br>
	 * Message: Summoning a servitor costs $s2 $s1.
	 */
	SUMMONING_SERVITOR_COSTS_S2_S1(1197),

	/**
	 * ID: 1198<br>
	 * Message: The item has been successfully crystallized.
	 */
	CRYSTALLIZATION_SUCCESSFUL(1198),

	/**
	 * ID: 1199<br>
	 * Message: =======<Clan War Target>=======
	 */
	CLAN_WAR_HEADER(1199),

	/**
	 * ID: 1200<br>
	 * Message:($s1 ($s2 Alliance)
	 */
	S1_S2_ALLIANCE(1200),

	/**
	 * ID: 1201<br>
	 * Message: Please select the quest you wish to abort.
	 */
	SELECT_QUEST_TO_ABOR(1201),

	/**
	 * ID: 1202<br>
	 * Message:($s1 (No alliance exists)
	 */
	S1_NO_ALLI_EXISTS(1202),

	/**
	 * ID: 1203<br>
	 * Message: There is no clan war in progress.
	 */
	NO_WAR_IN_PROGRESS(1203),

	/**
	 * ID: 1204<br>
	 * Message: The screenshot has been saved. ($s1 $s2x$s3)
	 */
	SCREENSHOT(1204),

	/**
	 * ID: 1205<br>
	 * Message: Your mailbox is full. There is a 100 message limit.
	 */
	MAILBOX_FULL(1205),

	/**
	 * ID: 1206<br>
	 * Message: The memo box is full. There is a 100 memo limit.
	 */
	MEMOBOX_FULL(1206),

	/**
	 * ID: 1207<br>
	 * Message: Please make an entry in the field.
	 */
	MAKE_AN_ENTRY(1207),

	/**
	 * ID: 1208<br>
	 * Message: $c1 died and dropped $s3 $s2.
	 */
	C1_DIED_DROPPED_S3_S2(1208),

	/**
	 * ID: 1209<br>
	 * Message: Congratulations. Your raid was successful.
	 */
	RAID_WAS_SUCCESSFUL(1209),

	/**
	 * ID: 1210<br>
	 * Message: Seven Signs: The quest event period has begun. Visit a Priest of
	 * Dawn or Priestess of Dusk to participate in the event.
	 */
	QUEST_EVENT_PERIOD_BEGUN(1210),

	/**
	 * ID: 1211<br>
	 * Message: Seven Signs: The quest event period has ended. The next quest
	 * event will start in one week.
	 */
	QUEST_EVENT_PERIOD_ENDED(1211),

	/**
	 * ID: 1212<br>
	 * Message: Seven Signs: The Lords of Dawn have obtained the Seal of
	 * Avarice.
	 */
	DAWN_OBTAINED_AVARICE(1212),

	/**
	 * ID: 1213<br>
	 * Message: Seven Signs: The Lords of Dawn have obtained the Seal of Gnosis.
	 */
	DAWN_OBTAINED_GNOSIS(1213),

	/**
	 * ID: 1214<br>
	 * Message: Seven Signs: The Lords of Dawn have obtained the Seal of Strife.
	 */
	DAWN_OBTAINED_STRIFE(1214),

	/**
	 * ID: 1215<br>
	 * Message: Seven Signs: The Revolutionaries of Dusk have obtained the Seal
	 * of Avarice.
	 */
	DUSK_OBTAINED_AVARICE(1215),

	/**
	 * ID: 1216<br>
	 * Message: Seven Signs: The Revolutionaries of Dusk have obtained the Seal
	 * of Gnosis.
	 */
	DUSK_OBTAINED_GNOSIS(1216),

	/**
	 * ID: 1217<br>
	 * Message: Seven Signs: The Revolutionaries of Dusk have obtained the Seal
	 * of Strife.
	 */
	DUSK_OBTAINED_STRIFE(1217),

	/**
	 * ID: 1218<br>
	 * Message: Seven Signs: The Seal Validation period has begun.
	 */
	SEAL_VALIDATION_PERIOD_BEGUN(1218),

	/**
	 * ID: 1219<br>
	 * Message: Seven Signs: The Seal Validation period has ended.
	 */
	SEAL_VALIDATION_PERIOD_ENDED(1219),

	/**
	 * ID: 1220<br>
	 * Message: Are you sure you wish to summon it?
	 */
	SUMMON_CONFIRM(1220),

	/**
	 * ID: 1221<br>
	 * Message: Are you sure you wish to return it?
	 */
	RETURN_CONFIRM(1221),

	/**
	 * ID: 1222<br>
	 * Message: Current location : $s1, $s2, $s3 (GM Consultation Service)
	 */
	LOC_GM_CONSULATION_SERVICE_S1_S2_S3(1222),

	/**
	 * ID: 1223<br>
	 * Message: We depart for Talking Island in five minutes.
	 */
	DEPART_FOR_TALKING_5_MINUTES(1223),

	/**
	 * ID: 1224<br>
	 * Message: We depart for Talking Island in one minute.
	 */
	DEPART_FOR_TALKING_1_MINUTE(1224),

	/**
	 * ID: 1225<br>
	 * Message: All aboard for Talking Island
	 */
	DEPART_FOR_TALKING(1225),

	/**
	 * ID: 1226<br>
	 * Message: We are now leaving for Talking Island.
	 */
	LEAVING_FOR_TALKING(1226),

	/**
	 * ID: 1227<br>
	 * Message: You have $s1 unread messages.
	 */
	S1_UNREAD_MESSAGES(1227),

	/**
	 * ID: 1228<br>
	 * Message: $c1 has blocked you. You cannot send mail to $c1.
	 */
	C1_BLOCKED_YOU_CANNOT_MAIL(1228),

	/**
	 * ID: 1229<br>
	 * Message: No more messages may be sent at this time. Each account is
	 * allowed 10 messages per day.
	 */
	NO_MORE_MESSAGES_TODAY(1229),

	/**
	 * ID: 1230<br>
	 * Message: You are limited to five recipients at a time.
	 */
	ONLY_FIVE_RECIPIENTS(1230),

	/**
	 * ID: 1231<br>
	 * Message: You've sent mail.
	 */
	SENT_MAIL(1231),

	/**
	 * ID: 1232<br>
	 * Message: The message was not sent.
	 */
	MESSAGE_NOT_SENT(1232),

	/**
	 * ID: 1233<br>
	 * Message: You've got mail.
	 */
	NEW_MAIL(1233),

	/**
	 * ID: 1234<br>
	 * Message: The mail has been stored in your temporary mailbox.
	 */
	MAIL_STORED_IN_MAILBOX(1234),

	/**
	 * ID: 1235<br>
	 * Message: Do you wish to delete all your friends?
	 */
	ALL_FRIENDS_DELETE_CONFIRM(1235),

	/**
	 * ID: 1236<br>
	 * Message: Please enter security card number.
	 */
	ENTER_SECURITY_CARD_NUMBER(1236),

	/**
	 * ID: 1237<br>
	 * Message: Please enter the card number for number $s1.
	 */
	ENTER_CARD_NUMBER_FOR_S1(1237),

	/**
	 * ID: 1238<br>
	 * Message: Your temporary mailbox is full. No more mail can be stored, you
	 * have reached the 10 message limit.
	 */
	TEMP_MAILBOX_FULL(1205),

	/**
	 * ID: 1239<br>
	 * Message: The keyboard security module has failed to load. Please exit the
	 * game and try again.
	 */
	KEYBOARD_MODULE_FAILED_LOAD(1239),

	/**
	 * ID: 1240<br>
	 * Message: Seven Signs: The Revolutionaries of Dusk have won.
	 */
	DUSK_WON(1240),

	/**
	 * ID: 1241<br>
	 * Message: Seven Signs: The Lords of Dawn have won.
	 */
	DAWN_WON(1241),

	/**
	 * ID: 1242<br>
	 * Message: Users who have not verified their age may not log in between the
	 * hours if 10:00 p.m. and 6:00 a.m.
	 */
	NOT_VERIFIED_AGE_NO_LOGIN(1242),

	/**
	 * ID: 1243<br>
	 * Message: The security card number is invalid.
	 */
	SECURITY_CARD_NUMBER_INVALID(1243),

	/**
	 * ID: 1244<br>
	 * Message: Users who have not verified their age may not log in between the
	 * hours if 10:00 p.m. and 6:00 a.m. Logging off now
	 */
	NOT_VERIFIED_AGE_LOG_OFF(1244),

	/**
	 * ID: 1245<br>
	 * Message: You will be loged out in $s1 minutes.
	 */
	LOGOUT_IN_S1_MINUTES(1245),

	/**
	 * ID: 1246<br>
	 * Message: $c1 died and has dropped $s2 adena.
	 */
	C1_DIED_DROPPED_S2_ADENA(1246),

	/**
	 * ID: 1247<br>
	 * Message: The corpse is too old. The skill cannot be used.
	 */
	CORPSE_TOO_OLD_SKILL_NOT_USED(1247),

	/**
	 * ID: 1248<br>
	 * Message: You are out of feed. Mount status canceled.
	 */
	OUT_OF_FEED_MOUNT_CANCELED(1248),

	/**
	 * ID: 1249<br>
	 * Message: You may only ride a wyvern while you're riding a strider.
	 */
	YOU_MAY_ONLY_RIDE_WYVERN_WHILE_RIDING_STRIDER(1249),

	/**
	 * ID: 1250<br>
	 * Message: Do you really want to surrender? If you surrender during an
	 * alliance war, your Exp will drop the same as if you were to die once.
	 */
	SURRENDER_ALLY_WAR_CONFIRM(1250),

	/**
	 * ID: 1251<br>
	 * you will not be able to accept another clan to your alliance for one day.
	 */
	DISMISS_ALLY_CONFIRM(1251),

	/**
	 * ID: 1252<br>
	 * Message: Are you sure you want to surrender? Exp penalty will be the same
	 * as death.
	 */
	SURRENDER_CONFIRM1(1252),

	/**
	 * ID: 1253<br>
	 * Message: Are you sure you want to surrender? Exp penalty will be the same
	 * as death and you will not be allowed to participate in clan war.
	 */
	SURRENDER_CONFIRM2(1253),

	/**
	 * ID: 1254<br>
	 * Message: Thank you for submitting feedback.
	 */
	THANKS_FOR_FEEDBACK(1254),

	/**
	 * ID: 1255<br>
	 * Message: GM consultation has begun.
	 */
	GM_CONSULTATION_BEGUN(1255),

	/**
	 * ID: 1256<br>
	 * Message: Please write the name after the command.
	 */
	PLEASE_WRITE_NAME_AFTER_COMMAND(1256),

	/**
	 * ID: 1257<br>
	 * Message: The special skill of a servitor or pet cannot be registerd as a
	 * macro.
	 */
	PET_SKILL_NOT_AS_MACRO(1257),

	/**
	 * ID: 1258<br>
	 * Message: $s1 has been crystallized
	 */
	S1_CRYSTALLIZED(1258),

	/**
	 * ID: 1259<br>
	 * Message: =======<Alliance Target>=======
	 */
	ALLIANCE_TARGET_HEADER(1259),

	/**
	 * ID: 1260<br>
	 * Message: Seven Signs: Preparations have begun for the next quest event.
	 */
	PREPARATIONS_PERIOD_BEGUN(1260),

	/**
	 * ID: 1261<br>
	 * Message: Seven Signs: The quest event period has begun. Speak with a
	 * Priest of Dawn or Dusk Priestess if you wish to participate in the event.
	 */
	COMPETITION_PERIOD_BEGUN(1261),

	/**
	 * ID: 1262<br>
	 * Message: Seven Signs: Quest event has ended. Results are being tallied.
	 */
	RESULTS_PERIOD_BEGUN(1262),

	/**
	 * ID: 1263<br>
	 * Message: Seven Signs: This is the seal validation period. A new quest
	 * event period begins next Monday.
	 */
	VALIDATION_PERIOD_BEGUN(1263),

	/**
	 * ID: 1264<br>
	 * Message: This soul stone cannot currently absorb souls. Absorption has
	 * failed.
	 */
	STONE_CANNOT_ABSORB(1264),

	/**
	 * ID: 1265<br>
	 * Message: You can't absorb souls without a soul stone.
	 */
	CANT_ABSORB_WITHOUT_STONE(1265),

	/**
	 * ID: 1266<br>
	 * Message: The exchange has ended.
	 */
	EXCHANGE_HAS_ENDED(1266),

	/**
	 * ID: 1267<br>
	 * Message: Your contribution score is increased by $s1.
	 */
	CONTRIB_SCORE_INCREASED_S1(1267),

	/**
	 * ID: 1268<br>
	 * Message: Do you wish to add class as your sub class?
	 */
	ADD_SUBCLASS_CONFIRM(1268),

	/**
	 * ID: 1269<br>
	 * Message: The new sub class has been added.
	 */
	ADD_NEW_SUBCLASS(1269),

	/**
	 * ID: 1270<br>
	 * Message: The transfer of sub class has been completed.
	 */
	SUBCLASS_TRANSFER_COMPLETED(1270),

	/**
	 * ID: 1271<br>
	 * Message: Do you wish to participate? Until the next seal validation
	 * period, you are a member of the Lords of Dawn.
	 */
	DAWN_CONFIRM(1271),

	/**
	 * ID: 1271<br>
	 * Message: Do you wish to participate? Until the next seal validation
	 * period, you are a member of the Revolutionaries of Dusk.
	 */
	DUSK_CONFIRM(1272),

	/**
	 * ID: 1273<br>
	 * Message: You will participate in the Seven Signs as a member of the Lords
	 * of Dawn.
	 */
	SEVENSIGNS_PARTECIPATION_DAWN(1273),

	/**
	 * ID: 1274<br>
	 * Message: You will participate in the Seven Signs as a member of the
	 * Revolutionaries of Dusk.
	 */
	SEVENSIGNS_PARTECIPATION_DUSK(1274),

	/**
	 * ID: 1275<br>
	 * Message: You've chosen to fight for the Seal of Avarice during this quest
	 * event period.
	 */
	FIGHT_FOR_AVARICE(1275),

	/**
	 * ID: 1276<br>
	 * Message: You've chosen to fight for the Seal of Gnosis during this quest
	 * event period.
	 */
	FIGHT_FOR_GNOSIS(1276),

	/**
	 * ID: 1277<br>
	 * Message: You've chosen to fight for the Seal of Strife during this quest
	 * event period.
	 */
	FIGHT_FOR_STRIFE(1277),

	/**
	 * ID: 1278<br>
	 * Message: The NPC server is not operating at this time.
	 */
	NPC_SERVER_NOT_OPERATING(1278),

	/**
	 * ID: 1279<br>
	 * Message: Contribution level has exceeded the limit. You may not continue.
	 */
	CONTRIB_SCORE_EXCEEDED(1279),

	/**
	 * ID: 1280<br>
	 * Message: Magic Critical Hit!
	 */
	CRITICAL_HIT_MAGIC(1280),

	/**
	 * ID: 1281<br>
	 * Message: Your excellent shield defense was a success!
	 */
	YOUR_EXCELLENT_SHIELD_DEFENSE_WAS_A_SUCCESS(1281),

	/**
	 * ID: 1282<br>
	 * Message: Your Karma has been changed to $s1
	 */
	YOUR_KARMA_HAS_BEEN_CHANGED_TO_S1(1282),

	/**
	 * ID: 1283<br>
	 * Message: The minimum frame option has been activated.
	 */
	MINIMUM_FRAME_ACTIVATED(1283),

	/**
	 * ID: 1284<br>
	 * Message: The minimum frame option has been deactivated.
	 */
	MINIMUM_FRAME_DEACTIVATED(1284),

	/**
	 * ID: 1285<br>
	 * Message: No inventory exists: You cannot purchase an item.
	 */
	NO_INVENTORY_CANNOT_PURCHASE(1285),

	/**
	 * ID: 1286<br>
	 * Message: (Until next Monday at 6:00 p.m.)
	 */
	UNTIL_MONDAY_6PM(1286),

	/**
	 * ID: 1287<br>
	 * Message: (Until today at 6:00 p.m.)
	 */
	UNTIL_TODAY_6PM(1287),

	/**
	 * ID: 1288<br>
	 * Message: If trends continue, $s1 will win and the seal will belong to:
	 */
	S1_WILL_WIN_COMPETITION(1288),

	/**
	 * ID: 1289<br>
	 * Message: (Until next Monday at 6:00 p.m.)
	 */
	SEAL_OWNED_10_MORE_VOTED(1289),

	/**
	 * ID: 1290<br>
	 * Message: Although the seal was not owned, since 35 percent or more people
	 * have voted.
	 */
	SEAL_NOT_OWNED_35_MORE_VOTED(1290),

	/**
	 * ID: 1291<br>
	 * because less than 10 percent of people have voted.
	 */
	SEAL_OWNED_10_LESS_VOTED(1291),

	/**
	 * ID: 1292<br>
	 * and since less than 35 percent of people have voted.
	 */
	SEAL_NOT_OWNED_35_LESS_VOTED(1292),

	/**
	 * ID: 1293<br>
	 * Message: If current trends continue, it will end in a tie.
	 */
	COMPETITION_WILL_TIE(1293),

	/**
	 * ID: 1294<br>
	 * Message: The competition has ended in a tie. Therefore, nobody has been
	 * awarded the seal.
	 */
	COMPETITION_TIE_SEAL_NOT_AWARDED(1294),

	/**
	 * ID: 1295<br>
	 * Message: Sub classes may not be created or changed while a skill is in
	 * use.
	 */
	SUBCLASS_NO_CHANGE_OR_CREATE_WHILE_SKILL_IN_USE(1295),

	/**
	 * ID: 1296<br>
	 * Message: You cannot open a Private Store here.
	 */
	NO_PRIVATE_STORE_HERE(1296),

	/**
	 * ID: 1297<br>
	 * Message: You cannot open a Private Workshop here.
	 */
	NO_PRIVATE_WORKSHOP_HERE(1297),

	/**
	 * ID: 1298<br>
	 * Message: Please confirm that you would like to exit the Monster Race
	 * Track.
	 */
	MONS_EXIT_CONFIRM(1298),

	/**
	 * ID: 1299<br>
	 * Message: $c1's casting has been interrupted.
	 */
	C1_CASTING_INTERRUPTED(27),

	/**
	 * ID: 1300<br>
	 * Message: You are no longer trying on equipment.
	 */
	WEAR_ITEMS_STOPPED(1300),

	/**
	 * ID: 1301<br>
	 * Message: Only a Lord of Dawn may use this.
	 */
	CAN_BE_USED_BY_DAWN(1301),

	/**
	 * ID: 1302<br>
	 * Message: Only a Revolutionary of Dusk may use this.
	 */
	CAN_BE_USED_BY_DUSK(1302),

	/**
	 * ID: 1303<br>
	 * Message: This may only be used during the quest event period.
	 */
	CAN_BE_USED_DURING_QUEST_EVENT_PERIOD(1176),

	/**
	 * ID: 1304<br>
	 * except for an Alliance with a castle owning clan.
	 */
	STRIFE_CANCELED_DEFENSIVE_REGISTRATION(1304),

	/**
	 * ID: 1305<br>
	 * Message: Seal Stones may only be transferred during the quest event
	 * period.
	 */
	SEAL_STONES_ONLY_WHILE_QUEST(1305),

	/**
	 * ID: 1306<br>
	 * Message: You are no longer trying on equipment.
	 */
	NO_LONGER_TRYING_ON(1306),

	/**
	 * ID: 1307<br>
	 * Message: Only during the seal validation period may you settle your
	 * account.
	 */
	SETTLE_ACCOUNT_ONLY_IN_SEAL_VALIDATION(1307),

	/**
	 * ID: 1308<br>
	 * Message: Congratulations - You've completed a class transfer!
	 */
	CLASS_TRANSFER(1308),

	/**
	 * ID: 1309<br>
	 * Message:To use this option, you must have the lastest version of MSN
	 * Messenger installed on your computer.
	 */
	LATEST_MSN_REQUIRED(1309),

	/**
	 * ID: 1310<br>
	 * Message: For full functionality, the latest version of MSN Messenger must
	 * be installed on your computer.
	 */
	LATEST_MSN_RECOMMENDED(1310),

	/**
	 * ID: 1311<br>
	 * Message: Previous versions of MSN Messenger only provide the basic
	 * features for in-game MSN Messenger Chat. Add/Delete Contacts and other
	 * MSN Messenger options are not available
	 */
	MSN_ONLY_BASIC(1311),

	/**
	 * ID: 1312<br>
	 * Message: The latest version of MSN Messenger may be obtained from the MSN
	 * web site (http://messenger.msn.com).
	 */
	MSN_OBTAINED_FROM(1312),

	/**
	 * ID: 1313<br>
	 * Message: $s1, to better serve our customers, all chat histories [...]
	 */
	S1_CHAT_HISTORIES_STORED(1313),

	/**
	 * ID: 1314<br>
	 * Message: Please enter the passport ID of the person you wish to add to
	 * your contact list.
	 */
	ENTER_PASSPORT_FOR_ADDING(1314),

	/**
	 * ID: 1315<br>
	 * Message: Deleting a contact will remove that contact from MSN Messenger
	 * as well. The contact can still check your online status and well not be
	 * blocked from sending you a message.
	 */
	DELETING_A_CONTACT(1315),

	/**
	 * ID: 1316<br>
	 * Message: The contact will be deleted and blocked from your contact list.
	 */
	CONTACT_WILL_DELETED(1316),

	/**
	 * ID: 1317<br>
	 * Message: Would you like to delete this contact?
	 */
	CONTACT_DELETE_CONFIRM(1317),

	/**
	 * ID: 1318<br>
	 * Message: Please select the contact you want to block or unblock.
	 */
	SELECT_CONTACT_FOR_BLOCK_UNBLOCK(1318),

	/**
	 * ID: 1319<br>
	 * Message: Please select the name of the contact you wish to change to
	 * another group.
	 */
	SELECT_CONTACT_FOR_CHANGE_GROUP(1319),

	/**
	 * ID: 1320<br>
	 * Message: After selecting the group you wish to move your contact to,
	 * press the OK button.
	 */
	SELECT_GROUP_PRESS_OK(1320),

	/**
	 * ID: 1321<br>
	 * Message: Enter the name of the group you wish to add.
	 */
	ENTER_GROUP_NAME(1321),

	/**
	 * ID: 1322<br>
	 * Message: Select the group and enter the new name.
	 */
	SELECT_GROUP_ENTER_NAME(1322),

	/**
	 * ID: 1323<br>
	 * Message: Select the group you wish to delete and click the OK button.
	 */
	SELECT_GROUP_TO_DELETE(1323),

	/**
	 * ID: 1324<br>
	 * Message: Signing in...
	 */
	SIGNING_IN(1324),

	/**
	 * ID: 1325<br>
	 * Message: You've logged into another computer and have been logged out of
	 * the .NET Messenger Service on this computer.
	 */
	ANOTHER_COMPUTER_LOGOUT(1325),

	/**
	 * ID: 1326<br>
	 * Message: $s1 :
	 */
	S1_D(1326),

	/**
	 * ID: 1327<br>
	 * Message: The following message could not be delivered:
	 */
	MESSAGE_NOT_DELIVERED(1327),

	/**
	 * ID: 1328<br>
	 * Message: Members of the Revolutionaries of Dusk will not be resurrected.
	 */
	DUSK_NOT_RESURRECTED(1328),

	/**
	 * ID: 1329<br>
	 * Message: You are currently blocked from using the Private Store and
	 * Private Workshop.
	 */
	BLOCKED_FROM_USING_STORE(1329),

	/**
	 * ID: 1330<br>
	 * Message: You may not open a Private Store or Private Workshop for another
	 * $s1 minute(s)
	 */
	NO_STORE_FOR_S1_MINUTES(1330),

	/**
	 * ID: 1331<br>
	 * Message: You are no longer blocked from using the Private Store and
	 * Private Workshop
	 */
	NO_LONGER_BLOCKED_USING_STORE(1331),

	/**
	 * ID: 1332<br>
	 * Message: Items may not be used after your character or pet dies.
	 */
	NO_ITEMS_AFTER_DEATH(1332),

	/**
	 * ID: 1333<br>
	 * Message: The replay file is not accessible. Please verify that the
	 * replay.ini exists in your Linage 2 directory.
	 */
	REPLAY_INACCESSIBLE(1333),

	/**
	 * ID: 1334<br>
	 * Message: The new camera data has been stored.
	 */
	NEW_CAMERA_STORED(1334),

	/**
	 * ID: 1335<br>
	 * Message: The attempt to store the new camera data has failed.
	 */
	CAMERA_STORING_FAILED(1335),

	/**
	 * ID: 1336<br>
	 * Message: The replay file, $s1.$$s2 has been corrupted, please check the
	 * fle.
	 */
	REPLAY_S1_S2_CORRUPTED(1336),

	/**
	 * ID: 1337<br>
	 * Message: This will terminate the replay. Do you wish to continue?
	 */
	REPLAY_TERMINATE_CONFIRM(1337),

	/**
	 * ID: 1338<br>
	 * Message: You have exceeded the maximum amount that may be transferred at
	 * one time.
	 */
	EXCEEDED_MAXIMUM_AMOUNT(1338),

	/**
	 * ID: 1339<br>
	 * Message: Once a macro is assigned to a shortcut, it cannot be run as a
	 * macro again.
	 */
	MACRO_SHORTCUT_NOT_RUN(1339),

	/**
	 * ID: 1340<br>
	 * Message: This server cannot be accessed by the coupon you are using.
	 */
	SERVER_NOT_ACCESSED_BY_COUPON(1340),

	/**
	 * ID: 1341<br>
	 * Message: Incorrect name and/or email address.
	 */
	INCORRECT_NAME_OR_ADDRESS(1341),

	/**
	 * ID: 1342<br>
	 * Message: You are already logged in.
	 */
	ALREADY_LOGGED_IN(1342),

	/**
	 * ID: 1343<br>
	 * Message: Incorrect email address and/or password. Your attempt to log
	 * into .NET Messenger Service has failed.
	 */
	INCORRECT_ADDRESS_OR_PASSWORD(1343),

	/**
	 * ID: 1344<br>
	 * Message: Your request to log into the .NET Messenger service has failed.
	 * Please verify that you are currently connected to the internet.
	 */
	NET_LOGIN_FAILED(1344),

	/**
	 * ID: 1345<br>
	 * Message: Click the OK button after you have selected a contact name.
	 */
	SELECT_CONTACT_CLICK_OK(1345),

	/**
	 * ID: 1346<br>
	 * Message: You are currently entering a chat message.
	 */
	CURRENTLY_ENTERING_CHAT(1346),

	/**
	 * ID: 1347<br>
	 * Message: The Linage II messenger could not carry out the task you
	 * requested.
	 */
	MESSENGER_FAILED_CARRYING_OUT_TASK(1347),

	/**
	 * ID: 1348<br>
	 * Message: $s1 has entered the chat room.
	 */
	S1_ENTERED_CHAT_ROOM(1348),

	/**
	 * ID: 1349<br>
	 * Message: $s1 has left the chat room.
	 */
	S1_LEFT_CHAT_ROOM(1349),

	/**
	 * ID: 1350<br>
	 * Message: The state will be changed to indicate "off-line." All the chat
	 * windows currently opened will be closed.
	 */
	GOING_OFFLINE(1350),

	/**
	 * ID: 1351<br>
	 * Message: Click the Delete button after selecting the contact you wish to
	 * remove.
	 */
	SELECT_CONTACT_CLICK_REMOVE(1351),

	/**
	 * ID: 1352<br>
	 * Message: You have been added to $s1 ($s2)'s contact list.
	 */
	ADDED_TO_S1_S2_CONTACT_LIST(1352),

	/**
	 * ID: 1353<br>
	 * Message: You can set the option to show your status as always being
	 * off-line to all of your contacts.
	 */
	CAN_SET_OPTION_TO_ALWAYS_SHOW_OFFLINE(1353),

	/**
	 * ID: 1354<br>
	 * Message: You are not allowed to chat with a contact while chatting block
	 * is imposed.
	 */
	NO_CHAT_WHILE_BLOCKED(1354),

	/**
	 * ID: 1355<br>
	 * Message: The contact is currently blocked from chatting.
	 */
	CONTACT_CURRENTLY_BLOCKED(1355),

	/**
	 * ID: 1356<br>
	 * Message: The contact is not currently logged in.
	 */
	CONTACT_CURRENTLY_OFFLINE(1356),

	/**
	 * ID: 1357<br>
	 * Message: You have been blocked from chatting with that contact.
	 */
	YOU_ARE_BLOCKED(1357),

	/**
	 * ID: 1358<br>
	 * Message: You are being logged out...
	 */
	YOU_ARE_LOGGING_OUT(1358),

	/**
	 * ID: 1359<br>
	 * Message: $s1 has logged in.
	 */
	S1_LOGGED_IN2(1359),

	/**
	 * ID: 1360<br>
	 * Message: You have received a message from $s1.
	 */
	GOT_MESSAGE_FROM_S1(1360),

	/**
	 * ID: 1361<br>
	 * Message: Due to a system error, you have been logged out of the .NET
	 * Messenger Service.
	 */
	LOGGED_OUT_DUE_TO_ERROR(1361),

	/**
	 * ID: 1362<br>
	 * click the button next to My Status and then use the Options menu.
	 */
	SELECT_CONTACT_TO_DELETE(1362),

	/**
	 * ID: 1363<br>
	 * Message: Your request to participate in the alliance war has been denied.
	 */
	YOUR_REQUEST_ALLIANCE_WAR_DENIED(1363),

	/**
	 * ID: 1364<br>
	 * Message: The request for an alliance war has been rejected.
	 */
	REQUEST_ALLIANCE_WAR_REJECTED(1364),

	/**
	 * ID: 1365<br>
	 * Message: $s2 of $s1 clan has surrendered as an individual.
	 */
	S2_OF_S1_SURRENDERED_AS_INDIVIDUAL(1365),

	/**
	 * ID: 1366<br>
	 * Message: In order to delete a group, you must not [...]
	 */
	DELTE_GROUP_INSTRUCTION(1366),

	/**
	 * ID: 1367<br>
	 * Message: Only members of the group are allowed to add records.
	 */
	ONLY_GROUP_CAN_ADD_RECORDS(1367),

	/**
	 * ID: 1368<br>
	 * Message: You can not try those items on at the same time.
	 */
	YOU_CAN_NOT_TRY_THOSE_ITEMS_ON_AT_THE_SAME_TIME(1368),

	/**
	 * ID: 1369<br>
	 * Message: You've exceeded the maximum.
	 */
	EXCEEDED_THE_MAXIMUM(1369),

	/**
	 * ID: 1370<br>
	 * Message: Your message to $c1 did not reach its recipient. You cannot send
	 * mail to the GM staff.
	 */
	CANNOT_MAIL_GM_C1(704),

	/**
	 * ID: 1371<br>
	 * Message: It has been determined that you're not engaged in normal
	 * gameplay and a restriction has been imposed upon you. You may not move
	 * for $s1 minutes.
	 */
	GAMEPLAY_RESTRICTION_PENALTY_S1(1371),

	/**
	 * ID: 1372<br>
	 * Message: Your punishment will continue for $s1 minutes.
	 */
	PUNISHMENT_CONTINUE_S1_MINUTES(1372),

	/**
	 * ID: 1373<br>
	 * Message: $c1 has picked up $s2 that was dropped by a Raid Boss.
	 */
	C1_PICKED_UP_S2_FROM_RAIDBOSS(1373),

	/**
	 * ID: 1374<br>
	 * Message: $c1 has picked up $s3 $s2(s) that was dropped by a Raid Boss.
	 */
	C1_PICKED_UP_S3_S2_S_FROM_RAIDBOSS(1374),

	/**
	 * ID: 1375<br>
	 * Message: $c1 has picked up $s2 adena that was dropped by a Raid Boss.
	 */
	C1_PICKED_UP_S2_ADENA_FROM_RAIDBOSS(1375),

	/**
	 * ID: 1376<br>
	 * Message: $c1 has picked up $s2 that was dropped by another character.
	 */
	C1_PICKED_UP_S2_FROM_ANOTHER_CHARACTER(1376),

	/**
	 * ID: 1377<br>
	 * Message: $c1 has picked up $s3 $s2(s) that was dropped by a another
	 * character.
	 */
	C1_PICKED_UP_S3_S2_S_FROM_ANOTHER_CHARACTER(1377),

	/**
	 * ID: 1378<br>
	 * Message: $c1 has picked up +$s3 $s2 that was dropped by a another
	 * character.
	 */
	C1_PICKED_UP_S3_S2_FROM_ANOTHER_CHARACTER(1378),

	/**
	 * ID: 1379<br>
	 * Message: $c1 has obtained $s2 adena.
	 */
	C1_OBTAINED_S2_ADENA(1379),

	/**
	 * ID: 1380<br>
	 * Message: You can't summon a $s1 while on the battleground.
	 */
	CANT_SUMMON_S1_ON_BATTLEGROUND(1380),

	/**
	 * ID: 1381<br>
	 * Message: The party leader has obtained $s2 of $s1.
	 */
	LEADER_OBTAINED_S2_OF_S1(1381),

	/**
	 * ID: 1382<br>
	 * Message: To fulfill the quest, you must bring the chosen weapon. Are you
	 * sure you want to choose this weapon?
	 */
	CHOOSE_WEAPON_CONFIRM(1382),

	/**
	 * ID: 1383<br>
	 * Message: Are you sure you want to exchange?
	 */
	EXCHANGE_CONFIRM(1383),

	/**
	 * ID: 1384<br>
	 * Message: $c1 has become the party leader.
	 */
	C1_HAS_BECOME_A_PARTY_LEADER(1384),

	/**
	 * ID: 1385<br>
	 * Message: You are not allowed to dismount at this location.
	 */
	NO_DISMOUNT_HERE(1385),

	/**
	 * ID: 1386<br>
	 * Message: You are no longer held in place.
	 */
	NO_LONGER_HELD_IN_PLACE(1386),

	/**
	 * ID: 1387<br>
	 * Message: Please select the item you would like to try on.
	 */
	SELECT_ITEM_TO_TRY_ON(1387),

	/**
	 * ID: 1388<br>
	 * Message: A party room has been created.
	 */
	PARTY_ROOM_CREATED(1388),

	/**
	 * ID: 1389<br>
	 * Message: The party room's information has been revised.
	 */
	PARTY_ROOM_REVISED(1389),

	/**
	 * ID: 1390<br>
	 * Message: You are not allowed to enter the party room.
	 */
	PARTY_ROOM_FORBIDDEN(1390),

	/**
	 * ID: 1391<br>
	 * Message: You have exited from the party room.
	 */
	PARTY_ROOM_EXITED(1391),

	/**
	 * ID: 1392<br>
	 * Message: $c1 has left the party room.
	 */
	C1_LEFT_PARTY_ROOM(1392),

	/**
	 * ID: 1393<br>
	 * Message: You have been ousted from the party room.
	 */
	OUSTED_FROM_PARTY_ROOM(1393),

	/**
	 * ID: 1394<br>
	 * Message: $c1 has been kicked from the party room.
	 */
	C1_KICKED_FROM_PARTY_ROOM(1394),

	/**
	 * ID: 1395<br>
	 * Message: The party room has been disbanded.
	 */
	PARTY_ROOM_DISBANDED(1395),

	/**
	 * ID: 1396<br>
	 * Message: The list of party rooms can only be viewed by a person who has
	 * not joined a party or who is currently the leader of a party.
	 */
	CANT_VIEW_PARTY_ROOMS(1396),

	/**
	 * ID: 1397<br>
	 * Message: The leader of the party room has changed.
	 */
	PARTY_ROOM_LEADER_CHANGED(1397),

	/**
	 * ID: 1398<br>
	 * Message: We are recruiting party members.
	 */
	RECRUITING_PARTY_MEMBERS(1398),

	/**
	 * ID: 1399<br>
	 * Message: Only the leader of the party can transfer party leadership to
	 * another player.
	 */
	ONLY_A_PARTY_LEADER_CAN_TRANSFER_ONES_RIGHTS_TO_ANOTHER_PLAYER(1399),

	/**
	 * ID: 1400<br>
	 * Message: Please select the person you wish to make the party leader.
	 */
	PLEASE_SELECT_THE_PERSON_TO_WHOM_YOU_WOULD_LIKE_TO_TRANSFER_THE_RIGHTS_OF_A_PARTY_LEADER(
			1400),

	/**
	 * ID: 1401<br>
	 * Message: Slow down.you are already the party leader.
	 */
	YOU_CANNOT_TRANSFER_RIGHTS_TO_YOURSELF(1401),

	/**
	 * ID: 1402<br>
	 * Message: You may only transfer party leadership to another member of the
	 * party.
	 */
	YOU_CAN_TRANSFER_RIGHTS_ONLY_TO_ANOTHER_PARTY_MEMBER(1402),

	/**
	 * ID: 1403<br>
	 * Message: You have failed to transfer the party leadership.
	 */
	YOU_HAVE_FAILED_TO_TRANSFER_THE_PARTY_LEADER_RIGHTS(1403),

	/**
	 * ID: 1404<br>
	 * Message: The owner of the private manufacturing store has changed the
	 * price for creating this item. Please check the new price before trying
	 * again.
	 */
	MANUFACTURE_PRICE_HAS_CHANGED(1404),

	/**
	 * ID: 1405<br>
	 * Message: $s1 CPs have been restored.
	 */
	S1_CP_WILL_BE_RESTORED(1405),

	/**
	 * ID: 1406<br>
	 * Message: $s2 CPs has been restored by $c1.
	 */
	S2_CP_WILL_BE_RESTORED_BY_C1(1406),

	/**
	 * ID: 1407<br>
	 * Message: You are using a computer that does not allow you to log in with
	 * two accounts at the same time.
	 */
	NO_LOGIN_WITH_TWO_ACCOUNTS(1407),

	/**
	 * ID: 1408<br>
	 * Message: Your prepaid remaining usage time is $s1 hours and $s2 minutes.
	 * You have $s3 paid reservations left.
	 */
	PREPAID_LEFT_S1_S2_S3(1408),

	/**
	 * ID: 1409<br>
	 * Message: Your prepaid usage time has expired. Your new prepaid
	 * reservation will be used. The remaining usage time is $s1 hours and $s2
	 * minutes.
	 */
	PREPAID_EXPIRED_S1_S2(1409),

	/**
	 * ID: 1410<br>
	 * Message: Your prepaid usage time has expired. You do not have any more
	 * prepaid reservations left.
	 */
	PREPAID_EXPIRED(1410),

	/**
	 * ID: 1411<br>
	 * Message: The number of your prepaid reservations has changed.
	 */
	PREPAID_CHANGED(1411),

	/**
	 * ID: 1412<br>
	 * Message: Your prepaid usage time has $s1 minutes left.
	 */
	PREPAID_LEFT_S1(1412),

	/**
	 * ID: 1413<br>
	 * Message: You do not meet the requirements to enter that party room.
	 */
	CANT_ENTER_PARTY_ROOM(1413),

	/**
	 * ID: 1414<br>
	 * Message: The width and length should be 100 or more grids and less than
	 * 5000 grids respectively.
	 */
	WRONG_GRID_COUNT(1414),

	/**
	 * ID: 1415<br>
	 * Message: The command file is not sent.
	 */
	COMMAND_FILE_NOT_SENT(1415),

	/**
	 * ID: 1416<br>
	 * Message: The representative of Team 1 has not been selected.
	 */
	TEAM_1_NO_REPRESENTATIVE(1416),

	/**
	 * ID: 1417<br>
	 * Message: The representative of Team 2 has not been selected.
	 */
	TEAM_2_NO_REPRESENTATIVE(1417),

	/**
	 * ID: 1418<br>
	 * Message: The name of Team 1 has not yet been chosen.
	 */
	TEAM_1_NO_NAME(1418),

	/**
	 * ID: 1419<br>
	 * Message: The name of Team 2 has not yet been chosen.
	 */
	TEAM_2_NO_NAME(1419),

	/**
	 * ID: 1420<br>
	 * Message: The name of Team 1 and the name of Team 2 are identical.
	 */
	TEAM_NAME_IDENTICAL(1420),

	/**
	 * ID: 1421<br>
	 * Message: The race setup file has not been designated.
	 */
	RACE_SETUP_FILE1(1421),

	/**
	 * ID: 1422<br>
	 * Message: Race setup file error - BuffCnt is not specified
	 */
	RACE_SETUP_FILE2(1422),

	/**
	 * ID: 1423<br>
	 * Message: Race setup file error - BuffID$s1 is not specified.
	 */
	RACE_SETUP_FILE3(1423),

	/**
	 * ID: 1424<br>
	 * Message: Race setup file error - BuffLv$s1 is not specified.
	 */
	RACE_SETUP_FILE4(1424),

	/**
	 * ID: 1425<br>
	 * Message: Race setup file error - DefaultAllow is not specified
	 */
	RACE_SETUP_FILE5(1425),

	/**
	 * ID: 1426<br>
	 * Message: Race setup file error - ExpSkillCnt is not specified.
	 */
	RACE_SETUP_FILE6(1426),

	/**
	 * ID: 1427<br>
	 * Message: Race setup file error - ExpSkillID$s1 is not specified.
	 */
	RACE_SETUP_FILE7(1427),

	/**
	 * ID: 1428<br>
	 * Message: Race setup file error - ExpItemCnt is not specified.
	 */
	RACE_SETUP_FILE8(1428),

	/**
	 * ID: 1429<br>
	 * Message: Race setup file error - ExpItemID$s1 is not specified.
	 */
	RACE_SETUP_FILE9(1429),

	/**
	 * ID: 1430<br>
	 * Message: Race setup file error - TeleportDelay is not specified
	 */
	RACE_SETUP_FILE10(1430),

	/**
	 * ID: 1431<br>
	 * Message: The race will be stopped temporarily.
	 */
	RACE_STOPPED_TEMPORARILY(1431),

	/**
	 * ID: 1432<br>
	 * Message: Your opponent is currently in a petrified state.
	 */
	OPPONENT_PETRIFIED(1432),

	/**
	 * ID: 1433<br>
	 * Message: You will now automatically apply $s1 to your target.
	 */
	USE_OF_S1_WILL_BE_AUTO(1433),

	/**
	 * ID: 1434<br>
	 * Message: You will no longer automatically apply $s1 to your weapon.
	 */
	AUTO_USE_OF_S1_CANCELLED(1434),

	/**
	 * ID: 1435<br>
	 * Message: Due to insufficient $s1, the automatic use function has been
	 * deactivated.
	 */
	AUTO_USE_CANCELLED_LACK_OF_S1(1435),

	/**
	 * ID: 1436<br>
	 * Message: Due to insufficient $s1, the automatic use function cannot be
	 * activated.
	 */
	CANNOT_AUTO_USE_LACK_OF_S1(1436),

	/**
	 * ID: 1437<br>
	 * Message: Players are no longer allowed to play dice. Dice can no longer
	 * be purchased from a village store. However, you can still sell them to
	 * any village store.
	 */
	DICE_NO_LONGER_ALLOWED(1437),

	/**
	 * ID: 1438<br>
	 * Message: There is no skill that enables enchant.
	 */
	THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT(1438),

	/**
	 * ID: 1439<br>
	 * Message: You do not have all of the items needed to enchant that skill.
	 */
	YOU_DONT_HAVE_ALL_OF_THE_ITEMS_NEEDED_TO_ENCHANT_THAT_SKILL(1439),

	/**
	 * ID: 1440<br>
	 * Message: You have succeeded in enchanting the skill $s1.
	 */
	YOU_HAVE_SUCCEEDED_IN_ENCHANTING_THE_SKILL_S1(1440),

	/**
	 * ID: 1441<br>
	 * Message: Skill enchant failed. The skill will be initialized.
	 */
	YOU_HAVE_FAILED_TO_ENCHANT_THE_SKILL_S1(1441),

	/**
	 * ID: 1443<br>
	 * Message: You do not have enough SP to enchant that skill.
	 */
	YOU_DONT_HAVE_ENOUGH_SP_TO_ENCHANT_THAT_SKILL(1443),

	/**
	 * ID: 1444<br>
	 * Message: You do not have enough experience (Exp) to enchant that skill.
	 */
	YOU_DONT_HAVE_ENOUGH_EXP_TO_ENCHANT_THAT_SKILL(1444),

	/**
	 * ID: 1445<br>
	 * Message: Your previous subclass will be removed and replaced with the new
	 * subclass at level 40. Do you wish to continue?
	 */
	REPLACE_SUBCLASS_CONFIRM(1445),

	/**
	 * ID: 1446<br>
	 * Message: The ferry from $s1 to $s2 has been delayed.
	 */
	FERRY_FROM_S1_TO_S2_DELAYED(1446),

	/**
	 * ID: 1447<br>
	 * Message: You cannot do that while fishing.
	 */
	CANNOT_DO_WHILE_FISHING_1(1447),

	/**
	 * ID: 1448<br>
	 * Message: Only fishing skills may be used at this time.
	 */
	ONLY_FISHING_SKILLS_NOW(1448),

	/**
	 * ID: 1449<br>
	 * Message: You've got a bite!
	 */
	GOT_A_BITE(1449),

	/**
	 * ID: 1450<br>
	 * Message: That fish is more determined than you are - it spit the hook!
	 */
	FISH_SPIT_THE_HOOK(1450),

	/**
	 * ID: 1451<br>
	 * Message: Your bait was stolen by that fish!
	 */
	BAIT_STOLEN_BY_FISH(1451),

	/**
	 * ID: 1452<br>
	 * Message: Baits have been lost because the fish got away.
	 */
	BAIT_LOST_FISH_GOT_AWAY(1452),

	/**
	 * ID: 1453<br>
	 * Message: You do not have a fishing pole equipped.
	 */
	FISHING_POLE_NOT_EQUIPPED(1453),

	/**
	 * ID: 1454<br>
	 * Message: You must put bait on your hook before you can fish.
	 */
	BAIT_ON_HOOK_BEFORE_FISHING(1454),

	/**
	 * ID: 1455<br>
	 * Message: You cannot fish while under water.
	 */
	CANNOT_FISH_UNDER_WATER(1455),

	/**
	 * ID: 1456<br>
	 * Message: You cannot fish while riding as a passenger of a boat - it's
	 * against the rules.
	 */
	CANNOT_FISH_ON_BOAT(1456),

	/**
	 * ID: 1457<br>
	 * Message: You can't fish here.
	 */
	CANNOT_FISH_HERE(1457),

	/**
	 * ID: 1458<br>
	 * Message: Your attempt at fishing has been cancelled.
	 */
	FISHING_ATTEMPT_CANCELLED(1458),

	/**
	 * ID: 1459<br>
	 * Message: You do not have enough bait.
	 */
	NOT_ENOUGH_BAIT(1459),

	/**
	 * ID: 1460<br>
	 * Message: You reel your line in and stop fishing.
	 */
	REEL_LINE_AND_STOP_FISHING(1460),

	/**
	 * ID: 1461<br>
	 * Message: You cast your line and start to fish.
	 */
	CAST_LINE_AND_START_FISHING(1461),

	/**
	 * ID: 1462<br>
	 * Message: You may only use the Pumping skill while you are fishing.
	 */
	CAN_USE_PUMPING_ONLY_WHILE_FISHING(1462),

	/**
	 * ID: 1463<br>
	 * Message: You may only use the Reeling skill while you are fishing.
	 */
	CAN_USE_REELING_ONLY_WHILE_FISHING(1463),

	/**
	 * ID: 1464<br>
	 * Message: The fish has resisted your attempt to bring it in.
	 */
	FISH_RESISTED_ATTEMPT_TO_BRING_IT_IN(1464),

	/**
	 * ID: 1465<br>
	 * Message: Your pumping is successful, causing $s1 damage.
	 */
	PUMPING_SUCCESFUL_S1_DAMAGE(1465),

	/**
	 * ID: 1466<br>
	 * Message: You failed to do anything with the fish and it regains $s1 HP.
	 */
	FISH_RESISTED_PUMPING_S1_HP_REGAINED(1466),

	/**
	 * ID: 1467<br>
	 * Message: You reel that fish in closer and cause $s1 damage.
	 */
	REELING_SUCCESFUL_S1_DAMAGE(1467),

	/**
	 * ID: 1468<br>
	 * Message: You failed to reel that fish in further and it regains $s1 HP.
	 */
	FISH_RESISTED_REELING_S1_HP_REGAINED(1468),

	/**
	 * ID: 1469<br>
	 * Message: You caught something!
	 */
	YOU_CAUGHT_SOMETHING(1469),

	/**
	 * ID: 1470<br>
	 * Message: You cannot do that while fishing.
	 */
	CANNOT_DO_WHILE_FISHING_2(1470),

	/**
	 * ID: 1471<br>
	 * Message: You cannot do that while fishing.
	 */
	CANNOT_DO_WHILE_FISHING_3(1471),

	/**
	 * ID: 1472<br>
	 * Message: You look oddly at the fishing pole in disbelief and realize that
	 * you can't attack anything with this.
	 */
	CANNOT_ATTACK_WITH_FISHING_POLE(1472),

	/**
	 * ID: 1473<br>
	 * Message: $s1 is not sufficient.
	 */
	S1_NOT_SUFFICIENT(1473),

	/**
	 * ID: 1474<br>
	 * Message: $s1 is not available.
	 */
	S1_NOT_AVAILABLE(1474),

	/**
	 * ID: 1475<br>
	 * Message: Pet has dropped $s1.
	 */
	PET_DROPPED_S1(1475),

	/**
	 * ID: 1476<br>
	 * Message: Pet has dropped +$s1 $s2.
	 */
	PET_DROPPED_S1_S2(375),

	/**
	 * ID: 1477<br>
	 * Message: Pet has dropped $s2 of $s1.
	 */
	PET_DROPPED_S2_S1_S(1477),

	/**
	 * ID: 1478<br>
	 * Message: You may only register a 64 x 64 pixel, 256-color BMP.
	 */
	ONLY_64_PIXEL_256_COLOR_BMP(1478),

	/**
	 * ID: 1479<br>
	 * Message: That is the wrong grade of soulshot for that fishing pole.
	 */
	WRONG_FISHINGSHOT_GRADE(1479),

	/**
	 * ID: 1480<br>
	 * Message: Are you sure you want to remove yourself from the Grand Olympiad
	 * Games waiting list?
	 */
	OLYMPIAD_REMOVE_CONFIRM(1480),

	/**
	 * ID: 1481<br>
	 * Message: You have selected a class irrelevant individual match. Do you
	 * wish to participate?
	 */
	OLYMPIAD_NON_CLASS_CONFIRM(1481),

	/**
	 * ID: 1482<br>
	 * Message: You've selected to join a class specific game. Continue?
	 */
	OLYMPIAD_CLASS_CONFIRM(1482),

	/**
	 * ID: 1483<br>
	 * Message: Are you ready to be a Hero?
	 */
	HERO_CONFIRM(1483),

	/**
	 * ID: 1484<br>
	 * Message: Are you sure this is the Hero weapon you wish to use? Kamael
	 * race cannot use this.
	 */
	HERO_WEAPON_CONFIRM(1484),

	/**
	 * ID: 1485<br>
	 * Message: The ferry from Talking Island to Gludin Harbor has been delayed.
	 */
	FERRY_TALKING_GLUDIN_DELAYED(1485),

	/**
	 * ID: 1486<br>
	 * Message: The ferry from Gludin Harbor to Talking Island has been delayed.
	 */
	FERRY_GLUDIN_TALKING_DELAYED(1486),

	/**
	 * ID: 1487<br>
	 * Message: The ferry from Giran Harbor to Talking Island has been delayed.
	 */
	FERRY_GIRAN_TALKING_DELAYED(1487),

	/**
	 * ID: 1488<br>
	 * Message: The ferry from Talking Island to Giran Harbor has been delayed.
	 */
	FERRY_TALKING_GIRAN_DELAYED(1488),

	/**
	 * ID: 1489<br>
	 * Message: Innadril cruise service has been delayed.
	 */
	INNADRIL_BOAT_DELAYED(1489),

	/**
	 * ID: 1490<br>
	 * Message: Traded $s2 of crop $s1.
	 */
	TRADED_S2_OF_CROP_S1(1490),

	/**
	 * ID: 1491<br>
	 * Message: Failed in trading $s2 of crop $s1.
	 */
	FAILED_IN_TRADING_S2_OF_CROP_S1(1491),

	/**
	 * ID: 1492<br>
	 * Message: You will be moved to the Olympiad Stadium in $s1 second(s).
	 */
	YOU_WILL_ENTER_THE_OLYMPIAD_STADIUM_IN_S1_SECOND_S(1492),

	/**
	 * ID: 1493<br>
	 * Message: Your opponent made haste with their tail between their legs),
	 * the match has been cancelled.
	 */
	THE_GAME_HAS_BEEN_CANCELLED_BECAUSE_THE_OTHER_PARTY_ENDS_THE_GAME(1493),

	/**
	 * ID: 1494<br>
	 * Message: Your opponent does not meet the requirements to do battle), the
	 * match has been cancelled.
	 */
	THE_GAME_HAS_BEEN_CANCELLED_BECAUSE_THE_OTHER_PARTY_DOES_NOT_MEET_THE_REQUIREMENTS_FOR_JOINING_THE_GAME(
			1494),

	/**
	 * ID: 1495<br>
	 * Message: The match will start in $s1 second(s).
	 */
	THE_GAME_WILL_START_IN_S1_SECOND_S(1495),

	/**
	 * ID: 1496<br>
	 * Message: The match has started, fight!
	 */
	STARTS_THE_GAME(1496),

	/**
	 * ID: 1497<br>
	 * Message: Congratulations, $c1! You win the match!
	 */
	C1_HAS_WON_THE_GAME(1497),

	/**
	 * ID: 1498<br>
	 * Message: There is no victor, the match ends in a tie.
	 */
	THE_GAME_ENDED_IN_A_TIE(1498),

	/**
	 * ID: 1499<br>
	 * Message: You will be moved back to town in $s1 second(s).
	 */
	YOU_WILL_BE_MOVED_TO_TOWN_IN_S1_SECONDS(1499),

	/**
	 * ID: 1500<br>
	 * Message: $c1% does not meet the participation requirements. A sub-class
	 * character cannot participate in the Olympiad.
	 */
	C1_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_CLASS_CHARACTER(1500),

	/**
	 * ID: 1501<br>
	 * Message: $c1% does not meet the participation requirements. Only Noblesse
	 * can participate in the Olympiad.
	 */
	C1_DOES_NOT_MEET_REQUIREMENTS_ONLY_NOBLESS_CAN_PARTICIPATE_IN_THE_OLYMPIAD(
			1501),

	/**
	 * ID: 1502<br>
	 * Message: $c1 is already registered on the match waiting list.
	 */
	C1_IS_ALREADY_REGISTERED_ON_THE_MATCH_WAITING_LIST(1502),

	/**
	 * ID: 1503<br>
	 * Message: You have been registered in the Grand Olympiad Games waiting
	 * list for a class specific match.
	 */
	YOU_HAVE_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_CLASSIFIED_GAMES(1503),

	/**
	 * ID: 1504<br>
	 * Message: You have registered on the waiting list for the
	 * non-class-limited individual match event.
	 */
	YOU_HAVE_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_NO_CLASS_GAMES(1504),

	/**
	 * ID: 1505<br>
	 * Message: You have been removed from the Grand Olympiad Games waiting
	 * list.
	 */
	YOU_HAVE_BEEN_DELETED_FROM_THE_WAITING_LIST_OF_A_GAME(1505),

	/**
	 * ID: 1506<br>
	 * Message: You are not currently registered on any Grand Olympiad Games
	 * waiting list.
	 */
	YOU_HAVE_NOT_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_A_GAME(1506),

	/**
	 * ID: 1507<br>
	 * Message: You cannot equip that item in a Grand Olympiad Games match.
	 */
	THIS_ITEM_CANT_BE_EQUIPPED_FOR_THE_OLYMPIAD_EVENT(1507),

	/**
	 * ID: 1508<br>
	 * Message: You cannot use that item in a Grand Olympiad Games match.
	 */
	THIS_ITEM_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT(1508),

	/**
	 * ID: 1509<br>
	 * Message: You cannot use that skill in a Grand Olympiad Games match.
	 */
	THIS_SKILL_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT(1509),

	/**
	 * ID: 1510<br>
	 * Message: $c1 is making an attempt at resurrection with $s2 experience
	 * points. Do you want to be resurrected?
	 */
	RESSURECTION_REQUEST_BY_C1_FOR_S2_XP(1510),

	/**
	 * ID: 1511<br>
	 * Message: While a pet is attempting to resurrect, it cannot help in
	 * resurrecting its master.
	 */
	MASTER_CANNOT_RES(1511),

	/**
	 * ID: 1512<br>
	 * Message: You cannot resurrect a pet while their owner is being
	 * resurrected.
	 */
	CANNOT_RES_PET(1512),

	/**
	 * ID: 1513<br>
	 * Message: Resurrection has already been proposed.
	 */
	RES_HAS_ALREADY_BEEN_PROPOSED(1513),

	/**
	 * ID: 1514<br>
	 * Message: You cannot the owner of a pet while their pet is being
	 * resurrected
	 */
	CANNOT_RES_MASTER(1514),

	/**
	 * ID: 1515<br>
	 * Message: A pet cannot be resurrected while it's owner is in the process
	 * of resurrecting.
	 */
	CANNOT_RES_PET2(1515),

	/**
	 * ID: 1516<br>
	 * Message: The target is unavailable for seeding.
	 */
	THE_TARGET_IS_UNAVAILABLE_FOR_SEEDING(1516),

	/**
	 * ID: 1517<br>
	 * Message: Failed in Blessed Enchant. The enchant value of the item became
	 * 0.
	 */
	BLESSED_ENCHANT_FAILED(1517),

	/**
	 * ID: 1518<br>
	 * Message: You do not meet the required condition to equip that item.
	 */
	CANNOT_EQUIP_ITEM_DUE_TO_BAD_CONDITION(1518),

	/**
	 * ID: 1519<br>
	 * the pet's body will disappear along with all the pet's items.
	 */
	MAKE_SURE_YOU_RESSURECT_YOUR_PET_WITHIN_24_HOURS(1519),

	/**
	 * ID: 1520<br>
	 * Message: Servitor passed away.
	 */
	SERVITOR_PASSED_AWAY(1520),

	/**
	 * ID: 1521<br>
	 * Message: Your servitor has vanished! You'll need to summon a new one.
	 */
	YOUR_SERVITOR_HAS_VANISHED(1521),

	/**
	 * ID: 1522<br>
	 * Message: Your pet's corpse has decayed!
	 */
	YOUR_PETS_CORPSE_HAS_DECAYED(1522),

	/**
	 * ID: 1523<br>
	 * Message: You should release your pet or servitor so that it does not fall
	 * off of the boat and drown!
	 */
	RELEASE_PET_ON_BOAT(1523),

	/**
	 * ID: 1524<br>
	 * Message: $c1's pet gained $s2.
	 */
	C1_PET_GAINED_S2(1524),

	/**
	 * ID: 1525<br>
	 * Message: $c1's pet gained $s3 of $s2.
	 */
	C1_PET_GAINED_S3_S2_S(1525),

	/**
	 * ID: 1526<br>
	 * Message: $c1's pet gained +$s2$s3.
	 */
	C1_PET_GAINED_S2_S3(1526),

	/**
	 * ID: 1527<br>
	 * Message: Your pet was hungry so it ate $s1.
	 */
	PET_TOOK_S1_BECAUSE_HE_WAS_HUNGRY(1527),

	/**
	 * ID: 1528<br>
	 * Message: You've sent a petition to the GM staff.
	 */
	SENT_PETITION_TO_GM(1528),

	/**
	 * ID: 1529<br>
	 * Message: $c1 is inviting you to the command channel. Do you want accept?
	 */
	COMMAND_CHANNEL_CONFIRM_FROM_C1(1529),

	/**
	 * ID: 1530<br>
	 * Message: Select a target or enter the name.
	 */
	SELECT_TARGET_OR_ENTER_NAME(1530),

	/**
	 * ID: 1531<br>
	 * Message: Enter the name of the clan that you wish to declare war on.
	 */
	ENTER_CLAN_NAME_TO_DECLARE_WAR2(1531),

	/**
	 * ID: 1532<br>
	 * Message: Enter the name of the clan that you wish to have a cease-fire
	 * with.
	 */
	ENTER_CLAN_NAME_TO_CEASE_FIRE(1532),

	/**
	 * ID: 1533<br>
	 * Message: Announcement: $c1 has picked up $s2.
	 */
	ANNOUNCEMENT_C1_PICKED_UP_S2(1533),

	/**
	 * ID: 1534<br>
	 * Message: Announcement: $c1 has picked up +$s2$s3.
	 */
	ANNOUNCEMENT_C1_PICKED_UP_S2_S3(1534),

	/**
	 * ID: 1535<br>
	 * Message: Announcement: $c1's pet has picked up $s2.
	 */
	ANNOUNCEMENT_C1_PET_PICKED_UP_S2(1535),

	/**
	 * ID: 1536<br>
	 * Message: Announcement: $c1's pet has picked up +$s2$s3.
	 */
	ANNOUNCEMENT_C1_PET_PICKED_UP_S2_S3(1536),

	/**
	 * ID: 1537<br>
	 * Message: Current Location: $s1, $s2, $s3 (near Rune Village)
	 */
	LOC_RUNE_S1_S2_S3(1537),

	/**
	 * ID: 1538<br>
	 * Message: Current Location: $s1, $s2, $s3 (near the Town of Goddard)
	 */
	LOC_GODDARD_S1_S2_S3(1538),

	/**
	 * ID: 1539<br>
	 * Message: Cargo has arrived at Talking Island Village.
	 */
	CARGO_AT_TALKING_VILLAGE(1539),

	/**
	 * ID: 1540<br>
	 * Message: Cargo has arrived at the Dark Elf Village.
	 */
	CARGO_AT_DARKELF_VILLAGE(1540),

	/**
	 * ID: 1541<br>
	 * Message: Cargo has arrived at Elven Village.
	 */
	CARGO_AT_ELVEN_VILLAGE(1541),

	/**
	 * ID: 1542<br>
	 * Message: Cargo has arrived at Orc Village.
	 */
	CARGO_AT_ORC_VILLAGE(1542),

	/**
	 * ID: 1543<br>
	 * Message: Cargo has arrived at Dwarfen Village.
	 */
	CARGO_AT_DWARVEN_VILLAGE(1543),

	/**
	 * ID: 1544<br>
	 * Message: Cargo has arrived at Aden Castle Town.
	 */
	CARGO_AT_ADEN(1544),

	/**
	 * ID: 1545<br>
	 * Message: Cargo has arrived at Town of Oren.
	 */
	CARGO_AT_OREN(1545),

	/**
	 * ID: 1546<br>
	 * Message: Cargo has arrived at Hunters Village.
	 */
	CARGO_AT_HUNTERS(1546),

	/**
	 * ID: 1547<br>
	 * Message: Cargo has arrived at the Town of Dion.
	 */
	CARGO_AT_DION(1547),

	/**
	 * ID: 1548<br>
	 * Message: Cargo has arrived at Floran Village.
	 */
	CARGO_AT_FLORAN(1548),

	/**
	 * ID: 1549<br>
	 * Message: Cargo has arrived at Gludin Village.
	 */
	CARGO_AT_GLUDIN(1549),

	/**
	 * ID: 1550<br>
	 * Message: Cargo has arrived at the Town of Gludio.
	 */
	CARGO_AT_GLUDIO(1550),

	/**
	 * ID: 1551<br>
	 * Message: Cargo has arrived at Giran Castle Town.
	 */
	CARGO_AT_GIRAN(1551),

	/**
	 * ID: 1552<br>
	 * Message: Cargo has arrived at Heine.
	 */
	CARGO_AT_HEINE(1552),

	/**
	 * ID: 1553<br>
	 * Message: Cargo has arrived at Rune Village.
	 */
	CARGO_AT_RUNE(1553),

	/**
	 * ID: 1554<br>
	 * Message: Cargo has arrived at the Town of Goddard.
	 */
	CARGO_AT_GODDARD(1554),

	/**
	 * ID: 1555<br>
	 * Message: Do you want to cancel character deletion?
	 */
	CANCEL_CHARACTER_DELETION_CONFIRM(1555),

	/**
	 * ID: 1556<br>
	 * Message: Your clan notice has been saved.
	 */
	CLAN_NOTICE_SAVED(1556),

	/**
	 * ID: 1557<br>
	 * Message: Seed price should be more than $s1 and less than $s2.
	 */
	SEED_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2(1557),

	/**
	 * ID: 1558<br>
	 * Message: The quantity of seed should be more than $s1 and less than $s2.
	 */
	THE_QUANTITY_OF_SEED_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2(1558),

	/**
	 * ID: 1559<br>
	 * Message: Crop price should be more than $s1 and less than $s2.
	 */
	CROP_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2(1559),

	/**
	 * ID: 1560<br>
	 * Message: The quantity of crop should be more than $s1 and less than $s2
	 */
	THE_QUANTITY_OF_CROP_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2(1560),

	/**
	 * ID: 1561<br>
	 * Message: The clan, $s1, has declared a Clan War.
	 */
	CLAN_S1_DECLARED_WAR(1561),

	/**
	 * ID: 1562<br>
	 * Message: A Clan War has been declared against the clan, $s1. you will
	 * only lose a quarter of the normal experience from death.
	 */
	CLAN_WAR_DECLARED_AGAINST_S1_IF_KILLED_LOSE_LOW_EXP(1562),

	/**
	 * ID: 1563<br>
	 * or they do not have enough members.
	 */
	CANNOT_DECLARE_WAR_TOO_LOW_LEVEL_OR_NOT_ENOUGH_MEMBERS(1563),

	/**
	 * ID: 1564<br>
	 * Message: A Clan War can be declared only if the clan is level three or
	 * above, and the number of clan members is fifteen or greater.
	 */
	CLAN_WAR_DECLARED_IF_CLAN_LVL3_OR_15_MEMBER(1564),

	/**
	 * ID: 1565<br>
	 * Message: A Clan War cannot be declared against a clan that does not
	 * exist!
	 */
	CLAN_WAR_CANNOT_DECLARED_CLAN_NOT_EXIST(1565),

	/**
	 * ID: 1566<br>
	 * Message: The clan, $s1, has decided to stop the war.
	 */
	CLAN_S1_HAS_DECIDED_TO_STOP(1566),

	/**
	 * ID: 1567<br>
	 * Message: The war against $s1 Clan has been stopped.
	 */
	WAR_AGAINST_S1_HAS_STOPPED(1567),

	/**
	 * ID: 1568<br>
	 * Message: The target for declaration is wrong.
	 */
	WRONG_DECLARATION_TARGET(1568),

	/**
	 * ID: 1569<br>
	 * Message: A declaration of Clan War against an allied clan can't be made.
	 */
	CLAN_WAR_AGAINST_A_ALLIED_CLAN_NOT_WORK(1569),

	/**
	 * ID: 1570<br>
	 * Message: A declaration of war against more than 30 Clans can't be made at
	 * the same time
	 */
	TOO_MANY_CLAN_WARS(1570),

	/**
	 * ID: 1571<br>
	 * Message: ======<Clans You've Declared War On>======
	 */
	CLANS_YOU_DECLARED_WAR_ON(1571),

	/**
	 * ID: 1572<br>
	 * Message: ======<Clans That Have Declared War On You>======
	 */
	CLANS_THAT_HAVE_DECLARED_WAR_ON_YOU(1572),

	/**
	 * ID: 1573<br>
	 * Message: All is well. There are no clans that have declared war against
	 * your clan.
	 */
	NO_WARS_AGAINST_YOU(1573),

	/**
	 * ID: 1574<br>
	 * Message: Command Channels can only be formed by a party leader who is
	 * also the leader of a level 5 clan.
	 */
	COMMAND_CHANNEL_ONLY_BY_LEVEL_5_CLAN_LEADER_PARTY_LEADER(1574),

	/**
	 * ID: 1575<br>
	 * Message: Your pet uses spiritshot.
	 */
	PET_USE_SPIRITSHOT(1575),

	/**
	 * ID: 1576<br>
	 * Message: Your servitor uses spiritshot.
	 */
	SERVITOR_USE_SPIRITSHOT(1576),

	/**
	 * ID: 1577<br>
	 * Message: Servitor uses the power of spirit.
	 */
	SERVITOR_USE_THE_POWER_OF_SPIRIT(1577),

	/**
	 * ID: 1578<br>
	 * Message: Items are not available for a private store or a private
	 * manufacture.
	 */
	ITEMS_UNAVAILABLE_FOR_STORE_MANUFACTURE(1578),

	/**
	 * ID: 1579<br>
	 * Message: $c1's pet gained $s2 adena.
	 */
	C1_PET_GAINED_S2_ADENA(1579),

	/**
	 * ID: 1580<br>
	 * Message: The Command Channel has been formed.
	 */
	COMMAND_CHANNEL_FORMED(1580),

	/**
	 * ID: 1581<br>
	 * Message: The Command Channel has been disbanded.
	 */
	COMMAND_CHANNEL_DISBANDED(1581),

	/**
	 * ID: 1582<br>
	 * Message: You have joined the Command Channel.
	 */
	JOINED_COMMAND_CHANNEL(1582),

	/**
	 * ID: 1583<br>
	 * Message: You were dismissed from the Command Channel.
	 */
	DISMISSED_FROM_COMMAND_CHANNEL(1583),

	/**
	 * ID: 1584<br>
	 * Message: $c1's party has been dismissed from the Command Channel.
	 */
	C1_PARTY_DISMISSED_FROM_COMMAND_CHANNEL(1583),

	/**
	 * ID: 1585<br>
	 * Message: The Command Channel has been disbanded.
	 */
	COMMAND_CHANNEL_DISBANDED2(1585),

	/**
	 * ID: 1586<br>
	 * Message: You have quit the Command Channel.
	 */
	LEFT_COMMAND_CHANNEL(1586),

	/**
	 * ID: 1587<br>
	 * Message: $c1's party has left the Command Channel.
	 */
	C1_PARTY_LEFT_COMMAND_CHANNEL(1586),

	/**
	 * ID: 1588<br>
	 * Message: The Command Channel is activated only when there are at least 5
	 * parties participating.
	 */
	COMMAND_CHANNEL_ONLY_AT_LEAST_5_PARTIES(1588),

	/**
	 * ID: 1589<br>
	 * Message: Command Channel authority has been transferred to $c1.
	 */
	COMMAND_CHANNEL_LEADER_NOW_C1(1589),

	/**
	 * ID: 1590<br>
	 * Message: ===<Guild Info (Total Parties: $s1)>===
	 */
	GUILD_INFO_HEADER(1590),

	/**
	 * ID: 1591<br>
	 * Message: No user has been invited to the Command Channel.
	 */
	NO_USER_INVITED_TO_COMMAND_CHANNEL(1591),

	/**
	 * ID: 1592<br>
	 * Message: You can no longer set up a Command Channel.
	 */
	CANNOT_LONGER_SETUP_COMMAND_CHANNEL(1592),

	/**
	 * ID: 1593<br>
	 * Message: You do not have authority to invite someone to the Command
	 * Channel.
	 */
	CANNOT_INVITE_TO_COMMAND_CHANNEL(1593),

	/**
	 * ID: 1594<br>
	 * Message: $c1's party is already a member of the Command Channel.
	 */
	C1_ALREADY_MEMBER_OF_COMMAND_CHANNEL(1594),

	/**
	 * ID: 1595<br>
	 * Message: $s1 has succeeded.
	 */
	S1_SUCCEEDED(1595),

	/**
	 * ID: 1596<br>
	 * Message: You were hit by $s1!
	 */
	HIT_BY_S1(1596),

	/**
	 * ID: 1597<br>
	 * Message: $s1 has failed.
	 */
	S1_FAILED(1597),

	/**
	 * ID: 1598<br>
	 * Message: Soulshots and spiritshots are not available for a dead pet or
	 * servitor. Sad, isn't it?
	 */
	SOULSHOTS_AND_SPIRITSHOTS_ARE_NOT_AVAILABLE_FOR_A_DEAD_PET(1598),

	/**
	 * ID: 1599<br>
	 * Message: You cannot observe while you are in combat!
	 */
	CANNOT_OBSERVE_IN_COMBAT(1599),

	/**
	 * ID: 1600<br>
	 * Message: Tomorrow's items will ALL be set to 0. Do you wish to continue?
	 */
	TOMORROW_ITEM_ZERO_CONFIRM(1600),

	/**
	 * ID: 1601<br>
	 * Message: Tomorrow's items will all be set to the same value as today's
	 * items. Do you wish to continue?
	 */
	TOMORROW_ITEM_SAME_CONFIRM(1601),

	/**
	 * ID: 1602<br>
	 * Message: Only a party leader can access the Command Channel.
	 */
	COMMAND_CHANNEL_ONLY_FOR_PARTY_LEADER(1602),

	/**
	 * ID: 1603<br>
	 * Message: Only channel operator can give All Command.
	 */
	ONLY_COMMANDER_GIVE_COMMAND(1603),

	/**
	 * ID: 1604<br>
	 * Message: While dressed in formal wear, you can't use items that require
	 * all skills and casting operations.
	 */
	CANNOT_USE_ITEMS_SKILLS_WITH_FORMALWEAR(1604),

	/**
	 * ID: 1605<br>
	 * Message: * Here, you can buy only seeds of $s1 Manor.
	 */
	HERE_YOU_CAN_BUY_ONLY_SEEDS_OF_S1_MANOR(1605),

	/**
	 * ID: 1606<br>
	 * Message: Congratulations - You've completed the third-class transfer
	 * quest!
	 */
	THIRD_CLASS_TRANSFER(1308),

	/**
	 * ID: 1607<br>
	 * Message: $s1 adena has been withdrawn to pay for purchasing fees.
	 */
	S1_ADENA_HAS_BEEN_WITHDRAWN_TO_PAY_FOR_PURCHASING_FEES(1607),

	/**
	 * ID: 1608<br>
	 * Message: Due to insufficient adena you cannot buy another castle.
	 */
	INSUFFICIENT_ADENA_TO_BUY_CASTLE(1608),

	/**
	 * ID: 1609<br>
	 * Message: War has already been declared against that clan... but I'll make
	 * note that you really don't like them.
	 */
	WAR_ALREADY_DECLARED(1609),

	/**
	 * ID: 1610<br>
	 * Message: Fool! You cannot declare war against your own clan!
	 */
	CANNOT_DECLARE_AGAINST_OWN_CLAN(1610),

	/**
	 * ID: 1611<br>
	 * Message: Leader: $c1
	 */
	PARTY_LEADER_C1(1611),

	/**
	 * ID: 1612<br>
	 * Message: =====<War List>=====
	 */
	WAR_LIST(1612),

	/**
	 * ID: 1613<br>
	 * Message: There is no clan listed on War List.
	 */
	NO_CLAN_ON_WAR_LIST(1612),

	/**
	 * ID: 1614<br>
	 * Message: You have joined a channel that was already open.
	 */
	JOINED_CHANNEL_ALREADY_OPEN(1614),

	/**
	 * ID: 1615<br>
	 * Message: The number of remaining parties is $s1 until a channel is
	 * activated
	 */
	S1_PARTIES_REMAINING_UNTIL_CHANNEL(1615),

	/**
	 * ID: 1616<br>
	 * Message: The Command Channel has been activated.
	 */
	COMMAND_CHANNEL_ACTIVATED(1616),

	/**
	 * ID: 1617<br>
	 * Message: You do not have the authority to use the Command Channel.
	 */
	CANT_USE_COMMAND_CHANNEL(1617),

	/**
	 * ID: 1618<br>
	 * Message: The ferry from Rune Harbor to Gludin Harbor has been delayed.
	 */
	FERRY_RUNE_GLUDIN_DELAYED(1618),

	/**
	 * ID: 1619<br>
	 * Message: The ferry from Gludin Harbor to Rune Harbor has been delayed.
	 */
	FERRY_GLUDIN_RUNE_DELAYED(1619),

	/**
	 * ID: 1620<br>
	 * Message: Arrived at Rune Harbor.
	 */
	ARRIVED_AT_RUNE(1620),

	/**
	 * ID: 1621<br>
	 * Message: Departure for Gludin Harbor will take place in five minutes!
	 */
	DEPARTURE_FOR_GLUDIN_5_MINUTES(1621),

	/**
	 * ID: 1622<br>
	 * Message: Departure for Gludin Harbor will take place in one minute!
	 */
	DEPARTURE_FOR_GLUDIN_1_MINUTE(1622),

	/**
	 * ID: 1623<br>
	 * Message: Make haste! We will be departing for Gludin Harbor shortly...
	 */
	DEPARTURE_FOR_GLUDIN_SHORTLY(1623),

	/**
	 * ID: 1624<br>
	 * Message: We are now departing for Gludin Harbor Hold on and enjoy the
	 * ride!
	 */
	DEPARTURE_FOR_GLUDIN_NOW(1624),

	/**
	 * ID: 1625<br>
	 * Message: Departure for Rune Harbor will take place after anchoring for
	 * ten minutes.
	 */
	REPARTURE_FOR_RUNE_10_MINUTES(1625),

	/**
	 * ID: 1626<br>
	 * Message: Departure for Rune Harbor will take place in five minutes!
	 */
	DEPARTURE_FOR_RUNE_5_MINUTES(1626),

	/**
	 * ID: 1627<br>
	 * Message: Departure for Rune Harbor will take place in one minute!
	 */
	DEPARTURE_FOR_RUNE_1_MINUTE(1627),

	/**
	 * ID: 1628<br>
	 * Message: Make haste! We will be departing for Gludin Harbor shortly...
	 */
	DEPARTURE_FOR_GLUDIN_SHORTLY2(1628),

	/**
	 * ID: 1629<br>
	 * Message: We are now departing for Rune Harbor Hold on and enjoy the ride!
	 */
	DEPARTURE_FOR_RUNE_NOW(1629),

	/**
	 * ID: 1630<br>
	 * Message: The ferry from Rune Harbor will be arriving at Gludin Harbor in
	 * approximately 15 minutes.
	 */
	FERRY_FROM_RUNE_AT_GLUDIN_15_MINUTES(1630),

	/**
	 * ID: 1631<br>
	 * Message: The ferry from Rune Harbor will be arriving at Gludin Harbor in
	 * approximately 10 minutes.
	 */
	FERRY_FROM_RUNE_AT_GLUDIN_10_MINUTES(1631),

	/**
	 * ID: 1632<br>
	 * Message: The ferry from Rune Harbor will be arriving at Gludin Harbor in
	 * approximately 10 minutes.
	 */
	FERRY_FROM_RUNE_AT_GLUDIN_5_MINUTES(1632),

	/**
	 * ID: 1633<br>
	 * Message: The ferry from Rune Harbor will be arriving at Gludin Harbor in
	 * approximately 1 minute.
	 */
	FERRY_FROM_RUNE_AT_GLUDIN_1_MINUTE(1633),

	/**
	 * ID: 1634<br>
	 * Message: The ferry from Gludin Harbor will be arriving at Rune Harbor in
	 * approximately 15 minutes.
	 */
	FERRY_FROM_GLUDIN_AT_RUNE_15_MINUTES(1634),

	/**
	 * ID: 1635<br>
	 * Message: The ferry from Gludin Harbor will be arriving at Rune harbor in
	 * approximately 10 minutes.
	 */
	FERRY_FROM_GLUDIN_AT_RUNE_10_MINUTES(1635),

	/**
	 * ID: 1636<br>
	 * Message: The ferry from Gludin Harbor will be arriving at Rune Harbor in
	 * approximately 10 minutes.
	 */
	FERRY_FROM_GLUDIN_AT_RUNE_5_MINUTES(1636),

	/**
	 * ID: 1637<br>
	 * Message: The ferry from Gludin Harbor will be arriving at Rune Harbor in
	 * approximately 1 minute.
	 */
	FERRY_FROM_GLUDIN_AT_RUNE_1_MINUTE(1637),

	/**
	 * ID: 1638<br>
	 * Message: You cannot fish while using a recipe book, private manufacture
	 * or private store.
	 */
	CANNOT_FISH_WHILE_USING_RECIPE_BOOK(1638),

	/**
	 * ID: 1639<br>
	 * Message: Period $s1 of the Grand Olympiad Games has started!
	 */
	OLYMPIAD_PERIOD_S1_HAS_STARTED(1639),

	/**
	 * ID: 1640<br>
	 * Message: Period $s1 of the Grand Olympiad Games has now ended.
	 */
	OLYMPIAD_PERIOD_S1_HAS_ENDED(1640),

	/**
	 * ID: 1641<br>
	 * and make haste to a Grand Olympiad Manager! Battles in the Grand Olympiad
	 * Games are now taking place!
	 */
	THE_OLYMPIAD_GAME_HAS_STARTED(1641),

	/**
	 * ID: 1642<br>
	 * Message: Much carnage has been left for the cleanup crew of the Olympiad
	 * Stadium. Battles in the Grand Olympiad Games are now over!
	 */
	THE_OLYMPIAD_GAME_HAS_ENDED(1642),

	/**
	 * ID: 1643<br>
	 * Message: Current Location: $s1, $s2, $s3 (Dimensional Gap)
	 */
	LOC_DIMENSIONAL_GAP_S1_S2_S3(1643),

	// 1644 - 1648: none

	/**
	 * ID: 1649<br>
	 * Message: Play time is now accumulating.
	 */
	PLAY_TIME_NOW_ACCUMULATING(1649),

	/**
	 * ID: 1650<br>
	 * Message: Due to high server traffic, your login attempt has failed.
	 * Please try again soon.
	 */
	TRY_LOGIN_LATER(1650),

	/**
	 * ID: 1651<br>
	 * Message: The Grand Olympiad Games are not currently in progress.
	 */
	THE_OLYMPIAD_GAME_IS_NOT_CURRENTLY_IN_PROGRESS(1651),

	/**
	 * ID: 1652<br>
	 * Message: You are now recording gameplay.
	 */
	RECORDING_GAMEPLAY_START(1652),

	/**
	 * ID: 1653<br>
	 * Message: Your recording has been successfully stored. ($s1)
	 */
	RECORDING_GAMEPLAY_STOP_S1(1653),

	/**
	 * ID: 1654<br>
	 * Message: Your attempt to record the replay file has failed.
	 */
	RECORDING_GAMEPLAY_FAILED(1654),

	/**
	 * ID: 1655<br>
	 * Message: You caught something smelly and scary, maybe you should throw it
	 * back!?
	 */
	YOU_CAUGHT_SOMETHING_SMELLY_THROW_IT_BACK(1655),

	/**
	 * ID: 1656<br>
	 * Message: You have successfully traded the item with the NPC.
	 */
	SUCCESSFULLY_TRADED_WITH_NPC(1656),

	/**
	 * ID: 1657<br>
	 * Message: $c1 has earned $s2 points in the Grand Olympiad Games.
	 */
	C1_HAS_GAINED_S2_OLYMPIAD_POINTS(1657),

	/**
	 * ID: 1658<br>
	 * Message: $c1 has lost $s2 points in the Grand Olympiad Games.
	 */
	C1_HAS_LOST_S2_OLYMPIAD_POINTS(1658),

	/**
	 * ID: 1659<br>
	 * Message: Current Location: $s1, $s2, $s3 (Cemetery of the Empire)
	 */
	LOC_CEMETARY_OF_THE_EMPIRE_S1_S2_S3(1659),

	/**
	 * ID: 1660<br>
	 * Message: Channel Creator: $c1.
	 */
	CHANNEL_CREATOR_C1(1660),

	/**
	 * ID: 1661<br>
	 * Message: $c1 has obtained $s3 $s2s.
	 */
	C1_OBTAINED_S3_S2_S(1661),

	/**
	 * ID: 1662<br>
	 * Message: The fish are no longer biting here because you've caught too
	 * many! Try fishing in another location.
	 */
	FISH_NO_MORE_BITING_TRY_OTHER_LOCATION(1662),

	/**
	 * ID: 1663<br>
	 * Message: The clan crest was successfully registered. Remember, only a
	 * clan that owns a clan hall or castle can have their crest displayed.
	 */
	CLAN_EMBLEM_WAS_SUCCESSFULLY_REGISTERED(1663),

	/**
	 * ID: 1664<br>
	 * Message: The fish is resisting your efforts to haul it in! Look at that
	 * bobber go!
	 */
	FISH_RESISTING_LOOK_BOBBLER(1664),

	/**
	 * ID: 1665<br>
	 * Message: You've worn that fish out! It can't even pull the bobber under
	 * the water!
	 */
	YOU_WORN_FISH_OUT(1665),

	/**
	 * ID: 1666<br>
	 * Message: You have obtained +$s1 $s2.
	 */
	OBTAINED_S1_S2(1666),

	/**
	 * ID: 1667<br>
	 * Message: Lethal Strike!
	 */
	LETHAL_STRIKE(1667),

	/**
	 * ID: 1668<br>
	 * Message: Your lethal strike was successful!
	 */
	LETHAL_STRIKE_SUCCESSFUL(1668),

	/**
	 * ID: 1669<br>
	 * Message: There was nothing found inside of that.
	 */
	NOTHING_INSIDE_THAT(1669),

	/**
	 * ID: 1670<br>
	 * Message: Due to your Reeling and/or Pumping skill being three or more
	 * levels higher than your Fishing skill, a 50 damage penalty will be
	 * applied.
	 */
	REELING_PUMPING_3_LEVELS_HIGHER_THAN_FISHING_PENALTY(1670),

	/**
	 * ID: 1671<br>
	 * Message: Your reeling was successful! (Mastery Penalty:$s1 )
	 */
	REELING_SUCCESSFUL_PENALTY_S1(1671),

	/**
	 * ID: 1672<br>
	 * Message: Your pumping was successful! (Mastery Penalty:$s1 )
	 */
	PUMPING_SUCCESSFUL_PENALTY_S1(1672),

	/**
	 * ID: 1673<br>
	 * Message: Your current record for this Grand Olympiad is $s1 match(es),
	 * $s2 win(s) and $s3 defeat(s). You have earned $s4 Olympiad Point(s).
	 */
	THE_CURRENT_RECORD_FOR_THIS_OLYMPIAD_SESSION_IS_S1_MATCHES_S2_WINS_S3_DEFEATS_YOU_HAVE_EARNED_S4_OLYMPIAD_POINTS(
			1673),

	/**
	 * ID: 1674<br>
	 * Message: This command can only be used by a Noblesse.
	 */
	NOBLESSE_ONLY(1674),

	/**
	 * ID: 1675<br>
	 * Message: A manor cannot be set up between 6 a.m. and 8 p.m.
	 */
	A_MANOR_CANNOT_BE_SET_UP_BETWEEN_6_AM_AND_8_PM(1675),

	/**
	 * ID: 1676<br>
	 * Message: You do not have a servitor or pet and therefore cannot use the
	 * automatic-use function.
	 */
	NO_SERVITOR_CANNOT_AUTOMATE_USE(1676),

	/**
	 * ID: 1677<br>
	 * Message: A cease-fire during a Clan War can not be called while members
	 * of your clan are engaged in battle.
	 */
	CANT_STOP_CLAN_WAR_WHILE_IN_COMBAT(1677),

	/**
	 * ID: 1678<br>
	 * Message: You have not declared a Clan War against the clan $s1.
	 */
	NO_CLAN_WAR_AGAINST_CLAN_S1(1678),

	/**
	 * ID: 1679<br>
	 * Message: Only the creator of a channel can issue a global command.
	 */
	ONLY_CHANNEL_CREATOR_CAN_GLOBAL_COMMAND(1679),

	/**
	 * ID: 1680<br>
	 * Message: $c1 has declined the channel invitation.
	 */
	C1_DECLINED_CHANNEL_INVITATION(1680),

	/**
	 * ID: 1681<br>
	 * Message: Since $c1 did not respond, your channel invitation has failed.
	 */
	C1_DID_NOT_RESPOND_CHANNEL_INVITATION_FAILED(1681),

	/**
	 * ID: 1682<br>
	 * Message: Only the creator of a channel can use the channel dismiss
	 * command.
	 */
	ONLY_CHANNEL_CREATOR_CAN_DISMISS(1682),

	/**
	 * ID: 1683<br>
	 * Message: Only a party leader can choose the option to leave a channel.
	 */
	ONLY_PARTY_LEADER_CAN_LEAVE_CHANNEL(1683),

	/**
	 * ID: 1684<br>
	 * Message: A Clan War can not be declared against a clan that is being
	 * dissolved.
	 */
	NO_CLAN_WAR_AGAINST_DISSOLVING_CLAN(1684),

	/**
	 * ID: 1685<br>
	 * Message: You are unable to equip this item when your PK count is greater
	 * or equal to one.
	 */
	YOU_ARE_UNABLE_TO_EQUIP_THIS_ITEM_WHEN_YOUR_PK_COUNT_IS_GREATER_THAN_OR_EQUAL_TO_ONE(
			1685),

	/**
	 * ID: 1686<br>
	 * Message: Stones and mortar tumble to the earth - the castle wall has
	 * taken damage!
	 */
	CASTLE_WALL_DAMAGED(1686),

	/**
	 * ID: 1687<br>
	 * Message: This area cannot be entered while mounted atop of a Wyvern. You
	 * will be dismounted from your Wyvern if you do not leave!
	 */
	AREA_CANNOT_BE_ENTERED_WHILE_MOUNTED_WYVERN(1687),

	/**
	 * ID: 1688<br>
	 * Message: You cannot enchant while operating a Private Store or Private
	 * Workshop.
	 */
	CANNOT_ENCHANT_WHILE_STORE(1688),

	/**
	 * ID: 1689<br>
	 * Message: $c1 is already registered on the class match waiting list.
	 */
	C1_IS_ALREADY_REGISTERED_ON_THE_CLASS_MATCH_WAITING_LIST(1689),

	/**
	 * ID: 1690<br>
	 * Message: $c1 is already registered on the waiting list for the
	 * non-class-limited individual match event.
	 */
	C1_IS_ALREADY_REGISTERED_ON_THE_NON_CLASS_LIMITED_MATCH_WAITING_LIST(1690),

	/**
	 * ID: 1691<br>
	 * Message: $c1% does not meet the participation requirements. You cannot
	 * participate in the Olympiad because your inventory slot exceeds 80%.
	 */
	C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_INVENTORY_SLOT_EXCEEDS_80_PERCENT(1691),

	/**
	 * ID: 1692<br>
	 * Message: $c1% does not meet the participation requirements. You cannot
	 * participate in the Olympiad because you have changed to your sub-class.
	 */
	C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_WHILE_CHANGED_TO_SUB_CLASS(1692),

	/**
	 * ID: 1693<br>
	 * Message: You may not observe a Grand Olympiad Games match while you are
	 * on the waiting list.
	 */
	WHILE_YOU_ARE_ON_THE_WAITING_LIST_YOU_ARE_NOT_ALLOWED_TO_WATCH_THE_GAME(
			1693),

	/**
	 * ID: 1694<br>
	 * Message: Only a clan leader that is a Noblesse can view the Siege War
	 * Status window during a siege war.
	 */
	ONLY_NOBLESSE_LEADER_CAN_VIEW_SIEGE_STATUS_WINDOW(1694),

	/**
	 * ID: 1695<br>
	 * Message: You can only use that during a Siege War!
	 */
	ONLY_DURING_SIEGE(1695),

	/**
	 * ID: 1696<br>
	 * Message: Your accumulated play time is $s1.
	 */
	ACCUMULATED_PLAY_TIME_IS_S1(1696),

	/**
	 * ID: 1697<br>
	 * Message: Your accumulated play time has reached Fatigue level, so you
	 * will receive experience or item drops at only 50 percent [...]
	 */
	ACCUMULATED_PLAY_TIME_WARNING1(1697),

	/**
	 * ID: 1698<br>
	 * Message: Your accumulated play time has reached Ill-health level, so you
	 * will no longer gain experience or item drops. [...}
	 */
	ACCUMULATED_PLAY_TIME_WARNING2(1698),

	/**
	 * ID: 1699<br>
	 * Message: You cannot dismiss a party member by force.
	 */
	CANNOT_DISMISS_PARTY_MEMBER(1699),

	/**
	 * ID: 1700<br>
	 * Message: You don't have enough spiritshots needed for a pet/servitor.
	 */
	NOT_ENOUGH_SPIRITHOTS_FOR_PET(1700),

	/**
	 * ID: 1701<br>
	 * Message: You don't have enough soulshots needed for a pet/servitor.
	 */
	NOT_ENOUGH_SOULSHOTS_FOR_PET(1701),

	/**
	 * ID: 1702<br>
	 * Message: $s1 is using a third party program.
	 */
	S1_USING_THIRD_PARTY_PROGRAM(1702),

	/**
	 * ID: 1703<br>
	 * Message: The previous investigated user is not using a third party
	 * program
	 */
	NOT_USING_THIRD_PARTY_PROGRAM(1703),

	/**
	 * ID: 1704<br>
	 * Message: Please close the setup window for your private manufacturing
	 * store or private store, and try again.
	 */
	CLOSE_STORE_WINDOW_AND_TRY_AGAIN(1704),

	/**
	 * ID: 1705<br>
	 * Message: PC Bang Points acquisition period. Points acquisition period
	 * left $s1 hour.
	 */
	PCPOINT_ACQUISITION_PERIOD(1705),

	/**
	 * ID: 1706<br>
	 * Message: PC Bang Points use period. Points acquisition period left $s1
	 * hour.
	 */
	PCPOINT_USE_PERIOD(1706),

	/**
	 * ID: 1707<br>
	 * Message: You acquired $s1 PC Bang Point.
	 */
	ACQUIRED_S1_PCPOINT(1707),

	/**
	 * ID: 1708<br>
	 * Message: Double points! You acquired $s1 PC Bang Point.
	 */
	ACQUIRED_S1_PCPOINT_DOUBLE(1708),

	/**
	 * ID: 1709<br>
	 * Message: You are using $s1 point.
	 */
	USING_S1_PCPOINT(1709),

	/**
	 * ID: 1710<br>
	 * Message: You are short of accumulated points.
	 */
	SHORT_OF_ACCUMULATED_POINTS(1710),

	/**
	 * ID: 1711<br>
	 * Message: PC Bang Points use period has expired.
	 */
	PCPOINT_USE_PERIOD_EXPIRED(1711),

	/**
	 * ID: 1712<br>
	 * Message: The PC Bang Points accumulation period has expired.
	 */
	PCPOINT_ACCUMULATION_PERIOD_EXPIRED(1712),

	/**
	 * ID: 1713<br>
	 * Message: The games may be delayed due to an insufficient number of
	 * players waiting.
	 */
	GAMES_DELAYED(1713),

	/**
	 * ID: 1714<br>
	 * Message: Current Location: $s1, $s2, $s3 (Near the Town of Schuttgart)
	 */
	LOC_SCHUTTGART_S1_S2_S3(1714),

	/**
	 * ID: 1715<br>
	 * Message: This is a Peaceful Zone
	 */
	PEACEFUL_ZONE(1715),

	/**
	 * ID: 1716<br>
	 * Message: Altered Zone
	 */
	ALTERED_ZONE(1716),

	/**
	 * ID: 1717<br>
	 * Message: Siege War Zone
	 */
	SIEGE_ZONE(1717),

	/**
	 * ID: 1718<br>
	 * Message: General Field
	 */
	GENERAL_ZONE(1718),

	/**
	 * ID: 1719<br>
	 * Message: Seven Signs Zone
	 */
	SEVENSIGNS_ZONE(1719),

	/**
	 * ID: 1720<br>
	 * Message: ---
	 */
	UNKNOWN1(1720),

	/**
	 * ID: 1721<br>
	 * Message: Combat Zone
	 */
	COMBAT_ZONE(1721),

	/**
	 * ID: 1722<br>
	 * Message: Please enter the name of the item you wish to search for.
	 */
	ENTER_ITEM_NAME_SEARCH(1722),

	/**
	 * ID: 1723<br>
	 * Message: Please take a moment to provide feedback about the petition
	 * service.
	 */
	PLEASE_PROVIDE_PETITION_FEEDBACK(1723),

	/**
	 * ID: 1724<br>
	 * Message: A servitor whom is engaged in battle cannot be de-activated.
	 */
	SERVITOR_NOT_RETURN_IN_BATTLE(1724),

	/**
	 * ID: 1725<br>
	 * Message: You have earned $s1 raid point(s).
	 */
	EARNED_S1_RAID_POINTS(1725),

	/**
	 * ID: 1726<br>
	 * Message: $s1 has disappeared because its time period has expired.
	 */
	S1_PERIOD_EXPIRED_DISAPPEARED(1726),

	/**
	 * ID: 1727<br>
	 * Message: $c1 has invited you to a party room. Do you accept?
	 */
	C1_INVITED_YOU_TO_PARTY_ROOM_CONFIRM(1727),

	/**
	 * ID: 1728<br>
	 * Message: The recipient of your invitation did not accept the party
	 * matching invitation.
	 */
	PARTY_MATCHING_REQUEST_NO_RESPONSE(1728),

	/**
	 * ID: 1729<br>
	 * Message: You cannot join a Command Channel while teleporting.
	 */
	NOT_JOIN_CHANNEL_WHILE_TELEPORTING(1729),

	/**
	 * ID: 1730<br>
	 * Message: To establish a Clan Academy, your clan must be Level 5 or
	 * higher.
	 */
	YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN_ACADEMY(1730),

	/**
	 * ID: 1731<br>
	 * Message: Only the leader can create a Clan Academy.
	 */
	ONLY_LEADER_CAN_CREATE_ACADEMY(1731),

	/**
	 * ID: 1732<br>
	 * Message: To create a Clan Academy, a Blood Mark is needed.
	 */
	NEED_BLOODMARK_FOR_ACADEMY(1732),

	/**
	 * ID: 1733<br>
	 * Message: You do not have enough adena to create a Clan Academy.
	 */
	NEED_ADENA_FOR_ACADEMY(1733),

	/**
	 * ID: 1734<br>
	 * not belong another clan and not yet completed their 2nd class transfer.
	 */
	ACADEMY_REQUIREMENTS(1734),

	/**
	 * ID: 1735<br>
	 * Message: $s1 does not meet the requirements to join a Clan Academy.
	 */
	S1_DOESNOT_MEET_REQUIREMENTS_TO_JOIN_ACADEMY(1735),

	/**
	 * ID: 1736<br>
	 * Message: The Clan Academy has reached its maximum enrollment.
	 */
	ACADEMY_MAXIMUM(1736),

	/**
	 * ID: 1737<br>
	 * Message: Your clan has not established a Clan Academy but is eligible to
	 * do so.
	 */
	CLAN_CAN_CREATE_ACADEMY(1737),

	/**
	 * ID: 1738<br>
	 * Message: Your clan has already established a Clan Academy.
	 */
	CLAN_HAS_ALREADY_ESTABLISHED_A_CLAN_ACADEMY(1738),

	/**
	 * ID: 1739<br>
	 * Message: Would you like to create a Clan Academy?
	 */
	CLAN_ACADEMY_CREATE_CONFIRM(1739),

	/**
	 * ID: 1740<br>
	 * Message: Please enter the name of the Clan Academy.
	 */
	ACADEMY_CREATE_ENTER_NAME(1740),

	/**
	 * ID: 1741<br>
	 * Message: Congratulations! The $s1's Clan Academy has been created.
	 */
	THE_S1S_CLAN_ACADEMY_HAS_BEEN_CREATED(1741),

	/**
	 * ID: 1742<br>
	 * Message: A message inviting $s1 to join the Clan Academy is being sent.
	 */
	ACADEMY_INVITATION_SENT_TO_S1(1742),

	/**
	 * ID: 1743<br>
	 * Message: To open a Clan Academy, the leader of a Level 5 clan or above
	 * must pay XX Proofs of Blood or a certain amount of adena.
	 */
	OPEN_ACADEMY_CONDITIONS(1743),

	/**
	 * ID: 1744<br>
	 * Message: There was no response to your invitation to join the Clan
	 * Academy, so the invitation has been rescinded.
	 */
	ACADEMY_JOIN_NO_RESPONSE(1744),

	/**
	 * ID: 1745<br>
	 * Message: The recipient of your invitation to join the Clan Academy has
	 * declined.
	 */
	ACADEMY_JOIN_DECLINE(1745),

	/**
	 * ID: 1746<br>
	 * Message: You have already joined a Clan Academy.
	 */
	ALREADY_JOINED_ACADEMY(1746),

	/**
	 * ID: 1747<br>
	 * Message: $s1 has sent you an invitation to join the Clan Academy
	 * belonging to the $s2 clan. Do you accept?
	 */
	JOIN_ACADEMY_REQUEST_BY_S1_FOR_CLAN_S2(1747),

	/**
	 * ID: 1748<br>
	 * Message: Clan Academy member $s1 has successfully completed the 2nd class
	 * transfer and obtained $s2 Clan Reputation points.
	 */
	CLAN_MEMBER_GRADUATED_FROM_ACADEMY(1748),

	/**
	 * ID: 1749<br>
	 * Message: Congratulations! You will now graduate from the Clan Academy and
	 * leave your current clan. As a graduate of the academy, you can
	 * immediately join a clan as a regular member without being subject to any
	 * penalties.
	 */
	ACADEMY_MEMBERSHIP_TERMINATED(1749),

	/**
	 * ID: 1750<br>
	 * Message: $c1% does not meet the participation requirements. The owner of
	 * $s2 cannot participate in the Olympiad.
	 */
	C1_CANNOT_JOIN_OLYMPIAD_POSSESSING_S2(1750),

	/**
	 * ID: 1751<br>
	 * Message: The Grand Master has given you a commemorative item.
	 */
	GRAND_MASTER_COMMEMORATIVE_ITEM(1751),

	/**
	 * ID: 1752<br>
	 * Message: Since the clan has received a graduate of the Clan Academy, it
	 * has earned $s1 points towards its reputation score.
	 */
	MEMBER_GRADUATED_EARNED_S1_REPU(1752),

	/**
	 * ID: 1753<br>
	 * Message: The clan leader has decreed that that particular privilege
	 * cannot be granted to a Clan Academy member.
	 */
	CANT_TRANSFER_PRIVILEGE_TO_ACADEMY_MEMBER(1753),

	/**
	 * ID: 1754<br>
	 * Message: That privilege cannot be granted to a Clan Academy member.
	 */
	RIGHT_CANT_TRANSFERRED_TO_ACADEMY_MEMBER(1754),

	/**
	 * ID: 1755<br>
	 * Message: $s2 has been designated as the apprentice of clan member $s1.
	 */
	S2_HAS_BEEN_DESIGNATED_AS_APPRENTICE_OF_CLAN_MEMBER_S1(1755),

	/**
	 * ID: 1756<br>
	 * Message: Your apprentice, $s1, has logged in.
	 */
	YOUR_APPRENTICE_S1_HAS_LOGGED_IN(1756),

	/**
	 * ID: 1757<br>
	 * Message: Your apprentice, $c1, has logged out.
	 */
	YOUR_APPRENTICE_C1_HAS_LOGGED_OUT(1757),

	/**
	 * ID: 1758<br>
	 * Message: Your sponsor, $c1, has logged in.
	 */
	YOUR_SPONSOR_C1_HAS_LOGGED_IN(1758),

	/**
	 * ID: 1759<br>
	 * Message: Your sponsor, $c1, has logged out.
	 */
	YOUR_SPONSOR_C1_HAS_LOGGED_OUT(1759),

	/**
	 * ID: 1760<br>
	 * Message: Clan member $c1's name title has been changed to $2.
	 */
	CLAN_MEMBER_C1_TITLE_CHANGED_TO_S2(1760),

	/**
	 * ID: 1761<br>
	 * Message: Clan member $c1's privilege level has been changed to $s2.
	 */
	CLAN_MEMBER_C1_PRIVILEGE_CHANGED_TO_S2(1761),

	/**
	 * ID: 1762<br>
	 * Message: You do not have the right to dismiss an apprentice.
	 */
	YOU_DO_NOT_HAVE_THE_RIGHT_TO_DISMISS_AN_APPRENTICE(1762),

	/**
	 * ID: 1763<br>
	 * Message: $s2, clan member $c1's apprentice, has been removed.
	 */
	S2_CLAN_MEMBER_C1_APPRENTICE_HAS_BEEN_REMOVED(1763),

	/**
	 * ID: 1764<br>
	 * Message: This item can only be worn by a member of the Clan Academy.
	 */
	EQUIP_ONLY_FOR_ACADEMY(1764),

	/**
	 * ID: 1765<br>
	 * Message: As a graduate of the Clan Academy, you can no longer wear this
	 * item.
	 */
	EQUIP_NOT_FOR_GRADUATES(1765),

	/**
	 * ID: 1766<br>
	 * Message: An application to join the clan has been sent to $c1 in $s2.
	 */
	CLAN_JOIN_APPLICATION_SENT_TO_C1_IN_S2(1766),

	/**
	 * ID: 1767<br>
	 * Message: An application to join the clan Academy has been sent to $c1.
	 */
	ACADEMY_JOIN_APPLICATION_SENT_TO_C1(1767),

	/**
	 * ID: 1768<br>
	 * Message: $c1 has invited you to join the Clan Academy of $s2 clan. Would
	 * you like to join?
	 */
	JOIN_REQUEST_BY_C1_TO_CLAN_S2_ACADEMY(1768),

	/**
	 * ID: 1769<br>
	 * Message: $c1 has sent you an invitation to join the $s3 Order of Knights
	 * under the $s2 clan. Would you like to join?
	 */
	JOIN_REQUEST_BY_C1_TO_ORDER_OF_KNIGHTS_S3_UNDER_CLAN_S2(1769),

	/**
	 * ID: 1770<br>
	 * Message: The clan's reputation score has dropped below 0. The clan may
	 * face certain penalties as a result.
	 */
	CLAN_REPU_0_MAY_FACE_PENALTIES(1770),

	/**
	 * ID: 1771<br>
	 * Message: Now that your clan level is above Level 5, it can accumulate
	 * clan reputation points.
	 */
	CLAN_CAN_ACCUMULATE_CLAN_REPUTATION_POINTS(1771),

	/**
	 * ID: 1772<br>
	 * Message: Since your clan was defeated in a siege, $s1 points have been
	 * deducted from your clan's reputation score and given to the opposing
	 * clan.
	 */
	CLAN_WAS_DEFEATED_IN_SIEGE_AND_LOST_S1_REPUTATION_POINTS(1772),

	/**
	 * ID: 1773<br>
	 * Message: Since your clan emerged victorious from the siege, $s1 points
	 * have been added to your clan's reputation score.
	 */
	CLAN_VICTORIOUS_IN_SIEGE_AND_GAINED_S1_REPUTATION_POINTS(1773),

	/**
	 * ID: 1774<br>
	 * Message: Your clan's newly acquired contested clan hall has added $s1
	 * points to your clan's reputation score.
	 */
	CLAN_ACQUIRED_CONTESTED_CLAN_HALL_AND_S1_REPUTATION_POINTS(1774),

	/**
	 * ID: 1775<br>
	 * Message: Clan member $c1 was an active member of the highest-ranked party
	 * in the Festival of Darkness. $s2 points have been added to your clan's
	 * reputation score.
	 */
	CLAN_MEMBER_C1_WAS_IN_HIGHEST_RANKED_PARTY_IN_FESTIVAL_OF_DARKNESS_AND_GAINED_S2_REPUTATION(
			1775),

	/**
	 * ID: 1776<br>
	 * Message: Clan member $c1 was named a hero. $2s points have been added to
	 * your clan's reputation score.
	 */
	CLAN_MEMBER_C1_BECAME_HERO_AND_GAINED_S2_REPUTATION_POINTS(1776),

	/**
	 * ID: 1777<br>
	 * Message: You have successfully completed a clan quest. $s1 points have
	 * been added to your clan's reputation score.
	 */
	CLAN_QUEST_COMPLETED_AND_S1_POINTS_GAINED(1777),

	/**
	 * ID: 1778<br>
	 * Message: An opposing clan has captured your clan's contested clan hall.
	 * $s1 points have been deducted from your clan's reputation score.
	 */
	OPPOSING_CLAN_CAPTURED_CLAN_HALL_AND_YOUR_CLAN_LOSES_S1_POINTS(1778),

	/**
	 * ID: 1779<br>
	 * Message: After losing the contested clan hall, 300 points have been
	 * deducted from your clan's reputation score.
	 */
	CLAN_LOST_CONTESTED_CLAN_HALL_AND_300_POINTS(1779),

	/**
	 * ID: 1780<br>
	 * Message: Your clan has captured your opponent's contested clan hall. $s1
	 * points have been deducted from your opponent's clan reputation score.
	 */
	CLAN_CAPTURED_CONTESTED_CLAN_HALL_AND_S1_POINTS_DEDUCTED_FROM_OPPONENT(1780),

	/**
	 * ID: 1781<br>
	 * Message: Your clan has added $1s points to its clan reputation score.
	 */
	CLAN_ADDED_S1S_POINTS_TO_REPUTATION_SCORE(1781),

	/**
	 * ID: 1782<br>
	 * Message: Your clan member $c1 was killed. $s2 points have been deducted
	 * from your clan's reputation score and added to your opponent's clan
	 * reputation score.
	 */
	CLAN_MEMBER_C1_WAS_KILLED_AND_S2_POINTS_DEDUCTED_FROM_REPUTATION(1782),

	/**
	 * ID: 1783<br>
	 * Message: For killing an opposing clan member, $s1 points have been
	 * deducted from your opponents' clan reputation score.
	 */
	FOR_KILLING_OPPOSING_MEMBER_S1_POINTS_WERE_DEDUCTED_FROM_OPPONENTS(1783),

	/**
	 * ID: 1784<br>
	 * Message: Your clan has failed to defend the castle. $s1 points have been
	 * deducted from your clan's reputation score and added to your opponents'.
	 */
	YOUR_CLAN_FAILED_TO_DEFEND_CASTLE_AND_S1_POINTS_LOST_AND_ADDED_TO_OPPONENT(
			1784),

	/**
	 * ID: 1785<br>
	 * Message: The clan you belong to has been initialized. $s1 points have
	 * been deducted from your clan reputation score.
	 */
	YOUR_CLAN_HAS_BEEN_INITIALIZED_AND_S1_POINTS_LOST(1785),

	/**
	 * ID: 1786<br>
	 * Message: Your clan has failed to defend the castle. $s1 points have been
	 * deducted from your clan's reputation score.
	 */
	YOUR_CLAN_FAILED_TO_DEFEND_CASTLE_AND_S1_POINTS_LOST(1786),

	/**
	 * ID: 1787<br>
	 * Message: $s1 points have been deducted from the clan's reputation score.
	 */
	S1_DEDUCTED_FROM_CLAN_REP(1787),

	/**
	 * ID: 1788<br>
	 * Message: The clan skill $s1 has been added.
	 */
	CLAN_SKILL_S1_ADDED(851),

	/**
	 * ID: 1789<br>
	 * Message: Since the Clan Reputation Score has dropped to 0 or lower, your
	 * clan skill(s) will be de-activated.
	 */
	REPUTATION_POINTS_0_OR_LOWER_CLAN_SKILLS_DEACTIVATED(1789),

	/**
	 * ID: 1790<br>
	 * Message: The conditions necessary to increase the clan's level have not
	 * been met.
	 */
	FAILED_TO_INCREASE_CLAN_LEVEL(1790),

	/**
	 * ID: 1791<br>
	 * Message: The conditions necessary to create a military unit have not been
	 * met.
	 */
	YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_MILITARY_UNIT(1791),

	/**
	 * ID: 1792<br>
	 * Message: Please assign a manager for your new Order of Knights.
	 */
	ASSIGN_MANAGER_FOR_ORDER_OF_KNIGHTS(1792),

	/**
	 * ID: 1793<br>
	 * Message: $c1 has been selected as the captain of $s2.
	 */
	C1_HAS_BEEN_SELECTED_AS_CAPTAIN_OF_S2(1793),

	/**
	 * ID: 1794<br>
	 * Message: The Knights of $s1 have been created.
	 */
	THE_KNIGHTS_OF_S1_HAVE_BEEN_CREATED(1794),

	/**
	 * ID: 1795<br>
	 * Message: The Royal Guard of $s1 have been created.
	 */
	THE_ROYAL_GUARD_OF_S1_HAVE_BEEN_CREATED(1795),

	/**
	 * ID: 1796<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE17(1796),

	/**
	 * ID: 1797<br>
	 * Message: $c1 has been promoted to $s2.
	 */
	C1_PROMOTED_TO_S2(1797),

	/**
	 * ID: 1798<br>
	 * Message: Clan lord privileges have been transferred to $c1.
	 */
	CLAN_LEADER_PRIVILEGES_HAVE_BEEN_TRANSFERRED_TO_C1(1798),

	/**
	 * ID: 1799<br>
	 * Message: We are searching for BOT users. Please try again later.
	 */
	SEARCHING_FOR_BOT_USERS_TRY_AGAIN_LATER(184),

	/**
	 * ID: 1800<br>
	 * Message: User $c1 has a history of using BOT.
	 */
	C1_HISTORY_USING_BOT(1800),

	/**
	 * ID: 1801<br>
	 * Message: The attempt to sell has failed.
	 */
	SELL_ATTEMPT_FAILED(1801),

	/**
	 * ID: 1802<br>
	 * Message: The attempt to trade has failed.
	 */
	TRADE_ATTEMPT_FAILED(1802),

	/**
	 * ID: 1803<br>
	 * Message: The request to participate in the game cannot be made starting
	 * from 10 minutes before the end of the game.
	 */
	GAME_REQUEST_CANNOT_BE_MADE(1803),

	/**
	 * ID: 1804<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE18(1804),

	/**
	 * ID: 1805<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE19(1805),

	/**
	 * ID: 1806<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE20(1806),

	/**
	 * ID: 1807<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE21(1807),

	/**
	 * ID: 1808<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE22(1808),

	/**
	 * ID: 1809<br>
	 * please visit the PlayNC website (http://www.plaync.com/us/support/)
	 */
	ACCOUNT_MUST_VERIFIED(1809),

	/**
	 * ID: 1810<br>
	 * Message: The refuse invitation state has been activated.
	 */
	REFUSE_INVITATION_ACTIVATED(1810),

	/**
	 * ID: 1812<br>
	 * Message: Since the refuse invitation state is currently activated, no
	 * invitation can be made
	 */
	REFUSE_INVITATION_CURRENTLY_ACTIVE(1812),

	/**
	 * ID: 1813<br>
	 * Message: $s1 has $s2 hour(s) of usage time remaining.
	 */
	THERE_IS_S1_HOUR_AND_S2_MINUTE_LEFT_OF_THE_FIXED_USAGE_TIME(1813),

	/**
	 * ID: 1814<br>
	 * Message: $s1 has $s2 minute(s) of usage time remaining.
	 */
	S2_MINUTE_OF_USAGE_TIME_ARE_LEFT_FOR_S1(1814),

	/**
	 * ID: 1815<br>
	 * Message: $s2 was dropped in the $s1 region.
	 */
	S2_WAS_DROPPED_IN_THE_S1_REGION(1815),

	/**
	 * ID: 1816<br>
	 * Message: The owner of $s2 has appeared in the $s1 region.
	 */
	THE_OWNER_OF_S2_HAS_APPEARED_IN_THE_S1_REGION(1816),

	/**
	 * ID: 1817<br>
	 * Message: $s2's owner has logged into the $s1 region.
	 */
	S2_OWNER_HAS_LOGGED_INTO_THE_S1_REGION(1817),

	/**
	 * ID: 1818<br>
	 * Message: $s1 has disappeared.
	 */
	S1_HAS_DISAPPEARED(1818),

	/**
	 * ID: 1819<br>
	 * Message: An evil is pulsating from $s2 in $s1.
	 */
	EVIL_FROM_S2_IN_S1(1819),

	/**
	 * ID: 1820<br>
	 * Message: $s1 is currently asleep.
	 */
	S1_CURRENTLY_SLEEP(1820),

	/**
	 * ID: 1821<br>
	 * Message: $s2's evil presence is felt in $s1.
	 */
	S2_EVIL_PRESENCE_FELT_IN_S1(1821),

	/**
	 * ID: 1822<br>
	 * Message: $s1 has been sealed.
	 */
	S1_SEALED(1822),

	/**
	 * ID: 1823<br>
	 * Message: The registration period for a clan hall war has ended.
	 */
	CLANHALL_WAR_REGISTRATION_PERIOD_ENDED(1823),

	/**
	 * ID: 1824<br>
	 * Message: You have been registered for a clan hall war. Please move to the
	 * left side of the clan hall's arena and get ready.
	 */
	REGISTERED_FOR_CLANHALL_WAR(1824),

	/**
	 * ID: 1825<br>
	 * Message: You have failed in your attempt to register for the clan hall
	 * war. Please try again.
	 */
	CLANHALL_WAR_REGISTRATION_FAILED(1825),

	/**
	 * ID: 1826<br>
	 * Message: In $s1 minute(s), the game will begin. All players must hurry
	 * and move to the left side of the clan hall's arena.
	 */
	CLANHALL_WAR_BEGINS_IN_S1_MINUTES(1826),

	/**
	 * ID: 1827<br>
	 * Message: In $s1 minute(s), the game will begin. All players must, please
	 * enter the arena now
	 */
	CLANHALL_WAR_BEGINS_IN_S1_MINUTES_ENTER_NOW(1827),

	/**
	 * ID: 1828<br>
	 * Message: In $s1 seconds(s), the game will begin.
	 */
	CLANHALL_WAR_BEGINS_IN_S1_SECONDS(1828),

	/**
	 * ID: 1829<br>
	 * Message: The Command Channel is full.
	 */
	COMMAND_CHANNEL_FULL(1829),

	/**
	 * ID: 1830<br>
	 * Message: $c1 is not allowed to use the party room invite command. Please
	 * update the waiting list.
	 */
	C1_NOT_ALLOWED_INVITE_TO_PARTY_ROOM(1830),

	/**
	 * ID: 1831<br>
	 * Message: $c1 does not meet the conditions of the party room. Please
	 * update the waiting list.
	 */
	C1_NOT_MEET_CONDITIONS_FOR_PARTY_ROOM(1831),

	/**
	 * ID: 1832<br>
	 * Message: Only a room leader may invite others to a party room.
	 */
	ONLY_ROOM_LEADER_CAN_INVITE(1832),

	/**
	 * ID: 1833<br>
	 * Message: All of $s1 will be dropped. Would you like to continue?
	 */
	CONFIRM_DROP_ALL_OF_S1(1833),

	/**
	 * ID: 1834<br>
	 * Message: The party room is full. No more characters can be invitet in
	 */
	PARTY_ROOM_FULL(1834),

	/**
	 * ID: 1835<br>
	 * Message: $s1 is full and cannot accept additional clan members at this
	 * time.
	 */
	S1_CLAN_IS_FULL(1835),

	/**
	 * ID: 1836<br>
	 * Message: You cannot join a Clan Academy because you have successfully
	 * completed your 2nd class transfer.
	 */
	CANNOT_JOIN_ACADEMY_AFTER_2ND_OCCUPATION(1836),

	/**
	 * ID: 1837<br>
	 * Message: $c1 has sent you an invitation to join the $s3 Royal Guard under
	 * the $s2 clan. Would you like to join?
	 */
	C1_SENT_INVITATION_TO_ROYAL_GUARD_S3_OF_CLAN_S2(1837),

	/**
	 * ID: 1838<br>
	 * Message: 1. The coupon an be used once per character.
	 */
	COUPON_ONCE_PER_CHARACTER(1838),

	/**
	 * ID: 1839<br>
	 * Message: 2. A used serial number may not be used again.
	 */
	SERIAL_MAY_USED_ONCE(1839),

	/**
	 * ID: 1840<br>
	 * Message: 3. If you enter the incorrect serial number more than 5 times,
	 * ...
	 */
	SERIAL_INPUT_INCORRECT(1840),

	/**
	 * ID: 1841<br>
	 * Message: The clan hall war has been cancelled. Not enough clans have
	 * registered.
	 */
	CLANHALL_WAR_CANCELLED(1841),

	/**
	 * ID: 1842<br>
	 * Message: $c1 wishes to summon you from $s2. Do you accept?
	 */
	C1_WISHES_TO_SUMMON_YOU_FROM_S2_DO_YOU_ACCEPT(1842),

	/**
	 * ID: 1843<br>
	 * Message: $c1 is engaged in combat and cannot be summoned.
	 */
	C1_IS_ENGAGED_IN_COMBAT_AND_CANNOT_BE_SUMMONED(1843),

	/**
	 * ID: 1844<br>
	 * Message: $c1 is dead at the moment and cannot be summoned.
	 */
	C1_IS_DEAD_AT_THE_MOMENT_AND_CANNOT_BE_SUMMONED(1844),

	/**
	 * ID: 1845<br>
	 * Message: Hero weapons cannot be destroyed.
	 */
	HERO_WEAPONS_CANT_DESTROYED(1845),

	/**
	 * ID: 1846<br>
	 * Message: You are too far away from the Fenrir to mount it.
	 */
	TOO_FAR_AWAY_FROM_FENRIR_TO_MOUNT(1846),

	/**
	 * ID: 1847<br>
	 * Message: You caught a fish $s1 in length.
	 */
	CAUGHT_FISH_S1_LENGTH(1847),

	/**
	 * ID: 1848<br>
	 * Message: Because of the size of fish caught, you will be registered in
	 * the ranking
	 */
	REGISTERED_IN_FISH_SIZE_RANKING(1848),

	/**
	 * ID: 1849<br>
	 * Message: All of $s1 will be discarded. Would you like to continue?
	 */
	CONFIRM_DISCARD_ALL_OF_S1(1849),

	/**
	 * ID: 1850<br>
	 * Message: The Captain of the Order of Knights cannot be appointed.
	 */
	CAPTAIN_OF_ORDER_OF_KNIGHTS_CANNOT_BE_APPOINTED(1850),

	/**
	 * ID: 1851<br>
	 * Message: The Captain of the Royal Guard cannot be appointed.
	 */
	CAPTAIN_OF_ROYAL_GUARD_CANNOT_BE_APPOINTED(1851),

	/**
	 * ID: 1852<br>
	 * Message: The attempt to acquire the skill has failed because of an
	 * insufficient Clan Reputation Score.
	 */
	ACQUIRE_SKILL_FAILED_BAD_CLAN_REP_SCORE(1852),

	/**
	 * ID: 1853<br>
	 * Message: Quantity items of the same type cannot be exchanged at the same
	 * time
	 */
	CANT_EXCHANGE_QUANTITY_ITEMS_OF_SAME_TYPE(1853),

	/**
	 * ID: 1854<br>
	 * Message: The item was converted successfully.
	 */
	ITEM_CONVERTED_SUCCESSFULLY(1854),

	/**
	 * ID: 1855<br>
	 * Message: Another military unit is already using that name. Please enter a
	 * different name.
	 */
	ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME(1855),

	/**
	 * ID: 1856<br>
	 * Message: Since your opponent is now the owner of $s1, the Olympiad has
	 * been cancelled.
	 */
	OPPONENT_POSSESSES_S1_OLYMPIAD_CANCELLED(1856),

	/**
	 * ID: 1857<br>
	 * Message: $c1 is the owner of $s2 and cannot participate in the Olympiad.
	 */
	C1_OWNS_S2_AND_CANNOT_PARTICIPATE_IN_OLYMPIAD(1857),

	/**
	 * ID: 1858<br>
	 * Message: $c1 is currently dead and cannot participate in the Olympiad.
	 */
	C1_CANNOT_PARTICIPATE_OLYMPIAD_WHILE_DEAD(1858),

	/**
	 * ID: 1859<br>
	 * Message: You exceeded the quantity that can be moved at one time.
	 */
	EXCEEDED_QUANTITY_FOR_MOVED(1859),

	/**
	 * ID: 1860<br>
	 * Message: The Clan Reputation Score is too low.
	 */
	THE_CLAN_REPUTATION_SCORE_IS_TOO_LOW(1860),

	/**
	 * ID: 1861<br>
	 * Message: The clan's crest has been deleted.
	 */
	CLAN_CREST_HAS_BEEN_DELETED(1861),

	/**
	 * ID: 1862<br>
	 * Message: Clan skills will now be activated since the clan's reputation
	 * score is 0 or higher.
	 */
	CLAN_SKILLS_WILL_BE_ACTIVATED_SINCE_REPUTATION_IS_0_OR_HIGHER(1862),

	/**
	 * ID: 1863<br>
	 * Message: $c1 purchased a clan item, reducing the Clan Reputation by $s2
	 * points.
	 */
	C1_PURCHASED_CLAN_ITEM_REDUCING_S2_REPU_POINTS(1863),

	/**
	 * ID: 1864<br>
	 * Message: Your pet/servitor is unresponsive and will not obey any orders.
	 */
	PET_REFUSING_ORDER(1864),

	/**
	 * ID: 1865<br>
	 * Message: Your pet/servitor is currently in a state of distress.
	 */
	PET_IN_STATE_OF_DISTRESS(1865),

	/**
	 * ID: 1866<br>
	 * Message: MP was reduced by $s1.
	 */
	MP_REDUCED_BY_S1(1866),

	/**
	 * ID: 1867<br>
	 * Message: Your opponent's MP was reduced by $s1.
	 */
	YOUR_OPPONENTS_MP_WAS_REDUCED_BY_S1(1867),

	/**
	 * ID: 1868<br>
	 * Message: You cannot exchange an item while it is being used.
	 */
	CANNOT_EXCHANCE_USED_ITEM(1868),

	/**
	 * ID: 1869<br>
	 * Message: $c1 has granted the Command Channel's master party the privilege
	 * of item looting.
	 */
	C1_GRANTED_MASTER_PARTY_LOOTING_RIGHTS(1869),

	/**
	 * ID: 1870<br>
	 * Message: A Command Channel with looting rights already exists.
	 */
	COMMAND_CHANNEL_WITH_LOOTING_RIGHTS_EXISTS(1870),

	/**
	 * ID: 1871<br>
	 * Message: Do you want to dismiss $c1 from the clan?
	 */
	CONFIRM_DISMISS_C1_FROM_CLAN(1871),

	/**
	 * ID: 1872<br>
	 * Message: You have $s1 hour(s) and $s2 minute(s) left.
	 */
	S1_HOURS_S2_MINUTES_LEFT(1872),

	/**
	 * ID: 1873<br>
	 * Message: There are $s1 hour(s) and $s2 minute(s) left in the fixed use
	 * time for this PC Cafe.
	 */
	S1_HOURS_S2_MINUTES_LEFT_FOR_THIS_PCCAFE(1873),

	/**
	 * ID: 1874<br>
	 * Message: There are $s1 minute(s) left for this individual user.
	 */
	S1_MINUTES_LEFT_FOR_THIS_USER(1874),

	/**
	 * ID: 1875<br>
	 * Message: There are $s1 minute(s) left in the fixed use time for this PC
	 * Cafe.
	 */
	S1_MINUTES_LEFT_FOR_THIS_PCCAFE(1875),

	/**
	 * ID: 1876<br>
	 * Message: Do you want to leave $s1 clan?
	 */
	CONFIRM_LEAVE_S1_CLAN(1876),

	/**
	 * ID: 1877<br>
	 * Message: The game will end in $s1 minutes.
	 */
	GAME_WILL_END_IN_S1_MINUTES(1877),

	/**
	 * ID: 1878<br>
	 * Message: The game will end in $s1 seconds.
	 */
	GAME_WILL_END_IN_S1_SECONDS(1878),

	/**
	 * ID: 1879<br>
	 * Message: In $s1 minute(s), you will be teleported outside of the game
	 * arena.
	 */
	IN_S1_MINUTES_TELEPORTED_OUTSIDE_OF_GAME_ARENA(1879),

	/**
	 * ID: 1880<br>
	 * Message: In $s1 seconds(s), you will be teleported outside of the game
	 * arena.
	 */
	IN_S1_SECONDS_TELEPORTED_OUTSIDE_OF_GAME_ARENA(1880),

	/**
	 * ID: 1881<br>
	 * Message: The preliminary match will begin in $s1 second(s). Prepare
	 * yourself.
	 */
	PRELIMINARY_MATCH_BEGIN_IN_S1_SECONDS(1881),

	/**
	 * ID: 1882<br>
	 * Message: Characters cannot be created from this server.
	 */
	CHARACTERS_NOT_CREATED_FROM_THIS_SERVER(1882),

	/**
	 * ID: 1883<br>
	 * Message: There are no offerings I own or I made a bid for.
	 */
	NO_OFFERINGS_OWN_OR_MADE_BID_FOR(1883),

	/**
	 * ID: 1884<br>
	 * Message: Enter the PC Room coupon serial number.
	 */
	ENTER_PCROOM_SERIAL_NUMBER(1884),

	/**
	 * ID: 1885<br>
	 * Message: This serial number cannot be entered. Please try again in
	 * minute(s).
	 */
	SERIAL_NUMBER_CANT_ENTERED(1885),

	/**
	 * ID: 1886<br>
	 * Message: This serial has already been used.
	 */
	SERIAL_NUMBER_ALREADY_USED(1886),

	/**
	 * ID: 1887<br>
	 * Message: Invalid serial number. Your attempt to enter the number has
	 * failed time(s). You will be allowed to make more attempt(s).
	 */
	SERIAL_NUMBER_ENTERING_FAILED(1887),

	/**
	 * ID: 1888<br>
	 * Message: Invalid serial number. Your attempt to enter the number has
	 * failed 5 time(s). Please try again in 4 hours.
	 */
	SERIAL_NUMBER_ENTERING_FAILED_5_TIMES(1888),

	/**
	 * ID: 1889<br>
	 * Message: Congratulations! You have received $s1.
	 */
	CONGRATULATIONS_RECEIVED_S1(1889),

	/**
	 * ID: 1890<br>
	 * Message: Since you have already used this coupon, you may not use this
	 * serial number.
	 */
	ALREADY_USED_COUPON_NOT_USE_SERIAL_NUMBER(1890),

	/**
	 * ID: 1891<br>
	 * Message: You may not use items in a private store or private work shop.
	 */
	NOT_USE_ITEMS_IN_PRIVATE_STORE(1891),

	/**
	 * ID: 1892<br>
	 * Message: The replay file for the previous version cannot be played.
	 */
	REPLAY_FILE_PREVIOUS_VERSION_CANT_PLAYED(1892),

	/**
	 * ID: 1893<br>
	 * Message: This file cannot be replayed.
	 */
	FILE_CANT_REPLAYED(1893),

	/**
	 * ID: 1894<br>
	 * Message: A sub-class cannot be created or changed while you are over your
	 * weight limit.
	 */
	NOT_SUBCLASS_WHILE_OVERWEIGHT(1894),

	/**
	 * ID: 1895<br>
	 * Message: $c1 is in an area which blocks summoning.
	 */
	C1_IN_SUMMON_BLOCKING_AREA(1895),

	/**
	 * ID: 1896<br>
	 * Message: $c1 has already been summoned.
	 */
	C1_ALREADY_SUMMONED(1896),

	/**
	 * ID: 1897<br>
	 * Message: $s1 is required for summoning.
	 */
	S1_REQUIRED_FOR_SUMMONING(1897),

	/**
	 * ID: 1898<br>
	 * Message: $c1 is currently trading or operating a private store and cannot
	 * be summoned.
	 */
	C1_CURRENTLY_TRADING_OR_OPERATING_PRIVATE_STORE_AND_CANNOT_BE_SUMMONED(1898),

	/**
	 * ID: 1899<br>
	 * Message: Your target is in an area which blocks summoning.
	 */
	YOUR_TARGET_IS_IN_AN_AREA_WHICH_BLOCKS_SUMMONING(1899),

	/**
	 * ID: 1900<br>
	 * Message: $c1 has entered the party room.
	 */
	C1_ENTERED_PARTY_ROOM(1900),

	/**
	 * ID: 1901<br>
	 * Message: $c1 has invited you to enter the party room.
	 */
	C1_INVITED_YOU_TO_PARTY_ROOM(1901),

	/**
	 * ID: 1902<br>
	 * Message: Incompatible item grade. This item cannot be used.
	 */
	INCOMPATIBLE_ITEM_GRADE(1902),

	/**
	 * ID: 1903<br>
	 * Message: Those of you who have requested NCOTP should run NCOTP by using
	 * your cell phone [...]
	 */
	NCOTP(1903),

	/**
	 * ID: 1904<br>
	 * Message: A sub-class may not be created or changed while a servitor or
	 * pet is summoned.
	 */
	CANT_SUBCLASS_WITH_SUMMONED_SERVITOR(1904),

	/**
	 * ID: 1905<br>
	 * Message: $s2 of $s1 will be replaced with $s4 of $s3.
	 */
	S2_OF_S1_WILL_REPLACED_WITH_S4_OF_S3(1905),

	/**
	 * ID: 1906<br>
	 * Message: Select the combat unit
	 */
	SELECT_COMBAT_UNIT(1906),

	/**
	 * ID: 1907<br>
	 * Message: Select the character who will [...]
	 */
	SELECT_CHARACTER_WHO_WILL(1907),

	/**
	 * ID: 1908<br>
	 * Message: $c1 in a state which prevents summoning.
	 */
	C1_STATE_FORBIDS_SUMMONING(1908),

	/**
	 * ID: 1909<br>
	 * Message: ==< List of Academy Graduates During the Past Week >==
	 */
	ACADEMY_LIST_HEADER(1909),

	/**
	 * ID: 1910<br>
	 * Message: Graduates: $c1.
	 */
	GRADUATES_C1(1910),

	/**
	 * ID: 1911<br>
	 * Message: You cannot summon players who are currently participating in the
	 * Grand Olympiad.
	 */
	YOU_CANNOT_SUMMON_PLAYERS_WHO_ARE_IN_OLYMPIAD(1911),

	/**
	 * ID: 1912<br>
	 * Message: Only those requesting NCOTP should make an entry into this
	 * field.
	 */
	NCOTP2(1912),

	/**
	 * ID: 1913<br>
	 * Message: The remaining recycle time for $s1 is $s2 minute(s).
	 */
	TIME_FOR_S1_IS_S2_MINUTES_REMAINING(1913),

	/**
	 * ID: 1914<br>
	 * Message: The remaining recycle time for $s1 is $s2 seconds(s).
	 */
	TIME_FOR_S1_IS_S2_SECONDS_REMAINING(1914),

	/**
	 * ID: 1915<br>
	 * Message: The game will end in $s1 second(s).
	 */
	GAME_ENDS_IN_S1_SECONDS(1915),

	/**
	 * ID: 1916<br>
	 * Message: Your Death Penalty is now level $s1.
	 */
	DEATH_PENALTY_LEVEL_S1_ADDED(851),

	/**
	 * ID: 1917<br>
	 * Message: Your Death Penalty has been lifted.
	 */
	DEATH_PENALTY_LIFTED(1917),

	/**
	 * ID: 1918<br>
	 * Message: Your pet is too high level to control.
	 */
	PET_TOO_HIGH_TO_CONTROL(1918),

	/**
	 * ID: 1919<br>
	 * Message: The Grand Olympiad registration period has ended.
	 */
	OLYMPIAD_REGISTRATION_PERIOD_ENDED(1919),

	/**
	 * ID: 1920<br>
	 * Message: Your account is currently inactive because you have not logged
	 * into the game for some time. You may reactivate your account by visiting
	 * the PlayNC website (http://www.plaync.com/us/support/).
	 */
	ACCOUNT_INACTIVITY(1920),

	/**
	 * ID: 1921<br>
	 * Message: $s2 hour(s) and $s3 minute(s) have passed since $s1 has killed.
	 */
	S2_HOURS_S3_MINUTES_SINCE_S1_KILLED(1921),

	/**
	 * ID: 1922<br>
	 * Message: Because $s1 has failed to kill for one full day, it has expired.
	 */
	S1_FAILED_KILLING_EXPIRED(1922),

	/**
	 * ID: 1923<br>
	 * Message: Court Magician: The portal has been created!
	 */
	COURT_MAGICIAN_CREATED_PORTAL(1923),

	/**
	 * ID: 1924<br>
	 * Message: Current Location: $s1, $s2, $s3 (Near the Primeval Isle)
	 */
	LOC_PRIMEVAL_ISLE_S1_S2_S3(1924),

	/**
	 * ID: 1925<br>
	 * Message: Due to the affects of the Seal of Strife, it is not possible to
	 * summon at this time.
	 */
	SEAL_OF_STRIFE_FORBIDS_SUMMONING(1925),

	/**
	 * ID: 1926<br>
	 * Message: There is no opponent to receive your challenge for a duel.
	 */
	THERE_IS_NO_OPPONENT_TO_RECEIVE_YOUR_CHALLENGE_FOR_A_DUEL(1926),

	/**
	 * ID: 1927<br>
	 * Message: $c1 has been challenged to a duel.
	 */
	C1_HAS_BEEN_CHALLENGED_TO_A_DUEL(1927),

	/**
	 * ID: 1928<br>
	 * Message: $c1's party has been challenged to a duel.
	 */
	C1_PARTY_HAS_BEEN_CHALLENGED_TO_A_DUEL(1928),

	/**
	 * ID: 1929<br>
	 * Message: $c1 has accepted your challenge to a duel. The duel will begin
	 * in a few moments.
	 */
	C1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS(
			1929),

	/**
	 * ID: 1930<br>
	 * Message: You have accepted $c1's challenge to a duel. The duel will begin
	 * in a few moments.
	 */
	YOU_HAVE_ACCEPTED_C1_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS(
			1930),

	/**
	 * ID: 1931<br>
	 * Message: $c1 has declined your challenge to a duel.
	 */
	C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL(1931),

	/**
	 * ID: 1932<br>
	 * Message: $c1 has declined your challenge to a duel.
	 */
	C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL2(1932),

	/**
	 * ID: 1933<br>
	 * Message: You have accepted $c1's challenge to a party duel. The duel will
	 * begin in a few moments.
	 */
	YOU_HAVE_ACCEPTED_C1_CHALLENGE_TO_A_PARTY_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS(
			1933),

	/**
	 * ID: 1934<br>
	 * Message: $s1 has accepted your challenge to duel against their party. The
	 * duel will begin in a few moments.
	 */
	S1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_DUEL_AGAINST_THEIR_PARTY_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS(
			1934),

	/**
	 * ID: 1935<br>
	 * Message: $c1 has declined your challenge to a party duel.
	 */
	C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_PARTY_DUEL(1935),

	/**
	 * ID: 1936<br>
	 * Message: The opposing party has declined your challenge to a duel.
	 */
	THE_OPPOSING_PARTY_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL(1936),

	/**
	 * ID: 1937<br>
	 * Message: Since the person you challenged is not currently in a party,
	 * they cannot duel against your party.
	 */
	SINCE_THE_PERSON_YOU_CHALLENGED_IS_NOT_CURRENTLY_IN_A_PARTY_THEY_CANNOT_DUEL_AGAINST_YOUR_PARTY(
			1937),

	/**
	 * ID: 1938<br>
	 * Message: $c1 has challenged you to a duel.
	 */
	C1_HAS_CHALLENGED_YOU_TO_A_DUEL(1938),

	/**
	 * ID: 1939<br>
	 * Message: $c1's party has challenged your party to a duel.
	 */
	C1_PARTY_HAS_CHALLENGED_YOUR_PARTY_TO_A_DUEL(1939),

	/**
	 * ID: 1940<br>
	 * Message: You are unable to request a duel at this time.
	 */
	YOU_ARE_UNABLE_TO_REQUEST_A_DUEL_AT_THIS_TIME(1940),

	/**
	 * ID: 1941<br>
	 * Message: This is no suitable place to challenge anyone or party to a
	 * duel.
	 */
	NO_PLACE_FOR_DUEL(1941),

	/**
	 * ID: 1942<br>
	 * Message: The opposing party is currently unable to accept a challenge to
	 * a duel.
	 */
	THE_OPPOSING_PARTY_IS_CURRENTLY_UNABLE_TO_ACCEPT_A_CHALLENGE_TO_A_DUEL(1942),

	/**
	 * ID: 1943<br>
	 * Message: The opposing party is currently not in a suitable location for a
	 * duel.
	 */
	THE_OPPOSING_PARTY_IS_AT_BAD_LOCATION_FOR_A_DUEL(1943),

	/**
	 * ID: 1944<br>
	 * Message: In a moment, you will be transported to the site where the duel
	 * will take place.
	 */
	IN_A_MOMENT_YOU_WILL_BE_TRANSPORTED_TO_THE_SITE_WHERE_THE_DUEL_WILL_TAKE_PLACE(
			1944),

	/**
	 * ID: 1945<br>
	 * Message: The duel will begin in $s1 second(s).
	 */
	THE_DUEL_WILL_BEGIN_IN_S1_SECONDS(1945),

	/**
	 * ID: 1946<br>
	 * Message: $c1 has challenged you to a duel. Will you accept?
	 */
	C1_CHALLENGED_YOU_TO_A_DUEL(1946),

	/**
	 * ID: 1947<br>
	 * Message: $c1's party has challenged your party to a duel. Will you
	 * accept?
	 */
	C1_CHALLENGED_YOU_TO_A_PARTY_DUEL(1947),

	/**
	 * ID: 1948<br>
	 * Message: The duel will begin in $s1 second(s).
	 */
	THE_DUEL_WILL_BEGIN_IN_S1_SECONDS2(1948),

	/**
	 * ID: 1949<br>
	 * Message: Let the duel begin!
	 */
	LET_THE_DUEL_BEGIN(1949),

	/**
	 * ID: 1950<br>
	 * Message: $c1 has won the duel.
	 */
	C1_HAS_WON_THE_DUEL(1950),

	/**
	 * ID: 1951<br>
	 * Message: $c1's party has won the duel.
	 */
	C1_PARTY_HAS_WON_THE_DUEL(1951),

	/**
	 * ID: 1952<br>
	 * Message: The duel has ended in a tie.
	 */
	THE_DUEL_HAS_ENDED_IN_A_TIE(1952),

	/**
	 * ID: 1953<br>
	 * Message: Since $c1 was disqualified, $s2 has won.
	 */
	SINCE_C1_WAS_DISQUALIFIED_S2_HAS_WON(1953),

	/**
	 * ID: 1954<br>
	 * Message: Since $c1's party was disqualified, $s2's party has won.
	 */
	SINCE_C1_PARTY_WAS_DISQUALIFIED_S2_PARTY_HAS_WON(1954),

	/**
	 * ID: 1955<br>
	 * Message: Since $c1 withdrew from the duel, $s2 has won.
	 */
	SINCE_C1_WITHDREW_FROM_THE_DUEL_S2_HAS_WON(1955),

	/**
	 * ID: 1956<br>
	 * Message: Since $c1's party withdrew from the duel, $s2's party has won.
	 */
	SINCE_C1_PARTY_WITHDREW_FROM_THE_DUEL_S2_PARTY_HAS_WON(1956),

	/**
	 * ID: 1957<br>
	 * Message: Select the item to be augmented.
	 */
	SELECT_THE_ITEM_TO_BE_AUGMENTED(1957),

	/**
	 * ID: 1958<br>
	 * Message: Select the catalyst for augmentation.
	 */
	SELECT_THE_CATALYST_FOR_AUGMENTATION(1958),

	/**
	 * ID: 1959<br>
	 * Message: Requires $s1 $s2.
	 */
	REQUIRES_S1_S2(1959),

	/**
	 * ID: 1960<br>
	 * Message: This is not a suitable item.
	 */
	THIS_IS_NOT_A_SUITABLE_ITEM(1960),

	/**
	 * ID: 1961<br>
	 * Message: Gemstone quantity is incorrect.
	 */
	GEMSTONE_QUANTITY_IS_INCORRECT(1961),

	/**
	 * ID: 1962<br>
	 * Message: The item was successfully augmented!
	 */
	THE_ITEM_WAS_SUCCESSFULLY_AUGMENTED(1962),

	/**
	 * ID : 1963<br>
	 * Message: Select the item from which you wish to remove augmentation.
	 */
	SELECT_THE_ITEM_FROM_WHICH_YOU_WISH_TO_REMOVE_AUGMENTATION(1963),

	/**
	 * ID: 1964<br>
	 * Message: Augmentation removal can only be done on an augmented item.
	 */
	AUGMENTATION_REMOVAL_CAN_ONLY_BE_DONE_ON_AN_AUGMENTED_ITEM(1964),

	/**
	 * ID: 1965<br>
	 * Message: Augmentation has been successfully removed from your $s1.
	 */
	AUGMENTATION_HAS_BEEN_SUCCESSFULLY_REMOVED_FROM_YOUR_S1(1965),

	/**
	 * ID: 1966<br>
	 * Message: Only the clan leader may issue commands.
	 */
	ONLY_CLAN_LEADER_CAN_ISSUE_COMMANDS(1966),

	/**
	 * ID: 1967<br>
	 * Message: The gate is firmly locked. Please try again later.
	 */
	GATE_LOCKED_TRY_AGAIN_LATER(184),

	/**
	 * ID: 1968<br>
	 * Message: $s1's owner.
	 */
	S1_OWNER(1968),

	/**
	 * ID: 1968<br>
	 * Message: Area where $s1 appears.
	 */
	AREA_S1_APPEARS(1969),

	/**
	 * ID: 1970<br>
	 * Message: Once an item is augmented, it cannot be augmented again.
	 */
	ONCE_AN_ITEM_IS_AUGMENTED_IT_CANNOT_BE_AUGMENTED_AGAIN(1970),

	/**
	 * ID: 1971<br>
	 * Message: The level of the hardener is too high to be used.
	 */
	HARDENER_LEVEL_TOO_HIGH(1971),

	/**
	 * ID: 1972<br>
	 * Message: You cannot augment items while a private store or private
	 * workshop is in operation.
	 */
	YOU_CANNOT_AUGMENT_ITEMS_WHILE_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP_IS_IN_OPERATION(
			1972),

	/**
	 * ID: 1973<br>
	 * Message: You cannot augment items while frozen.
	 */
	YOU_CANNOT_AUGMENT_ITEMS_WHILE_FROZEN(1973),

	/**
	 * ID: 1974<br>
	 * Message: You cannot augment items while dead.
	 */
	YOU_CANNOT_AUGMENT_ITEMS_WHILE_DEAD(1974),

	/**
	 * ID: 1975<br>
	 * Message: You cannot augment items while engaged in trade activities.
	 */
	YOU_CANNOT_AUGMENT_ITEMS_WHILE_TRADING(1975),

	/**
	 * ID: 1976<br>
	 * Message: You cannot augment items while paralyzed.
	 */
	YOU_CANNOT_AUGMENT_ITEMS_WHILE_PARALYZED(1976),

	/**
	 * ID: 1977<br>
	 * Message: You cannot augment items while fishing.
	 */
	YOU_CANNOT_AUGMENT_ITEMS_WHILE_FISHING(1977),

	/**
	 * ID: 1978<br>
	 * Message: You cannot augment items while sitting down.
	 */
	YOU_CANNOT_AUGMENT_ITEMS_WHILE_SITTING_DOWN(1978),

	/**
	 * ID: 1979<br>
	 * Message: $s1's remaining Mana is now 10.
	 */
	S1S_REMAINING_MANA_IS_NOW_10(1979),

	/**
	 * ID: 1980<br>
	 * Message: $s1's remaining Mana is now 5.
	 */
	S1S_REMAINING_MANA_IS_NOW_5(1980),

	/**
	 * ID: 1981<br>
	 * Message: $s1's remaining Mana is now 1. It will disappear soon.
	 */
	S1S_REMAINING_MANA_IS_NOW_1(1981),

	/**
	 * ID: 1982<br>
	 * Message: $s1's remaining Mana is now 0, and the item has disappeared.
	 */
	S1S_REMAINING_MANA_IS_NOW_0(1982),

	/**
	 * ID: 1984<br>
	 * Message: Press the Augment button to begin.
	 */
	PRESS_THE_AUGMENT_BUTTON_TO_BEGIN(1984),

	/**
	 * ID: 1985<br>
	 * Message: $s1's drop area ($s2)
	 */
	S1_DROP_AREA_S2(1985),

	/**
	 * ID: 1986<br>
	 * Message: $s1's owner ($s2)
	 */
	S1_OWNER_S2(1986),

	/**
	 * ID: 1987<br>
	 * Message: $s1
	 */
	S1(1987),

	/**
	 * ID: 1988<br>
	 * Message: The ferry has arrived at Primeval Isle.
	 */
	FERRY_ARRIVED_AT_PRIMEVAL(1988),

	/**
	 * ID: 1989<br>
	 * Message: The ferry will leave for Rune Harbor after anchoring for three
	 * minutes.
	 */
	FERRY_LEAVING_FOR_RUNE_3_MINUTES(1989),

	/**
	 * ID: 1990<br>
	 * Message: The ferry is now departing Primeval Isle for Rune Harbor.
	 */
	FERRY_LEAVING_PRIMEVAL_FOR_RUNE_NOW(1990),

	/**
	 * ID: 1991<br>
	 * Message: The ferry will leave for Primeval Isle after anchoring for three
	 * minutes.
	 */
	FERRY_LEAVING_FOR_PRIMEVAL_3_MINUTES(1991),

	/**
	 * ID: 1992<br>
	 * Message: The ferry is now departing Rune Harbor for Primeval Isle.
	 */
	FERRY_LEAVING_RUNE_FOR_PRIMEVAL_NOW(1992),

	/**
	 * ID: 1993<br>
	 * Message: The ferry from Primeval Isle to Rune Harbor has been delayed.
	 */
	FERRY_FROM_PRIMEVAL_TO_RUNE_DELAYED(1993),

	/**
	 * ID: 1994<br>
	 * Message: The ferry from Rune Harbor to Primeval Isle has been delayed.
	 */
	FERRY_FROM_RUNE_TO_PRIMEVAL_DELAYED(1994),

	/**
	 * ID: 1995<br>
	 * Message: $s1 channel filtering option
	 */
	S1_CHANNEL_FILTER_OPTION(1995),

	/**
	 * ID: 1996<br>
	 * Message: The attack has been blocked.
	 */
	ATTACK_WAS_BLOCKED(1996),

	/**
	 * ID: 1997<br>
	 * Message: $c1 is performing a counterattack.
	 */
	C1_PERFORMING_COUNTERATTACK(1997),

	/**
	 * ID: 1998<br>
	 * Message: You countered $c1's attack.
	 */
	COUNTERED_C1_ATTACK(1998),

	/**
	 * ID: 1999<br>
	 * Message: $c1 dodges the attack.
	 */
	C1_DODGES_ATTACK(1999),

	/**
	 * ID: 2000<br>
	 * Message: You have avoided $c1's attack.
	 */
	AVOIDED_C1_ATTACK2(2000),

	/**
	 * ID: 2001<br>
	 * Message: Augmentation failed due to inappropriate conditions.
	 */
	AUGMENTATION_FAILED_DUE_TO_INAPPROPRIATE_CONDITIONS(2001),

	/**
	 * ID: 2002<br>
	 * Message: Trap failed.
	 */
	TRAP_FAILED(2002),

	/**
	 * ID: 2003<br>
	 * Message: You obtained an ordinary material.
	 */
	OBTAINED_ORDINARY_MATERIAL(2003),

	/**
	 * ID: 2004<br>
	 * Message: You obtained a rare material.
	 */
	OBTAINED_RATE_MATERIAL(2004),

	/**
	 * ID: 2005<br>
	 * Message: You obtained a unique material.
	 */
	OBTAINED_UNIQUE_MATERIAL(2005),

	/**
	 * ID: 2006<br>
	 * Message: You obtained the only material of this kind.
	 */
	OBTAINED_ONLY_MATERIAL(2006),

	/**
	 * ID: 2007<br>
	 * Message: Please enter the recipient's name.
	 */
	ENTER_RECIPIENTS_NAME(2007),

	/**
	 * ID: 2008<br>
	 * Message: Please enter the text.
	 */
	ENTER_TEXT(2008),

	/**
	 * ID: 2009<br>
	 * Message: You cannot exceed 1500 characters.
	 */
	CANT_EXCEED_1500_CHARACTERS(2009),

	/**
	 * ID: 2009<br>
	 * Message: $s2 $s1
	 */
	S2_S1(1987),

	/**
	 * ID: 2011<br>
	 * Message: The augmented item cannot be discarded.
	 */
	AUGMENTED_ITEM_CANNOT_BE_DISCARDED(2011),

	/**
	 * ID: 2012<br>
	 * Message: $s1 has been activated.
	 */
	S1_HAS_BEEN_ACTIVATED(2012),

	/**
	 * ID: 2013<br>
	 * Message: Your seed or remaining purchase amount is inadequate.
	 */
	YOUR_SEED_OR_REMAINING_PURCHASE_AMOUNT_IS_INADEQUATE(2013),

	/**
	 * ID: 2014<br>
	 * Message: You cannot proceed because the manor cannot accept any more
	 * crops. All crops have been returned and no adena withdrawn.
	 */
	MANOR_CANT_ACCEPT_MORE_CROPS(2014),

	/**
	 * ID: 2015<br>
	 * Message: A skill is ready to be used again.
	 */
	SKILL_READY_TO_USE_AGAIN(2015),

	/**
	 * ID: 2016<br>
	 * Message: A skill is ready to be used again but its re-use counter time
	 * has increased.
	 */
	SKILL_READY_TO_USE_AGAIN_BUT_TIME_INCREASED(2016),

	/**
	 * ID: 2017<br>
	 * Message: $c1 cannot duel because $c1 is currently engaged in a private
	 * store or manufacture.
	 */
	C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_A_PRIVATE_STORE_OR_MANUFACTURE(
			2017),

	/**
	 * ID: 2018<br>
	 * Message: $c1 cannot duel because $c1 is currently fishing.
	 */
	C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_FISHING(2018),

	/**
	 * ID: 2019<br>
	 * Message: $c1 cannot duel because $c1's HP or MP is below 50%.
	 */
	C1_CANNOT_DUEL_BECAUSE_C1_HP_OR_MP_IS_BELOW_50_PERCENT(2019),

	/**
	 * ID: 2020<br>
	 * Message: $c1 cannot make a challenge to a duel because $c1 is currently
	 * in a duel-prohibited area (Peaceful Zone / Seven Signs Zone / Near Water
	 * / Restart Prohibited Area).
	 */
	C1_CANNOT_MAKE_A_CHALLANGE_TO_A_DUEL_BECAUSE_C1_IS_CURRENTLY_IN_A_DUEL_PROHIBITED_AREA(
			2020),

	/**
	 * ID: 2021<br>
	 * Message: $c1 cannot duel because $c1 is currently engaged in battle.
	 */
	C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_BATTLE(2021),

	/**
	 * ID: 2022<br>
	 * Message: $c1 cannot duel because $c1 is already engaged in a duel.
	 */
	C1_CANNOT_DUEL_BECAUSE_C1_IS_ALREADY_ENGAGED_IN_A_DUEL(2022),

	/**
	 * ID: 2023<br>
	 * Message: $c1 cannot duel because $c1 is in a chaotic state.
	 */
	C1_CANNOT_DUEL_BECAUSE_C1_IS_IN_A_CHAOTIC_STATE(2023),

	/**
	 * ID: 2024<br>
	 * Message: $c1 cannot duel because $c1 is participating in the Olympiad.
	 */
	C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_THE_OLYMPIAD(2024),

	/**
	 * ID: 2025<br>
	 * Message: $c1 cannot duel because $c1 is participating in a clan hall war.
	 */
	C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_CLAN_HALL_WAR(2025),

	/**
	 * ID: 2026<br>
	 * Message: $c1 cannot duel because $c1 is participating in a siege war.
	 */
	C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_SIEGE_WAR(2026),

	/**
	 * ID: 2027<br>
	 * Message: $c1 cannot duel because $c1 is currently riding a boat, wyvern,
	 * or strider.
	 */
	C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_RIDING_A_BOAT_WYVERN_OR_STRIDER(2027),

	/**
	 * ID: 2028<br>
	 * Message: $c1 cannot receive a duel challenge because $c1 is too far away.
	 */
	C1_CANNOT_RECEIVE_A_DUEL_CHALLENGE_BECAUSE_C1_IS_TOO_FAR_AWAY(2028),

	/**
	 * ID: 2029<br>
	 * Message: $c1 is currently teleporting and cannot participate in the
	 * Olympiad.
	 */
	C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_DURING_TELEPORT(2029),

	/**
	 * ID: 2030<br>
	 * Message: You are currently logging in.
	 */
	CURRENTLY_LOGGING_IN(2030),

	/**
	 * ID: 2031<br>
	 * Message: Please wait a moment.
	 */
	PLEASE_WAIT_A_MOMENT(2031),

	/**
	 * ID: 2032<br>
	 * Message: It is not the right time for purchasing the item.
	 */
	NOT_TIME_TO_PURCHASE_ITEM(2032),

	/**
	 * ID: 2033<br>
	 * Message: A sub-class cannot be created or changed because you have
	 * exceeded your inventory limit.
	 */
	NOT_SUBCLASS_WHILE_INVENTORY_FULL(2033),

	/**
	 * ID: 2034<br>
	 * Message: There are $s1 hour(s) and $s2 minute(s) remaining until the time
	 * when the item can be purchased.
	 */
	ITEM_PURCHASABLE_IN_S1_HOURS_S2_MINUTES(2034),

	/**
	 * ID: 2035<br>
	 * Message: There are $s1 minute(s) remaining until the time when the item
	 * can be purchased.
	 */
	ITEM_PURCHASABLE_IN_S1_MINUTES(2035),

	/**
	 * ID: 2036<br>
	 * Message: Unable to invite because the party is locked.
	 */
	NO_INVITE_PARTY_LOCKED(2036),

	/**
	 * ID: 2037<br>
	 * Message: Unable to create character. You are unable to create a new
	 * character on the selected server. A restriction is in place which
	 * restricts users from creating characters on different servers where no
	 * previous characters exists. Please choose another server.
	 */
	CANT_CREATE_CHARACTER_DURING_RESTRICTION(2037),

	/**
	 * ID: 2038<br>
	 * Message: Some Lineage II features have been limited for free trials.
	 * Trial accounts aren't allowed to drop items and/or Adena. To unlock all
	 * of the features of Lineage II, purchase the full version today.
	 */
	ACCOUNT_CANT_DROP_ITEMS(2038),

	/**
	 * ID: 2039<br>
	 * Message: Some Lineage II features have been limited for free trials.
	 * Trial accounts aren't allowed to trade items and/or Adena. To unlock all
	 * of the features of Lineage II, purchase the full version today.
	 */
	ACCOUNT_CANT_TRADE_ITEMS(2039),

	/**
	 * ID: 2040<br>
	 * Message: Cannot trade items with the targeted user.
	 */
	CANT_TRADE_WITH_TARGET(2040),

	/**
	 * ID: 2041<br>
	 * Message: Some Lineage II features have been limited for free trials.
	 * Trial accounts aren't allowed to setup private stores. To unlock all of
	 * the features of Lineage II, purchase the full version today.
	 */
	CANT_OPEN_PRIVATE_STORE(2041),

	/**
	 * ID: 2042<br>
	 * Message: This account has been suspended for non-payment based on the
	 * cell phone payment agreement.\\n Please submit proof of payment by fax
	 * (02-2186-3499) and contact customer service at 1600-0020.
	 */
	ILLEGAL_USE23(2042),

	/**
	 * ID: 2043<br>
	 * Message: You have exceeded your inventory volume limit and may not take
	 * this quest item. Please make room in your inventory and try again
	 */
	YOU_HAVE_EXCEEDED_YOUR_INVENTORY_VOLUME_LIMIT_AND_CANNOT_TAKE_THIS_QUESTITEM(
			2043),

	/**
	 * ID: 2044<br>
	 * Message: Some Lineage II features have been limited for free trials.
	 * Trial accounts aren't allowed to set up private manufacturing stores. To
	 * unlock all of the features of Lineage II, purchase the full version
	 * today.
	 */
	CANT_SETUP_PRIVATE_WORKSHOP(2044),

	/**
	 * ID: 2045<br>
	 * Message: Some Lineage II features have been limited for free trials.
	 * Trial accounts aren't allowed to use private manufacturing stores. To
	 * unlock all of the features of Lineage II, purchase the full version
	 * today.
	 */
	CANT_USE_PRIVATE_WORKSHOP(2045),

	/**
	 * ID: 2046<br>
	 * Message: Some Lineage II features have been limited for free trials.
	 * Trial accounts aren't allowed buy items from private stores. To unlock
	 * all of the features of Lineage II, purchase the full version today.
	 */
	CANT_USE_PRIVATE_STORES(2046),

	/**
	 * ID: 2047<br>
	 * Message: Some Lineage II features have been limited for free trials.
	 * Trial accounts aren't allowed to access clan warehouses. To unlock all of
	 * the features of Lineage II, purchase the full version today.
	 */
	CANT_USE_CLAN_WH(2047),

	/**
	 * ID: 2048<br>
	 * Message: The shortcut in use conflicts with $s1. Do you wish to reset the
	 * conflicting shortcuts and use the saved shortcut?
	 */
	CONFLICTING_SHORTCUT(2048),

	/**
	 * ID: 2049<br>
	 * Message: The shortcut will be applied and saved in the server. Will you
	 * continue?
	 */
	CONFIRM_SHORTCUT_WILL_SAVED_ON_SERVER(2049),

	/**
	 * ID: 2050<br>
	 * Message: $s1 Blood Pledge is trying to display a flag.
	 */
	S1_TRYING_RAISE_FLAG(2050),

	/**
	 * ID: 2051<br>
	 * Message: You must accept the User Agreement before this account can
	 * access Lineage II.
	 */
	MUST_ACCEPT_AGREEMENT(2051),

	/**
	 * ID: 2052<br>
	 * Message: A guardian's consent is required before this account can be used
	 * to play Lineage II.
	 */
	NEED_CONSENT_TO_PLAY_THIS_ACCOUNT(2052),

	/**
	 * ID: 2053<br>
	 * Message: This account has declined the User Agreement or is pending a
	 * withdrawl request.
	 */
	ACCOUNT_DECLINED_AGREEMENT_OR_PENDING(2053),

	/**
	 * ID: 2054<br>
	 * Message: This account has been suspended.
	 */
	ACCOUNT_SUSPENDED(2054),

	/**
	 * ID: 2055<br>
	 * Message: Your account has been suspended from all game services.
	 */
	ACCOUNT_SUSPENDED_FROM_ALL_SERVICES(2055),

	/**
	 * ID: 2056<br>
	 * Message: Your account has been converted to an integrated account, and is
	 * unable to be accessed.
	 */
	ACCOUNT_CONVERTED(2056),

	/**
	 * ID: 2057<br>
	 * Message: You have blocked $c1.
	 */
	BLOCKED_C1(2057),

	/**
	 * ID: 2058<br>
	 * Message: You are already polymorphed and cannot polymorph again.
	 */
	YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN(2058),

	/**
	 * ID: 2059<br>
	 * Message: The nearby area is too narrow for you to polymorph. Please move
	 * to another area and try to polymorph again.
	 */
	AREA_UNSUITABLE_FOR_POLYMORPH(2059),

	/**
	 * ID: 2060<br>
	 * Message: You cannot polymorph into the desired form in water.
	 */
	YOU_CANNOT_POLYMORPH_INTO_THE_DESIRED_FORM_IN_WATER(2060),

	/**
	 * ID: 2061<br>
	 * Message: You are still under transform penalty and cannot be polymorphed.
	 */
	CANT_MORPH_DUE_TO_MORPH_PENALTY(2061),

	/**
	 * ID: 2062<br>
	 * Message: You cannot polymorph when you have summoned a servitor/pet.
	 */
	YOU_CANNOT_POLYMORPH_WHEN_YOU_HAVE_SUMMONED_A_SERVITOR(2062),

	/**
	 * ID: 2063<br>
	 * Message: You cannot polymorph while riding a pet.
	 */
	YOU_CANNOT_POLYMORPH_WHILE_RIDING_A_PET(2063),

	/**
	 * ID: 2064<br>
	 * Message: You cannot polymorph while under the effect of a special skill
	 */
	CANT_MORPH_WHILE_UNDER_SPECIAL_SKILL_EFFECT(2064),

	/**
	 * ID: 2065<br>
	 * Message: That item cannot be taken off
	 */
	ITEM_CANNOT_BE_TAKEN_OFF(2065),

	/**
	 * ID: 2066<br>
	 * Message: That weapon cannot perform any attacks.
	 */
	THAT_WEAPON_CANT_ATTACK(2066),

	/**
	 * ID: 2067<br>
	 * Message: That weapon cannot use any other skill except the weapon's
	 * skill.
	 */
	WEAPON_CAN_USE_ONLY_WEAPON_SKILL(2067),

	/**
	 * ID: 2068<br>
	 * Message: You do not have all of the items needed to untrain the enchant
	 * skill.
	 */
	YOU_DONT_HAVE_ALL_ITENS_NEEDED_TO_UNTRAIN_SKILL_ENCHANT(2068),

	/**
	 * ID: 2069<br>
	 * Message: Untrain of enchant skill was successful. Current level of
	 * enchant skill $s1 has been decreased by 1.
	 */
	UNTRAIN_SUCCESSFUL_SKILL_S1_ENCHANT_LEVEL_DECREASED_BY_ONE(2069),

	/**
	 * ID: 2070<br>
	 * Message: Untrain of enchant skill was successful. Current level of
	 * enchant skill $s1 became 0 and enchant skill will be initialized.
	 */
	UNTRAIN_SUCCESSFUL_SKILL_S1_ENCHANT_LEVEL_RESETED(2070),

	/**
	 * ID: 2071<br>
	 * Message: You do not have all of the items needed to enchant skill route
	 * change.
	 */
	YOU_DONT_HAVE_ALL_ITENS_NEEDED_TO_CHANGE_SKILL_ENCHANT_ROUTE(2071),

	/**
	 * ID: 2072<br>
	 * Message: Enchant skill route change was successful. Lv of enchant skill
	 * $s1 has been decreased by $s2.
	 */
	SKILL_ENCHANT_CHANGE_SUCCESSFUL_S1_LEVEL_WAS_DECREASED_BY_S2(2072),

	/**
	 * ID: 2073<br>
	 * Message: Enchant skill route change was successful. Lv of enchant skill
	 * $s1 will remain.
	 */
	SKILL_ENCHANT_CHANGE_SUCCESSFUL_S1_LEVEL_WILL_REMAIN(2073),

	/**
	 * ID: 2074<br>
	 * Message: Skill enchant failed. Current level of enchant skill $s1 will
	 * remain unchanged.
	 */
	SKILL_ENCHANT_FAILED_S1_LEVEL_WILL_REMAIN(2074),

	/**
	 * ID: 2075<br>
	 * Message: It is not auction period.
	 */
	NO_AUCTION_PERIOD(2075),

	/**
	 * ID: 2076<br>
	 * Message: Bidding is not allowed because the maximum bidding price exceeds
	 * 100 billion.
	 */
	BID_CANT_EXCEED_100_BILLION(2076),

	/**
	 * ID: 2077<br>
	 * Message: Your bid must be higher than the current highest bid.
	 */
	BID_MUST_BE_HIGHER_THAN_CURRENT_BID(2077),

	/**
	 * ID: 2078<br>
	 * Message: You do not have enough adena for this bid.
	 */
	NOT_ENOUGH_ADENA_FOR_THIS_BID(2078),

	/**
	 * ID: 2079<br>
	 * Message: You currently have the highest bid, but the reserve has not been
	 * met.
	 */
	HIGHEST_BID_BUT_RESERVE_NOT_MET(2079),

	/**
	 * ID: 2080<br>
	 * Message: You have been outbid.
	 */
	YOU_HAVE_BEEN_OUTBID(2080),

	/**
	 * ID: 2081<br>
	 * Message: There are no funds presently due to you.
	 */
	NO_FUNDS_DUE(2081),

	/**
	 * ID: 2082<br>
	 * Message: You have exceeded the total amount of adena allowed in
	 * inventory.
	 */
	EXCEEDED_MAX_ADENA_AMOUNT_IN_INVENTORY(2082),

	/**
	 * ID: 2083<br>
	 * Message: The auction has begun.
	 */
	AUCTION_BEGUN(2083),

	/**
	 * ID: 2084<br>
	 * Message: Enemy Blood Pledges have intruded into the fortress.
	 */
	ENEMIES_INTRUDED_FORTRESS(2084),

	/**
	 * ID: 2085<br>
	 * Message: Shout and trade chatting cannot be used while possessing a
	 * cursed weapon.
	 */
	SHOUT_AND_TRADE_CHAT_CANNOT_BE_USED_WHILE_POSSESSING_CURSED_WEAPON(2085),

	/**
	 * ID: 2086<br>
	 * Message: Search on user $s2 for third-party program use will be completed
	 * in $s1 minute(s).
	 */
	SEARCH_ON_S2_FOR_BOT_USE_COMPLETED_IN_S1_MINUTES(2086),

	/**
	 * ID: 2087<br>
	 * Message: A fortress is under attack!
	 */
	A_FORTRESS_IS_UNDER_ATTACK(2087),

	/**
	 * ID: 2088<br>
	 * Message: $s1 minute(s) until the fortress battle starts.
	 */
	S1_MINUTES_UNTIL_THE_FORTRESS_BATTLE_STARTS(2088),

	/**
	 * ID: 2089<br>
	 * Message: $s1 minute(s) until the fortress battle starts.
	 */
	S1_SECONDS_UNTIL_THE_FORTRESS_BATTLE_STARTS(2089),

	/**
	 * ID: 2090<br>
	 * Message: The fortress battle $s1 has begun.
	 */
	THE_FORTRESS_BATTLE_S1_HAS_BEGUN(2090),

	/**
	 * ID: 2091<br>
	 * Message: Your account can only be used after changing your password and
	 * quiz.
	 */
	CHANGE_PASSWORT_FIRST(2091),

	/**
	 * ID: 2092<br>
	 * Message: You cannot bid due to a passed-in price.
	 */
	CANNOT_BID_DUE_TO_PASSED_IN_PRICE(2092),

	/**
	 * ID: 2093<br>
	 * Message: The passed-in price is $s1 adena. Would you like to return the
	 * passed-in price?
	 */
	PASSED_IN_PRICE_IS_S1_ADENA_WOULD_YOU_LIKE_TO_RETURN_IT(2093),

	/**
	 * ID: 2094<br>
	 * Message: Another user is purchasing. Please try again later.
	 */
	ANOTHER_USER_PURCHASING_TRY_AGAIN_LATER(184),

	/**
	 * ID: 2095<br>
	 * Message: Some Lineage II features have been limited for free trials.
	 * Trial accounts have limited chatting capabilities. To unlock all of the
	 * features of Lineage II, purchase the full version today.
	 */
	ACCOUNT_CANNOT_SHOUT(2095),

	/**
	 * ID: 2096<br>
	 * Message: $c1 is in a location which cannot be entered, therefore it
	 * cannot be processed.
	 */
	C1_IS_IN_LOCATION_THAT_CANNOT_BE_ENTERED(2096),

	/**
	 * ID: 2097<br>
	 * Message: $c1's level requirement is not sufficient and cannot be entered.
	 */
	C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT(2097),

	/**
	 * ID: 2098<br>
	 * Message: $c1's quest requirement is not sufficient and cannot be entered.
	 */
	C1_QUEST_REQUIREMENT_NOT_SUFFICIENT(2098),

	/**
	 * ID: 2099<br>
	 * Message: $c1's item requirement is not sufficient and cannot be entered.
	 */
	C1_ITEM_REQUIREMENT_NOT_SUFFICIENT(2099),

	/**
	 * ID: 2100<br>
	 * Message: $c1 may not re-enter yet.
	 */
	C1_MAY_NOT_REENTER_YET(2100),

	/**
	 * ID: 2101<br>
	 * Message: You are not currently in a party, so you cannot enter.
	 */
	NOT_IN_PARTY_CANT_ENTER(2101),

	/**
	 * ID: 2102<br>
	 * Message: You cannot enter due to the party having exceeded the limit.
	 */
	PARTY_EXCEEDED_THE_LIMIT_CANT_ENTER(2102),

	/**
	 * ID: 2103<br>
	 * Message: You cannot enter because you are not associated with the current
	 * command channel.
	 */
	NOT_IN_COMMAND_CHANNEL_CANT_ENTER(2103),

	/**
	 * ID: 2104<br>
	 * Message: The maximum number of instance zones has been exceeded. You
	 * cannot enter.
	 */
	MAXIMUM_INSTANCE_ZONE_NUMBER_EXCEEDED_CANT_ENTER(2104),

	/**
	 * ID: 2105<br>
	 * Message: You have entered another instance zone, therefore you cannot
	 * enter corresponding dungeon.
	 */
	ALREADY_ENTERED_ANOTHER_INSTANCE_CANT_ENTER(2105),

	/**
	 * ID: 2106<br>
	 * Message: This dungeon will expire in $s1 minute(s). You will be forced
	 * out of the dungeon when the time expires.
	 */
	DUNGEON_EXPIRES_IN_S1_MINUTES(2106),

	/**
	 * ID: 2107<br>
	 * Message: This instance zone will be terminated in $s1 minute(s). You will
	 * be forced out of the dungeon when the time expires.
	 */
	INSTANCE_ZONE_TERMINATES_IN_S1_MINUTES(2107),

	/**
	 * ID: 2108<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE24(2108),

	/**
	 * ID: 2109<br>
	 * Message: The server has been integrated, and your character, $s1, has
	 * overlapped with another name. Please enter a new name for your character
	 */
	CHARACTER_NAME_OVERLAPPING_RENAME_CHARACTER(2109),

	/**
	 * ID: 2110<br>
	 * Message: This character name already exists or is an invalid name. Please
	 * enter a new name
	 */
	CHARACTER_NAME_INVALID_RENAME_CHARACTER(2110),

	/**
	 * ID: 2111<br>
	 * Message: Enter a shortcut to assign.
	 */
	ENTER_SHORTCUT_TO_ASSIGN(2111),

	/**
	 * ID: 2112<br>
	 * Message: Sub-key can be CTRL, ALT, SHIFT and you may enter two sub-keys
	 * at a time.
	 */
	SUBKEY_EXPLANATION1(2112),

	/**
	 * ID: 2113<br>
	 * Message: (Sub key explanation)
	 */
	SUBKEY_EXPLANATION2(2113),

	/**
	 * ID: 2114<br>
	 * Message: Forced attack and stand-in-place attacks assigned previously to
	 * Ctrl and Shift will be changed to Alt + Q and Alt + E when set as
	 * expanded sub-key mode, and CTRL and SHIFT will be available to assign to
	 * another shortcut. Will you continue?
	 */
	SUBKEY_EXPLANATION3(2114),

	/**
	 * ID: 2115<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE25(2115),

	/**
	 * ID: 2116<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE26(2116),

	/**
	 * ID: 2117<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE27(2117),

	/**
	 * ID: 2118<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE28(2118),

	/**
	 * ID: 2119<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE29(2119),

	/**
	 * ID: 2120<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE30(2120),

	/**
	 * ID: 2121<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE31(2121),

	/**
	 * ID: 2122<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE32(2122),

	/**
	 * ID: 2123<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE33(2123),

	/**
	 * ID: 2124<br>
	 * Message: The server has been integrated, and your Clan name, $s1, has
	 * been overlapped with another name. Please enter the Clan name to be
	 * changed.
	 */
	CLAN_NAME_OVERLAPPING_RENAME_CLAN(2124),

	/**
	 * ID: 2125<br>
	 * Message: This name already exists or is an invalid name. Please enter the
	 * Clan name to be changed.
	 */
	CLAN_NAME_INVALID_RENAME_CLAN(2125),

	/**
	 * ID: 2126<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE34(2126),

	/**
	 * ID: 2127<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE35(2127),

	/**
	 * ID: 2128<br>
	 * Message: Your account has been suspended ...
	 */
	ILLEGAL_USE36(2128),

	/**
	 * ID: 2129<br>
	 * Message: The augmented item cannot be converted. Please convert after the
	 * augmentation has been removed.
	 */
	AUGMENTED_ITEM_CANT_CONVERTED(2129),

	/**
	 * ID: 2130<br>
	 * Message: You cannot convert this item.
	 */
	CANT_CONVERT_THIS_ITEM(2130),

	/**
	 * ID: 2131<br>
	 * Message: You have bid the highest price and have won the item. The item
	 * can be found in your personal warehouse.
	 */
	WON_BID_ITEM_CAN_BE_FOUND_IN_WAREHOUSE(2131),

	/**
	 * ID: 2132<br>
	 * Message: You have entered a common server.
	 */
	ENTERED_COMMON_SERVER(2132),

	/**
	 * ID: 2133<br>
	 * Message: You have entered an adults-only server.
	 */
	ENTERED_ADULTS_ONLY_SERVER(2133),

	/**
	 * ID: 2134<br>
	 * Message: You have entered a server for juveniles.
	 */
	ENTERED_JUVENILES_SERVER(2134),

	/**
	 * ID: 2135<br>
	 * Message: Because of your Fatigue level, this is not allowed.
	 */
	NOT_ALLOWED_DUE_TO_FATIGUE_LEVEL(2135),

	/**
	 * ID: 2136<br>
	 * Message: A clan name change application has been submitted.
	 */
	CLAN_NAME_CHANCE_PETITION_SUBMITTED(2136),

	/**
	 * ID: 2137<br>
	 * Message: You are about to bid $s1 item with $s2 adena. Will you continue?
	 */
	CONFIRM_BID_S2_ADENA_FOR_S1_ITEM(2137),

	/**
	 * ID: 2138<br>
	 * Message: Please enter a bid price.
	 */
	ENTER_BID_PRICE(2138),

	/**
	 * ID: 2139<br>
	 * Message: $c1's Pet.
	 */
	C1_PET(2139),

	/**
	 * ID: 2140<br>
	 * Message: $c1's Servitor.
	 */
	C1_SERVITOR(2140),

	/**
	 * ID: 2141<br>
	 * Message: You slightly resisted $c1's magic.
	 */
	SLIGHTLY_RESISTED_C1_MAGICC(2141),

	/**
	 * ID: 2142<br>
	 * Message: You cannot expel $c1 because $c1 is not a party member.
	 */
	CANT_EXPEL_C1_NOT_A_PARTY_MEMBER(2142),

	/**
	 * ID: 2143<br>
	 * Message: You cannot add elemental power while operating a Private Store
	 * or Private Workshop.
	 */
	CANNOT_ADD_ELEMENTAL_POWER_WHILE_OPERATING_PRIVATE_STORE_OR_WORKSHOP(2143),

	/**
	 * ID: 2144<br>
	 * Message: Please select item to add elemental power.
	 */
	SELECT_ITEM_TO_ADD_ELEMENTAL_POWER(2144),

	/**
	 * ID: 2145<br>
	 * Message: Attribute item usage has been cancelled.
	 */
	ELEMENTAL_ENHANCE_CANCELED(2145),

	/**
	 * ID: 2146<br>
	 * Message: Elemental power enhancer usage requirement is not sufficient.
	 */
	ELEMENTAL_ENHANCE_REQUIREMENT_NOT_SUFFICIENT(2146),

	/**
	 * ID: 2147<br>
	 * Message: $s2 elemental power has been added successfully to $s1.
	 */
	ELEMENTAL_POWER_S2_SUCCESSFULLY_ADDED_TO_S1(1987),

	/**
	 * ID: 2148<br>
	 * Message: $s3 elemental power has been added successfully to +$s1 $s2.
	 */
	ELEMENTAL_POWER_S3_SUCCESSFULLY_ADDED_TO_S1_S2(2148),

	/**
	 * ID: 2149<br>
	 * Message: You have failed to add elemental power.
	 */
	FAILED_ADDING_ELEMENTAL_POWER(2149),

	/**
	 * ID: 2150<br>
	 * Message: Another elemental power has already been added. This elemental
	 * power cannot be added.
	 */
	ANOTHER_ELEMENTAL_POWER_ALREADY_ADDED(2150),

	/**
	 * ID: 2151<br>
	 * Message: Your opponent has resistance to magic, the damage was decreased.
	 */
	OPPONENT_HAS_RESISTANCE_MAGIC_DAMAGE_DECREASED(2151),

	/**
	 * ID: 2152<br>
	 * Message: The assigned shortcut will be deleted and the initial shortcut
	 * setting restored. Will you continue?
	 */
	CONFIRM_SHORCUT_DELETE(2152),

	/**
	 * ID: 2153<br>
	 * Message: You are currently logged into 10 of your accounts and can no
	 * longer access your other accounts.
	 */
	MAXIMUM_ACCOUNT_LOGINS_REACHED(2153),

	/**
	 * ID: 2154<br>
	 * Message: The target is not a flagpole so a flag cannot be displayed.
	 */
	THE_TARGET_IS_NOT_A_FLAGPOLE_SO_A_FLAG_CANNOT_BE_DISPLAYED(2154),

	/**
	 * ID: 2155<br>
	 * Message: A flag is already being displayed, another flag cannot be
	 * displayed.
	 */
	A_FLAG_IS_ALREADY_BEING_DISPLAYED_ANOTHER_FLAG_CANNOT_BE_DISPLAYED(2155),

	/**
	 * ID: 2156<br>
	 * Message: There are not enough necessary items to use the skill.
	 */
	THERE_ARE_NOT_ENOUGH_NECESSARY_ITEMS_TO_USE_THE_SKILL(2156),

	/**
	 * ID: 2157<br>
	 * Message: Bid will be attempted with $s1 adena.
	 */
	BID_WILL_BE_ATTEMPTED_WITH_S1_ADENA(2157),

	/**
	 * ID: 2158<br>
	 * Message: Force attack is impossible against a temporary allied member
	 * during a siege.
	 */
	FORCED_ATTACK_IS_IMPOSSIBLE_AGAINST_SIEGE_SIDE_TEMPORARY_ALLIED_MEMBERS(
			2158),

	/**
	 * ID: 2159<br>
	 * Message: Bidder exists, the auction time has been extended by 5 minutes.
	 */
	BIDDER_EXISTS_AUCTION_TIME_EXTENDED_BY_5_MINUTES(2159),

	/**
	 * ID: 2160<br>
	 * Message: Bidder exists, the auction time has been extended by 3 minutes.
	 */
	BIDDER_EXISTS_AUCTION_TIME_EXTENDED_BY_3_MINUTES(2160),

	/**
	 * ID: 2161<br>
	 * Message: There is not enough space to move, the skill cannot be used.
	 */
	NOT_ENOUGH_SPACE_FOR_SKILL(2161),

	/**
	 * ID: 2162<br>
	 * Message: Your soul has increased by $s1, so it is now at $s2.
	 */
	YOUR_SOUL_HAS_INCREASED_BY_S1_SO_IT_IS_NOW_AT_S2(2162),

	/**
	 * ID: 2163<br>
	 * Message: Soul cannot be increased anymore.
	 */
	SOUL_CANNOT_BE_INCREASED_ANYMORE(2163),

	/**
	 * ID: 2164<br>
	 * Message: The barracks have been seized.
	 */
	SEIZED_BARRACKS(2164),

	/**
	 * ID: 2165<br>
	 * Message: The barracks function has been restored.
	 */
	BARRACKS_FUNCTION_RESTORED(2165),

	/**
	 * ID: 2166<br>
	 * Message: All barracks are occupied.
	 */
	ALL_BARRACKS_OCCUPIED(2166),

	/**
	 * ID: 2167<br>
	 * Message: A malicious skill cannot be used in a peace zone.
	 */
	A_MALICIOUS_SKILL_CANNOT_BE_USED_IN_PEACE_ZONE(2167),

	/**
	 * ID: 2168<br>
	 * Message: $c1 has acquired the flag.
	 */
	C1_ACQUIRED_THE_FLAG(2168),

	/**
	 * ID: 2169<br>
	 * Message: Your clan has been registered to $s1's fortress battle.
	 */
	REGISTERED_TO_S1_FORTRESS_BATTLE(2169),

	/**
	 * ID: 2170<br>
	 * Message: A malicious skill cannot be used when an opponent is in the
	 * peace zone
	 */
	CANT_USE_BAD_MAGIC_WHEN_OPPONENT_IN_PEACE_ZONE(2170),

	/**
	 * ID: 2171<br>
	 * Message: This item cannot be crystallized.
	 */
	ITEM_CANNOT_CRYSTALLIZED(2171),

	/**
	 * ID: 2172<br>
	 * Message: +$s1 $s2's auction has ended.
	 */
	S1_S2_AUCTION_ENDED(2172),

	/**
	 * ID: 2173<br>
	 * Message: $s1's auction has ended.
	 */
	S1_AUCTION_ENDED(2173),

	/**
	 * ID: 2174<br>
	 * Message: $c1 cannot duel because $c1 is currently polymorphed.
	 */
	C1_CANNOT_DUEL_WHILE_POLYMORPHED(2174),

	/**
	 * ID: 2175<br>
	 * Message: Party duel cannot be initiated due to a polymorphed partymember
	 */
	CANNOT_PARTY_DUEL_WHILE_A_MEMBER_IS_POLYMORPHED(2175),

	/**
	 * ID: 2176<br>
	 * Message: $s1's elemental power has been removed.
	 */
	S1_ELEMENTAL_POWER_REMOVED(2176),

	/**
	 * ID: 2177<br>
	 * Message: +$s1 $s2's elemental power has been removed.
	 */
	S1_S2_ELEMENTAL_POWER_REMOVED(2177),

	/**
	 * ID: 2178<br>
	 * Message: You failed to remove the elemental power.
	 */
	FAILED_TO_REMOVE_ELEMENTAL_POWER(2178),

	/**
	 * ID: 2179<br>
	 * Message: You have the highest bid submitted in Giran Castle Auction.
	 */
	HIGHEST_BID_FOR_GIRAN_CASTLE(2179),

	/**
	 * ID: 2180<br>
	 * Message: You have the highest bid submitted in Aden Castle Auction.
	 */
	HIGHEST_BID_FOR_ADEN_CASTLE(2180),

	/**
	 * ID: 2181<br>
	 * Message: You have the highest bid submitted in Rune Castle Auction.
	 */
	HIGHEST_BID_FOR_RUNE_CASTLE(2181),

	/**
	 * ID: 2182<br>
	 * Message: You cannot polymorph while riding a boat.
	 */
	CANT_POLYMORPH_ON_BOAT(2182),

	/**
	 * ID: 2183<br>
	 * Message: The fortress battle of $s1 has finished.
	 */
	THE_FORTRESS_BATTLE_OF_S1_HAS_FINISHED(2183),

	/**
	 * ID: 2184<br>
	 * Message: $s1 clan is victorious in the fortress battle of $s2.
	 */
	S1_CLAN_IS_VICTORIOUS_IN_THE_FORTRESS_BATTLE_OF_S2(2184),

	/**
	 * ID: 2185<br>
	 * Message: Only a party leader can try to enter.
	 */
	ONLY_PARTY_LEADER_CAN_ENTER(2185),

	/**
	 * ID: 2186<br>
	 * Message: Soul cannot be absorbed anymore.
	 */
	SOUL_CANNOT_BE_ABSORBED_ANYMORE(2186),

	/**
	 * ID: 2187<br>
	 * Message: The target is located where you cannot charge.
	 */
	CANT_REACH_TARGET_TO_CHARGE(2187),

	/**
	 * ID: 2188<br>
	 * Message: Another enchantment is in progress. Please complete previous
	 * task and try again.
	 */
	ENCHANTMENT_ALREADY_IN_PROGRESS(2188),

	/**
	 * ID: 2189<br>
	 * Message: Current Location: $s1, $s2, $s3 (near Near Kamael Village)
	 */
	LOC_KAMAEL_VILLAGE_S1_S2_S3(2189),

	/**
	 * ID: 2190<br>
	 * Message: Current Location: $s1, $s2, $s3 (Near south of Wastelands Camp)
	 */
	LOC_WASTELANDS_CAMP_S1_S2_S3(2190),

	/**
	 * ID: 2191<br>
	 * it will applied when you start the game next time. Will you apply now?
	 */
	CONFIRM_APPLY_SELECTIONS(2191),

	/**
	 * ID: 2192<br>
	 * Message: You have bid on an item auction.
	 */
	BID_ON_ITEM_AUCTION(2192),

	/**
	 * ID: 2193<br>
	 * Message: It's too far from the NPC to work.
	 */
	TOO_FAR_FROM_NPC(2193),

	/**
	 * ID: 2194<br>
	 * Message: Current polymorph form cannot be applied with corresponding
	 * effects.
	 */
	CANT_APPLY_CURRENT_POLYMORPH_WITH_CORRESPONDING_EFFECTS(2194),

	/**
	 * ID: 2195<br>
	 * Message: There is not enough soul.
	 */
	THERE_IS_NOT_ENOUGH_SOUL(2195),

	/**
	 * ID: 2196<br>
	 * Message: No Owned Clan.
	 */
	NO_OWNED_CLAN(2196),

	/**
	 * ID: 2197<br>
	 * Message: Owned by clan $s1.
	 */
	OWNED_S1_CLAN(2197),

	/**
	 * ID: 2198<br>
	 * Message: You have the highest bid in an item auction.
	 */
	HIGHEST_BID_IN_ITEM_AUCTION(2198),

	/**
	 * ID: 2199<br>
	 * Message: You cannot enter this instance zone while the NPC server is
	 * unavailable.
	 */
	CANT_ENTER_INSTANCE_ZONE_NPC_SERVER_OFFLINE(2199),

	/**
	 * ID: 2200<br>
	 * Message: This instance zone will be terminated because the NPC server is
	 * unavailable. You will be forcibly removed from the dungeon shortly
	 */
	INSTANCE_ZONE_TERMINATED_NPC_SERVER_OFFLINE(2200),

	/**
	 * ID: 2201<br>
	 * Message: $s1 year(s) $s2 month(s) $s3 day(s)
	 */
	S1_YEARS_S2_MONTHS_S3_DAYS(2201),

	/**
	 * ID: 2202<br>
	 * Message: $s1 hour(s) $s2 minute(s) $s3 second(s)
	 */
	S1_HOURS_S2_MINUTES_S3_SECONDS(2202),

	/**
	 * ID: 2203<br>
	 * Message: $s1 month(s) $s2 day(s)
	 */
	S1_MONTHS_S2_DAYS(2203),

	/**
	 * ID: 2204<br>
	 * Message: $s1 hour(s)
	 */
	S1_HOURS(2204),

	/**
	 * ID: 2205<br>
	 * Message: You have entered an area where the mini map cannot be used. The
	 * mini map will be closed.
	 */
	AREA_FORBIDS_MINIMAP(2205),

	/**
	 * ID: 2206<br>
	 * Message: You have entered an area where the mini map can be used.
	 */
	AREA_ALLOWS_MINIMAP(2206),

	/**
	 * ID: 2207<br>
	 * Message: This is an area where you cannot use the mini map. The mini map
	 * will not be opened.
	 */
	CANT_OPEN_MINIMAP(2207),

	/**
	 * ID: 2208<br>
	 * Message: You do not meet the skill level requirements.
	 */
	YOU_DONT_MEET_SKILL_LEVEL_REQUIREMENTS(2208),

	/**
	 * ID: 2209<br>
	 * Message: This is an area where radar cannot be used
	 */
	AREA_WHERE_RADAR_CANNOT_BE_USED(2209),

	/**
	 * ID: 2210<br>
	 * Message: It will return to an unenchanted condition.
	 */
	RETURN_TO_UNENCHANTED_CONDITION(2210),

	/**
	 * ID: 2211<br>
	 * Message: You have not acquired a good deed skill so you cannot acquire
	 * new skills.
	 */
	NOT_ACQUIRED_DEED_SKILL_CANNOT_ACQUIRE_SKILLS(2211),

	/**
	 * ID: 2212<br>
	 * Message: You have not completed the necessary quest for skill
	 * acquisition.
	 */
	NOT_COMPLETED_QUEST_FOR_SKILL_ACQUISITION(2212),

	/**
	 * ID: 2213<br>
	 * Message: Cannot board a ship while polymorphed.
	 */
	CANT_BOARD_SHIP_POLYMORPHED(2213),

	/**
	 * ID: 2214<br>
	 * Message: A new character will be created with the current settings.
	 * Continue
	 */
	CONFIRM_CHARACTER_CREATION(2214),

	/**
	 * ID: 2215<br>
	 * Message: $s1 P.Def
	 */
	S1_PDEF(2215),

	/**
	 * ID: 2216<br>
	 * Message: The CPU driver is not up to date. Please install an up-to-date
	 * CPU driver.
	 */
	PLEASE_UPDATE_CPU_DRIVER(2216),

	/**
	 * ID: 2217<br>
	 * Message: The ballista has been successfully destroyed and the clan's
	 * reputation will be increased.
	 */
	BALLISTA_DESTROYED_CLAN_REPU_INCREASED(2217),

	/**
	 * ID: 2218<br>
	 * Message: This is a main class skill only.
	 */
	MAIN_CLASS_SKILL_ONLY(2218),

	/**
	 * ID: 2219<br>
	 * Message: This lower clan skill has already been acquired.
	 */
	LOWER_CLAN_SKILL_ALREADY_ACQUIRED(2219),

	/**
	 * ID: 2220<br>
	 * Message: The previous level skill has not been learned.
	 */
	PREVIOUS_LEVEL_SKILL_NOT_LEARNED(2220),

	/**
	 * ID: 2221<br>
	 * Message: Will you activate the selected functions?
	 */
	ACTIVATE_SELECTED_FUNTIONS_CONFIRM(2221),

	/**
	 * ID: 2222<br>
	 * Message: It will cost 150,000 adena to place scouts. Will you place them.
	 */
	SCOUT_COSTS_150000_ADENA(2222),

	/**
	 * ID: 2223<br>
	 * Message: It will cost 200,000 adena for a fortress gate enhancement. Will
	 * you enhance it?
	 */
	FORTRESS_GATE_COSTS_200000_ADENA(2223),

	/**
	 * ID: 2224<br>
	 * Message: Crossbow is preparing to fire.
	 */
	CROSSBOW_PREPARING_TO_FIRE(2224),

	/**
	 * ID: 2225<br>
	 * Message: There are no other skills to learn. Please come back after $s1nd
	 * class change.
	 */
	NO_SKILLS_TO_LEARN_RETURN_AFTER_S1_CLASS_CHANGE(2225),

	/**
	 * ID: 2226<br>
	 * Message: Not enough bolts.
	 */
	NOT_ENOUGH_BOLTS(2226),

	/**
	 * ID: 2227<br>
	 * Message: It is not possible to register for the castle siege side or
	 * castle siege of a higher castle in the contract
	 */
	NOT_POSSIBLE_TO_REGISTER_TO_CASTLE_SIEGE(2227),

	/**
	 * ID: 2228<br>
	 * Message: Instance zone time limit:
	 */
	INSTANCE_ZONE_TIME_LIMIT(2228),

	/**
	 * ID: 2229<br>
	 * Message: There is no instance zone under a time limit
	 */
	NO_INSTANCEZONE_TIME_LIMIT(2229),

	/**
	 * ID: 2230<br>
	 * Message: Available to use after $s1 $s2hour(s) $s3minute(s).
	 */
	AVAILABLE_AFTER_S1_S2_HOURS_S3_MINUTES(2230),

	/**
	 * ID: 2231<br>
	 * Message: The reputation score of the upper castle in contract is not
	 * enough and supply was not granted.
	 */
	REPUTATION_SCORE_FOR_CONTRACT_NOT_ENOUGH(2231),

	/**
	 * ID: 2232<br>
	 * Message: $s1 will be crystallized before destruction. Will you continue?
	 */
	S1_CRYSTALLIZED_BEFORE_DESTRUCTION(2232),

	/**
	 * ID: 2233<br>
	 * Message: Siege registration is not possible due to a contract with a
	 * higher castle.
	 */
	CANT_REGISTER_TO_SIEGE_DUE_TO_CONTRACT(2233),

	/**
	 * ID: 2234<br>
	 * Message: Will you use the selected Kamael-race-only Hero Weapon?
	 */
	CONFIRM_KAMAEL_HERO_WEAPON(2234),

	/**
	 * ID: 2235<br>
	 * Message: The instance zone in use has been deleted and cannot be
	 * accessed.
	 */
	INSTANCE_ZONE_DELETED_CANT_ACCESSED(2235),

	/**
	 * ID: 2236<br>
	 * Message: $s1 minute(s) left for wyvern riding.
	 */
	S1_MINUTES_LEFT_ON_WYVERN(2236),

	/**
	 * ID: 2237<br>
	 * Message: $s1 seconds(s) left for wyvern riding.
	 */
	S1_SECONDS_LEFT_ON_WYVERN(2237),

	/**
	 * ID: 2238<br>
	 * Message: You have participated in the siege of $s1. This siege will
	 * continue for 2 hours.
	 */
	PARTICIPATING_IN_SIEGE_OF_S1(1987),

	/**
	 * ID: 2239<br>
	 * Message: The siege of $s1, in which you are participating, has finished.
	 */
	SIEGE_OF_S1_FINIHSED(2239),

	/**
	 * ID: 2240<br>
	 * Message: You cannot register for the Team Battle Clan Hall War when your
	 * Clan Lord is on the waiting list for a transaction.
	 */
	CANT_REGISTER_TO_TEAM_BATTLE_CLAN_HALL_WAR_WHILE_LORD_ON_TRANSACTION_WAITING_LIST(
			2240),

	/**
	 * ID: 2241<br>
	 * Message: You cannot apply for a Clan Lord transaction if your clan has
	 * registed for the Team Battle Clan Hall War.
	 */
	CANT_APPLY_ON_LORD_TRANSACTION_WHILE_REGISTERED_TO_TEAM_BATTLE_CLAN_HALL_WAR(
			2241),

	/**
	 * ID: 2242<br>
	 * Message: Clan members cannot leave or be expelled when they are regisered
	 * for the Team Battle Clan Hall War.
	 */
	MEMBERS_CANT_LEAVE_WHEN_REGISTERED_TO_TEAM_BATTLE_CLAN_HALL_WAR(2242),

	/**
	 * ID: 2243<br>
	 * the previous clan lord rather than the new clan lord participates in the
	 * clan hall battle.
	 */
	WHEN_BANDITSTRONGHOLD_WILDBEASTRESERVRE_CLANLORD_IN_DANGER_PREVIOUS_LORD_PARTICIPATES_IN_BATTLE(
			2243),

	/**
	 * ID: 2244<br>
	 * Message: $s1 minute(s) remaining.
	 */
	S1_MINUTES_REMAINING(2244),

	/**
	 * ID: 2245<br>
	 * Message: $s1 second(s) remaining.
	 */
	S1_SECONDS_REMAINING(2245),

	/**
	 * ID: 2246<br>
	 * Message: The contest will begin in $s1 minute(s).
	 */
	CONTEST_BEGIN_IN_S1_MINUTES(2246),

	/**
	 * ID: 2247<br>
	 * Message: You cannot board an airship while transformed.
	 */
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_TRANSFORMED(2247),

	/**
	 * ID: 2248<br>
	 * Message: You cannot board an airship while petrified.
	 */
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_PETRIFIED(2248),

	/**
	 * ID: 2249<br>
	 * Message: You cannot board an airship while dead.
	 */
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_DEAD(2249),

	/**
	 * ID: 2250<br>
	 * Message: You cannot board an airship while fishing.
	 */
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_FISHING(2250),

	/**
	 * ID: 2251<br>
	 * Message: You cannot board an airship while in battle.
	 */
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_BATTLE(2251),

	/**
	 * ID: 2252<br>
	 * Message: You cannot board an airship while in a duel.
	 */
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_A_DUEL(2252),

	/**
	 * ID: 2253<br>
	 * Message: You cannot board an airship while sitting.
	 */
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_SITTING(2253),

	/**
	 * ID: 2254<br>
	 * Message: You cannot board an airship while casting.
	 */
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_CASTING(2254),

	/**
	 * ID: 2255<br>
	 * Message: You cannot board an airship when a cursed weapon is equipped.
	 */
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_CURSED_WEAPON_IS_EQUIPPED(2255),

	/**
	 * ID: 2256<br>
	 * Message: You cannot board an airship while holding a flag.
	 */
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_HOLDING_A_FLAG(2256),

	/**
	 * ID: 2257<br>
	 * Message: You cannot board an airship while a pet or a servitor is
	 * summoned.
	 */
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_PET_OR_A_SERVITOR_IS_SUMMONED(2257),

	/**
	 * ID: 2258<br>
	 * Message: You have already boarded another airship.
	 */
	YOU_HAVE_ALREADY_BOARDED_ANOTHER_AIRSHIP(2258),

	/**
	 * ID: 2259<br>
	 * Message: Current Location: $s1, $s2, $s3 (near Fantasy Isle)
	 */
	LOC_FANTASY_ISLAND_S1_S2_S3(2259),

	/**
	 * ID: 2260<br>
	 * Message: A pet can run away if you do not fill its hunger gauge to 10% or
	 * above.
	 */
	PET_CAN_RUN_AWAY_WHEN_HUNGER_BELOW_10_PERCENT(2260),

	/**
	 * ID: 2261<br>
	 * Message: $c1 has given $c2 damage of $s3.
	 */
	C1_GAVE_C2_DAMAGE_OF_S3(2261),

	/**
	 * ID: 2262<br>
	 * Message: $c1 has received $s3 damage from $c2.
	 */
	C1_RECEIVED_DAMAGE_OF_S3_FROM_C2(2262),

	/**
	 * ID: 2263<br>
	 * Message: $c1 has received damage of $s3 through $c2.
	 */
	C1_RECEIVED_DAMAGE_OF_S3_THROUGH_C2(2263),

	/**
	 * ID: 2264<br>
	 * Message: $c1 has evaded $c2's attack.
	 */
	C1_EVADED_C2_ATTACK(2264),

	/**
	 * ID: 2265<br>
	 * Message: $c1's attack went astray.
	 */
	C1_ATTACK_WENT_ASTRAY(2265),

	/**
	 * ID: 2266<br>
	 * Message: $c1 had a critical hit!
	 */
	C1_HAD_CRITICAL_HIT(44),

	/**
	 * ID: 2267<br>
	 * Message: $c1 resisted $c2's drain.
	 */
	C1_RESISTED_C2_DRAIN(2267),

	/**
	 * ID: 2268<br>
	 * Message: $c1's attack failed.
	 */
	C1_ATTACK_FAILED(158),

	/**
	 * ID: 2269<br>
	 * Message: $c1 resisted $c2's magic.
	 */
	C1_RESISTED_C2_DRAIN2(2269),

	/**
	 * ID: 2270<br>
	 * Message: $c1 has received damage from $s2 through the fire of magic
	 */
	C1_RECEIVED_DAMAGE_FROM_S2_THROUGH_FIRE_OF_MAGIC(2270),

	/**
	 * ID: 2271<br>
	 * Message: $c1 weakly resisted $c2's magic.
	 */
	C1_WEAKLY_RESISTED_C2_MAGIC(2271),

	/**
	 * ID: 2272<br>
	 * Message: You have selected shortcuts without settings up sub-keys. You
	 * can only use the set shortcut in the Enter Chat mode. Do you still wish
	 * to use the set shortcuts
	 */
	USE_SHORTCUT_CONFIRM(2272),

	/**
	 * ID: 2273<br>
	 * Message: This skill cannot be learned while in the sub-class state.
	 * Please try again after changing to the main class.
	 */
	SKILL_NOT_FOR_SUBCLASS(2273),

	/**
	 * ID: 2276<br>
	 * Message: The rebel army recaptured the fortress.
	 */
	NPCS_RECAPTURED_FORTRESS(2276),

	/**
	 * ID: 2293<br>
	 * Message: Current location: $s1, $s2, $s3 (inside the Steel Citadel)
	 */
	LOC_STEEL_CITADEL_S1_S2_S3(2293),

	/**
	 * ID: 2296<br>
	 * Message: You have gained Vitality points.
	 */
	GAINED_VITALITY_POINTS(2296),

	/**
	 * ID: 2301<br>
	 * Message: Current location: Steel Citadel Resistance
	 */
	LOC_STEEL_CITADEL_RESISTANCE(2301),

	/**
	 * ID: 2302<br>
	 * Message: Your Vitamin Item has arrived! Visit the Vitamin Manager in any
	 * village to obtain it
	 */
	YOUR_VITAMIN_ITEM_HAS_ARRIVED(2302),

	/**
	 * ID: 2303<br>
	 * Message: There are $s2 second(s) remaining in $s1's re-use time.
	 */
	S2_SECONDS_REMAINING_FOR_REUSE_S1(46),

	/**
	 * ID: 2304<br>
	 * Message: There are $s2 minute(s), $s3 second(s) remaining in $s1's re-use
	 * time.
	 */
	S2_MINUTES_S3_SECONDS_REMAINING_FOR_REUSE_S1(46),

	/**
	 * ID: 2305<br>
	 * Message: There are $s2 hour(s), $s3 minute(s), and $s4 second(s)
	 * remaining in $s1's re-use time.
	 */
	S2_HOURS_S3_MINUTES_S4_SECONDS_REMAINING_FOR_REUSE_S1(46),

	/**
	 * ID: 2306<br>
	 * Message: Resurrection is possible because of the courage charm's effect.
	 * Would you like to resurrect now?
	 */
	RESURRECT_USING_CHARM_OF_COURAGE(2306),

	/**
	 * ID: 2314<br>
	 * Message: Your Vitality is at maximum.
	 */
	VITALITY_IS_AT_MAXIMUM(2314),

	/**
	 * ID: 2315<br>
	 * Message: You have gained Vitality points.
	 */
	VITALITY_HAS_INCREASED(2315),

	/**
	 * ID: 2316<br>
	 * Message: You have lost Vitality points.
	 */
	VITALITY_HAS_DECREASED(2316),

	/**
	 * ID: 2317<br>
	 * Message: Your Vitality is fully exhausted.
	 */
	VITALITY_IS_EXHAUSTED(2317),

	/**
	 * ID: 2319<br>
	 * Message: You have acquired $s1 reputation score.
	 */
	ACQUIRED_S1_REPUTATION_SCORE(2319),

	/**
	 * ID: 2321<br>
	 * Message: Current location: Inside Kamaloka
	 */
	LOC_KAMALOKA(2321),

	/**
	 * ID: 2322<br>
	 * Message: Current location: Inside Nia Kamaloka
	 */

	LOC_NIA_KAMALOKA(2322),
	/**
	 * ID: 2323<br>
	 * Message: Current location: Inside Rim Kamaloka
	 */
	LOC_RIM_KAMALOKA(2323),

	/**
	 * ID: 2326<br>
	 * Message: You have acquired 50 Clan's Fame Points..
	 */
	ACQUIRED_50_CLAN_FAME_POINTS(2326),

	/**
	 * ID: 2327<br>
	 * Message: You don't have enough reputation score.
	 */
	NOT_ENOUGH_FAME_POINTS(2327),

	/**
	 * ID: 2333<br>
	 * Message: You cannot receive the vitamin item because you have exceed your
	 * inventory weight/quantity limit.
	 */
	YOU_CANNOT_RECEIVE_THE_VITAMIN_ITEM(2333),

	/**
	 * ID: 2335<br>
	 * Message: There are no more vitamin items to be found
	 */
	THERE_ARE_NO_MORE_VITAMIN_ITEMS_TO_BE_FOUND(2335),

	/**
	 * ID: 2336<br>
	 * Message: CP Siphon!
	 */
	CP_SIPHON(2336),

	/**
	 * ID: 2337<br>
	 * Message: Your CP was drained because you were hit with a CP siphon skill.
	 */
	CP_DISAPPEARS_WHEN_HIT_WITH_A_HALF_KILL_SKILL(2337),

	/**
	 * ID: 2348<br>
	 * Message: You cannot use My Teleports during a battle.
	 */
	YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_BATTLE(2348),

	/**
	 * ID: 2349<br>
	 * Message: You cannot use My Teleports while participating a large-scale
	 * battle such as a castle siege, fortress siege, or hideout siege..
	 */
	YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING(2349),

	/**
	 * ID: 2350<br>
	 * Message: You cannot use My Teleports during a duel
	 */
	YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_DUEL(2350),

	/**
	 * ID: 2351<br>
	 * Message: You cannot use My Teleports while flying
	 */
	YOU_CANNOT_USE_MY_TELEPORTS_WHILE_FLYING(2351),

	/**
	 * ID: 2352<br>
	 * Message: You cannot use My Teleports while participating in an Olympiad
	 * match
	 */
	YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING_IN_AN_OLYMPIAD_MATCH(2352),

	/**
	 * ID: 2353<br>
	 * Message: You cannot use My Teleports while you are in a flint or
	 * paralyzed state
	 */
	YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_PARALYZED(2353),

	/**
	 * ID: 2354<br>
	 * Message: You cannot use My Teleports while you are dead
	 */
	YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_DEAD(2354),

	/**
	 * ID: 2355<br>
	 * Message: You cannot use My Teleports in this area
	 */
	YOU_CANNOT_USE_MY_TELEPORTS_IN_THIS_AREA(2355),

	/**
	 * ID: 2356<br>
	 * Message: You cannot use My Teleports underwater
	 */
	YOU_CANNOT_USE_MY_TELEPORTS_UNDERWATER(2356),

	/**
	 * ID: 2357<br>
	 * Message: You cannot use My Teleports in an instant zone
	 */
	YOU_CANNOT_USE_MY_TELEPORTS_IN_AN_INSTANT_ZONE(2357),

	/**
	 * ID: 2358<br>
	 * Message: You have no space to save the teleport location
	 */
	YOU_HAVE_NO_SPACE_TO_SAVE_THE_TELEPORT_LOCATION(2358),

	/**
	 * ID: 2359<br>
	 * Message: You cannot teleport because you do not have a teleport item
	 */
	YOU_CANNOT_TELEPORT_BECAUSE_YOU_DO_NOT_HAVE_A_TELEPORT_ITEM(2359),

	/**
	 * ID: 2366<br>
	 * Message: The limited-time item has been deleted..
	 */
	TIME_LIMITED_ITEM_DELETED(2366),

	/**
	 * 2372 1 There is not much time remaining until the hunting helper pet
	 * leaves.
	 */
	THERE_NOT_MUCH_TIME_REMAINING_UNTIL_HELPER_LEAVES(2372),

	/**
	 * 2373 1 The hunting helper pet is now leaving. 0 B09B79 0 0 0 0 0 none
	 */
	THE_HELPER_PET_LEAVING(2373),

	/**
	 * 2375 1 The hunting helper pet cannot be returned because there is not
	 * much time remaining until it leaves. 0
	 */
	THE_HELPER_PET_CANNOT_BE_RETURNED(2375),

	/**
	 * ID: 2376<br>
	 * Message: You cannot receive a vitamin item during an exchange.
	 */
	YOU_CANNOT_RECEIVE_A_VITAMIN_ITEM_DURING_AN_EXCHANGE(2376),

	/**
	 * ID: 2390<br>
	 * Message: Your number of My Teleports slots has reached its maximum limit.
	 */
	YOUR_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_REACHED_ITS_MAXIMUM_LIMIT(2390),

	/**
	 * ID: 2396<br>
	 * Message: That pet/servitor skill cannot be used because it is recharging.
	 */
	PET_SKILL_CANNOT_BE_USED_RECHARCHING(2396),

	/**
	 * ID: 2398<br>
	 * Message: You have no open My Teleports slots.
	 */
	YOU_HAVE_NO_OPEN_MY_TELEPORTS_SLOTS(2398),

	/**
	 * ID: 2440<br>
	 * Message: $c1 is already registered on the waiting list for the
	 * non-class-limited match event.
	 */
	C1_IS_ALREADY_REGISTERED_NON_CLASS_LIMITED_EVENT_TEAMS(2440),

	/**
	 * ID: 2441<br>
	 * Message: Only a party leader can request a team match.
	 */
	ONLY_PARTY_LEADER_CAN_REQUEST_TEAM_MATCH(2441),

	/**
	 * ID: 2442<br>
	 * Message: The request cannot be made because the requirements have not
	 * been made. To participate in a team match you must first form a 3-member
	 * party.
	 */
	PARTY_REQUIREMENTS_NOT_MET(2442),

	/**
	 * ID: 2936<br>
	 * Message: The disguise scroll cannot be used because it is meant for use
	 * in a different territory.
	 */
	THE_DISGUISE_SCROLL_MEANT_FOR_DIFFERENT_TERRITORY(2936),

	/**
	 * ID: 2937<br>
	 * Message: A territory owning clan member cannot use a disguise scroll.
	 */
	TERRITORY_OWNING_CLAN_CANNOT_USE_DISGUISE_SCROLL(2937),

	/**
	 * ID: 2955<br>
	 * Message: The territory war exclusive disguise and transformation can be
	 * used 20 minutes before the start of the territory war to 10 minutes after
	 * its end.
	 */
	TERRITORY_WAR_SCROLL_CAN_NOT_USED_NOW(2955),

	/**
	 * ID: 2400<br>
	 * Message: Instant Zone currently in use: $s1
	 */
	INSTANT_ZONE_CURRENTLY_INUSE_S1(46),

	/**
	 * ID: 2402<br>
	 * Message: The Territory War request period has ended.
	 */
	THE_TERRITORY_WAR_REGISTERING_PERIOD_ENDED(2402),

	/**
	 * ID: 2403<br>
	 * Message: Territory War begins in 10 minutes!
	 */
	TERRITORY_WAR_BEGINS_IN_10_MINUTES(2403),

	/**
	 * ID: 2404<br>
	 * Message: Territory War begins in 5 minutes!
	 */
	TERRITORY_WAR_BEGINS_IN_5_MINUTES(2404),

	/**
	 * ID: 2405<br>
	 * Message: Territory War begins in 1 minute!
	 */
	TERRITORY_WAR_BEGINS_IN_1_MINUTE(2405),

	/**
	 * ID: 2408<br>
	 * Message: You have registered on the waiting list for the
	 * non-class-limited team match event.
	 */
	YOU_HAVE_REGISTERED_IN_A_WAITING_LIST_OF_TEAM_GAMES(2408),

	/**
	 * ID: 2409<br>
	 * Message: The number of My Teleports slots has been increased.
	 */
	THE_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_BEEN_INCREASED(2409),

	/**
	 * ID: 2410<br>
	 * Message: You cannot use My Teleports to reach this area!
	 */
	YOU_CANNOT_USE_MY_TELEPORTS_TO_REACH_THIS_AREA(2410),

	/**
	 * ID: 2424<br>
	 * Message: The collection has failed.
	 */
	THE_COLLECTION_HAS_FAILED(2424),

	/**
	 * ID: 2448 Message: Your birthday gift has arrived
	 */
	YOUR_BIRTHDAY_GIFT_HAS_ARRIVED(2448),

	/**
	 * ID: 2449 Message: There are $s1 days until your character's birthday.
	 */
	THERE_ARE_S1_DAYS_UNTIL_YOUR_CHARACTERS_BIRTHDAY(2449),

	/**
	 * ID: 2450 Message: $c1's character birthday is $s3/$s4/$s2.
	 */
	C1_BIRTHDAY_IS_S3_S4_S2(2450),

	/**
	 * ID: 2451 Message: The cloak equip has been removed because the armor set
	 * equip has been removed.
	 */
	CLOAK_REMOVED_BECAUSE_ARMOR_SET_REMOVED(2451),

	/**
	 * ID: 2455<br>
	 * Message: The airship must be summoned in order for you to board.
	 */
	THE_AIRSHIP_MUST_BE_SUMMONED_TO_BOARD(2455),

	/**
	 * ID: 2456<br>
	 * Message: In order to acquire an airship, the clan's level must be level 5
	 * or higher.
	 */
	THE_AIRSHIP_NEED_CLANLVL_5_TO_SUMMON(2456),

	/**
	 * ID: 2457<br>
	 * Message: An airship cannot be summoned because either you have not
	 * registered your airship license, or the airship has not yet been summoned
	 */
	THE_AIRSHIP_NEED_LICENSE_TO_SUMMON(2457),

	/**
	 * ID: 2458<br>
	 * Message: The airship owned by the clan is already being used by another
	 * clan member.
	 */
	THE_AIRSHIP_ALREADY_USED(2458),

	/**
	 * ID: 2459<br>
	 * Message: The Airship Summon License has already been acquired.
	 */
	THE_AIRSHIP_SUMMON_LICENSE_ALREADY_ACQUIRED(2459),

	/**
	 * ID:2460<br>
	 * Message: The clan owned airship already exists.
	 */
	THE_AIRSHIP_IS_ALREADY_EXISTS(2460),

	/**
	 * ID:2461<br>
	 * Message: The airship owned by the clan can only be purchased by the clan
	 * lord.
	 */
	THE_AIRSHIP_NO_PRIVILEGES(2461),

	/**
	 * ID:2462<br>
	 * Message: The airship cannot be summoned because you don't have enough
	 * $s1%.
	 */
	THE_AIRSHIP_NEED_MORE_S1(1987),

	/**
	 * ID: 2463 Message: The airship's fuel (EP) will soon run out.
	 */
	THE_AIRSHIP_FUEL_SOON_RUN_OUT(2463),

	/**
	 * ID: 2464 Message: The airship's fuel (EP) has run out. The airship's
	 * speed will be greatly decreased in this condition.
	 */
	THE_AIRSHIP_FUEL_RUN_OUT(2464),

	/**
	 * ID: 2465 Message: You have selected a 3 vs 3 class irrelevant team match.
	 * Do you wish to participate?
	 */
	OLYMPIAD_3VS3_CONFIRM(2465),

	/**
	 * ID: 2491 Message: Your ship cannot teleport because it does not have
	 * enough fuel for the trip.
	 */
	THE_AIRSHIP_CANNOT_TELEPORT(2491),

	/**
	 * ID: 2492 Message: The airship has been summoned. It will automatically
	 * depart in %s minutes.
	 */
	THE_AIRSHIP_SUMMONED(2492),

	/**
	 * ID: 2500<br>
	 * Message: The collection has succeeded.
	 */
	THE_COLLECTION_HAS_SUCCEEDED(2500),

	/**
	 * ID: 2701 Message: The match is being prepared. Please try again later.
	 */
	MATCH_BEING_PREPARED_TRY_LATER(2701),

	/**
	 * ID: 2702 Message: You were excluded from the match because the
	 * registration count was not correct.
	 */
	EXCLUDED_FROM_MATCH_DUE_INCORRECT_COUNT(2702),

	/**
	 * ID: 2703 Message: The team was adjusted because the population ratio was
	 * not correct.
	 */
	TEAM_ADJUSTED_BECAUSE_WRONG_POPULATION_RATIO(2703),

	/**
	 * ID: 2704<br>
	 * Message: You cannot register because capacity has been exceeded.
	 */
	CANNOT_REGISTER_CAUSE_QUEUE_FULL(2704),

	/**
	 * ID: 2705 Message: The match waiting time was extended by 1 minute.
	 */
	MATCH_WAITING_TIME_EXTENDED(2705),

	/**
	 * ID: 2706 Message: You cannot enter because you do not meet the
	 * requirements.
	 */
	CANNOT_ENTER_CAUSE_DONT_MATCH_REQUIREMENTS(2706),

	/**
	 * ID: 2707 Message: You cannot make another request for 10 seconds after
	 * cancelling a match registration.
	 */
	CANNOT_REQUEST_REGISTRATION_10_SECS_AFTER(2707),

	/**
	 * ID: 2708<br>
	 * Message: You cannot register while possessing a cursed weapon.
	 */
	CANNOT_REGISTER_PROCESSING_CURSED_WEAPON(2708),

	/**
	 * ID: 2709<br>
	 * Message: Applicants for the Olympiad, Underground Coliseum, or Kratei's
	 * Cube matches cannot register.
	 */
	COLISEUM_OLYMPIAD_KRATEIS_APPLICANTS_CANNOT_PARTICIPATE(2709),

	/**
	 * ID: 2710<br>
	 * Message: Current location: $s1, $s2, $s3 (near the Keucereus clan
	 * association location)
	 */
	LOC_KEUCEREUS_S1_S2_S3(2710),

	/**
	 * ID: 2711<br>
	 * Message: Current location: $s1, $s2, $s3 (inside the Seed of Infinity)
	 */
	LOC_IN_SEED_INFINITY_S1_S2_S3(2711),

	/**
	 * ID: 2712<br>
	 * Message: Current location: $s1, $s2, $s3 (outside the Seed of Infinity)
	 */
	LOC_OUT_SEED_INFINITY_S1_S2_S3(2712),

	/**
	 * ID: 2716<br>
	 * Message: Current location: $s1, $s2, $s3 (inside Aerial Cleft)
	 */
	LOC_CLEFT_S1_S2_S3(2716),

	/**
	 * ID: 2720<br>
	 * Message: Instant zone from here: $s1's entry has been restricted.
	 */
	INSTANT_ZONE_S1_RESTRICTED(2720),

	/**
	 * ID: 2721<br>
	 * Message: Boarding or cancellation of boarding on Airships is not allowed
	 * in the current area.
	 */
	BOARD_OR_CANCEL_NOT_POSSIBLE_HERE(2721),

	/**
	 * ID: 2722<br>
	 * Message: Another airship has already been summoned at the wharf. Please
	 * try again later.
	 */
	ANOTHER_AIRSHIP_ALREADY_SUMMONED(2722),

	/**
	 * ID: 2727<br>
	 * Message: You cannot board because you do not meet the requirements.
	 */
	YOU_CANNOT_BOARD_NOT_MEET_REQUEIREMENTS(2727),

	/**
	 * ID: 2729<br>
	 * Message: You cannot control the helm while transformed.
	 */
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_TRANSFORMED(2729),

	/**
	 * ID: 2730<br>
	 * Message: You cannot control the helm while you are petrified.
	 */
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_YOU_ARE_PETRIFIED(2730),

	/**
	 * ID: 2731<br>
	 * Message: You cannot control the helm when you are dead.
	 */
	YOU_CANNOT_CONTROL_THE_HELM_WHEN_YOU_ARE_DEAD(2731),

	/**
	 * ID: 2732<br>
	 * Message: You cannot control the helm while fishing.
	 */
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_FISHING(2732),

	/**
	 * ID: 2733<br>
	 * Message: You cannot control the helm while in a battle.
	 */
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_BATTLE(2733),

	/**
	 * ID: 2734<br>
	 * Message: You cannot control the helm while in a duel.
	 */
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_DUEL(2734),
	/**
	 * ID: 2735<br>
	 * Message: You cannot control the helm while in a sitting position.
	 */
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_SITTING_POSITION(2735),

	/**
	 * ID: 2736<br>
	 * Message: You cannot control the helm while using a skill.
	 */
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_USING_A_SKILL(2736),

	/**
	 * ID: 2737<br>
	 * Message: You cannot control the helm while a cursed weapon is equipped.
	 */
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_A_CURSED_WEAPON_IS_EQUIPPED(2737),

	/**
	 * ID: 2738<br>
	 * Message: You cannot control the helm while holding a flag.
	 */
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_HOLDING_A_FLAG(2738),

	/**
	 * ID: 2750<br>
	 * Message: The $s1 ward has been destroyed! $c2 now has the territory ward.
	 */
	THE_S1_WARD_HAS_BEEN_DESTROYED(2750),

	/**
	 * ID: 2751<br>
	 * Message: The character that acquired $s1 ward has been killed.
	 */
	THE_CHAR_THAT_ACQUIRED_S1_WARD_HAS_BEEN_KILLED(2751),

	/**
	 * ID: 2762<br>
	 * Message: You cannot control because you are too far.
	 */
	CANT_CONTROL_TOO_FAR(2762),

	/**
	 * ID: 2766<br>
	 * Message: Seed of Infinity Stage 1 Attack In Progress.
	 */
	SEED_OF_INFINITY_STAGE_1_ATTACK_IN_PROGRESS(2766),

	/**
	 * ID: 2767<br>
	 * Message: Seed of Infinity Stage 2 Attack In Progress.
	 */
	SEED_OF_INFINITY_STAGE_2_ATTACK_IN_PROGRESS(2767),

	/**
	 * ID: 2768<br>
	 * Message: Seed of Infinity Conquest Complete.
	 */
	SEED_OF_INFINITY_CONQUEST_COMPLETE(2768),

	/**
	 * ID: 2769<br>
	 * Message: Seed of Infinity Stage 1 Defense In Progress.
	 */
	SEED_OF_INFINITY_STAGE_1_DEFENSE_IN_PROGRESS(2769),

	/**
	 * ID: 2770<br>
	 * Message: Seed of Infinity Stage 2 Defense In Progress.
	 */
	SEED_OF_INFINITY_STAGE_2_DEFENSE_IN_PROGRESS(2770),

	/**
	 * ID: 2771<br>
	 * Message: Seed of Destruction Attack in Progress.
	 */
	SEED_OF_DESTRUCTION_ATTACK_IN_PROGRESS(2771),

	/**
	 * ID: 2772<br>
	 * Message: Seed of Destruction Conquest Complete.
	 */
	SEED_OF_DESTRUCTION_CONQUEST_COMPLETE(2772),

	/**
	 * ID: 2773<br>
	 * Message: Seed of Destruction Defense in Progress.
	 */
	SEED_OF_DESTRUCTION_DEFENSE_IN_PROGRESS(2773),

	/**
	 * ID: 2777<br>
	 * Message: The airship's summon license has been entered. Your clan can now
	 * summon the airship.
	 */
	THE_AIRSHIP_SUMMON_LICENSE_ENTERED(2777),

	/**
	 * ID: 2778<br>
	 * Message: You cannot teleport while in possession of a ward.
	 */
	YOU_CANNOT_TELEPORT_WHILE_IN_POSSESSION_OF_A_WARD(2778),

	/**
	 * ID: 2795<br>
	 * Message: You've already requested a territory war in another territory
	 * elsewhere.
	 */
	YOU_ALREADY_REQUESTED_TW_REGISTRATION(2795),

	/**
	 * ID: 2796<br>
	 * Message: The clan who owns the territory cannot participate in the
	 * territory war as mercenaries.
	 */
	THE_TERRITORY_OWNER_CLAN_CANNOT_PARTICIPATE_AS_MERCENARIES(2796),

	/**
	 * ID: 2797<br>
	 * Message: It is not a territory war registration period, so a request
	 * cannot be made at this time.
	 */
	NOT_TERRITORY_REGISTRATION_PERIOD(410),

	/**
	 * ID: 2798<br>
	 * Message: The territory war will end in $s1-hour(s).
	 */
	THE_TERRITORY_WAR_WILL_END_IN_S1_HOURS(2204),

	/**
	 * ID: 2799<br>
	 * Message: The territory war will end in $s1-minute(s).
	 */
	THE_TERRITORY_WAR_WILL_END_IN_S1_MINUTES(2799),

	/**
	 * ID: 2900<br>
	 * Message: $s1-second(s) to the end of territory war!
	 */
	S1_SECONDS_TO_THE_END_OF_TERRITORY_WAR(2900),

	/**
	 * ID: 2901<br>
	 * Message: You cannot force attack a member of the same territory.
	 */
	YOU_CANNOT_ATTACK_A_MEMBER_OF_THE_SAME_TERRITORY(2901),

	/**
	 * ID: 2902<br>
	 * Message: You've acquired the ward. Move quickly to your forces' outpost.
	 */
	YOU_VE_ACQUIRED_THE_WARD(2902),

	/**
	 * ID: 2903<br>
	 * Message: Territory war has begun.
	 */
	TERRITORY_WAR_HAS_BEGUN(2903),

	/**
	 * ID: 2904<br>
	 * Message: Territory war has ended.
	 */
	TERRITORY_WAR_HAS_ENDED(2904),

	/**
	 * ID: 2911 You've requested $c1 to be on your Friends List.
	 */
	YOU_REQUESTED_C1_TO_BE_FRIEND(2911),

	/**
	 * ID: 2913 Message: Clan $s1 has succeeded in capturing $s2's territory
	 * ward.
	 */
	CLAN_S1_HAS_SUCCEDED_IN_CAPTURING_S2_TERRITORY_WARD(2913),

	/**
	 * ID: 2914<br>
	 * Message: The territory war will begin in 20 minutes! Territory related
	 * functions (ie: battlefield channel, Disguise Scrolls, Transformations,
	 * etc...) can now be used.
	 */
	TERRITORY_WAR_BEGINS_IN_20_MINUTES(2914),

	/**
	 * ID: 2922<br>
	 * Message: Block Checker will end in 5 seconds!
	 */
	BLOCK_CHECKER_ENDS_5(2922),

	/**
	 * ID: 2923<br>
	 * Message: Block Checker will end in 4 seconds!!
	 */
	BLOCK_CHECKER_ENDS_4(2923),

	/**
	 * ID: 2924<br>
	 * Message: You cannot enter a Seed while in a flying transformation state.
	 */
	YOU_CANNOT_ENTER_SEED_IN_FLYING_TRANSFORM(2924),

	/**
	 * ID: 2925<br>
	 * Message: Block Checker will end in 3 seconds!!!
	 */
	BLOCK_CHECKER_ENDS_3(2925),

	/**
	 * ID: 2926<br>
	 * Message: Block Checker will end in 2 seconds!!!!
	 */
	BLOCK_CHECKER_ENDS_2(2926),

	/**
	 * ID: 2927<br>
	 * Message: Block Checker will end in 1 second!!!!!
	 */
	BLOCK_CHECKER_ENDS_1(2927),

	/**
	 * ID: 2928<br>
	 * Message: The $c1 team has won.
	 */
	TEAM_C1_WON(2928),

	/**
	 * ID: 2961<br>
	 * Message: $s2 unit(s) of the item $s1 is/are required.
	 */
	S2_UNIT_OF_THE_ITEM_S1_REQUIRED(2961),

	/**
	 * ID: 2964<br>
	 * Message: Being appointed as a Noblesse will cancel all related quests. Do
	 * you wish to continue?
	 */
	CANCEL_NOBLESSE_QUESTS(2964),

	/**
	 * ID: 2966<br>
	 * Message: This is a Payment Request transaction. Please attach the item.
	 */
	PAYMENT_REQUEST_NO_ITEM(2966),

	/**
	 * ID: 2968<br>
	 * Message: The mail limit (240) has been exceeded and this cannot be
	 * forwarded.
	 */
	CANT_FORWARD_MAIL_LIMIT_EXCEEDED(2968),

	/**
	 * ID: 2969<br>
	 * Message: The previous mail was forwarded less than 1 minute ago and this
	 * cannot be forwarded.
	 */
	CANT_FORWARD_LESS_THAN_MINUTE(2969),

	/**
	 * ID: 2970<br>
	 * Message: You cannot forward in a non-peace zone.
	 */
	CANT_FORWARD_NOT_IN_PEACE_ZONE(2970),

	/**
	 * ID: 2971<br>
	 * Message: You cannot forward during exchange.
	 */
	CANT_FORWARD_DURING_EXCHANGE(2971),

	/**
	 * ID: 2972<br>
	 * Message: You cannot forward because the private shop or workshop is in
	 * progress.
	 */
	CANT_FORWARD_PRIVATE_STORE(2972),

	/**
	 * ID: 2973<br>
	 * Message: You cannot forward during an item enhancement or attribute
	 * enhancement.
	 */
	CANT_FORWARD_DURING_ENCHANT(2973),

	/**
	 * ID: 2974<br>
	 * Message: The item that you're trying to send cannot be forwarded because
	 * it isn't proper.
	 */
	CANT_FORWARD_BAD_ITEM(2974),

	/**
	 * ID: 2975<br>
	 * Message: You cannot forward because you don't have enough adena.
	 */
	CANT_FORWARD_NO_ADENA(2975),

	/**
	 * ID: 2976<br>
	 * Message: You cannot receive in a non-peace zone location.
	 */
	CANT_RECEIVE_NOT_IN_PEACE_ZONE(2976),

	/**
	 * ID: 2977<br>
	 * Message: You cannot receive during an exchange.
	 */
	CANT_RECEIVE_DURING_EXCHANGE(2977),

	/**
	 * ID: 2978<br>
	 * Message: You cannot receive because the private shop or workshop is in
	 * progress.
	 */
	CANT_RECEIVE_PRIVATE_STORE(2978),

	/**
	 * ID: 2979<br>
	 * Message: You cannot receive during an item enhancement or attribute
	 * enhancement.
	 */
	CANT_RECEIVE_DURING_ENCHANT(2979),

	/**
	 * ID: 2980<br>
	 * Message: You cannot receive because you don't have enough adena.
	 */
	CANT_RECEIVE_NO_ADENA(2980),

	/**
	 * ID: 2981<br>
	 * Message: You cannot receive because your inventory is full.
	 */
	CANT_RECEIVE_INVENTORY_FULL(2981),

	/**
	 * ID: 2982<br>
	 * Message: You cannot cancel in a non-peace zone location.
	 */
	CANT_CANCEL_NOT_IN_PEACE_ZONE(2982),

	/**
	 * ID: 2983<br>
	 * Message: You cannot cancel during an exchange.
	 */
	CANT_CANCEL_DURING_EXCHANGE(2983),

	/**
	 * ID: 2984<br>
	 * Message: You cannot cancel because the private shop or workshop is in
	 * progress.
	 */
	CANT_CANCEL_PRIVATE_STORE(2984),

	/**
	 * ID: 2985<br>
	 * Message: You cannot cancel during an item enhancement or attribute
	 * enhancement.
	 */
	CANT_CANCEL_DURING_ENCHANT(2985),

	/**
	 * ID: 2988<br>
	 * Message: You could not cancel receipt because your inventory is full.
	 */
	CANT_CANCEL_INVENTORY_FULL(2988),

	/**
	 * ID: 3002<br>
	 * Message: When the recipient doesn't exist or the character is deleted,
	 * sending mail is not possible.
	 */
	RECIPIENT_NOT_EXIST(3002),

	/**
	 * ID: 3008<br>
	 * Message: The mail has arrived.
	 */
	MAIL_ARRIVED(3008),

	/**
	 * ID: 3009<br>
	 * Message: Mail successfully sent.
	 */
	MAIL_SUCCESSFULLY_SENT(3009),

	/**
	 * ID: 3010<br>
	 * Message: Mail successfully returned.
	 */
	MAIL_SUCCESSFULLY_RETURNED(3010),

	/**
	 * ID: 3011<br>
	 * Message: Mail successfully cancelled.
	 */
	MAIL_SUCCESSFULLY_CANCELLED(3011),

	/**
	 * ID: 3012<br>
	 * Message: Mail successfully received.
	 */
	MAIL_SUCCESSFULLY_RECEIVED(3012),

	/**
	 * ID: 3013<br>
	 * Message: $c1 has successfuly enchanted a +$s2 $s3.
	 */
	C1_SUCCESSFULY_ENCHANTED_A_S2_S3(3013),

	/**
	 * ID: 3014<br>
	 * Message: Do you wish to erase the selected mail ?
	 */
	DO_YOU_WISH_TO_ERASE_MAIL(3014),

	/**
	 * ID: 3015<br>
	 * Message: Please select the mail to be deleted.
	 */
	PLEASE_SELECT_MAIL_TO_BE_DELETED(3015),

	/**
	 * ID: 3016<br>
	 * Message: Item selection is possible up to 8.
	 */
	ITEM_SELECTION_POSSIBLE_UP_TO_8(3016),

	/**
	 * ID: 3019<br>
	 * Message: You cannot send mail to yourself.
	 */
	YOU_CANT_SEND_MAIL_TO_YOURSELF(3019),

	/**
	 * ID: 3020<br>
	 * Message: When not entering the amount for the payment request, you cannot
	 * send any mail.
	 */
	PAYMENT_AMOUNT_NOT_ENTERED(3020),

	/**
	 * ID: 3023<br>
	 * Message: I can feel that the energy being flown in the Kasha's eye is
	 * getting stronger rapidly.
	 */
	I_CAN_FEEL_ENERGY_KASHA_EYE_GETTING_STRONGER_RAPIDLY(3023),

	/**
	 * ID: 3024<br>
	 * Message: Kasha's eye pitches and tosses like it's about to explode.
	 */
	KASHA_EYE_PITCHES_TOSSES_EXPLODE(3024),

	/**
	 * ID: 3025<br>
	 * Message: Payment of $s1 Adena was completed by $s2.
	 */
	PAYMENT_OF_S1_ADENA_COMPLETED_BY_S2(3025),

	/**
	 * ID: 3026<br>
	 * Message: You cannot use the skill enhancing function on this level. You
	 * can use the corresponding function on levels higher than 76Lv .
	 */
	YOU_CANNOT_USE_SKILL_ENCHANT_ON_THIS_LEVEL(3026),

	/**
	 * ID: 3027<br>
	 * Message: You cannot use the skill enhancing function in this class. You
	 * can use corresponding function when completing the third class change.
	 */
	YOU_CANNOT_USE_SKILL_ENCHANT_IN_THIS_CLASS(3027),

	/**
	 * ID: 3028<br>
	 * Message: "You cannot use the skill enhancing function in this class. You
	 * can use the skill enhancing function under off-battle status, and cannot
	 * use the function while transforming, battling and on-board.
	 */
	YOU_CANNOT_USE_SKILL_ENCHANT_ATTACKING_TRANSFORMED_BOAT(3028),

	/**
	 * ID: 3029<br>
	 * Message: $s1 returned the mail.
	 */
	S1_RETURNED_MAIL(3029),

	/**
	 * ID: 3030<br>
	 * Message: You cannot cancel sent mail since the recipient received it.
	 */
	YOU_CANT_CANCEL_RECEIVED_MAIL(3030),

	/**
	 * ID: 3059<br>
	 * Message: $s1 did not receive it during the waiting time, so it was
	 * returned automatically.
	 */
	S1_NOT_RECEIVE_DURING_WAITING_TIME_MAIL_RETURNED(3059),

	/**
	 * ID: 3062<br>
	 * Message: Do you want to pay $s1 Adena ?
	 */
	DO_YOU_WANT_TO_PAY_S1_ADENA(3062),

	/**
	 * ID: 3063<br>
	 * Message: Do you really want to forward ?
	 */
	DO_YOU_WANT_TO_FORWARD(3063),

	/**
	 * ID: 3064<br>
	 * Message: There is an unread mail.
	 */
	UNREAD_MAIL(3064),

	/**
	 * ID: 3065<br>
	 * Message: Current location: Inside the Chamber of Delusion
	 */
	LOC_DELUSION_CHAMBER(3065),

	/**
	 * ID: 3066<br>
	 * Message: You cannot use the mail function outside the Peace Zone.
	 */
	CANT_USE_MAIL_OUTSIDE_PEACE_ZONE(3066),

	/**
	 * ID: 3067<br>
	 * Message: $s1 cancelled the sent mail.
	 */
	S1_CANCELLED_MAIL(3067),

	/**
	 * ID: 3068<br>
	 * Message: The mail was returned due to the exceeded waiting time.
	 */
	MAIL_RETURNED(3068),

	/**
	 * ID: 3069<br>
	 * Message: Do you really want to cancel the transaction ?
	 */
	DO_YOU_WANT_TO_CANCEL_TRANSACTION(3069),

	/**
	 * ID: 3072<br>
	 * Message: $s1 acquired the attached item to your mail.
	 */
	S1_ACQUIRED_ATTACHED_ITEM(3072),

	/**
	 * ID: 3073<br>
	 * Message: You have acquired $s2 $s1.
	 */
	YOU_ACQUIRED_S2_S1(1987),

	/**
	 * ID: 3074<br>
	 * Message: The allowed length for recipient exceeded.
	 */
	ALLOWED_LENGTH_FOR_RECIPIENT_EXCEEDED(3074),

	/**
	 * ID: 3075<br>
	 * Message: The allowed length for a title exceeded.
	 */
	ALLOWED_LENGTH_FOR_TITLE_EXCEEDED(3075),

	/**
	 * ID: 3077<br>
	 * Message: The mail limit (240) of the opponent's character has been
	 * exceeded and this cannot be forwarded.
	 */
	MAIL_LIMIT_EXCEEDED(3077),

	/**
	 * ID: 3078<br>
	 * Message: You're making a request for payment. Do you want to proceed ?
	 */
	YOU_MAKING_PAYMENT_REQUEST(3078),

	/**
	 * ID: 3079<br>
	 * Message: There are items in the Pet Inventory so you cannot register as
	 * an individual store or drop items. Please empty the items in the Pet
	 * Inventory.
	 */
	ITEMS_IN_PET_INVENTORY(3079),

	/**
	 * ID: 3080<br>
	 * Message: You cannot reset the Skill Link because there is not enough
	 * Adena.
	 */
	CANNOT_RESET_SKILL_LINK_BECAUSE_NOT_ENOUGH_ADENA(3080),

	/**
	 * ID: 3081<br>
	 * Message: You cannot receive it because you are under condition that the
	 * opponent cannot acquire any Adena for payment
	 */
	YOU_CANNOT_RECEIVE_CONDITION_OPPONENT_CANT_ACQUIRE_ADENA(3081),

	/**
	 * ID: 3082<br>
	 * Message: You cannot send mail to any character that has blocked you.
	 */
	YOU_CANNOT_SEND_MAIL_TO_CHAR_BLOCK_YOU(3082),

	/**
	 * ID: 3108<br>
	 * Message: You are no longer protected from aggressive monsters.
	 */
	YOU_ARE_NO_LONGER_PROTECTED_FROM_AGGRESSIVE_MONSTERS(3108),

	/**
	 * ID: 3119<br>
	 * Message: The couple action was denied.
	 */
	COUPLE_ACTION_DENIED(3119),

	/**
	 * ID: 3120<br>
	 * Message: The request cannot be completed because the target does not meet
	 * location requirements.
	 */
	TARGET_DO_NOT_MEET_LOC_REQUIREMENTS(3120),

	/**
	 * ID: 3121<br>
	 * Message: The couple action was cancelled.
	 */
	COUPLE_ACTION_CANCELED(3121),

	/**
	 * ID: 3135<br>
	 * Message: "Requesting approval for changing party loot to ""$s1""."
	 */
	REQUESTING_APPROVAL_CHANGE_PARTY_LOOT_S1(1987),

	/**
	 * ID: 3137<br>
	 * Message: Party loot change was cancelled.
	 */
	PARTY_LOOT_CHANGE_CANCELLED(3137),

	/**
	 * ID: 3138<br>
	 * Message: "Party loot was changed to ""$s1""."
	 */
	PARTY_LOOT_CHANGED_S1(1987),

	/**
	 * ID: 3144<br>
	 * Message:
	 * "The $s2's attribute was successfully bestowed on $s1, and resistance to $s3 was increased."
	 */
	THE_S2_ATTRIBUTE_WAS_SUCCESSFULLY_BESTOWED_ON_S1_RES_TO_S3_INCREASED(3144),

	/**
	 * ID: 3150<br>
	 * Message: You have requested a couple action with $c1.
	 */
	YOU_HAVE_REQUESTED_COUPLE_ACTION_C1(3150),

	/**
	 * ID: 3152<br>
	 * Message:
	 * "$s1's $s2 attribute was removed, and resistance to $s3 was decreased."
	 */
	S1_S2_ATTRIBUTE_REMOVED_RESISTANCE_S3_DECREASED(3152),

	/**
	 * ID: 3156<br>
	 * Message: You do not have enough funds to cancel this attribute.
	 */
	YOU_DO_NOT_HAVE_ENOUGH_FUNDS_TO_CANCEL_ATTRIBUTE(3156),

	/**
	 * ID: 3160<br>
	 * Message:
	 * "+$s1$s2's $s3 attribute was removed, so resistance to $s4 was decreased."
	 */
	S1_S2_S3_ATTRIBUTE_REMOVED_RESISTANCE_TO_S4_DECREASED(3160),

	/**
	 * ID: 3163<br>
	 * Message:
	 * "The $s3's attribute was successfully bestowed on +$s1$s2, and resistance to $s4 was increased."
	 */
	THE_S3_ATTRIBUTE_BESTOWED_ON_S1_S2_RESISTANCE_TO_S4_INCREASED(3163),

	/**
	 * ID: 3164<be> Message: $c1 is set to refuse couple actions and cannot be
	 * requested for a couple action.
	 */
	C1_IS_SET_TO_REFUSE_COUPLE_ACTIONS(3164),

	/**
	 * ID: 3168<br>
	 * Message: $c1 is set to refuse party requests and cannot receive a party
	 * request.
	 */
	C1_IS_SET_TO_REFUSE_PARTY_REQUEST(3168),

	/**
	 * ID: 3169<br>
	 * Message: $c1 is set to refuse duel requests and cannot receive a duel
	 * request.
	 */
	C1_IS_SET_TO_REFUSE_DUEL_REQUEST(3169),

	/**
	 * ID: 3206<br>
	 * Message: You currently do not have any Recommendations.
	 */
	YOU_CURRENTLY_DO_NOT_HAVE_ANY_RECOMMENDATIONS(3206),

	/**
	 * ID: 3207<br>
	 * Message: You obtained $s1 Recommendations
	 */
	YOU_OBTAINED_S1_RECOMMENDATIONS(3207),

	/**
	 * ID: 6501<br>
	 * Message: You cannot bookmark this location because you do not have a My
	 * Teleport Flag.
	 */
	YOU_CANNOT_BOOKMARK_THIS_LOCATION_BECAUSE_YOU_DO_NOT_HAVE_A_MY_TELEPORT_FLAG(
			6501),

	/**
	 * ID: 6503<br>
	 * Message: The evil Thomas D. Turkey has appeared. Please save Santa.
	 */
	THOMAS_D_TURKEY_APPEARED(6503),

	/**
	 * ID: 6504<br>
	 * Message: You won the battle against Thomas D. Turkey. Santa has been
	 * rescued.
	 */
	THOMAS_D_TURKEY_DEFETED(6504),

	/**
	 * ID: 6505<br>
	 * Message: You did not rescue Santa, and Thomas D. Turkey has disappeared.
	 */
	THOMAS_D_TURKEY_DISAPPEARED(6505);

	public final int id;
	public final SM_SYSTEM_MESSAGE packet;

	SystemMessage(int id) {
		this.id = id;
		this.packet = new SM_SYSTEM_MESSAGE(this);
	}
}
