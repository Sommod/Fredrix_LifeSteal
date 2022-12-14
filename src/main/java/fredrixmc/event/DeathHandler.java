package fredrixmc.event;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fredrixmc.manager.Manager;
import fredrixmc.player.PlayerData;

public class DeathHandler implements Listener {
	
	protected DeathHandler() { }
	
	protected void onDeath(Manager manager, PlayerDeathEvent event) {
		PlayerData data = manager.getPlayerManager().getPlayerData(event.getEntity());
		AttributeInstance instance;
		
		if(data.getCurrentHearts() > 2) {
			data.subtractCurrentHearts(2);
			instance = event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
			instance.setBaseValue(data.getCurrentHearts());
		
		} else {
			YamlConfiguration tmp = YamlConfiguration.loadConfiguration(manager.getFilesManager().getFile("config"));
			
			if(data.getTotalRevives() < 1) {
				data.setIsDead(true);
				event.getEntity().kickPlayer(manager.getMessagesManager().getMessage("basic.kick").getMessage());
				
				if(tmp.getBoolean("revive.remove all on kick")) {
					data.setCraftedRevives(0);
					data.setFreeRevives(0);
				}
				
			} else {			
				data.subtractRevives(1, tmp.getBoolean("revive.free.use first"));
				data.setCurrentHearts((float) tmp.getDouble("default.hearts"));
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
