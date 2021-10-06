package me.candiesjar.helium.commands;

import com.google.common.collect.Maps;
import me.candiesjar.helium.commands.subcommands.*;
import me.candiesjar.helium.enums.CommandType;
import me.candiesjar.helium.enums.MessagesFields;
import me.candiesjar.helium.interfaces.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class CommandManager implements CommandExecutor {

    private final Map<String, SubCommand> aliasMap = Maps.newHashMap();

    public CommandManager() {
        aliasMap.put("die", new DieCommand());
        aliasMap.put("fly", new FlyCommand());
        aliasMap.put("ping", new PingCommand());
        aliasMap.put("gm", new GamemodeCommand());
        aliasMap.put("helium", new HeliumCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (aliasMap.get(label.toLowerCase()) == null) return true;
        SubCommand subCommand = aliasMap.get(label.toLowerCase());

        if (subCommand.getCommandType() == CommandType.ONLY_PLAYER && !(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesFields.NOT_PLAYER.getFormattedString());
            return true;
        }

        if (!commandSender.hasPermission(subCommand.getPermission())) {
            commandSender.sendMessage(MessagesFields.MISSING_PERMISSION.getFormattedString()
                    .replace("%permission%", subCommand.getPermission()));
            return true;
        }

        subCommand.perform(commandSender, args);
        return true;
    }
}
