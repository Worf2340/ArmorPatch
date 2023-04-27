package com.mctng.armorPatch;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemTypeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
//            System.out.println(p.getItemInHand().getType());
//            p.sendMessage(ChatColor.GOLD + p.getItemInHand().getType().toString());
//
//            p.sendMessage(String.valueOf(p.getItemInHand().getTypeId()));
//            System.out.println(p.getItemInHand().getTypeId());

//            String type = p.getItemInHand().getType().toString();
//            Pattern pattern = Pattern.compile("(?<=LOTR_ITEM).*");
//            Matcher m = pattern.matcher(type);
//
//            if (m.find()) {
//                p.sendMessage(ChatColor.LIGHT_PURPLE + m.group(1));
//            }

            for (ItemStack item : p.getInventory()) {
                if (item.getType() != null && item.getType() != Material.AIR) {
                    p.sendMessage(ChatColor.GOLD + item.getType().toString());
                }
            }
        }

        return true;
    }
}
