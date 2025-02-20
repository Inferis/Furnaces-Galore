package org.inferis.furnacesgalore.block.entity;

import org.inferis.furnacesgalore.FurnacesGalore;
import org.inferis.furnacesgalore.block.FurnacesGaloreBlocks;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class FurnacesGaloreBlockEntityTypes {
    public static final BlockEntityType<CopperFurnaceBlockEntity> COPPER_FURNACE = register(
        "copper_furnace", 
        FabricBlockEntityTypeBuilder.<CopperFurnaceBlockEntity>create(CopperFurnaceBlockEntity::new, FurnacesGaloreBlocks.COPPER_FURNACE).build());
    public static final BlockEntityType<IronFurnaceBlockEntity> IRON_FURNACE = register(
        "iron_furnace", 
        FabricBlockEntityTypeBuilder.<IronFurnaceBlockEntity>create(IronFurnaceBlockEntity::new, FurnacesGaloreBlocks.IRON_FURNACE).build());
        public static final BlockEntityType<GoldFurnaceBlockEntity> GOLD_FURNACE = register(
        "gold_furnace", 
        FabricBlockEntityTypeBuilder.<GoldFurnaceBlockEntity>create(GoldFurnaceBlockEntity::new, FurnacesGaloreBlocks.GOLD_FURNACE).build());
    public static final BlockEntityType<DiamondFurnaceBlockEntity> DIAMOND_FURNACE = register(
        "diamond_furnace", 
        FabricBlockEntityTypeBuilder.<DiamondFurnaceBlockEntity>create(DiamondFurnaceBlockEntity::new, FurnacesGaloreBlocks.DIAMOND_FURNACE).build());
    public static final BlockEntityType<NetheriteFurnaceBlockEntity> NETHERITE_FURNACE = register(
        "netherite_furnace", 
        FabricBlockEntityTypeBuilder.<NetheriteFurnaceBlockEntity>create(NetheriteFurnaceBlockEntity::new, FurnacesGaloreBlocks.NETHERITE_FURNACE).build());
                                    
        

    public static <T extends BlockEntityType<?>> T register(String identifier, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, FurnacesGalore.id(identifier), blockEntityType);
    }

    public static void registerBlockEntityTypes() {
        // so the static declarations above are properly initialized at the right time...
    }
}
