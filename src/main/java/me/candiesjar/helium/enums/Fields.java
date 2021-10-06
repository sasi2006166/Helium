package me.candiesjar.helium.enums;

import java.util.List;

import static me.candiesjar.helium.Helium.getInstance;

public enum Fields {

    HELIUM_PERMISSION("HELIUM.command_permission"),
    GAMEMODE_PERMISSION("HELIUM.gamemode_permission"),
    DIE_PERMISSION("HELIUM.die_permission"),

    USE_GAMEMODE_COMMAND("HELIUM.commands.gamemode.enabled"),
    USE_GAMEMODE_TAB_COMPLETE("HELIUM.commands.gamemode.tab_complete"),
    USE_DIE_COMMAND("HELIUM.commands.die.enabled"),

    SPAWN_TELEPORT("HELIUM.general.teleport_on_spawn");

    private final String path;

    Fields(String path) {
        this.path = path;
    }

    public String getString() {
        return getInstance().getConfig().getString(path);
    }

    public List<String> getStringList() {
        return getInstance().getConfig().getStringList(path);
    }

    public boolean getBoolean() {
        return getInstance().getConfig().getBoolean(path);
    }

    public int getInteger() {
        return getInstance().getConfig().getInt(path);
    }

}
