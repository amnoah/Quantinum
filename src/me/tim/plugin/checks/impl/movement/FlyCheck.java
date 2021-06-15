package me.tim.plugin.checks.impl.movement;

import me.tim.plugin.checks.Check;
import me.tim.plugin.checks.result.CheckResult;
import me.tim.plugin.checks.result.Level;
import me.tim.plugin.util.math.Calculation;
import me.tim.plugin.util.player.QuantPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class FlyCheck extends Check {
    private boolean lastGround, preLastGround;
    private double lastYDist;

    public FlyCheck() {
        super("FlyCheck");
    }

    @Override
    public CheckResult runCheck(PlayerEvent e, QuantPlayer qp) {
        if (e instanceof PlayerMoveEvent) {
            PlayerMoveEvent em = (PlayerMoveEvent) e;
            if (qp.getPlayer().getAllowFlight()) return null;

            double yDist = em.getTo().getY() - em.getFrom().getY();
            double lastYDist = this.lastYDist;
            this.lastYDist = yDist;

            double predDist = (lastYDist - 0.08D) * qp.getFlyMotionYMultiplier();

            boolean onGround = qp.proveNearGround(em.getTo());
            boolean lastGrounded = this.lastGround;
            this.lastGround = onGround;
            boolean prevLastGround = this.preLastGround;
            this.preLastGround = lastGrounded;

            if (!onGround && !lastGrounded && !prevLastGround) {
                if (Math.abs(predDist) >= 0.005D) {
                    if (!Calculation.isRoughlyEqual(yDist, predDist, 0.001)) {
                        return new CheckResult(Level.DEFINITLY, "MOVE_FLY, using fly-hacks");
                    }
                }
            }
        }
        return new CheckResult(Level.PASSED, null);
    }
}
