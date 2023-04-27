package com.mctng.armorPatch;

import com.mctng.armorPatch.debug.DebugCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class ArmorPatch extends JavaPlugin {
    public ArrayList<Player> debugList = new ArrayList<>();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(this), this);
        //this.getCommand("itemtype").setExecutor(new ItemTypeCommand()); // Only needed for testing purposes
        //this.getCommand("simhit").setExecutor(new SimHitCommand()); // Only needed for testing purposes
        this.getCommand("armorpatch").setExecutor(new DebugCommand(this)); // Only needed for testing purposes
    }
}
