package sh.niall.ena.chat.listeners;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.persistence.PersistentDataContainer;
import sh.niall.ena.chat.enums.ChatColour;
import sh.niall.ena.utils.ColourUtils;
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
        PersistentDataContainer playerData = event.getPlayer().getPersistentDataContainer();
        String prefix = vaultChat.getPlayerPrefix(event.getPlayer());
        String nameColour = StorageUtils.getString(playerData, StorageUtils.playerChatColour);
        event.setFormat(ColourUtils.formatString(prefix + nameColour + "%1$s&f: %2$s"));
    }
}
