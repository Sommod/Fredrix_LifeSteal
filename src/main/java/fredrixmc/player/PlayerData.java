package fredrixmc.player;

import java.util.UUID;

public class PlayerData {
	
	private UUID id;
	private float craftedRevives;
	private float timedRevives;
	private long nextRevive;
	private boolean isDead;
	private float maxRevives;
	private float maxCraftedRevives;
	private float maxTimedRevives;
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
	
	public UUID getID() { return id; }
	public boolean isID(UUID id) { return id.equals(this.id); }
	public float getCraftedRevives() { return craftedRevives; }
	public float getFreeRevives() { return timedRevives; }
	public long getNextFreeReviveTime() { return nextRevive; }
	public boolean isDead() { return isDead; }
	public float getMaxRevives() { return maxRevives; }
	public float getMaxCraftedRevives() { return maxCraftedRevives; }
	public float getMaxFreeRevives() { return maxTimedRevives; }
	public int getMaxHearts() { return maxHearts; }
	public int getCurrentHearts() { return currentHearts; }
	
	public void setIsDead(boolean value) {
		isDead = value;
	}
	
	public void setCraftedRevives(int value) {
		craftedRevives = value;
	}
	
	public void setFreeRevives(int value) {
		timedRevives = value;
	}
	
	public void addCraftedRevives(int value) {
		craftedRevives = alterValue(craftedRevives, value);
	}
	
	public void addFreeRevives(int value) {
		timedRevives = alterValue(timedRevives, value);
	}
	
	public void substracCraftedRevives(int value) {
		craftedRevives = alterValue(craftedRevives, -(value));
	}
	
	public void substracFreeRevives(int value) {
		timedRevives = alterValue(timedRevives, -(value));
	}
	
	private float alterValue(float stored, float value) {
		ensureValue(value);
		return stored = stored + value > 0 ? 0 : stored + value;
	}
	
	private float ensureValue(float value) {
		if(value % .5 == 0)
			return value;
		
		float tmp = value % .5F;
		boolean tmpBoolean = tmp >= .25F;
		
		if((value % 1F > .5F && tmpBoolean) || (value % 1F < .5F && !tmpBoolean))
			return Math.round(value);
		else if(value % 1F > .5F && !tmpBoolean)
			return Math.round(value) - .5F;
		else
			return Math.round(value) + .5F;
		
	}
	
	public void setMaxRevives(float value) {
		maxRevives = alterValue(maxRevives, value);
	}
	
	public void setMaxCraftedRevives(float value) {
		maxCraftedRevives = value < 0 ? 0 : value;
	}
	
	public void setMaxFreeRevives(float value) {
		maxTimedRevives = value < 0 ? 0 : value;
	}
	
	public void addMaxRevives(float value) {
		maxRevives = alterValue(maxCraftedRevives, value);
	}
	
	public void addMaxCraftedRevives(float value) {
		maxCraftedRevives = alterValue(maxCraftedRevives, value);
	}
	
	public void addMaxFreeRevives(float value) {
		maxTimedRevives = alterValue(maxTimedRevives, value);
	}
	
	public void substractMaxRevives(float value) {
		maxRevives = alterValue(maxRevives, value);
	}

}
