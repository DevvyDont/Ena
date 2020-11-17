package sh.niall.ena.services;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.persistence.PersistentDataContainer;
import sh.niall.ena.utils.StorageUtils;

public class PlayerDeathTracking implements Listener {

    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent event) {
        addOneToTotal(event.getEntity().getPersistentDataContainer(), StorageUtils.playerDeathCount);

        if (event.getEntity().getKiller() != null)
            addOneToTotal(event.getEntity().getKiller().getPersistentDataContainer(), StorageUtils.playerKillCount);
    }

    private void addOneToTotal(PersistentDataContainer playerData, NamespacedKey key) {
        StorageUtils.setInt(playerData, key, StorageUtils.getInt(playerData, key) + 1);
    }
}
