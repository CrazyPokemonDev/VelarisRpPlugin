package de.crazypokemondev.minecraftpaper.velarisrpplugin.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public abstract class PowerHandler {
    protected final Player player;
    private int taskId;

    public PowerHandler(Player player) {
        this.player = player;
    }

    public abstract void handle();

    public abstract boolean handles(Event event);

    public abstract void handleEvent(Event event);

    public abstract void grantPermanentEffects();

    public abstract void removePermanentEffects();

    public Player getPlayer() {
        return player;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
