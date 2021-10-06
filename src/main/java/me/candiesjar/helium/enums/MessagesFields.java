package me.candiesjar.helium.enums;

import me.candiesjar.helium.utils.ConfigManager;
import org.bukkit.ChatColor;

import java.util.List;

public enum MessagesFields {

    NOT_PLAYER("MESSAGES.not_player"),
    PLUGIN_RELOADED("MESSAGES.reload"),
    COMMAND("MESSAGES.command"),
    COMMAND_ARGUMENTS("MESSAGES.arguments"),
    MISSING_PERMISSION("MESSAGES.no_permission"),
    PING_COMMAND("MESSAGES.ping"),
    GAMEMODE_UPDATE("MESSAGES.gamemode_update"),
    DIE_MESSAGE("MESSAGES.die_message"),
    SPAWN_CONFIGURATED("MESSAGES.spawn_configurated");

    private final String path;
    private final ConfigManager configManager = new ConfigManager();

    MessagesFields(String path) {
        this.path = path;
    }

    public String getString() {
        return configManager.get("messages.yml").getString(path);
    }

    public List<String> getStringList() {
        return configManager.get("messages.yml").getStringList(path);
    }

    public String getFormattedString() {
        return ChatColor.translateAlternateColorCodes('&', configManager.get("messages.yml").getString(path));
    }

    public static String getFormattedString(String path) {
        return ChatColor.translateAlternateColorCodes('&', path);
    }
}
