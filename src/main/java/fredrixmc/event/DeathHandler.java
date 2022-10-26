package fredrixmc.event;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
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
		AttributeInstance instance;
		
		if(data.getCurrentHearts() > 2) {
			data.subtractCurrentHearts(2);
			instance = event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
			instance.setBaseValue(data.getCurrentHearts());
		
		} else {
			if(data.getTotalRevives() < 1) {
				
			}
		}
		
		if(event.getEntity().getKiller() != null && event.getEntity().getKiller() instanceof Player) {
			PlayerData killerData = manager.getPlayerManager().getPlayerData(event.getEntity().getKiller());
			
			if(killerData.getCurrentHearts() < killerData.getMaxHearts()) {
				if(killerData.getMaxHearts() - killerData.getCurrentHearts() < 2)
					killerData.setCurrentHearts(killerData.getMaxHearts());
				else
					killerData.addCurrentHearts(2);
			}
		}
	}

}
