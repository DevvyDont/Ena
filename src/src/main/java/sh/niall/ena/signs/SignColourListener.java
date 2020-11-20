package sh.niall.ena.signs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.SignChangeEvent;
import sh.niall.ena.utils.ColourUtils;
import sh.niall.miya.services.MiyaListener;

public class SignColourListener extends MiyaListener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnSignTextChange(SignChangeEvent event) {
        for (int i = 0; i < event.getLines().length; i++) {
            event.setLine(i, ColourUtils.formatString(event.getLine(i)));
        }
    }

}
