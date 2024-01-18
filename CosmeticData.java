package org.seventyf1ve.cosmetics;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Getter @Setter
public class CosmeticData {
    private final String name;
    private boolean head;
    private ItemStack icon;
    private int slot;
    private String action;

    public CosmeticData(String name){
        this.name = name;
    }

    public ItemStack getIcon(Player player){
        ItemStack item = icon.clone();

        if(item.hasItemMeta()){
            ItemMeta meta = item.getItemMeta();

            if(meta.hasDisplayName()){
                String displayName = meta.getDisplayName();
                meta.setDisplayName(PlaceholderAPI.setPlaceholders(player, displayName));
            }

            if(meta.hasLore()){
                List<String> description = meta.getLore();
                meta.setLore(PlaceholderAPI.setPlaceholders(player, description));
            }
            item.setItemMeta(meta);
        }
        return item;
    }
}
