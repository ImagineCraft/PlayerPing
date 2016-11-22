package org.imaginecraft.playerping;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerPing extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        Player target = null;
        if (args.length > 0) {
            target = getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Can't find player " + args[0]);
                return true;
            }
        } else if (sender instanceof Player) target = (Player) sender;
        if (target != null) {
            sender.sendMessage(ChatColor.GOLD + (target == sender ? "Your" : target.getDisplayName() + "'s") + " current ping: " + ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + ((CraftPlayer) target).getHandle().ping + "ms" + ChatColor.DARK_GRAY + "]");
            return true;
        }
        return false;
    }
}