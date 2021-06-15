package me.tim.plugin.checks.impl.player;

import me.tim.plugin.Quantinum;
import me.tim.plugin.checks.Check;
import me.tim.plugin.checks.result.CheckResult;
import me.tim.plugin.checks.result.Level;
import me.tim.plugin.util.player.QuantPlayer;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class NoFallCheck extends Check {
    private boolean lastGround, preLastGround;

    public NoFallCheck() {
        super("NoFallCheck");
    }

    @Override
    public CheckResult runCheck(PlayerEvent e, QuantPlayer qp) {
        if (e instanceof PlayerMoveEvent) {
            PlayerMoveEvent em = (PlayerMoveEvent) e;

            boolean shouldCheck = qp.isAir(qp.getPlayer().getLocation().add(0, -3, 0));
            boolean onGround = qp.proveNearGround(em.getTo());

            boolean lastGrounded = this.lastGround;
            this.lastGround = onGround;

            boolean prevLastGround = this.preLastGround;
            this.preLastGround = lastGrounded;

            if (shouldCheck) {
                if (!onGround && !lastGrounded && !prevLastGround) {
                    if (em.getPlayer().isOnGround()) {
                        return new CheckResult(Level.DEFINITLY, "PLAYER_NOFALL, using nofall hacks!");
                    }
                }
            }
        }
        return new CheckResult(Level.PASSED, null);
    }
}
