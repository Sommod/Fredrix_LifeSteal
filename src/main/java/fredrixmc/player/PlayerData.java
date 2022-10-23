package fredrixmc.player;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerData {
	
	private UUID id;
	private String name;
	private float craftedRevives;
	private float timedRevives;
	private long nextRevive;
	private boolean isDead;
	private float maxRevives;
	private float maxCraftedRevives;
	private float maxTimedRevives;
	private float maxHearts;
	private float currentHearts;
	
	public PlayerData(UUID id) {
		this(id, Bukkit.getServer().getOfflinePlayer(id).getName(), 0, 1, getNextMonthTime(), false, 1, 1, 1, 20, 10);
	}
	
	public PlayerData(UUID id, String name, float craftedRevives, float timedRevives, long nextTimedRevive, boolean isDead, int maxRevives, int maxCraftedRevives, int maxTimedRevives, int maxHearts, int currentHearts) {
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
	
	public PlayerData(File dataFile) {
		YamlConfiguration loader = YamlConfiguration.loadConfiguration(dataFile);
		
		id = UUID.fromString(dataFile.getName());
		name = loader.getString("name");
		craftedRevives = (float) loader.getDouble("lives.crafted");
		timedRevives = (float) loader.getDouble("lives.free");
		nextRevive = loader.getLong("lives.time");
		isDead = loader.getBoolean("lives.alive");
		maxCraftedRevives = (float) loader.getDouble("lives.allowed crafted");
		maxTimedRevives = (float) loader.getDouble("lives.allowed free");
		maxRevives = maxCraftedRevives + maxTimedRevives;
		maxHearts = (float) loader.getDouble("hearts.max");
		currentHearts = (float) loader.getDouble("hearts.current");
	}
	
	private static long getNextMonthTime() {
		Calendar result = Calendar.getInstance();
		result.add(Calendar.MONTH, 1);
		return result.getTimeInMillis();
	}
	
	public UUID getID() { return id; }
	public String getName() { return name; }
	public boolean isID(UUID id) { return id.equals(this.id); }
	public float getCraftedRevives() { return craftedRevives; }
	public float getFreeRevives() { return timedRevives; }
	public long getNextFreeReviveTime() { return nextRevive; }
	public boolean isDead() { return isDead; }
	public float getMaxRevives() { return maxRevives; }
	public float getMaxCraftedRevives() { return maxCraftedRevives; }
	public float getMaxFreeRevives() { return maxTimedRevives; }
	public float getMaxHearts() { return maxHearts; }
	public float getCurrentHearts() { return currentHearts; }
	
	public void setIsDead(boolean value) {
		isDead = value;
	}
	
	public void setCraftedRevives(int value) {
		craftedRevives = positive(ensureValue(value));
	}
	
	public void setFreeRevives(int value) {
		timedRevives = positive(ensureValue(value));
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
		maxRevives = alterValue(maxRevives, -value);
	}
	
	public void substractMaxCraftedRevives(float value) {
		maxCraftedRevives = alterValue(maxCraftedRevives, -value);
	}
	
	public void substractMaxFreeRevives(float value) {
		maxTimedRevives = alterValue(maxTimedRevives, -value);
	}
	
	public void setMaxHearts(float value) {
		maxHearts = positive(ensureValue(value));
	}
	
	public void setCurrentHearts(float value) {
		currentHearts = positive(ensureValue(value));
	}
	
	private float positive(float value) {
		return value > 0 ? value : -value;
	}
}
