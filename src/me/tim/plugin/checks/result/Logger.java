package me.tim.plugin.checks.result;

import me.tim.plugin.util.player.QuantPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class Logger {

    public static void logCheck(CheckResult r, Player qp) {
        String msg = "§dQuantinum§7: " + qp.getName() + "-" + r.getLevel().toString() + " " + r.getMessage();

        if (qp.hasPermission("quantinum.notify")) {
            qp.sendMessage(msg);
        }
    }
}
