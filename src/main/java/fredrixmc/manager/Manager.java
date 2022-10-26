package fredrixmc.manager;

import coldfyre.aacore.api.manager.FilesManager;
import coldfyre.aacore.api.manager.PluginManager;
import fredrixmc.LifeSteal;
import fredrixmc.player.PlayerManager;

public class Manager extends PluginManager<LifeSteal> {

	private PlayerManager playerManager;
	private Messages messages;
	
	public Manager(LifeSteal plugin, FilesManager filesManager) {
		super(plugin, filesManager);
	}
	
	@Override
	public void onStartup() {
		playerManager = new PlayerManager(this);
		messages = new Messages(this);
	}
	
	public PlayerManager getPlayerManager() {
		return playerManager;
	}
	
	public Messages getMessagesManager() {
		return messages;
	}

}
