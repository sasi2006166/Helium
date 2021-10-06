package me.candiesjar.helium.commands.subcommands;

import me.candiesjar.helium.enums.CommandType;
import me.candiesjar.helium.enums.Fields;
import me.candiesjar.helium.enums.MessagesFields;
import me.candiesjar.helium.interfaces.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DieCommand extends SubCommand {

    @Override
    public CommandType getCommandType() {
        return CommandType.ONLY_PLAYER;
    }

    @Override
    public String getPermission() {
        return Fields.DIE_PERMISSION.getString();
    }

    @Override
    public void perform(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        player.setHealth(0);
        player.sendMessage(MessagesFields.DIE_MESSAGE.getFormattedString());
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
