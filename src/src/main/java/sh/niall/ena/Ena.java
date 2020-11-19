package sh.niall.ena;

import org.bukkit.plugin.java.JavaPlugin;
import sh.niall.ena.commands.chat.ChatColourCommand;
import sh.niall.ena.commands.player.StatsCommand;
import sh.niall.ena.commands.spawn.SpawnCommand;
import sh.niall.ena.services.ChatFormatter;
import sh.niall.ena.services.PlayedDurationTracking;
import sh.niall.ena.services.PlayerDeathTracking;
import sh.niall.miya.MiyaPlugin;

public final class Ena extends MiyaPlugin {

    private static Ena plugin;

    @Override
    public void onEnable() {
        plugin = this;

        addService(new ChatFormatter());
        addService(new PlayedDurationTracking());
        addService(new PlayerDeathTracking());

        // Commands
        addService(new ChatColourCommand());
        addService(new StatsCommand());
        addService(new SpawnCommand());
    }

    @Override
    public void onDisable() {
    }

    public static Ena getPlugin() {
        return plugin;
    }
}
