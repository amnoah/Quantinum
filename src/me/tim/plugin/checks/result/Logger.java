package me.tim.plugin.checks.result;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Logger {

    public static void logCheck(CheckResult r, UUID uuid) {
        Player qp = Bukkit.getPlayer(uuid);
        String msg = "§dQuantinum§7: " + qp.getName() + "-" + r.getLevel().toString() + " " + r.getMessage();

        if (qp.hasPermission("quantinum.notify")) {
            qp.sendMessage(msg);
        }
    }

    public static void logCheck(CheckResult r, Entity e) {
        String msg = "§dQuantinum§7: " + e.getName() + "-" + r.getLevel().toString() + " " + r.getMessage();

        if (e.hasPermission("quantinum.notify")) {
            e.sendMessage(msg);
        }
    }
}
