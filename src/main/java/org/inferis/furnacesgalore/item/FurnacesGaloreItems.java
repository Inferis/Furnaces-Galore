package org.inferis.furnacesgalore.item;

import org.inferis.furnacesgalore.FurnacesGalore;
import org.inferis.furnacesgalore.block.FurnacesGaloreBlocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class FurnacesGaloreItems {
    public static FurnaceBlockItem COPPER_FURNACE;
    public static FurnaceBlockItem IRON_FURNACE;
    public static FurnaceBlockItem GOLD_FURNACE;
    public static FurnaceBlockItem DIAMOND_FURNACE;
    public static FurnaceBlockItem NETHERITE_FURNACE;

    interface ItemMaker<T extends Item> {
        T makeItem(RegistryKey<Item> key);
    }

    private static <T extends Item> T registerItem(String name, ItemMaker<T> itemMaker) {
        FurnacesGalore.LOGGER.info("Registering item: " + FurnacesGalore.MODID + ":" + name);

        var identifier = FurnacesGalore.id(name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, identifier);
        T item = itemMaker.makeItem(key);
        return Registry.register(Registries.ITEM, identifier, item);
    }

    public static void registerItems() {
        FurnacesGalore.LOGGER.info("Registering mod items for " + FurnacesGalore.MODID);

        COPPER_FURNACE = registerItem("copper_furnace", key -> { 
            return new FurnaceBlockItem(FurnacesGaloreBlocks.COPPER_FURNACE, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key)); 
        });
        IRON_FURNACE = registerItem("iron_furnace", key -> { 
            return new FurnaceBlockItem(FurnacesGaloreBlocks.IRON_FURNACE, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key)); 
        });
        GOLD_FURNACE = registerItem("gold_furnace", key -> { 
            return new FurnaceBlockItem(FurnacesGaloreBlocks.GOLD_FURNACE, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key)); 
        });
        DIAMOND_FURNACE = registerItem("diamond_furnace", key -> { 
            return new FurnaceBlockItem(FurnacesGaloreBlocks.DIAMOND_FURNACE, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key)); 
        });
        NETHERITE_FURNACE = registerItem("netherite_furnace", key -> { 
            return new FurnaceBlockItem(FurnacesGaloreBlocks.NETHERITE_FURNACE, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key)); 
        });
    }

    public static void registerItemGroups() {
        FurnacesGalore.LOGGER.info("Registering item groups for " + FurnacesGalore.MODID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.add(COPPER_FURNACE);
            content.add(IRON_FURNACE);
            content.add(GOLD_FURNACE);
            content.add(DIAMOND_FURNACE);
            content.add(NETHERITE_FURNACE);
        });
    }    
}
