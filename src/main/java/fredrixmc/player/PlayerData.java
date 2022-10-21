package fredrixmc.player;

import java.util.UUID;

public class PlayerData {
	
	private UUID id;
	private float craftedRevives;
	private float timedRevives;
	private long nextRevive;
	private boolean isDead;
	private int maxRevives;
	private int maxCraftedRevives;
	private int maxTimedRevives;
	private int maxHearts;
	private int currentHearts;
	
	public PlayerData(UUID id) {
		
	}
	
	public PlayerData(UUID id, float craftedRevives, float timedRevives, long nextTimedRevive, boolean isDead, int maxRevives, int maxCraftedRevives, int maxTimedRevives, int maxHearts, int currentHearts) {
		this.id = id;
		this.craftedRevives = craftedRevives;
		this.timedRevives = timedRevives;
		this.nextRevive = nextTimedRevive;
		this.isDead = isDead;
		this.maxRevives = maxRevives;
		this.maxCraftedRevives = maxCraftedRevives;
		this.maxTimedRevives = maxTimedRevives;
		this.maxHearts = maxHearts;
		this.currentHearts = currentHearts;
	}

}
