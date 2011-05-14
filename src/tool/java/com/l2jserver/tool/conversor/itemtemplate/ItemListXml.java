package com.l2jserver.tool.conversor.itemtemplate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.NONE)
public class ItemListXml {
	@XmlElement(name = "item")
	private List<ItemXml> items;

	/**
	 * @return the items
	 */
	public List<ItemXml> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<ItemXml> items) {
		this.items = items;
	}
}
