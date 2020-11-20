package sh.niall.ena.stats.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import sh.niall.ena.utils.ColourUtils;
import sh.niall.ena.utils.HumanUtils;
import sh.niall.ena.utils.StorageUtils;
import sh.niall.ena.utils.TimeUtils;
import sh.niall.miya.services.MiyaCommand;

public class PlayerStatsCommand extends MiyaCommand {

    private final static String statsMessage = String.join(
            System.getProperty("line.separator"),
            "Stats for player %s%s&f:",
            "They are currently %s&f.",
            "They have played for %s.",
            "They have died %s times",
            "They have killed %s players"
    );

    public PlayerStatsCommand() {
        commandName = "stats";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO: Allow a username argument to search instead.
        Player player = (Player) sender;
        PersistentDataContainer playerData = player.getPersistentDataContainer();

        String nameColour = StorageUtils.getString(playerData, StorageUtils.playerChatColour);
        String name = player.getDisplayName();
        String status = player.isOnline() ? "&aonline" : "&4offline";
        String duration = HumanUtils.secondsToHumanReadable(TimeUtils.calculatePlayedDuration(playerData));
        int deathCount = StorageUtils.getInt(playerData, StorageUtils.playerDeathCount);
        int killCount = StorageUtils.getInt(playerData, StorageUtils.playerKillCount);

        String toSend = String.format(statsMessage, nameColour, name, status, duration, deathCount, killCount);
        sender.sendMessage(ColourUtils.formatString(toSend));
        return true;
    }


}
