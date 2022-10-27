package fredrixmc.event;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import fredrixmc.manager.Manager;

public class Events implements Listener {
	
	private Manager manager;
	
	private JoinHandler join;
	private DeathHandler death;
	
	public Events(Manager manager) {
		this.manager = manager;
		
		
		join = new JoinHandler();
		death = new DeathHandler();
	}
	
	@EventHandler
	public void delegate(Event what) {
		if(what instanceof AsyncPlayerPreLoginEvent)
			join.joinAction(manager, (AsyncPlayerPreLoginEvent) what);
		else if(what instanceof PlayerDeathEvent)
			death.onDeath(manager, (PlayerDeathEvent) what);
		//TODO: InventoryClickEvent
	}
}
