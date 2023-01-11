package de.crazypokemondev.minecraftpaper.velarisrpplugin.listeners;

import de.crazypokemondev.minecraftpaper.velarisrpplugin.VelarisRpPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerEvent;

public class ForwardEventListener implements Listener {
    private void forward(EntityEvent event) {
        if (event.getEntity() instanceof Player player) {
            VelarisRpPlugin.POWER_MANAGER.handleEvent(event, player);
        }
    }
    private void forward(PlayerEvent event) {
        VelarisRpPlugin.POWER_MANAGER.handleEvent(event, event.getPlayer());
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        forward(event);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        forward(event);
    }

    @EventHandler
    public void onEntityTargetEntity(EntityTargetEvent event) {
        if (event.getTarget() instanceof Player player) VelarisRpPlugin.POWER_MANAGER.handleEvent(event, player);
    }
}
