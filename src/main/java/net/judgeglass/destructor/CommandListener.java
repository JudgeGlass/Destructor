package net.judgeglass.destructor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

            if(handItem == null){
                player.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.BLUE + "You are currently not holding anything!");
                return true;
            }

            int initialAmount = handItem.getAmount();

            Recipe itemRecipe = Bukkit.getServer().getRecipesFor(handItem).get(0);

            List<ItemStack> itemsToDrop = new ArrayList<>();

            if(itemRecipe instanceof ShapedRecipe) {
                ShapedRecipe shapedRecipe = (ShapedRecipe) itemRecipe;

                Iterator iterator = shapedRecipe.getIngredientMap().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry element = (Map.Entry) iterator.next();
                    ItemStack item = (ItemStack) element.getValue();
                    if (item != null) {
                        if(item.getData().getItemType() == Material.WOOD){
                            itemsToDrop.add(new ItemStack(Material.WOOD, 1, (short)0, (byte)0));
                        }else {
                            itemsToDrop.add(item);
                        }
                    }
                }
            }else{
                player.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.BLUE + "The block you wanted to deconstruct is non-craftable");
                return true;
            }

            for(ItemStack item: itemsToDrop) {
                if(item.getData().getItemType() == Material.LOG && handItem.getData().getItemType() == Material.WOOD){
                    if(handItem.getAmount() < 4){
                        player.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.BLUE + "Not enough items!");
                        return true;
                    }else{
                        initialAmount -= 3;
                    }
                }
                player.getWorld().dropItemNaturally(player.getLocation(), item);
            }

            itemsToDrop.clear();

            if(initialAmount > 1) {
                handItem.setAmount(initialAmount - 1);
            }else{
                handItem.setAmount(0);
            }

            player.sendMessage(ChatColor.RED + "Destructor: " + ChatColor.BLUE + "DONE!");

            return true;
        }
        return false;
    }
}
