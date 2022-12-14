package fredrixmc.messages;

import javax.annotation.Nullable;

import fredrixmc.manager.Messages.Location;

public class MessageObject implements MessageInstance {
	
	private String message;
	
	@Nullable
	private Location loc;
	
	public MessageObject(String message, Location loc) {
		this.message = message;
		this.loc = loc;
	}

	@Override
	public String getRawMessage() {
		return message;
	}

	@Override
	public Location getLocation() {
		return loc;
	}

	@Override
	public String getMessage(String... values) {
		return alter(values);
	}
	
	private String alter(String... value) {
		if(value.length == 0 || value.length % 2 != 0)
			return new String(message);
		
		String result = new String(message);
		
		for(int i = 0, j = 1; i < value.length; i += 2, j += 2)
			result = result.replaceAll(value[i], value[j]);
		
		return result;
	}

}
