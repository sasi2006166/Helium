package me.candiesjar.helium.commands.subcommands;

import me.candiesjar.helium.enums.CommandType;
import me.candiesjar.helium.interfaces.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlyCommand extends SubCommand {

    @Override
    public CommandType getCommandType() {
        return CommandType.ONLY_PLAYER;
    }

    @Override
    public String getPermission() {
        return "helium";
    }

    @Override
    public void perform(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        if (!player.getAllowFlight()) {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage("test");
        } else {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage("fly disabilitata");
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
