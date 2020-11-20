package sh.niall.ena.chat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import sh.niall.ena.utils.PlayerUtils;
import sh.niall.ena.utils.StorageUtils;
import sh.niall.miya.services.MiyaCommand;

import java.util.ArrayList;
import java.util.List;

public class NickNameCommand extends MiyaCommand implements TabCompleter {

    public NickNameCommand() {
        commandName = "nickname";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        if (args.length == 0) {
            PlayerUtils.send(player, String.format(
                    "%sPlease provide your new nickname or say %s/nickname clear %sto clear!",
                    ChatColor.RED, ChatColor.YELLOW, ChatColor.RED
            ));
            return true;
        }

        if (args[0].equalsIgnoreCase("clear")) {
            StorageUtils.deleteKey(player.getPersistentDataContainer(), StorageUtils.playerNickname);
            PlayerUtils.send(player, ChatColor.GREEN + "Removed your nickname!");
        } else {
            String newNick = String.join(" ", args);
            StorageUtils.setString(player.getPersistentDataContainer(), StorageUtils.playerNickname, newNick);
            PlayerUtils.send(player, "Changed your nickname to " + PlayerUtils.getName(player));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> output = new ArrayList<>();
        if ("clear".startsWith(args[0].toLowerCase()))
            output.add("clear");
        return output;
    }
}
