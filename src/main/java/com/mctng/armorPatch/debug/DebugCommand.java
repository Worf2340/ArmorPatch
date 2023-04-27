package com.mctng.armorPatch.debug;

import com.mctng.armorPatch.ArmorPatch;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandExecutor {
    ArmorPatch plugin;
    public DebugCommand(ArmorPatch plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command must be run by a player.");
            return false;
        }

        if (strings.length == 1 && strings[0].equalsIgnoreCase("debug")){
            Player p = (Player) commandSender;
            if (this.plugin.debugList.contains(p)) {
                this.plugin.debugList.remove(p);
                p.sendMessage(ChatColor.RED + "[ArmorPatch] Debug mode disabled for " + p.getName());
            }
            else {
                this.plugin.debugList.add(p);
                p.sendMessage(ChatColor.GREEN + "[ArmorPatch] Debug mode enabled for " + p.getName());
            }

            return true;
        }

        return false;
    }
}
