package script;

import com.l2jserver.model.world.capability.Scriptable;
import com.l2jserver.service.game.scripting.Script;

public class DummyScript implements Script<Scriptable> {
	@Override
	public void load(Scriptable object) {
	}

	@Override
	public void run() {

	}

	@Override
	public void unload() {
	}

	@Override
	public Scriptable getObject() {
		return null;
	}
}
