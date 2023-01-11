package de.crazypokemondev.minecraftpaper.velarisrpplugin.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WindlingHandler extends PowerHandler {
    public WindlingHandler(Player player) {
        super(player);
    }

    @Override
    public void handle() {

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
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20000000, 0));
    }

    @Override
    public void removePermanentEffects() {
        player.removePotionEffect(PotionEffectType.SLOW_FALLING);
    }
}
