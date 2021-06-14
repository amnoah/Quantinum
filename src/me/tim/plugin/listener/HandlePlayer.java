package me.tim.plugin.listener;

import me.tim.plugin.checks.CheckManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class HandlePlayer implements Listener {
    private CheckManager checkMan;

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        this.checkMan = new CheckManager();
        this.checkMan.handleLagBack(e);
    }
}
