package de.crazypokemondev.minecraftpaper.velarisrpplugin.handlers;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class WerewolfHandler extends PowerHandler {
    private final Random random;

    public WerewolfHandler(Player player) {
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
            player.damage(2);
        }
    }

    @Override
    public boolean handles(Event event) {
        return event instanceof EntityDamageByEntityEvent || event instanceof FoodLevelChangeEvent;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EntityDamageByEntityEvent e) {
            if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) {
                if ((e.getDamager() instanceof Mob mob && isIronWeapon(mob.getEquipment().getItemInMainHand()))
                        || (e.getDamager() instanceof HumanEntity human && isIronWeapon(human.getEquipment().getItemInMainHand()))) {
                    e.setDamage(e.getDamage() * 2);
                }
            }
        }
        else if (event instanceof FoodLevelChangeEvent e) {
            if (e.getItem() != null) {
                if (isMeat(e.getItem())) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0));
                    int foodValue = getFoodValue(e.getItem());
                    e.setFoodLevel(Math.min(20, e.getEntity().getFoodLevel() + foodValue));
                    float saturationDelta = getSaturationDelta(e.getItem());
                    player.setSaturation(Math.max(0, player.getSaturation() + saturationDelta));
                }
                else {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0));
                }
            }
        }
    }

    static final Set<Material> ironWeapons = Set.of(Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE,
            Material.IRON_SHOVEL, Material.IRON_SWORD);
    static boolean isIronWeapon(ItemStack item) {
        return ironWeapons.contains(item.getType());
    }

    private static final Set<Material> meatItems = Set.of(Material.MUTTON, Material.COOKED_MUTTON, Material.PORKCHOP,
            Material.COOKED_PORKCHOP, Material.SALMON, Material.COOKED_SALMON, Material.BEEF, Material.COOKED_BEEF,
            Material.CHICKEN, Material.COOKED_CHICKEN, Material.COD, Material.COOKED_COD, Material.RABBIT,
            Material.COOKED_RABBIT, Material.RABBIT_STEW, Material.ROTTEN_FLESH);
    private static boolean isMeat(ItemStack item) {
        return meatItems.contains(item.getType());
    }

    private static final Map<Material, Integer> foodValues = Map.ofEntries(
            Map.entry(Material.MUTTON, 6),
            Map.entry(Material.COOKED_MUTTON, 2),
            Map.entry(Material.PORKCHOP, 8),
            Map.entry(Material.COOKED_PORKCHOP, 3),
            Map.entry(Material.SALMON, 6),
            Map.entry(Material.COOKED_SALMON, 2),
            Map.entry(Material.BEEF, 8),
            Map.entry(Material.COOKED_BEEF, 3),
            Map.entry(Material.CHICKEN, 6),
            Map.entry(Material.COOKED_CHICKEN, 3),
            Map.entry(Material.COD, 5),
            Map.entry(Material.COOKED_COD, 2),
            Map.entry(Material.RABBIT, 5),
            Map.entry(Material.COOKED_RABBIT, 3),
            Map.entry(Material.RABBIT_STEW, 6),
            Map.entry(Material.ROTTEN_FLESH, 4)
    );
    private static int getFoodValue(ItemStack item) {
        return foodValues.get(item.getType());
    }

    private static final Map<Material, Float> saturationDeltas = Map.ofEntries(
            Map.entry(Material.MUTTON, 8.4f),
            Map.entry(Material.COOKED_MUTTON, -8.4f),
            Map.entry(Material.PORKCHOP, 11f),
            Map.entry(Material.COOKED_PORKCHOP, -11f),
            Map.entry(Material.SALMON, 9.2f),
            Map.entry(Material.COOKED_SALMON, -9.2f),
            Map.entry(Material.BEEF, 11f),
            Map.entry(Material.COOKED_BEEF, -11f),
            Map.entry(Material.CHICKEN, 6f),
            Map.entry(Material.COOKED_CHICKEN, -6f),
            Map.entry(Material.COD, 5.6f),
            Map.entry(Material.COOKED_COD, -5.6f),
            Map.entry(Material.RABBIT, 4.2f),
            Map.entry(Material.COOKED_RABBIT, -4.2f),
            Map.entry(Material.RABBIT_STEW, -4.2f),
            Map.entry(Material.ROTTEN_FLESH, 0f)
    );
    private float getSaturationDelta(ItemStack item) {
        return saturationDeltas.get(item.getType());
    }

    @Override
    public void grantPermanentEffects() {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20000000, 1));
    }

    @Override
    public void removePermanentEffects() {
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    }
}
