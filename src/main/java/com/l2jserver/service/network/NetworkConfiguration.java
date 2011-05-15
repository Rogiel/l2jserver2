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
package com.l2jserver.service.network;

import java.net.InetSocketAddress;

import com.l2jserver.service.configuration.Configuration;
import com.l2jserver.service.configuration.Configuration.ConfigurationName;

/**
 * The network {@link Configuration}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@ConfigurationName("network")
public interface NetworkConfiguration extends Configuration {
	// TODO set default value
	/**
	 * Get the server listen address
	 * 
	 * @return the listen address
	 */
	@ConfigurationPropertyGetter(name = "listen", defaultValue = "0.0.0.0:7777")
	InetSocketAddress getListenAddress();

	/**
	 * Set the server listen address
	 * 
	 * @param addr
	 *            the listen address
	 */
	@ConfigurationPropertySetter(name = "listen")
	void setListenAddress(InetSocketAddress addr);
}
