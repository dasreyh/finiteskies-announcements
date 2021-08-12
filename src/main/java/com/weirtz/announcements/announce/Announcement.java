package com.weirtz.announcements.announce;

import com.weirtz.announcements.Main;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class Announcement extends BukkitRunnable{

    private List<ServerMessage> messages;
    private boolean isEnabled;
    private Main plugin;
    private int counter;

    //Setters
    public Announcement(Main instance, List<ServerMessage> messages, boolean isEnabled){
        this.plugin = instance;
        this.messages = messages;
        this.isEnabled = isEnabled;
        this.counter = 0;
    }

    @Override
    public void run(){
        //If plugin is not enabled, stop.
        if(!isEnabled){
            cancel();
            return;
        }

        //Broadcast randomly or in listed order.
        if (plugin.getConfig().getBoolean("random-broadcast")){
            ServerMessage message = getRandomMessage(messages);
            printMessage(message);
        } else {
            if(counter == (messages.size())){
                counter = 0;
            }
            ServerMessage message = messages.get(counter);
            printMessage(message);
            counter++;
        }
    }

    private void printMessage(ServerMessage message){
        List<String> messageSentences = message.getSentences();
        for(String sentence : messageSentences){
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', sentence));
        }
    }

    public ServerMessage getRandomMessage(List<ServerMessage> strings){
        Random r = new Random();
        ServerMessage randomElement = strings.get(r.nextInt(strings.size()));
        return randomElement;
    }

}
