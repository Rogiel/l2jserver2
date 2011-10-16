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
package com.l2jserver.util.jaxb;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.l2jserver.util.jaxb.MapAdapter.MapElements;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class MapAdapter extends XmlAdapter<MapElements[], Map<String, String>> {
	@Override
	public MapElements[] marshal(Map<String, String> map) throws Exception {
		return map.values().toArray(new MapElements[map.size()]);
	}

	@Override
	public Map<String, String> unmarshal(MapElements[] elems) throws Exception {
		Map<String, String> r = new HashMap<String, String>();
		for (MapElements mapelement : elems)
			r.put(mapelement.key, mapelement.value);
		return r;

	}

	/**
	 * Internal usage class: reads map data from XML file
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public static class MapElements {
		/**
		 * The key attribute
		 */
		@XmlAttribute(name = "id")
		public String key;
		/**
		 * The tag value
		 */
		@XmlValue
		public String value;

		/**
		 * Creates an empty instance. Invoked from JAXB.
		 */
		protected MapElements() {
		}

		/**
		 * @param key
		 *            the key attribute
		 * @param value
		 *            the tag value
		 */
		public MapElements(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}
}