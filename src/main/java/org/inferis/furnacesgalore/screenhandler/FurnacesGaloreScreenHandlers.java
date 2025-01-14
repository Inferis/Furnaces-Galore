package org.inferis.furnacesgalore.screenhandler;

import org.inferis.furnacesgalore.FurnacesGalore;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;

public class FurnacesGaloreScreenHandlers {
	public static final ScreenHandlerType<AcceleratedFurnaceScreenHandler> ACCELERATED_FURNACE = Registry.register(Registries.SCREEN_HANDLER, FurnacesGalore.id("accelerated_furnace"), new ScreenHandlerType<>(AcceleratedFurnaceScreenHandler::new, FeatureSet.empty()));
    
    public static void registerScreenHandlers() {
    }
}
