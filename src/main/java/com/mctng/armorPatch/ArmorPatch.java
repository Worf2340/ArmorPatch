package com.mctng.armorPatch;

import org.bukkit.plugin.java.JavaPlugin;

public class ArmorPatch extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        this.getCommand("itemtype").setExecutor(new ItemTypeCommand());
        this.getCommand("simhit").setExecutor(new SimHitCommand());
    }
}
