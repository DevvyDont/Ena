package sh.niall.ena.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;

public class PlayerUtils {

    public static String getName(Player player) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        String name = StorageUtils.getString(playerData, StorageUtils.playerNickname, player.getDisplayName());
        String nameColour = StorageUtils.getString(playerData, StorageUtils.playerChatColour);
        return nameColour + name + ChatColor.WHITE;
    }

    public static void send(Player player, String message) {
        player.sendMessage(ColourUtils.formatString(message));
    }

}
