
/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
*/
package script.template.skill;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.factory.SkillTemplateIDFactory;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.capability.Castable;
import com.l2jserver.model.world.capability.Caster;
import com.l2jserver.model.world.character.CharacterClass;

public class TestSkillTemplate extends SkillTemplate {
	public static final int ID = 10;

	@Inject
	public TestSkillTemplate(SkillTemplateIDFactory factory) {
		super(factory.createID(ID));
		this.maximumLevel = 1;
	}

	@Override
	public CharacterClass[] getClasses() {
		return new CharacterClass[] { CharacterClass.HUMAN_FIGHTER };
	}

	@Override
	public void cast(Caster caster, Castable... targets) {
		// TODO do casting
	}
}
