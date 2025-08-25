package com.yourdomain.ClickToPutThings;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;



public class ClickToPutThings implements Listener {
    @EventHandler
    void putthingstobox(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getAction() == Action.LEFT_CLICK_BLOCK && event.getPlayer().getGameMode() == GameMode.SURVIVAL){
            if(event.getClickedBlock().getType() == Material.CHEST){
                Block clickedBlock = event.getClickedBlock();
                ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
                if(itemInMainHand == null){
                    player.sendMessage(ChatColor.RED + "你的手上没有方块");
                }
                else{
                    Chest chest = (Chest) clickedBlock.getState();
                    Inventory chestInventory = chest.getInventory();
                    ItemStack itemToadd = itemInMainHand.clone();
                    java.util.HashMap<Integer, ItemStack> leftOver = chestInventory.addItem(itemToadd);
                    if(leftOver.isEmpty()){
                        player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                        player.sendMessage(ChatColor.GOLD + "物品已放入");
                    }
                    else{
                        ItemStack itemStack = leftOver.get(0);
                        int addedAmount = itemInMainHand.getAmount() - itemStack.getAmount();
                        if(addedAmount > 0) {
                            player.getInventory().setItemInMainHand(itemStack);
                            player.sendMessage(ChatColor.RED + "已放入" + addedAmount + "个物品至箱子。您的箱子已满。");
                        }
                        else{
                            player.sendMessage(ChatColor.RED + "箱子已满。");
                        }
                    }
                }
            }
        }
    }
}

