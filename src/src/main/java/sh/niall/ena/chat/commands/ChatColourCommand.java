package sh.niall.ena.chat.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import sh.niall.ena.chat.enums.ChatColour;
import sh.niall.ena.utils.ColourUtils;
import sh.niall.ena.utils.StorageUtils;
import sh.niall.miya.services.MiyaCommand;

public class ChatColourCommand extends MiyaCommand implements Listener {

    private Inventory inventory;

    public ChatColourCommand() {
        commandName = "chatcolor";
        createInventory();
    }

    private void createInventory() {
        // Inventory can only be multiples of 9
        inventory = Bukkit.createInventory(null, 18, "Chat Colour Picker");

        for (ChatColour chatColour : ChatColour.values()) {
            ItemStack itemStack = new ItemStack(chatColour.getMaterial(), 1);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(chatColour.getName());
            itemStack.setItemMeta(itemMeta);
            inventory.addItem(itemStack);
        }

        // Add barrier for closing purposes
        ItemStack itemStack = new ItemStack(Material.BARRIER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Close Menu");
        itemStack.setItemMeta(itemMeta);
        inventory.addItem(itemStack);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            ((Player) sender).openInventory(inventory);
            return true;
        } else {
            return false;
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent event) {
        // Ignore if not our inventory or the user clicks on nothing.
        if (event.getInventory() != inventory || event.getCurrentItem() == null)
            return;

        // Since this is our inventory, we should handle this event and no one else should.
        event.setCancelled(true);
        Material material = event.getCurrentItem().getType();
        ChatColour chatColour = ChatColour.getFromMaterial(material);
        if (chatColour == null && material != Material.BARRIER)
            return;

        Player player = (Player) event.getWhoClicked();
        player.closeInventory();
        if (material != Material.BARRIER) {
            PersistentDataContainer playerData = player.getPersistentDataContainer();
            StorageUtils.setString(playerData, StorageUtils.playerChatColour, chatColour.getColourCode());

            String message = String.format("%sChanged your chat name color!", chatColour.getColourCode());
            player.sendMessage(ColourUtils.formatString(message));
        }
    }
}
