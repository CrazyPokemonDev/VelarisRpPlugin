package de.crazypokemondev.minecraftpaper.velarisrpplugin.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.PiglinAbstract;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class NetherlingHandler extends PowerHandler {
    private final Random random;

    public NetherlingHandler(Player player) {
        super(player);
        this.random = new Random();
    }

    @Override
    public void handle() {
        int chance = 4;
        EntityEquipment equipment = player.getEquipment();
        if (equipment.getHelmet() != null) chance--;
        if (equipment.getChestplate() != null) chance--;
        if (equipment.getLeggings() != null) chance--;
        if (equipment.getBoots() != null) chance--;
        if (player.isInWaterOrBubbleColumn()) {
            player.damage(1);
        }
        else if (chance > 0 && random.nextInt(4) < chance && player.isInRain() && random.nextBoolean()) {
            player.damage(1);
        }
    }

    @Override
    public boolean handles(Event event) {
        return event instanceof EntityTargetEvent;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EntityTargetEvent e) {
            if (e.getEntity() instanceof PiglinAbstract) e.setCancelled(true);
        }
    }

    @Override
    public void grantPermanentEffects() {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20000000, 0));
    }

    @Override
    public void removePermanentEffects() {
        player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
    }
}
