package com.schoentoon.respawnanchorsworldguard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Material;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RespawnAnchorInteractListener implements Listener {

    @EventHandler
    public void onInteractBlock(final PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getClickedBlock() == null) return;

        if (event.getClickedBlock().getType() == Material.RESPAWN_ANCHOR) {
            // the blowing up should only happen when there's at least one charge and we're right clicking without glowstone
            // in our hand. or when right clicking with glowstone in our hand while it's fully charged
            RespawnAnchor anchor = (RespawnAnchor) event.getClickedBlock().getBlockData();
            if (anchor.getCharges() >= 1) {
                boolean isGlowstone = event.getPlayer().getEquipment().getItem(event.getHand()).getType() == Material.GLOWSTONE;
                if (!isGlowstone || anchor.getCharges() == anchor.getMaximumCharges()) {
                    LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(event.getPlayer());
                    RegionContainer region = WorldGuard.getInstance().getPlatform().getRegionContainer();
                    RegionQuery query = region.createQuery();

                    // query whether the respawn-anchor-explode flag is set in this region or not, which is on by default
                    if (query.testState(
                            BukkitAdapter.adapt(event.getClickedBlock().getLocation()),
                            localPlayer,
                            RespawnAnchorsWorldGuard.RESPAWN_ANCHOR_EXPLODE)
                    ) {
                        event.setCancelled(true); // cancel so we don't end up setting spawn

                        event.getClickedBlock().setType(Material.AIR); // get rid of the respawn anchor, otherwise it would take all the explosion damage

                        event.getClickedBlock().getWorld().createExplosion(
                                event.getClickedBlock().getLocation(),
                                5.0f,
                                true,
                                true
                        ); // create an explosion on the same spot, with fire and break blocks. of a size of 5
                    }
                }
            }
        }
    }
}
