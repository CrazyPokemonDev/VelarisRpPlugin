package de.crazypokemondev.minecraftpaper.velarisrpplugin;

import de.crazypokemondev.minecraftpaper.velarisrpplugin.commands.RpCommand;
import de.crazypokemondev.minecraftpaper.velarisrpplugin.listeners.ForwardEventListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VelarisRpPlugin extends JavaPlugin {
    public static VelarisRpPlugin INSTANCE = null;
    public static PowerManager POWER_MANAGER = null;

    @Override
    public void onEnable() {
        INSTANCE = this;
        POWER_MANAGER = new PowerManager();
        getServer().getPluginManager().registerEvents(POWER_MANAGER, this);
        getServer().getPluginManager().registerEvents(new ForwardEventListener(), this);
        Objects.requireNonNull(this.getCommand("rp")).setExecutor(new RpCommand());
    }

    @Override
    public void onDisable() {
        INSTANCE = null;
        POWER_MANAGER.disableAll();
        POWER_MANAGER = null;
    }
}
