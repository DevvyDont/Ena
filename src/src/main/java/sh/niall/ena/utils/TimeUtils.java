package sh.niall.ena.utils;

import org.bukkit.persistence.PersistentDataContainer;

import java.time.Instant;

public class TimeUtils {

    /**
     * Calculates how long a player has played on the server for using their persistent data.
     * @param playerData The players persistent data container.
     * @return How long they've played on the server in seconds.
     */
    public static long calculatePlayedDuration(PersistentDataContainer playerData) {
        long playedDuration = StorageUtils.getLong(playerData, StorageUtils.playerTimeSpent);
        long loginTimestamp = StorageUtils.getLong(playerData, StorageUtils.playerLoginTs);
        long currentSessionTime = Instant.now().getEpochSecond() - loginTimestamp;
        return currentSessionTime + playedDuration;
    }

}
