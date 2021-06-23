package me.tim.plugin.checks;

import me.tim.plugin.Quantinum;
import me.tim.plugin.checks.impl.player.NoFallCheck;
import me.tim.plugin.checks.result.CheckResult;
import me.tim.plugin.checks.result.Logger;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class CheckManager {
    private List<Check> checks;

    public CheckManager() {
        this.checks = new ArrayList<>();
        this.addChecks();
    }

    private void addChecks() {
        //this.checks.add(new FlyCheck());
        this.checks.add(new NoFallCheck());
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

            CheckResult cr = check.runCheck(e, Quantinum.acPlayer.get(e.getPlayer().getUniqueId()));
            if (cr.failed()) {
                if (e instanceof PlayerMoveEvent) {
                    PlayerMoveEvent em = (PlayerMoveEvent) e;
                    em.setTo(em.getFrom());
                }
                Logger.logCheck(cr, e.getPlayer().getUniqueId());
            }
        });
    }
}
