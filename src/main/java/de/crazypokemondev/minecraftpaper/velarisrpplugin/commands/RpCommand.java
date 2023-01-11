package de.crazypokemondev.minecraftpaper.velarisrpplugin.commands;

import de.crazypokemondev.minecraftpaper.velarisrpplugin.VelarisRpPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length < 1) return false;
            switch (args[0]) {
                case "on":
                    VelarisRpPlugin.POWER_MANAGER.enableForPlayer(player);
                    return true;
                case "off":
                    VelarisRpPlugin.POWER_MANAGER.disableForPlayer(player);
                    return true;
                default:
                    return false;
            }
        }
        sender.sendMessage("Can only use this command as a player!");
        return true;
    }
}
