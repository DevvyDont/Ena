package sh.niall.ena;

import org.bukkit.plugin.java.JavaPlugin;
import sh.niall.ena.commands.spawn.SetSpawnCommand;
import sh.niall.ena.commands.spawn.SpawnCommand;

public final class Ena extends JavaPlugin {

    private static Ena plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // Spawn Commands
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
    }

    @Override
    public void onDisable() {
    }

    public static Ena getPlugin() {
        return plugin;
    }
}
