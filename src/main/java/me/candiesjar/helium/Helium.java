package me.candiesjar.helium;

import org.bukkit.plugin.java.JavaPlugin;

public final class Helium extends JavaPlugin {

    private static Helium instance;

    public static Helium getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        // CONFIGURATION AND INSTANCE
        getLogger().info("§7[§a✔§7] Starting plugin §7[§a✔§7]");
        instance = this;
        saveDefaultConfig();


        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
