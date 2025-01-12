package org.inferis.furnacesgalore.block;

import org.inferis.furnacesgalore.FurnacesGalore;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class FurnacesGaloreBlocks {
    public static IronFurnaceBlock IRON_FURNACE;
    public static GoldFurnaceBlock GOLD_FURNACE;

    interface BlockMaker<T extends Block> {
        T makeBlock(RegistryKey<Block> key);
    }

    private static <T extends Block> T registerBlock(String name, BlockMaker<T> blockMaker) {
        FurnacesGalore.LOGGER.info("Registering block: " + FurnacesGalore.MODID + ":" + name);

        var identifier = FurnacesGalore.id(name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        return Registry.register(
            Registries.BLOCK,
            identifier,
            blockMaker.makeBlock(key));
    }

    public static void registerBlocks() {
        IRON_FURNACE = registerBlock("iron_furnace", key -> { 
            return new IronFurnaceBlock(AbstractBlock.Settings.copy(Blocks.STONE).registryKey(key)); 
        });       
        GOLD_FURNACE = registerBlock("gold_furnace", key -> { 
            return new GoldFurnaceBlock(AbstractBlock.Settings.copy(Blocks.FURNACE).registryKey(key)); 
        });       
    }
}
