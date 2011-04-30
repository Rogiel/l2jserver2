package script.dao.mysql5;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.db.dao.ItemDAO;

public class DAOModuleMySQL5 extends AbstractModule {
	@Override
	protected void configure() {
		bind(CharacterDAO.class).to(MySQL5CharacterDAO.class).in(
				Scopes.SINGLETON);
		bind(ItemDAO.class).to(MySQL5ItemDAO.class).in(Scopes.SINGLETON);
	}
}
