package com.mctng.armorPatch;

import com.mctng.armorPatch.debug.ItemTypeCommand;
import com.mctng.armorPatch.debug.SimHitCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorPatch extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        //this.getCommand("itemtype").setExecutor(new ItemTypeCommand()); // Only needed for testing purposes
        //this.getCommand("simhit").setExecutor(new SimHitCommand()); // Only needed for testing purposes
    }
}
