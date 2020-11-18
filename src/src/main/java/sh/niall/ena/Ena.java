package sh.niall.ena;

import org.bukkit.plugin.java.JavaPlugin;
import sh.niall.ena.commands.chat.ChatColourCommand;
import sh.niall.ena.commands.player.StatsCommand;
import sh.niall.ena.commands.spawn.SpawnCommand;
import sh.niall.ena.services.ChatFormatter;
import sh.niall.ena.services.PlayedDurationTracking;
import sh.niall.ena.services.PlayerDeathTracking;

public final class Ena extends JavaPlugin {

    private static Ena plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new ChatFormatter(), this);
        getServer().getPluginManager().registerEvents(new PlayedDurationTracking(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathTracking(), this);

        // Commands
        getCommand("chatcolor").setExecutor(new ChatColourCommand());
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
    }

    @Override
    public void onDisable() {
    }

    public static Ena getPlugin() {
        return plugin;
    }
}
