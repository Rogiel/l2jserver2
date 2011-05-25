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
package com.l2jserver.model.template;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.TeleportationTemplateID;
import com.l2jserver.util.dimensional.Coordinate;
import com.l2jserver.util.jaxb.CoordinateAdapter;
import com.l2jserver.util.jaxb.ItemTemplateIDAdapter;
import com.l2jserver.util.jaxb.TeleportationTemplateIDAdapter;

/**
 * Template for effects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlRootElement(name = "teleport")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "teleports")
public class TeleportationTemplate extends AbstractTemplate<Coordinate> {
	@XmlAttribute(name = "id")
	@XmlJavaTypeAdapter(TeleportationTemplateIDAdapter.class)
	protected TeleportationTemplateID id;
	@XmlAttribute(name = "name")
	protected String name;

	@XmlAttribute(name = "item")
	@XmlJavaTypeAdapter(ItemTemplateIDAdapter.class)
	protected ItemTemplateID itemTemplateID;
	@XmlAttribute(name = "price")
	protected int price;

	@XmlElement(name = "point")
	@XmlJavaTypeAdapter(CoordinateAdapter.class)
	protected Coordinate coordinate;

	protected List<TeleportRestriction> restrictions;

	public enum TeleportRestriction {
		NOBLE;
	}

	@Override
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
