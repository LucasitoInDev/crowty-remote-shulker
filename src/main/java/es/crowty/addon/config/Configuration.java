package es.crowty.addon.config;

import es.crowty.addon.util.Color;
import es.crowty.addon.util.Messager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

public final class Configuration {
    private final File file;
    private FileConfiguration config;

    public Configuration(String fileName) {
        File folder = new File("plugins/crowty-remote-shulker");
        this.file = new File(folder, fileName);

        if (!file.exists()) {
            Messager.log("§7Configuration file §c§lNOT FOUND§7, §7creating §bnew one§7...");

            try {
                if (!folder.exists()) {
                    boolean foldersCreated = folder.mkdirs();
                    if (!foldersCreated) {
                        Messager.log("§cERROR: §7Could not create the plugin folder structure.");
                        return;
                    }
                }

                // Cargar desde los recursos
                InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(fileName);
                if (resourceStream != null) {
                    Files.copy(resourceStream, file.toPath());
                    Messager.log("§aConfiguration and logs loaded correctly§r§2!");
                } else {
                    Messager.log("§cERROR: §7Resource file not found: " + fileName);
                }
            } catch (IOException ex) {
                Messager.log("§cERROR: §7The configuration §chas not been loaded§r§7: §4" + ex);
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public File getFile() {
        return this.file;
    }

    public boolean isEnabledWorlds() { return getConfig().getBoolean("config.enabled-worlds.enabled"); }

    public List<String> getEnabledWorlds() {
        return getConfig().getStringList("config.enabled-worlds.worlds");
    }

    public String getPermission() {
        return getConfig().getString("config.permission");
    }

    public boolean isOpenSoundActive() { return getConfig().getBoolean("config.sound.open.enabled"); }

    public String getOpenSound() {
        return getConfig().getString("config.sound.open.sound-type");
    }

    public boolean isCloseSoundActive() { return getConfig().getBoolean("config.sound.close.enabled"); }

    public String getCloseSound() {
        return getConfig().getString("config.sound.close.sound-type");
    }

    public String getPrefix() {
        return getConfig().getString(Color.translate("config.prefix"));
    }

    public String getNoPermissionMessage() {
        return getConfig().getString(Color.translate("config.nopermission"));
    }

    public boolean isNoPermissionMessage() {
        return getConfig().getBoolean(Color.translate("config.notify-nopermission"));
    }

    public void reloadConfig() {
        this.config = YamlConfiguration.loadConfiguration(file);
        Messager.log("§aConfiguration reloaded successfully!");
    }

    public void saveConfig() {
        try {
            getConfig().save(getFile());
        } catch (IOException ex) {
            Messager.warn("§cError in the configuration, check that it is well structured§r§7: §4" + ex);
        }
    }
}
