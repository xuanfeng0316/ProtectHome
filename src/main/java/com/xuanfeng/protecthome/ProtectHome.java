package com.xuanfeng.protecthome;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class ProtectHome extends JavaPlugin implements Listener {
    
    //Replace this with the actual player name you need to exclude.
    private static final Set<String> WHITELIST = new HashSet<>(Arrays.asList(
        "xuanfeng0316",
        "YaoZhouyi",
        "Zhouyi2013"
    ));

    private static final int CENTER_X = 43000;//Replace with the x coordinate of the center point.
    private static final int CENTER_Z = 43000;//Replace with the z coordinate of the center point.
    private static final double RADIUS = 1000;//Set it to the radius you need.
    private static final double RADIUS_SQUARED = RADIUS * RADIUS;

    private Location teleportLocation;
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        
        World world = getServer().getWorld("world");
        if (world == null) {
            world = getServer().getWorlds().get(0);
            getLogger().info("§eWorld 'world' not found, using: " + world.getName());
        }
        teleportLocation = new Location(world, 0, 0, 0);
        
        getLogger().info("§aThe base protection plug-in has been started.");
        getLogger().info("§aThe only developer of this plugin:xuanfeng0316");
        getLogger().info("§aThis plug-in is open source on: https://github.com/xuanfeng0316/ProtectHome");
        getLogger().info(String.format("§eprotection domain: central point(%d, %d) radius %.0f block", CENTER_X, CENTER_Z, RADIUS));
        getLogger().info("§aWhite list: " + String.join(", ", WHITELIST));
    }
    
    @Override
    public void onDisable() {
        getLogger().info("§cProtectHome has been disabled.");
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        
        if (WHITELIST.contains(player.getName())) {
            return;
        }
        
        Location to = event.getTo();
        if (to == null) return;
        
        double dx = to.getX() - CENTER_X;
        double dz = to.getZ() - CENTER_Z;
        double distSq = dx * dx + dz * dz;
        
        if (distSq <= RADIUS_SQUARED) {
            player.teleport(teleportLocation);
            player.sendMessage("§c⚠ You have entered the protected area!");
        }
    }
}
