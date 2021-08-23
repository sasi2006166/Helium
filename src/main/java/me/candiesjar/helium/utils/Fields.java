package me.candiesjar.helium.utils;

import me.candiesjar.helium.Helium;
import org.bukkit.ChatColor;

import java.util.List;

public enum Fields {
    ;

    private final String path;

    Fields(String path) {
        this.path = path;
    }

    public String getString() {
        return Helium.getInstance().getConfig().getString(path);
    }

    public List<String> getStringList() {
        return Helium.getInstance().getConfig().getStringList(path);
    }

    public int getInteger() {
        return Helium.getInstance().getConfig().getInt(path);
    }

    public static String getFormattedString(String path) {
        return ChatColor.translateAlternateColorCodes('&', path);
    }
}
