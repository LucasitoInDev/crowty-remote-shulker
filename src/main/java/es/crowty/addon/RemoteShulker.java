package es.crowty.addon;

import es.crowty.addon.config.Configuration;
import es.crowty.addon.listener.ListenerManager;
import es.crowty.addon.util.Messager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RemoteShulker extends JavaPlugin {
    public static RemoteShulker pl;
    private Configuration config;
    private ListenerManager listenerManager;

    @Override
    public void onEnable() {
        pl = this;
        config = new Configuration("config.yml");
        Messager.log("§6■ §7□ □ §fCROWTY §5REMOTE-SHULKER§r §f» §7Initializing plugin configuration...");
        listenerManager = new ListenerManager();
        Messager.log("§6■ §e■ §7□ §fCROWTY §5REMOTE-SHULKER§r §f» §7Registering commands...");
        Messager.log("§a■ ■ ■ §fCROWTY §5REMOTE-SHULKER§r §f» §aStarted successfully!");
    }

    @Override
    public void onDisable() {
        listenerManager = null;
        Messager.log("§c■ ■ ■ §fCROWTY §5REMOTE-SHULKER§r §f» §cPlugin shut down correctly!");
        pl = null;
    }

    public Configuration getConfiguration() {
        return config;
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }
}
