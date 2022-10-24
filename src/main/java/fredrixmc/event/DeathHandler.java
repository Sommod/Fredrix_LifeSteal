package fredrixmc.event;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fredrixmc.manager.Manager;
import fredrixmc.player.PlayerData;

public class DeathHandler implements Listener {
	
	private Manager manager;
	
	public DeathHandler(Manager manager) {
		this.manager = manager;
		manager.getPlugin().getServer().getPluginManager().registerEvents(this, manager.getPlugin());
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		PlayerData data = manager.getPlayerManager().getPlayerData(event.getEntity());
		//TODO: Check health to see if value is 1 heart (2 value).
		//TODO: If true, then set isDead to TRUE.
		//TODO: Kick/Ban player from server.
		
		data.substractCurrentHearts(2F);
		
		AttributeInstance ai = event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
		ai.setBaseValue((double) data.getCurrentHearts());
		
		if(event.getEntity().getKiller() != null) {
			PlayerData killer = manager.getPlayerManager().getPlayerData(event.getEntity().getKiller());
			//TODO: Check health to see if value is at max.
			//TODO: If true, then don't add hearts
			
			killer.addCurrentHearts(2F);
			
			ai = event.getEntity().getKiller().getAttribute(Attribute.GENERIC_MAX_HEALTH);
			ai.setBaseValue((double) killer.getCurrentHearts());
		}
	}

}
