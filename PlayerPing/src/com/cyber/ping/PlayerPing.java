/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyber.ping;

import net.minecraft.server.v1_10_R1.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author robin
 */
public class PlayerPing extends JavaPlugin{
    
    Server server;
    
    @Override
    public void onEnable(){
        System.out.println("PlayerPing enabled");
        server = this.getServer();
    }
    
    @Override
    public void onDisable(){
        System.out.println("PlayerPing disabled");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "The console cannot perform this command!");
            return true;
        }
        if(command.getName().equalsIgnoreCase("ping")){
            Player target;
            if(args.length == 0){
                target = (Player) sender;
                printPing((Player)sender, target);
            }
            else if(isPlayer(args[0])){
                target = getPlayer(args[0]);
                printPing((Player)sender, target);
            }
            else{
                sender.sendMessage(ChatColor.RED+"Can't find player "+args[0]);
            }
        }
        return false;
    }
    
    private Integer getPing(Player p){
        CraftPlayer pingc = (CraftPlayer) p;
        EntityPlayer pinge = pingc.getHandle();
        return pinge.ping;
    }
    
    private boolean isPlayer(String name){
        for(Player p : server.getOnlinePlayers()){
            if(p.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    
    
    private Player getPlayer(String name){
        for(Player p : server.getOnlinePlayers()){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }
    
    private void printPing(Player sender, Player target){
        Integer ping = Integer.valueOf(getPing(target));
        
        if(sender.equals(target)){
            sender.sendMessage(ChatColor.GOLD+"Your current ping: " + ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + ping + "ms" + ChatColor.DARK_GRAY + "]");
        }
        else{
            sender.sendMessage(ChatColor.GOLD+target.getDisplayName()+"'s current ping: " + ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + ping + "ms" + ChatColor.DARK_GRAY + "]");
        }
    }
}