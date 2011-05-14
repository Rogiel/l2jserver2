package com.l2jserver.tool.conversor.itemtemplate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.NONE)
public class ItemXml {
	@XmlAttribute(name = "id")
	private int id;
	@XmlAttribute(name = "name")
	private String name;
	@XmlAttribute(name = "type")
	private String type;

	@XmlElementRef
	private List<ItemValueXml> values;
	@XmlElement(name = "for")
	private List<ItemSetXml> sets;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the values
	 */
	public List<ItemValueXml> getValues() {
		return values;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(List<ItemValueXml> values) {
		this.values = values;
	}

	/**
	 * @return the sets
	 */
	public List<ItemSetXml> getSets() {
		return sets;
	}

	/**
	 * @param sets
	 *            the sets to set
	 */
	public void setSets(List<ItemSetXml> sets) {
		this.sets = sets;
	}
}
