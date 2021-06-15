package me.tim.plugin.checks;

import me.tim.plugin.checks.result.CheckResult;
import me.tim.plugin.util.player.QuantPlayer;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;

public class Check {
    private String name;

    public Check(String name) {
        this.name = name;
    }

    public CheckResult runCheck(PlayerEvent e, QuantPlayer qp) {
        return null;
    }
    public CheckResult runCheck(EntityEvent e, QuantPlayer qp) {
        return null;
    }

    public String getName() {
        return name;
    }
}
