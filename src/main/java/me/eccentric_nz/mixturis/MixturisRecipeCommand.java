package me.eccentric_nz.mixturis;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;

public class MixturisRecipeCommand implements CommandExecutor {

    Mixturis plugin;

    public MixturisRecipeCommand(Mixturis plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mixturis")) {
            if (args.length < 1) {
                return false;
            }
            Player player = null;
            if (sender instanceof Player) {
                player = (Player) sender;
            }

            Recipe selectedRecipe = new ShapedRecipe(new ItemStack(Material.WOOD, 1));
            if (selectedRecipe instanceof FurnaceRecipe) {
                furnaceRecipe(player, (FurnaceRecipe) selectedRecipe);
                return true;
            } else if (selectedRecipe instanceof ShapedRecipe) {
                shapedRecipe(player, (ShapedRecipe) selectedRecipe);
                return true;
            } else if (selectedRecipe instanceof ShapelessRecipe) {
                shapelessRecipe(player, (ShapelessRecipe) selectedRecipe);
                return true;
            }
        }
        return false;
    }

    public void furnaceRecipe(Player player, FurnaceRecipe recipe) {
        player.sendMessage("recipeFurnace");
    }

    public void shapedRecipe(Player player, final ShapedRecipe recipe) {
        player.closeInventory();
        plugin.getRecipeViewers().add(player.getName());
        final InventoryView view = player.openWorkbench(null, true);
        final String[] recipeShape = recipe.getShape();
        final Map<Character, ItemStack> ingredientMap = recipe.getIngredientMap();
        for (int j = 0; j < recipeShape.length; j++) {
            for (int k = 0; k < recipeShape[j].length(); k++) {
                final ItemStack item = ingredientMap.get(recipeShape[j].toCharArray()[k]);
                if (item == null) {
                    continue;
                }
                item.setAmount(0);
                view.getTopInventory().setItem(j * 3 + k + 1, item);
            }
        }
    }

    public void shapelessRecipe(Player player, final ShapelessRecipe recipe) {
        final List<ItemStack> ingredients = recipe.getIngredientList();
        plugin.getRecipeViewers().add(player.getName());
        final InventoryView view = player.openWorkbench(null, true);
        for (int i = 0; i < ingredients.size(); i++) {
            view.setItem(i + 1, ingredients.get(i));
        }
    }

    public String getMaterialName(final ItemStack stack) {
        if (stack == null) {
            return ("recipeNothing");
        }
        return getMaterialName(stack.getType());
    }

    public String getMaterialName(final Material type) {
        if (type == null) {
            return ("recipeNothing");
        }
        return type.toString().replace("_", " ").toLowerCase(Locale.ENGLISH);
    }
}
