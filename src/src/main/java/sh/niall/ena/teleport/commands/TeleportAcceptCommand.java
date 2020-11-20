package sh.niall.ena.teleport.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import sh.niall.ena.teleport.entities.TeleportRequest;
import sh.niall.miya.services.MiyaCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeleportAcceptCommand extends MiyaCommand implements Listener, TabCompleter {

    Map<Player, TeleportRequest> teleportRequests = new HashMap<>();

    public TeleportAcceptCommand() {
        commandName = "teleport";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        switch (args[0].toLowerCase()) {
            case "accept":
            case "a":
                return acceptTeleport(player);
            case "deny":
            case "d":
                return denyTeleport(player);
            case "cancel":
            case "c":
                return cancelTeleport(player);
            default:
                return createNewTeleportRequest(player, Bukkit.getPlayer(args[0]));
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String[] commands = {"accept", "deny", "cancel"};
        String argLower = args[0].toLowerCase();

        List<String> output = new ArrayList<>();
        for (String cmd : commands) {
            if (argLower.isEmpty() || cmd.startsWith(argLower))
                output.add(cmd);
        }

        output.addAll(Bukkit.getOnlinePlayers().stream().filter(p -> p.getName().toLowerCase().startsWith(argLower))
                .limit(5).map(Player::getDisplayName).collect(Collectors.toList()));
        return output;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void OnPlayerLogout(PlayerQuitEvent playerQuitEvent) {
        // Clear their sent requests
        TeleportRequest sentRequest = getRequestViaSender(playerQuitEvent.getPlayer());
        if (sentRequest != null)
            sentRequest.cancel();

        TeleportRequest receivedRequest = teleportRequests.get(playerQuitEvent.getPlayer());
        if (receivedRequest != null)
            receivedRequest.deny();
    }

    private boolean acceptTeleport(Player player) {
        TeleportRequest request = teleportRequests.get(player);
        if (request == null)
            player.sendMessage(ChatColor.RED + "You don't have any teleport requests!");
        else
            request.accept();
        return true;
    }

    private boolean denyTeleport(Player player) {
        TeleportRequest request = teleportRequests.get(player);
        if (request == null)
            player.sendMessage(ChatColor.RED + "You don't have any teleport requests!");
        else
            request.deny();
        return true;
    }

    private boolean cancelTeleport(Player player) {
        TeleportRequest request = getRequestViaSender(player);
        if (request == null)
            player.sendMessage(ChatColor.RED + "You don't have any teleport requests!");
        else
            request.cancel();
        return true;
    }

    private boolean createNewTeleportRequest(Player sender, Player target) {
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "I don't seem them. Are they online?");
            return true;
        }

        if (sender == target) {
            sender.sendMessage(ChatColor.RED + " You can't teleport to yourself, silly!");
            return true;
        }

        teleportRequests.put(target, new TeleportRequest(teleportRequests, sender, target));
        return true;
    }

    private TeleportRequest getRequestViaSender(Player sender) {
        for (Map.Entry<Player, TeleportRequest> entry : teleportRequests.entrySet()) {
            if (entry.getValue().getFromPlayer() == sender) {
                return entry.getValue();
            }
        }
        return null;
    }
}
