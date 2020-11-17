package sh.niall.ena.chat;

import org.bukkit.Material;

public enum ChatColour {
    WHITE (Material.WHITE_WOOL, "White", "&f"),
    ORANGE (Material.ORANGE_WOOL, "Orange", "&6"),
    PINK (Material.MAGENTA_WOOL, "Pink", "&d"),
    YELLOW (Material.YELLOW_WOOL, "Yellow", "&e"),
    LIME (Material.LIME_WOOL, "Lime", "&a"),
    LIGHT_GRAY (Material.LIGHT_GRAY_WOOL, "Light Gray", "&7"),
    CYAN (Material.CYAN_WOOL, "Cyan", "&b"),
    PURPLE (Material.PURPLE_WOOL, "Purple", "&5"),
    BLUE (Material.BLUE_WOOL, "Blue", "&9"),
    RED (Material.RED_WOOL, "Red", "&4");

    private final Material material;
    private final String name;
    private final String colourCode;

    ChatColour(Material material, String name, String colourCode) {
        this.material = material;
        this.name = name;
        this.colourCode = colourCode;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public String getColourCode() {
        return colourCode;
    }

    public static ChatColour getFromMaterial(Material material) {
        for (ChatColour chatColour : ChatColour.values()) {
            if (chatColour.getMaterial() == material)
                return chatColour;
        }
        return null;
    }
}
