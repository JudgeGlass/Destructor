package net.judgeglass.destructor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("destructor")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You appear to not be a player");
                return false;
            }

            Player player = (Player)sender;
            player.sendMessage(ChatColor.RED + "Destructor: " + ChatColor.BLUE + "Init Destructor...");

            ItemStack handItem = player.getInventory().getItemInMainHand();
            


            return true;
        }
        return false;
    }
}
