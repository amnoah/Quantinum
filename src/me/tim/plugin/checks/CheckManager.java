package me.tim.plugin.checks;

import me.tim.plugin.Quantinum;
import me.tim.plugin.checks.impl.movement.FlyCheck;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class CheckManager {
    private List<Check> checks;

    public CheckManager() {
        this.checks = new ArrayList<>();
        this.addChecks();
    }

    private void addChecks() {
        this.checks.add(new FlyCheck());
    }

    public Check getCheckByName(String name) {
        for (Check c : this.checks) {
            if (c == null) return null;

            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public void handleLagBack(PlayerEvent e) {
        this.checks.forEach(check -> {
            if (check == null) return;

            if (check.runCheck(e, Quantinum.acPlayer.get(e.getPlayer().getUniqueId())).failed()) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        e.getPlayer().setFlySpeed(0);
                        e.getPlayer().setWalkSpeed(0);
                    }
                }.runTaskLaterAsynchronously(Quantinum.getInstance(), 3000);
            }
        });
    }
}
