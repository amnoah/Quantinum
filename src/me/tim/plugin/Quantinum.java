package me.tim.plugin;

import me.tim.plugin.checks.CheckManager;
import me.tim.plugin.listener.HandleJoinLeave;
import me.tim.plugin.listener.HandlePlayer;
import me.tim.plugin.util.player.QuantPlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Quantinum extends JavaPlugin {
    public static HashMap<UUID, QuantPlayer> acPlayer = new HashMap<>();

    private PluginManager pm;
    private static Quantinum pl;

    @Override
    public void onEnable() {
        this.pm = Bukkit.getPluginManager();
        pl = this;
        this.registerEvents(pm);
        Bukkit.getOnlinePlayers().forEach(player -> acPlayer.put(player.getUniqueId(), new QuantPlayer(player)));
        System.out.println("|Quantinum|: Enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("|Quantinum|: Disabled");
    }

    private void registerEvents(PluginManager pm) {
        pm.registerEvents(new HandleJoinLeave(), this);
        pm.registerEvents(new HandlePlayer(), this);
    }

    public static Quantinum getInstance() {
        return pl;
    }
}
