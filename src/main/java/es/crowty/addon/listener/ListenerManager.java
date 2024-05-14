package es.crowty.addon.listener;

import es.crowty.addon.listener.types.RemoteShulkerListener;
import es.crowty.addon.RemoteShulker;
import org.bukkit.Bukkit;

public class ListenerManager {
    public ListenerManager() {
        Bukkit.getPluginManager().registerEvents(new RemoteShulkerListener(), RemoteShulker.pl);
    }
}
