package me.candiesjar.helium.workloads.objects;

import lombok.RequiredArgsConstructor;
import me.candiesjar.helium.workloads.Workload;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class TeleportablePlayer implements Workload {
    private final Player player;
    private final Location location;

    @Override
    public void compute() {
        player.teleport(location);
    }
}
