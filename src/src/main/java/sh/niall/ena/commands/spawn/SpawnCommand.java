package sh.niall.ena.commands.spawn;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private static final String tpMessage = "Teleported to Spawn from:\nX: %s\nY: %s\nZ: %s";

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