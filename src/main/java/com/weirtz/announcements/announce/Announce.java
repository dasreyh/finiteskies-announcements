package com.weirtz.announcements.announce;

import com.weirtz.announcements.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class Announce {

    private List<ServerMessage> messages;
    private Main plugin;
    private FileConfiguration config;
    private String prefix;
    private int delay;
    public BukkitTask curr;

    public Announce(Main instance, List<ServerMessage> messageList) {
        this.plugin = instance;
        this.messages = messageList;
        this.config = this.plugin.getConfig();
        prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("announcer-prefix"));
        //Gets the delay and converts it to ticks (20 ticks = 1 second)
        delay = (plugin.getConfig().getInt("message-delay")*20);
    }

    //Stop announcement plugin
    public void stopAnnouncer(){
        if(curr != null){
            curr.cancel();
            curr = null;
            return;
        }
        System.out.println("Announcements are already off.");
    }

    //Start announcement plugin
    public void startAnnouncer(){
        curr = new Announcement(plugin, messages, config.getBoolean("plugin-enabled")).runTaskTimerAsynchronously(plugin, 0, delay);
    }


}
