package me.eccentric_nz.mixturis;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Mixturis extends JavaPlugin {

    private FileConfiguration recipesConfig;
    private MixturisUtilities u;

    @Override
    public void onDisable() {
        // TODO: Place any custom disable code here.
    }

    @Override
    public void onEnable() {
        u = new MixturisUtilities(this);
        saveDefaultConfig();
        loadRecipeConfig();
        new MixturisShapedRecipe(this).addShapedRecipes();
        new MixturisShapelessRecipe(this).addShapelessRecipes();
        new MixturisFurnaceRecipe(this).addFurnaceRecipes();
    }

    private void loadRecipeConfig() {
        u.copy(getDataFolder() + File.separator + "recipes.yml", getResource("recipes.yml"));
        this.recipesConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "recipes.yml"));
    }

    public MixturisUtilities getU() {
        return u;
    }

    public FileConfiguration getRecipesConfig() {
        return recipesConfig;
    }
}
