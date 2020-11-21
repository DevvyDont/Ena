package sh.niall.ena.stats.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.persistence.PersistentDataContainer;
import sh.niall.ena.Ena;
import sh.niall.ena.utils.StorageUtils;
import sh.niall.ena.utils.TimeUtils;
import sh.niall.miya.services.MiyaListener;

import java.time.Instant;

public class PlayedDurationTracking extends MiyaListener {

    @EventHandler
    public void OnPlayerLogin(PlayerJoinEvent event) {
        PersistentDataContainer playerData = event.getPlayer().getPersistentDataContainer();
        StorageUtils.setLong(playerData, StorageUtils.playerLoginTs, Instant.now().getEpochSecond());
    }

    @EventHandler
    public void OnPlayerLogout(PlayerQuitEvent event) {
        savePlayer(event.getPlayer());
    }

    /**
     * If our plugin gets disabled for any reason we should save online players
     * @param event
     */
    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {
        if (event.getPlugin() == Ena.getPlugin())
            for (Player player : Bukkit.getOnlinePlayers())
                savePlayer(player);
    }

    private void savePlayer(Player player) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        StorageUtils.setLong(playerData, StorageUtils.playerLogoutTs, Instant.now().getEpochSecond());
        StorageUtils.setLong(playerData, StorageUtils.playerTimeSpent, TimeUtils.calculatePlayedDuration(playerData));
        StorageUtils.setLong(playerData, StorageUtils.playerLoginTs, 0);
    }
}
