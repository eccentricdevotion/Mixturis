/*
 *  Copyright 2013 eccentric_nz.
 */
package me.eccentric_nz.mixturis;

import java.util.Arrays;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author eccentric_nz
 */
public class MixturisFurnaceRecipe {

    private final Mixturis plugin;

    public MixturisFurnaceRecipe(Mixturis plugin) {
        this.plugin = plugin;
    }

    public void addFurnaceRecipes() {
        Set<String> furnace = plugin.getRecipesConfig().getConfigurationSection("furnace").getKeys(false);
        for (String s : furnace) {
            plugin.getServer().addRecipe(makeRecipe(s));
        }
    }

    private FurnaceRecipe makeRecipe(String s) {
        FurnaceRecipe f;
        /*
         recipe: '35:0'
         result: '35:15'
         displayname: true
         lore: "Done to a crisp"
         */
        ItemStack is;
        String[] result = plugin.getRecipesConfig().getString("furnace." + s + ".result").split(":");
        int result_id = Integer.parseInt(result[0]);
        Material result_m = Material.getMaterial(result_id);
        if (result.length == 2) {
            byte result_data = Byte.parseByte(result[1]);
            is = new ItemStack(result_m, 1, result_data);
        } else {
            is = new ItemStack(result_m, 1);
        }
        ItemMeta im = is.getItemMeta();
        boolean set_meta = false;
        if (plugin.getRecipesConfig().getBoolean("furnace." + s + ".displayname")) {
            im.setDisplayName(s);
            if (!plugin.getRecipesConfig().getString("furnace." + s + ".lore").equals("")) {
                im.setLore(Arrays.asList(plugin.getRecipesConfig().getString("furnace." + s + ".lore").split("\n")));
            }
            set_meta = true;
        }
        if (!plugin.getRecipesConfig().getString("furnace." + s + ".enchantment").equals("NONE")) {
            Enchantment e = EnchantmentWrapper.getByName(plugin.getRecipesConfig().getString("furnace." + s + ".enchantment"));
            im.addEnchant(e, plugin.getRecipesConfig().getInt("furnace." + s + ".strength"), true);
            set_meta = true;
        }
        if (set_meta) {
            is.setItemMeta(im);
        }
        String[] ingredient = plugin.getRecipesConfig().getString("furnace." + s + ".recipe").split(":");
        int recipe_id = Integer.parseInt(ingredient[0]);
        Material recipe_m = Material.getMaterial(recipe_id);
        if (ingredient.length == 2) {
            int recipe_data = Integer.parseInt(ingredient[1]);
            f = new FurnaceRecipe(is, recipe_m, recipe_data);
        } else {
            f = new FurnaceRecipe(is, recipe_m);
        }
        return f;
    }
}
