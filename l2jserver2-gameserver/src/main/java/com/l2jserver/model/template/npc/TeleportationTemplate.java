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
package com.l2jserver.model.template.npc;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.TeleportationTemplateID;
import com.l2jserver.model.template.AbstractTemplate;
import com.l2jserver.util.geometry.Coordinate;
import com.l2jserver.util.jaxb.CoordinateAdapter;
import com.l2jserver.util.jaxb.ItemTemplateIDAdapter;
import com.l2jserver.util.jaxb.TeleportationTemplateIDAdapter;

/**
 * Template for effects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlRootElement(name = "teleport", namespace = "http://schemas.l2jserver2.com/teleport")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://schemas.l2jserver2.com/teleport", name = "TeleportType")
public class TeleportationTemplate extends AbstractTemplate {
	@XmlAttribute(name = "id")
	@XmlJavaTypeAdapter(TeleportationTemplateIDAdapter.class)
	protected TeleportationTemplateID id;
	@XmlAttribute(name = "name")
	protected String name;

	@XmlAttribute(name = "item", required = false)
	@XmlJavaTypeAdapter(ItemTemplateIDAdapter.class)
	protected ItemTemplateID itemTemplateID;
	@XmlAttribute(name = "price", required = true)
	protected int price;

	@XmlElement(name = "point", required = false)
	@XmlJavaTypeAdapter(CoordinateAdapter.class)
	protected Coordinate coordinate;

	@XmlElementWrapper(name = "restrictions", required = false)
	@XmlElement(name = "restriction", required = true)
	protected List<TeleportRestriction> restrictions;

	@XmlType(name = "TeleportRestrictionType")
	public enum TeleportRestriction {
		NOBLE;
	}

	/**
	 * Create a new {@link Coordinate}
	 * 
	 * @return the created object
	 */
	public Coordinate create() {
		return coordinate;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public TeleportationTemplateID getId() {
		return id;
	}

	/**
	 * @return the itemTemplateID
	 */
	public ItemTemplateID getItemTemplateID() {
		return itemTemplateID;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	@Override
	public TeleportationTemplateID getID() {
		return id;
	}
}
