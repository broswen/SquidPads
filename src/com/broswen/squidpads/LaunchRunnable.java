package com.broswen.squidpads;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftSquid;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Squid;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.particle.ParticleEffect;

/**
 * Created by broswen on 7/27/2016.
 */
public class LaunchRunnable extends BukkitRunnable {

    Player player;
    Squid squid;
    public LaunchRunnable(Player player) {
        this.player = player;
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1, 1);
        squid = (Squid) player.getWorld().spawnEntity(player.getLocation().add(0,2,0), EntityType.SQUID);
        squid.setAI(false);
        squid.setGravity(false);
        squid.setGlowing(true);
        squid.setPassenger(player);
        squid.setInvulnerable(true);
    }

    @Override
    public void run() {

        if(!player.isOnline() || squid.getPassenger() == null || player.isDead()){
            squid.remove();
            player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            cancel();
        }


        ParticleEffect.WATER_BUBBLE.send(Bukkit.getOnlinePlayers(), squid.getLocation(), 1, 1, 1, 0, 10, 100);
        ParticleEffect.WATER_WAKE.send(Bukkit.getOnlinePlayers(), squid.getLocation(), 1, 1, 1, 0, 10, 100);
        player.setFallDistance(0);
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 255));
        squid.setVelocity(player.getEyeLocation().getDirection().normalize().multiply(0.7));
    }
}
