package me.tim.plugin.checks;

import me.tim.plugin.checks.result.CheckResult;
import me.tim.plugin.util.player.QuantPlayer;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;

public class Check {
    private String name;
    private int vl;

    public Check(String name, int vl) {
        this.name = name;
        this.vl = vl;
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

    public int getVl() {
        return vl;
    }

    public void setVl(int vl) {
        this.vl = vl;
    }
}
