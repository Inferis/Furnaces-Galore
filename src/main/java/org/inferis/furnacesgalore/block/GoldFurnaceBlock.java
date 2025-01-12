package org.inferis.furnacesgalore.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import org.inferis.furnacesgalore.block.entity.FurnacesGaloreBlockEntityTypes;
import org.inferis.furnacesgalore.block.entity.GoldFurnaceBlockEntity;
import com.mojang.serialization.MapCodec;

public class GoldFurnaceBlock extends AbstractAcceleratedFurnaceBlock {
    public static final MapCodec<GoldFurnaceBlock> CODEC = createCodec(GoldFurnaceBlock::new);

    public GoldFurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<GoldFurnaceBlock> getCodec() {
       return CODEC;
    }

    protected BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerEntityType() {
        return FurnacesGaloreBlockEntityTypes.GOLD_FURNACE; 
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GoldFurnaceBlockEntity(pos, state);
    }
}
