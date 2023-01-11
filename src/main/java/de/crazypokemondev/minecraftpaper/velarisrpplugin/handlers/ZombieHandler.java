package de.crazypokemondev.minecraftpaper.velarisrpplugin.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZombieHandler extends PowerHandler {
    public ZombieHandler(Player player) {
        super(player);
    }

    @Override
    public void handle() {
        Location location = player.getLocation();
        if (player.getWorld().isDayTime()
                && player.getWorld().getBlockAt(location).getLightFromSky() > 11
                && player.getWorld().getHighestBlockYAt(location) <= location.getBlockY()
                && !player.isInWaterOrRainOrBubbleColumn()
                && !player.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
            if (player.getEquipment().getHelmet() == null) {
                player.setFireTicks(200);
            }
        }
    }

    @Override
    public boolean handles(Event event) {
        return false;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    public void grantPermanentEffects() {
        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20000000, 0));
    }

    @Override
    public void removePermanentEffects() {
        player.removePotionEffect(PotionEffectType.WATER_BREATHING);
    }
}
