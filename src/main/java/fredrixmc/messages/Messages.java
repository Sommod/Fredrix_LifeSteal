package fredrixmc.messages;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.YamlConfiguration;

import fredrixmc.manager.Manager;

public class Messages {
	
	public enum Location {
		CHAT, ACTIONBAR, KICK, TITLE;
		
		public static Location getLocation(String value) {
			for(Location l : values()) {
				if(l.name().equals(value.toUpperCase()))
					return l;
			}
			
			return CHAT;
		}
	}
	
	private Map<String, MessageInstance> messages;
	private Manager manager;
	
	public Messages(Manager manager) {
		messages = new HashMap<String, MessageInstance>();
		this.manager = manager;
		
		initMessages();
	}
	
	private void initMessages() {
		messages.clear();
		String[] path = new String[] {"messages.basic", "messages.life", "messages.revive", "messages.admin"};
		
		YamlConfiguration messageConf = YamlConfiguration.loadConfiguration(manager.getFilesManager().getFile("messages"));
		
		for(String s : path) {
			if(s.equals("messages.admin")) {
				for(String section : messageConf.getConfigurationSection(s).getValues(false).keySet()) {
					String fullPath = s + "." + section;
					
					if(section.equals("revives") || section.equals("hearts")) {
						for(String subSection : messageConf.getConfigurationSection(s + "." + section).getValues(false).keySet()) {
							String superPath = fullPath + "." + section + "." + subSection;
							
							messages.put(superPath.substring(superPath.indexOf(".") + 1), createInstance(messageConf, superPath));
						}
						
						continue;
					}
					
					messages.put(fullPath.substring(fullPath.indexOf(".") + 1), createInstance(messageConf, fullPath));
				}
				
				continue;
			}
			
			for(String section : messageConf.getConfigurationSection(s).getValues(false).keySet()) {
				String fullPath = s + "." + section;
				
				if(fullPath.equals("messages.basic.kick")) {
					messages.put(fullPath.substring(fullPath.indexOf(".") + 1), new MessageObject(messageConf.getString(fullPath + ".message"), null));
					continue;
				}
				
				messages.put(fullPath.substring(fullPath.indexOf(".") + 1), createInstance(messageConf, fullPath));
			}
		}
		
	}
	
	private MessageInstance createInstance(YamlConfiguration conf, String path) {
		return new MessageObject(conf.getString(path + ".message"), Location.getLocation(conf.getString(path + ".show location")));
	}
	
	public MessageInstance getMessage(String path) {
		return messages.get(path);
	}
	
	public boolean exists(String path) {
		return messages.containsKey(path);
	}
	
	public void reloadMessage(String path) {
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(manager.getFilesManager().getFile("messages"));
		
		messages.put(path.substring(path.indexOf(".") + 1), createInstance(conf, path));
	}
	
	public void reload() {
		initMessages();
	}

}
