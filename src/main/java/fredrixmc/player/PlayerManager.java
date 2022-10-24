package fredrixmc.player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import coldfyre.aacore.api.manager.FilesManager;
import coldfyre.aacore.api.manager.PluginManager;
import fredrixmc.LifeSteal;

public class PlayerManager {
	
	private Map<UUID, PlayerData> data;
	private PluginManager<LifeSteal> manager;
	
	public PlayerManager(PluginManager<LifeSteal> manager) {
		this.manager = manager;
		
		fillData();
	}
	
	private void fillData() {
		if(data != null)
			data.clear();
		
		data = new HashMap<UUID, PlayerData>();
		
		for(File f : manager.getFilesManager().getDirectory("Player Data").listFiles()) {
			PlayerData load = new PlayerData(f);
			
			data.put(load.getID(), load);
		}
	}
	
	public PlayerData getPlayerData(UUID id) {
		return data.get(id);
	}
	
	public PlayerData getPlayerData(OfflinePlayer player) {
		return data.get(player.getUniqueId());
	}
	
	public boolean exists(UUID id) {
		return data.containsKey(id);
	}
	
	public boolean exists(OfflinePlayer player) {
		return data.containsKey(player.getUniqueId());
	}
	
	public void createNewPlayerData(UUID id, boolean forceOverride) {
		createNewPlayerData(new PlayerData(id), forceOverride);
	}
	
	public void createNewPlayerData(PlayerData playerData, boolean forceOverride) {
		if((exists(playerData.getID()) && forceOverride) || !exists(playerData.getID()))
			data.put(playerData.getID(), playerData);
	}
	
	public boolean deletePlayerData(OfflinePlayer player) {
		return deletePlayerData(data.get(player.getUniqueId()));
	}
	
	public boolean deletePlayerData(UUID id) {
		return deletePlayerData(data.get(id));
	}
	
	public boolean deletePlayerData(PlayerData pData) {
		if(pData == null)
			return false;
		
		manager.getFilesManager().deleteFile(pData.getID().toString());
		return true;	
	}
	
	public void savePlayerData(PlayerData data) {
		File playerFile =  manager.getFilesManager().getFile(data.getID().toString() + ".yml");
		
		if(!playerFile.exists()) {
			manager.getFilesManager().changeDirectory(manager.getFilesManager().getDirectory("Player Data"));
			manager.getFilesManager().createFile(data.getID().toString(), data.getID().toString() + ".yml");
			savePlayerData(data);
			return;
		}
		
		YamlConfiguration save = YamlConfiguration.loadConfiguration(playerFile);
		save.set("name", data.getName());
		save.set("lives.crafted", data.getCraftedRevives());
		save.set("lives.free", data.getFreeRevives());
		save.set("lives.allowed crafted", data.getMaxCraftedRevives());
		save.set("lives.allowed free", data.getMaxFreeRevives());
		save.set("lives.time", data.getNextFreeReviveTime());
		save.set("lives.alive", data.isDead());
		save.set("hearts.max", data.getMaxHearts());
		save.set("hearts.current", data.getCurrentHearts());
		
		boolean success = save(save, playerFile);
		
		if(!success)
			manager.getPlugin().getServer().getConsoleSender().sendMessage("§cLifeSteal: **ERROR, could not save data for " + data.getName() + ". Shutting down server will result in loss of data.");
	}
	
	public void saveAll() {
		for(PlayerData pData : data.values())
			savePlayerData(pData);
	}
	
	public void reload(boolean saveFirst) {
		if(saveFirst)
			saveAll();
		
		fillData();
	}
	
	public void reloadPlayer(UUID id, boolean saveFirst) {
		if(saveFirst)
			savePlayerData(data.get(id));
		
		data.put(id, new PlayerData(manager.getFilesManager().getFile(id.toString() + ".yml")));
	}
	
	public void reloadPlayer(OfflinePlayer player, boolean saveFirst) {
		reloadPlayer(player.getUniqueId(), saveFirst);
	}
	
	private boolean save(YamlConfiguration conf, File file) {
		try {
			conf.save(file);
			return true;
		} catch (IOException e) {
			FilesManager.logException(e, new File(manager.getFilesManager().getDirectory("Error Logs"), FilesManager.getTime()));
			return false;
		}
	}
	
}
