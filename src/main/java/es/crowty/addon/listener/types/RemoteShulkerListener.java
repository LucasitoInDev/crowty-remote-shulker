package es.crowty.addon.listener.types;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.ShulkerBox;
import es.crowty.addon.RemoteShulker;
import es.crowty.addon.config.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class RemoteShulkerListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType().toString().endsWith("_SHULKER_BOX")) {
            Configuration config = RemoteShulker.pl.getConfiguration();
            if (!canInteract(player, config)) return;

            if (!player.hasPermission(config.getPermission())) {
                if (config.isNoPermissionMessage()) {
                    player.sendMessage(config.getPrefix() + " " + config.getNoPermissionMessage());
                }
                return;
            }

            event.setCancelled(true);

            if (item.hasItemMeta() && item.getItemMeta() instanceof BlockStateMeta) {
                BlockStateMeta bsm = (BlockStateMeta) item.getItemMeta();
                if (bsm.getBlockState() instanceof ShulkerBox) {
                    ShulkerBox shulkerBox = (ShulkerBox) bsm.getBlockState();
                    Inventory shulkerInventory = shulkerBox.getInventory();

                    Inventory displayInventory = Bukkit.createInventory(player, shulkerInventory.getSize(), item.getItemMeta().getDisplayName());
                    displayInventory.setContents(shulkerInventory.getContents());

                    if (config.isOpenSoundActive()) {
                        Sound sound = Sound.valueOf(config.getOpenSound());
                        player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
                    }

                    player.openInventory(displayInventory);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() instanceof ShulkerBox) {
            Configuration config = RemoteShulker.pl.getConfiguration();
            if (!config.isCloseSoundActive()) return;

            Player player = (Player) e.getPlayer();
            Sound sound = Sound.valueOf(config.getCloseSound());
            player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
        }
    }

    private boolean canInteract(Player player, Configuration config) {
        if (!config.isEnabledWorlds()) return true;

        String worldName = player.getWorld().getName();
        return config.getEnabledWorlds().contains(worldName);
    }
}