package me.tim.plugin.checks.impl.player;

import me.tim.plugin.checks.Check;
import me.tim.plugin.checks.result.CheckResult;
import me.tim.plugin.checks.result.Level;
import me.tim.plugin.util.player.QuantPlayer;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class NoFallCheck extends Check {
    private boolean lastGround, preLastGround;

    public NoFallCheck() {
        super("NoFallCheck", 1);
    }

    @Override
    public CheckResult runCheck(PlayerEvent e, QuantPlayer qp) {
        int vl = this.getVl();
        if (e instanceof PlayerMoveEvent) {
            if (this.getVl() > 4) e.getPlayer().kickPlayer("Using Nofall Hacks");

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
                        vl++;
                        this.setVl(vl);
                        return new CheckResult(Level.DEFINITLY, "PLAYER_NOFALL, using nofall hacks!");
                    }
                }
            }
            this.setVl(1);
        }
        return new CheckResult(Level.PASSED, null);
    }
}
