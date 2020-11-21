package sh.niall.ena;

import sh.niall.ena.chat.commands.ChatColourCommand;
import sh.niall.ena.chat.commands.NickNameCommand;
import sh.niall.ena.chat.listeners.ChatFormatter;
import sh.niall.ena.signs.SignColourListener;
import sh.niall.ena.spawn.commands.SpawnCommand;
import sh.niall.ena.stats.commands.PlayerStatsCommand;
import sh.niall.ena.stats.listeners.PlayedDurationTracking;
import sh.niall.ena.stats.listeners.PlayerDeathTracking;
import sh.niall.ena.teleport.commands.TeleportAcceptCommand;
import sh.niall.miya.MiyaPlugin;

public final class Ena extends MiyaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        addService(new ChatFormatter());
        addService(new PlayedDurationTracking());
        addService(new PlayerDeathTracking());
        addService(new SignColourListener());

        // Commands
        addService(new ChatColourCommand());
        addService(new PlayerStatsCommand());
        addService(new SpawnCommand());
        addService(new TeleportAcceptCommand());
        addService(new NickNameCommand());
    }

}
