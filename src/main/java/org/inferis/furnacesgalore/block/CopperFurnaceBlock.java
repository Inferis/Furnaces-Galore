package org.inferis.furnacesgalore.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

import org.inferis.furnacesgalore.block.entity.CopperFurnaceBlockEntity;
import org.inferis.furnacesgalore.block.entity.FurnacesGaloreBlockEntityTypes;
import com.mojang.serialization.MapCodec;

public class CopperFurnaceBlock extends AbstractAcceleratedFurnaceBlock {
    public static final MapCodec<CopperFurnaceBlock> CODEC = createCodec(CopperFurnaceBlock::new);

    public CopperFurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<CopperFurnaceBlock> getCodec() {
       return CODEC;
    }

    protected BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerEntityType() {
        return FurnacesGaloreBlockEntityTypes.COPPER_FURNACE; 
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CopperFurnaceBlockEntity(pos, state);
    }
}
