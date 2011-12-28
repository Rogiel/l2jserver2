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
package com.l2jserver.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the service type
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "services")
public class ServiceDescriptor<T extends Service> {
	/**
	 * The service interface class
	 */
	@XmlAttribute(name = "interface")
	private final Class<T> serviceInterface;
	/**
	 * The service implementation class
	 */
	@XmlAttribute(name = "implementation")
	private final Class<? extends T> serviceImplementation;
	/**
	 * The node (will be used later for configuration purposes)
	 */
	private final Node node;

	/**
	 * @param serviceInterface
	 *            the service interface
	 * @param serviceImplementation
	 *            the service implementation
	 * @param node
	 *            the XML node
	 */
	public ServiceDescriptor(Class<T> serviceInterface,
			Class<? extends T> serviceImplementation, Node node) {
		this.serviceInterface = serviceInterface;
		this.serviceImplementation = serviceImplementation;
		this.node = node;
	}

	/**
	 * @return the service interface class
	 */
	public Class<T> getServiceInterface() {
		return serviceInterface;
	}

	/**
	 * @return the service implementation class
	 */
	public Class<? extends T> getServiceImplementation() {
		return serviceImplementation;
	}

	/**
	 * @return the xml node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * @param node
	 *            the XML node
	 * @return a new {@link ServiceDescriptor} instance for the given service
	 *         XML node
	 * @throws ClassNotFoundException
	 *             if any of the services class could not be found
	 * @throws DOMException
	 *             if any error occur while parsing the XML data
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ServiceDescriptor fromNode(Node node)
			throws ClassNotFoundException, DOMException {
		final NamedNodeMap attrs = node.getAttributes();
		final Class<? extends Service> serviceInterface = (Class<? extends Service>) Class
				.forName(attrs.getNamedItem("interface").getNodeValue());
		final Class<? extends Service> serviceImplementation = (Class<? extends Service>) Class
				.forName(attrs.getNamedItem("implementation").getNodeValue());
		return new ServiceDescriptor(serviceInterface, serviceImplementation,
				node);
	}
}
