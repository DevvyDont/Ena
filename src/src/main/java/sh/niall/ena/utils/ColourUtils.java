package sh.niall.ena.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColourUtils {

    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String formatString(String message) {
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String colourCode = message.substring(matcher.start(), matcher.end());
            message = message.replace(colourCode, ChatColor.of(colourCode).toString());
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
