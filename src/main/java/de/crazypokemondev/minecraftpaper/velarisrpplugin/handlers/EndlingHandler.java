package de.crazypokemondev.minecraftpaper.velarisrpplugin.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.EntityEquipment;

import java.util.Random;

public class EndlingHandler extends PowerHandler {
    private final Random random;

    public EndlingHandler(Player player) {
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
        return false;
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    public void grantPermanentEffects() {

    }

    @Override
    public void removePermanentEffects() {

    }
}
