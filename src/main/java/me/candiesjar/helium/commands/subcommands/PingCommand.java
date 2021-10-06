package me.candiesjar.helium.commands.subcommands;

import me.candiesjar.helium.enums.CommandType;
import me.candiesjar.helium.enums.MessagesFields;
import me.candiesjar.helium.interfaces.SubCommand;
import me.candiesjar.helium.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PingCommand extends SubCommand {

    @Override
    public CommandType getCommandType() {
        return CommandType.ONLY_PLAYER;
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public void perform(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        if (args.length == 0) {
            player.sendMessage(MessagesFields.PING_COMMAND.getFormattedString().replace("%ping%", "" + Utils.getPing(player)));
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
