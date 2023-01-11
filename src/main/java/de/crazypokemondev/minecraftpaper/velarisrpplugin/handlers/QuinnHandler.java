package de.crazypokemondev.minecraftpaper.velarisrpplugin.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class QuinnHandler extends PowerHandler {
    public QuinnHandler(Player player) {
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
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20000000, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20000000, 1));
    }

    @Override
    public void removePermanentEffects() {
        player.removePotionEffect(PotionEffectType.BLINDNESS);
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    }
}
