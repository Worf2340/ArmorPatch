package com.mctng.armorPatch.debug;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class SimHitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }

        Player player = (Player) sender;

        // Make sure the player is not in creative mode
        if (player.getGameMode() == GameMode.CREATIVE) {
            player.sendMessage("You must be in survival or adventure mode to use this command.");
            return true;
        }

        // Create the EntityDamageByEntityEvent
        double damageAmount = 5.0; // You can customize the damage amount
        //EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(player, player, EntityDamageEvent.DamageCause.CUSTOM, damageAmount);
        EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(player, player, EntityDamageEvent.DamageCause.CUSTOM, damageAmount);

        // Call the event
        Bukkit.getPluginManager().callEvent(event);

        // Check if the event is cancelled, if not, apply the damage
        if (!event.isCancelled()) {
            player.damage(event.getFinalDamage());
            player.sendMessage("You've been damaged by " + event.getFinalDamage() + " points.");
        }

        return true;
    }
}
