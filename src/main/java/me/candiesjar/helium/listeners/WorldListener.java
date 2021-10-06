package me.candiesjar.helium.listeners;

import me.candiesjar.helium.Helium;
import me.candiesjar.helium.enums.Fields;
import me.candiesjar.helium.utils.ConfigManager;
import me.candiesjar.helium.utils.Utils;
import me.candiesjar.helium.workloads.objects.TeleportablePlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class WorldListener implements Listener {

    private final ConfigManager configManager = new ConfigManager();

    @EventHandler
    public void onSpawn(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Fields.SPAWN_TELEPORT.getBoolean())
            if (configManager.get("spawn.yml").getString("spawn_point") != null) {
                Location location = Utils.stringToLocation(Objects.requireNonNull(configManager.get("spawn.yml").getString("loc")));
                Helium.getInstance().getWorkloadThread().add(new TeleportablePlayer(player, location));
            }
    }
}
