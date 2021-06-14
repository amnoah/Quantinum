package me.tim.plugin.listener;

import me.tim.plugin.Quantinum;
import me.tim.plugin.util.player.QuantPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class HandleJoinLeave implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Quantinum.acPlayer.put(p.getUniqueId(), new QuantPlayer(p));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Quantinum.acPlayer.remove(p.getUniqueId());
    }
}
