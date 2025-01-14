package org.inferis.furnacesgalore;

import org.inferis.furnacesgalore.screen.AcceleratedFurnaceScreen;
import org.inferis.furnacesgalore.screenhandler.FurnacesGaloreScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class FurnacesGaloreClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HandledScreens.register(FurnacesGaloreScreenHandlers.ACCELERATED_FURNACE, AcceleratedFurnaceScreen::new);
	}
}