package fredrixmc.messages;

import fredrixmc.messages.Messages.Location;

public interface MessageInstance {
	
	public String getRawMessage();
	public Location getLocation();
	public String getMessage(String... values);

}
