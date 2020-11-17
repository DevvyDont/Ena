package sh.niall.ena.utils;

import org.bukkit.persistence.PersistentDataContainer;

import java.time.Instant;

public class TimeUtils {

    public static long calculatePlayedDuration(PersistentDataContainer playerData) {
        long playedDuration = StorageUtils.getLong(playerData, StorageUtils.playerTimeSpent);
        long loginTimestamp = StorageUtils.getLong(playerData, StorageUtils.playerLoginTs);
        long currentSessionTime = Instant.now().getEpochSecond() - loginTimestamp;
        return currentSessionTime + playedDuration;
    }

}
