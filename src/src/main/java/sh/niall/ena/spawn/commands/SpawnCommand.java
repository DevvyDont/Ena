package sh.niall.ena.spawn.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sh.niall.miya.services.MiyaCommand;

public class SpawnCommand extends MiyaCommand {

    private static final String tpMessage = "Teleported to Spawn from:\nX: %s\nY: %s\nZ: %s";

    public SpawnCommand() {
        commandName = "spawn";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        Location location = player.getLocation();
        Location spawnLocation = player.getWorld().getSpawnLocation();
        player.teleport(spawnLocation);
        String msg = String.format(tpMessage, (int) location.getX(), (int) location.getY(), (int) location.getZ());
        player.sendMessage(msg);
        return true;
    }

}
