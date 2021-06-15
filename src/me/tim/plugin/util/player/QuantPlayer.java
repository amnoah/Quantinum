package me.tim.plugin.util.player;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class QuantPlayer {
    private Player p;

    public QuantPlayer(Player p) {
        this.p = p;
    }

    public Player getPlayer() {
        return p;
    }

    public double getFlyMotionYMultiplier() {
        return 0.9800000190734863;
    }

    public boolean proveNearGround(Location loc) {
        double expand = 0.44;
        for (double x = -expand; x <= expand; x += expand) {
            for (double z = -expand; z <= expand; z += expand) {
                if(loc.clone().add(x, -0.5001, z).getBlock().getType() != Material.AIR) return true;
            }
        }
        return false;
    }

    public boolean isAir(Location loc) {
        return loc.clone().getBlock().getType() == Material.AIR;
    }

    public double getDistToLocation(Location loc) {
        double xDiff = this.getPlayer().getLocation().getX() - loc.getX();
        double yDiff = this.getPlayer().getLocation().getY() - loc.getY();
        double zDiff = this.getPlayer().getLocation().getZ() - loc.getZ();

        return Math.sqrt(xDiff * xDiff * yDiff * yDiff * zDiff * zDiff);
    }
}
