package org.inferis.furnacesgalore.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

import org.inferis.furnacesgalore.block.entity.DiamondFurnaceBlockEntity;
import org.inferis.furnacesgalore.block.entity.FurnacesGaloreBlockEntityTypes;
import com.mojang.serialization.MapCodec;

public class DiamondFurnaceBlock extends AbstractAcceleratedFurnaceBlock {
    public static final MapCodec<DiamondFurnaceBlock> CODEC = createCodec(DiamondFurnaceBlock::new);

    public DiamondFurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<DiamondFurnaceBlock> getCodec() {
       return CODEC;
    }

    protected BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerEntityType() {
        return FurnacesGaloreBlockEntityTypes.DIAMOND_FURNACE; 
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DiamondFurnaceBlockEntity(pos, state);
    }
}
