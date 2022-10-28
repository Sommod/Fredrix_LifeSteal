package fredrixmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fredrixmc.manager.Manager;

public class CommandHandler implements CommandExecutor {
	
	private Manager manager;
	
	public CommandHandler(Manager manager) {
		this.manager = manager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("lifesteal")) {
			switch (args.length) {
			case 0:
				//TODO: run GUI (if enabled).
				//TODO: If not, then run 'Version' Command.
				return true;

			default:
				return false;
			}
		}
		
		return false;
	}

}
