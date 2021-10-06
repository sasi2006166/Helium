package me.candiesjar.helium.commands.subcommands;

import me.candiesjar.helium.enums.CommandType;
import me.candiesjar.helium.enums.Fields;
import me.candiesjar.helium.enums.MessagesFields;
import me.candiesjar.helium.interfaces.SubCommand;
import me.candiesjar.helium.utils.ConfigManager;
import me.candiesjar.helium.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class HeliumCommand extends SubCommand {

    private final ConfigManager configManager = new ConfigManager();

    @Override
    public CommandType getCommandType() {
        return CommandType.UNIVERSAL;
    }

    @Override
    public String getPermission() {
        return Fields.HELIUM_PERMISSION.getString();
    }

    @Override
    public void perform(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage("§8§l» §7Running §fHelium 1.0 §7by §bCandiesJar §8§l«");
            return;
        }
        switch (args[0].toLowerCase()) {
            case "help":
                for (String mainCommand : MessagesFields.COMMAND.getStringList()) {
                    commandSender.sendMessage(MessagesFields.getFormattedString(mainCommand));
                }
                break;
            case "setspawn":
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage(MessagesFields.NOT_PLAYER.getFormattedString());
                    return;
                }

                Player player = (Player) commandSender;
                configManager.get("spawn.yml").set("spawn", Utils.locationToString(player.getLocation()));
                configManager.save("spawn.yml");
                player.sendMessage(MessagesFields.SPAWN_CONFIGURATED.getFormattedString());
                break;
            case "reload":
                configManager.reloadAll();
                commandSender.sendMessage(MessagesFields.PLUGIN_RELOADED.getFormattedString());
                break;
            default:
                commandSender.sendMessage(MessagesFields.COMMAND_ARGUMENTS.getFormattedString());
                break;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 1) {
            return Arrays.asList("help", "reload", "setspawn");
        }
        return null;
    }
}
