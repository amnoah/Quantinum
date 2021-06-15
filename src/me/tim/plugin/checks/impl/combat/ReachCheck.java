package me.tim.plugin.checks.impl.combat;

import me.tim.plugin.checks.Check;
import me.tim.plugin.checks.result.CheckResult;
import me.tim.plugin.checks.result.Level;
import me.tim.plugin.util.player.QuantPlayer;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;

public class ReachCheck extends Check {
    public ReachCheck() {
        super("ReachCombatCheck");
    }

    @Override
    public CheckResult runCheck(EntityEvent e, QuantPlayer qp) {
        if (e instanceof EntityDamageEvent) {
            EntityDamageEvent ed = (EntityDamageEvent) e;
            if (qp.getDistToLocation(ed.getEntity().getLocation()) > 4.5) {
                return new CheckResult(Level.DEFINITLY, "COMBAT_REACH, tryed to reach to far (" + qp.getDistToLocation(ed.getEntity().getLocation()) + ")");
            }
        }
        return new CheckResult(Level.PASSED, null);
    }
}
