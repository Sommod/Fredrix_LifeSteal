package fredrixmc;

import org.bukkit.plugin.java.JavaPlugin;

import coldfyre.aacore.api.manager.FilesManager;
import coldfyre.aacore.api.manager.PluginManager;

public class LifeSteal extends JavaPlugin {
	
	private PluginManager<LifeSteal> manager;
	
	@Override
	public void onEnable() {
		manager = new PluginManager<LifeSteal>(this, new FilesManager(getDataFolder()));
	}

}
