package fredrixmc.messages;

import coldfyre.aacore.api.Utilities;

public enum HelpInfo {
	HELP("Help", "/LifeSteal Help [Command]", "§6/LifeSteal §b§nHelp§r §6[Command]", "§c§lHelp §7[§ch§7]§f:", "§fDisplays a list of all commands as well as displaying detailed information about the command including shortcuts that can be used for the command."),
	VERSION("Version", "/LifeSteal Version", "§6/LifeSteal §b§nVersion", "§c§lVersion §7[§cv§7]§f:", "§fDisplays information about the plugin."),
	CRAFT("Craft", "/LifeSteal Craft [Amount]", "§6/LifeSteal §b§nCraft §r§6[Amount]", "§c§lCraft §7[§cc§7]§f:", "§fCrafts the revives that can be used to self-revive or be used to revive another player."),
	GIVE("Give", "/LifeSteal Give <player> [Amount]", "§6/LifeSteal §b§nGive §r§6<Player> [Amount]", "§c§lGive §7[§cg§7]§f:", "§fGives either §c1§f revive to the specified person. Optionally, many revives can be given to the specified person."),
	CONSUME("Consume", "/LifeSteal Consume [Amount]", "§6/LifeSteal §b§nConsume §r§6[Amount]", "§c§lConsume §7[§cco§7]§f:", "§fThis will give a certain amount of hearts, but at the cost of using a revive."),
	LIVES("Lives", "/LifeSteal Lives [Player]", "§6/LifeSteal §b§nLives §r§6[Player]", "§c§lLives §7[§cl§7]§f:", "§fShows the number of revives you currently possess. Provide an optional input of a player name to see how many revives they currently possess."),
	
	// Admin Commands
	ADMIN("Admin", "/LifeSteal Admin", "§6/LifeSteal §b§nAdmin", "§c§lAdmin §7[§ca§7]§f:", "§fBase command for all Administrator commands. These range from altering plugin data to player data."),
	ADMIN_HELP("Admin Help", "/LifeSteal Admin Help [Command]", "§6/LifeSteal Admin §b§nHelp §r§6[Command]", "§c§lHelp §7[§ch§7]§f:", "§fDisplays a list of all Administrator commands; Optionally, can display shortcuts and command description.");
	//TODO: Create help messages for all Admin Commands
	
	private String top, command, formattedCommand, message, shortcuts;
	
	private HelpInfo(String top, String command, String formattedCommand, String shortcuts, String message) {
		this.top = top;
		this.command = command;
		this.message = message;
		this.shortcuts = shortcuts;
		this.formattedCommand = formattedCommand;
	}
	
	public String[] getFull() {
		String[] result = new String[2];
		
		result[0] = top;
		result[1] = command;
		
		for(String s : Utilities.wordWarpAsArray(message, 13))
			result = Utilities.addToArray(result, s);
		
		return Utilities.addToArray(result, getFooterDisplay());
	}
	
	public String getHeader() {
		return top;
	}
	
	public String getHeaderDisplay() {
		return "§c===§f Help: %t% §c====".replaceAll("%t%", top);
	}
	
	public String getCommandDisplay() {
		return formattedCommand;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getCommand() {
		return command;
	}
	
	public String getShortcuts() {
		return shortcuts;
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
