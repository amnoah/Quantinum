package me.tim.plugin.checks.impl.player;

import me.tim.plugin.Quantinum;
import me.tim.plugin.checks.Check;
import me.tim.plugin.checks.result.CheckResult;
import me.tim.plugin.checks.result.Level;
import me.tim.plugin.util.player.QuantPlayer;
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

            boolean onGround = qp.proveNearGround(em.getTo());

            boolean lastGrounded = this.lastGround;
            this.lastGround = onGround;

            boolean prevLastGround = this.preLastGround;
            this.preLastGround = lastGrounded;

            if (!onGround && !lastGrounded && !prevLastGround) {
                if (em.getPlayer().isOnGround()) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            em.getPlayer().damage(2);
                        }
                    }.runTaskTimerAsynchronously(Quantinum.getInstance(), 0, 700);
                    return new CheckResult(Level.DEFINITLY, "PLAYER_NOFALL, using nofall hacks!");
                }
            }
        }
        return new CheckResult(Level.PASSED, null);
    }
}
