package com.schoentoon.respawnanchorsworldguard;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class RespawnAnchorsWorldGuard extends JavaPlugin {
    public static StateFlag RESPAWN_ANCHOR_EXPLODE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();

        getServer().getPluginManager().registerEvents(new RespawnAnchorInteractListener(), this);
    }

    @Override
    public void onLoad() {
        super.onLoad();

        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            StateFlag flag = new StateFlag("respawn-anchor-explode", true);
            registry.register(flag);
            RESPAWN_ANCHOR_EXPLODE = flag;
        } catch (FlagConflictException e) {
            e.printStackTrace();
            System.err.println("wtf");
            getServer().shutdown();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
