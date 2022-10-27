package fredrixmc.event;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import fredrixmc.manager.Manager;
import fredrixmc.player.PlayerData;

public class JoinHandler {
	
	protected JoinHandler() { }
	
	protected void joinAction(Manager manager, AsyncPlayerPreLoginEvent event) {
		OfflinePlayer target = manager.getPlugin().getServer().getOfflinePlayer(event.getUniqueId());
		PlayerData targetData = manager.getPlayerManager().getPlayerData(target);
		
		if(targetData.isDead())
			event.disallow(Result.KICK_OTHER, manager.getMessagesManager().getMessage("basic.kick").getMessage());
	}

}
