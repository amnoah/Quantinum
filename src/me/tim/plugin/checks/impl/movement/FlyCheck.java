package me.tim.plugin.checks.impl.movement;

import me.tim.plugin.checks.Check;
import me.tim.plugin.checks.result.CheckResult;
import me.tim.plugin.checks.result.Level;
import me.tim.plugin.util.player.QuantPlayer;
import org.bukkit.event.player.PlayerEvent;

public class FlyCheck extends Check {
    public FlyCheck() {
        super("FlyCheck");
    }

    @Override
    public CheckResult runCheck(PlayerEvent e, QuantPlayer qp) {
        if (qp.getPlayer().isFlying() && !qp.getPlayer().getAllowFlight()) return new CheckResult(Level.DEFINITLY, "VANILLAFLY_MOVE, onground:" + qp.getPlayer().isOnGround());

        return new CheckResult(Level.PASSED, null);
    }
}
