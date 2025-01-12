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
    public static FurnaceBlockItem IRON_FURNACE;
    public static FurnaceBlockItem GOLD_FURNACE;

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

        IRON_FURNACE = registerItem("iron_furnace", key -> { 
            return new FurnaceBlockItem(FurnacesGaloreBlocks.IRON_FURNACE, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key)); 
        });
        GOLD_FURNACE = registerItem("gold_furnace", key -> { 
            return new FurnaceBlockItem(FurnacesGaloreBlocks.GOLD_FURNACE, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key)); 
        });
    }

    public static void registerItemGroups() {
        FurnacesGalore.LOGGER.info("Registering item groups for " + FurnacesGalore.MODID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.add(IRON_FURNACE);
            content.add(GOLD_FURNACE);
        });
    }    
}
