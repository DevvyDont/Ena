package sh.niall.ena.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static String getName(Player player) {
        String nameColour = StorageUtils.getString(player.getPersistentDataContainer(), StorageUtils.playerChatColour);
        return nameColour + player.getDisplayName() + ChatColor.WHITE;
    }

}
