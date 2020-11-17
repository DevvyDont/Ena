package sh.niall.ena;

import org.bukkit.plugin.java.JavaPlugin;

public final class Ena extends JavaPlugin {

    private static Ena plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    @Override
    public void onDisable() {
    }

    public static Ena getPlugin() {
        return plugin;
    }
}
