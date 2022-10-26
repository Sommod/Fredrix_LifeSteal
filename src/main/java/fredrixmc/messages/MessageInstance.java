package fredrixmc.messages;

import fredrixmc.manager.Messages.Location;

public interface MessageInstance {
	
	public String getRawMessage();
	public Location getLocation();
	public String getMessage(String... values);

}
