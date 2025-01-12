package org.inferis.furnacesgalore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import net.fabricmc.loader.api.FabricLoader;

public class FurnacesGaloreConfig {
	private static final String CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("furnaces-galore.cfg").toString();

    public double copperAcceleration = 1.5;
    public double ironAcceleration = 2.1;
    public double goldAcceleration = 3.2;
    public double diamondAcceleration = 5.4;
    public double netheriteAcceleration = 9.8;
    
    public void save() {
        var file = new File(CONFIG_FILE);
        try {
            var writer = Files.asCharSink(file, Charsets.UTF_8);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(this));
        } catch (IOException e) {
            FurnacesGalore.LOGGER.error("Could not save config", e);
        }
    }

    public boolean load() {
        var file = new File(CONFIG_FILE);
        if (!file.exists()) {
            return false;
        }

        try {
            var json = Files.asCharSource(file, Charsets.UTF_8).read();
            Gson gson = new Gson();
            var loadedConfig = gson.fromJson(json, FurnacesGaloreConfig.class);

            copperAcceleration = loadedConfig.copperAcceleration;
            ironAcceleration = loadedConfig.ironAcceleration;
            goldAcceleration = loadedConfig.goldAcceleration;
            diamondAcceleration = loadedConfig.diamondAcceleration;
            netheriteAcceleration = loadedConfig.netheriteAcceleration;
            return true;
        }
        catch (FileNotFoundException e) {
            FurnacesGalore.LOGGER.error("Could not open config", e);
        }
        catch (IOException e) {
            FurnacesGalore.LOGGER.error("Could not read config", e);
        }
        catch (JsonSyntaxException e) {
            FurnacesGalore.LOGGER.error("Could not read config, syntax error", e);
        }
        return false;
    }

    public void initialLoad() {
        // if we didn't load, no file (or a corrupt one) is present, so save
        // our defaults.
        if (!load()) {
            save();
        }
    }
}
