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
package com.l2jserver.service;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.cache.EhCacheService;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.configuration.ProxyConfigurationService;
import com.l2jserver.service.core.Log4JLoggingService;
import com.l2jserver.service.core.LoggingService;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.MySQLDatabaseService;
import com.l2jserver.service.game.CharacterService;
import com.l2jserver.service.game.CharacterServiceImpl;
import com.l2jserver.service.game.SpawnService;
import com.l2jserver.service.game.SpawnServiceImpl;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.chat.SimpleChatService;
import com.l2jserver.service.game.pathing.MapperPathingService;
import com.l2jserver.service.game.pathing.PathingService;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.scripting.ScriptingServiceImpl;
import com.l2jserver.service.game.template.ScriptTemplateService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.service.game.world.CachedWorldIDService;
import com.l2jserver.service.game.world.WorldIDService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.WorldServiceImpl;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.game.world.event.WorldEventDispatcherImpl;
import com.l2jserver.service.network.NettyNetworkService;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.service.network.keygen.BlowfishKeygenService;
import com.l2jserver.service.network.keygen.SecureBlowfishKeygenService;

/**
 * Google Guice {@link Module} for services
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ServiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ServiceManager.class).in(Scopes.SINGLETON);
		bind(LoggingService.class).to(Log4JLoggingService.class).in(
				Scopes.SINGLETON);
		bind(ConfigurationService.class).to(ProxyConfigurationService.class)
				.in(Scopes.SINGLETON);
		bind(CacheService.class).to(EhCacheService.class).in(Scopes.SINGLETON);
		bind(DatabaseService.class).to(MySQLDatabaseService.class).in(
				Scopes.SINGLETON);
		bind(WorldIDService.class).to(CachedWorldIDService.class).in(
				Scopes.SINGLETON);

		bind(PathingService.class).to(MapperPathingService.class).in(
				Scopes.SINGLETON);

		bind(BlowfishKeygenService.class).to(SecureBlowfishKeygenService.class)
				.in(Scopes.SINGLETON);
		bind(NetworkService.class).to(NettyNetworkService.class).in(
				Scopes.SINGLETON);
		bind(ScriptingService.class).to(ScriptingServiceImpl.class).in(
				Scopes.SINGLETON);
		bind(TemplateService.class).to(ScriptTemplateService.class).in(
				Scopes.SINGLETON);

		bind(ChatService.class).to(SimpleChatService.class)
				.in(Scopes.SINGLETON);
		bind(SpawnService.class).to(SpawnServiceImpl.class)
				.in(Scopes.SINGLETON);
		bind(CharacterService.class).to(CharacterServiceImpl.class).in(
				Scopes.SINGLETON);

		bind(WorldService.class).to(WorldServiceImpl.class)
				.in(Scopes.SINGLETON);
		bind(WorldEventDispatcher.class).to(WorldEventDispatcherImpl.class).in(
				Scopes.SINGLETON);
	}
}
