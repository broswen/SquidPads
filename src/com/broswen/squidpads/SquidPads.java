package com.broswen.squidpads;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by broswen on 7/27/2016.
 */
public class SquidPads extends JavaPlugin implements Listener{

    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable(){

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(!e.getAction().equals(Action.PHYSICAL)) return;
        if(!e.getClickedBlock().getType().equals(Material.GOLD_PLATE)) return;
        if(!e.getClickedBlock().getRelative(BlockFace.DOWN).getType().equals(Material.LAPIS_BLOCK)) return;
        BukkitTask task = (BukkitTask) new LaunchRunnable(e.getPlayer()).runTaskTimer(this, 0L, 1L);
    }

}
