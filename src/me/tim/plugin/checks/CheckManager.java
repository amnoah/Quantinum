package me.tim.plugin.checks;

import me.tim.plugin.Quantinum;
import me.tim.plugin.checks.impl.combat.ReachCheck;
import me.tim.plugin.checks.impl.player.NoFallCheck;
import me.tim.plugin.checks.result.CheckResult;
import me.tim.plugin.checks.result.Logger;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.swing.text.html.parser.Entity;
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
        this.checks.add(new ReachCheck());
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
            if (check.getName().contains("Combat")) return;

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

    public void handleLagBack(EntityEvent e) {
        this.checks.forEach(check -> {
            if (check == null) return;
            if (!check.getName().contains("Combat")) return;

            CheckResult cr = check.runCheck(e, Quantinum.acPlayer.get(e.getEntity().getUniqueId()));
            if (cr.failed()) {
                if (e instanceof EntityDamageEvent) {
                    EntityDamageEvent em = (EntityDamageEvent) e;
                    em.setCancelled(true);
                }
                Logger.logCheck(cr, e.getEntity());
            }
        });
    }
}
