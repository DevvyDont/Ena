package sh.niall.ena.stats.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import sh.niall.ena.utils.*;
import sh.niall.miya.services.MiyaCommand;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerStatsCommand extends MiyaCommand implements TabCompleter {

    private final static String statsMessage = String.join(
            "\n",
            "Stats for player %s:",
            "They have played for %s.",
            "They have died %s times",
            "They have killed %s players"
    );

    public PlayerStatsCommand() {
        commandName = "stats";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = args.length > 0 ? Bukkit.getPlayer(args[0]) : (Player) sender;
        if (player == null) {
            sender.sendMessage("I don't know a user with the name " + ChatColor.RED + args[0]);
            return true;
        }

        PersistentDataContainer playerData = player.getPersistentDataContainer();
        String name = PlayerUtils.getName(player);
        String duration = HumanUtils.secondsToHumanReadable(TimeUtils.calculatePlayedDuration(playerData));
        int deathCount = StorageUtils.getInt(playerData, StorageUtils.playerDeathCount);
        int killCount = StorageUtils.getInt(playerData, StorageUtils.playerKillCount);

        String toSend = String.format(statsMessage, name, duration, deathCount, killCount);
        sender.sendMessage(ColourUtils.formatString(toSend));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String argLower = args[0].toLowerCase();

        return Bukkit.getOnlinePlayers().stream().filter(p -> p.getName().toLowerCase().startsWith(argLower))
                .limit(5).map(Player::getDisplayName).collect(Collectors.toList());
    }
}
