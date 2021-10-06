package me.candiesjar.helium.interfaces;

import me.candiesjar.helium.Helium;
import me.candiesjar.helium.enums.CommandType;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public abstract class SubCommand implements TabCompleter {

    public SubCommand() {
        Helium.getInstance().getCommand("helium").setTabCompleter(this);
    }

    public abstract CommandType getCommandType();
    public abstract String getPermission();
    public abstract void perform(CommandSender commandSender, String[] args);
}
