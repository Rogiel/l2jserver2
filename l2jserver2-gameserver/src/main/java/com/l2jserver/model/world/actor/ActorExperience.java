/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.world.actor;

import com.l2jserver.model.world.Actor;

/**
 * This enumeration maps for each level the minimum experience required.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@SuppressWarnings("javadoc")
public enum ActorExperience {
	/**
	 * This is an unreachable level!
	 */
	LEVEL_0(-1L),

	LEVEL_1(0L), LEVEL_2(68L), LEVEL_3(363L), LEVEL_4(1168L), LEVEL_5(2884L), LEVEL_6(
			6038L), LEVEL_7(11287L), LEVEL_8(19423L), LEVEL_9(31378L),

	LEVEL_10(48229L), LEVEL_11(71201L), LEVEL_12(101676L), LEVEL_13(141192L), LEVEL_14(
			191452L), LEVEL_15(254327L), LEVEL_16(331864L), LEVEL_17(426284L), LEVEL_18(
			539995L), LEVEL_19(675590L),

	LEVEL_20(835854L), LEVEL_21(1023775L), LEVEL_22(1242536L), LEVEL_23(
			1495531L), LEVEL_24(1786365L), LEVEL_25(2118860L), LEVEL_26(
			2497059L), LEVEL_27(2925229L), LEVEL_28(3407873L), LEVEL_29(
			3949727L),

	LEVEL_30(4555766L), LEVEL_31(5231213L), LEVEL_32(5981539L), LEVEL_33(
			6812472L), LEVEL_34(7729999L), LEVEL_35(8740372L), LEVEL_36(
			9850111L), LEVEL_37(11066012L), LEVEL_38(12395149L), LEVEL_39(
			13844879L),

	LEVEL_40(15422851L), LEVEL_41(17137002L), LEVEL_42(18995573L), LEVEL_43(
			21007103L), LEVEL_44(23180442L), LEVEL_45(25524751L), LEVEL_46(
			28049509L), LEVEL_47(30764519L), LEVEL_48(33679907L), LEVEL_49(
			36806133L),

	LEVEL_50(40153995L), LEVEL_51(45524865L), LEVEL_52(51262204L), LEVEL_53(
			57383682L), LEVEL_54(63907585L), LEVEL_55(70852742L), LEVEL_56(
			80700339L), LEVEL_57(91162131L), LEVEL_58(102265326L), LEVEL_59(
			114038008L),

	LEVEL_60(126509030L), LEVEL_61(146307211L), LEVEL_62(167243291L), LEVEL_63(
			189363788L), LEVEL_64(212716741L), LEVEL_65(237351413L), LEVEL_66(
			271973532L), LEVEL_67(308441375L), LEVEL_68(346825235L), LEVEL_69(
			387197529L),

	LEVEL_70(429632402L), LEVEL_71(474205751L), LEVEL_72(532692055L), LEVEL_73(
			606319094L), LEVEL_74(696376867L), LEVEL_75(804219972L), LEVEL_76(
			931275828L), LEVEL_77(1151275834L), LEVEL_78(1511275834L), LEVEL_79(
			2099275834L),

	LEVEL_80(4200000000L), LEVEL_81(6300000000L), LEVEL_82(8820000000L), LEVEL_83(
			11844000000L), LEVEL_84(15472800000L), LEVEL_85(19827360000L), LEVEL_86(
			25314105600L), LEVEL_87(32211728640L);
	/**
	 * The minimum experience required for this level
	 */
	public final long experience;
	/**
	 * The level represented by this experience value
	 */
	public final int level;

	/**
	 * @param experience
	 *            the minimum experience for the level
	 */
	ActorExperience(long experience) {
		this.experience = experience;
		this.level = this.ordinal();
	}

	/**
	 * Look for level of the {@link Actor} given it's <tt>experience</tt>
	 * 
	 * @param experience
	 *            the experience value
	 * @return the {@link ActorExperience}
	 */
	public static ActorExperience getLevel(long experience) {
		ActorExperience last = ActorExperience.LEVEL_0;
		for (ActorExperience exp : values()) {
			if (exp.experience < experience) {
				last = exp;
				continue;
			}
			if (exp.experience >= experience) {
				return exp;
			}
		}
		return last;
	}
}
