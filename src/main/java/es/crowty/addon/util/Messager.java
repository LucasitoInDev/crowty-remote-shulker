package es.crowty.addon.util;

import es.crowty.addon.RemoteShulker;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messager {

    public static void log(String s) {
        Bukkit.getConsoleSender().sendMessage("§6[crowty-remote-shulker] §e" + s);
    }

    public static void notifyTo(Player p, String s) {
        p.sendMessage(Color.translate(RemoteShulker.pl.getConfiguration().getPrefix()) + s);
    }

    public static void warn(final String s) {
        Bukkit.getConsoleSender().sendMessage("§4[crowty-remote-shulker] §c" + s);
    }

    public static void notifyNoPermission(CommandSender sender) {
        if (!RemoteShulker.pl.getConfiguration().isNoPermissionMessage()) {
            return;
        }
        sender.sendMessage(Color.translate(RemoteShulker.pl.getConfiguration().getNoPermissionMessage()));
    }

}