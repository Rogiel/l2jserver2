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
package com.l2jserver.service.network.keygen;

import com.l2jserver.service.Service;

/**
 * This service generated cryptography keys used to encrypt and decrypt client
 * or server packets. Implementations can use an fixed set of keys or generate a
 * random one. Note that the generated key might not be cryptographically safe,
 * and this is implementation specific.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface BlowfishKeygenService extends Service {
	/**
	 * Creates a new 128 bits key. Note that the key is not necessarily random
	 * and can be a fixed key.
	 * 
	 * @return the 128 bits key
	 */
	byte[] generate();
}
