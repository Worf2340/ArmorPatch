package com.mctng.armorPatch;

import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityDamageByEntityListener implements Listener {

    ArmorPatch plugin;

    public EntityDamageByEntityListener(ArmorPatch plugin) {
        this.plugin = plugin;
    }
    private static final Map<Integer, Double> ARMOR_POINTS_MAP = new HashMap<Integer, Double>() {{
        put(4784, 2.0); // Arnorian Helmet
        put(4785, 6.0); // Arnorian Chestplate
        put(4786, 5.0); // Arnorian Leggings
        put(4787, 2.0); // Arnorian Boots

        put(4747, 2.0); // Blackroot Vale Helmet
        put(4748, 6.0); // Blackroot Vale Chestplate
        put(4749, 5.0); // Blackroot Vale Leggings
        put(4750, 2.0); // Blackroot Vale Bootsput

        put(4422, 2.0); // Blue Dwarven Helmet
        put(4423, 7.0); // Blue Dwarven Vale Chestplate
        put(4424, 6.0); // Blue Dwarven Vale Leggings
        put(4425, 2.0); // Blue Dwarven Vale Boots

        put(4675, 2.0); // Dalish Helmet
        put(4676, 6.0); // Dalish Chestplate
        put(4677, 5.0); // Dalish Leggings
        put(4678, 2.0); // Dalish Boots

        put(4541, 2.0); // Dol Amroth Helmet
        put(4542, 6.0); // Dol Amroth Chestplate
        put(4543, 5.0); // Dol Amroth Leggings
        put(4544, 2.0); // Dol Amroth Boots

        put(4679, 2.0); // Dorwinion Helmet
        put(4680, 5.0); // Dorwinion Chestplate
        put(4681, 4.0); // Dorwinion Leggings
        put(4682, 2.0); // Dorwinion Boots

        put(4683, 2.0); // Dorwinion Elven Helmet
        put(4684, 6.0); // Dorwinion Elven Chestplate
        put(4685, 5.0); // Dorwinion Elven Leggings
        put(4686, 2.0); // Dorwinion Elven Boots

        put(4222, 2.0); // Dwarven Helmet
        put(4223, 7.0); // Dwarven Chestplate
        put(4224, 6.0); // Dwarven Leggings
        put(4225, 2.0); // Dwarven Boots

        put(4526, 2.0); // Silver-Trimmed Dwarven Helmet
        put(4527, 7.0); // Silver-Trimmed Dwarven Chestplate
        put(4528, 6.0); // Silver-Trimmed Dwarven Leggings
        put(4529, 2.0); // Silver-Trimmed Dwarven Boots

        put(4530, 2.0); // Gold-Trimmed Dwarven Helmet
        put(4531, 7.0); // Gold-Trimmed Dwarven Chestplate
        put(4532, 6.0); // Gold-Trimmed Dwarven Leggings
        put(4533, 2.0); // Gold-Trimmed Dwarven Boots

        put(4534, 2.0); // Mithril-Trimmed Dwarven Helmet
        put(4535, 7.0); // Mithril-Trimmed Dwarven Chestplate
        put(4536, 6.0); // Mithril-Trimmed Dwarven Leggings
        put(4537, 2.0); // Mithril-Trimmed Dwarven Boots

        put(4181, 2.0); // Galadhrim Helmet
        put(4182, 6.0); // Galadhrim Chestplate
        put(4183, 5.0); // Galadhrim Leggings
        put(4184, 2.0); // Galadhrim Boots

        put(4227, 2.0); // Galvorn Helmet
        put(4228, 6.0); // Galvorn Chestplate
        put(4229, 5.0); // Galvorn Leggings
        put(4230, 2.0); // Galvorn Boots

        put(4579, 2.0); // Gondolinian Helmet
        put(4580, 7.0); // Gondolinian Chestplate
        put(4581, 6.0); // Gondolinian Leggings
        put(4582, 2.0); // Gondolinian Boots

        put(4153, 2.0); // Gondorian Helmet
        put(4154, 6.0); // Gondorian Chestplate
        put(4155, 5.0); // Gondorian Leggings
        put(4156, 2.0); // Gondorian Boots
        put(4281, 2.0); // Winged-Gondorian Helmet

        put(4693, 2.0); // Ithilien Ranger Helmet
        put(4694, 5.0); // Ithilien Ranger Chestplate
        put(4695, 4.0); // Ithilien Ranger Leggings
        put(4696, 2.0); // Ithilien Ranger Boots

        put(4764, 2.0); // Lamedon Helmet
        put(4765, 5.0); // Lamedon Chestplate
        put(4766, 4.0); // Lamedon Leggings
        put(4767, 2.0); // Lamedon Boots
        put(4769, 4.0); // Lamedon Jacket

        put(4391, 2.0); // Lindon Helmet
        put(4392, 6.0); // Lindon Chestplate
        put(4393, 5.0); // Lindon Leggings
        put(4394, 2.0); // Lindon Boots

        put(4730, 2.0); // Lossarnach Helmet
        put(4731, 5.0); // Lossarnach Chestplate
        put(4732, 4.0); // Lossarnach Leggings
        put(4733, 2.0); // Lossarnach Boots

        put(4734, 2.0); // Pelargir Helmet
        put(4735, 6.0); // Pelargir Chestplate
        put(4736, 5.0); // Pelargir Leggings
        put(4737, 2.0); // Pelargir Boots

        put(4738, 2.0); // Pinnath Gelin Helmet
        put(4739, 6.0); // Pinnath Gelin Chestplate
        put(4740, 5.0); // Pinnath Gelin Leggings
        put(4741, 2.0); // Pinnath Gelin Boots

        put(4295, 2.0); // Ranger Helmet
        put(4296, 5.0); // Ranger Chestplate
        put(4297, 4.0); // Ranger Leggings
        put(4298, 2.0); // Ranger Boots

        put(4829, 2.0); // Rivendell Helmet
        put(4830, 6.0); // Rivendell Chestplate
        put(4831, 5.0); // Rivendell Leggings
        put(4832, 2.0); // Rivendell Boots

        put(4277, 2.0); // Rohirric Coif
        put(4278, 5.0); // Rohirric Hauberk
        put(4279, 4.0); // Rohirric Leggings
        put(4280, 2.0); // Rohirric Boots

        put(4585, 2.0); // Rohirric Marshal Helmet
        put(4586, 6.0); // Rohirric Marshal Chestplate
        put(4587, 5.0); // Rohirric Marshal Leggings
        put(4588, 2.0); // Rohirric Marshal Boots

        put(4598, 2.0); // Taurethrim Helmet
        put(4599, 5.0); // Taurethrim Chestplate
        put(4600, 4.0); // Taurethrim Leggings
        put(4601, 2.0); // Taurethrim Boots
        put(4602, 2.0); // Taurethrim Chieftan Helmet

        put(4630, 2.0); // Golden Taurethrim Helmet
        put(4631, 6.0); // Golden Taurethrim Chestplate
        put(4632, 5.0); // Golden Taurethrim Leggings
        put(4633, 2.0); // Golden Taurethrim Boots

        put(4325, 2.0); // Wood-Elven Helmet
        put(4326, 6.0); // Wood-Elven Chestplate
        put(4327, 5.0); // Wood-Elven Leggings
        put(4328, 2.0); // Wood-Elven Boots

        put(4266, 1.0); // Wood-Elven Scout Hood
        put(4267, 4.0); // Wood-Elven Scout Tunic
        put(4268, 3.0); // Wood-Elven Scout Leggings
        put(4269, 1.0); // Wood-Elven Scout Boots

        put(4358, 2.0); // Angmar Helmet
        put(4359, 6.0); // Angmar Chestplate
        put(4360, 5.0); // Angmar Leggings
        put(4361, 2.0); // Angmar Boots

        put(4903, 2.0); // Black Numenorean Helmet
        put(4904, 6.0); // Black Numenorean Chestplate
        put(4905, 5.0); // Black Numenorean Leggings
        put(4906, 2.0); // Black Numenorean Boots

        put(4484, 2.0); // Black Uruk Helmet
        put(4485, 7.0); // Black Uruk Chestplate
        put(4486, 6.0); // Black Uruk Leggings
        put(4487, 2.0); // Black Uruk Boots

        put(4378, 2.0); // Coast Southron Helmet
        put(4379, 5.0); // Coast Southron Chestplate
        put(4380, 4.0); // Coast Southron Leggings
        put(4381, 2.0); // Coast Southron Boots
        put(4489, 2.0); // Southron Champion Helmet

        put(4869, 2.0); // Corsair Helmet
        put(4870, 5.0); // Corsair Chestplate
        put(4871, 4.0); // Corsair Leggings
        put(4872, 2.0); // Corsair Boots

        put(4457, 2.0); // Dol Guldur Helmet
        put(4458, 6.0); // Dol Guldur Chestplate
        put(4459, 5.0); // Dol Guldur Leggings
        put(4460, 2.0); // Dol Guldur Boots

        put(4299, 2.0); // Dunlending Helmet
        put(4300, 5.0); // Dunlending Chestplate
        put(4301, 4.0); // Dunlending Leggings
        put(4302, 2.0); // Dunlending Boots

        put(4865, 2.0); // Gulfen Helmet
        put(4866, 5.0); // Gulfen Chestplate
        put(4867, 4.0); // Gulfen Leggings
        put(4868, 2.0); // Gulfen Boots

        put(4641, 2.0); // Gundabad Uruk Helmet
        put(4642, 7.0); // Gundabad Uruk Chestplate
        put(4643, 6.0); // Gundabad Uruk Leggings
        put(4644, 2.0); // Gundabad Uruk Boots

        put(4508, 2.0); // Half-Troll Helmet
        put(4509, 5.0); // Half-Troll Chestplate
        put(4510, 4.0); // Half-Troll Leggings
        put(4511, 2.0); // Half-Troll Boots

        put(4882, 2.0); // Harnennor Helmet
        put(4883, 5.0); // Harnennor Chestplate
        put(4884, 4.0); // Harnennor Leggings
        put(4885, 2.0); // Harnennor Boots

        put(4128, 2.0); // Mordor Helmet
        put(4129, 6.0); // Mordor Chestplate
        put(4130, 5.0); // Mordor Leggings
        put(4131, 2.0); // Mordor Boots

        put(4551, 2.0); // Morwaith Helmet
        put(4552, 5.0); // Morwaith Chestplate
        put(4553, 4.0); // Morwaith Leggings
        put(4554, 2.0); // Morwaith Boots

        put(4555, 1.0); // Morwaith Chieftan Helmet
        put(4556, 4.0); // Morwaith Chieftan Chestplate
        put(4557, 3.0); // Morwaith Chieftan Leggings
        put(4558, 1.0); // Morwaith Chieftan Boots

        put(4310, 2.0); // Morgul Helmet
        put(4311, 6.0); // Morgul Chestplate
        put(4312, 5.0); // Morgul Leggings
        put(4313, 2.0); // Morgul Boots

        put(4795, 2.0); // Rhunic Helmet
        put(4796, 5.0); // Rhunic Chestplate
        put(4797, 4.0); // Rhunic Leggings
        put(4798, 2.0); // Rhunic Boots

        put(4808, 2.0); // Golden Rhunic Helmet
        put(4809, 6.0); // Golden Rhunic Chestplate
        put(4810, 5.0); // Golden Rhunic Leggings
        put(4811, 2.0); // Golden Rhunic Boots
        put(4812, 2.0); // Rhunic Warlord Helmet

        put(4878, 2.0); // Umbaric Helmet
        put(4879, 6.0); // Umbaric Chestplate
        put(4880, 5.0); // Umbaric Leggings
        put(4881, 2.0); // Umbaric Boots

        put(4254, 2.0); // Uruk Helmet
        put(4255, 7.0); // Uruk Chestplate
        put(4256, 6.0); // Uruk Leggings
        put(4257, 2.0); // Uruk Boots
        put(4649, 2.0); // Uruk Berserker Helmet

        put(4464, 2.0); // Utumno Helmet
        put(4465, 7.0); // Utumno Chestplate
        put(4466, 6.0); // Utumno Leggings
        put(4467, 2.0); // Utumno Boots

        put(4563, 1.0); // Bone Helmet
        put(4564, 3.0); // Bone Chestplate
        put(4565, 2.0); // Bone Leggings
        put(4566, 1.0); // Bone Boots

        put(4108, 2.0); // Bronze Helmet
        put(4109, 5.0); // Bronze Chestplate
        put(4110, 4.0); // Bronze Leggings
        put(4111, 2.0); // Bronze Boots

        put(302, 2.0); // Chain Helmet
        put(303, 5.0); // Chain Chestplate
        put(304, 4.0); // Chain Leggings
        put(305, 1.0); // Chain Boots

        put(310, 3.0); // Diamond Helmet
        put(311, 8.0); // Diamond Chestplate
        put(312, 6.0); // Diamond Leggings
        put(313, 3.0); // Diamond Boots

        put(4191, 1.0); // Fur Helmet
        put(4192, 4.0); // Fur Chestplate
        put(4193, 3.0); // Fur Leggings
        put(4194, 1.0); // Fur Boots

        put(4384, 1.0); // Gemsbook Hide Helmet
        put(4385, 4.0); // Gemsbook Hide Chestplate
        put(4386, 3.0); // Gemsbook Hide Leggings
        put(4387, 1.0); // Gemsbook Hide Boots

        put(314, 2.0); // Gold Helmet
        put(315, 5.0); // Gold Chestplate
        put(316, 3.0); // Gold Leggings
        put(317, 1.0); // Gold Boots

        put(306, 2.0); // Iron Helmet
        put(307, 6.0); // Iron Chestplate
        put(308, 5.0); // Iron Leggings
        put(309, 2.0); // Iron Boots

        put(298, 1.0); // Leather Helmet
        put(299, 3.0); // Leather Chestplate
        put(300, 2.0); // Leather Leggings
        put(301, 1.0); // Leather Boots

        put(4157, 3.0); // Mithril Helmet
        put(4158, 8.0); // Mithril Chestplate
        put(4159, 6.0); // Mithril Leggings
        put(4160, 3.0); // Mithril Boots

        put(4621, 1.0); // Galahdrim Cloak Helmet
        put(4622, 3.0); // Galahdrim Cloak Chestplate
        put(4623, 2.0); // Galahdrim Cloak Leggings
        put(4624, 1.0); // Galahdrim Cloak Boots

        put(4753, 4.0); // Dol Amroth Gambeson

        put(4757, 4.0); // Gondorian Gambeson

        put(4758, 4.0); // Lebennin Gambeson

        put(4776, 4.0); // Dalish Gambeson


    }};

    private static final Map<Integer, Double> REACH_MAP = new HashMap<Integer, Double>() {{
        put(4608, 1.5); // Lindon Battlestaff
        put(4609, 1.5); // Galadhrim Battlestaff
        put(4610, 1.5); // Wood-Elven Battlestaff
        put(4834, 1.5); // Rivendell Battlestaff

        put(4664, 2.0); // Mithril Halberd

        put(4603, 1.5); // Umbaric Poleaxe
        put(4719, 1.5); // Angmar Poleaxe

        put(4607, 2.0); // Dol Amroth Lance
        put(4645, 2.0); // Gondorian Lance
        put(4657, 2.0); // Rohirric Lance

        put(4660, 2.0); // Galadhrim Longspear
        put(4661, 2.0); // Lindon Longspear
        put(4662, 2.0); // Wood-Elven Longspear
        put(4755, 2.0); // Dol Amroth Longspear
        put(4835, 2.0); // Rivendell Longspear

        put(4604, 2.0); // Uruk Pike
        put(4627, 2.0); // Half-Troll Pike
        put(4628, 2.0); // Iron Pike
        put(4636, 2.0); // Dwarven Pike
        put(4637, 2.0); // Blue Dwarven Pike
        put(4700, 2.0); // Gundabad Uruk Pike
        put(4707, 2.0); // Taurethrim Pike
        put(4720, 2.0); // Dol Guldur Spike
        put(4725, 2.0); // Umbaric Pike
        put(4752, 2.0); // Gondorian Pike
        put(4794, 2.0); // Rhunic Pike
        put(4890, 2.0); // Haradric Pike

        put(4161, 1.5); // Gondorian Spear
        put(4162, 1.5); // Mordor Spear
        put(4163, 1.5); // Bronze Spear
        put(4164, 1.5); // Iron Spear
        put(4165, 1.5); // Mithril Spear
        put(4179, 1.5); // Galadhrim Spear
        put(4523, 1.5); // Uruk Spear
        put(4176, 1.5); // Rohirric Spear
        put(4324, 1.5); // Wood-Elven Spear
        put(4353, 1.5); // Angmar Spear
        put(4402, 1.5); // Lindon Spear
        put(4406, 1.5); // Umbaric Spear
        put(4427, 1.5); // Dwarven Spear
        put(4428, 1.5); // Blue Dwarven Spear
        put(4450, 1.5); // Dol Guldur Spear
        put(4481, 1.5); // Black Uruk Spear
        put(4494, 1.5); // Utumno Spear
        put(4550, 1.5); // Morwaith Spear
        put(4596, 1.5); // Taurethrim Spear
        put(4673, 1.5); // Dalish Spear
        put(4687, 1.5); // Bladorthin Spear
        put(4699, 1.5); // Gunadabd Uruk Spear
        put(4759, 1.5); // Stone Spear
        put(4792, 1.5); // Rhunic Spear
        put(4828, 1.5); // Rivendell Spear
        put(4839, 1.5); // Arnorian Spear
        put(4889, 1.5); // Haradric Spear
        put(4910, 1.5); // Black Numenorean Spear

        put(4304, 1.5); // Dunlending Trident
        put(4745, 1.5); // Pelargir Trident


        put(4606, 1.5); // Mordor Warscythe
    }};
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        Entity damager = event.getDamager();

        // Check if damage is from a projectile
        boolean isProjectile = damager instanceof Projectile;

//        player.sendMessage(String.valueOf(isProjectile));
//        player.sendMessage(String.valueOf(event.getCause()));

        ItemStack[] armorInventory = player.getInventory().getArmorContents();
        ItemStack weapon = null;

        if (!isProjectile) {
            if (damager instanceof Player) {
                weapon = ((Player) damager).getInventory().getItemInHand();
            } else if (damager instanceof LivingEntity) {
                weapon = ((LivingEntity) damager).getEquipment().getItemInHand();
            }
        }


        double damage = event.getDamage(EntityDamageEvent.DamageModifier.BASE);
        double totalProtection = -1 * getArmorDamageReductionWithModifiers(armorInventory, weapon) * damage;

        if (this.plugin.debugList.contains(player)) {
            player.sendMessage(ChatColor.RED + String.valueOf(event.getFinalDamage()));
        }
//        player.sendMessage(ChatColor.BLUE + String.valueOf(event.getOriginalDamage(EntityDamageEvent.DamageModifier.ARMOR)));
//        player.sendMessage(ChatColor.LIGHT_PURPLE + String.valueOf(totalProtection));
//        player.sendMessage(ChatColor.GOLD + String.valueOf(event.getOriginalDamage(EntityDamageEvent.DamageModifier.MAGIC)));
//        player.sendMessage(ChatColor.GREEN + String.valueOf(event.getFinalDamage()));

        // Calculate damage reduction using the total protection value
        event.setDamage(EntityDamageEvent.DamageModifier.ARMOR, totalProtection);

        if (this.plugin.debugList.contains(player)) {
            player.sendMessage(ChatColor.GREEN + String.valueOf(event.getFinalDamage()));
        }
    }

    public double getArmorDamageReductionWithModifiers(ItemStack[] armorInventory, ItemStack weapon) {
        double totalArmorPoints = 0;

        boolean weaponHasHighReach = weapon != null && hasHighReach(weapon);

        for (ItemStack armorPiece : armorInventory) {
            if (armorPiece != null && armorPiece.getType() != Material.AIR) {
                double baseArmorPoints = ARMOR_POINTS_MAP.get(armorPiece.getTypeId());
                double modifierArmorPoints = 0;

                List<String> modifiers = getCustomModifiers(armorPiece); // Assume this method returns the list of custom modifiers

                for (String modifier : modifiers) {
                   // System.out.println(armorPiece + ": " + modifier);
                    switch (modifier) {
                        case "protect1":
                            modifierArmorPoints += 1;
                            break;
                        case "protect2":
                            modifierArmorPoints += 2;
                            break;
                        case "protectMithril":
                            if (weaponHasHighReach) {
                                modifierArmorPoints += 4;
                            }
                            break;
                        case "protectWeak1":
                            modifierArmorPoints -= 1;
                            break;
                        case "protectWeak2":
                            modifierArmorPoints -= 2;
                            break;
                    }
                }

                // System.out.println(armorPiece + ": " + modifierArmorPoints);

                totalArmorPoints += (baseArmorPoints + modifierArmorPoints);
            }
        }

        // Each armor point reduces damage by 4%
        return totalArmorPoints * .04;
    }

    // Assume the implementation of the following methods
    private boolean hasHighReach(ItemStack weapon) {
        int itemId = weapon.getTypeId();
        Double baseReach = REACH_MAP.get(itemId);

        // Return false if the item id doesn't exist in the REACH_MAP
        if (baseReach == null) {
            return false;
        }

        List<String> weaponModifiers = getCustomModifiers(weapon);
        double reach = baseReach;

        // Apply the meleeReach1 modifier
        if (weaponModifiers.contains("meleeReach1")) {
            reach *= 1.25;
        }

        // Apply the meleeUnreach1 modifier
        if (weaponModifiers.contains("meleeUnreach1")) {
            reach *= 0.75;
        }

        // Check if the adjusted reach is greater than 1.3
        if (reach > 1.3) {
            return true;
        } else {
            return false;
        }
    }


    private List<String> getCustomModifiers(ItemStack armorPiece) {
        ArrayList<String> modifiers = new ArrayList<>();

        net.minecraft.server.v1_7_R4.ItemStack nmsItem = CraftItemStack.asNMSCopy(armorPiece);

        NBTTagCompound nbt = nmsItem.getTag();

        if (nbt != null) {
            NBTTagList tagList = nbt.getList("LOTREnch", 8);

            for (int i = 0; i < tagList.size(); i++) {
                String currentTag = tagList.getString(i);
                modifiers.add(currentTag);
            }

        }

        return modifiers;
    }
}
