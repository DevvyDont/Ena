package sh.niall.ena.services;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.persistence.PersistentDataContainer;
import sh.niall.ena.Ena;
import sh.niall.ena.chat.ChatColour;
import sh.niall.ena.utils.ColourUtils;
import sh.niall.ena.utils.StorageUtils;

public class ChatFormatter implements Listener {

    private final Chat vaultChat;
    private final String msgFormat = "%1$s&f: %2$s";

    public ChatFormatter() {
        this.vaultChat = Ena.getPlugin().getServer().getServicesManager().load(Chat.class);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void OnPlayerLogin(PlayerLoginEvent event) {
        PersistentDataContainer playerData = event.getPlayer().getPersistentDataContainer();
        if (StorageUtils.getString(playerData, StorageUtils.playerChatColour) == null)
            StorageUtils.setString(playerData, StorageUtils.playerChatColour, ChatColour.WHITE.getColourCode());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnPlayerMessage(AsyncPlayerChatEvent event) {
        PersistentDataContainer playerData = event.getPlayer().getPersistentDataContainer();
        String prefix = vaultChat.getPlayerPrefix(event.getPlayer());
        String nameColour = StorageUtils.getString(playerData, StorageUtils.playerChatColour);
        event.setFormat(ColourUtils.formatString(prefix + nameColour + msgFormat));
    }
}
