package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command can only be used by players!");
			return true;
		}
		if (label.equalsIgnoreCase("HotPotato")) {
			Player player = (Player) sender;
			checkHotPotatoArguments(player, args);
			return true;
		}
		return true;
	}

	private void checkHotPotatoArguments(Player player, String[] args) {
		if (args.length == 0) {
			HelpCommand.hotPotatoHelp(player);
			return;
		}
		String argument = args[0].toUpperCase();
		switch (argument) {
		case "CREATE":
			CreateCommand.checkCreateCommand(player, args);
			return;
		case "DELETE":
			DeleteCommand.checkDeleteCommand(player, args);
			return;
		case "DISABLE":
			DisableCommand.disableCommandChecker(player, args);
			return;
		case "ENABLE":
			EnableCommand.enableCommandChecker(player, args);
			return;
		case "ENTRANCE":
			EntranceCommand.checkEntranceCommand(player, args);
			return;
		case "ITEM":
			ItemCommand.checkItemCommand(player, args);
			return;
		case "JOIN":
			JoinCommand.joinArena(player, args);
			return;
		case "LEAVE":
			LeaveCommand.leaveArena(player);
			return;
		case "LOBBY":
			LobbyCommand.checkLobbyCommand(player, args);
			return;
		case "SET":
			SetLobby.checkSetCommand(player, args);
			return;
		case "SPAWN":
			SpawnCommand.checkSpawnCommand(player, args);
			return;
		case "TEST":
			return;
		default:
			HelpCommand.hotPotatoHelp(player);
			return;
		}
	}

}
