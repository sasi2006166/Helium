package me.candiesjar.helium.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Utils {


    public static String locationToString(Location location) {
        return location.getWorld().getName() +  ";"
                + location.getBlockX() + ";"
                + location.getBlockY() + ";"
                + location.getBlockZ() + ";"
                + location.getYaw() + ";"
                + location.getPitch();
    }

    public static Location stringToLocation(String string) {
        String[] s = string.split(";");
        return new Location(Bukkit.getWorld(s[0]),
                Double.parseDouble(s[1]),
                Double.parseDouble(s[2]),
                Double.parseDouble(s[3]),
                Float.parseFloat(s[4]),
                Float.parseFloat(s[5]));
    }

    public static int getPing(Player player) {
        Class<?> CPClass;
        String serverName = Bukkit.getServer().getClass().getPackage().getName(),
                serverVersion = serverName.substring(serverName.lastIndexOf(".") + 1);

        try {
            CPClass = Class.forName("org.bukkit.craftbukkit." + serverVersion + ".entity.CraftPlayer");
            Object CraftPlayer = CPClass.cast(player);

            Method getHandle = CraftPlayer.getClass().getMethod("getHandle");
            Object EntityPlayer = getHandle.invoke(CraftPlayer);

            Field ping = EntityPlayer.getClass().getDeclaredField("ping");

            return ping.getInt(EntityPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
