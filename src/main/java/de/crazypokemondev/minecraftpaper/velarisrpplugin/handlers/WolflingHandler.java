package de.crazypokemondev.minecraftpaper.velarisrpplugin.handlers;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class WolflingHandler extends PowerHandler {
    private final Random random;

    public WolflingHandler(Player player) {
        super(player);
        this.random = new Random();
    }

    @Override
    public void handle() {
        EntityEquipment equipment = player.getEquipment();
        int chance = 0;
        if (equipment.getHelmet() != null && equipment.getHelmet().getType() == Material.IRON_HELMET) chance++;
        if (equipment.getChestplate() != null && equipment.getChestplate().getType() == Material.IRON_CHESTPLATE) chance++;
        if (equipment.getLeggings() != null && equipment.getLeggings().getType() == Material.IRON_LEGGINGS) chance++;
        if (equipment.getBoots() != null && equipment.getBoots().getType() == Material.IRON_BOOTS) chance++;
        if (chance > 0 && random.nextInt(4) < chance) {
            player.damage(1);
        }
    }

    @Override
    public boolean handles(Event event) {
        return event instanceof EntityDamageByEntityEvent;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EntityDamageByEntityEvent e) {
            if (e.getCause() == DamageCause.ENTITY_ATTACK || e.getCause() == DamageCause.ENTITY_SWEEP_ATTACK) {
                if ((e.getDamager() instanceof Mob mob && WerewolfHandler.isIronWeapon(mob.getEquipment().getItemInMainHand()))
                        || (e.getDamager() instanceof HumanEntity human && WerewolfHandler.isIronWeapon(human.getEquipment().getItemInMainHand()))) {
                    e.setDamage(e.getDamage() * 1.5);
                }
            }
        }
    }

    @Override
    public void grantPermanentEffects() {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20000000, 0));
    }

    @Override
    public void removePermanentEffects() {
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    }
}
