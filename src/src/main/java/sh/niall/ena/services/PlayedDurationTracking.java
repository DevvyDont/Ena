package sh.niall.ena.services;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import sh.niall.ena.utils.StorageUtils;
import sh.niall.ena.utils.TimeUtils;

import java.time.Instant;

public class PlayedDurationTracking implements Listener {

    @EventHandler
    public void OnPlayerLogin(PlayerJoinEvent event) {
        PersistentDataContainer playerData = event.getPlayer().getPersistentDataContainer();
        StorageUtils.setLong(playerData, StorageUtils.playerLoginTs, Instant.now().getEpochSecond());
    }

    @EventHandler
    public void OnPlayerLogout(PlayerQuitEvent event) {
        PersistentDataContainer playerData = event.getPlayer().getPersistentDataContainer();
        StorageUtils.setLong(playerData, StorageUtils.playerLogoutTs, Instant.now().getEpochSecond());
        StorageUtils.setLong(playerData, StorageUtils.playerTimeSpent, TimeUtils.calculatePlayedDuration(playerData));
        StorageUtils.setLong(playerData, StorageUtils.playerLoginTs, 0);
    }
}
