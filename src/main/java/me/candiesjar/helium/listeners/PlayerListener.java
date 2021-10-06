package me.candiesjar.helium.listeners;

import me.candiesjar.helium.utils.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final ConfigManager configManager = new ConfigManager();

    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();


    }


}
