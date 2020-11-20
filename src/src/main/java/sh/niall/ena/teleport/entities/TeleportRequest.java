package sh.niall.ena.teleport.entities;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import sh.niall.ena.Ena;
import sh.niall.ena.utils.ColourUtils;
import sh.niall.ena.utils.PlayerUtils;

import java.util.Map;

public class TeleportRequest {

    private final Player fromPlayer;
    private final Player toPlayer;
    private BukkitTask timeoutTask;
    private final Map<Player, TeleportRequest> requestMap;

    public TeleportRequest(Map<Player, TeleportRequest> requestMap, Player fromPlayer, Player toPlayer) {
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
        this.requestMap = requestMap;
        createRequest();
        createTimeout();
    }

    public Player getFromPlayer() {
        return fromPlayer;
    }

    private void createRequest() {
        send(fromPlayer, String.format("Sent a teleport request to %s.", PlayerUtils.getName(toPlayer)));
        send(toPlayer, String.format(
                "%s wants to teleport to you!\nPlease type %s/tp accept %sor %s/tp deny.",
                PlayerUtils.getName(fromPlayer), ChatColor.GREEN, ChatColor.WHITE, ChatColor.RED
        ));
    }

    public void cancel() {
        cancelTimeout();
        requestMap.remove(toPlayer);
        send(fromPlayer, String.format("Teleport request to %s canceled.", PlayerUtils.getName(toPlayer)));
        send(toPlayer, String.format("Teleport request from %s canceled.", PlayerUtils.getName(fromPlayer)));
    }

    public void accept() {
        cancelTimeout();
        requestMap.remove(toPlayer);
        send(fromPlayer, String.format("Teleporting to %s!", PlayerUtils.getName(toPlayer)));
        send(toPlayer, String.format("Teleporting %s to you!", PlayerUtils.getName(fromPlayer)));
        fromPlayer.teleport(toPlayer);
    }

    public void deny() {
        cancelTimeout();
        requestMap.remove(toPlayer);
        send(fromPlayer, PlayerUtils.getName(toPlayer) + ChatColor.RED + " has denied your teleport request.");
        send(toPlayer, String.format("Denied %s teleport request.", PlayerUtils.getName(fromPlayer)));
    }

    private void send(Player player, String msg) {
        player.sendMessage(ColourUtils.formatString(msg));
    }

    private void createTimeout() {
        timeoutTask = new BukkitRunnable() {
            @Override
            public void run() {
                send(fromPlayer, String.format("Teleport request to %s timed out.", PlayerUtils.getName(toPlayer)));
                send(toPlayer, String.format("Teleport request from %s timed out.", PlayerUtils.getName(fromPlayer)));
                requestMap.remove(toPlayer);
            }
        }.runTaskLater(Ena.getPlugin(), 30 * 20);
    }

    private void cancelTimeout() {
        if (timeoutTask != null)
            timeoutTask.cancel();
    }
}
