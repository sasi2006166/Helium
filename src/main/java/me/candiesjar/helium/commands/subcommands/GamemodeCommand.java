package me.candiesjar.helium.commands.subcommands;

import me.candiesjar.helium.enums.CommandType;
import me.candiesjar.helium.enums.Fields;
import me.candiesjar.helium.enums.MessagesFields;
import me.candiesjar.helium.interfaces.SubCommand;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class GamemodeCommand extends SubCommand {

    @Override
    public CommandType getCommandType() {
        return null;
    }

    @Override
    public String getPermission() {
        return Fields.GAMEMODE_PERMISSION.getString();
    }

    @Override
    public void perform(CommandSender commandSender, String[] args) {
        if (Fields.USE_GAMEMODE_COMMAND.getBoolean()) {
            Player player = (Player) commandSender;
            if (args.length == 0) {
                commandSender.sendMessage("test1");
                return;
            }
            switch (args[0].toLowerCase()) {
                case "0":
                case "s":
                case "survival":
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(MessagesFields.GAMEMODE_UPDATE.getFormattedString()
                            .replace("%gamemode%", "" + player.getGameMode()));
                    break;
                case "1":
                case "c":
                case "creative":
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(MessagesFields.GAMEMODE_UPDATE.getFormattedString()
                            .replace("%gamemode%", "" + player.getGameMode()));
                    break;
                case "2":
                case "a":
                case "adventure":
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(MessagesFields.GAMEMODE_UPDATE.getFormattedString()
                            .replace("%gamemode%", "" + player.getGameMode()));
                    break;
                case "3":
                case "sp":
                case "spectator":
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(MessagesFields.GAMEMODE_UPDATE.getFormattedString()
                            .replace("%gamemode%", "" + player.getGameMode()));
                    break;
                default:
                    player.sendMessage("test");
                    break;
            }
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (Fields.USE_GAMEMODE_TAB_COMPLETE.getBoolean())
            if (strings.length == 1) {
                return Arrays.asList("0", "survival", "1", "creative", "2", "adventure", "3", "spectator");
            }
        return null;
    }
}
