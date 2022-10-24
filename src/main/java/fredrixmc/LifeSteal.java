package fredrixmc;

import org.bukkit.plugin.java.JavaPlugin;

import coldfyre.aacore.api.manager.FilesManager;
import fredrixmc.manager.Manager;

public class LifeSteal extends JavaPlugin {
	
	@Override
	public void onEnable() {
		new Manager(this, new FilesManager(getDataFolder()));
	}

}
