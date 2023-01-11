package de.crazypokemondev.minecraftpaper.velarisrpplugin;

import de.crazypokemondev.minecraftpaper.velarisrpplugin.handlers.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PowerManager implements Listener {
    private final List<PowerHandler> handlers = new ArrayList<>();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        enableForPlayer(event.getPlayer());
    }

    private void addHandler(PowerHandler handler) {
        Bukkit.getServer().getScheduler().runTask(VelarisRpPlugin.INSTANCE, handler::grantPermanentEffects);
        BukkitTask task = Bukkit.getServer().getScheduler().runTaskTimer(VelarisRpPlugin.INSTANCE, handler::handle, 0, 20);
        handler.setTaskId(task.getTaskId());
        handlers.add(handler);
    }

    private void disableHandler(PowerHandler handler) {
        Bukkit.getServer().getScheduler().cancelTask(handler.getTaskId());
        handler.removePermanentEffects();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        disableForPlayer(event.getPlayer());
    }

    public void enableForPlayer(Player player) {
        disableForPlayer(player);
        if (player.hasPermission("rp.zombie")) {
            addHandler(new ZombieHandler(player));
        }
        if (player.hasPermission("rp.netherling")) {
            addHandler(new NetherlingHandler(player));
        }
        if (player.hasPermission("rp.endling")) {
            addHandler(new EndlingHandler(player));
        }
        if (player.hasPermission("rp.windling")) {
            addHandler(new WindlingHandler(player));
        }
        if (player.hasPermission("rp.quinn")) {
            addHandler(new QuinnHandler(player));
        }
        if (player.hasPermission("rp.wolfling")) {
            addHandler(new WolflingHandler(player));
        }
        if (player.hasPermission("rp.werewolf")) {
            addHandler(new WerewolfHandler(player));
        }
    }

    public void disableForPlayer(Player player) {
        handlers.stream().filter(isSamePlayer(player)).forEach(this::disableHandler);
        handlers.removeIf(isSamePlayer(player));
    }

    public void disableAll() {
        handlers.forEach(this::disableHandler);
        handlers.clear();
    }

    @NotNull
    private Predicate<PowerHandler> isSamePlayer(Player player) {
        return x -> x.getPlayer().getUniqueId().equals(player.getUniqueId());
    }

    public void handleEvent(Event event, Player player) {
        handlers.stream().filter(x -> x.handles(event) && x.getPlayer().getUniqueId().equals(player.getUniqueId()))
                .forEach(x -> x.handleEvent(event));
    }
}
