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
	
	private Map<String, String> message;
	private Map<String, Location> location;
	
	private Manager manager;
	
	public Messages(Manager manager) {
		message = new HashMap<String, String>();
		location = new HashMap<String, Location>();
		this.manager = manager;
		
		initMessages();
	}
	
	private void initMessages() {
		message.clear();
		location.clear();
		String[] path = new String[] {"messages.basic", "messages.life", "messages.revive", "messages.admin"};
		
		YamlConfiguration messageConf = YamlConfiguration.loadConfiguration(manager.getFilesManager().getFile("config"));
		
		for(String s : path) {
			if(s.equals("messages.admin")) {
				for(String section : messageConf.getConfigurationSection(s).getValues(false).keySet()) {
					String fullPath = s + "." + section;
					
					if(section.equals("revives") || section.equals("hearts")) {
						for(String subSection : messageConf.getConfigurationSection(s + "." + section).getValues(false).keySet()) {
							String superPath = fullPath + "." + section + "." + subSection;
							
							message.put(superPath + ".message",messageConf.getString(superPath + ".message"));
							location.put(superPath + ".show location", Location.getLocation(messageConf.getString(superPath + ".show location")));
						}
						
						continue;
					}
					
					message.put(fullPath + ".message", messageConf.getString(fullPath + ".message"));
					location.put(fullPath + ".show location", Location.getLocation(messageConf.getString(fullPath + ".show location")));
				}
				
				continue;
			}
			
			for(String section : messageConf.getConfigurationSection(s).getValues(false).keySet()) {
				String fullPath = s + "." + section;
				
				if(fullPath.equals("messages.basic.kick")) {
					message.put(fullPath + ".message", messageConf.getString(fullPath + ".message"));
					location.put(fullPath + ".show location", Location.KICK);
					continue;
				}
				
				message.put(fullPath + ".message", messageConf.getString(fullPath + ".message"));
				location.put(fullPath + ".show location", Location.getLocation(messageConf.getString(fullPath + ".show location")));
			}
		}
		
	}
	
	public String getMessage(String path) {
		return message.get(path);
	}
	
	public Location getLocation(String path) {
		return location.get(path);
	}

}
