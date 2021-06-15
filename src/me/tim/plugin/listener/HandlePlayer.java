package me.tim.plugin.listener;

import me.tim.plugin.checks.CheckManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class HandlePlayer implements Listener {
    private CheckManager checkMan = new CheckManager();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        this.checkMan.handleLagBack(e);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        this.checkMan.handleLagBack(e);
    }
}
