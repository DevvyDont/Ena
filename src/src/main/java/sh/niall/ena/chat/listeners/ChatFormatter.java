package sh.niall.ena.chat.listeners;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.persistence.PersistentDataContainer;
import sh.niall.ena.chat.enums.ChatColour;
import sh.niall.ena.utils.ColourUtils;
import sh.niall.ena.utils.PlayerUtils;
import sh.niall.ena.utils.StorageUtils;
import sh.niall.miya.services.MiyaListener;

public class ChatFormatter extends MiyaListener {

    private Chat vaultChat;

    @Override
    public void onRegister() {
        this.vaultChat = getServer().getServicesManager().load(Chat.class);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void OnPlayerLogin(PlayerLoginEvent event) {
        PersistentDataContainer playerData = event.getPlayer().getPersistentDataContainer();
        if (StorageUtils.getString(playerData, StorageUtils.playerChatColour) == null)
            StorageUtils.setString(playerData, StorageUtils.playerChatColour, ChatColour.WHITE.getColourCode());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnPlayerMessage(AsyncPlayerChatEvent event) {
        String prefix = vaultChat.getPlayerPrefix(event.getPlayer());
        event.setFormat(ColourUtils.formatString(prefix + PlayerUtils.getName(event.getPlayer()) + ": %2$s"));
        event.setMessage(ColourUtils.formatString(event.getMessage()));
    }
}
