/*
 *  Copyright 2013 eccentric_nz.
 */
package me.eccentric_nz.mixturis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author eccentric_nz
 */
public class MixturisUtilities {

    Mixturis plugin;

    public MixturisUtilities(Mixturis plugin) {
        this.plugin = plugin;
    }

    /**
     * Copies the schematic file to the TARDIS plugin directory if it is not
     * present.
     *
     * @param filepath the path to the file to write to
     * @param in the input file to read from
     * @return a File
     */
    public File copy(String filepath, InputStream in) {
        File file = new File(filepath);
        if (!file.exists()) {
            OutputStream out = null;
            try {
                out = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int len;
                try {
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                } catch (IOException io) {
                    System.err.println("[Mixturis] Could not save the file (" + file.toString() + ").");
                } finally {
                    try {
                        out.close();
                    } catch (IOException e) {
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("[Mixturis] File not found.");
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return file;
    }

    /**
     * Method to find the configuration section a recipe is in. Thought this
     * might be useful to allow custom recipes to contain items that have been
     * crafted from another custom recipe. But you can't set an ingredient to an
     * ItemStack so it is pretty much useless.
     *
     * @param s the recipe to search for
     * @return the recipe type
     */
    public RecipeType findConfigSection(String s) {
        if (plugin.getRecipesConfig().isConfigurationSection("shaped." + s)) {
            return RecipeType.SHAPED;
        }
        if (plugin.getRecipesConfig().isConfigurationSection("shapeless." + s)) {
            return RecipeType.SHAPELESS;
        }
        if (plugin.getRecipesConfig().isConfigurationSection("furnace." + s)) {
            return RecipeType.FURNACE;
        }
        return RecipeType.NONE;
    }

    public enum RecipeType {

        SHAPED, SHAPELESS, FURNACE, NONE
    }
}
