package fredrixmc.messages;

import coldfyre.aacore.api.Utilities;

public enum HelpInfo {
	HELP("Help", "§c§lHelp §7[§ch§7]§f: ", "§fDisplays a list of all commands as well as displaying detailed information about the command including shortcuts that can be used for the command."),
	VERSION("Version", "§c§lHelp §7[§ch§7]", "§fDisplays information about the plugin.");
	
	private String top, command, message;
	
	private HelpInfo(String top, String command, String message) {
		this.top = top;
		this.command = command;
		this.message = message;
	}
	
	public String[] getFull() {
		String[] result = new String[2];
		
		result[0] = top;
		result[1] = command;
		
		for(String s : Utilities.wordWarpAsArray(message, 13))
			result = Utilities.addToArray(result, s);
		
		return Utilities.addToArray(result, getFooterDisplay());
	}
	
	public String getHeaderDisplay() {
		return top;
	}
	
	public String getCommandDisplay() {
		return command;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getFooterDisplay() {
		String result = "§c";
		
		for(int i = 0; i < top.length() - 4; i++)
			result += "=";
		
		return result;
	}
	
	@Override
	public String toString() {
		String complete = "";
		
		for(String line : getFull())
			complete += line;
		
		return complete;
	}
}
