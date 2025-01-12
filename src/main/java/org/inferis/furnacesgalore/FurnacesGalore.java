package org.inferis.furnacesgalore;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import org.inferis.furnacesgalore.block.FurnacesGaloreBlocks;
import org.inferis.furnacesgalore.block.entity.FurnacesGaloreBlockEntityTypes;
import org.inferis.furnacesgalore.item.FurnacesGaloreItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FurnacesGalore implements ModInitializer {
	public static final String MODID = "furnaces-galore";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final FurnacesGaloreConfig CONFIG = new FurnacesGaloreConfig();

	public static Identifier id(String id) {
		return Identifier.of(MODID, id);
	}

	@Override
	public void onInitialize() {
		CONFIG.initialLoad();

		FurnacesGaloreBlocks.registerBlocks();
		FurnacesGaloreBlockEntityTypes.registerBlockEntityTypes();
		FurnacesGaloreItems.registerItems();
		FurnacesGaloreItems.registerItemGroups();
	}
}