package me.CloverCola.HotPotato.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CloverCola.HotPotato.StatusCheck;

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
			HelpCommand help = new HelpCommand();
			help.hotPotatoHelp(player);
			return;
		}
		String argument = args[0].toUpperCase();
		switch (argument) {
		case "CREATE":
			CreateCommand.checkCreateCommand(player, args);
			return;
		case "DELETE":
			DeleteCommand delete = new DeleteCommand();
			delete.checkDeleteCommand(player, args);
			return;
		case "DISABLE":
			DisableCommand disable = new DisableCommand();
			disable.disableCommandChecker(player, args);
			return;
		case "ENABLE":
			EnableCommand enable = new EnableCommand();
			enable.enableCommandChecker(player, args);
			return;
		case "ENTRANCE":
			EntranceCommand entrance = new EntranceCommand();
			entrance.checkEntranceCommand(player, args);
			return;
		case "ITEM":
			// TODO If items are implemented, finish and implement this class.
			ItemCommand item = new ItemCommand();
			item.checkItemCommand(player, args);
			return;
		case "JOIN":
			JoinCommand join = new JoinCommand();
			join.joinArena(player, args);
			return;
		case "LEAVE":
			LeaveCommand leave = new LeaveCommand();
			leave.leaveArena(player);
			return;
		case "LOBBY":
			LobbyCommand lobby = new LobbyCommand();
			lobby.checkLobbyCommand(player, args);
			return;
		case "SPAWN":
			SpawnCommand spawn = new SpawnCommand();
			spawn.checkSpawnCommand(player, args);
			return;
		case "TEST":
			StatusCheck check = new StatusCheck();
			if (check.isInArena(player) == false) {
				player.sendMessage(ChatColor.RED + "You must be in an arena to use this command!");
			}
			check.setTaggedPlayer(player);
			return;
		default:
			HelpCommand help = new HelpCommand();
			help.hotPotatoHelp(player);
			return;
		}
	}

}
