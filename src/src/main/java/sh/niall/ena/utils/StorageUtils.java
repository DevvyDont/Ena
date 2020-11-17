package sh.niall.ena.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import sh.niall.ena.Ena;

public class StorageUtils {
    public static final NamespacedKey playerKillCount = new NamespacedKey(Ena.getPlugin(), "playerKillCount");
    public static final NamespacedKey playerDeathCount = new NamespacedKey(Ena.getPlugin(), "playerDeathCount");
    public static final NamespacedKey playerChatColour = new NamespacedKey(Ena.getPlugin(), "playerChatColour");
    public static final NamespacedKey playerTimeSpent = new NamespacedKey(Ena.getPlugin(), "playerTimeSpent");
    public static final NamespacedKey playerLoginTs = new NamespacedKey(Ena.getPlugin(), "playerLoginTs");
    public static final NamespacedKey playerLogoutTs = new NamespacedKey(Ena.getPlugin(), "playerLogoutTs");

    public static String getString(PersistentDataContainer data, NamespacedKey key) {
        return data.get(key, PersistentDataType.STRING);
    }

    public static void setString(PersistentDataContainer data, NamespacedKey key, String toStore) {
        data.set(key, PersistentDataType.STRING, toStore);
    }

    public static long getLong(PersistentDataContainer data, NamespacedKey key) {
        Long foundData = data.get(key, PersistentDataType.LONG);
        return foundData == null ? 0 : (long) foundData;
    }

    public static void setLong(PersistentDataContainer data, NamespacedKey key, long toStore) {
        data.set(key, PersistentDataType.LONG, toStore);
    }

    public static int getInt(PersistentDataContainer data, NamespacedKey key) {
        Integer foundData = data.get(key, PersistentDataType.INTEGER);
        return foundData == null ? 0 : foundData;
    }

    public static void setInt(PersistentDataContainer data, NamespacedKey key, int toStore) {
        data.set(key, PersistentDataType.INTEGER, toStore);
    }
}
