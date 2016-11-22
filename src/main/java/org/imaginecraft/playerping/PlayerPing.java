/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.imaginecraft.playerping;

import net.minecraft.server.v1_10_R1.EntityPlayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author robin
 */
public class PlayerPing extends JavaPlugin {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        Player target = null;
        if (args.length > 0) {
            target = getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Can't find player " + args[0]);
                return true;
            }
        }
        else if (sender instanceof Player) target = (Player)sender;
        if (target != null) {
            int ping = getPing(target);
            sender.sendMessage(ChatColor.GOLD + (target == sender ? "Your" : target.getDisplayName() + "'s") + " current ping: " + ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + ping + "ms" + ChatColor.DARK_GRAY + "]");
            return true;
        }
        return false;
    }
    
    private int getPing(Player p) {
        CraftPlayer pingc = (CraftPlayer) p;
        EntityPlayer pinge = pingc.getHandle();
        return pinge.ping;
    }
    
    
    private Player getPlayer(String name) {
        for(Player p : getServer().getOnlinePlayers()){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }
}