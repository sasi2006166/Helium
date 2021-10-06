package me.candiesjar.helium;

import me.candiesjar.helium.commands.CommandManager;
import me.candiesjar.helium.utils.ConfigManager;
import me.candiesjar.helium.workloads.WorkloadThread;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Helium extends JavaPlugin {

    private static Helium instance;
    private final ConfigManager configManager = new ConfigManager();
    private WorkloadThread workloadThread;

    public static Helium getInstance() {
        return instance;
    }
    public WorkloadThread getWorkloadThread() {
        return workloadThread;
    }

    @Override
    public void onEnable() {

        instance = this;
        createConfigurations();
        Objects.requireNonNull(getCommand("helium")).setExecutor(new CommandManager());

        workloadThread = new WorkloadThread();
        Bukkit.getScheduler().runTaskTimer(this, workloadThread, 5L, 1L);

        getLogger().info("§7[§a✔§7] Loaded successfully §7[§a✔§7]");
    }


    private void createConfigurations() {
        configManager.create("config.yml");
        configManager.create("messages.yml");
        configManager.create("spawn.yml");
    }
}
