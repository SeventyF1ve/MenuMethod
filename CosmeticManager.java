package org.seventyf1ve.cosmetics;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.seventyf1ve.PurpleMenu;
import org.seventyf1ve.utilities.item.ItemBuilder;

import java.util.Map;

public class CosmeticManager {
    @Getter private final Map<String, CosmeticData> cosmetics;

    public CosmeticManager(){
        this.cosmetics = Maps.newHashMap();
    }

    public void load() {
        cosmetics.clear();

        ConfigurationSection section = PurpleMenu.getInstance().getCosmetic().getConfigurationSection("COSMETIC_MENU.COSMETICS");

        if (section == null) return;

        for (String cosmetic : section.getKeys(false)) {
            CosmeticData cosmeticData = new CosmeticData(cosmetic);
            cosmeticData.setHead(section.getBoolean(cosmetic + ".ICON.SKULL.ENABLE"));
            ItemStack itemStack;

            if (cosmeticData.isHead()) {
                if (!section.getString(cosmetic + ".ICON.SKULL.CUSTOM").isEmpty()) {
                    itemStack = new ItemBuilder(Material.SKULL_ITEM, 1, 3)
                            .setName(section.getString(cosmetic + ".ICON.DISPLAYNAME"))
                            .setLore(section.getStringList(cosmetic + ".ICON.DESCRIPTION"))
                            .setCustomHead(section.getString(cosmetic + ".ICON.SKULL.CUSTOM"))
                            .build();
                } else if (!section.getString(cosmetic + ".ICON.SKULL.PLAYER").isEmpty()) {
                    itemStack = new ItemBuilder(Material.SKULL_ITEM, 1, 3)
                            .setName(section.getString(cosmetic + ".ICON.DISPLAYNAME"))
                            .setLore(section.getStringList(cosmetic + ".ICON.DESCRIPTION"))
                            .setSkullOwner(section.getString(cosmetic + ".ICON.SKULL.PLAYER"))
                            .build();
                }
                else{
                    itemStack = new ItemBuilder(section.getString(cosmetic + ".ICON.MATERIAL"))
                            .setDurability(section.getInt(cosmetic + ".ICON.DATA"))
                            .setName(section.getString(cosmetic + ".ICON.DISPLAYNAME"))
                            .setLore(section.getStringList(cosmetic + ".ICON.DESCRIPTION"))
                            .build();
                }
                cosmeticData.setIcon(itemStack);
                cosmeticData.setSlot(section.getInt(cosmetic + ".ICON.SLOT"));
                cosmeticData.setAction(section.getString(cosmetic + ".ICON.ACTION"));
                cosmetics.put(cosmetic, cosmeticData);
            }
        }
    }
}
