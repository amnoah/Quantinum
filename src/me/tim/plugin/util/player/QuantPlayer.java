package me.tim.plugin.util.player;

import org.bukkit.entity.Player;

public class QuantPlayer {

    private Player p;

    public QuantPlayer(Player p) {
        this.p = p;
    }

    public Player getPlayer() {
        return p;
    }
}
