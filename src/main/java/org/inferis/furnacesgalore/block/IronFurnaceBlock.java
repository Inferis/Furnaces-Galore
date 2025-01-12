package org.inferis.furnacesgalore.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import org.inferis.furnacesgalore.block.entity.FurnacesGaloreBlockEntityTypes;
import org.inferis.furnacesgalore.block.entity.IronFurnaceBlockEntity;

import com.mojang.serialization.MapCodec;

public class IronFurnaceBlock extends AbstractAcceleratedFurnaceBlock {
    public static final MapCodec<IronFurnaceBlock> CODEC = createCodec(IronFurnaceBlock::new);

    public IronFurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<IronFurnaceBlock> getCodec() {
       return CODEC;
    }

    protected BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerEntityType() {
        return FurnacesGaloreBlockEntityTypes.IRON_FURNACE; 
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new IronFurnaceBlockEntity(pos, state);
    }
}
